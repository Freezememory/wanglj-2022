package com.wanglj.wanglj2022;

import com.wanglj.wanglj2022.base.OCRTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class Wanglj2022ApplicationTests {

    @Autowired
    private OCRTest ocrTest;
    @Test
    void contextLoads() throws Exception {
        String s = ocrTest.encodeBase64File("C:\\Users\\11525\\Pictures\\my\\xinshicard.png");
        System.out.println(s);
    }





}
