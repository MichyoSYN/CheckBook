package song.michyo.accountbook.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import song.michyo.accountbook.util.Category;

/**
 * Created by songm3 on 3/2/16.
 */

public class BillModel implements Parcelable {
    private static final String DEFAULT_NAME = "default_user";
    private static final String DEFAULT_CATEGORY = Category.NA.getCategory();
    private static final char DEFAULT_SYMBOL = '-';
    private static final DateFormat dateFormat =new SimpleDateFormat("yyyyMMdd");

    private String name;
    private String category;
    private double money;
    private char symbol;
    private Date date;

    public BillModel(String name, String category, double money, char symbol, Date date) {
        this.name = name;
        this.category = category;
        this.money = money;
        this.symbol = symbol;
        this.date = date;
    }

    public BillModel(String name, String category, double money, char symbol, String date) throws ParseException {
        this(name, category, money, symbol, convertStringToDate(date));
    }

    public BillModel(double money) {
        this(DEFAULT_NAME, DEFAULT_CATEGORY, money, DEFAULT_SYMBOL, new Date());
    }

    protected BillModel(Parcel in) throws ParseException {
        name = in.readString();
        category = in.readString();
        money = in.readDouble();
        symbol = in.readString().charAt(0);
        date = convertStringToDate(in.readString());
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(category);
        dest.writeDouble(money);
        dest.writeString(String.valueOf(symbol));
        dest.writeString(convertDateToString(date));
    }

    public static final Creator<BillModel> CREATOR = new Creator<BillModel>() {
        @Override
        public BillModel createFromParcel(Parcel in) {
            try {
                return new BillModel(in);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            return new BillModel(0);
        }

        @Override
        public BillModel[] newArray(int size) {
            return new BillModel[size];
        }
    };

    public String getName() {
        return name;
    }

    public BillModel setName(String name) {
        this.name = name;
        return this;
    }

    public String getCategory() {
        return category;
    }

    public BillModel setCategory(String category) {
        this.category = category;
        return this;
    }

    public double getMoney() {
        return money;
    }

    public BillModel setMoney(double money) {
        this.money = money;
        return this;
    }

    public char getSymbol() {
        return symbol;
    }

    public BillModel setSymbol(char symbol) {
        this.symbol = symbol;
        return this;
    }

    public Date getDate() {
        return date;
    }

    public BillModel setDate(Date date) {
        this.date = date;
        return this;
    }

    public BillModel setDate(String date) throws ParseException {
        this.date = convertStringToDate(date);
        return this;
    }

    public static Date convertStringToDate(String date) throws ParseException {
        return dateFormat.parse(date);
    }

    public static String convertDateToString(Date date) {
        return dateFormat.format(date);
    }
}
