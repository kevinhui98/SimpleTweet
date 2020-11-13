package com.codepath.apps.restclienttemplate.models;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.parceler.Parcel;

import java.util.ArrayList;
import java.util.List;

//added room notation
@Parcel
@Entity(foreignKeys = @ForeignKey(entity =  User.class, parentColumns = "id", childColumns = "userId"))
public class Tweet {
//added column info for the tweet and the user
    @ColumnInfo
    @PrimaryKey
    public long id;

    @ColumnInfo
    public String body;

    @ColumnInfo
    public String createdAt;

    @ColumnInfo
    public long userId;

    @Ignore
    public User user;

    // empty constructor needed by the Parceler library
    public  Tweet() {}

    //given a json object turn in to a java tweet object
    public static Tweet fromJson(JSONObject jsonObject) throws JSONException { //throw exception need if the string name doesn't exist
        Tweet tweet = new Tweet();
        tweet.body = jsonObject.getString("text");
        tweet.createdAt = jsonObject.getString("created_at");
        tweet.id = jsonObject.getLong("id");
        User user = User.fromJson(jsonObject.getJSONObject("user"));
        tweet.user = user; //create a static method take in json object and return user model
        tweet.userId = user.id;

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
