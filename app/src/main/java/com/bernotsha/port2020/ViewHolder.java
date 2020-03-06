package com.bernotsha.port2020;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class ViewHolder extends RecyclerView.ViewHolder {
    View mview;

    public ViewHolder(@NonNull View itemView) {
        super(itemView);
        mview=itemView;
    }
    public void setDetails(Context ctx, String title,String desc,String image)
    {
        TextView mtitle = mview.findViewById(R.id.post_title);
        TextView mdesc = mview.findViewById(R.id.post_des);
        ImageView mimage = mview.findViewById(R.id.post_image);
        mtitle.setText(title);
        mdesc.setText(desc);
        Picasso.get().load(image).into(mimage);
    }

}