package com.example.sameershekhar.hebecollins.activities.adapters;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.sameershekhar.hebecollins.R;
import com.example.sameershekhar.hebecollins.activities.models.UserAlbum;
import com.example.sameershekhar.hebecollins.activities.utils.Config;

import java.util.ArrayList;

/**
 * Created by sameershekhar on 17-Aug-17.
 */

public class UserImageGallaryAdaptor extends RecyclerView.Adapter<UserImageGallaryAdaptor.MyViewHolder> {
    ArrayList<UserAlbum> userAlbumArrayList=new ArrayList<>();
    Activity activity;

    public UserImageGallaryAdaptor(ArrayList<UserAlbum> userAlbumArrayList, Context context) {
        this.userAlbumArrayList = userAlbumArrayList;
        this.activity = (Activity) context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.user_photo_gallary_row,parent,false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {


        holder.imageDate.setText(userAlbumArrayList.get(position).getTime());
        String path= Config.img_path+userAlbumArrayList.get(position).getImage_name()+".jpg";

        Glide.with(activity).load(path).into(holder.userImage);
    }



    @Override
    public int getItemCount() {
        return userAlbumArrayList.size();
    }
    public static class MyViewHolder extends RecyclerView.ViewHolder {

        ImageView userImage;
        TextView imageDate;
        public MyViewHolder(View itemView) {
            super(itemView);
            userImage=(ImageView)itemView.findViewById(R.id.image_thumnail);
            imageDate=(TextView)itemView.findViewById(R.id.dateTime);

        }
    }

}
