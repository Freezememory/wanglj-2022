package com.wanglj.wanglj2022;

/**
 * @author Wanglj
 * @date 2023/4/22 16:07
 * @desc Test
 */
public class Test {


    @org.junit.jupiter.api.Test
    void contextLoads() throws Exception {
        //String s = ocrTest.encodeBase64File("C:\\Users\\11525\\Pictures\\my\\xinshicard.png");
        //System.out.println(s);
        System.out.println(getAge(8));
    }

    private Integer getAge(int i) {
        i--;
        int age;
        if (i < 0) {
            age = 10;
        } else {
            age = getAge(i) + 2;
        }
        return age;
    }

}
