package com.example.sheryarkhan.projectcity.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.sheryarkhan.projectcity.R;

/**
 * Created by Sheryar Khan on 9/7/2017.
 */

public class CommentsListAdapter extends RecyclerView.Adapter<CommentsListAdapter.CommentsHolder> {



    public CommentsListAdapter(){

    }

    @Override
    public CommentsHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        return new CommentsHolder(LayoutInflater.from(context).inflate(R.layout.comment_list_item, parent, false));
    }



    @Override
    public void onBindViewHolder(CommentsHolder holder, int position) {

        Context context = holder.itemView.getContext();
        CommentsListAdapter.CommentsHolder mholder = (CommentsListAdapter.CommentsHolder) holder;
        SetUpCommentsView(context,mholder,position);

    }

    private void SetUpCommentsView(Context context, CommentsHolder mholder, int position) {

        mholder.txtName.setText("Faizan Khan");
        mholder.txtTimestamp.setText("12d ago");
        //mholder.txtLikes.setText("126 Likes");
        mholder.txtComment.setText("Lorem ipsum dolor sit amet, et eos illum democritum, no luptatum persequeris per. Ne eos amet homero convenire, eu eius labores eam. Ne postulant suavitate eloquentiam eum, te natum soluta nec. Nonumy saperet ea has. Harum facete definitionem ne pri");

    }


    @Override
    public int getItemCount() {
        return 10;
    }

    class CommentsHolder extends RecyclerView.ViewHolder{

        TextView txtName;
        TextView txtTimestamp;
        TextView txtComment;
        //TextView txtLikes;
        ImageView imgProfilePic;

        public CommentsHolder(View itemView) {
            super(itemView);

            txtName = (TextView) itemView.findViewById(R.id.txtName);
            txtTimestamp = (TextView) itemView.findViewById(R.id.txtTimestamp);
            txtComment = (TextView) itemView.findViewById(R.id.txtComment);
//            txtLikes = (TextView) itemView.findViewById(R.id.txtLikes);
            imgProfilePic = (ImageView) itemView.findViewById(R.id.imgProfilePic);


        }
    }



}


