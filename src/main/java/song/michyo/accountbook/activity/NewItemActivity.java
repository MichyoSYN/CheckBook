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

/**
 * Created by songm3 on 2/29/16.
 */
public class NewItemActivity extends Activity{

    public static final String EXTRA_BILL = "bill";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_item);

        setButtonListener();
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

    private String getEditTextContent(int id) {
        EditText editText = (EditText) findViewById(id);
        return editText.getText().toString();
    }

    private BillModel getBill() {
        double money_amount = Double.parseDouble(getEditTextContent(R.id.editText_money_number));
        BillModel billModel = new BillModel(money_amount);
        return billModel;
    }

    private void insertBillToDb(BillModel billModel) {
        DbHelper dbHelper = new DbHelper(NewItemActivity.this);
        dbHelper.insertBill(billModel);
    }
}
