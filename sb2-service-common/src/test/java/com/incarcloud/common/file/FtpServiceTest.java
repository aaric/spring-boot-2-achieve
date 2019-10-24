package com.incarcloud.common.file;

import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

/**
 * FtpServiceTest
 *
 * @author Aaric, created on 2019-08-15T17:51.
 * @version 0.8.0-SNAPSHOT
 */
@Slf4j
@SpringBootTest
@RunWith(SpringRunner.class)
public class FtpServiceTest {

    /**
     * 测试文件
     */
    private String testFileDirectory = "C:\\Users\\root\\Desktop";
    private String testFileName = "banzhuan.jpg";

    private File testFile;

    @Autowired(required = false)
    protected FtpService ftpService;

    @Test
    @Ignore
    public void testIsHasFile() {
        Assert.assertFalse(ftpService.isHasFile("/fakeFile"));
    }

    @Test
    @Ignore
    public void testUploadFile() {
        Assert.assertTrue(ftpService.uploadFile("/" + testFileName, new File(testFileDirectory, testFileName)));
    }

    @Test
    @Ignore
    public void testUploadFiles() {
        Map<String, File> uploadFiles = new HashMap<>();
        uploadFiles.put("/01-" + testFileName, new File(testFileDirectory, testFileName));
        uploadFiles.put("/02-" + testFileName, new File(testFileDirectory, testFileName));
        ftpService.uploadFiles(uploadFiles);
    }

    @Test
    @Ignore
    public void testDownloadFile() {
        File downloadFile = ftpService.downloadFile("/" + testFileName);
        log.info(downloadFile.getAbsolutePath());
        Assert.assertNotNull(downloadFile);
    }
}
