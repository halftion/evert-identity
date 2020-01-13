package messages;

import java.util.Date;
import java.sql.Timestamp;

public class message {
    private int messageid;
    private int likeNumber;
    private int autherid;
    private String title;
    private String content;
    private String creatTime;
    private String headpic;
    private String auther;
    private String digest;


    public message(int messageid, int likeNumber, String title, String content, String creatTime, String headpic, String auther, int autherid,String digest) {
        this.messageid = messageid;
        this.likeNumber = likeNumber;
        this.title = title;
        this.content = content;
        this.creatTime = creatTime;
        this.headpic = headpic;
        this.auther = auther;
        this.autherid = autherid;
        this.digest = digest;
    }


    public message() {
        Date date = new Date();
        Long lon = date.getTime();
        Timestamp ts = new Timestamp(lon);
        this.messageid = 0;
        this.likeNumber = 0;
        this.title = "留言标题";
        this.content = "留言的内容";
        this.creatTime = ts.toString();
        this.headpic = "https://i.loli.net/2019/12/08/QaniKqetLwMoXrA.png";
        this.auther = "用户xxx";
        this.autherid = 0;
        this.digest = "文章摘要";
    }

    public int getMessageid() {
        return messageid;
    }

    public void setMessageid(int messageid) {
        this.messageid = messageid;
    }

    public int getLikeNumber() {
        return likeNumber;
    }

    public void setLikeNumber(int likeNumber) {
        this.likeNumber = likeNumber;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCreatTime() {
        return creatTime;
    }

    public void setCreatTime(String creatTime) {
        this.creatTime = creatTime;
    }

    public String getHeadpic() {
        return headpic;
    }

    public void setHeadpic(String headpic) {
        this.headpic = headpic;
    }

    public String getAuther() {
        return auther;
    }

    public void setAuther(String auther) {
        this.auther = auther;
    }

    public int getAutherid() {
        return autherid;
    }

    public void setAutherid(int autherid) {
        this.autherid = autherid;
    }

    public String getDigest() {
        return digest;
    }

    public void setDigest(String digest) {
        this.digest = digest;
    }
}
