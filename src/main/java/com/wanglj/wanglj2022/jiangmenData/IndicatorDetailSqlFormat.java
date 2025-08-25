package com.wanglj.wanglj2022.jiangmenData;

/**
 * @author Wanglj
 * @date 2025/8/14 22:13
 * @desc PostManJsonFormat
 */
public class IndicatorDetailSqlFormat {

    public static final String batchSqlFormat =
            "\n\n\n-- scheme_indicator_id_value\n" +
                    "update `chinaunicom_medical_hospital_level_performance`.`scheme_indicator_analysis_dimension_patient_details_rel` set `deleted` = 1 where type = 1 and scheme_indicator_id = scheme_indicator_id_value ;\n" +
                    "\n" +
                    "INSERT INTO `chinaunicom_medical_hospital_level_performance`.`scheme_indicator_analysis_dimension_patient_details_rel` (`id`, `create_by`, `create_time`, `update_by`, `update_time`, `deleted`, `scheme_indicator_id`, `type`, `field_type`, `field_code`, `field_name`) VALUES ({}, 'admin', SYSDATE(), 'admin', SYSDATE(), 0, scheme_indicator_id_value, 1, 'varchar', 'dis_dept_name', '出院科室名称');\n" +
                    "INSERT INTO `chinaunicom_medical_hospital_level_performance`.`scheme_indicator_analysis_dimension_patient_details_rel` (`id`, `create_by`, `create_time`, `update_by`, `update_time`, `deleted`, `scheme_indicator_id`, `type`, `field_type`, `field_code`, `field_name`) VALUES ({}, 'admin', SYSDATE(), 'admin', SYSDATE(), 0, scheme_indicator_id_value, 1, 'varchar', 'visit_no', '住院流水号');\n" +
                    "INSERT INTO `chinaunicom_medical_hospital_level_performance`.`scheme_indicator_analysis_dimension_patient_details_rel` (`id`, `create_by`, `create_time`, `update_by`, `update_time`, `deleted`, `scheme_indicator_id`, `type`, `field_type`, `field_code`, `field_name`) VALUES ({}, 'admin', SYSDATE(), 'admin', SYSDATE(), 0, scheme_indicator_id_value, 1, 'datetime', 'dis_datetime', '出院日期时间');\n" +
                    "INSERT INTO `chinaunicom_medical_hospital_level_performance`.`scheme_indicator_analysis_dimension_patient_details_rel` (`id`, `create_by`, `create_time`, `update_by`, `update_time`, `deleted`, `scheme_indicator_id`, `type`, `field_type`, `field_code`, `field_name`) VALUES ({}, 'admin', SYSDATE(), 'admin', SYSDATE(), 0, scheme_indicator_id_value, 1, 'varchar', 'patient_name', '病人姓名');\n" +
                    "INSERT INTO `chinaunicom_medical_hospital_level_performance`.`scheme_indicator_analysis_dimension_patient_details_rel` (`id`, `create_by`, `create_time`, `update_by`, `update_time`, `deleted`, `scheme_indicator_id`, `type`, `field_type`, `field_code`, `field_name`) VALUES ({}, 'admin', SYSDATE(), 'admin', SYSDATE(), 0, scheme_indicator_id_value, 1, 'int', 'inhostipal_days', '住院天数');\n" +
                    "INSERT INTO `chinaunicom_medical_hospital_level_performance`.`scheme_indicator_analysis_dimension_patient_details_rel` (`id`, `create_by`, `create_time`, `update_by`, `update_time`, `deleted`, `scheme_indicator_id`, `type`, `field_type`, `field_code`, `field_name`) VALUES ({}, 'admin', SYSDATE(), 'admin', SYSDATE(), 0, scheme_indicator_id_value, 1, 'varchar', 'leave_hospital_way_name', '离院方式');\n" +
                    "INSERT INTO `chinaunicom_medical_hospital_level_performance`.`scheme_indicator_analysis_dimension_patient_details_rel` (`id`, `create_by`, `create_time`, `update_by`, `update_time`, `deleted`, `scheme_indicator_id`, `type`, `field_type`, `field_code`, `field_name`) VALUES ({}, 'admin', SYSDATE(), 'admin', SYSDATE(), 0, scheme_indicator_id_value, 1, 'datetime', 'operation_datetime', '手术时间');\n" +
                    "INSERT INTO `chinaunicom_medical_hospital_level_performance`.`scheme_indicator_analysis_dimension_patient_details_rel` (`id`, `create_by`, `create_time`, `update_by`, `update_time`, `deleted`, `scheme_indicator_id`, `type`, `field_type`, `field_code`, `field_name`) VALUES ({}, 'admin', SYSDATE(), 'admin', SYSDATE(), 0, scheme_indicator_id_value, 1, 'varchar', 'operation_level_name', '手术名称');\n" +
                    "INSERT INTO `chinaunicom_medical_hospital_level_performance`.`scheme_indicator_analysis_dimension_patient_details_rel` (`id`, `create_by`, `create_time`, `update_by`, `update_time`, `deleted`, `scheme_indicator_id`, `type`, `field_type`, `field_code`, `field_name`) VALUES ({}, 'admin', SYSDATE(), 'admin', SYSDATE(), 0, scheme_indicator_id_value, 1, 'varchar', 'disease_diagnosis_name', '疾病诊断名称');";


    public static final String indicator_category_insert_sql_format = "INSERT INTO `chinaunicom_medical_mgmt_indicator`.`indicator_category_rel` (`id`, `indicator_id`, `category_id`, `create_by`, `create_time`, `update_by`, `update_time`, `version`, `deleted`) \n" +
            "VALUES ({}, {}, {}, 'admin', SYSDATE(), 'admin', SYSDATE(), 0, 0);\n\n";
}
