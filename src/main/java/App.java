import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class App {



    static public void listFilesForFolder(final File folder) {

        ArrayList<File> arrayList = new ArrayList<>();

        for (final File fileEntry : folder.listFiles()) {
            if (fileEntry.isDirectory()) {
                listFilesForFolder(fileEntry);
            } else {
                arrayList.add(fileEntry);
                //System.out.println(fileEntry.getPath());
            }
        }

        arrayList.sort(Comparator.comparing(File::getName).reversed());

        for (File file : arrayList) {
            System.out.println(file.getPath());
        }
    }


    public static void main(String[] args) {

        final File folder = new File("src/main/resources");
        listFilesForFolder(folder);


//        File dir = new File("src/main/resources"); //path указывает на директорию
//        File[] arrFiles = dir.listFiles();
//        List<File> lst = Arrays.asList(arrFiles);


    }


}
