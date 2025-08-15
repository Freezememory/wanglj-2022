package com.wanglj.wanglj2022.jiangmenData;

import lombok.Data;
import java.util.List;

/**
 * 指标对象（主对象）
 */
@Data
public class Indicator {

    // 指标编号
    private String indicatorCode;

    // 指标名称
    private String indicatorName;

    // 数据项列表（一对多关系）
    private List<DataItem> dataItems;


    /**
     * 数据项对象（子对象）
     */
    @Data
    public static class DataItem {

        // 数据项描述
        private String description;

        // 数据项值（如果有的话）
        private String value;
    }

}

