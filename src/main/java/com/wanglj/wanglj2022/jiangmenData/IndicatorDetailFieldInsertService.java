package com.wanglj.wanglj2022.jiangmenData;

import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.StrUtil;
import lombok.extern.slf4j.Slf4j;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Slf4j
public class IndicatorDetailFieldInsertService {

    private static final String detail_id_path = "E:\\WorkFiles\\2019_广州\\2025_江门医院\\第三章建表\\明细字段处理\\明细字段待处理id.txt";
    private static final String detail_batch_deal_path = "E:\\WorkFiles\\2019_广州\\2025_江门医院\\第三章建表\\明细字段处理\\detail_batch_deal.sql";

    public static void main(String[] args) {
        IndicatorDetailFieldInsertService indicatorExcelParser = new IndicatorDetailFieldInsertService();
        indicatorExcelParser.detailBatchDeal();
    }

    public void detailBatchDeal() {
        this.createFile(detail_batch_deal_path);
        try (Stream<String> lines = Files.lines(Paths.get(detail_id_path))) {

            List<Long> detailIdList = lines.map(Long::parseLong).collect(Collectors.toList());

            for (Long schemeIndicatorId : detailIdList) {
                List<Long> list = new ArrayList<>();
                for (int j = 0; j < 9; j++) {
                    list.add(IdUtil.getSnowflakeNextId());
                }
                String dealFormat = IndicatorDetailSqlFormat.batchSqlFormat
                        .replace("scheme_indicator_id_value", String.valueOf(schemeIndicatorId))
                        .replace("{}", "%s");
                log.info("dealFormat: {}" , dealFormat );
                String batchSqlStr = String.format(dealFormat, list.toArray());

                try {
                    Files.write(Paths.get(detail_batch_deal_path), batchSqlStr.getBytes(), StandardOpenOption.APPEND);
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