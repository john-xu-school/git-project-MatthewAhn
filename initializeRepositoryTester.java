import java.io.BufferedWriter;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

public class initializeRepositoryTester {
    public static void main(String[] args) {

        git test = new git();

        test.initializeRepository();
        // test.deleteReopsitory();

        // write data, filname and correct hash
        String data = "hello";
        String filename = "testFile";
        // make the file and run blob on it
        String pathToDirectory = System.getProperty("user.dir") + File.separator + "git" + File.separator;
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(pathToDirectory + filename))) {
            bufferedWriter.write(data);
            bufferedWriter.close();
        } catch (Exception e) {
            System.out.print(e);
        }

        test.blob(filename);
        // checks if index and objects match what they should
        try {
            FileReader file = new FileReader(pathToDirectory+ "index");
            BufferedReader reader = new BufferedReader(file);

            System.out.println("is " + reader.readLine());
            System.out.println("C: aaf4c61ddcc5e8a2dabede0f3b482cd9aea9434d testfile");

            FileReader fileTwo = new FileReader(pathToDirectory+"objects"+File.separator+"aaf4c61ddcc5e8a2dabede0f3b482cd9aea9434d");
            BufferedReader readerTwo = new BufferedReader(fileTwo);

            System.out.println("is " + readerTwo.readLine());
            System.out.println("C: hello");
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
