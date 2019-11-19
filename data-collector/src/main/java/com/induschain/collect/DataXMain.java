package com.induschain.collect;

import com.alibaba.datax.core.Engine;

public class DataXMain {

    public static void main(String[] args) {
        System.setProperty("datax.home", getCurrentClasspath());
        String[] datxArgs = {"-job", getCurrentClasspath() + "mysql2mysql.json", "-mode", "standalone", "-jobid", "-1"};
        try {
            Engine.entry(datxArgs);
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }

    public static String getCurrentClasspath() {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        String currentClasspath = classLoader.getResource("").getPath();
        // 当前操作系统
        String osName = System.getProperty("os.name");
        if (osName.startsWith("Windows")) {
            // 删除path中最前面的/
            currentClasspath = currentClasspath.substring(1);
        }
        System.out.println(currentClasspath);
        return "/Users/chenyunyun/Documents/产链数字科技/datamix/data-collector/target/classes/";
    }
}


