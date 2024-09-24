import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

public class initializeRepositoryTester {
    public static void main(String[] args) {
    
        git test = new git();
    
        // test.initializeRepository();
        // test.deleteReopsitory();

        // write data, filname and correct hash
        String data = "hello";
        String correctHash = "aaf4c61ddcc5e8a2dabede0f3b482cd9aea9434d";
        String filename = "testFile";
        // make the file and run blob on it
        String pathToDirectory = System.getProperty("user.dir") + File.separator + "git" + File.separator;
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(pathToDirectory+filename))){
                bufferedWriter.write(data);
        }
        catch(Exception e){
            System.out.print(e);
        }

        test.blob(filename);
        // checks if index and objects match what they should
            // does index have "aaf4c61ddcc5e8a2dabede0f3b482cd9aea9434d testFile"
            // does objects have "aaf4c61ddcc5e8a2dabede0f3b482cd9aea9434d" with "hello" inside it
        }
}
