package com.incarcloud.sb2.utils;

import org.apache.commons.lang3.StringUtils;
import org.junit.Assert;
import org.junit.Test;

/**
 * com.cooggo.springboot2.utils
 *
 * @author Aaric, created on 2019-04-30T14:05.
 * @since 0.0.2-SNAPSHOT
 */
public class HtmlXssUtilTest {

    @Test
    public void testTripXSS() {
        // Javascript: script, style, iframe
        String htmlText = "<p>hello</p><s><script type=\"text/javascript\" >alert('hello')</script><p>hello</p>";
        htmlText = "<script type=\"text/javascript\" >alert('hello')</script >" + htmlText;
        htmlText = htmlText + "<script type=\"text/javascript\" >alert('hello')</script >something<style></style><iframe>xxx";
        System.err.println(htmlText);

        String result = HtmlXssUtil.stripXSS(htmlText);
        System.out.println(result);
        Assert.assertEquals("_alert('hello')<p>hello</p><s>_alert('hello')<p>hello</p>_alert('hello')somethingxxx", result);
    }

    @Test
    public void testValidateFileExt() {
        String fileName = "hello.png";
        Assert.assertTrue(StringUtils.isNotBlank(fileName));
        Assert.assertTrue(-1 != StringUtils.indexOf(fileName, "."));
        Assert.assertTrue(-1 != StringUtils.lastIndexOf(fileName, ".png"));

        boolean flag = true;
        if (!(StringUtils.endsWith(fileName, ".jpg") || StringUtils.endsWith(fileName, ".png"))) {
            flag = false;
        }
        Assert.assertTrue(flag);
    }
}
