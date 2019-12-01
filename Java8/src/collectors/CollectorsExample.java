package collectors;


import java.util.*;
import java.util.concurrent.ConcurrentMap;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * A simple Java Program to demonstrate how to use collectors to collect the result of Stream in different collections e.g. List, Set, and Map, and exploring advanced Collectors options like gropuingBy and partitionBy
 */

public class CollectorsExample {

    public static void main(String[] args) {

        List<Integer> integerList = Arrays.asList(10, 20, 30, 11, 20, 33, 4, 44, 55, 20);


        // 1. Collectors.toSet() Example
        Set<Integer> numbersWithoutDups = integerList.stream().collect(Collectors.toSet());
        System.out.println(integerList + " :Input list" );
        System.out.println(numbersWithoutDups+ " :stream().collect(Collectors.toSet())");

        System.out.println();System.out.println();

        // 2. Collectors.toList() Example
        List<Integer> numbersWithDups = integerList.stream().collect(Collectors.toList());
        System.out.println(integerList + " :Input list" );
        System.out.println(numbersWithDups + " :stream().collect(Collectors.toList())");

        System.out.println();System.out.println();

        // 3. Collectors.toCollection() Example
        ArrayList<Integer> anArrayList = integerList.stream()
                .collect(Collectors.toCollection(ArrayList::new));

        System.out.println(integerList + " :Input list" );
        System.out.println(anArrayList + " :ArrayList created using .collect(Collectors.toCollection(ArrayList::new))");

        System.out.println();System.out.println();

        // 4. Collectors.toMap() Example
        Map<Integer, String> intToString = numbersWithoutDups.stream()
                .collect(Collectors.toMap(Function.identity(), String::valueOf));

        System.out.println(numbersWithoutDups + " :Input list");
        System.out.println(intToString + " :Map created from numbersWithoutDups using .collect(Collectors.toMap(Function.identity(), String::valueOf))");

        System.out.println();System.out.println();

        // 5. Collectors.toConcurrentMap() Example
        ConcurrentMap<Integer, String> concurrentIntToString
                = numbersWithoutDups.parallelStream()
                .collect(Collectors.toConcurrentMap(Function.identity(),
                        String::valueOf));

        System.out.println(numbersWithoutDups + " :Input list" );
        System.out.println(concurrentIntToString + " :ConcurrentMap created by Collectors.toConcurrentMap()");
        System.out.println();System.out.println();

        // 6. Collectors.joining() Example
        String csv = integerList.stream().map(String::valueOf).collect(Collectors.joining(", "));

        System.out.println(numbersWithoutDups + " :Input list" );
        System.out.println(csv + " Comma separated String created by Collectors.joining()");
        System.out.println();System.out.println();

        // 7. Collectors.summaryStatistics() Example
        IntSummaryStatistics summary = integerList.stream()
                .collect(Collectors.summarizingInt(Integer::valueOf));

        double average = summary.getAverage();
        int maximum = summary.getMax();
        int minimum = summary.getMin();

        System.out.println(integerList + " :Input list" );
        System.out.println("Average of all number from list using SummaryStatistics: " + average);
        System.out.println("Maximum of all number from list using SummaryStatistics: " + maximum);
        System.out.println("Minimum of all number from list using SummaryStatistics: " + minimum);
        System.out.println();System.out.println();

        // 8. Collectors.groupingBy() Example
        Stream<Locale> streamOfLocales = Stream.of(Locale.getAvailableLocales());

        Map<String, List<Locale>> countryToLocale = streamOfLocales
                .collect(Collectors.groupingBy(Locale::getCountry));

        System.out.println("input list of locales" + streamOfLocales);
        System.out.println("locales group by countries using Collectors.groupingBy: "
                + countryToLocale);
        System.out.println();System.out.println();


        // 9. Collectors.partitionBy() Example
        Map<Boolean, List<Integer>> evenAndOddNumbers = integerList.stream().
                collect(Collectors.partitioningBy(number -> number%2 ==0 ));

        System.out.println("input list: " + integerList);
        System.out.println("list of even nubmers: " + evenAndOddNumbers.get(true));
        System.out.println("list of odd nubmers: " + evenAndOddNumbers.get(false));
        System.out.println();System.out.println();

        // 10. Collectors.counting() Example
        long count = integerList.stream().filter( number -> number> 10)
                .collect(Collectors.counting());
        System.out.println("count: " + count);
    }


    public class Student {
        private int id;
        private String name;

        public Student(int id, String name) {
            this.id = id;
            this.setName(name);
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

}

