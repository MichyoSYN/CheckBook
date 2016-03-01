package song.michyo.accountbook.adaptor;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import song.michyo.accountbook.R;
import song.michyo.accountbook.model.ThumbnailListRowModel;

/**
 * Created by songm3 on 3/1/16.
 */
public class ThumbnailListAdaptor extends BaseAdapter {

    private static LayoutInflater inflater=null;
    private List<ThumbnailListRowModel> data;

    public ThumbnailListAdaptor(Context context, List<ThumbnailListRowModel> dataList) {
        inflater = LayoutInflater.from(context);
        this.data = dataList;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = new ViewHolder();
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.listview_thumbnails, null);
            viewHolder.imageView_thumbnail = (ImageView) convertView.findViewById(R.id.imageView_listview_thumbnail);
            viewHolder.textView_category = (TextView) convertView.findViewById(R.id.textView_listview_category);
            viewHolder.textView_date = (TextView) convertView.findViewById(R.id.textView_listview_date);
            viewHolder.textView_money = (TextView) convertView.findViewById(R.id.textView_listview_money);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        setData(viewHolder, data.get(position));
        return convertView;
    }

    private void setData(ViewHolder viewHolder, ThumbnailListRowModel rowData) {
        viewHolder.textView_category.setText(rowData.getCategory());
        viewHolder.textView_date.setText(rowData.getDate());
        viewHolder.textView_money.setText(String.valueOf(rowData.getMoney()));
    }

    private static class ViewHolder {
        ImageView imageView_thumbnail;
        TextView textView_category;
        TextView textView_date;
        TextView textView_money;
    }
}
