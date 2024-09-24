import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class initializeRepository {
    //checks if files/directories exist then creates them
    public void initializeRepository() {
        String pathToDirectory = System.getProperty("user.dir");
        String gitPath = pathToDirectory + File.separator + "git";
        String objectsPath = gitPath + File.separator + "objects";
        String indexPath = gitPath + File.separator + "index";

        File git = new File(gitPath);

        if (!git.exists()) {
            try {
                git.mkdir();

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        File objects = new File(objectsPath);

        if (!objects.exists()) {
            try {
                objects.mkdir();

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        File index = new File(indexPath);
        if (!index.exists()){
            try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(indexPath))) {
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        if ((git.exists()) && (objects.exists()) && (index.exists())) {
        System.out.println("Git Repo already exits");
        }
    }

    //deletes index, objects, and git
    public void deleteReopsitory(){
        String pathToDirectory = System.getProperty("user.dir");
        String gitPath = pathToDirectory + File.separator + "git";
        String objectsPath = gitPath + File.separator + "objects";
        String indexPath = gitPath + File.separator + "index";

        File git = new File(gitPath);
        File objects = new File(objectsPath);
        File index = new File(indexPath);

        String deleted = "deleted: ";
        if (objects.exists()){
            objects.delete();
            deleted+="objects ";
        }
        if (index.exists()){
            index.delete();
            deleted+="index ";
        }
        if (git.exists()){
            git.delete();
            deleted+="git ";
        }
        
        System.out.println (deleted);
    }
}