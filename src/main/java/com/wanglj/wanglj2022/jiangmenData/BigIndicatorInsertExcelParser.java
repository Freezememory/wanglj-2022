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
public class BigIndicatorInsertExcelParser {

    // 最终解析结果
    private final List<Indicator> result = new ArrayList<>();

    private Indicator currentIndicator;

    private static final String codeJsonPath = "E:\\WorkFiles\\2019_广州\\2025_江门医院\\第三章建表\\第三章分类代码.json";
    private static final String base_indicator_insert_path = "E:\\WorkFiles\\2019_广州\\2025_江门医院\\第三章建表\\基础指标\\big_indicator_insert.sql";

    private final Map<String, Integer> itemMap = new HashMap<>();

    private final Map<Integer, Boolean> fileMap = new HashMap<>();


    public static void main(String[] args) {
        BigIndicatorInsertExcelParser indicatorExcelParser = new BigIndicatorInsertExcelParser();
        indicatorExcelParser.readExcel();
    }

    public void readExcel() {
        String fileName = "E:\\WorkFiles\\2019_广州\\2025_江门医院\\第三章建表\\基础指标\\医院等级评审2025版-逻辑规则_本地记录.xlsx";
        String sheetName = "第三章指标处理";
        List<BigIndicatorInsertExcelData> dataList = EasyExcel.read(new File(fileName))
                .head(BigIndicatorInsertExcelData.class)
                .sheet(sheetName)
                .doReadSync();
        log.info("解析总行数 : {} 行", dataList.size());
        for (BigIndicatorInsertExcelData excelData : dataList) {
            this.invoke(excelData);
        }
        log.info("过滤后结果行数 : {} 行", result.size());

        this.createFile(-4, base_indicator_insert_path);

        // 打印解析结果
        for (Indicator indicator : result) {
            String indicatorName = indicator.getIndicatorName();
            String indicatorExist = indicator.getIndicatorExist();
            if(!"不存在".equals(indicatorExist)){
                continue;
            }
            long snowflakeNextId = IdUtil.getSnowflakeNextId();
            String updateSqlStr = StrUtil.format(PostManJsonFormat.bigIndicatorInsertSqlFormat, snowflakeNextId, indicatorName ,snowflakeNextId, indicatorName);
            try {
                Files.write(Paths.get(base_indicator_insert_path), updateSqlStr.getBytes(), StandardOpenOption.APPEND);
            } catch (IOException e) {
                log.error("追加内容时出错: ", e);
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

    public void invoke(BigIndicatorInsertExcelData data) {
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
            currentIndicator.setIndicatorExist(data.getIndicatorExit());
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