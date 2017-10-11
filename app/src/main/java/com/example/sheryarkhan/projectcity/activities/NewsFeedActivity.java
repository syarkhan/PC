package com.example.sheryarkhan.projectcity.activities;

import android.content.Context;
import android.os.Vibrator;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sheryarkhan.projectcity.R;
import com.example.sheryarkhan.projectcity.Utils.BottomNavigationViewHelper;
import com.example.sheryarkhan.projectcity.Utils.Constants;
import com.example.sheryarkhan.projectcity.adapters.NewsFeedRecyclerAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import com.google.firebase.storage.StorageReference;
import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;

import data.NewsFeedItemPOJO;
import data.PostsPOJO;

public class NewsFeedActivity extends AppCompatActivity {


    private SwipeRefreshLayout swipeRefreshLayout;
    private RecyclerView newsFeedRecyclerView;
    private NewsFeedRecyclerAdapter newsFeedRecyclerAdapter;
    private List<NewsFeedItemPOJO> feedItemsList;
    private FirebaseStorage firebaseStorage;
    private DatabaseReference databaseReference;
    private FirebaseAuth firebaseAuth;
    private StorageReference storageReference;
    private List<String> images = Collections.emptyList();
    private ChildEventListener mChildListener;
    private ProgressBar recyclerViewProgressBar;
    private TextView txtNetworkError;

    private LinearLayoutManager linearLayoutManager;


    private ArrayList<PostsPOJO> list;

    boolean isFirstTime = true;
    int counter = 1;
    DatabaseReference query;
//    ArrayList<NewsFeedItemPOJO> list3;
//    private ViewPager viewPager;
//    private ViewPagerAdapter viewPagerAdapter;


//    private String[] images = {"btnbackground","camera","q1bg"};

//    private String[] locations = {"Clifton Block 2","Clifton Block 6","Shahrah e Faisal","Clifton Block 5",
//    "Clifton Block 3","Clifton Block 9","Clifton Block 4","Clifton Block 7"
//    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_feed);
        Log.d("onCreate","onCreate "+counter++);


        List<Map<String, Object>> TOWN = Constants.TOWN_DETAILS();
        Log.d("dadaTowns",TOWN.toString());

        setupBottomNavigationView();

        recyclerViewProgressBar = (ProgressBar)findViewById(R.id.recyclerViewProgressBar);
        txtNetworkError = (TextView)findViewById(R.id.txtNetworkError);

        databaseReference = FirebaseDatabase.getInstance().getReference();
        storageReference = FirebaseStorage.getInstance().getReference();
        final Vibrator vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);

//
//        Query user = databaseReference.orderByChild("")
//        for(int i=0;i<locations.length;i++)
//        {
//            databaseReference.child("Posts").child(String.valueOf(i+3)).child("Location").setValue(locations[i]);
//            databaseReference.child("Posts").child(String.valueOf(i+3)).child("PostText").setValue("Lorem ipsum dolor sit amet");
//            databaseReference.child("Posts").child(String.valueOf(i+3)).child("Timestamp").setValue(1502179255);
//            databaseReference.child("Posts").child(String.valueOf(i+3)).child("UserID").setValue(3);
//        }

        list = new ArrayList<>();

        swipeRefreshLayout = (SwipeRefreshLayout)findViewById(R.id.swipeRefreshLayout);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {



                if(vibrator.hasVibrator())
                {
                    vibrator.vibrate(30);
                }
                //newsFeedRecyclerAdapter.notifyDataSetChanged();
                recyclerViewProgressBar.setVisibility(View.GONE);
                txtNetworkError.setVisibility(View.GONE);
                newsFeedRecyclerView.setVisibility(View.VISIBLE);
                Toast.makeText(getBaseContext(),"Refreshed",Toast.LENGTH_SHORT).show();
                swipeRefreshLayout.setRefreshing(false);
            }
        });

        newsFeedRecyclerView = (RecyclerView) findViewById(R.id.news_feed_recyclerview);
        //feedItemsList = new ArrayList<>();

        //newsFeedRecyclerAdapter = new NewsFeedRecyclerAdapter(this,generateDummyData(list));
        //newsFeedRecyclerView.setAdapter(newsFeedRecyclerAdapter);



        newsFeedRecyclerView.setHasFixedSize(true);
        newsFeedRecyclerView.setItemViewCacheSize(20);
        newsFeedRecyclerView.setDrawingCacheEnabled(true);
        newsFeedRecyclerView.setDrawingCacheQuality(View.DRAWING_CACHE_QUALITY_HIGH);
        linearLayoutManager = new LinearLayoutManager(this);
        newsFeedRecyclerView.setLayoutManager(linearLayoutManager);
        newsFeedRecyclerAdapter = new NewsFeedRecyclerAdapter(list);
        newsFeedRecyclerView.setAdapter(newsFeedRecyclerAdapter);
        newsFeedRecyclerAdapter.notifyItemChanged(0);





        query = databaseReference.child("posts");

