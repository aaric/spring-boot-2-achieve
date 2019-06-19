package com.cooggo.sb2;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * SpringBoot2Test
 *
 * @author Aaric, created on 2019-03-26T16:51.
 * @since 0.0.1-SNAPSHOT
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class SpringBoot2Test {

    @Value("${spring.profiles.active}")
    private String springProfilesActive;

    @Test
    public void testExecute() throws Exception {
        // chcp 65001
        String commandString = "ipconfig /all";
        Process process = Runtime.getRuntime().exec(commandString);
        BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
        String line = null;
        while (null != (line = reader.readLine())) {
            System.out.println(line);
        }
        reader.close();
        process.waitFor();
    }
}
