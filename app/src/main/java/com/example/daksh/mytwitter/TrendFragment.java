package com.example.daksh.mytwitter;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.twitter.sdk.android.core.OAuthSigning;
import com.twitter.sdk.android.core.TwitterAuthConfig;
import com.twitter.sdk.android.core.TwitterAuthToken;
import com.twitter.sdk.android.core.TwitterCore;

import java.util.ArrayList;
import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class TrendFragment extends Fragment {
    ListView trendListView;
    ArrayAdapter<String> adapter;
    ArrayList<String> trendArrayList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.trendfragment, container, false);
        trendListView = (ListView) v.findViewById(R.id.trendListView);
        trendArrayList = new ArrayList<>();
        adapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_list_item_1, trendArrayList);
        trendListView.setAdapter(adapter);

        ApiInterface apiInterface=ApiClient.getApiInterface();
        Call<ArrayList<Trend>> call = apiInterface.getTrends(23424848);
        call.enqueue(new Callback<ArrayList<Trend>>() {
            @Override
            public void onResponse(Call<ArrayList<Trend>> call, Response<ArrayList<Trend>> response) {
                if (response.isSuccessful()) {
                    for(int i=0;i<response.body().get(0).trends.size() ;i++){
                        trendArrayList.add(response.body().get(0).trends.get(i).name);
                    }
                    adapter.notifyDataSetChanged();

                }
            }

            @Override
            public void onFailure(Call<ArrayList<Trend>> call, Throwable t) {

            }
        });

        return v;
    }



}
