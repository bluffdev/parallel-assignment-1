package prime;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

public class PrimeThread implements Runnable {
    private AtomicInteger counter;
    private AtomicInteger primeCount;
    private AtomicLong primeSum;
    
    public PrimeThread(AtomicInteger counter, AtomicInteger primeCount, AtomicLong primeSum) {
        this.counter = counter;
        this.primeCount = primeCount;
        this.primeSum = primeSum;
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

    @Override 
    public void run() {
        while (this.counter.get() < 100000000) {
            if (isPrime(this.counter.getAndIncrement())) {
                this.primeCount.incrementAndGet();
                // this.primeSum.addAndGet(c);
            }
        }
    }
}
