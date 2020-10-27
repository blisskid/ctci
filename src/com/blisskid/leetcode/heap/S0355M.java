package com.blisskid.leetcode.heap;
import java.util.*;

public class S0355M {

    public static void main(String[] args) {
        S0355M s = new S0355M();
        s.postTweet(1, 5);
        s.postTweet(1, 3);
        s.getNewsFeed(1);
    }
    //tow map to store tweet and follow
    Map<Integer, List<Tweet>> tMap = null;
    Map<Integer, List<Integer>> fMap = null;
    private int count = 0;

    /** Initialize your data structure here. */
    public S0355M() {
        tMap = new HashMap();
        fMap = new HashMap();
    }

    private class Tweet implements Comparable<Tweet> {
        Integer tweetId;
        Long currentTime;

        Tweet(Integer tweetId, Long currentTime) {
            this.tweetId = tweetId;
            this.currentTime = currentTime;
        }

        @Override
        public int compareTo(Tweet o) {
            return o.currentTime.compareTo(this.currentTime);
        }

    }

    /** Compose a new tweet. */
    public void postTweet(int userId, int tweetId) {
        if (count == 100) count = 0;
        List<Tweet> tweets = tMap.get(userId);
        if (tweets == null || tweets.isEmpty()) {
            List<Tweet> list = new ArrayList();
            Tweet tweet = new Tweet(tweetId, System.currentTimeMillis() * 100 + this.count);
            list.add(tweet);
            tMap.put(userId, list);
        } else {
            Tweet tweet = new Tweet(tweetId, System.currentTimeMillis() * 100 + this.count);
            tweets.add(tweet);
        }
        count++;
    }

    /** Retrieve the 10 most recent tweet ids in the user's news feed. Each item in the news feed must be posted by users who the user followed or by the user herself. Tweets must be ordered from most recent to least recent. */
    public List<Integer> getNewsFeed(int userId) {
        PriorityQueue<Tweet> newestTweets = new PriorityQueue(10);
        //find my tweets
        List<Tweet> mytweets = tMap.get(userId);
        if (mytweets != null && !mytweets.isEmpty()) {
            for (Tweet tweet : mytweets) {
                newestTweets.offer(tweet);
            }
        }
        //find followees
        List<Integer> followees = fMap.get(userId);
        if (followees != null && !followees.isEmpty()) {
            for (Integer followee : followees) {
                //find tweets
                List<Tweet> tweets = tMap.get(followee);
                if (tweets != null && !tweets.isEmpty()) {
                    for (Tweet tweet : tweets) {
                        newestTweets.offer(tweet);
                    }
                }
            }
        }

        List<Integer> resultList = new ArrayList();
        while (!newestTweets.isEmpty()) {
            Tweet t = newestTweets.poll();
            resultList.add(t.tweetId);
        }

        return resultList;
    }

    /** Follower follows a followee. If the operation is invalid, it should be a no-op. */
    public void follow(int followerId, int followeeId) {
        List<Integer> followees = fMap.get(followerId);
        if (followees == null || followees.isEmpty()) {
            List<Integer> list = new ArrayList();
            list.add(followeeId);
            fMap.put(followerId, list);
        } else {
            followees.add(followeeId);
        }
    }

    /** Follower unfollows a followee. If the operation is invalid, it should be a no-op. */
    public void unfollow(int followerId, int followeeId) {
        List<Integer> followees = fMap.get(followerId);
        if (followees != null && !followees.isEmpty()) {
            followees.remove(new Integer(followeeId));
        }
    }
}
