package com.github.haliibobo.learn.redis.vote;

import org.junit.Before;
import org.junit.Test;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.ZParams;
import redis.clients.jedis.params.ZAddParams;

import java.util.*;

public class Vote {

    private static final  int ONE_WEEK_SECONDS = 7*24*60*60;
    private static final  int VOTE_SCORE = 24*60*60/200;

    private static final  int ARTICLE_PER_PAGE = 10;

    Jedis jedis;

    @Before
    public void setup(){
        jedis = new Jedis("halibobo.cn",9529);
        jedis.auth("19911205_Lzb");
        System.out.println("connected");
    }
    public void  articleVote(Jedis conn,String user,String article,int voted){
        //获取 投票截止时间
        long cutoff = System.currentTimeMillis() - Vote.ONE_WEEK_SECONDS;
        if ( Double.compare(conn.zscore("time:",article),cutoff) < 0 ){
            return;
        }
        //获取文章id
        String articleId = article.split(":")[1];
        //将用户添加到该文章的投票用户名单中
        //新增投票
        if (conn.zadd("voted:" + articleId,voted,user,new ZAddParams().nx()) ==1) {
            //更新分数表中该文章的分数
            conn.zincrby("score:",Vote.VOTE_SCORE*voted,article);
            //更新文章表中 文章的投票数
            conn.hincrBy(article,voted == 1?"votes":"negVotes",1);
        }else if(conn.zadd("voted:" + articleId,voted,user,new ZAddParams().ch()) ==1){
            //修改投票
            //更新分数表中该文章的分数 分数变更两倍
            conn.zincrby("score:",Vote.VOTE_SCORE*voted*2,article);
            //更新文章表中 文章的投票数
            if (voted == 1){ //反对转赞成
                conn.hincrBy(article,"votes",1);
                conn.hincrBy(article,"negVotes",-1);
            }else if (voted == -1){ // 赞成转反对
                conn.hincrBy(article,"votes",-1);
                conn.hincrBy(article,"negVotes",1);
            }
        }
    }




    public String postArticle(Jedis conn,String user,String title,String link){
        //获取新的文章id
        String articleId = String.valueOf(conn.incr("article:"));
        String voted =  "voted:" + articleId;
        conn.zadd(voted,1,user);
        conn.expire(voted,Vote.ONE_WEEK_SECONDS);
        long now = System.currentTimeMillis();
        String article = "articleId:" + articleId;
        Map<String,String> info = new HashMap<>();
        info.put("title",title);
        info.put("link",link);
        info.put("poster",user);
        info.put("time",String.valueOf(now));
        info.put("votes","1");
        info.put("negVotes","0");
        conn.hset(article,info);
        conn.zadd("score:",now +Vote.VOTE_SCORE,article);
        conn.zadd("time:",now,article);
        return articleId;
    }

    public List<Map<String,String>> getArticles(Jedis conn,int page,String order){
        if (order == null) {
            order = "score:";
        }
        List<Map<String,String>> res = new ArrayList<>();
        int start = (page -1)* Vote.ARTICLE_PER_PAGE;
        int end = start + Vote.ARTICLE_PER_PAGE -1;
        Set<String > articleIds  =conn.zrevrange(order,start,end);
        for (String articleId : articleIds) {
            Map<String,String> info = conn.hgetAll(articleId);
            info.put("id",articleId);
            res.add(info);

        }
        return res;
    }

    public void addRemoveGroups (Jedis conn,String articleId,Set<String> toAdd,Set<String> toRemove){
        String article = "article:" + articleId;
        toAdd.forEach(group-> conn.sadd("group:"+ group,article));
        toRemove.forEach(group-> conn.srem("group:"+ group,article));
    }
    public List<Map<String,String>> getGroupArticles (Jedis conn,String group,int page,String order){
        String key = "order:" + group;
        if (!conn.exists(key)){
            ZParams zParams = new ZParams();
            zParams.aggregate(ZParams.Aggregate.MAX);
            conn.zinterstore(key, zParams,"group:" + group,order);
            conn.expire(key,60);
        }
        return getArticles(conn,page,key);
    }

    @Test
    public void testPostArticle(){
        System.out.println("postArticle: " +postArticle(jedis,"halibobo","hello redis","halibobo.cn/hello redis"));
    }

    @Test
    public void testVoteArticle(){
        articleVote(jedis,"halibobo2","articleId:1",-1);
    }
}
