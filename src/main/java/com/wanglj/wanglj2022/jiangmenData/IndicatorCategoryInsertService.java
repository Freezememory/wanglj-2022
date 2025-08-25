package com.wanglj.wanglj2022.jiangmenData;

import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.StrUtil;
import lombok.extern.slf4j.Slf4j;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Slf4j
public class IndicatorCategoryInsertService {

    private static final String indicator_id_path = "E:\\WorkFiles\\2019_广州\\2025_江门医院\\第三章建表\\基础指标\\基础指标id.txt";
    private static final String indicator_category_deal_path = "E:\\WorkFiles\\2019_广州\\2025_江门医院\\第三章建表\\基础指标\\IndicatorCategory.sql";

    public static void main(String[] args) {
        IndicatorCategoryInsertService indicatorExcelParser = new IndicatorCategoryInsertService();
        indicatorExcelParser.detailBatchDeal();
    }

    public void detailBatchDeal() {
        this.createFile(indicator_category_deal_path);
        try (Stream<String> lines = Files.lines(Paths.get(indicator_id_path))) {

            List<Long> detailIdList = lines.filter(StrUtil::isNotBlank).map(Long::parseLong).collect(Collectors.toList());

            long categoryId = 1948639228305166338L;
            for (Long indicatorId : detailIdList) {
                if (indicatorId == 1959890756194377728L) {
                    categoryId = 1948639341194858497L;
                }
                String batchSqlStr = StrUtil.format(IndicatorDetailSqlFormat.indicator_category_insert_sql_format, IdUtil.getSnowflakeNextId(), indicatorId,categoryId );
                try {
                    Files.write(Paths.get(indicator_category_deal_path), batchSqlStr.getBytes(), StandardOpenOption.APPEND);
                } catch (IOException e) {
                    log.error("追加内容时出错: ", e);
                }
            }

        } catch (IOException e) {
            System.err.println("读取文件时出错: " + e.getMessage());
        }
    }


    private void createFile(String orderFilePath) {
        try {
            FileWriter writer = new FileWriter(orderFilePath, false);
            writer.write("");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


}