package com.example.sheryarkhan.projectcity.adapters;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.net.Uri;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.text.format.DateUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.example.sheryarkhan.projectcity.activities.CommentsActivity;
import com.example.sheryarkhan.projectcity.activities.PostNewsActivity;
import com.example.sheryarkhan.projectcity.Glide.GlideApp;
import com.example.sheryarkhan.projectcity.activities.ProfileActivity;
import com.example.sheryarkhan.projectcity.R;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.Collections;
import java.util.List;

import data.PostsPOJO;

/**
 * Created by Sheryar Khan on 8/1/2017.
 */

public class NewsFeedRecyclerAdapter extends RecyclerView.Adapter<NewsFeedRecyclerAdapter.MainViewHolder> {

    //private Activity activity;
    //private LayoutInflater inflater;
    private Context context;
    private Typeface ROBOTO_FONT_THIN;
    private Typeface ROBOTO_FONT_REGULAR;
    private Typeface ROBOTO_FONT_LIGHT;

    private ViewPagerAdapter viewPagerAdapter;
    private StorageReference storageReference;


    //private String[] images = {"loginbg","q1bg"};
    private List<String> images = Collections.emptyList();

    private static final int TYPE_SHARE_NEWS = 1;
    private static final int TYPE_NEWS_POST = 2;

    //private List<NewsFeedItemPOJO> newsFeedItems;

    public static List<PostsPOJO> newsFeedItemPOJOs = Collections.emptyList();

    private DatabaseReference databaseReference;

    public NewsFeedRecyclerAdapter(List<PostsPOJO> newsFeedItems)
    {
        //this.context = context;
        this.newsFeedItemPOJOs = newsFeedItems;
        storageReference = FirebaseStorage.getInstance().getReference();
        databaseReference = FirebaseDatabase.getInstance().getReference();

        //inflater = LayoutInflater.from(context);


    }

    @Override
    public int getItemCount() {
        try {
            return (newsFeedItemPOJOs.size() + 1); //Plus 1 for the Share news layout at the top
        }catch (Exception ex)
        {

        }
        return 0;
    }

    @Override
    public int getItemViewType(int position) {

        if(position == 0)
        {
            return TYPE_SHARE_NEWS;
        }
        else
        {
            return TYPE_NEWS_POST;
        }
    }



    @Override
    public MainViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        context = parent.getContext();
//        return new OnlyPostImageViewHolder(LayoutInflater.from(context).inflate(R.layout.news_feed_list_item, parent, false));

        switch (viewType)
        {
            case TYPE_NEWS_POST:
                    return new OnlyPostImageViewHolder(LayoutInflater.from(context).inflate(R.layout.news_feed_list_item, parent, false));

            case TYPE_SHARE_NEWS:
                return new ShareNewsPostViewHolder(LayoutInflater.from(context).inflate(R.layout.share_news_item_layout, parent, false));

        }
            return null;
//        View view = inflater.inflate(R.layout.news_feed_list_item,parent,false);
//        ViewHolder viewHolder = new ViewHolder(view);
//        return viewHolder;
    }


//    @Override
//    public int getItemViewType(int position) {
//        return super.getItemViewType(position);
//    }

    @Override
    public void onBindViewHolder(final MainViewHolder holder, final int position) {

        context = holder.itemView.getContext();
        //final Context context = holder.itemView.getContext();
        if(holder.getItemViewType() == TYPE_NEWS_POST){


//            notifyDataSetChanged();
            OnlyPostImageViewHolder mholder = (OnlyPostImageViewHolder) holder;
            setUpPictureView(context,mholder,position-1);
        }
        else if(holder.getItemViewType() == TYPE_SHARE_NEWS)
        {

            final ShareNewsPostViewHolder mholder = (ShareNewsPostViewHolder) holder;

            try {
                GlideApp.with(context)
                        .load(mholder.profilePicturePath)
                        .circleCrop()
                        .transition(DrawableTransitionOptions.withCrossFade(1000))
                        .error(R.color.link)
                        .into(mholder.imgProfilePic);
            } catch (Exception ex) {
                Log.d("error", ex.toString());
            }
            mholder.imgProfilePic.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context, ProfileActivity.class);
                    context.startActivity(intent);

                    //Toast.makeText(context,"KJAHDKJHASKJD",Toast.LENGTH_SHORT).show();
                }
            });
            mholder.shareNewsLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(context, PostNewsActivity.class);
                context.startActivity(intent);
                Toast.makeText(context,"Share news post",Toast.LENGTH_SHORT).show();
            }
        });

        }
