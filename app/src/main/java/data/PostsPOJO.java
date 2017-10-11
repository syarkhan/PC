package data;

import com.google.firebase.database.Exclude;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Sheryar Khan on 8/8/2017.
 */

public class PostsPOJO {


    private String userid;
    private String username;
    private Long timestamp;
    private String posttext;
    private String location;
    private String secondarylocation;
    private String profilepicture;
    private Map<String,Boolean> content_post;
    private Map<String,Boolean> likes = new HashMap<>();



    public String getusername() {
        return username;
    }

    public void setusername(String username) {
        this.username = username;
    }

    public String getprofilepicture() {
        return profilepicture;
    }

    public void setprofilepicture(String profilepicture) {
        this.profilepicture = profilepicture;
    }

    public String getuserid() {

        return userid;
    }

    public void setuserid(String userid) {
        this.userid = userid;
    }

    public Long gettimestamp() {
        return timestamp;
    }

    public void settimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }

    public String getposttext() {
        return posttext;
    }

    public void setposttext(String posttext) {
        this.posttext = posttext;
    }

    public String getlocation() {
        return location;
    }

    public void setlocation(String location) {
        this.location = location;
    }


    //WITH LIKES CONSTRUCTOR
    public PostsPOJO(String userid,String profilepicture, String username, Long timestamp,
                     String posttext, String location,String secondarylocation,
                     Map<String,Boolean> content_post,Map<String,Boolean> likes)
    {
        this.userid = userid;
        this.username = username;
        this.timestamp = timestamp;
        this.posttext = posttext;
        this.location = location;
        this.secondarylocation = secondarylocation;
        this.profilepicture = profilepicture;
        this.content_post = content_post;
        this.likes = likes;

    }


    //WITHOUT LIKES CONSTRUCTOR
    public PostsPOJO(String userid,String profilepicture, String username, Long timestamp,
                     String posttext, String location,String secondarylocation,
                     Map<String,Boolean> content_post)
    {
        this.userid = userid;
        this.username = username;
        this.timestamp = timestamp;
        this.posttext = posttext;
        this.location = location;
        this.secondarylocation = secondarylocation;
        this.profilepicture = profilepicture;
        this.content_post = content_post;
    }

    //WITHOUT MEDIA CONSTRUCTOR
    public PostsPOJO(String userid,String profilepicture, String username, Long timestamp,
                     String posttext, String location,String secondarylocation)
    {
        this.userid = userid;
        this.username = username;
        this.timestamp = timestamp;
        this.posttext = posttext;
        this.location = location;
        this.secondarylocation = secondarylocation;
        this.profilepicture = profilepicture;

    }



    public PostsPOJO()
    {

    }

    public Map<String,Boolean> getcontent_post() {
        return content_post;
    }

    public void setcontent_post(Map<String,Boolean> content_post) {
        this.content_post = content_post;
    }

    //@Exclude
    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("userid", userid);
        result.put("username", username);
        result.put("timestamp", timestamp);
        result.put("posttext", posttext);
        result.put("location", location);
        result.put("secondarylocation", secondarylocation);
        result.put("profilepicture", profilepicture);
        result.put("content_post",content_post);
        result.put("likes",likes);

        return result;
    }

    public String ToString()
    {

        return "[" + getuserid()+" ,"+ getprofilepicture() + " ,"+ getcontent_post().toString() + " ,"+ getlocation() + " ,"+ getposttext() +"]";

    }

    public String getsecondarylocation() {
        return secondarylocation;
    }

    public void setSecondarylocation(String secondarylocation) {
        this.secondarylocation = secondarylocation;
    }

    public Map<String, Boolean> getLikes() {
        return likes;
    }

    public void setLikes(Map<String, Boolean> likes) {
        this.likes = likes;
    }
}
