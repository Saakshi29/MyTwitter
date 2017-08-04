package com.example.daksh.mytwitter;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

public class FollowersActivity extends AppCompatActivity {
    ListView followerListView;
    ArrayAdapter<String> adapter;
    ArrayList<String> followerArrayList;
    TwitterSession session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.followers);
        session = TwitterCore.getInstance().getSessionManager().getActiveSession();
        followerListView = (ListView) findViewById(R.id.followersListView);
        followerArrayList = new ArrayList<>();
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, followerArrayList);
        followerListView.setAdapter(adapter);

       ApiInterface apiInterface=ApiClient.getApiInterface();
        Call<Followers> call = apiInterface.getFollowers(-1,session.getUserName(),true,false);
        call.enqueue(new Callback<Followers>() {
            @Override
            public void onResponse(Call<Followers> call, Response<Followers> response) {
                if(response.isSuccessful()) {
                    ArrayList<Followers.FollowersObject> followerList = response.body().users;
                    for (int i = 0; i < followerList.size(); i++) {
                        followerArrayList.add(followerList.get(i).name);
                    }
                    adapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<Followers> call, Throwable t) {

            }
        });
    }


}