//        DatabaseReference connectedRef = FirebaseDatabase.getInstance().getReference(".info/connected");
//        connectedRef.addListenerForSingleValueEvent(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot snapshot) {
//                boolean connected = snapshot.getValue(Boolean.class);
//                if (connected) {
//                    System.out.println("connected");
//
//                } else {
//                    System.out.println("not connected");
//                    Toast.makeText(NewsFeedActivity.this,"No Internet Connection!",Toast.LENGTH_SHORT).show();
//                    recyclerViewProgressBar.setVisibility(View.GONE);
//                    txtNetworkError.setVisibility(View.VISIBLE);
//                }
//            }
//
//            @Override
//            public void onCancelled(DatabaseError error) {
//                System.err.println("Listener was cancelled");
//            }
//        });



//        query.addListenerForSingleValueEvent(new ValueEventListener() {
//
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//
//
//                for(DataSnapshot item : dataSnapshot.getChildren()) {
//                    final PostsPOJO postsPOJO = item.getValue(PostsPOJO.class);
//                    //images = postsPOJO.getcontent_post();
//
//                    list.add(new PostsPOJO(postsPOJO.getuserid(),
//                            postsPOJO.getprofilepicture() ,postsPOJO.getusername(),
//                            postsPOJO.gettimestamp(),postsPOJO.getposttext(),
//                            postsPOJO.getlocation(),postsPOJO.getsecondarylocation()
//                            ,postsPOJO.getcontent_post(),postsPOJO.getLikes()));
//                    Log.d("datalist",list.toString());
//
//                }
//                    Collections.reverse(list);
//                    recyclerViewProgressBar.setVisibility(View.GONE);
//                    newsFeedRecyclerView.setVisibility(View.VISIBLE);
//                    newsFeedRecyclerAdapter.notifyDataSetChanged();
//                    isFirstTime = false;//asdasd
//
//
//            }
//
//            @Override
//            public void onCancelled(DatabaseError databaseError) {
//            }
//        });




    mChildListener = new ChildEventListener() {
        @Override
        public void onChildAdded(DataSnapshot dataSnapshot, String s) {
            if (!isFirstTime) {
                final PostsPOJO postsPOJO = dataSnapshot.getValue(PostsPOJO.class);
                //images = postsPOJO.getcontent_post();

                list.add(0, new PostsPOJO(postsPOJO.getuserid(),
                        postsPOJO.getprofilepicture(), postsPOJO.getusername(),
                        postsPOJO.gettimestamp(), postsPOJO.getposttext(),
                        postsPOJO.getlocation(), postsPOJO.getsecondarylocation()
                        , postsPOJO.getcontent_post(), postsPOJO.getLikes()));

                newsFeedRecyclerAdapter.notifyItemInserted(1);
                newsFeedRecyclerAdapter.notifyItemRangeChanged(1, list.size());
            }

        }

        @Override
        public void onChildChanged(DataSnapshot dataSnapshot, String s) {

        }

        @Override
        public void onChildRemoved(DataSnapshot dataSnapshot) {


        }

        @Override
        public void onChildMoved(DataSnapshot dataSnapshot, String s) {

        }

        @Override
        public void onCancelled(DatabaseError databaseError) {

        }
    };

    //query.addChildEventListener(mChildListener);






