package ua.warko.yalantistask1.ui.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.realm.OrderedRealmCollection;
import io.realm.RealmRecyclerViewAdapter;
import ua.warko.yalantistask1.R;
import ua.warko.yalantistask1.pojo.ContentDataModel;


/**
 * Created by Warko on 18.05.2016.
 */
public class RealmContentAdapter extends RealmRecyclerViewAdapter<ContentDataModel, RealmContentAdapter.ViewHolder> {
    private Context mContext;


    public static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.card_icon)
        ImageView cardIcon;
        @BindView(R.id.like_counter)
        TextView likeCounter;
        @BindView(R.id.card_header_text)
        TextView cardHeader;
        @BindView(R.id.card_address_text)
        TextView cardAddress;
        @BindView(R.id.card_date_text)
        TextView cardDate;
        @BindView(R.id.card_days_text)
        TextView cardDays;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public RealmContentAdapter(Context context, OrderedRealmCollection<ContentDataModel> data) {
        super(context, data, true);
        mContext = context;

    }


    @Override
    public RealmContentAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RealmContentAdapter.ViewHolder holder, int position) {
        ContentDataModel model = getData().get(position);
        holder.likeCounter.setText(String.valueOf(model.getLikesCounter()));
        holder.cardHeader.setText(model.getTitle());
        holder.cardIcon.setImageResource(R.mipmap.ic_doc);
        if (model.getGeoAddress() != null) {
            holder.cardAddress.setText(model.getGeoAddress().getAddress());
        } else {
            holder.cardAddress.setText("");
        }
        if (model.getStartDate() == 0) {
            holder.cardDate.setText("");
        } else {
            holder.cardDate.setText(model.getNormalDate());
        }
        holder.cardDays.setText(model.getDays());

    }

}
