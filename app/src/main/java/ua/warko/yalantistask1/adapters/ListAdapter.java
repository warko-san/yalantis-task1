package ua.warko.yalantistask1.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import ua.warko.yalantistask1.R;
import ua.warko.yalantistask1.models.ContentDataModel;

/**
 * Created by Warko on 14.04.2016.
 */
public class ListAdapter extends ArrayAdapter<ContentDataModel> {
    private List<ContentDataModel> mDataSet;
    private Context mContext;

    public static class ViewHolder {
        private ImageView cardIcon;
        private TextView likeCounter, cardHeader, cardAddress, cardDate, cardDays;

    }

    public ListAdapter(Context context, List<ContentDataModel> content) {
        super(context, 0, content);
        mContext = context;
        mDataSet = content;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
            convertView = layoutInflater.inflate(R.layout.card_item, parent, false);
            holder = new ViewHolder();
            holder.likeCounter = (TextView) convertView.findViewById(R.id.like_counter);
            holder.cardHeader = (TextView) convertView.findViewById(R.id.card_header_text);
            holder.cardIcon = (ImageView) convertView.findViewById(R.id.card_icon);
            holder.cardAddress = (TextView) convertView.findViewById(R.id.card_address_text);
            holder.cardDate = (TextView) convertView.findViewById(R.id.card_date_text);
            holder.cardDays = (TextView) convertView.findViewById(R.id.card_days_text);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.likeCounter.setText(mDataSet.get(position).getCounter());
        holder.cardHeader.setText(mDataSet.get(position).getHeader());
        if (mDataSet.get(position).getHeader().equals(mContext.getString(R.string.nature_header))) {
            holder.cardIcon.setImageResource(R.drawable.nature);
        } else if (mDataSet.get(position).getHeader().equals(mContext.getString(R.string.destroy_header))) {
            holder.cardIcon.setImageResource(R.mipmap.ic_city);
        } else if (mDataSet.get(position).getHeader().equals(mContext.getString(R.string.investigation_header))) {
            holder.cardIcon.setImageResource(R.drawable.search);
        } else {
            holder.cardIcon.setImageResource(R.drawable.business);
        }
        holder.cardAddress.setText(mDataSet.get(position).getAddress());
        holder.cardDate.setText(mDataSet.get(position).getCreationDate());
        holder.cardDays.setText(mDataSet.get(position).getDays());
        return convertView;
    }
}
