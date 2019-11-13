package com.incarcloud.common.file;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

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
@ExtendWith(SpringExtension.class)
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
    @Disabled
    public void testIsHasFile() {
        Assertions.assertFalse(ftpService.isHasFile("/fakeFile"));
    }

    @Test
    @Disabled
    public void testUploadFile() {
        Assertions.assertTrue(ftpService.uploadFile("/" + testFileName, new File(testFileDirectory, testFileName)));
    }

    @Test
    @Disabled
    public void testUploadFiles() {
        Map<String, File> uploadFiles = new HashMap<>();
        uploadFiles.put("/01-" + testFileName, new File(testFileDirectory, testFileName));
        uploadFiles.put("/02-" + testFileName, new File(testFileDirectory, testFileName));
        ftpService.uploadFiles(uploadFiles);
    }

    @Test
    @Disabled
    public void testDownloadFile() {
        File downloadFile = ftpService.downloadFile("/" + testFileName);
        log.info(downloadFile.getAbsolutePath());
        Assertions.assertNotNull(downloadFile);
    }
}