//query.addChildEventListener(new ChildEventListener() {
//    @Override
//    public void onChildAdded(DataSnapshot dataSnapshot, String s) {
//
//        final PostsPOJO postsPOJO = dataSnapshot.getValue(PostsPOJO.class);
//        //images = postsPOJO.getcontent_post();
//
//        list.add(new PostsPOJO(postsPOJO.getuserid(),
//                postsPOJO.getprofilepicture() ,postsPOJO.getusername(),
//                postsPOJO.gettimestamp(),postsPOJO.getposttext(),
//                postsPOJO.getlocation(),postsPOJO.getsecondarylocation()
//                ,postsPOJO.getcontent_post(),postsPOJO.getLikes()));
//
//        newsFeedRecyclerAdapter.notifyItemInserted(0);
//
//    }
//
//    @Override
//    public void onChildChanged(DataSnapshot dataSnapshot, String s) {
//
//    }
//
//    @Override
//    public void onChildRemoved(DataSnapshot dataSnapshot) {
//
////        final PostsPOJO postsPOJO = dataSnapshot.getValue(PostsPOJO.class);
////        list.remove(postsPOJO);
////        newsFeedRecyclerAdapter.notifyItemRemoved();
//
//    }
//
//    @Override
//    public void onChildMoved(DataSnapshot dataSnapshot, String s) {
//
//    }
//
//    @Override
//    public void onCancelled(DatabaseError databaseError) {
//
//    }
//});


//                query.addChildEventListener(new ChildEventListener() {
//                    @Override
//                    public void onChildAdded(DataSnapshot dataSnapshot, String s) {
//
//
//                        final PostsPOJO postsPOJO = dataSnapshot.getValue(PostsPOJO.class);
//
//                        list.add(postsPOJO);
//
////                Query userDetails = databaseReference.child("Users/"+postsPOJO.getUserID());
////                String u = String.valueOf(userDetails);
////                userDetails.addValueEventListener(new ValueEventListener() {
////                    @Override
////                    public void onDataChange(DataSnapshot dataSnapshot) {
////
////                        String username = dataSnapshot.child("Username").getValue(String.class);
////
////                        list.add(new PostsPOJO(postsPOJO.getUserID(), username, postsPOJO.getTimestamp(),postsPOJO.getPostText(),postsPOJO.getLocation()));
////
////                    }
////
////                    @Override
////                    public void onCancelled(DatabaseError databaseError) {
////
////                    }
////                });
//
//                        //PostsPOJO newsFeedItemPOJO = dataSnapshot.getValue(PostsPOJO.class);
//                        //list.add(newsFeedItemPOJO);
//
//                        Log.d("dadalist", list.toString());
//
//                        newsFeedRecyclerAdapter.notifyDataSetChanged();
////                newsFeedRecyclerAdapter = new NewsFeedRecyclerAdapter(NewsFeedActivity.this,generateDummyData(newsFeedItemPOJO));
////                newsFeedRecyclerView.setAdapter(newsFeedRecyclerAdapter);
//                        //newsFeedRecyclerAdapter.notifyDataSetChanged();
//
//
//                    }
//
//                    @Override
//                    public void onChildChanged(DataSnapshot dataSnapshot, String s) {
//
//                    }
//
//                    @Override
//                    public void onChildRemoved(DataSnapshot dataSnapshot) {
//
//                    }
//
//                    @Override
//                    public void onChildMoved(DataSnapshot dataSnapshot, String s) {
//
//                    }
//
//                    @Override
//                    public void onCancelled(DatabaseError databaseError) {
//
//                    }
//                });


        //newsFeedRecyclerAdapter.notifyDataSetChanged();
//        viewPager = (ViewPager)findViewById(R.id.viewPager);
//        viewPagerAdapter = new ViewPagerAdapter(this,images);
//        viewPager.setAdapter(viewPagerAdapter);


        //newsFeedListView = (ListView)findViewById(R.id.news_feed_listView);


//        getActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#3b5998")));
//        getActionBar().setIcon(new ColorDrawable(getResources().getColor(android.R.color.transparent)));


//        Bitmap profilePic = BitmapFactory.decodeResource(getResources(), R.drawable.loginbg);
//        Bitmap postPic = BitmapFactory.decodeResource(getResources(), R.drawable.discoverbtn);

//        for(int i=0; i < 12;i++)
//        {
//
//            NewsFeedItemPOJO item = new NewsFeedItemPOJO();
//            item.setName("Sheryar Khan");
//            item.setStatus("Hello how are you? I am fine. What about you?");
//            item.setId(i);
//            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//                item.setImage(getDrawable(R.drawable.camera));
//            }
//            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//                item.setProfilePic(getDrawable(R.drawable.confirm));
//            }
//            //item.setProfilePic(profilePic);
//            item.setTimeStamp("1501583198");
//            item.setUrl("www.google.com");
//
//            feedItemsList.add(item);
//
//
//        }

        //newsFeedRecyclerAdapter.notifyDataSetChanged();


