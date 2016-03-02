package song.michyo.accountbook.util;

/**
 * Created by songm3 on 3/2/16.
 */
public enum Category {
    NA("NA"),
    CAR("Car"),
    TRAVEL("Travel"),
    FOOD("Food & Drink"),
    FAMILY("Family & Personal"),
    BILLS("Bills"),
    ENTERTAINMENT("Entertainment"),
    HOME("Home"),
    UTILITIES("Utilities"),
    SHOPPING("Shopping");

    private final String category;

    Category(String category) {
        this.category = category;
    }

    public String getCategory() {
        return category;
    }
}
