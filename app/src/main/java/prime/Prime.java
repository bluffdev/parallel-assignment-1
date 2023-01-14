package prime;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Date;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

public class Prime {
    private static void writeToFile(Long time, Integer total, Long sum) throws IOException {
        OutputStream output = null;
        String content = time.toString() + " " + total.toString() + " " + sum.toString();
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
 
        for (int i = 0; i < threads.length; i++) {
            PrimeThread primeThread = new PrimeThread(counter, primeCount, primeSum);
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

        // [99999989, 99999971, 99999959, 99999941, 99999931, 99999847, 99999839, 99999827, 99999821, 99999787]

        Date end = new Date();

        long seconds = (end.getTime() - start.getTime()) / 1000;
        
        System.out.println(seconds);
        System.out.println(primeCount.get());
        System.out.println(primeSum.get());

        // try {
        //     writeToFile(seconds, primeNumberCount, primeNumberSum);
        // } catch (IOException e) {
        //     e.printStackTrace();    
        // } 
    }
}
