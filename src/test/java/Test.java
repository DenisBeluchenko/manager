import org.junit.jupiter.api.Assertions;

public class Test {
    @org.junit.jupiter.api.Test
    public void testAnswer() {
        Category category = new Category(null);
        category.addingNewCategoryExistingCategories("еда,");
        Assertions.assertEquals(100, category.addingPurchaseAmountCategoryReturningTotalAmountPurchases("еда", 100));
    }
}