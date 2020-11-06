package com.codepath.apps.restclienttemplate.models;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Tweet {
    public String body;
    public String createdAt;
    public long id;
    public User user;

    //given a json object turn in to a java tweet object
    public static Tweet fromJson(JSONObject jsonObject) throws JSONException { //throw exception need if the string name doesn't exist
        Tweet tweet = new Tweet();
        tweet.body = jsonObject.getString("text");
        tweet.createdAt = jsonObject.getString("created_at");
        tweet.id = jsonObject.getLong("id");
        tweet.user = User.fromJson(jsonObject.getJSONObject("user")); //create a static method take in json object and return user model

        return tweet;
    }
    //pass in a whole json array and in return get a list of tweets
    public static List<Tweet> fromJsonArray(JSONArray jsonArray) throws JSONException {
        List<Tweet> tweets = new ArrayList<>();
        //for loop through the json array and for each element of the array
        //calling the json method we just define to add to the list of tweet objects
        for(int i = 0; i < jsonArray.length();i++){
            tweets.add(fromJson(jsonArray.getJSONObject(i)));
        }
        return tweets;
    }
}
