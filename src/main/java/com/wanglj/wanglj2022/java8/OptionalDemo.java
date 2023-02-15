package com.wanglj.wanglj2022.java8;

import java.security.GeneralSecurityException;
import java.util.HashMap;
import java.util.Optional;

/**
 * @Author: Wanglj
 * @Date: 2022/12/29 15:51
 * @Description :
 */
public class OptionalDemo {


/*    public static void main(String[] args) {
        optionDemo();
    }*/


    public static void optionDemo() {
        HashMap<String, Object> map = new HashMap<>();

        Object key = Optional.ofNullable(map.get("key")).orElse(null);

/*        if(key.isPresent()){
            Object o = key.get();
            System.out.println(o);
        }else {
            System.out.println("没有值");
        }*/

        //Object value = null;
        //Optional<Object> value1 = Optional.ofNullable(value);
    }


    public static void main(String[] args) throws GeneralSecurityException {
        //1.Empty 创建一个空的Optional
        Optional<String> empty = Optional.empty();
        System.out.println("empty: " + empty.isPresent());


        //2.of如果为null会直接报空指针;ofNullable如果为null会继续执行，不影响程序
        //Optional<String> optionalString=Optional.of(null);
        Optional<String> optionalString2 = Optional.ofNullable(null);
        //Optional<User> optionalUser = Optional.ofNullable(new User(null, "张三"));
        Optional<User> optionalUser = Optional.ofNullable(null);


        //3.filter 如果一个值存在，并且该值给定的谓词相匹配时，返回一个 Optional描述的值，否则返回一个空的 Optional 。
        System.out.println("filter: " + optionalUser.filter(o -> o.getName().equals("张三")).map(User::getId).orElseGet(() -> "null"));

        //4.flatMap 如果一个值存在，应用提供的 Optional映射函数给它，返回该结果，否则返回一个空的 Optional 。
        System.out.println("flatMap: " + optionalUser.flatMap(OptionalDemo::getFlatMap));

        //5.orElse 返回值如果存在，否则返回 other  。
        System.out.println("orElse: " + optionalUser.map(User::getId).map(String::toUpperCase).orElse("123"));

        //6.orElseGet 如果存在该值，返回值， 否则触发 other，并返回 other 调用的结果。
        System.out.println("orElseGet: " + optionalUser.map(User::getId).map(String::toUpperCase).orElseGet(() -> "暂无数据"));

        //7.orElseThrow 如果存在该值，返回包含的值，否则抛出由 Supplier 继承的异常
        System.out.println("orElseThrow: " + optionalUser.map(User::getId).map(String::toUpperCase).orElseThrow(ClassCastException::new));

        Optional<User> optionalUser1 = Optional.of(new User("123", null));

        //8.isPresent 返回 true如果存在值，否则为 false 。
        System.out.println("isPresent: " + optionalUser1.isPresent());

        //9.ifPresent 如果存在值，则使用该值调用指定的消费者，否则不执行任何操作。
        System.out.print("ifPresent: ");
        optionalUser1.ifPresent(o -> System.out.println(o.getName()));
        //ifPresent判断当前数据是否为空，为空的会直接报空指针
        //optionalUser1.ifPresent(o->o.getName().equals("z行数"));

        //10.get 如果 Optional中有一个值，返回值，否则抛出 NoSuchElementException 。
        //11.equals 指示某个其他对象是否等于此可选项。
        Optional<User> optionalUser2 = Optional.of(new User("23", "张三"));
        System.out.println("equals: " + optionalUser2.map(User::getName).map(o -> o.equals("张三")).get());
    }


    private static Optional<User> getFlatMap(User user) {
        return Optional.ofNullable(user);
    }


}
