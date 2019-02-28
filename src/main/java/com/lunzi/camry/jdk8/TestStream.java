package com.lunzi.camry.jdk8;


import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * 流相关的操作
 * <p>
 * Created by lunzi on 2018/11/16 10:14 AM
 */
public class TestStream {
    //创建流
    public static void createStream() {
        Stream<Integer> streamOf = Stream.of(1, 2, 3);//of：由...组成
        Stream<Double> streamGen = Stream.generate(Math::random);
        Stream<Integer> streamIter = Stream.iterate(0, n -> n + 2);//无限流

    }

    //去重，排序，截断，计算个数
    public static void test1() {
        Stream<Integer> streams = Stream.of(1, 3, 5, 7, 2, 4, 7, 8, 9, 2);
        streams.distinct();//去重
        streams.sorted();//排序
        streams.limit(1);//截断
        streams.count();//计算个数

    }

    //latMap、flatMapToInt、flatMapToLong、flatMapToDouble
    //allMatch、noneMatch、anyMatch
    //findFirst、findAny、filter
    //skip、peek、collect
    //reduce
    public static void test2() {
        Stream<Integer> streams = Stream.of(1, 2, 3, 4);
        streams.map(x->x*2);//map 映射
        streams.mapToLong(x->x);
        streams.mapToInt(x->x);
        streams.mapToDouble(x->x);
        streams.skip(1);
    }


    public static void test3(){
        Stream<Integer> streams = Stream.of(1, 2, 3, 4);
        streams.flatMap(x->{
            return streams.skip(1);
        });
    }

    public static void test4(){

    }

    public static void testPara() {
        IntStream.range(1, 10).parallel().forEach((num) -> {
            try {
                Thread.sleep(2000);
                if (num % 2 == 0) {
                    System.out.println("偶数");
                } else {
                    System.out.println("质数");
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
    }

    public static void main(String[] args) {

    }
}
