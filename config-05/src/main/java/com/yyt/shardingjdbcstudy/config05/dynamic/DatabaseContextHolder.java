package com.yyt.shardingjdbcstudy.config05.dynamic;

/**
 * @author yangxin
 * @date 2021/9/13
 * @see DataSourceType
 */
public class DatabaseContextHolder {

    private static final ThreadLocal<String> contextHolder = new ThreadLocal<>();

    public static void setCustomerType(String customerType) {
        contextHolder.set(customerType);
    }

    public static String getCustomerType() {
        return contextHolder.get();
    }

    public static void clearCustomerType() {
        contextHolder.remove();
    }

    public static void doSharding(){
        setCustomerType(DataSourceType.SHARDING.getType());
    }

}
