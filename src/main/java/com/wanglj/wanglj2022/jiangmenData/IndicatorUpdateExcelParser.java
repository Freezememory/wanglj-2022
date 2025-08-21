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
public class IndicatorUpdateExcelParser {

    // 最终解析结果
    private final List<Indicator> result = new ArrayList<>();

    private Indicator currentIndicator;

    // private static final String filePath = "E:\\WorkFiles\\2019_广州\\2025_江门医院\\第三章建表\\ga03Table\\createTable_Ga03_{}.sql";
    private static final String filePath = "E:\\WorkFiles\\2019_广州\\2025_江门医院\\第三章建表\\ga03Collection\\postman_collection_createTable_Ga03_{}.json";

    // private static final String commonFilePath = "E:\\WorkFiles\\2019_广州\\2025_江门医院\\第三章建表\\ga03Table\\createTable_Ga03_common.sql";
    private static final String commonFilePath = "E:\\WorkFiles\\2019_广州\\2025_江门医院\\第三章建表\\ga03Collection\\postman_collection_createTable_Ga03_common.json";
    private static final String tableFilePath = "E:\\WorkFiles\\2019_广州\\2025_江门医院\\第三章建表\\ga03Collection\\Ga03_table_item_name_rel.txt";

    private static final String codeJsonPath = "E:\\WorkFiles\\2019_广州\\2025_江门医院\\第三章建表\\第三章分类代码.json";

    private static final String result_detail_update_path = "E:\\WorkFiles\\2019_广州\\2025_江门医院\\第三章建表\\result_detail_update.sql";
    private static final String common_result_detail_update_path = "E:\\WorkFiles\\2019_广州\\2025_江门医院\\第三章建表\\common_result_detail_update.sql";

    private final Map<String, Integer> itemMap = new HashMap<>();

    private final Map<Integer, Boolean> fileMap = new HashMap<>();

    private final List<String> commonTableList = new ArrayList<>();

    public static void main(String[] args) {
        IndicatorUpdateExcelParser indicatorExcelParser = new IndicatorUpdateExcelParser();
        indicatorExcelParser.readExcel();

        System.out.println("同期 ICU 患者血管导管累计使用天数".replace(" ", ""));

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
            log.info("数据库规则: {}  , 是否过滤: {} ", dataRule, filterStatus);
            if (!filterStatus) {
                this.invoke(excelData);
            }

        }
        log.info("过滤后结果行数 : {} 行", result.size());

        // this.createFile(-3, result_detail_update_path);
        this.createFile(-4, common_result_detail_update_path);

        HashMap<Integer, JSONObject> codeJsonList = this.getCodeJsonList();
        // 打印解析结果
        for (Indicator indicator : result) {
            String indicatorCode = indicator.getIndicatorCode();
            String[] split = indicatorCode.split("\\.");
            Integer order = Integer.parseInt(split[1]);
            if (order < 6) {
                continue;
            }

            JSONObject jsonObject = codeJsonList.get(order);

            String code = jsonObject.getStr("code");

            String num = indicatorCode.substring(indicatorCode.lastIndexOf(".") + 1);
            String prefixNum = String.format("%03d", Integer.parseInt(num)); // "123"

            log.info("指标编号: {}  ,指标名称: {} ", indicator.getIndicatorCode(), indicator.getIndicatorName());
            for (int i = 0; i < indicator.getDataItems().size(); i++) {
                Indicator.DataItem item = indicator.getDataItems().get(i);
                String dataItem = item.getDescription().replace(" ", "");

                if (itemMap.get(dataItem) > 1) {
                    if (!commonTableList.contains(dataItem)) {
                        if (!FileUtil.exist(commonFilePath)) {
                            FileUtil.mkdir(commonFilePath);
                        }
                        String tableName = "comm_" + String.format("%03d", commonTableList.size() + 1) + code.toLowerCase();
                        String tableFullName = ("ads_ga03_" + code + "_" + tableName + "_full_daily").toLowerCase();
                        String detailTableFullName = tableFullName.replace("_full_daily", "detail_full_daily");

                        String updateSqlStr = StrUtil.format(PostManJsonFormat.updateSqlFormat, tableFullName, detailTableFullName, dataItem);
                        try {
                            Files.write(Paths.get(common_result_detail_update_path), updateSqlStr.getBytes(), StandardOpenOption.APPEND);

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
                String tableFullName = ("ads_ga03_" + code + "_" + tableName + "_full_daily").toLowerCase();
                String detailTableFullName = tableFullName.replace("count", "detail");

                String updateSqlStr = StrUtil.format(PostManJsonFormat.updateSqlFormat, tableFullName, detailTableFullName, dataItem);

                /*try {
                    Files.write(Paths.get(result_detail_update_path), updateSqlStr.getBytes(), StandardOpenOption.APPEND);
                } catch (IOException e) {
                    log.error("追加内容时出错: ", e);
                }*/
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

        // 添加数据项到当前指标
        if (currentIndicator != null && StrUtil.isNotBlank(data.getDataItem()) && !"/".equals(data.getDataItem())) {
            String dataItem = data.getDataItem().replace(" ", "");
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