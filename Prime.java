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

    public static void main(String[] args) {
        try {
            writeToFile(10, 5, 11);
        } catch (IOException e) {
            e.printStackTrace();    
        } 
    }
}
