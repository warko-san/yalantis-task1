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
public class PictureAdapter extends RecyclerView.Adapter<PictureAdapter.MyViewHolder> {

    private ArrayList<PictureData> mPictureDataSet;
    public Context myContext;
    // Adds a ViewHolder
    public static class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView imagePicture;

        public MyViewHolder(View itemView) {
            super(itemView);
            this.imagePicture = (ImageView) itemView.findViewById(R.id.imageView);
        }
    }
    // Applies PictureAdapter
    public PictureAdapter(Context context, ArrayList<PictureData> images) {
        this.mPictureDataSet = images;
        myContext = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent,
                                           int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycler_item, parent, false);
        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int listPosition) {

        ImageView imageViewPicture = holder.imagePicture;
        String src = mPictureDataSet.get(listPosition).getImage();
        Picasso.with(myContext)
                .load("file:///android_asset/images/" + src + ".jpg")
                .resize(200, 200)
                .into(imageViewPicture);
    }

    @Override
    public int getItemCount() {
        return mPictureDataSet.size();
    }
}
