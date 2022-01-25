package com.gary.common.core.util;

import org.apache.commons.lang3.StringUtils;
import org.springframework.util.AntPathMatcher;

import java.util.List;

/**
 * @Classname StringUtils
 * @Description 字符串处理工具
 * @Date 2022/1/25 11:59
 * @Auth gary
 */
public class TaxiStringUtils {
    public static AntPathMatcher pathMatcher = new AntPathMatcher();

    public static boolean matches(String url, List<String> whites) {
        if (StringUtils.isBlank(url)){
            return false;
        }
        if (whites==null||whites.size()==0){
            return false;
        }
        for (String white : whites) {
            if (pathMatcher.match(white,url)){
                return true;
            }
        }
        return false;
    }
}
