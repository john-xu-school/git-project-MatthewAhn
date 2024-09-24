import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class git {
    String pathToDirectory = System.getProperty("user.dir");
    String gitPath = pathToDirectory + File.separator + "git";
    String objectsPath = gitPath + File.separator + "objects";
    String indexPath = gitPath + File.separator + "index";

    int zip = 0;

    // checks if files/directories exist then creates them
    public void initializeRepository() {

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
        if (!index.exists()) {
            try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(indexPath))) {
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        if ((git.exists()) && (objects.exists()) && (index.exists())) {
            System.out.println("Git Repo already exits");
        }
    }

    // deletes index, objects, and git
    public void deleteReopsitory() {
        File git = new File(gitPath);
        File objects = new File(objectsPath);
        File index = new File(indexPath);

        String deleted = "deleted: ";
        if (objects.exists()) {
            objects.delete();
            deleted += "objects ";
        }
        if (index.exists()) {
            index.delete();
            deleted += "index ";
        }
        if (git.exists()) {
            git.delete();
            deleted += "git ";
        }

        System.out.println(deleted);
    }

    
    public void blob(String filename){
         // get file conents and sha1 hash
        StringBuffer contents = new StringBuffer();
        try {
            FileReader file = new FileReader(gitPath + File.separator +filename);
            BufferedReader bufferReader = new BufferedReader(file);

            char character = (char) bufferReader.read();

            while (character != (char) -1) {
                contents.append(character);
                character = (char) bufferReader.read();
            }
        }catch(Exception e){
            System.out.println(e+"sss");
        }

        String contentsString = zip(contents.toString());

        String hashCode = sha1HashCode(contentsString);
        
         // create file in objects folder with name as the hash and same contents
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(objectsPath + File.separator+ hashCode))) {
           bufferedWriter.write(contents.toString());
           bufferedWriter.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        // write the hash [space] filename as a line in index
        try{
            BufferedWriter indexEditor = new BufferedWriter(new FileWriter(indexPath));
            indexEditor.write(hashCode + " " + filename);
            indexEditor.newLine();
            indexEditor.close();
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    // generates sha1 hashcode badsed on contents
    public String sha1HashCode(String contents) {
        String result="";
        try{
            MessageDigest message = MessageDigest.getInstance("SHA-1");
            byte [] array = message.digest(contents.getBytes());
            for (byte i : array) {
                result += String.format("%02x", i);
            }
            while (result.length() < 40) {
                result = "0" + result;
            }
        }catch (NoSuchAlgorithmException e){
            System.out.println(e);
        }
        return result;
    }

    private String zip(String contentsString){
        String result = contentsString;
        if (zip == 1){
           
        }
        return result;
    }
}