//        else {
//            OnlyPostVideoViewHolder mHolder = (OnlyPostVideoViewHolder) holder;
//            PostsPOJO currentData = newsFeedItemPOJOs.get(position);
//            mHolder.Id = currentData.getUserID();
//
//            mHolder.txtName.setText(currentData.getUsername());
//
//            // Converting timestamp into X ago format
//            CharSequence timeAgo = DateUtils.getRelativeTimeSpanString(
//                    Long.parseLong(String.valueOf(currentData.getTimestamp())),
//                    System.currentTimeMillis(), DateUtils.SECOND_IN_MILLIS);
//            mHolder.txtTimeStamp.setText(timeAgo);
//
//            mHolder.txtUrl.setText("vimeo.com");
//
//            mHolder.txtStatusMsg.setText(currentData.getPostText());
//
//            mHolder.imgProfilePic.setImageDrawable(context.getDrawable(R.drawable.loginbg));
//
//
//            mHolder.imgPost.setImageDrawable(context.getDrawable(R.drawable.q1bg));
//
//            mHolder.txtUrl.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    Toast.makeText(context,"URL:"+position,Toast.LENGTH_SHORT).show();
//                }
//            });
//
//
//            mHolder.imgPost.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//
//                    Intent intent = new Intent(context, PostImageDisplayActivity.class);
//                    intent.putExtra("imageIndex", position);
//                    context.startActivity(intent);
//
//                }
//            });
//
//
//        }
//        //set height in proportion to screen size
//        int proportionalHeight = UIUtil.containerHeight((MainActivity) mCntx);
//        TableRow.LayoutParams params = new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, proportionalHeight); // (width, height)
//        holder.container.setLayoutParams(params);

    }
//    protected void postAndNotifyAdapter(final Handler handler, final RecyclerView recyclerView, final RecyclerView.Adapter adapter) {
//        handler.post(new Runnable() {
//            @Override
//            public void run() {
//                if (!recyclerView.isComputingLayout()) {
//                    adapter.notifyDataSetChanged();
//                } else {
//                    postAndNotifyAdapter(handler, recyclerView, adapter);
//                }
//            }
//        });
//    }

//    @Override
//    public void onViewRecycled(MainViewHolder holder) {
//        super.onViewRecycled(holder);
//        int position = holder.getAdapterPosition();
//
////        switch (getItemViewType(position))
////        {
////            case TYPE_NEWS_POST:
////                OnlyPostImageViewHolder mholder = (OnlyPostImageViewHolder) holder;
////                mholder.txtName.setText("");
////
////            case TYPE_SHARE_NEWS:
////
////        }
//        if(position > 0)
//        {
//            OnlyPostImageViewHolder mholder = (OnlyPostImageViewHolder) holder;
//            mholder.txtName.setText("");
//        }
//
//
//    }

