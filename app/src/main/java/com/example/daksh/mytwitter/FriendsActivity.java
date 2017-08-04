package com.example.daksh.mytwitter;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.AbsListView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.twitter.sdk.android.core.OAuthSigning;
import com.twitter.sdk.android.core.TwitterAuthConfig;
import com.twitter.sdk.android.core.TwitterAuthToken;
import com.twitter.sdk.android.core.TwitterCore;
import com.twitter.sdk.android.core.TwitterSession;

import java.util.ArrayList;
import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class FriendsActivity extends AppCompatActivity {
    ListView friendListView;
    FriendsAdapter adapter;
    ArrayList<Friends.FriendObject> friendArrayList;
    TwitterSession session;
    String next_cursor;
    boolean flag=true;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.friends);
        next_cursor = "-1";
        session = TwitterCore.getInstance().getSessionManager().getActiveSession();
        friendListView = (ListView) findViewById(R.id.friendsListView);
        friendArrayList = new ArrayList<>();
        adapter = new FriendsAdapter(this, friendArrayList);
        friendListView.setAdapter(adapter);

        friendListView.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView absListView, int i) {

            }

            @Override
            public void onScroll(AbsListView absListView, int i, int i1, int i2) {
                Log.d("check",i2+"");
           // Log.d("check",friendArrayList.size()+"size");
                if (i + i1 == i2 && friendArrayList.size()!=0 && flag == true) {
                    if (!next_cursor.equals("0")){
                        flag=false;
                       loadMoreTweets();
                       // flag=true;
                        Log.d("check",i + i1+"");
                }

                }

            }
        });
        ApiInterface apiInterface = ApiClient.getApiInterface();
        Call<Friends> call = apiInterface.getFriends("-1", session.getUserName(), true, false);
        call.enqueue(new Callback<Friends>() {
            @Override
            public void onResponse(Call<Friends> call, Response<Friends> response) {

                if (response.isSuccessful()) {

                    next_cursor = response.body().next_cursor_str;
                    ArrayList<Friends.FriendObject> friendList = response.body().users;
                    friendArrayList.addAll(friendList);
                    /*for (int i = 0; i < friendList.size(); i++) {
                        Friends.FriendObject f = new Friends.FriendObject(friendList.get(i).name, friendList.get(i).screen_name,
                                friendList.get(i).profile_image_url);
                        friendArrayList.add(f);
                    }*/
                    adapter.notifyDataSetChanged();
                } else {
                }
            }

            @Override
            public void onFailure(Call<Friends> call, Throwable t) {

            }
        });

    }

    private void loadMoreTweets() {
        ApiInterface apiInterface = ApiClient.getApiInterface();
        Call<Friends> call = apiInterface.getFriends(next_cursor, session.getUserName(), true, false);
        call.enqueue(new Callback<Friends>() {
            @Override
            public void onResponse(Call<Friends> call, Response<Friends> response) {

                if (response.isSuccessful()) {
                    next_cursor = response.body().next_cursor_str;
                    ArrayList<Friends.FriendObject> friendList = response.body().users;
                   // friendArrayList.addAll(friendList);
                    for (int i = 0; i < friendList.size(); i++) {
                       // Log.d("check",friendList.get(i).name);
                        Friends.FriendObject f = new Friends.FriendObject(friendList.get(i).name, friendList.get(i).screen_name,
                                friendList.get(i).profile_image_url);
                        friendArrayList.add(f);
                    }
                    adapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<Friends> call, Throwable t) {

            }
        });
    }


}
