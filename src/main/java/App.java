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

        ArrayList<File> list = new ArrayList<>();

        for (final File fileEntry : folder.listFiles()) {
            if (fileEntry.isDirectory()) {
                listFilesForFolder(fileEntry);
            } else {
                list.add(fileEntry);
            }
        }

        list.sort(Comparator.comparing(File::getName).reversed());


        for (int i = 0; i < list.size(); ++i) {
            File file = list.get(i);
            System.out.println(file.getPath());
            List<String> listStr = readFileByFilter(file.getPath(), "require ");
            for (String str : listStr)
            {
                String require = str.substring(str.indexOf('‘') + 1, str.indexOf('’'));
                File ifExist = new File("src/main/resources/" + require);
                if (ifExist.exists())
                {
                    System.out.println(require);/////
                    //File fileTmp = file.
                    for (int j = i; j < list.size(); ++j)
                    {
                        if (list.get(j).getPath().equals("src/main/resources/" + require))
                        {
                            System.out.println("YES");
                            break;
                        }

                        if (j + 1 == list.size())
                        {
                            System.out.println("NO!");
                        }
                    }

                }

            }
        }
    }


    public static void main(String[] args) throws IOException {

        final File folder = new File("src/main/resources");
        listFilesForFolder(folder);

    }

}
