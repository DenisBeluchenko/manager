import java.util.HashMap;
import java.util.Map;

public class Category {
    private Map<String, Integer> category;

    public Category(Map<String, Integer> category) {
        this.category = category;
    }

    public void setCategory(Map<String, Integer> category) {
        this.category = category;
    }

    public void addCategory(String categorys) {
        Map<String, Integer> newCategory = new HashMap<>();
        String[] split = categorys.split(",");
        for (String s : split) {
            newCategory.put(s, 0);
        }
        setCategory(newCategory);
    }

    public int answer(String categorys, int amount) {
        category.put(categorys, category.get(categorys) + amount);
        return category.get(categorys);
    }
}
