package com.wanglj.wanglj2022.base;


import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import sun.misc.BASE64Encoder;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URLEncoder;
//import com.sun.org.apache.xml.internal.security.utils.Base64;
/**
 * @Author: Wanglj
 * @Date: 2022/10/13 22:49
 * @Description :
 */
@Slf4j
@Component
public class OCRTest {

    public static void main(String[] args) {
        String strUrl = "";
        //String strFilep = "E://mnt//hgfs//LinuxTestPic//21//002003.jpg";
        String strFilep = "C:\\Users\\11525\\Pictures\\xsz\\123.jpg";
        String strpid = "2";//pid
        File file = new File(strFilep);
        FileInputStream is;

        try {
/*            is = new FileInputStream(file);
            byte[] data = new byte[is.available()];
            is.read(data);
            String base64file = Base64.encode(data);*/

            //获取OCR识别图片base64编码
            String base64FileString = FileBase64.encodeBase64File(strFilep);
            //参数拼接
            String params = "filedata=" + URLEncoder.encode(base64FileString, "utf-8");
            params += "&pid=" + URLEncoder.encode("5", "utf-8");

            System.out.println(params);
            //JSONObject object = readByPOST(strUrl, params);
            //System.out.println(object.toJSONString());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


/*    public static void main(String[] args) throws Exception {
        //String strUrl = "C:\\Users\\11525\\Pictures\\my\\jiashicard.jpg";
        String strUrl = "C:\\Users\\11525\\Pictures\\my\\xinshicard.png";
*//*        File file = new File(strUrl);
        InputStream inputStream = new FileInputStream(file);
        MultipartFile multipartFile = new MockMultipartFile(file.getName(), inputStream);
        String base64String = getBase64String(multipartFile);
        System.out.println(base64String);*//*
       // System.out.println(URLEncoder.encode(base64String, "utf-8"));

        //File file = new File(strUrl);
        //String base64String = getBase64String(file);

        String base64StringNew = encodeBase64File(strUrl);

        //String encode = URLEncoder.encode(base64String, "utf-8");
        String encode = URLEncoder.encode(base64StringNew, "utf-8");
        //System.out.println(base64file);
        System.out.println(encode);
        //System.out.println(URLEncoder.encode("5", "utf-8"));
    }*/

    /**
     * <p>将文件转成base64 字符串</p>
     * @param path 文件路径
     * @return
     * @throws Exception
     */
    public static String encodeBase64File(String path) throws Exception {
        File file = new File(path);
        FileInputStream inputFile = new FileInputStream(file);
        byte[] buffer = new byte[(int)file.length()];
        inputFile.read(buffer);
        inputFile.close();
        return new BASE64Encoder().encode(buffer);
    }

    /**
     * 获取图片base64编码
     *
     * @param file 上传文件
     * @return base64编码
     */
/*    public static String getBase64String(MultipartFile file) {
        BASE64Encoder bEncoder = new BASE64Encoder();
        String[] suffixArra = file.getOriginalFilename().split("\\.");
        String preffix = "data:image/jpg;base64,".replace("jpg", suffixArra[suffixArra.length - 1]);
        String base64EncoderImg = "";
        try {
            base64EncoderImg = preffix + bEncoder.encode(file.getBytes()).replaceAll("[\\s*\t\n\r]", "");
        } catch (IOException e) {
            log.error("OCR图片转Base64编码异常：{}", e.getMessage());
        }
        return base64EncoderImg;
    }*/

/*    public static   String getBase64String(File ocrFile) {
        FileInputStream is = null;
        String base64EncoderImg = "";
        try {
            is = new FileInputStream(ocrFile);
            byte[] data = new byte[is.available()];
            is.read(data);
            base64EncoderImg = Base64.encode(data);
        }catch (Exception e){
            log.error("OCR图片转Base64编码异常：{}", e.getMessage());
        }finally {
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    log.error("OCR文件写入流关闭异常：{}", e.getMessage());
                }
            }
        }
        return base64EncoderImg;
    }*/

/*
    public static void main(String[] args) {
        //JSONObject ocrResultObject = JSONObject.parseObject(jiashiStr);
        JSONObject ocrResultObject = JSONObject.parseObject(xinshiStr);
        JSONObject resultObject = new JSONObject();
        if (ocrResultObject == null) {
            log.info("OCR识别失败");
        }
        log.info("OCR识别结果：{}", ocrResultObject.toJSONString());
        //调用OCR接口状态返回值，0成功
        String resultCode = ocrResultObject.getString("ErrorCode") == null ? "1" : ocrResultObject.getString("ErrorCode");
        if ("0".equals(resultCode)) {
            //获取页面结果信息
            JSONObject resultListObject = ocrResultObject.getJSONArray("PageInfo").getJSONObject(0)
                    .getJSONArray("Result").getJSONObject(0)
                    .getJSONArray("ResultList").getJSONObject(0);
            //识别返回的数据结果集
            JSONArray jsonArray = resultListObject.getJSONArray("FieldList");
            if (jsonArray != null) {
                int size = jsonArray.size();
                for (int i = 0; i < size; i++) {
                    String key = jsonArray.getJSONObject(i).getString("key");//字段名
                    String value = jsonArray.getJSONObject(i).getString("value");//具体值 如驾驶证号码
                    String chnKey = jsonArray.getJSONObject(i).getString("chn_key");//字段中文说明
                    //获取返回页面结果
                    getCardInfo(key, value, chnKey, resultObject);
                }
            }
        } else {
            log.info("OCR识别失败");
        }
        log.info("结果： {}", resultObject);
    }
*/

    /**
     * @param key          参数值
     * @param value        识别值
     * @param chnKey       中文说明值
     * @param resultObject 返回结果
     */
    private static void getCardInfo(String key, String value, String chnKey, JSONObject resultObject) {
        if (("姓名").equals(chnKey)) {
            resultObject.put("xm", value);
        }
        if (("证号").equals(chnKey)) {
            resultObject.put("jszh", value);
        }
        if (("号牌号码").equals(chnKey)) {
            resultObject.put("hphm", value);
        }
        if (("车辆类型").equals(chnKey)) {
            resultObject.put("cllx", value);
        }
        if (("注册日期").equals(chnKey)) {
            resultObject.put("zcrq", value);
        }
        if (("发动机号").equals(chnKey)) {
            String fdjh = value == null ? "" : value;
            //发动机号只取后六位
            fdjh = fdjh.length() > 6 ? fdjh.substring(fdjh.length() - 6) : fdjh;
            resultObject.put("fdjh", fdjh);
        }
        if (("品牌型号").equals(chnKey)) {
            String ppxh = value == null ? "" : value;
            //中文正则
            String REGEX_CHINESE = "[\u4e00-\u9fa5]";
            //品牌型号去除中文
            ppxh = ppxh.replaceAll(REGEX_CHINESE, "");
            resultObject.put("ppxh", ppxh);
        }
    }


    private static String xinshiStr = "{\n" +
            "    \"PageInfo\": [\n" +
            "        {\n" +
            "            \"img_data\": \"\",\n" +
            "            \"PageIndex\": 1,\n" +
            "            \"ErrorCode\": 0,\n" +
            "            \"Result\": [\n" +
            "                {\n" +
            "                    \"ResultList\": [\n" +
            "                        {\n" +
            "                            \"image_data\": \"/9jasdas\",\n" +
            "                            \"direct\": 0,\n" +
            "                            \"angle\": 0,\n" +
            "                            \"pid\": 5,\n" +
            "                            \"position\": {\n" +
            "                                \"top\": 41,\n" +
            "                                \"left\": 14,\n" +
            "                                \"width\": 583,\n" +
            "                                \"height\": 402\n" +
            "                            },\n" +
            "                            \"type\": \"行驶证正页\",\n" +
            "                            \"FieldList\": [\n" +
            "                                {\n" +
            "                                    \"score\": 99,\n" +
            "                                    \"chn_key\": \"号牌号码\",\n" +
            "                                    \"quad\": [\n" +
            "                                        273,\n" +
            "                                        175,\n" +
            "                                        474,\n" +
            "                                        175,\n" +
            "                                        476,\n" +
            "                                        223,\n" +
            "                                        273,\n" +
            "                                        223\n" +
            "                                    ],\n" +
            "                                    \"image_data\": \"/9j/4AAQSkZ\",\n" +
            "                                    \"position\": {\n" +
            "                                        \"top\": 175,\n" +
            "                                        \"left\": 273,\n" +
            "                                        \"width\": 201,\n" +
            "                                        \"height\": 48\n" +
            "                                    },\n" +
            "                                    \"value\": \"粤K6026R\",\n" +
            "                                    \"key\": \"PlateNo\"\n" +
            "                                },\n" +
            "                                {\n" +
            "                                    \"score\": 99,\n" +
            "                                    \"chn_key\": \"车辆类型\",\n" +
            "                                    \"quad\": [\n" +
            "                                        738,\n" +
            "                                        171,\n" +
            "                                        920,\n" +
            "                                        171,\n" +
            "                                        920,\n" +
            "                                        221,\n" +
            "                                        738,\n" +
            "                                        221\n" +
            "                                    ],\n" +
            "                                    \"image_data\": \"/9j/4AAQSkZ\",\n" +
            "                                    \"position\": {\n" +
            "                                        \"top\": 171,\n" +
            "                                        \"left\": 738,\n" +
            "                                        \"width\": 182,\n" +
            "                                        \"height\": 50\n" +
            "                                    },\n" +
            "                                    \"value\": \"小型轿车\",\n" +
            "                                    \"key\": \"VType\"\n" +
            "                                },\n" +
            "                                {\n" +
            "                                    \"score\": 99,\n" +
            "                                    \"chn_key\": \"所有人\",\n" +
            "                                    \"quad\": [\n" +
            "                                        273,\n" +
            "                                        255,\n" +
            "                                        419,\n" +
            "                                        255,\n" +
            "                                        419,\n" +
            "                                        310,\n" +
            "                                        273,\n" +
            "                                        310\n" +
            "                                    ],\n" +
            "                                    \"image_data\": \"/9j/4AAQSkZ\",\n" +
            "                                    \"position\": {\n" +
            "                                        \"top\": 255,\n" +
            "                                        \"left\": 273,\n" +
            "                                        \"width\": 146,\n" +
            "                                        \"height\": 55\n" +
            "                                    },\n" +
            "                                    \"value\": \"唐国都\",\n" +
            "                                    \"key\": \"Owner\"\n" +
            "                                },\n" +
            "                                {\n" +
            "                                    \"score\": 99,\n" +
            "                                    \"chn_key\": \"地址\",\n" +
            "                                    \"quad\": [\n" +
            "                                        273,\n" +
            "                                        339,\n" +
            "                                        975,\n" +
            "                                        323,\n" +
            "                                        975,\n" +
            "                                        376,\n" +
            "                                        273,\n" +
            "                                        392\n" +
            "                                    ],\n" +
            "                                    \"image_data\": \"/9j/4AAQSkZ\",\n" +
            "                                    \"position\": {\n" +
            "                                        \"top\": 323,\n" +
            "                                        \"left\": 273,\n" +
            "                                        \"width\": 702,\n" +
            "                                        \"height\": 69\n" +
            "                                    },\n" +
            "                                    \"value\": \"广东省高州市大坡镇贺口堡村30号\",\n" +
            "                                    \"key\": \"Address\"\n" +
            "                                },\n" +
            "                                {\n" +
            "                                    \"score\": 99,\n" +
            "                                    \"chn_key\": \"使用性质\",\n" +
            "                                    \"quad\": [\n" +
            "                                        262,\n" +
            "                                        430,\n" +
            "                                        419,\n" +
            "                                        424,\n" +
            "                                        421,\n" +
            "                                        481,\n" +
            "                                        264,\n" +
            "                                        485\n" +
            "                                    ],\n" +
            "                                    \"image_data\": \"/9j/4AAQSkZ\",\n" +
            "                                    \"position\": {\n" +
            "                                        \"top\": 424,\n" +
            "                                        \"left\": 262,\n" +
            "                                        \"width\": 157,\n" +
            "                                        \"height\": 61\n" +
            "                                    },\n" +
            "                                    \"value\": \"非营运\",\n" +
            "                                    \"key\": \"UseCharacter\"\n" +
            "                                },\n" +
            "                                {\n" +
            "                                    \"score\": 99,\n" +
            "                                    \"chn_key\": \"品牌型号\",\n" +
            "                                    \"quad\": [\n" +
            "                                        656,\n" +
            "                                        410,\n" +
            "                                        1148,\n" +
            "                                        394,\n" +
            "                                        1150,\n" +
            "                                        449,\n" +
            "                                        656,\n" +
            "                                        465\n" +
            "                                    ],\n" +
            "                                    \"image_data\": \"/9j/4AAQSkZ\",\n" +
            "                                    \"position\": {\n" +
            "                                        \"top\": 394,\n" +
            "                                        \"left\": 656,\n" +
            "                                        \"width\": 492,\n" +
            "                                        \"height\": 71\n" +
            "                                    },\n" +
            "                                    \"value\": \"东风日产牌DFL7201VANF2\",\n" +
            "                                    \"key\": \"Model\"\n" +
            "                                },\n" +
            "                                {\n" +
            "                                    \"score\": 99,\n" +
            "                                    \"chn_key\": \"车辆识别代码\",\n" +
            "                                    \"quad\": [\n" +
            "                                        624,\n" +
            "                                        496,\n" +
            "                                        1029,\n" +
            "                                        483,\n" +
            "                                        1032,\n" +
            "                                        537,\n" +
            "                                        629,\n" +
            "                                        551\n" +
            "                                    ],\n" +
            "                                    \"image_data\": \"/9j/4AAQSkZ\",\n" +
            "                                    \"position\": {\n" +
            "                                        \"top\": 483,\n" +
            "                                        \"left\": 624,\n" +
            "                                        \"width\": 405,\n" +
            "                                        \"height\": 68\n" +
            "                                    },\n" +
            "                                    \"value\": \"LGBF7AE05KR166641\",\n" +
            "                                    \"key\": \"VIN\"\n" +
            "                                },\n" +
            "                                {\n" +
            "                                    \"score\": 99,\n" +
            "                                    \"chn_key\": \"发动机号\",\n" +
            "                                    \"quad\": [\n" +
            "                                        599,\n" +
            "                                        590,\n" +
            "                                        774,\n" +
            "                                        583,\n" +
            "                                        777,\n" +
            "                                        638,\n" +
            "                                        601,\n" +
            "                                        647\n" +
            "                                    ],\n" +
            "                                    \"image_data\": \"/9j/4AAQSkZ\",\n" +
            "                                    \"position\": {\n" +
            "                                        \"top\": 583,\n" +
            "                                        \"left\": 599,\n" +
            "                                        \"width\": 175,\n" +
            "                                        \"height\": 64\n" +
            "                                    },\n" +
            "                                    \"value\": \"884513Y\",\n" +
            "                                    \"key\": \"EngineNo\"\n" +
            "                                },\n" +
            "                                {\n" +
            "                                    \"score\": 99,\n" +
            "                                    \"chn_key\": \"注册日期\",\n" +
            "                                    \"quad\": [\n" +
            "                                        533,\n" +
            "                                        690,\n" +
            "                                        793,\n" +
            "                                        674,\n" +
            "                                        795,\n" +
            "                                        731,\n" +
            "                                        537,\n" +
            "                                        747\n" +
            "                                    ],\n" +
            "                                    \"image_data\": \"/9j/4AAQSkZ\",\n" +
            "                                    \"position\": {\n" +
            "                                        \"top\": 674,\n" +
            "                                        \"left\": 533,\n" +
            "                                        \"width\": 260,\n" +
            "                                        \"height\": 73\n" +
            "                                    },\n" +
            "                                    \"value\": \"2019-12-13\",\n" +
            "                                    \"key\": \"RegisterDate\"\n" +
            "                                },\n" +
            "                                {\n" +
            "                                    \"score\": 99,\n" +
            "                                    \"chn_key\": \"发证日期\",\n" +
            "                                    \"quad\": [\n" +
            "                                        927,\n" +
            "                                        658,\n" +
            "                                        1157,\n" +
            "                                        647,\n" +
            "                                        1157,\n" +
            "                                        699,\n" +
            "                                        929,\n" +
            "                                        711\n" +
            "                                    ],\n" +
            "                                    \"image_data\": \"/9j/4AAQSkZ\",\n" +
            "                                    \"position\": {\n" +
            "                                        \"top\": 647,\n" +
            "                                        \"left\": 927,\n" +
            "                                        \"width\": 230,\n" +
            "                                        \"height\": 64\n" +
            "                                    },\n" +
            "                                    \"value\": \"2019-12-13\",\n" +
            "                                    \"key\": \"IssueDate\"\n" +
            "                                },\n" +
            "                                {\n" +
            "                                    \"score\": 99,\n" +
            "                                    \"chn_key\": \"发证机关\",\n" +
            "                                    \"quad\": [\n" +
            "                                        107,\n" +
            "                                        531,\n" +
            "                                        364,\n" +
            "                                        524,\n" +
            "                                        367,\n" +
            "                                        752,\n" +
            "                                        109,\n" +
            "                                        765\n" +
            "                                    ],\n" +
            "                                    \"image_data\": \"/9j/4AAQSkZ\",\n" +
            "                                    \"position\": {\n" +
            "                                        \"top\": 524,\n" +
            "                                        \"left\": 107,\n" +
            "                                        \"width\": 257,\n" +
            "                                        \"height\": 241\n" +
            "                                    },\n" +
            "                                    \"value\": \"广东省茂名市公安局交通警察支队\",\n" +
            "                                    \"key\": \"Authority\"\n" +
            "                                }\n" +
            "                            ]\n" +
            "                        }\n" +
            "                    ],\n" +
            "                    \"Time\": \"2949\",\n" +
            "                    \"ErrorCode\": 0\n" +
            "                }\n" +
            "            ]\n" +
            "        }\n" +
            "    ],\n" +
            "    \"ErrorCode\": 0,\n" +
            "    \"SerialId\": 1580565931205652500\n" +
            "}";

    private static String jiashiStr = "{\n" +
            "    \"PageInfo\": [\n" +
            "        {\n" +
            "            \"img_data\": \"\",\n" +
            "            \"PageIndex\": 1,\n" +
            "            \"ErrorCode\": 0,\n" +
            "            \"Result\": [\n" +
            "                {\n" +
            "                    \"ResultList\": [\n" +
            "                        {\n" +
            "                            \"image_data\": \"/9jasdas\",\n" +
            "                            \"direct\": 0,\n" +
            "                            \"head_image_data\": \"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\",\n" +
            "                            \"head_position\": {\n" +
            "                                \"top\": 256,\n" +
            "                                \"left\": 600,\n" +
            "                                \"width\": 249,\n" +
            "                                \"height\": 295\n" +
            "                            },\n" +
            "                            \"angle\": 0,\n" +
            "                            \"pid\": 7,\n" +
            "                            \"position\": {\n" +
            "                                \"top\": 90,\n" +
            "                                \"left\": 117,\n" +
            "                                \"width\": 855,\n" +
            "                                \"height\": 586\n" +
            "                            },\n" +
            "                            \"type\": \"驾驶证正页\",\n" +
            "                            \"FieldList\": [\n" +
            "                                {\n" +
            "                                    \"score\": 99,\n" +
            "                                    \"chn_key\": \"证号\",\n" +
            "                                    \"quad\": [\n" +
            "                                        326,\n" +
            "                                        120,\n" +
            "                                        661,\n" +
            "                                        115,\n" +
            "                                        663,\n" +
            "                                        150,\n" +
            "                                        327,\n" +
            "                                        155\n" +
            "                                    ],\n" +
            "                                    \"image_data\": \"/9j/4AAQSkZ\",\n" +
            "                                    \"position\": {\n" +
            "                                        \"top\": 115,\n" +
            "                                        \"left\": 326,\n" +
            "                                        \"width\": 335,\n" +
            "                                        \"height\": 40\n" +
            "                                    },\n" +
            "                                    \"value\": \"440602198911260019\",\n" +
            "                                    \"key\": \"CardNo\"\n" +
            "                                },\n" +
            "                                {\n" +
            "                                    \"score\": 70,\n" +
            "                                    \"chn_key\": \"姓名\",\n" +
            "                                    \"quad\": [\n" +
            "                                        107,\n" +
            "                                        177,\n" +
            "                                        307,\n" +
            "                                        177,\n" +
            "                                        307,\n" +
            "                                        215,\n" +
            "                                        107,\n" +
            "                                        215\n" +
            "                                    ],\n" +
            "                                    \"image_data\": \"/9j/4AAQSkZ\",\n" +
            "                                    \"position\": {\n" +
            "                                        \"top\": 177,\n" +
            "                                        \"left\": 107,\n" +
            "                                        \"width\": 200,\n" +
            "                                        \"height\": 38\n" +
            "                                    },\n" +
            "                                    \"value\": \"陈俊亮\",\n" +
            "                                    \"key\": \"Name\"\n" +
            "                                },\n" +
            "                                {\n" +
            "                                    \"score\": 99,\n" +
            "                                    \"chn_key\": \"性别\",\n" +
            "                                    \"quad\": [\n" +
            "                                        0,\n" +
            "                                        0,\n" +
            "                                        0,\n" +
            "                                        0,\n" +
            "                                        0,\n" +
            "                                        0,\n" +
            "                                        0,\n" +
            "                                        0\n" +
            "                                    ],\n" +
            "                                    \"image_data\": \"/9j/4AAQSkZ\",\n" +
            "                                    \"position\": {\n" +
            "                                        \"top\": 0,\n" +
            "                                        \"left\": 0,\n" +
            "                                        \"width\": 0,\n" +
            "                                        \"height\": 0\n" +
            "                                    },\n" +
            "                                    \"value\": \"男\",\n" +
            "                                    \"key\": \"Sex\"\n" +
            "                                },\n" +
            "                                {\n" +
            "                                    \"score\": 99,\n" +
            "                                    \"chn_key\": \"国籍\",\n" +
            "                                    \"quad\": [\n" +
            "                                        0,\n" +
            "                                        0,\n" +
            "                                        0,\n" +
            "                                        0,\n" +
            "                                        0,\n" +
            "                                        0,\n" +
            "                                        0,\n" +
            "                                        0\n" +
            "                                    ],\n" +
            "                                    \"image_data\": \"/9j/4AAQSkZ\",\n" +
            "                                    \"position\": {\n" +
            "                                        \"top\": 0,\n" +
            "                                        \"left\": 0,\n" +
            "                                        \"width\": 0,\n" +
            "                                        \"height\": 0\n" +
            "                                    },\n" +
            "                                    \"value\": \"中国\",\n" +
            "                                    \"key\": \"Nationality\"\n" +
            "                                },\n" +
            "                                {\n" +
            "                                    \"score\": 99,\n" +
            "                                    \"chn_key\": \"地址\",\n" +
            "                                    \"quad\": [\n" +
            "                                        107,\n" +
            "                                        229,\n" +
            "                                        721,\n" +
            "                                        220,\n" +
            "                                        721,\n" +
            "                                        311,\n" +
            "                                        107,\n" +
            "                                        311\n" +
            "                                    ],\n" +
            "                                    \"image_data\": \"/9j/4AAQSkZ\",\n" +
            "                                    \"position\": {\n" +
            "                                        \"top\": 220,\n" +
            "                                        \"left\": 107,\n" +
            "                                        \"width\": 614,\n" +
            "                                        \"height\": 91\n" +
            "                                    },\n" +
            "                                    \"value\": \"广东省佛山市禅城区上沙东街82号之一302房\",\n" +
            "                                    \"key\": \"Address\"\n" +
            "                                },\n" +
            "                                {\n" +
            "                                    \"score\": 99,\n" +
            "                                    \"chn_key\": \"出生日期\",\n" +
            "                                    \"quad\": [\n" +
            "                                        341,\n" +
            "                                        329,\n" +
            "                                        514,\n" +
            "                                        327,\n" +
            "                                        516,\n" +
            "                                        361,\n" +
            "                                        341,\n" +
            "                                        364\n" +
            "                                    ],\n" +
            "                                    \"image_data\": \"/9j/4AAQSkZ\",\n" +
            "                                    \"position\": {\n" +
            "                                        \"top\": 327,\n" +
            "                                        \"left\": 341,\n" +
            "                                        \"width\": 173,\n" +
            "                                        \"height\": 37\n" +
            "                                    },\n" +
            "                                    \"value\": \"1989-11-26\",\n" +
            "                                    \"key\": \"Birth\"\n" +
            "                                },\n" +
            "                                {\n" +
            "                                    \"score\": 99,\n" +
            "                                    \"chn_key\": \"初始领证日期\",\n" +
            "                                    \"quad\": [\n" +
            "                                        386,\n" +
            "                                        384,\n" +
            "                                        561,\n" +
            "                                        384,\n" +
            "                                        563,\n" +
            "                                        421,\n" +
            "                                        387,\n" +
            "                                        421\n" +
            "                                    ],\n" +
            "                                    \"image_data\": \"/9j/4AAQSkZ\",\n" +
            "                                    \"position\": {\n" +
            "                                        \"top\": 384,\n" +
            "                                        \"left\": 386,\n" +
            "                                        \"width\": 175,\n" +
            "                                        \"height\": 37\n" +
            "                                    },\n" +
            "                                    \"value\": \"2011-03-07\",\n" +
            "                                    \"key\": \"FirstIssueDate\"\n" +
            "                                },\n" +
            "                                {\n" +
            "                                    \"score\": 87,\n" +
            "                                    \"chn_key\": \"准驾车型\",\n" +
            "                                    \"quad\": [\n" +
            "                                        414,\n" +
            "                                        448,\n" +
            "                                        508,\n" +
            "                                        448,\n" +
            "                                        508,\n" +
            "                                        479,\n" +
            "                                        414,\n" +
            "                                        479\n" +
            "                                    ],\n" +
            "                                    \"image_data\": \"/9j/4AAQSkZ\",\n" +
            "                                    \"position\": {\n" +
            "                                        \"top\": 448,\n" +
            "                                        \"left\": 414,\n" +
            "                                        \"width\": 94,\n" +
            "                                        \"height\": 31\n" +
            "                                    },\n" +
            "                                    \"value\": \"C1\",\n" +
            "                                    \"key\": \"Class\"\n" +
            "                                },\n" +
            "                                {\n" +
            "                                    \"score\": 99,\n" +
            "                                    \"chn_key\": \"起始日期\",\n" +
            "                                    \"quad\": [\n" +
            "                                        180,\n" +
            "                                        501,\n" +
            "                                        601,\n" +
            "                                        501,\n" +
            "                                        601,\n" +
            "                                        538,\n" +
            "                                        180,\n" +
            "                                        538\n" +
            "                                    ],\n" +
            "                                    \"image_data\": \"/9j/4AAQSkZ\",\n" +
            "                                    \"position\": {\n" +
            "                                        \"top\": 501,\n" +
            "                                        \"left\": 180,\n" +
            "                                        \"width\": 421,\n" +
            "                                        \"height\": 37\n" +
            "                                    },\n" +
            "                                    \"value\": \"2017-03-07\",\n" +
            "                                    \"key\": \"ValidFrom\"\n" +
            "                                },\n" +
            "                                {\n" +
            "                                    \"score\": 99,\n" +
            "                                    \"chn_key\": \"有效截止日期/有效年限\",\n" +
            "                                    \"quad\": [\n" +
            "                                        180,\n" +
            "                                        501,\n" +
            "                                        601,\n" +
            "                                        501,\n" +
            "                                        601,\n" +
            "                                        538,\n" +
            "                                        180,\n" +
            "                                        538\n" +
            "                                    ],\n" +
            "                                    \"image_data\": \"/9j/4AAQSkZ\",\n" +
            "                                    \"position\": {\n" +
            "                                        \"top\": 501,\n" +
            "                                        \"left\": 180,\n" +
            "                                        \"width\": 421,\n" +
            "                                        \"height\": 37\n" +
            "                                    },\n" +
            "                                    \"value\": \"2027-03-07\",\n" +
            "                                    \"key\": \"ValidTo(For)\"\n" +
            "                                },\n" +
            "                                {\n" +
            "                                    \"score\": 99,\n" +
            "                                    \"chn_key\": \"发证机关\",\n" +
            "                                    \"quad\": [\n" +
            "                                        52,\n" +
            "                                        339,\n" +
            "                                        220,\n" +
            "                                        336,\n" +
            "                                        220,\n" +
            "                                        488,\n" +
            "                                        53,\n" +
            "                                        489\n" +
            "                                    ],\n" +
            "                                    \"image_data\": \"/9j/4AAQSkZ\",\n" +
            "                                    \"position\": {\n" +
            "                                        \"top\": 336,\n" +
            "                                        \"left\": 52,\n" +
            "                                        \"width\": 168,\n" +
            "                                        \"height\": 153\n" +
            "                                    },\n" +
            "                                    \"value\": \"广东省佛山市公安局交通警察支队\",\n" +
            "                                    \"key\": \"Authority\"\n" +
            "                                }\n" +
            "                            ]\n" +
            "                        }\n" +
            "                    ],\n" +
            "                    \"Time\": \"2632\",\n" +
            "                    \"ErrorCode\": 0\n" +
            "                }\n" +
            "            ]\n" +
            "        }\n" +
            "    ],\n" +
            "    \"ErrorCode\": 0,\n" +
            "    \"SerialId\": 1580566811107717000\n" +
            "}";
}
