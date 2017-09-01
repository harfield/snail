package com.harfield.snail.tool;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URLDecoder;
import java.util.*;

/**
 * Created by harfield on 2017/7/26.
 */
public class ParseUtils {
    public static void main(String[] args) throws IOException {
        ParseUtils parseUtils = new ParseUtils();
        String s = parseUtils.parseDomain("news.ifeng.com", "10");
        System.out.println(s);
    }

    private static final Logger LOG = LoggerFactory.getLogger(ParseUtils.class);
    public static final Map<String, String> VENDOR_MAP = new HashMap<String, String>();
    public static final Set<String> DOMAIN_SUFFIX = new HashSet<String>();
    public static final Map<String, List<String>> DOMAIN_PATTERN = new HashMap<String, List<String>>();
    public static final Map<String, String> DOMAIN_NEW = new HashMap<String, String>();
    public static final Map<String, String> APP_NEW = new HashMap<String, String>();

    static {
        VENDOR_MAP.put("3", "晋拓-优酷");
        VENDOR_MAP.put("45", "泛为乐视");
        VENDOR_MAP.put("37", "晋拓-搜狐视频");
        VENDOR_MAP.put("29", "泛为-爱奇艺");
        VENDOR_MAP.put("51", "泛为-芒果TV");
        VENDOR_MAP.put("55", "泛为-风行视频");
        VENDOR_MAP.put("1", "晋拓-百度bes");
        VENDOR_MAP.put("42", "泛为-万流客");
        VENDOR_MAP.put("14", "晋拓-优酷移动");
        VENDOR_MAP.put("46", "泛为乐视移动");
        VENDOR_MAP.put("38", "晋拓-搜狐视频移动");
        VENDOR_MAP.put("30", "泛为-爱奇艺移动");
        VENDOR_MAP.put("52", "泛为-芒果TV移动");
        VENDOR_MAP.put("56", "泛为-风行视频移动");
        VENDOR_MAP.put("11", "晋拓-百度BES移动");
        VENDOR_MAP.put("43", "泛为-万流客移动");
        VENDOR_MAP.put("57", "腾讯RTB");
        VENDOR_MAP.put("58", "腾讯RTB移动");
        VENDOR_MAP.put("33", "暴风影音");
        VENDOR_MAP.put("34", "暴风影音移动");
        VENDOR_MAP.put("4", "景唐盛世");
        VENDOR_MAP.put("18", "广推移动");
        VENDOR_MAP.put("32", "微博移动");
        VENDOR_MAP.put("24", "光音移动");
        VENDOR_MAP.put("23", "光音-pc");
        VENDOR_MAP.put("60", "imageter移动");
        VENDOR_MAP.put("59", "imageter");
        VENDOR_MAP.put("36", "畅盈移动");
        VENDOR_MAP.put("19", "玉米");
        VENDOR_MAP.put("63", "乐传");
        VENDOR_MAP.put("21", "wifi万能钥匙");
        VENDOR_MAP.put("2", "淘宝");
        VENDOR_MAP.put("16", "淘宝移动");

    }

    static {
        try {
            InputStream resourceAsStream = ParseUtils.class.getResourceAsStream("/data/domain_suffix_list.txt");
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(resourceAsStream));
            String line = null;
            while ((line = bufferedReader.readLine()) != null) {
                DOMAIN_SUFFIX.add(line.trim());
            }
            bufferedReader.close();
            resourceAsStream.close();
            //domain.map
            bufferedReader = new BufferedReader(new InputStreamReader(ParseUtils.class.getResourceAsStream("/data/domain.map")));
            while (null != (line = bufferedReader.readLine())) {
                String[] split = line.split("\\s");
                if (split.length >= 3) {
                    DOMAIN_NEW.put(split[0], split[2]);
                    DOMAIN_PATTERN.put(split[0], Arrays.asList(split[1].split(",")));
                }
            }
            bufferedReader.close();
            //app_name.map
            bufferedReader = new BufferedReader(new InputStreamReader(ParseUtils.class.getResourceAsStream("/data/app_name.map")));
            while (null != (line = bufferedReader.readLine())) {
                String[] split = line.split("\\s");
                if (split.length >= 2) {
                    APP_NEW.put(split[0], split[1]);
                }
            }
            bufferedReader.close();
        } catch (Exception e) {

        }

    }

    public static String getGenderFromList(List<String> audienceTag) {
        for (String tag : audienceTag) {
            if (tag.startsWith("250")) {
                return tag;
            }
        }
        return null;
    }

    public static String getAgeFromList(List<String> audienceTag) {
        for (String tag : audienceTag) {
            if (tag.startsWith("260")) {
                return tag;
            }
        }
        return null;
    }

    public static String getEduFromList(List<String> audienceTag) {
        for (String tag : audienceTag) {
            if (tag.startsWith("230")) {
                return tag;
            }
        }
        return null;
    }

    public static List<String> getInterestFromList(List<String> audienceTag) {
        List<String> iList = new ArrayList<String>();
        for (String tag : audienceTag) {
            if (tag.startsWith("240")) {
                iList.add(tag);
            }
        }
        return iList.size() > 0 ? iList : null;
    }

    public static String parseDomain(String domain, String vendorId) {
        String url = domain;
        try {
            url = URLDecoder.decode(domain, "utf8");
        } catch (Exception e) {
            LOG.error("decode url {} error :{}", domain, e.getMessage());
            return null;
        }
        if (url.startsWith("http://")) {
            url = url.substring(7);
        }
        url = url.replaceAll("\\r", "").replaceAll("\\n", "");
        String str = url.split("/")[0].split(":")[0];
        url = null;
        while (str.indexOf(".") > 0) {
            if (DOMAIN_SUFFIX.contains(str.substring(str.indexOf(".") + 1))) {
                url = str;
            }
            str = str.substring(str.indexOf(".") + 1);
        }
        if (null == url) {
            return null;
        }
        if (DOMAIN_NEW.containsKey(vendorId)) {
            for (String d : DOMAIN_PATTERN.get(vendorId)) {
                if (url.contains(d)) {
                    url = DOMAIN_NEW.get(vendorId);
                }
            }
        }
        return url;
    }

    public static String parseAppName(String app, String vendorId) {
        String appName = app;
        if ("46".equals(vendorId) && app.length() < 2) {
            appName = "letv-mob(out)";
        } else if ("58".equals(vendorId)) {
            appName = appName.split("\\s")[0];
        } else {
            if (APP_NEW.containsKey(app)) {
                appName = APP_NEW.get(app);
            }
            appName = appName.split("\\s")[0];
        }
        return appName;
    }
}
