package com.incarcloud.common.file.impl;

import com.incarcloud.common.config.settings.FtpProperties;
import com.incarcloud.common.file.FtpService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.Iterator;
import java.util.Map;
import java.util.UUID;

/**
 * FTP文件服务实现
 *
 * @author Aaric, created on 2019-08-15T17:51.
 * @since 0.8.0-SNAPSHOT
 */
@Slf4j
@Service
public class FtpServiceImpl implements FtpService {

    @Autowired
    private FtpProperties ftpProperties;

    @Override
    public boolean isHasFile(String remotePath) {
        // 定义是否成功切换工作目录变量
        boolean change = false;

        // 获得FTPClient对象
        FTPClient ftpClient = getFTPClient();
        if (null != ftpClient) {
            try {
                // 基本判断
                if (StringUtils.isNotBlank(remotePath)) {
                    // 获得查询工作目录名称
                    String queryPathName = remotePath.substring(0, remotePath.lastIndexOf("/") + 1);

                    // 先判断目录是否存在，再判断文件是否存在
                    change = ftpClient.changeWorkingDirectory(getStringForIso(queryPathName));
                    if (!change) {
                        // 目录不存在，故文件不存在
                        return false;
                    } else {
                        // 获得查询文件名称
                        String queryFileName = remotePath.substring(remotePath.lastIndexOf("/") + 1);
                        // 遍历该目录的所有文件，检查是否存在文件
                        // flag: true->存在该文件 | false->不存在该文件
                        boolean flag = false;
                        for (FTPFile ftpFile : ftpClient.listFiles()) {
                            // 比较查询的文件名字和遍历查询得到的文件名称
                            if (StringUtils.equals(queryFileName, ftpFile.getName())) {
                                flag = true;
                                break;
                            }
                        }
                        return flag;
                    }

                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                try {
                    // 断开连接，释放连接资源
                    ftpClient.disconnect();
                    log.info("关闭FTP连接...");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return true;
    }

    @Override
    public boolean uploadFile(String remotePath, File uploadFile) {
        // 定义上传状态变量
        boolean flag = false;

        // 初始化FTPClient对象
        FTPClient ftpClient = getFTPClient();
        if (null != ftpClient) {
            try {
                // 上传文件
                flag = uploadFile(ftpClient, remotePath, uploadFile);

                // 断开连接，释放连接资源
                ftpClient.disconnect();
                log.info("关闭FTP连接...");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return flag;
    }

    @Override
    public void uploadFiles(Map<String, File> mapUploadFiles) {
        // 初始化FTPClient对象
        FTPClient ftpClient = getFTPClient();
        if (null != ftpClient) {
            try {
                // 上传文件
                if (null != mapUploadFiles && 0 != mapUploadFiles.size()) {
                    Map.Entry<String, File> entry = null;
                    Iterator<Map.Entry<String, File>> it = mapUploadFiles.entrySet().iterator();
                    while (it.hasNext()) {
                        entry = it.next();
                        uploadFile(ftpClient, entry.getKey(), entry.getValue());
                    }
                }

                // 断开连接，释放连接资源
                ftpClient.disconnect();
                log.info("关闭FTP连接...");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public File downloadFile(String remotePath) {
        // 初始化FTPClient对象
        FTPClient ftpClient = getFTPClient();
        if (null != ftpClient) {
            try {
                // 下载文件
                File thisFile = downloadFile(ftpClient, remotePath);

                // 断开连接，释放连接资源
                ftpClient.disconnect();
                log.info("关闭FTP连接...");

                // 返回下载文件
                return thisFile;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    /**
     * 初始化与FTP建立连接
     *
     * @return
     * @throws IOException
     */
    private FTPClient getFTPClient() {
        try {
            // 初始化FTPClient
            FTPClient ftpClient = new FTPClient();

            // 设置通用参数
            ftpClient.connect(ftpProperties.getHostname(), ftpProperties.getPort());
            ftpClient.login(ftpProperties.getUsername(), ftpProperties.getPassword());
            ftpClient.setControlEncoding(FtpProperties.DEFAULT_HTTP_ENCODING_UTF_8);
            ftpClient.setConnectTimeout(ftpProperties.getConnectTimeout());
            ftpClient.setDataTimeout(ftpProperties.getDataTimeout());
            ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
            ftpClient.enterLocalPassiveMode();

            // 如果连接失败则关闭连接
            if (FTPReply.isPositiveCompletion(ftpClient.getReplyCode())) {
                // 连接成功
                log.info("连接FTP服务器成功...");
            } else {
                // 连接失败
                log.info("连接FTP服务器失败...");
                ftpClient.disconnect();
            }

            return ftpClient;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * 获得ISO_8859_1编码字符串，解决FTP中文乱码问题
     *
     * @param string 字符串
     * @return
     * @throws UnsupportedEncodingException
     */
    private String getStringForIso(String string) throws UnsupportedEncodingException {
        return new String(string.getBytes(), FtpProperties.DEFAULT_HTTP_ENCODING_ISO_8859_1);
    }

    /**
     * 上传文件到FTP工作目录
     *
     * @param ftpClient  FTP对象
     * @param remotePath 上传到FTP相对路径，例如“/uploads/test/test.txt”
     * @param uploadFile 需要上传到FTP的本地文件
     * @return
     */
    private boolean uploadFile(FTPClient ftpClient, String remotePath, File uploadFile) {
        // 定义是否成功上传文件的变量
        boolean upload = false;

        try {
            // 切换到指定文件目录并上传文件到目录
            if (makeFtpDirectory(ftpClient, remotePath)) {

                // 获得上传文件流对象
                InputStream input = FileUtils.openInputStream(uploadFile);
                if (null != input) {
                    // 远程文件名字为“parents[parents.length - 1]”字符串
                    upload = ftpClient.storeFile(getStringForIso(remotePath.substring(remotePath.lastIndexOf(File.separator) + 1)), input);

                    // 关闭文件流
                    input.close();

                    // 记录日志
                    if (upload) {
                        log.info("将\"" + uploadFile.getAbsolutePath() + "\"文件上传到FTP的\"" + remotePath + "\"目录成功...");
                    } else {
                        log.info("将\"" + uploadFile.getAbsolutePath() + "\"文件上传到FTP的\"" + remotePath + "\"目录失败...");
                    }
                }

                return upload;
            }

        } catch (Exception e) {
            e.printStackTrace();

        }

        return upload;
    }

    /**
     * 创建FTP级联目录
     *
     * @param ftpClient  FTP对象
     * @param remotePath 上传到FTP相对路径，例如“/uploads/test/test.txt”
     * @return
     * @throws IOException
     */
    private boolean makeFtpDirectory(FTPClient ftpClient, String remotePath) throws IOException {
        // 定义是否成功切换工作目录变量
        boolean change = false;

        if (StringUtils.isNotBlank(remotePath)) {
            // 定义是否成功创建目录的变量
            boolean flag = false;

            // 获得一级一级目录名字
            String[] parents = StringUtils.split(remotePath, "/");

            // 判断目录的层级，分别进行操作
            if (1 != parents.length) {
                // 拼接目录信息
                String join = "";
                // 级联创建目录，忽略文件名字(length-1)
                for (int i = 0; i < parents.length - 1; i++) {
                    // 拼接工作目录
                    join += "/" + parents[i];
                    // 切换到工作目录
                    change = ftpClient.changeWorkingDirectory(getStringForIso(join));
                    // 如果目录不存在，则创建该目录
                    if (!change) {
                        // 创建目录
                        flag = ftpClient.makeDirectory(getStringForIso(parents[i]));
                        if (flag) {
                            // 如果成功创建该目录，则第二次切换到该目录
                            change = ftpClient.changeWorkingDirectory(getStringForIso(join));
                        } else {
                            // 记录日志
                            log.info("创建目录" + join + "失败...");
                        }
                    }
                }

            } else {
                // 直接切换到根目录("/")
                change = ftpClient.changeWorkingDirectory("/");
                log.info("直接切换到根目录\"/\"...");

            }
        }

        return change;
    }

    /**
     * 下载文件到本地
     *
     * @param ftpClient  FTP对象
     * @param remotePath 上传到FTP相对路径，例如“/uploads/test/test.txt”
     * @return
     */
    private File downloadFile(FTPClient ftpClient, String remotePath) {
        try {
            // 设置文件下载位置
            String suffix = remotePath.substring(remotePath.lastIndexOf("."));
            File download = new File(FileUtils.getTempDirectory(), UUID.randomUUID().toString() + suffix);

            // 下载文件
            OutputStream os = new FileOutputStream(download);
            if (ftpClient.retrieveFile(remotePath, os)) {
                return download;
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
