package com.example.daksh.mytwitter;

import com.twitter.sdk.android.core.models.Tweet;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Query;

/**
 * Created by Daksh Garg on 7/22/2017.
 */

public interface ApiInterface {

    @GET("statuses/home_timeline.json")
    Call<List<Tweet>> getUserTimelineTweets(@Query("count") int count);

    @GET("trends/place.json")
    Call<ArrayList<Trend>> getTrends(@Query("id") int id);

    @GET("friends/list.json")
    Call<Friends> getFriends(@Query("cursor") String cursor, @Query("screen_name") String screen_name,
                             @Query("skip_status") boolean skip_status, @Query("include_user_entities") boolean include_user_entities);

    @GET("followers/list.json")
    Call<Followers> getFollowers(@Query("cursor") int cursor, @Query("screen_name") String screen_name,
                                 @Query("skip_status") boolean skip_status, @Query("include_user_entities") boolean include_user_entities);


}
