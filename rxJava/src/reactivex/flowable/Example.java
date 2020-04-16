package reactivex.flowable;

import io.reactivex.Flowable;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class Example {
  public static long doubleIt(long value) {
    System.out.println("called for " + value);
    return value * 2;
  }

  public static void main(String[] args) throws InterruptedException {
    List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6);

//    numbers.stream()
//      .filter(e -> e % 2 == 0)
//      .map(e -> doubleIt(e))
//      .forEach(System.out::println);

    System.out.println("Example io.reactivex.Flowable");
    
    Flowable.interval(0, 1, TimeUnit.SECONDS)
      .filter(e -> e % 2 == 0)
      .map(e -> doubleIt(e))
      .take(4)
      .subscribe(System.out::println);

    Thread.sleep(6000);
  }
}
