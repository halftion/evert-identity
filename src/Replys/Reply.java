package Replys;

import java.sql.Timestamp;
import java.util.Date;

/* 评论 */
public class Reply {
    private int isRead;//未被阅读为0
    private int replyId;
    private int messageId;
    private int replyTo; //指示回复父reply,0为直接评论，其余为replyid
    private int autherId;
    private String autherName;
    private String autherHeadPic;
    private String replyToUserName;//被回复用户名
    private String content;
    private String messageTitle;
    private String messageDigest;
    private String creatTime;
    private String messageAuther;
    private String digest;

    public Reply() {
        Date date = new Date();
        Long lon = date.getTime();
        Timestamp ts = new Timestamp(lon);
        this.isRead = 0;
        this.replyId = 0;
        this.messageId = 0;
        this.replyTo = 0;
        this.autherId = 0;
        this.autherName = "作者";
        this.autherHeadPic = "https://i.loli.net/2019/11/06/Mzv84LUWjqgCZyV.png";
        this.replyToUserName = "";
        this.content = "";
        this.messageTitle = "测试标题";
        this.messageDigest = "留言摘要";
        this.creatTime = ts.toString();
        this.messageAuther = "留言作者";
        this.digest = "留言摘要";
    }

    public Reply(int isRead, int replyId, int messageId, int replyTo, int autherId, String autherName, String autherHeadPic, String replyToUserName, String content, String messageTitle, String messageDigest, String creatTime,String messageAuther,String digest) {
        this.isRead = isRead;
        this.replyId = replyId;
        this.messageId = messageId;
        this.replyTo = replyTo;
        this.autherId = autherId;
        this.autherName = autherName;
        this.autherHeadPic = autherHeadPic;
        this.replyToUserName = replyToUserName;
        this.content = content;
        this.messageTitle = messageTitle;
        this.messageDigest = messageDigest;
        this.creatTime = creatTime;
        this.messageAuther = messageAuther;
        this.digest = digest;
    }

    public int getIsRead() {
        return isRead;
    }

    public void setIsRead(int isRead) {
        this.isRead = isRead;
    }

    public int getReplyId() {
        return replyId;
    }

    public void setReplyId(int replyId) {
        this.replyId = replyId;
    }

    public int getMessageId() {
        return messageId;
    }

    public void setMessageId(int messageId) {
        this.messageId = messageId;
    }

    public int getReplyTo() {
        return replyTo;
    }

    public void setReplyTo(int replyTo) {
        this.replyTo = replyTo;
    }

    public int getAutherId() {
        return autherId;
    }

    public void setAutherId(int autherId) {
        this.autherId = autherId;
    }

    public String getAutherName() {
        return autherName;
    }

    public void setAutherName(String autherName) {
        this.autherName = autherName;
    }

    public String getAutherHeadPic() {
        return autherHeadPic;
    }

    public void setAutherHeadPic(String autherHeadPic) {
        this.autherHeadPic = autherHeadPic;
    }

    public String getReplyToUserName() {
        return replyToUserName;
    }

    public void setReplyToUserName(String replyToUserName) {
        this.replyToUserName = replyToUserName;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getMessageTitle() {
        return messageTitle;
    }

    public void setMessageTitle(String messageTitle) {
        this.messageTitle = messageTitle;
    }

    public String getMessageDigest() {
        return messageDigest;
    }

    public void setMessageDigest(String messageDigest) {
        this.messageDigest = messageDigest;
    }

    public String getCreatTime() {
        return creatTime;
    }

    public void setCreatTime(String creatTime) {
        this.creatTime = creatTime;
    }

    public String getMessageAuther() {
        return messageAuther;
    }

    public void setMessageAuther(String messageAuther) {
        this.messageAuther = messageAuther;
    }

    public String getDigest() {
        return digest;
    }

    public void setDigest(String digest) {
        this.digest = digest;
    }
}
