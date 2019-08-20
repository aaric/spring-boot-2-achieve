package com.incarcloud.common.utils;

import org.apache.commons.lang3.StringUtils;

import java.util.regex.Pattern;

/**
 * HTML防止XSS工具类
 *
 * @author Aaric, created on 2019-04-13T16:31.
 * @since 0.0.2-SNAPSHOT
 */
public final class HtmlXssUtil {


    /**
     * HTML Sensitive Words
     */
    private static final String[] DEFAULT_XSS_KEYWORDS = {
            "alert", "prompt", "confirm", "script", "javascript", "action", "formaction", "location", "name", "poster",
            "data", "code", "lowsrc", "bgsound", "href", "onload", "onunload", "onblur", "onchange", "onfocus",
            "onreset", "onselect", "onkeydown", "onkeypress", "onkeyup", "onclick", "ondblclick", "onmousedown", "onmousemove",
            "onmouseout", "onmouseover", "onmouseup", "onabort", "onwaiting"
    };

    /**
     * 过滤HTML敏感词汇
     *
     * @param htmlText HTML文本内容
     * @return
     */
    private static String processKeyWords(String htmlText) {
        if (StringUtils.isEmpty(htmlText)) {
            return "";
        }
        for (String s : DEFAULT_XSS_KEYWORDS) {
            if (htmlText.contains(s)) {
                htmlText = htmlText.replace(s, "_" + s);
            }
        }
        return htmlText;
    }

    /**
     * 过滤HTML Javascript危险标签和代码
     *
     * @param htmlText HTML文本内容
     * @return
     */
    public static String stripXSS(String htmlText) {
        String value = htmlText;
        if (value != null) {
            // NOTE: It's highly recommended to use the ESAPI library and uncomment the following line to
            // avoid encoded attacks.
            // value = ESAPI.encoder().canonicalize(value);
            // Avoid null characters
            value = value.replaceAll("", "");
            // Avoid anything between script tags
            Pattern scriptPattern = Pattern.compile("<script>(.*?)</script>", Pattern.CASE_INSENSITIVE);
            value = scriptPattern.matcher(value).replaceAll("");
            // Remove any lonesome </script> tag
            scriptPattern = Pattern.compile("</script(.*?)>", Pattern.CASE_INSENSITIVE);
            value = scriptPattern.matcher(value).replaceAll("");
            // Remove any lonesome <script ...> tag
            scriptPattern = Pattern.compile("<script(.*?)>", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);
            value = scriptPattern.matcher(value).replaceAll("");
            // Avoid eval(...) e­xpressions
            scriptPattern = Pattern.compile("eval\\((.*?)\\)", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);
            value = scriptPattern.matcher(value).replaceAll("");
            // Avoid e­xpression(...) e­xpressions
            scriptPattern = Pattern.compile("e­xpression\\((.*?)\\)", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);
            value = scriptPattern.matcher(value).replaceAll("");
            // Avoid javascript:... e­xpressions
            scriptPattern = Pattern.compile("javascript:", Pattern.CASE_INSENSITIVE);
            value = scriptPattern.matcher(value).replaceAll("");
            // Avoid vbscript:... e­xpressions
            scriptPattern = Pattern.compile("vbscript:", Pattern.CASE_INSENSITIVE);
            value = scriptPattern.matcher(value).replaceAll("");
            // Avoid onload= e­xpressions
            scriptPattern = Pattern.compile("onload(.*?)=", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);
            value = scriptPattern.matcher(value).replaceAll("");
            // Remove any lonesome </style> tag
            scriptPattern = Pattern.compile("</style(.*?)>", Pattern.CASE_INSENSITIVE);
            value = scriptPattern.matcher(value).replaceAll("");
            // Remove any lonesome <style ...> tag
            scriptPattern = Pattern.compile("<style(.*?)>", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);
            value = scriptPattern.matcher(value).replaceAll("");
            // Remove any lonesome </iframe> tag
            scriptPattern = Pattern.compile("</iframe(.*?)>", Pattern.CASE_INSENSITIVE);
            value = scriptPattern.matcher(value).replaceAll("");
            // Remove any lonesome <iframe ...> tag
            scriptPattern = Pattern.compile("<iframe(.*?)>", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);
            value = scriptPattern.matcher(value).replaceAll("");

            // Handling Sensitive Words
            value = processKeyWords(value);
        }
        return value;
    }
}
