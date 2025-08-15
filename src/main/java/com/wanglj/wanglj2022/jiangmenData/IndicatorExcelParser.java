package com.wanglj.wanglj2022.jiangmenData;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.alibaba.excel.EasyExcel;
import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
public class IndicatorExcelParser {

    // 最终解析结果
    private final List<Indicator> result = new ArrayList<>();

    private Indicator currentIndicator;


    // private static final String filePath = "E:\\WorkFiles\\2019_广州\\2025_江门医院\\第三章建表\\ga03Table\\createTable_Ga03_{}.sql";
    private static final String filePath = "E:\\WorkFiles\\2019_广州\\2025_江门医院\\第三章建表\\ga03Collection\\postman_collection_createTable_Ga03_{}.json";

    // private static final String commonFilePath = "E:\\WorkFiles\\2019_广州\\2025_江门医院\\第三章建表\\ga03Table\\createTable_Ga03_common.sql";
    private static final String commonFilePath = "E:\\WorkFiles\\2019_广州\\2025_江门医院\\第三章建表\\ga03Collection\\postman_collection_createTable_Ga03_common.json";


    private static final String codeJsonPath = "E:\\WorkFiles\\2019_广州\\2025_江门医院\\第三章建表\\第三章分类代码.json";

    private final Map<String, Integer> itemMap = new HashMap<>();

    private final Map<Integer, Boolean> fileMap = new HashMap<>();

    private final List<String> commonTableList = new ArrayList<>();


    private static final String sqlFormat = "{\n" +
            "  \"layerId\": \"1934450759259664390\",\n" +
            "  \"firstTheme\": \"1950030402880557058\",\n" +
            "  \"secondTheme\": \"{}\",\n" +
            "  \"upgradeFlag\": \"_full\",\n" +
            "  \"upgradeFrequency\": \"_daily\",\n" +
            "  \"sdsStandard\": 0,\n" +
            "  \"sql\": \"CREATE TABLE `{}` (\\n\\t`business_dt` INT NULL COMMENT \\\"业务时间yyyy/yyyymm/yyyyqq/yyyymmdd\\\",\\n\\t`dim_type` VARCHAR(50) NULL COMMENT \\\"维度类型(科室/专业组/全科室)\\\",\\n\\t`dept_code` VARCHAR(128) NULL COMMENT \\\"科室编码\\\",\\n\\t`spec_doct_code` VARCHAR(128) NULL DEFAULT \\\"0\\\" COMMENT \\\"专业组代码\\\",\\n\\t`business_dt_type` VARCHAR(128) NULL COMMENT \\\"业务时间类型(日/月/季/半年/年/月(26~25)/年(26~25)/季(26~25)/半年(26~25))\\\" ,\\n\\t`dept_name` VARCHAR(255) NULL COMMENT \\\"科室名称\\\",\\n\\t`spec_doct_name` VARCHAR(128) NULL DEFAULT \\\"0\\\" COMMENT \\\"专业组名称\\\",\\n\\t`label_result` DECIMAL(20,2) NULL COMMENT \\\"结果值\\\",\\n\\t`tb_value` DECIMAL(20,2) NULL COMMENT \\\"同比分析\\\",\\n\\t`hb_value` DECIMAL(20,2) NULL COMMENT \\\"环比分析\\\",\\n\\t`create_by` VARCHAR(32) NULL COMMENT \\\"创建人\\\",\\n\\t`create_time` DATETIME NULL COMMENT \\\"创建时间\\\",\\n\\t`update_by` VARCHAR(32) NULL COMMENT \\\"最后更新人\\\",\\n\\t`update_time` DATETIME NULL COMMENT \\\"最后更新时间\\\",\\n\\t`theme_code` VARCHAR(500) NULL COMMENT \\\"主题编码(指标编码)\\\",\\n\\t`theme_name` VARCHAR(500) NULL COMMENT \\\"主题名称(指标名称)\\\",\\n\\t`sjtjsj` DATETIME NULL COMMENT \\\"数据统计时间\\\"\\n) ENGINE=OLAP\\nUNIQUE KEY(business_dt,\\n  dim_type,\\n  dept_code,\\n  spec_doct_code,\\n  business_dt_type)\\nCOMMENT \\\"{}\\\"\\nDISTRIBUTED BY HASH(business_dt,\\n  dim_type,\\n  dept_code,\\n  spec_doct_code,\\n  business_dt_type) BUCKETS 8 ;\",\n" +
            "  \"datasourceType\": \"14\",\n" +
            "  \"datasourceId\": \"1934450975773831170\",\n" +
            "  \"fullNameFormat\": \"ads_GA03_{}_%s_full_daily\"\n" +
            "}";


    // private static final String sqlFormat = "\n\n create table    `{}`  ()   comment \"{}\" ";


    public static void main(String[] args) {
        IndicatorExcelParser indicatorExcelParser = new IndicatorExcelParser();
        indicatorExcelParser.readExcel();
    }

