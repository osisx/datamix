package com.induschain.workflow;

import lombok.Getter;

/**
 * 业务流程枚举类
 */
@Getter
public enum BusinessProcessEnum {

    LEAVE("请假流程", "key");

    private String name;
    private String key;

    BusinessProcessEnum(String name, String key) {
        this.name = name;
        this.key = key;
    }

    public static String getName(String key) {
        for (BusinessProcessEnum processEnum : BusinessProcessEnum.values()) {
            if (processEnum.getKey().equals(key)) {
                return processEnum.name;
            }
        }
        return null;
    }
}
