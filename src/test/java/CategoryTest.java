import junit.framework.TestCase;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import java.util.HashMap;
import java.util.Map;



public class CategoryTest extends TestCase {
        Category category;

@Test
    public void answerTest(){
    Map<String, Integer> t = new HashMap<>();
    t.put("rrr",0);
    category=new Category(t);
int res=category.answer("rrr",100);
Assertions.assertEquals(100,res);

}
}