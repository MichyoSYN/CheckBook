package song.michyo.accountbook.model;

/**
 * Created by songm3 on 3/1/16.
 */
public class ThumbnailListRowModel {
    private String category;
    private String date;
    private double money;

    public ThumbnailListRowModel(String category, String date, double money) {
        this.category = category;
        this.date = date;
        this.money = money;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setMoney(double money) {
        this.money = money;
    }

    public String getCategory() {
        return category;
    }

    public String getDate() {
        return date;
    }

    public double getMoney() {
        return money;
    }
}
