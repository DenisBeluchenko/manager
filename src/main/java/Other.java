import java.io.*;
import java.util.*;

public class Other {

    private Map<String, String> titles;

    public Other(Map<String, String> title) {
        this.titles = title;
    }

    public String creatingCategories() {
        String string = "";
        for (String key : titles.keySet()) {
            string += titles.get(key) + ",";
        }
        return string + "другое,";
    }

    public String determiningRelationshipProductCategory(String string) {
        if (titles.containsKey(string)) {
            return titles.get(string);
        } else {
            return "другое";
        }
    }

    public static Other loadingListProductsCategories() throws IOException {
        File file = new File("categories.tsv");
        Map<String, String> category = new HashMap<>();
        if (file.exists()) {
            try (InputStream inputStream = new FileInputStream(file)) {
                try {
                    Scanner scanner = new Scanner(inputStream);
                    for (int i = 0; i < 8; i++) {
                        category.put(scanner.next(), scanner.next());
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } else {
            System.out.println("Нет файла categories.tsv");
        }
        return new Other(category);
    }
}

