package com.wanglj.wanglj2022.spark;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;

import java.util.Arrays;
import java.util.List;

/**
 * @author Wanglj
 * @date 2025/8/10 23:47
 * @desc SparkSqlDemo
 */
public class SparkSqlDemo {

    public static void main(String[] args) {
        SparkConf conf = new SparkConf().setAppName("SparkSqlDemo").setMaster("local[*]");

        JavaSparkContext javaSparkContext = new JavaSparkContext(conf);
        JavaRDD<Integer> javaRDD = javaSparkContext.parallelize(Arrays.asList(1, 2, 3, 4, 5));

        JavaRDD<Integer> squares = javaRDD.map(num -> num * num);

        // JavaRDD<Integer> filter = squares.filter(num -> num % 2 == 0);
        List<Integer> collect = squares.collect();
        for (Integer integer : collect) {
            System.out.println("inter: " +   integer);
        }
        collect.forEach(System.out::println);
        javaSparkContext.stop();

        // System.out.println("hello world");


    }
}
