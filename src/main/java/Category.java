import java.util.HashMap;
import java.util.Map;

public class Category {
    private Map<String, Integer> category;

    public void setCategory(Map<String, Integer> category) {
        this.category = category;
    }

    public Category(Map<String, Integer> category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return " " + category;
    }

    public void addCategory(String cat) {
        int i = 0;
        Map<String, Integer> t = new HashMap<>();
        String[] s = cat.split(",");
        for (String c : s) {
            t.put(c, 0);
        }
        setCategory(t);
    }

    public int answer(String t, int i) {
        category.put(t, category.get(t) + i);
        return category.get(t);
    }
}
