package com.example.daksh.mytwitter;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.twitter.sdk.android.core.OAuthSigning;
import com.twitter.sdk.android.core.TwitterAuthConfig;
import com.twitter.sdk.android.core.TwitterAuthToken;
import com.twitter.sdk.android.core.TwitterCore;
import com.twitter.sdk.android.core.TwitterSession;
import com.twitter.sdk.android.core.models.Tweet;
import com.twitter.sdk.android.tweetui.FixedTweetTimeline;
import com.twitter.sdk.android.tweetui.TweetTimelineListAdapter;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UsertimelineActivity extends Fragment {
    ListView usertimelineListView;
    List<Tweet> usertimelineTweets;
    SwipeRefreshLayout swipeRefreshLayout;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.usertimeline, container, false);
        swipeRefreshLayout=(SwipeRefreshLayout)v.findViewById(R.id.swipe_refresh_layout);
        swipeRefreshLayout.setColorSchemeColors(getResources().getColor(R.color.colorAccent),
                getResources().getColor(R.color.colorAccent),getResources().getColor(R.color.colorAccent));
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                swipeRefreshLayout.setRefreshing(true);
                showTweets();

            }
        });
        /*swipeRefreshLayout.post(new Runnable() {
            @Override
            public void run() {
          // swipeRefreshLayout.setRefreshing(true);
                showTweets();
            }
        });*/
        usertimelineTweets = new ArrayList<>();
        usertimelineListView = (ListView) v.findViewById(R.id.usertimelineListView);

        showTweets();

        return v;
    }

    private  void showTweets(){
        ApiInterface apiInterface=ApiClient.getApiInterface();
        Call<List<Tweet>> call = apiInterface.getUserTimelineTweets(50);

        call.enqueue(new Callback<List<Tweet>>() {
            @Override
            public void onResponse(Call<List<Tweet>> call, Response<List<Tweet>> response) {
                if (response.isSuccessful()) {
                    usertimelineTweets.addAll(response.body());
                    showHomeTimeline();
                } else {

                }
            }

            @Override
            public void onFailure(Call<List<Tweet>> call, Throwable t) {
            }
        });
    }

    private void showHomeTimeline() {

        FixedTweetTimeline timeline = new FixedTweetTimeline.Builder()
                .setTweets(usertimelineTweets)
                .build();

        TweetTimelineListAdapter adapter = new TweetTimelineListAdapter.Builder(getActivity())
                .setTimeline(timeline)
                .build();
        usertimelineListView.setAdapter(adapter);
        swipeRefreshLayout.setRefreshing(false);

    }



}
