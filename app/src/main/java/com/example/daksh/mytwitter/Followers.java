package com.example.daksh.mytwitter;

import java.util.ArrayList;

/**
 * Created by Daksh Garg on 7/26/2017.
 */

public class Followers {
    String previous_cursor_str;
    String next_cursor_str;


    public ArrayList<FollowersObject> users;

    public static class FollowersObject{
        public String name;
    }
}
