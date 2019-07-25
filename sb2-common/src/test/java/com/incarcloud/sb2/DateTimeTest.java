package com.incarcloud.sb2;

import junit.framework.TestCase;
import org.apache.commons.lang3.RandomUtils;
import org.junit.Test;

import java.util.function.Function;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;

/**
 * DateTimeTest
 *
 * @author Aaric, created on 2019-07-25T10:51.
 * @since 0.6.0-SNAPSHOT
 */
public class DateTimeTest extends TestCase {

    @Test
    public void testCompute() {
        int a = 1;
        int b = 1;
        int sumTimes = 8000;
        int cycleTimes = 100000;

        // start
        long start = System.nanoTime();

        // ----- int -----
        //Function<Integer, Integer> sumFunction = x -> a * x + b;
        //IntStream.rangeClosed(1, cycleTimes).parallel().forEach(object -> System.out.println("Total: " + IntStream.rangeClosed(1, sumTimes).parallel().map(x -> sumFunction.apply(x)).sum()));
        // 13932 | 7479

        // ----- double -----
        Function<Double, Double> doubleFunction = x -> a * x + b;
        IntStream.rangeClosed(1, cycleTimes)/*.parallel()*/.forEach(object -> System.out.println("Total: " + DoubleStream.generate(() -> RandomUtils.nextDouble(1, 10)).limit(sumTimes)/*.parallel()*/.map(x -> doubleFunction.apply(x)).sum()));
        // 47985 | 109567

        // end
        System.out.println("--> " + (System.nanoTime() - start) / 1_000_000);
    }
}
