package com.wanlj

import org.apache.spark.sql.SparkSession

/**
 * @author Wanglj
 * @date 2024/6/24 10:55
 * @desc HelloWorld
 */
object HelloWorld {

  def main(array: Array[String]): Unit = {
    println("Hello  Wanglj")


     val spark =  SparkSession.builder().appName("SparkSql01")
       .master("local[*]")
       .getOrCreate();

   val df =  spark.read.option("header","true")
      .textFile("F:\\idea_workspace_2022\\wanglj-2022\\src\\main\\resources\\static\\test.csv");

    df.show();

  }
}
