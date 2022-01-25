package com.gary.common.redis.constant;

/**
 * @Classname RedisKey
 * @Description TODO
 * @Date 2022/1/25 16:31
 * @Auth gary
 */
public class RedisKey {
    private final static String projectName ="quickTaxi:";

    public static String getAuthUserKey(String uid){
        return projectName+"authUser:"+uid;
    }
}
