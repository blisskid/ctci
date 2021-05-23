package com.blisskid.leetcode;
import java.util.*;

//["Twitter","postTweet","postTweet","postTweet","postTweet","postTweet","postTweet","postTweet","postTweet","postTweet","postTweet","postTweet","getNewsFeed"]
//[[],[1,5],[1,3],[1,101],[1,13],[1,10],[1,2],[1,94],[1,505],[1,333],[1,22],[1,11],[1]]

class Twitter {
    public static void main(String[] args) {
        Twitter t=new Twitter();
        t.postTweet(1,5);
        t.postTweet(1,3);
        t.postTweet(1,101);
        t.postTweet(1,13);
        t.postTweet(1,10);
        t.postTweet(1,2);
        t.postTweet(1,94);
        t.postTweet(1,505);
        t.postTweet(1,333);
        t.postTweet(1,22);
        t.postTweet(1,11);
        t.getNewsFeed(1);

    }
    private static long seq;
    private HashMap<Integer,User> users;
    /** Initialize your data structure here. */
    public Twitter() {
        seq=0l;
        users=new HashMap<Integer,User>();
    }

    /** Compose a new tweet. */
    public void postTweet(int userId, int tweetId) {
        User user=null;
        if(!users.containsKey(userId)){
            user=new User(userId);
        }else{
            user=users.get(userId);
        }
        user.post(tweetId,seq);
        users.put(userId,user);
        seq++;
    }

    /** Retrieve the 10 most recent tweet ids in the user's news feed. Each item in the news feed must be posted by users who the user followed or by the user herself. Tweets must be ordered from most recent to least recent. */
    public List<Integer> getNewsFeed(int userId) {
        //1:get userId and all the followers
        //2:find all their tweets
        List<Integer> result=new ArrayList();
        User user=users.get(userId);
        if(user!=null){
            ArrayList<Integer> followeeIds=user.followees;
            //find all their recent tweets, add them to a priorityqueue
            PriorityQueue<Tweet> minHeap=new PriorityQueue<Tweet>(10,(a,b)->{
                return (int)(b.seq-a.seq);
            });
            PriorityQueue<Tweet> myTweets=user.getRecentTweets();
            for(Tweet t:myTweets){
                minHeap.offer(t);
            }
            for(Integer followeeId:followeeIds){
                User followee=users.get(followeeId);
                PriorityQueue<Tweet> tweets=followee.getRecentTweets();
                for(Tweet t:tweets){
                    minHeap.offer(t);
                }
            }
            for(int i=0;i<10;i++){
                if(minHeap.isEmpty()){
                    break;
                }else{
                    result.add(minHeap.poll().tweetId);
                }
            }
        }
        return result;
    }

    /** Follower follows a followee. If the operation is invalid, it should be a no-op. */
    public void follow(int followerId, int followeeId) {
        if(followeeId==followerId)  return;
        //add all followee's tweets
        User follower=users.get(followerId);
        if(follower==null){
            follower=new User(followerId);
            users.put(followerId,follower);
        }
        if(!users.containsKey(followeeId)){
            User followee=new User(followeeId);
            users.put(followeeId,followee);
        }
        follower.follow(followeeId);
    }

    /** Follower unfollows a followee. If the operation is invalid, it should be a no-op. */
    public void unfollow(int followerId, int followeeId) {
        if(followeeId==followerId)  return;
        User user=users.get(followerId);
        if(user!=null)
            user.unfollow(followeeId);
    }
}

class Tweet{
    public int tweetId;
    public long seq;

    public Tweet(int tweetId,long seq){
        this.tweetId=tweetId;
        this.seq=seq;
    }
}

class User{
    public int userId;
    ArrayList<Integer> followees;
    ArrayList<Tweet> tweets;

    public User(int userId){
        this.userId=userId;
        followees=new ArrayList<Integer>();
        tweets=new ArrayList<Tweet>();
    }

    public void post(int tweetId,long seq){
        Tweet tweet=new Tweet(tweetId,seq);
        this.tweets.add(tweet);
    }

    public void follow(int followeeId){
        if(!followees.contains(followeeId)){
            followees.add(followeeId);
        }
    }

    public void unfollow(int followeeId){
        if(followees.contains(followeeId)){
            followees.remove(new Integer(followeeId));
        }
    }

    //return 10 recent tweets,order by seq
    public PriorityQueue<Tweet> getRecentTweets(){
        int index=0;
        PriorityQueue<Tweet> res=new PriorityQueue<Tweet>(10,(a,b)->{
            return (int)(b.seq-a.seq);
        });
        while(index<tweets.size()){
            res.offer(tweets.get(index));
            index++;
        }
        return res;
    }
}