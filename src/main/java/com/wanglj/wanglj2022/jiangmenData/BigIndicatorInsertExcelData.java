package com.wanglj.wanglj2022.jiangmenData;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Data
@Slf4j
public class BigIndicatorInsertExcelData {
    // 指标编号（第一列）
    @ExcelProperty("指标编号")
    private String indicatorCode;

    // 指标名称（第二列）
    @ExcelProperty("指标")
    private String indicatorName;

    // 数据项（第三列）
    @ExcelProperty("指标是否存在")
    private String indicatorExit;

    // 数据项（第四列）
    @ExcelProperty("数据项")
    private String dataItem;

    @ExcelProperty("单位")
    private String unti;

    // 数据项（第五列）
    @ExcelProperty("提供数据的科室")
    private String deptName;

    // 数据项（第六列）
    @ExcelProperty("数据来源渠道")
    private String dataChannel;

    // 数据项（第七列）
    @ExcelProperty("数据库规则")
    private String dataRule;

}