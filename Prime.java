import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class Prime {
    private static void writeToFile(Integer time, Integer total, Integer sum) throws IOException {
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

    private static boolean isPrime(int n) {
        for (int i = 2; i < (n / 2) + 1; i++) {
            if (n % i == 0) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        int primeNumberCount = 0;
        int primeNumberSum = 0;

        Thread[] threads = new Thread[8];

        for (int i = 0; i < threads.length; i++) {
            PrimeThread primeThread = new PrimeThread(i);
            threads[i] = new Thread(primeThread);
            threads[i].start();
            // try {
            //     threads[i].join();
            // } catch (InterruptedException e) {
            //     e.printStackTrace();
            // }
        }

        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace(); 
            }
        }

        // for (int i = 2; i < 100000000; i++) {
        //     if (isPrime(i)) {
        //         primeNumberCount += 1;
        //         primeNumberSum += i;
        //     }
        // }

        // try {
        //     writeToFile(10, primeNumberCount, primeNumberSum);
        // } catch (IOException e) {
        //     e.printStackTrace();    
        // } 
    }
}
