package com.wanglj.wanglj2022.jiangmenData;

/**
 * @author Wanglj
 * @date 2025/8/14 22:13
 * @desc PostManJsonFormat
 */
public class PostManJsonFormat {


    public static final String prefixFormat = "{\n" +
            "\t\"info\": {\n" +
            "\t\t\"_postman_id\": \"de9320ce-1663-4b81-ba7f-a36e8e4c6777\",\n" +
            "\t\t\"name\": \"{}\",\n" +
            "\t\t\"schema\": \"https://schema.getpostman.com/json/collection/v2.1.0/collection.json\",\n" +
            "\t\t\"_exporter_id\": \"6910147\"\n" +
            "\t},\n" +
            "\t\"item\": [\n";
    public static final String bodyFormat = "{\\r\\n  \\\"layerId\\\": \\\"1934450759259664390\\\",\\r\\n  \\\"firstTheme\\\": \\\"1950030402880557058\\\",\\r\\n  \\\"secondTheme\\\": \\\"{}\\\",\\r\\n  \\\"upgradeFlag\\\": \\\"_full\\\",\\r\\n  \\\"upgradeFrequency\\\": \\\"_daily\\\",\\r\\n  \\\"sdsStandard\\\": 0,\\r\\n  \\\"sql\\\": \\\"CREATE TABLE `{}` (\\\\n\\\\t`business_dt` INT NULL COMMENT \\\\\\\"业务时间yyyy/yyyymm/yyyyqq/yyyymmdd\\\\\\\",\\\\n\\\\t`dim_type` VARCHAR(50) NULL COMMENT \\\\\\\"维度类型(科室/专业组/全科室)\\\\\\\",\\\\n\\\\t`dept_code` VARCHAR(128) NULL COMMENT \\\\\\\"科室编码\\\\\\\",\\\\n\\\\t`spec_doct_code` VARCHAR(128) NULL DEFAULT \\\\\\\"0\\\\\\\" COMMENT \\\\\\\"专业组代码\\\\\\\",\\\\n\\\\t`business_dt_type` VARCHAR(128) NULL COMMENT \\\\\\\"业务时间类型(日/月/季/半年/年/月(26~25)/年(26~25)/季(26~25)/半年(26~25))\\\\\\\" ,\\\\n\\\\t`dept_name` VARCHAR(255) NULL COMMENT \\\\\\\"科室名称\\\\\\\",\\\\n\\\\t`spec_doct_name` VARCHAR(128) NULL DEFAULT \\\\\\\"0\\\\\\\" COMMENT \\\\\\\"专业组名称\\\\\\\",\\\\n\\\\t`label_result` DECIMAL(20,2) NULL COMMENT \\\\\\\"结果值\\\\\\\",\\\\n\\\\t`tb_value` DECIMAL(20,2) NULL COMMENT \\\\\\\"同比分析\\\\\\\",\\\\n\\\\t`hb_value` DECIMAL(20,2) NULL COMMENT \\\\\\\"环比分析\\\\\\\",\\\\n\\\\t`create_by` VARCHAR(32) NULL COMMENT \\\\\\\"创建人\\\\\\\",\\\\n\\\\t`create_time` DATETIME NULL COMMENT \\\\\\\"创建时间\\\\\\\",\\\\n\\\\t`update_by` VARCHAR(32) NULL COMMENT \\\\\\\"最后更新人\\\\\\\",\\\\n\\\\t`update_time` DATETIME NULL COMMENT \\\\\\\"最后更新时间\\\\\\\",\\\\n\\\\t`theme_code` VARCHAR(500) NULL COMMENT \\\\\\\"主题编码(指标编码)\\\\\\\",\\\\n\\\\t`theme_name` VARCHAR(500) NULL COMMENT \\\\\\\"主题名称(指标名称)\\\\\\\",\\\\n\\\\t`sjtjsj` DATETIME NULL COMMENT \\\\\\\"数据统计时间\\\\\\\"\\\\n) ENGINE=OLAP\\\\nUNIQUE KEY(business_dt,\\\\n  dim_type,\\\\n  dept_code,\\\\n  spec_doct_code,\\\\n  business_dt_type)\\\\nCOMMENT \\\\\\\"{}\\\\\\\"\\\\nDISTRIBUTED BY HASH(business_dt,\\\\n  dim_type,\\\\n  dept_code,\\\\n  spec_doct_code,\\\\n  business_dt_type) BUCKETS 8 ;\\\",\\r\\n  \\\"datasourceType\\\": \\\"14\\\",\\r\\n  \\\"datasourceId\\\": \\\"1934450975773831170\\\",\\r\\n  \\\"fullNameFormat\\\": \\\"ads_GA03_{}_%s_full_daily\\\"\\r\\n}";


