package com.example.daksh.mytwitter;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by Daksh Garg on 7/26/2017.
 */

public class FriendsAdapter extends ArrayAdapter<Friends.FriendObject> {

    ArrayList<Friends.FriendObject> friendsArrayList;
    Context context;

    public FriendsAdapter(@NonNull Context context,ArrayList<Friends.FriendObject> friendsArrayList) {
        super(context, 0);
        this.friendsArrayList=friendsArrayList;
        this.context=context;
    }

    @Override
    public int getCount() {
        return friendsArrayList.size();
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if(convertView==null)
        {
            convertView = LayoutInflater.from(context).inflate(R.layout.friendsadapter, null);
            TextView nameTextView = (TextView) convertView.findViewById(R.id.nameTextView);
            TextView screenNameTextView = (TextView) convertView.findViewById(R.id.screen_nameTextView);
            CircleImageView circleImageView=(CircleImageView)convertView.findViewById(R.id.friendImageView);
            FriendViewHolder friendViewHolder = new FriendViewHolder(nameTextView ,screenNameTextView,circleImageView);
            convertView.setTag(friendViewHolder);

        }
        FriendViewHolder friendViewHolder = (FriendViewHolder) convertView.getTag();
        Friends.FriendObject f =friendsArrayList.get(position);
        friendViewHolder.nameTextView.setText(f.name);
        friendViewHolder.screenNameTextView.setText(f.screen_name);
        String url=f.profile_image_url;
        Picasso.with(context).load(url).into(friendViewHolder.circleImageView);

        return convertView;
    }

    static class FriendViewHolder {
        TextView nameTextView;
        TextView screenNameTextView;
        CircleImageView circleImageView;

        public FriendViewHolder(TextView nameTextView, TextView screenNameTextView, CircleImageView circleImageView) {
            this.nameTextView = nameTextView;
            this.screenNameTextView = screenNameTextView;
            this.circleImageView = circleImageView;

        }
    }


}