//    @Override
//    public void onViewRecycled(MainViewHolder holder) {
//        super.onViewRecycled(holder);
//        OnlyPostImageViewHolder mholder = (OnlyPostImageViewHolder) holder;
//        mholder.txtName.setText("");
//    }

    private void setUpPictureView(final Context context, final OnlyPostImageViewHolder mholder, final int position) {


//        if(position == 0)
//        {
//            mholder.shareNewsLayout.setVisibility(View.VISIBLE);
//        }
//        else if(position > 0)
//        {
//            mholder.shareNewsLayout.setVisibility(View.GONE);
//        }


        PostsPOJO currentData = newsFeedItemPOJOs.get(position);




        if (currentData.getcontent_post() == null)
        {
            mholder.viewPager.setVisibility(View.GONE);
            mholder.tabLayout.setVisibility(View.GONE);
        }
        else if(currentData.getcontent_post().size() > 1)
        {
            viewPagerAdapter = new ViewPagerAdapter(context,currentData.getcontent_post());
            mholder.viewPager.setAdapter(viewPagerAdapter);
            mholder.viewPager.setVisibility(View.VISIBLE);
            mholder.tabLayout.setVisibility(View.VISIBLE);
            mholder.tabLayout.setupWithViewPager(mholder.viewPager, true);
        }
        else if(currentData.getcontent_post().size() == 1)
        {
            viewPagerAdapter = new ViewPagerAdapter(context,currentData.getcontent_post());
            mholder.viewPager.setAdapter(viewPagerAdapter);
            mholder.tabLayout.setVisibility(View.GONE);
            mholder.viewPager.setVisibility(View.VISIBLE);
        }

//        final DatabaseReference userDetails = databaseReference.child("Users/"+currentData.getUserID());
//        userDetails.addListenerForSingleValueEvent(new ValueEventListener() {
//
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot1) {
//
//                String username = dataSnapshot1.child("Username").getValue(String.class);
//                String profilePicturePath = dataSnapshot1.child("ProfilePicture").getValue(String.class);

                //mholder.txtName.setBackground(null);
                //mholder.txtName.getLayoutParams().width = ViewGroup.LayoutParams.WRAP_CONTENT;
                //mholder.txtName.setLayoutParams(new ConstraintLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT));
                mholder.txtName.setText(currentData.getusername());

                StorageReference filePath = storageReference.child("images").child(currentData.getprofilepicture());


                filePath.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri uri) {
                        try {
                            GlideApp.with(context)
                                    .load(uri)
                                    .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
                                    .circleCrop()
                                    .transition(DrawableTransitionOptions.withCrossFade(1000))
                                    .error(R.color.link)
                                    .into(mholder.imgProfilePic);
                        } catch (Exception ex) {
                            Log.d("error", ex.toString());
                        }
                    }
                });

                //Toast.makeText(getApplicationContext(),username.toString(),Toast.LENGTH_LONG).show();


                //list.add(new PostsPOJO(postsPOJO.getUserID(),profilePicturePath ,username, postsPOJO.getTimestamp(),postsPOJO.getPostText(),postsPOJO.getLocation(),postsPOJO.getcontent_post()));
                //Log.d("datalist2", postsPOJO.getUserID()+","+profilePicturePath +","+username+","+postsPOJO.getTimestamp()+","+postsPOJO.getPostText()+","+postsPOJO.getLocation()+","+postsPOJO.getcontent_post());



//                            userDetails.removeEventListener(mListener);
//            }
//            @Override
//            public void onCancelled(DatabaseError databaseError) {
//            }
//        });



        //mholder.viewPager.setPageTransformer(true, new Zoom);
        //viewPager.setAdapter(viewPagerAdapter);

        mholder.txtName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, CommentsActivity.class);
                context.startActivity(intent);
            }
        });

        // Converting timestamp into X ago format
        CharSequence timeAgo = DateUtils.getRelativeTimeSpanString(
                Long.parseLong(String.valueOf(currentData.gettimestamp())),
                System.currentTimeMillis(), DateUtils.SECOND_IN_MILLIS);

        mholder.txtTimeStamp.setText(timeAgo);
        mholder.txtLocation.setText(currentData.getlocation());

        mholder.txtSecondary.setText(currentData.getsecondarylocation());

        mholder.txtStatusMsg.setText(currentData.getposttext());



        mholder.imgProfilePic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, ProfileActivity.class);
                context.startActivity(intent);
            }
        });




        //mholder.imgPost.setImageDrawable(currentData.getImage());

//        mholder.txtUrl.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Toast.makeText(context,"URL:"+position,Toast.LENGTH_SHORT).show();
//            }
//        });


