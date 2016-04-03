package ua.warko.yalantistask1;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by Warko on 20.03.2016.
 */
public class PictureAdapter extends RecyclerView.Adapter<PictureAdapter.ViewHolder> {

    private ArrayList mPictureDataSet;
    private Context mContext;

    // Adds a ViewHolder
    public static class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView imageView;

        public ViewHolder(View itemView) {
            super(itemView);
            this.imageView = (ImageView) itemView.findViewById(R.id.imageView);
        }
    }

    // Applies PictureAdapter
    public PictureAdapter(Context context, ArrayList images) {
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

        String src = mPictureDataSet.get(listPosition).toString();
        Picasso.with(mContext)
                .load(mContext.getString(R.string.asset_path) + src + ".jpg")
                .fit()
                .into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return mPictureDataSet.size();
    }
}
