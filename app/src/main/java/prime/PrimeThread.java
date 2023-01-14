package prime;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

public class PrimeThread implements Runnable {
    private AtomicInteger counter;
    private AtomicInteger primeCount;
    private AtomicLong primeSum;
    private List<Integer> largestPrimes;
    
    public PrimeThread(AtomicInteger counter, AtomicInteger primeCount, AtomicLong primeSum, List<Integer> largestPrimes) {
        this.counter = counter;
        this.primeCount = primeCount;
        this.primeSum = primeSum;
        this.largestPrimes = largestPrimes;
    }

    private boolean isPrime(int n) {
        if (n % 2 == 0 || n % 3 == 0) { 
            return false;
        }

        int range = (int)Math.sqrt(n);

        for (int i = 5; i <= range; i += 6) { 
            if (n % i == 0 || n % (i + 2) == 0) { 
                return false; 
            }
        }
        return true;
    }

    private synchronized void addPrimeToList(int p) {
        largestPrimes.add(p);
    } 

    @Override 
    public void run() {
        while (this.counter.get() < 100000000) {
            int c = this.counter.getAndIncrement();
            if (isPrime(c)) {
                this.primeCount.incrementAndGet();
                this.primeSum.addAndGet(c);
                addPrimeToList(c);
            }
        }
    }
}
