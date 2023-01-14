package prime;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

public class Prime {
    private static void writeToFile(Long time, Integer total, Long sum, List<Integer> largestPrimes) throws IOException {
        OutputStream output = null;
        String content = "Time: " +time.toString() + "s Count: " + total.toString() + " Sum: " + sum.toString() + "\n10 Largest Primes: " + largestPrimes.toString();
        try {
            output = new FileOutputStream("output.txt");
            output.write(content.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            output.close();
        }
    }

    public static void main(String[] args) {
        Date start = new Date();

        Thread[] threads = new Thread[8];
        AtomicInteger counter = new AtomicInteger(4);
        AtomicInteger primeCount = new AtomicInteger(2);
        AtomicLong primeSum = new AtomicLong(5);
        List<Integer> primes = new ArrayList<Integer>();

        for (int i = 0; i < threads.length; i++) {
            PrimeThread primeThread = new PrimeThread(counter, primeCount, primeSum, primes);
            threads[i] = new Thread(primeThread);
            threads[i].start();
        }

        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace(); 
            }
        }
        
        List<Integer> condensedList = primes.subList(primes.size() - 10, primes.size());
        Collections.sort(condensedList);
        List<Integer> largestPrimes = condensedList.subList(condensedList.size() - 10, condensedList.size());
        
        Date end = new Date();

        long seconds = (end.getTime() - start.getTime()) / 1000;
        
        Collections.sort(largestPrimes);

        try {
            writeToFile(seconds, primeCount.get(), primeSum.get(), largestPrimes);
        } catch (IOException e) {
            e.printStackTrace();    
        } 
    }
}
