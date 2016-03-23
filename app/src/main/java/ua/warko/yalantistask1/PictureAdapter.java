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
    public Context myContext; //[Comment] mContext
    // Adds a ViewHolder
    public static class MyViewHolder extends RecyclerView.ViewHolder { //[Comment] Just ViewHolder
        ImageView imagePicture; //[Comment] Wrong visibility modifier, wrong name. It's not a picture, it's imageView

        public MyViewHolder(View itemView) {
            super(itemView);
            this.imagePicture = (ImageView) itemView.findViewById(R.id.imageView);
        }
    }
    // Applies PictureAdapter
    public PictureAdapter(Context context, ArrayList<PictureData> images) {
        this.mPictureDataSet = images; //[Comment] without this
        myContext = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent,
                                           int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycler_item, parent, false);
        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder; //[Comment] return new ViewHolder(view)
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int listPosition) {

        ImageView imageViewPicture = holder.imagePicture;
        String src = mPictureDataSet.get(listPosition).getImage();
        Picasso.with(myContext)
                .load("file:///android_asset/images/" + src + ".jpg") //[Comment] hardcode
                .resize(200, 200) //[Comment] Magic numbers
                .into(imageViewPicture); //[Comment] holder.imagePicture
    }

    @Override
    public int getItemCount() {
        return mPictureDataSet.size();
    }
}
