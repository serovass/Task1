import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class App {

    static public List<String> readFileByFilter(String fileName, String filter) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(fileName));
        ArrayList<String> list = new ArrayList<>();
        while (reader.ready()) {
            String tmp = reader.readLine();
            if (tmp.contains(filter)) {
                list.add(tmp);
            }
        }
        reader.close();
        return list;
    }

    static public void listFilesForFolder(final File folder) throws IOException {

        LinkedList<File> list = new LinkedList<>();

        for (final File fileEntry : folder.listFiles()) {
            if (fileEntry.isDirectory()) {
                listFilesForFolder(fileEntry);
            } else {
                list.add(fileEntry);
            }
        }

        list.sort(Comparator.comparing(File::getName).reversed());
        //Collections.sort(list, Comparator.comparing(File::getAbsolutePath));


        for (File file : list) {
            System.out.println(file.getPath());
            List<String> listStr = readFileByFilter(file.getPath(), "require ");
            for (String str : listStr)
            {
                System.out.println(str);
            }
        }
    }


    public static void main(String[] args) throws IOException {

        final File folder = new File("src/main/resources");
        listFilesForFolder(folder);



    }


}
