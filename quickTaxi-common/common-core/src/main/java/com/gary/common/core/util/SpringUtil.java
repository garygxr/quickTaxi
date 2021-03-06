package com.gary.common.core.util;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.stereotype.Component;

/**
 * @Classname SpringUtil
 * @Description TODO
 * @Date 2022/1/25 17:56
 * @Auth gary
 */
@Component
public class SpringUtil {
    /** Spring应用上下文环境 */

    private static ConfigurableListableBeanFactory beanFactory;

    @Autowired
    public SpringUtil(ConfigurableListableBeanFactory beanFactory) {
        SpringUtil.beanFactory =  beanFactory;
    }

    /**
     * 获取类型为requiredType的对象
     *
     * @param clz
     * @return
     * @throws org.springframework.beans.BeansException
     *
     */
    public static <T> T getBean(Class<T> clz) throws BeansException
    {
        T result = (T) beanFactory.getBean(clz);
        return result;
    }
}