//        newsFeedListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
//
//                String gg = adapterView.getAdapter().getItem(position).toString();
//
//                Toast.makeText(getBaseContext(),gg,Toast.LENGTH_SHORT).show();
//
//            }
//        });


    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        //newsFeedRecyclerView.smoothScrollToPosition(0);
    }

    @Override
    protected void onPause() {
        super.onPause();
//        if (linearLayoutManager.findFirstCompletelyVisibleItemPosition() == 0) {
//            //this is the top of the RecyclerView
//
//
//        }

        Log.d("onPause","onPause "+counter++);


    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("onResume","onResume "+counter++);

    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d("onStart","onStart "+counter++);

    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d("onStop","onStop "+counter++);
        //query.removeEventListener(mChildListener);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("onDestroy","onDestroy "+counter++);
    }

    //    @Override
//    public void onConfigurationChanged(Configuration newConfig) {
//        super.onConfigurationChanged(newConfig);
//        VideosViewPager questionVideo = new VideosViewPager(NewsFeedActivity.this);
//        if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
//            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN);
//            getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
//
//            questionVideo.setDimensions(1920, 1080);
//            questionVideo.getHolder().setFixedSize(1920, 1080);
//
//        } else {
//            getWindow().setFlags(WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN, WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN);
//
//            questionVideo.setDimensions(1920, 1080);
//            questionVideo.getHolder().setFixedSize(1920, 1080);
//
//        }
//    }

//    private ArrayList<NewsFeedItemPOJO> generateDummyData(ArrayList<PostsPOJO> list)
//    {
//        ArrayList<NewsFeedItemPOJO> list2 = new ArrayList<>();
//
//        for(int i=0; i < list.size();i++)
//        {
//
////list.get(i).getUserID();
////            Query query = databaseReference.child("UserID");
////            query.addListenerForSingleValueEvent(new ValueEventListener() {
////                @Override
////                public void onDataChange(DataSnapshot dataSnapshot) {
////
////                }
////
////                @Override
////                public void onCancelled(DatabaseError databaseError) {
////
////                }
////            });
//            NewsFeedItemPOJO item = new NewsFeedItemPOJO();
//
//            item.setName(list.get(i).getLocation());
//            item.setStatus(list.get(i).getPostText());
//            item.setId(i);
//            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//                item.setImage(getDrawable(R.drawable.q1bg));
//            }
//            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//                item.setProfilePic(getDrawable(R.drawable.confirm));
//            }
//            //item.setProfilePic(profilePic);
//            item.setTimeStamp(list.get(i).getTimestamp().toString());
//            item.setUrl("www.google.com");
//
////            if(i == 1 || i == 3 || i == 5 || i == 7 || i == 9) {
////                item.setName("Sheryar Khan");
////                item.setStatus("Hello how are you? I am fine. What about you?");
////                item.setId(i);
////                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
////                    item.setImage(getDrawable(R.drawable.q1bg));
////                }
////                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
////                    item.setProfilePic(getDrawable(R.drawable.confirm));
////                }
////                //item.setProfilePic(profilePic);
////                item.setTimeStamp("1401723773793");
////                item.setUrl("www.google.com");
////            }
////            else{
////                item.setName("Dawood Khan");
////                item.setStatus("Bad news!! Today I saw a very horrible accident near Bakery.");
////                item.setId(i);
////                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
////                    item.setImage(getDrawable(R.drawable.discoverbtn));
////                }
////                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
////                    item.setProfilePic(getDrawable(R.drawable.camera));
////                }
////                //item.setProfilePic(profilePic);
////                item.setTimeStamp("1401723873793");
////                item.setUrl("www.youtube.com");
////            }
//
//
//            list2.add(item);
//
//
//        }
//
//        return list2;
//    }

    private void setupBottomNavigationView()
    {
        BottomNavigationViewEx bottomNavigationViewEx = (BottomNavigationViewEx)findViewById(R.id.bottomNavViewBar);
        BottomNavigationViewHelper.setupBottomNavigationView(bottomNavigationViewEx);
    }
}
