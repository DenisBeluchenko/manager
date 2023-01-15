import java.io.*;
import java.util.*;

public class Other {

    private Map<String, String> titles;

    public Other(Map<String, String> title) {
        this.titles = title;
    }

    public String titleCategory() {
        String s = "";
        for (String i : titles.keySet()) {
            s += titles.get(i) + ",";
        }
        return s + "другое,";
    }

    public String title(String t) {
        if (titles.containsKey(t)) {
            return titles.get(t);
        } else {
            return "другое";
        }
    }

    public static Other loadTitle() throws IOException {
        File file = new File("categories.tsv");
        Map<String, String> t = new HashMap<>();
        if (file.exists()) {
            try (InputStream in = new FileInputStream(file)) {
                Scanner scanner = new Scanner(in);
                for (int i = 0; i < 8; i++) {
                    t.put(scanner.next(), scanner.next());
                }
            }
        } else {
            System.out.println("Нет файла categories.tsv");
        }
        return new Other(t);
    }
}

