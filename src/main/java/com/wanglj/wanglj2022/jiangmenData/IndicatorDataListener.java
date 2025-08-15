package com.wanglj.wanglj2022.jiangmenData;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import java.util.ArrayList;
import java.util.List;

public class IndicatorDataListener extends AnalysisEventListener<ExcelData> {
    
    // 最终解析结果
    private final List<Indicator> result = new ArrayList<>();
    
    // 临时变量，用于存储当前正在处理的指标
    private Indicator currentIndicator;
    
    @Override
    public void invoke(ExcelData data, AnalysisContext context) {
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
        if (currentIndicator != null && data.getDataItem() != null && !data.getDataItem().isEmpty()) {
            Indicator.DataItem item = new Indicator.DataItem();
            item.setDescription(data.getDataItem());
            currentIndicator.getDataItems().add(item);
        }
    }
    
    @Override
    public void doAfterAllAnalysed(AnalysisContext context) {
        // 添加最后一个指标
        if (currentIndicator != null) {
            result.add(currentIndicator);
        }
    }
    
    public List<Indicator> getResult() {
        return result;
    }
}