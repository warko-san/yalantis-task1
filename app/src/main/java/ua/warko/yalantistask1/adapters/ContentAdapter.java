package ua.warko.yalantistask1.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import ua.warko.yalantistask1.R;
import ua.warko.yalantistask1.models.ContentDataModel;

/**
 * Created by Warko on 13.04.2016.
 */
public class ContentAdapter extends RecyclerView.Adapter<ContentAdapter.ViewHolder> {
    private List<ContentDataModel> mDataSet;
    private Context mContext;


    public static class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView cardIcon;
        private TextView likeCounter, cardHeader, cardAddress, cardDate, cardDays;

        public ViewHolder(View itemView) {
            super(itemView);
            this.cardIcon = (ImageView) itemView.findViewById(R.id.card_icon);
            this.likeCounter = (TextView) itemView.findViewById(R.id.like_counter);
            this.cardHeader = (TextView) itemView.findViewById(R.id.card_header_text);
            this.cardAddress = (TextView) itemView.findViewById(R.id.card_address_text);
            this.cardDate = (TextView) itemView.findViewById(R.id.card_date_text);
            this.cardDays = (TextView) itemView.findViewById(R.id.card_days_text);
        }

    }

    public ContentAdapter(Context context, List<ContentDataModel> data) {
        mDataSet = data;
        mContext = context;
    }

    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
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


    }


    @Override
    public int getItemCount() {
        return mDataSet.size();
    }
}
