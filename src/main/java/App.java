import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class App {

    static LinkedList<File> list = new LinkedList<>();

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

        for (final File fileEntry : folder.listFiles()) {
            if (fileEntry.isDirectory()) {
                listFilesForFolder(fileEntry);
            } else {
                list.add(fileEntry);
            }
        }
    }


    public static void main(String[] args) throws IOException {

        final File folder = new File("src/main/resources");
        listFilesForFolder(folder);


        list.sort(Comparator.comparing(File::getName));

        System.out.println("\n-------- SORTED BY NAME ----------");////

        for (File file : list)
        {
            System.out.println(file.getPath());////
        }

        System.out.println("\n-------- SORTING BY require ----------");////
        for (int i = 0; i < list.size(); ++i) {

            System.out.println(list.get(i).getPath());
            List<String> listStr = readFileByFilter(list.get(i).getPath(), "require ");
            for (String str : listStr)
            {
                String require = str.substring(str.indexOf('‘') + 1, str.indexOf('’'));
                File ifExist = new File("src/main/resources/" + require);

                if (ifExist.exists())
                {
                    System.out.print("    require: " + require);/////
                    int j;
                    for (j = i; j < list.size(); j++)
                    {
                        if (list.get(j).getPath().equals("src/main/resources/" + require))
                        {
                            System.out.println(" - TO REPLACE");////
                            File f = list.get(j);
                            list.remove(list.get(j));
                            list.add(i, f);
                            //list.
                            break;
                        }
                    }

                    if (j == list.size())
                    {
                        System.out.println(" - OK ");////
                    }
                }
            }
        }//for end

        System.out.println("\n------------DONE----------");////

        for (File file : list)
        {
            System.out.println(file.getPath());////
        }


    }
}
