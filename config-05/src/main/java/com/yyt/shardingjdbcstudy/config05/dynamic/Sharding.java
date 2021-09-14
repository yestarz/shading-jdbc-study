package com.yyt.shardingjdbcstudy.config05.dynamic;

import java.lang.annotation.*;

/**
 * @author yangxin
 * @date 2021/9/13
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Sharding {

}
