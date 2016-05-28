package ua.warko.yalantistask1.ui.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.facebook.drawee.view.SimpleDraweeView;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import ua.warko.yalantistask1.R;
import ua.warko.yalantistask1.pojo.Files;

/**
 * Created by Warko on 20.03.2016.
 */
public class PictureAdapter extends RecyclerView.Adapter<PictureAdapter.ViewHolder> {

    private List<Files> mPictureDataSet;
    private Context mContext;

    // Adds a ViewHolder
    public static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.imageView)
        SimpleDraweeView draweeView;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    // Applies PictureAdapter
    public PictureAdapter(Context context, List<Files> images) {
        mPictureDataSet = images;
        mContext = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent,
                                         int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycler_item, parent, false);
        return new ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(final ViewHolder holder, final int listPosition) {

        String fileName = mPictureDataSet.get(listPosition).getFilename();
        Picasso.with(mContext)
                .load(mContext.getString(R.string.images_path) + fileName)
                .fit()
                .into(holder.draweeView);
    }

    @Override
    public int getItemCount() {
        return mPictureDataSet.size();
    }
}
