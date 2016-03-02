package song.michyo.accountbook.activity;

import android.app.ListActivity;
import android.content.Intent;
import android.provider.ContactsContract;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import java.util.ArrayList;
import java.util.List;

import song.michyo.accountbook.R;
import song.michyo.accountbook.activity.NewItemActivity;
import song.michyo.accountbook.adaptor.ThumbnailListAdaptor;
import song.michyo.accountbook.model.BillModel;
import song.michyo.accountbook.model.ThumbnailListRowModel;

public class MainActivity extends ListActivity {
    List<ThumbnailListRowModel> tempData = null;
    ThumbnailListAdaptor listAdaptor = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tempData = tempDataPrepare();
        listAdaptor = new ThumbnailListAdaptor(this, tempData);
        setListAdapter(listAdaptor);

        setButtonListener();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (RESULT_OK == resultCode && 1 == requestCode) {
            BillModel billModel = data.getParcelableExtra(NewItemActivity.EXTRA_BILL);
            ThumbnailListRowModel rowModel = convertBillModelToRowModel(billModel);
            addOneRow(rowModel);
        }
    }

    private ThumbnailListRowModel convertBillModelToRowModel(BillModel billModel) {
        return new ThumbnailListRowModel(
                billModel.getCategory(),
                BillModel.convertDateToString(billModel.getDate()),
                billModel.getMoney());
    }

    private static List<ThumbnailListRowModel> tempDataPrepare() {
        ThumbnailListRowModel rowModel1 = new ThumbnailListRowModel("Car", "today", 50);
        ThumbnailListRowModel rowModel2 = new ThumbnailListRowModel("Family & Person", "today", 40);
        List<ThumbnailListRowModel> list = new ArrayList<>();
        list.add(rowModel1);
        list.add(rowModel2);
        return list;
    }

    private void setButtonListener() {
        Button button_add_new = (Button) findViewById(R.id.button_add);
        button_add_new.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent().setClass(MainActivity.this, NewItemActivity.class);
                MainActivity.this.startActivityForResult(intent, 1);
            }
        });
    }

    private void addOneRow(ThumbnailListRowModel rowModel) {
        tempData.add(rowModel);
        listAdaptor.notifyDataSetChanged();
    }
}
