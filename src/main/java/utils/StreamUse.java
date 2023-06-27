package utils;

import designModle.Person;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * @author KCWang
 * @version 1.0 stream流的用法 筛选、排序、聚合等操作
 * @date 2023/6/25 下午11:00
 */
public class StreamUse {
    public static void main(String[] args) {

        /**
         * 创建stream
         */
        int[] ints = {1, 2, 3};
        IntStream stream = Arrays.stream(ints);
        //并行流
        Arrays.asList(1,2,3).parallelStream().peek(System.out::println);
        Integer integer1 = Arrays.asList(1, 2, 3).parallelStream().filter(s -> s > 2).findFirst().get();
        Integer integer = Arrays.asList(1, 2, 3).parallelStream().filter(s -> s > 7).findFirst().orElseGet(() ->0);
        String s1 = Arrays.asList("1", "2", "3").stream().collect(Collectors.joining("-"));
        String s2 = Arrays.asList(1, 2, 3).stream().map(String::valueOf).collect(Collectors.joining("-"));
        System.out.println(s1+s2);

        /**
         * Collectors  对结果分流处理
         */
        List<Person> personList = Arrays.asList(
                Person.builder().sex("0").age(1).build(),
                Person.builder().sex("0").age(2).build(),
                Person.builder().sex("0").age(2).build(),
                Person.builder().sex("0").age(3).build());

        personList.stream()
                .peek(s -> System.out.println("对结果分流处理"+s.getSex()))
                .collect(Collectors.partitioningBy(s -> s.getAge() > 2))
                .forEach((bollean, list) -> list.forEach(person -> person.setSex(bollean?"女":"男")
                ));
        personList.stream()
                .peek(s -> System.out.println(s.getSex()))
                .collect(Collectors.groupingBy(s -> s.getAge()))
                .forEach((integer2, people) ->{
                    System.out.println("对结果分流处理"+people.stream().map(Person::getAge).reduce(0, Integer::sum));
                });
        personList.forEach(s -> System.out.println(s.getSex()));
        //排序
        personList.stream().sorted(Comparator.comparing(Person::getAge,Comparator.reverseOrder())).findFirst().ifPresent((x) ->System.out.println(x.getAge()));
        personList.stream().sorted(Comparator.comparing(Person::getAge)).findFirst().ifPresent((x) ->System.out.println(x.getAge()));
        //聚合
        System.out.println(personList.stream().max(Comparator.comparingInt(Person::getAge)).get().getAge());
        //

    }
}
