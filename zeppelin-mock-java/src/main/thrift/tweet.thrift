// from http://diwakergupta.github.io/thrift-missing-guide/

namespace java zeppelin

enum TweetType {
    TWEET = 1,
    RETWEET = 2,
    DM = 3,
    REPLY = 4
}

struct Tweet {
    1: required i32 userId;
    2: required string userName;
    3: required string text;
    4: optional string loc;
    5: optional TweetType tweetType = TweetType.TWEET
    16: optional string language = "english"
}

struct TweetSearchResult {
    1: list<Tweet> tweets;
}

exception TwitterUnavailable {
    1: string message;
}

const i32 MAX_RESULTS = 100;
  
service Twitter {
    void ping(),                                                            
    bool postTweet(1:Tweet tweet) throws (1:TwitterUnavailable unavailable),
    TweetSearchResult searchTweets(1:string query);
}