package song.michyo.accountbook.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import song.michyo.accountbook.R;

/**
 * Created by songm3 on 2/29/16.
 */
public class NewItemActivity extends Activity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_item);

        setButtonListener();
    }

    private String getEditTextContent(int id) {
        EditText editText = (EditText) findViewById(id);
        return editText.getText().toString();
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
                Intent intent = new Intent().setClass(NewItemActivity.this, MainActivity.class);
                intent.putExtra("money_amount", getEditTextContent(R.id.editText_money_number));
                NewItemActivity.this.setResult(RESULT_OK, intent);
                NewItemActivity.this.finish();
            }
        });
    }
}
