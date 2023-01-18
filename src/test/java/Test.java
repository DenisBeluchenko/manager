
import org.junit.jupiter.api.Assertions;

public class Test {
    @org.junit.jupiter.api.Test
    public void testAnswer() {
Category category=new Category(null);
category.addCategory("еда,");
Assertions.assertEquals(100,category.answer("еда", 100));

    }

}