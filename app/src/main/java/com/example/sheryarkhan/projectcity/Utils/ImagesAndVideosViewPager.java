package com.example.sheryarkhan.projectcity.Utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.example.sheryarkhan.projectcity.Glide.GlideApp;
import com.example.sheryarkhan.projectcity.R;
import com.example.sheryarkhan.projectcity.activities.PostImageDisplayActivity;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static com.example.sheryarkhan.projectcity.R.id.imageView;

/**
 * Created by Sheryar Khan on 8/27/2017.
 */


//android.support.v7.widget.AppCompatImageView

public class ImagesAndVideosViewPager extends android.support.v7.widget.AppCompatImageView{


    Context context;
    Map<String, Boolean> images;
    int position;
    Activity activity;
    ImagesAndVideosViewPager imagesAndVideosViewPager;
    View view;
    private StorageReference storageReference;


//    public ImagesAndVideosViewPager()
//    {
//
//    }

    public ImagesAndVideosViewPager(Context context) {
        super(context);
        this.context = context;
    }

    public ImagesAndVideosViewPager(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public ImagesAndVideosViewPager(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
    }



    public void Init(Context context, Map<String, Boolean> images, int position, View view) {


        this.context = context;
        this.images = images;
        this.position = position;
        activity = (Activity) context;
        this.view = view;
        imagesAndVideosViewPager = (ImagesAndVideosViewPager)findViewById(R.id.imagesAndVideosViewPager);
        imagesAndVideosViewPager.setTransitionName("image");
        storageReference = FirebaseStorage.getInstance().getReference();
        Log.d("imagesdada",images.toString()+" "+position);
        setUpImage();
        //imagesPager = (ImagesAndVideosViewPager) view.findViewById(R.id.imagesAndVideosViewPager);
        //imagesPager = new Images(context);
        //videosPager = new Videos(context);
//        if(position == 3)
//        {
//            videosPager.setUpVideo();
//        }
//        imagesPager.setUpImage();
        //LinearLayout linearLayout = (LinearLayout)view.findViewById(R.id.linearLayout);
        //imagesPager = (Images) view.findViewById(R.id.imagesAndVideosViewPager);
//            videosPager=  (Videos) view.findViewById(R.id.imagesAndVideosViewPager);

    }

    public void setUpImage() {


        //imageView = view.findViewById(R.id.imagesAndVideosViewPager);
        //imageView = new ImageView(context);

        imagesAndVideosViewPager.setVisibility(VISIBLE);
//        DisplayMetrics displayMetrics = new DisplayMetrics();
//        activity.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
//        int height = displayMetrics.heightPixels;
//        int width = displayMetrics.widthPixels;
//
//        imagesAndVideosViewPager.setMinimumHeight(height);
//        imagesAndVideosViewPager.setMinimumWidth(width);

//        final RequestOptions options = new RequestOptions();
//        options.diskCacheStrategy(DiskCacheStrategy.RESOURCE);


        StorageReference filePath = storageReference.child("images").child(new ArrayList<>(images.keySet()).get(position));
        filePath.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                try {
                    GlideApp.with(context)
                            .load(uri)
                            .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
                            .centerCrop()
                            .error(R.color.link)
                            .transition(DrawableTransitionOptions.withCrossFade(1000))
                            .into(imagesAndVideosViewPager);
                } catch (Exception ex) {
                    Log.d("error", ex.toString());
                }
            }
        });


        imagesAndVideosViewPager.setClickable(true);
        imagesAndVideosViewPager.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {

//                ActivityOptionsCompat optionsCompat = ActivityOptionsCompat
//                        .makeSceneTransitionAnimation(context,imagesAndVideosViewPager,"myImage");
//
//                context.startActivity(intent, optionsCompat.toBundle());

                String url = new ArrayList<>(images.keySet()).get(position);
                Intent intent = new Intent(context, PostImageDisplayActivity.class);
                intent.putExtra("url",url);
                ActivityOptionsCompat options = ActivityOptionsCompat.
                        makeSceneTransitionAnimation((Activity) context,
                                imagesAndVideosViewPager,
                                "image");
                context.startActivity(intent, options.toBundle());

//                String url = new ArrayList<>(images.keySet()).get(position);
//                Toast.makeText(context,"Hi : "+new ArrayList<>(images.keySet()).get(position),Toast.LENGTH_SHORT).show();
//                Intent intent = new Intent(context, PostImageDisplayActivity.class);
//                intent.putExtra("url",url);
//                context.startActivity(intent);


            }
        });

//        try {
//            Glide.with(context)
//                    .load(getImage(images.get(position)))
//                    .into(imagesAndVideosViewPager);
//        } catch (Exception ex) {
//            Log.d("error", ex.toString());
//        }



    }
    public int getImage(String imageName) {

        int drawableResourceId = context.getResources().getIdentifier(imageName, "drawable", context.getPackageName());

        return drawableResourceId;
    }

