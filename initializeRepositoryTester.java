// import java.io.BufferedWriter;
// import java.io.BufferedReader;
import java.io.File;
//import java.io.FileReader;
//import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.*;
//import java.nio.file.attribute.BasicFileAttributes;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class initializeRepositoryTester {

    static void traverse(String nextExpected){
        try{
            String treeContent = Files.readString(Paths.get("git"+File.separator+"objects"+File.separator+nextExpected));
            
            String[] lines = treeContent.split("\n");
            
            for (int i = 0; i < lines.length; i++){
                String[] lineContent = lines[i].split(" ");
                if (lineContent[0].equals("tree")){
                    traverse(lineContent[1]);
                }
                else{
                    Path pathToActualFile = findByFileName(Paths.get("test"), lineContent[2]).get(0);
                    String correctHash = git.sha1HashCode(Files.readString(pathToActualFile)); //rely on Hash Function to work
                    if (lineContent[1].equals(correctHash)){
                        System.out.print("Correct Hash for the following: ");
                    }
                    else{
                        System.out.print("INCORRECT Hash for the following: ");
                    }
                    System.out.println(lineContent[1] + " " + lineContent[2]);
                }
            }
        }
        catch (IOException e){
            e.printStackTrace();
        }

    }

    public static List<Path> findByFileName(Path path, String fileName)
            throws IOException {

        List<Path> result;
        try (Stream<Path> pathStream = Files.find(path,
                Integer.MAX_VALUE,
                (p, basicFileAttributes) ->
                        p.getFileName().toString().equalsIgnoreCase(fileName))
        ) {
            result = pathStream.collect(Collectors.toList());
        }
        return result;

    }

    public static void main(String[] args) {

        git test = new git();

        test.initializeRepository();

        test.deleteReopsitory();

        test.initializeRepository();

        test.generateAllTrees(Paths.get("test"));

        try{
            String testFolderHash = "8b90bec967664846c02a1bed968044fb1b991030"; //must be correct for the test to run
            String treeContent = Files.readString(Paths.get("git"+File.separator+"objects"+File.separator+testFolderHash));
            
            String[] lines = treeContent.split("\n");
            for (int i = 0; i < lines.length; i++){
                String[] lineContent = lines[i].split(" ");
                if (lineContent[0].equals("tree")){
                    traverse(lineContent[1]);
                }
                else{
                    Path pathToActualFile = findByFileName(Paths.get("test"), lineContent[2]).get(0);
                    String correctHash = git.sha1HashCode(Files.readString(pathToActualFile)); //rely on Hash Function to work
                    if (lineContent[1].equals(correctHash)){
                        System.out.print("Correct Hash for the following: ");
                    }
                    else{
                        System.out.print("INCORRECT Hash for the following: ");
                    }
                    System.out.println(lineContent[1] + " " + lineContent[2]);
                }
            }
        }
        catch (IOException e){
            System.out.println("A file that is supposed to exist doesn't");
            e.printStackTrace();
        }

        //Old Tester Below

        // // write data, filname and correct hash
        // String data = "hello";
        // String filename = "testFile";
        // // make the file and run blob on it
        // String pathToDirectory = System.getProperty("user.dir") + File.separator + "git" + File.separator;
        // try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(pathToDirectory + filename))) {
        //     bufferedWriter.write(data);
        //     bufferedWriter.close();
        // } catch (Exception e) {
        //     System.out.print(e);
        // }

        // test.generateAllTrees(Paths.get("test"));

        // //test.blob(Paths.get("git/testFile"));
        // // checks if index and objects match what they should
        // try {
        //     FileReader file = new FileReader(pathToDirectory+ "index");
        //     BufferedReader reader = new BufferedReader(file);

        //     System.out.println("is " + reader.readLine());
        //     System.out.println("C: aaf4c61ddcc5e8a2dabede0f3b482cd9aea9434d testfile");

        //     FileReader fileTwo = new FileReader(pathToDirectory+"objects"+File.separator+"aaf4c61ddcc5e8a2dabede0f3b482cd9aea9434d");
        //     BufferedReader readerTwo = new BufferedReader(fileTwo);

        //     System.out.println("is " + readerTwo.readLine());
        //     System.out.println("C: hello");
        // } catch (Exception e) {
        //     System.out.println(e);
        // }
    }
}
