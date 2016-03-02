package song.michyo.accountbook.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

import song.michyo.accountbook.R;
import song.michyo.accountbook.database.DbHelper;
import song.michyo.accountbook.model.BillModel;
import song.michyo.accountbook.util.Category;

/**
 * Created by songm3 on 2/29/16.
 */
public class NewItemActivity extends Activity{

    private CategoryButtonListener categoryButtonListener = new CategoryButtonListener();
    public static final String EXTRA_BILL = "bill";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_item);

        setButtonListener();
        setCategoryButtonListeners();
    }

    private void setButtonListener() {
        Button button_cancel = (Button) findViewById(R.id.button_cancel);
        button_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NewItemActivity.this.finish();
            }
        });

        Button button_next_step = (Button) findViewById(R.id.button_next_step);
        button_next_step.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                BillModel billModel = getBill();
                insertBillToDb(billModel);
                Toast.makeText(NewItemActivity.this, "A new bill item is created.", Toast.LENGTH_SHORT).show();

                Intent intent = new Intent().setClass(NewItemActivity.this, MainActivity.class);
                intent.putExtra(EXTRA_BILL, billModel);
                NewItemActivity.this.setResult(RESULT_OK, intent);
                NewItemActivity.this.finish();
            }
        });
    }

    private void setCategoryButtonListeners() {
        setOneCategoryButton(categoryButtonListener, R.id.button_table_r1l1, 1);
        setOneCategoryButton(categoryButtonListener, R.id.button_table_r1l2, 2);
        setOneCategoryButton(categoryButtonListener, R.id.button_table_r1l3, 3);
        setOneCategoryButton(categoryButtonListener, R.id.button_table_r2l1, 4);
        setOneCategoryButton(categoryButtonListener, R.id.button_table_r2l2, 5);
        setOneCategoryButton(categoryButtonListener, R.id.button_table_r2l3, 6);
        setOneCategoryButton(categoryButtonListener, R.id.button_table_r3l1, 7);
        setOneCategoryButton(categoryButtonListener, R.id.button_table_r3l2, 8);
        setOneCategoryButton(categoryButtonListener, R.id.button_table_r3l3, 9);
    }

    private void setOneCategoryButton(CategoryButtonListener listener, int id, int tag) {
        Button button_car = (Button) findViewById(id);
        button_car.setTag(tag);
        button_car.setOnClickListener(listener);
    }

    private String getEditTextContent(int id) {
        EditText editText = (EditText) findViewById(id);
        return editText.getText().toString();
    }

    private BillModel getBill() {
        double money_amount = Double.parseDouble(getEditTextContent(R.id.editText_money_number));
        String category = categoryButtonListener.getCategory();

        BillModel billModel = new BillModel(money_amount).setCategory(category);
        return billModel;
    }

    private void insertBillToDb(BillModel billModel) {
        DbHelper dbHelper = new DbHelper(NewItemActivity.this);
        dbHelper.insertBill(billModel);
    }

    private class CategoryButtonListener implements View.OnClickListener {
        private String category = Category.NA.getCategory();

        @Override
        public void onClick(View v) {
            int tag = (int) v.getTag();
            switch (tag) {
                case 1:
                    category = Category.CAR.getCategory();
                    break;
                case 2:
                    category = Category.TRAVEL.getCategory();
                    break;
                case 3:
                    category = Category.FOOD.getCategory();
                    break;
                case 4:
                    category = Category.FAMILY.getCategory();
                    break;
                case 5:
                    category = Category.BILLS.getCategory();
                    break;
                case 6:
                    category = Category.ENTERTAINMENT.getCategory();
                    break;
                case 7:
                    category = Category.HOME.getCategory();
                    break;
                case 8:
                    category = Category.UTILITIES.getCategory();
                    break;
                case 9:
                    category = Category.SHOPPING.getCategory();
                    break;
                default:
                    category = Category.NA.getCategory();
                    break;
            }
        }

        public String getCategory() {
            return category;
        }
    }
}