//    public void Init(Context context, String[] images, int position, View view) {
//        //imagesAndVideosViewPager = (ImagesAndVideosViewPager) findViewById(R.id.imagesAndVideosViewPager);
//        //video1 = (videoPager) findViewById(R.id.imagesAndVideosViewPager);
//        this.context = context;
//        this.images = images;
//        this.position = position;
//        activity = (Activity) context;
//        this.view = view;
//        imagesPager = (Images) findViewById(R.id.imagesAndVideosViewPager);
//        videosPager =  (Videos) findViewById(R.id.imagesAndVideosViewPager);
//
//        //LinearLayout linearLayout = (LinearLayout)view.findViewById(R.id.linearLayout);
//
//        if (position == 3) {
//
//            Videos video = new Videos(context);
//            video.setUpVideo();
//            videosPager.start();
//            //setUpVideo();
//        }
//        Images images1 = new Images(context);
//        images1.setUpImage();
//        //setUpImage();
//        //String abc = "1";
//    }


//    public class Images extends ImageView{
//
////        Context context;
////        String[] images;
////        int position;
////        Activity activity;
////        View view;
////        Images imagesPager;
////
////        private View imageView;
//
//
//
//        public Images(Context context) {
//            super(context);
////            this.context = context;
////            this.images = images;
////            this.position = position;
////            activity = (Activity) context;
////            this.view = view;
////            //LinearLayout linearLayout = (LinearLayout)view.findViewById(R.id.linearLayout);
////            imagesPager = (Images) view.findViewById(R.id.imagesAndVideosViewPager);
////            videosPager=  (Videos) view.findViewById(R.id.imagesAndVideosViewPager);
//
//        }
//        public void Init() {
////            imagesAndVideosViewPager = (ImagesAndVideosViewPager) findViewById(R.id.imagesAndVideosViewPager);
////            video1 = (videoPager) findViewById(R.id.imagesAndVideosViewPager);
////            this.context = context;
////            this.images = images;
////            this.position = position;
////            activity = (Activity) context;
////            this.view = view;
//            //LinearLayout linearLayout = (LinearLayout)view.findViewById(R.id.linearLayout);
//
//            if (position == 3) {
//
////                videoPager video = new videoPager(context);
////                video.setUpVideo();
//                //video1.start();
//                //setUpVideo();
//            }
//            setUpImage();
//            //String abc = "1";
//        }
//        public void setUpImage() {
//
//
//            //imageView = view.findViewById(R.id.imagesAndVideosViewPager);
//            //imageView = new ImageView(context);
//
//            DisplayMetrics displayMetrics = new DisplayMetrics();
//            activity.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
//            int height = displayMetrics.heightPixels;
//            int width = displayMetrics.widthPixels;
//
//            imagesAndVideosViewPager.setMinimumHeight(height);
//            imagesAndVideosViewPager.setMinimumWidth(width);
//
//
//            try {
//                Glide.with(context)
//                        .load(getImage(images[position]))
//                        .into(imagesAndVideosViewPager);
//            } catch (Exception ex) {
//                Log.d("error", ex.toString());
//            }
//
//
//        }
//
//        public Images(Context context, @Nullable AttributeSet attrs) {
//            super(context, attrs);
//        }
//
//        public Images(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
//            super(context, attrs, defStyleAttr);
//        }
//
//
////        public void Init(Context context) {
////            //imagesAndVideosViewPager = (ImagesAndVideosViewPager) findViewById(R.id.imagesAndVideosViewPager);
////            //video1 = (videoPager) findViewById(R.id.imagesAndVideosViewPager);
//////            this.context = context;
//////            this.images = images;
//////            this.position = position;
//////            activity = (Activity) context;
//////            this.view = view;
////            //LinearLayout linearLayout = (LinearLayout)view.findViewById(R.id.linearLayout);
////
////            if (position == 3) {
////
//////                videoPager video = new videoPager(context);
//////                video.setUpVideo();
////                //video1.start();
////                //setUpVideo();
////            }
////            setUpImage();
////            //String abc = "1";
////        }
//
//
//
//
//
//
//
//    }
//
//    public class Videos extends VideoView {
//
//        //video video1;
//        //Videos videosPager;
//        public Videos(Context context) {
//            super(context);
//            //video1 = (video)findViewById(R.id.imagesAndVideosViewPager);
//        }
//
//        public Videos(Context context, AttributeSet attrs) {
//            super(context, attrs);
//        }
//
//        public Videos(Context context, AttributeSet attrs, int defStyleAttr) {
//            super(context, attrs, defStyleAttr);
//        }
//
//        public void setUpVideo() {
//
//            //video1 = (video)findViewById(R.id.imagesAndVideosViewPager);
//            //imageView = view.findViewById(R.id.imagesAndVideosViewPager);
//            //imageView = new ImageView(context);
//
//            String videoPath = "android.resource://com.example.sheryarkhan/" + R.raw.video1;
//            Uri uri = Uri.parse(videoPath);
//
//            DisplayMetrics displayMetrics = new DisplayMetrics();
//            //activity.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
//            int height = displayMetrics.heightPixels;
//            int width = displayMetrics.widthPixels;
//
//            videosPager.setMinimumHeight(height);
//            videosPager.setMinimumWidth(width);
//            videosPager.setVideoURI(uri);
//
//
//
//        }
//
//    }

}





