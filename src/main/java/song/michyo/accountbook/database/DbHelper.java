package song.michyo.accountbook.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import song.michyo.accountbook.model.BillModel;

/**
 * Created by songm3 on 3/2/16.
 */
public class DbHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "bills";
    private static final int DB_VERSION = 1;

    public DbHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    public DbHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS bills (" +
                "id         INTEGER     primary key, " +
                "name       TEXT        NOT NULL, " +
                "category   TEXT        NOT NULL, " +
                "money      REAL        NOT NULL, " +
                "symbol     CHAR(0)     NOT NULL, " +
                "date       TEXT     NOT NULL" +
                ");");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS bills;");
        onCreate(db);
    }

    public void insertBill(BillModel billModel) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = billToContentValues(billModel);
        db.insert(DB_NAME, "name", contentValues);
    }

    private ContentValues billToContentValues(BillModel billModel) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", billModel.getName());
        contentValues.put("category", billModel.getCategory());
        contentValues.put("money", billModel.getMoney());
        contentValues.put("symbol", String.valueOf(billModel.getSymbol()));
        contentValues.put("date", billModel.getDate().toString());
        return contentValues;
    }
}