//        mholder.imgPost.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//                Intent intent = new Intent(context, PostImageDisplayActivity.class);
//                intent.putExtra("imageIndex", position);
//                context.startActivity(intent);
//
//            }
//        });

    }





    public class ShareNewsPostViewHolder extends MainViewHolder {

        TextView txtShareNews;
        ImageView imgProfilePic;
        ConstraintLayout shareNewsLayout;
        String profilePicturePath="";

        public ShareNewsPostViewHolder(View itemView) {
            super(itemView);

            txtShareNews = (TextView) itemView.findViewById(R.id.txtShareNews);
            shareNewsLayout = (ConstraintLayout)itemView.findViewById(R.id.shareNewsLayout);
            imgProfilePic = (ImageView)itemView.findViewById(R.id.imgProfilePic);
            txtShareNews.setTypeface(ROBOTO_FONT_LIGHT);
            SharedPreferences sharedPref = context.getSharedPreferences("UserData",Context.MODE_PRIVATE);
            profilePicturePath = sharedPref.getString("profilepicture", "");
        }


//        ImageView imgProfilePic;


    }


    public class OnlyPostImageViewHolder extends MainViewHolder {

        int Id;
        TextView txtName;
        TextView txtTimeStamp;
        TextView txtStatusMsg;
        TextView txtLikesAndComments,txtLocation,txtSecondary;
        //TextView txtUrl;
        ImageView imgProfilePic;
        //ImageView imgPost;
//        Button btnHelpful;
//        Button btnComments;
        ViewPager viewPager;
        TabLayout tabLayout;

        public OnlyPostImageViewHolder(View itemView) {

            super(itemView);

            //itemView.setOnClickListener(this);
            Id=0;
            viewPager = (ViewPager)itemView.findViewById(R.id.viewPager);
            tabLayout = (TabLayout)itemView.findViewById(R.id.pagerTabs);

            txtName = (TextView) itemView.findViewById(R.id.txtName);
            txtTimeStamp = (TextView) itemView.findViewById(R.id.txtTimeStampAndLocation);
            txtStatusMsg = (TextView) itemView.findViewById(R.id.txtStatusMsg);
            txtLikesAndComments = (TextView) itemView.findViewById(R.id.txtLikesAndComments);

            txtLocation = (TextView) itemView.findViewById(R.id.txtLocation);
            txtSecondary = (TextView) itemView.findViewById(R.id.txtSecondary);
            imgProfilePic = (ImageView) itemView.findViewById(R.id.imgProfilePic);



            txtName.setTypeface(ROBOTO_FONT_REGULAR);
            txtStatusMsg.setTypeface(ROBOTO_FONT_LIGHT);
            txtTimeStamp.setTypeface(ROBOTO_FONT_LIGHT);
            txtLikesAndComments.setTypeface(ROBOTO_FONT_LIGHT);
            txtLocation.setTypeface(ROBOTO_FONT_LIGHT);
//            btnHelpful = (Button) itemView.findViewById(R.id.btnHelpful);
//            btnComments = (Button) itemView.findViewById(R.id.btnComments);

        }


    }


    public class OnlyPostVideoViewHolder extends MainViewHolder {

        int Id;
        TextView txtName;
        TextView txtTimeStamp;
        TextView txtStatusMsg;
        TextView txtUrl;
        ImageView imgProfilePic;
        ImageView imgPost;


        public OnlyPostVideoViewHolder(View itemView) {

            super(itemView);

            //itemView.setOnClickListener(this);

            Id=0;
            txtName = (TextView) itemView.findViewById(R.id.txtName);
            txtTimeStamp = (TextView) itemView.findViewById(R.id.txtTimeStamp);
            txtStatusMsg = (TextView) itemView.findViewById(R.id.txtStatusMsg);
            txtUrl = (TextView) itemView.findViewById(R.id.txtUrl);
            imgPost = (ImageView) itemView.findViewById(R.id.imgPost);
            imgProfilePic = (ImageView) itemView.findViewById(R.id.imgProfilePic);




        }


    }

    public class MainViewHolder extends  RecyclerView.ViewHolder {
        public MainViewHolder(View v) {
            super(v);
            ROBOTO_FONT_THIN = Typeface.createFromAsset(context.getAssets(),"fonts/Roboto-Thin.ttf");
            ROBOTO_FONT_REGULAR = Typeface.createFromAsset(context.getAssets(),"fonts/Roboto-Regular.ttf");
            ROBOTO_FONT_LIGHT = Typeface.createFromAsset(context.getAssets(),"fonts/Roboto-Light.ttf");
        }
    }

