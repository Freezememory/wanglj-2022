package com.wanglj.wanglj2022.jiangmenData;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.IdUtil;
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
public class IndicatorInsertExcelParser {

    // 最终解析结果
    private final List<Indicator> result = new ArrayList<>();

    private Indicator currentIndicator;

    private static final String codeJsonPath = "E:\\WorkFiles\\2019_广州\\2025_江门医院\\第三章建表\\第三章分类代码.json";
    private static final String base_indicator_insert_path = "E:\\WorkFiles\\2019_广州\\2025_江门医院\\第三章建表\\基础指标\\result_detail_insert.sql";
    private static final String common_indicator_insert_path = "E:\\WorkFiles\\2019_广州\\2025_江门医院\\第三章建表\\基础指标\\common_detail_insert.sql";

    private final Map<String, Integer> itemMap = new HashMap<>();

    private final Map<Integer, Boolean> fileMap = new HashMap<>();

    private final List<String> commonTableList = new ArrayList<>();

    public static void main(String[] args) {
        IndicatorInsertExcelParser indicatorExcelParser = new IndicatorInsertExcelParser();
        indicatorExcelParser.readExcel();
    }

    public void readExcel() {
        String fileName = "E:\\WorkFiles\\2019_广州\\2025_江门医院\\第三章建表\\基础指标\\医院等级评审2025版-逻辑规则_本地记录.xlsx";
        String sheetName = "第三章 数据项处理";
        List<InsertExcelData> dataList = EasyExcel.read(new File(fileName))
                .head(InsertExcelData.class)
                .sheet(sheetName)
                .doReadSync();
        log.info("解析总行数 : {} 行", dataList.size());
        for (InsertExcelData excelData : dataList) {
            this.invoke(excelData);
            /*String dataRule = excelData.getDataRule();
            boolean filterStatus = StrUtil.isBlank(dataRule) || dataRule.contains("人工") || dataRule.contains("抽样");
            log.info("数据库规则: {}  , 是否过滤: {} ", dataRule, filterStatus);
            if (!filterStatus) {
            }*/

        }
        log.info("过滤后结果行数 : {} 行", result.size());

        this.createFile(-4, base_indicator_insert_path);
        // this.createFile(-5, common_indicator_insert_path);

        HashMap<Integer, JSONObject> codeJsonList = this.getCodeJsonList();
        // 打印解析结果
        for (Indicator indicator : result) {
            String indicatorCode = indicator.getIndicatorCode();
            String[] split = indicatorCode.split("\\.");
            Integer order = Integer.parseInt(split[1]);

            JSONObject jsonObject = codeJsonList.get(order);

            String code = jsonObject.getStr("code");
            String num = indicatorCode.substring(indicatorCode.lastIndexOf(".") + 1);
            String prefixNum = String.format("%03d", Integer.parseInt(num)); // "123"

            log.info("指标编号: {}  ,指标名称: {} ", indicator.getIndicatorCode(), indicator.getIndicatorName());
            for (int i = 0; i < indicator.getDataItems().size(); i++) {
                Indicator.DataItem item = indicator.getDataItems().get(i);
                if (!"不存在".equals(item.getValue())) {
                    continue;
                }
                String dataItem = item.getDescription().replace(" ", "");

                if (itemMap.get(dataItem) > 1) {
                    /*if (!commonTableList.contains(dataItem)) {
                        String tableName = "comm_" + String.format("%03d", commonTableList.size() + 1) + code.toLowerCase();
                        String tableFullName = ("ads_ga03_" + code + "_" + tableName + "_full_daily").toLowerCase();
                        String detailTableFullName = tableFullName.replace("_full_daily", "detail_full_daily");
                        String updateSqlStr = StrUtil.format(PostManJsonFormat.insertSqlFormat, IdUtil.getSnowflakeNextId(), dataItem ,tableFullName, detailTableFullName);
                        try {
                            Files.write(Paths.get(common_indicator_insert_path), updateSqlStr.getBytes(), StandardOpenOption.APPEND);
                        } catch (IOException e) {
                            log.error("追加内容时出错: ", e);
                        }
                        commonTableList.add(dataItem);
                    }*/
                    continue;
                }

                String tableName;
                if (i == 0) {
                    tableName = prefixNum + "num_count";
                } else {
                    tableName = prefixNum + "den_count";
                }
                log.info("  数据项  - " + dataItem);
                String tableFullName = ("ads_ga03_" + code + "_" + tableName + "_full_daily").toLowerCase();
                String detailTableFullName = tableFullName.replace("count", "detail");
                long snowflakeNextId = IdUtil.getSnowflakeNextId();
                String updateSqlStr = StrUtil.format(PostManJsonFormat.insertSqlFormat, snowflakeNextId, dataItem, snowflakeNextId, tableFullName, detailTableFullName);
                try {
                    Files.write(Paths.get(base_indicator_insert_path), updateSqlStr.getBytes(), StandardOpenOption.APPEND);
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
            if (order == -1) {
                Files.write(Paths.get(orderFilePath), StrUtil.format(PostManJsonFormat.prefixFormat, "ga" + order).getBytes(), StandardOpenOption.APPEND);
            } else {
                return;
            }
            fileMap.put(order, true);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void invoke(InsertExcelData data) {
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

        // 添加数据项到当前指标
        if (currentIndicator != null && StrUtil.isNotBlank(data.getDataItem()) && !"/".equals(data.getDataItem())) {
            String dataItem = data.getDataItem().replace(" ", "");
            String value = data.getItemExit();
            Indicator.DataItem item = new Indicator.DataItem();

            item.setDescription(dataItem);
            item.setValue(value);
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