    public void readExcel() {
        String fileName = "E:\\WorkFiles\\2019_广州\\2025_江门医院\\第三章建表\\第三章自动化建表梳理.xlsx";
        String sheetName = "Sheet2";

        List<ExcelData> dataList = EasyExcel.read(new File(fileName))
                .head(ExcelData.class)
                .sheet(sheetName)
                .doReadSync();

        log.info("解析总行数 : {} 行", dataList.size());

        for (ExcelData excelData : dataList) {
            String dataRule = excelData.getDataRule();
            boolean filterStatus = StrUtil.isBlank(dataRule) || dataRule.contains("人工") || dataRule.contains("抽样");
            // boolean filterStatus = codeStatus || dataRuleStatus;
            log.info("数据库规则: {}  , 是否过滤: {} ", dataRule, filterStatus);
            if (!filterStatus) {
                this.invoke(excelData);
            }
        }
        log.info("过滤后结果行数 : {} 行", result.size());

        this.createFile(-1, commonFilePath);

        HashMap<Integer, JSONObject> codeJsonList = this.getCodeJsonList();
        // 打印解析结果
        for (Indicator indicator : result) {
            String indicatorCode = indicator.getIndicatorCode();

            String[] split = indicatorCode.split("\\.");
            Integer order = Integer.parseInt(split[1]);
            if (order < 6) {
                continue;
            }

            String orderFilePath = StrUtil.format(filePath, order);
            if (ObjectUtil.isNull(fileMap) || ObjectUtil.isNull(fileMap.get(order)) || !fileMap.get(order)) {
                this.createFile(order, orderFilePath);
            }

            JSONObject jsonObject = codeJsonList.get(order);

            String id = jsonObject.getStr("id");
            String code = jsonObject.getStr("code");

            String num = indicatorCode.substring(indicatorCode.lastIndexOf(".") + 1);
            String prefixNum = String.format("%03d", Integer.parseInt(num)); // "123"

            log.info("指标编号: {}  ,指标名称: {} ", indicator.getIndicatorCode(), indicator.getIndicatorName());
            for (int i = 0; i < indicator.getDataItems().size(); i++) {
                Indicator.DataItem item = indicator.getDataItems().get(i);
                String dataItem = item.getDescription();

                if (itemMap.get(dataItem) > 1) {
                    if (!commonTableList.contains(dataItem)) {
                        if (!FileUtil.exist(commonFilePath)) {
                            FileUtil.mkdir(commonFilePath);
                        }
                        String tableName = "comm_" + String.format("%03d", commonTableList.size() + 1) + code.toLowerCase();
                        String name = "通用_" + dataItem + ",通用次数:" + itemMap.get(dataItem);
                        String createBody = StrUtil.format(PostManJsonFormat.bodyFormat, id, tableName, "通用_" + dataItem, code);
                        // String createBody = StrUtil.format(sqlFormat, id, tableName, "通用_" + dataItem, code);


                        String jsonFormat = StrUtil.format(PostManJsonFormat.jsonFormat, name, createBody);
                        try {
                            Files.write(Paths.get(commonFilePath), jsonFormat.getBytes(), StandardOpenOption.APPEND);
                        } catch (IOException e) {
                            log.error("追加内容时出错: ", e);
                        }
                        commonTableList.add(dataItem);
                    }
                    continue;
                }

                String tableName;
                if (i == 0) {
                    tableName = prefixNum + "num_count";
                } else {
                    tableName = prefixNum + "den_count";
                }
                log.info("  数据项  - " + dataItem);
                String createBody = StrUtil.format(PostManJsonFormat.bodyFormat, id, tableName, dataItem, code);
                // String createBody = StrUtil.format(sqlFormat, id, tableName, dataItem, code);
                String jsonFormat = StrUtil.format(PostManJsonFormat.jsonFormat, tableName, createBody);

                try {
                    Files.write(Paths.get(orderFilePath), jsonFormat.getBytes(), StandardOpenOption.APPEND);
                } catch (IOException e) {
                    log.error("追加内容时出错: ", e);
                }
            }
        }

    }

    private void createFile(Integer order, String orderFilePath) {
        try {
            FileWriter writer = new FileWriter(orderFilePath, false);
            writer.write("");

            Files.write(Paths.get(orderFilePath), StrUtil.format(PostManJsonFormat.prefixFormat, "ga" + order).getBytes(), StandardOpenOption.APPEND);
            // FileWriter writer = new FileWriter(orderFilePath);
            // writer.write("");
            fileMap.put(order, true);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    public void invoke(ExcelData data) {
        // 如果当前行有指标编号，说明是新指标开始
        if (data.getIndicatorCode() != null && !data.getIndicatorCode().isEmpty() && data.getIndicatorCode().startsWith("3.")) {
            // 保存上一个指标（如果有的话）
            if (currentIndicator != null) {
                result.add(currentIndicator);
            }

            // 创建新指标
            currentIndicator = new Indicator();
            currentIndicator.setIndicatorCode(data.getIndicatorCode());
            currentIndicator.setIndicatorName(data.getIndicatorName());
            currentIndicator.setDataItems(new ArrayList<>());
        }

        String dataItem = data.getDataItem();
        // 添加数据项到当前指标
        if (currentIndicator != null && StrUtil.isNotBlank(dataItem) && !"/".equals(dataItem)) {
            Indicator.DataItem item = new Indicator.DataItem();

            item.setDescription(dataItem);
            currentIndicator.getDataItems().add(item);


            if (ObjectUtil.isNotNull(itemMap.get(dataItem))) {
                itemMap.put(dataItem, itemMap.get(dataItem) + 1);
            } else {
                itemMap.put(dataItem, 1);
            }

        }
    }


    private HashMap<Integer, JSONObject> getCodeJsonList() {
        HashMap<Integer, JSONObject> map = new HashMap<>();
        String jsonContent = FileUtil.readUtf8String(codeJsonPath);
        JSONArray jsonArray = JSONUtil.parseArray(jsonContent);

        for (int i = 0; i < jsonArray.size(); i++) {
            JSONObject jsonObject = jsonArray.getJSONObject(i);
            map.put(i + 1, jsonObject);
        }

        return map;
    }

}