//    private static class ViewHolder {
//
//        TextView txtName;
//        TextView txtTimeStamp;
//        TextView txtStatusMsg;
//        TextView txtUrl;
//        ImageView imgProfilePic;
//        ImageView imgPost;
//
//    }

//    class ListViewObject {
//
//        TextView txtName;
//        TextView txtTimeStamp;
//        TextView txtStatusMsg;
//        TextView txtUrl;
//        ImageView imgProfilePic;
//        ImageView imgPost;
//
//    }

//    @Override
//    public int getCount() {
//        return newsFeedItems.size();
//    }
//
//    @Override
//    public Object getItem(int location) {
//        return newsFeedItems.get(location);
//    }
//
//    @Override
//    public long getItemId(int position) {
//        return position;
//    }
//
//    @Override
//    public View getView(int position, View convertView, ViewGroup parent) {
//
//        ViewHolder viewHolder;
//        if(inflater == null)
//        {
//            inflater = (LayoutInflater)activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//        }
//        if(convertView == null)
//        {
//            convertView = inflater.inflate(R.layout.news_feed_list_item,null);
//        }
//
//        viewHolder = new ViewHolder();
//
//        viewHolder.txtName = (TextView)convertView.findViewById(R.id.txtName);
//        viewHolder.txtTimeStamp = (TextView)convertView.findViewById(R.id.txtTimeStamp);
//        viewHolder.txtStatusMsg = (TextView)convertView.findViewById(R.id.txtStatusMsg);
//        viewHolder.txtUrl = (TextView)convertView.findViewById(R.id.txtUrl);
//        viewHolder.imgProfilePic = (ImageView)convertView.findViewById(R.id.imgProfilePic);
//        viewHolder.imgPost = (ImageView)convertView.findViewById(R.id.imgPost);
////      TextView name = (TextView)convertView.findViewById(R.id.name);
//
//        NewsFeedItemPOJO item = newsFeedItems.get(position);
//
//
//        try {
//            viewHolder.txtName.setText(item.getName());
//
//            // Converting timestamp into X ago format
//            CharSequence timeAgo = DateUtils.getRelativeTimeSpanString(
//                    Long.parseLong(item.getTimeStamp()),
//                    System.currentTimeMillis(), DateUtils.SECOND_IN_MILLIS);
//            viewHolder.txtTimeStamp.setText(timeAgo);
//
//
//            if (!TextUtils.isEmpty(item.getStatus())) {
//                viewHolder.txtStatusMsg.setText(item.getStatus());
//            } else {
//                viewHolder.txtStatusMsg.setVisibility(View.GONE);
//            }
//
//            // Checking for null feed url
//            if (item.getUrl() != null) {
//                viewHolder.txtUrl.setText(Html.fromHtml("<a href=\"" + item.getUrl() + "\">"
//                        + item.getUrl() + "</a> "));
//
//                // Making url clickable
//                viewHolder.txtUrl.setMovementMethod(LinkMovementMethod.getInstance());
//                viewHolder.txtUrl.setVisibility(View.VISIBLE);
//            } else {
//                // url is null, remove from the view
//                viewHolder.txtUrl.setVisibility(View.GONE);
//            }
//
//            // user profile pic
//            viewHolder.imgProfilePic.setImageBitmap(item.getProfilePic());
//
//
//            // Feed image
//            if (item.getImage() != null) {
//                //feedImageView.setImageUrl(item.getImge(), imageLoader);
//                viewHolder.imgPost.setImageBitmap(item.getImage());
//                viewHolder.imgPost.setVisibility(View.VISIBLE);
////            feedImageView
////                    .setResponseObserver(new FeedImageView.ResponseObserver() {
////                        @Override
////                        public void onError() {
////                        }
////
////                        @Override
////                        public void onSuccess() {
////                        }
////                    });
//            } else {
//                viewHolder.imgPost.setVisibility(View.GONE);
//            }
//
//
//
////            viewHolder.txtName.setOnClickListener(new View.OnClickListener() {
////                @Override
////                public void onClick(View view) {
////
////
////
////                }
////            });
//
//
//        }catch (Exception ex)
//        {
//            Log.d("dada2",ex.toString());
//        }
//        return convertView;
//    }


}