    public   static  final  String  jsonFormat = "\t\t{\n" +
            "\t\t\t\"name\": \"{}\",\n" +
            "\t\t\t\"protocolProfileBehavior\": {\n" +
            "\t\t\t\t\"disabledSystemHeaders\": {\n" +
            "\t\t\t\t\t\"accept\": true,\n" +
            "\t\t\t\t\t\"accept-encoding\": true,\n" +
            "\t\t\t\t\t\"user-agent\": true,\n" +
            "\t\t\t\t\t\"host\": true\n" +
            "\t\t\t\t}\n" +
            "\t\t\t},\n" +
            "\t\t\t\"request\": {\n" +
            "\t\t\t\t\"auth\": {\n" +
            "\t\t\t\t\t\"type\": \"noauth\"\n" +
            "\t\t\t\t},\n" +
            "\t\t\t\t\"method\": \"POST\",\n" +
            "\t\t\t\t\"header\": [\n" +
            "\t\t\t\t\t{\n" +
            "\t\t\t\t\t\t\"key\": \"Content-Type\",\n" +
            "\t\t\t\t\t\t\"value\": \"application/json;charset=UTF-8\"\n" +
            "\t\t\t\t\t},\n" +
            "\t\t\t\t\t{\n" +
            "\t\t\t\t\t\t\"key\": \"Accept\",\n" +
            "\t\t\t\t\t\t\"value\": \"application/json, text/plain, */*\",\n" +
            "\t\t\t\t\t\t\"type\": \"text\"\n" +
            "\t\t\t\t\t},\n" +
            "\t\t\t\t\t{\n" +
            "\t\t\t\t\t\t\"key\": \"accept-encoding\",\n" +
            "\t\t\t\t\t\t\"value\": \"gzip, deflate\",\n" +
            "\t\t\t\t\t\t\"type\": \"text\"\n" +
            "\t\t\t\t\t},\n" +
            "\t\t\t\t\t{\n" +
            "\t\t\t\t\t\t\"key\": \"accept-language\",\n" +
            "\t\t\t\t\t\t\"value\": \"zh-CN,zh;q=0.9\",\n" +
            "\t\t\t\t\t\t\"type\": \"text\"\n" +
            "\t\t\t\t\t},\n" +
            "\t\t\t\t\t{\n" +
            "\t\t\t\t\t\t\"key\": \"applicationid\",\n" +
            "\t\t\t\t\t\t\"value\": \"7556448279687169\",\n" +
            "\t\t\t\t\t\t\"type\": \"text\"\n" +
            "\t\t\t\t\t},\n" +
            "\t\t\t\t\t{\n" +
            "\t\t\t\t\t\t\"key\": \"cookie\",\n" +
            "\t\t\t\t\t\t\"value\": \"JSESSIONID=2878BC7D079BF26C7A0EF78699A13661; satoken=b5507be3-2c1e-4dc0-b0d1-bcea5c927630; responsive-token=45b683c8-eea2-482a-bb0f-3fd01db2e3e2; _webtracing_device_id=t_13500be7-88dcf0c6-4f6729a489fcaa79; instanceId=7556448279687169; _webtracing_session_id=s_13500bf7-5747e6c2-150c368c00924edf; sidebarStatusKey=closed\",\n" +
            "\t\t\t\t\t\t\"type\": \"text\"\n" +
            "\t\t\t\t\t},\n" +
            "\t\t\t\t\t{\n" +
            "\t\t\t\t\t\t\"key\": \"host\",\n" +
            "\t\t\t\t\t\t\"value\": \"192.168.62.20:7500\",\n" +
            "\t\t\t\t\t\t\"type\": \"text\"\n" +
            "\t\t\t\t\t},\n" +
            "\t\t\t\t\t{\n" +
            "\t\t\t\t\t\t\"key\": \"origin\",\n" +
            "\t\t\t\t\t\t\"value\": \"http://192.168.62.20:7500\",\n" +
            "\t\t\t\t\t\t\"type\": \"text\"\n" +
            "\t\t\t\t\t},\n" +
            "\t\t\t\t\t{\n" +
            "\t\t\t\t\t\t\"key\": \"referer\",\n" +
            "\t\t\t\t\t\t\"value\": \"http://192.168.62.20:7500/bigdata/data-governance-view\",\n" +
            "\t\t\t\t\t\t\"type\": \"text\"\n" +
            "\t\t\t\t\t},\n" +
            "\t\t\t\t\t{\n" +
            "\t\t\t\t\t\t\"key\": \"user-agent\",\n" +
            "\t\t\t\t\t\t\"value\": \"Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/136.0.0.0 Safari/537.36\",\n" +
            "\t\t\t\t\t\t\"type\": \"text\"\n" +
            "\t\t\t\t\t}\n" +
            "\t\t\t\t],\n" +
            "\t\t\t\t\"body\": {\n" +
            "\t\t\t\t\t\"mode\": \"raw\",\n" +
            "\t\t\t\t\t\"raw\": \"{}\",\n" +
            "\t\t\t\t\t\"options\": {\n" +
            "\t\t\t\t\t\t\"raw\": {\n" +
            "\t\t\t\t\t\t\t\"language\": \"json\"\n" +
            "\t\t\t\t\t\t}\n" +
            "\t\t\t\t\t}\n" +
            "\t\t\t\t},\n" +
            "\t\t\t\t\"url\": {\n" +
            "\t\t\t\t\t\"raw\": \"http://192.168.62.20:7500/bigdata/module/v1/module/table/batchDDLCreate\",\n" +
            "\t\t\t\t\t\"protocol\": \"http\",\n" +
            "\t\t\t\t\t\"host\": [\n" +
            "\t\t\t\t\t\t\"192\",\n" +
            "\t\t\t\t\t\t\"168\",\n" +
            "\t\t\t\t\t\t\"62\",\n" +
            "\t\t\t\t\t\t\"20\"\n" +
            "\t\t\t\t\t],\n" +
            "\t\t\t\t\t\"port\": \"7500\",\n" +
            "\t\t\t\t\t\"path\": [\n" +
            "\t\t\t\t\t\t\"bigdata\",\n" +
            "\t\t\t\t\t\t\"module\",\n" +
            "\t\t\t\t\t\t\"v1\",\n" +
            "\t\t\t\t\t\t\"module\",\n" +
            "\t\t\t\t\t\t\"table\",\n" +
            "\t\t\t\t\t\t\"batchDDLCreate\"\n" +
            "\t\t\t\t\t]\n" +
            "\t\t\t\t}\n" +
            "\t\t\t},\n" +
            "\t\t\t\"response\": []\n" +
            "\t\t},\n";
}
