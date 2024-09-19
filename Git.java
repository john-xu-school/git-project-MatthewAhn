import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Git{
    public static void main(String[] args) {
        String pathToDirectory = System.getProperty("user.dir");

        try{new File(pathToDirectory + File.separator + "git").mkdir();}
        catch (Exception e) {
            e.printStackTrace();
        }

        new File(pathToDirectory + File.separator + "git" + File.separator + "objects").mkdir();
        catch (Exception e) {
            e.printStackTrace();
        }

        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("index"))) {
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}