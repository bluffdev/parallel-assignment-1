public class PrimeThread implements Runnable {
    private int number;
    
    public PrimeThread(int number) {
        this.number = number;
    }

    @Override 
    public void run() {
        System.out.println(this.number);
    } 
}
