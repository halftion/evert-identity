package Replys;
import sqlConnection.sqlconnect;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import messages.EscapeUnescape;

public class Replydao {
    private Reply reply;
    PreparedStatement ps = null;

    public Replydao (Reply reply){
        this.reply = reply;
    }

    public int insert(){
        int state = 0;//提交状态
        sqlconnect pool = sqlconnect.getInstance();
        Connection connection = pool.getConnection();
        String sql = "INSERT INTO replys (isRead,messageId,replyTo,autherId,autherName,autherHeadPic,replyToUserName,content,messageTitle,messageDigest,creatTime,messageAuther,digest) VALUE (?,?,?,?,?,?,?,?,?,?,?,?,?)";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1,reply.getIsRead());
            ps.setInt(2,reply.getMessageId());
            ps.setInt(3,reply.getReplyTo());
            ps.setInt(4,reply.getAutherId());
            ps.setString(5,reply.getAutherName());
            ps.setString(6,reply.getAutherHeadPic());
            ps.setString(7,reply.getReplyToUserName());
            ps.setString(8,reply.getContent());
            ps.setString(9,reply.getMessageTitle());
            ps.setString(10,reply.getMessageDigest());
            ps.setString(11,reply.getCreatTime());
            ps.setString(12,reply.getMessageAuther());
            ps.setString(13,reply.getDigest());
            state = ps.executeUpdate();
            ps.close();
        }catch (SQLException e){
            e.printStackTrace();
        }
        pool.freeConnection(connection);
        return state;
    }

    public int update(){
        int state = 0;//提交状态
        sqlconnect pool = sqlconnect.getInstance();
        Connection connection = pool.getConnection();
        String sql = "UPDATE replys SET isRead = ?,messageId = ?,replyTo = ?,autherId = ?,autherName = ?,autherHeadPic = ?,replyToUserName = ?,content = ?,messageTitle = ?,messageDigest = ?,creatTime = ?,messageAuther = ?,digest = ? WHERE replyId = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(14,reply.getReplyId());
            ps.setInt(1,reply.getIsRead());
            ps.setInt(2,reply.getMessageId());
            ps.setInt(3,reply.getReplyTo());
            ps.setInt(4,reply.getAutherId());
            ps.setString(5,reply.getAutherName());
            ps.setString(6,reply.getAutherHeadPic());
            ps.setString(7,reply.getReplyToUserName());
            ps.setString(8,reply.getContent());
            ps.setString(9,reply.getMessageTitle());
            ps.setString(10,reply.getMessageDigest());
            ps.setString(11,reply.getCreatTime());
            ps.setString(12,reply.getMessageAuther());
            ps.setString(13,reply.getDigest());
            state = ps.executeUpdate();
            ps.close();
        }catch (SQLException e){
            e.printStackTrace();
        }
        pool.freeConnection(connection);
        return state;
    }

    public int delete(){
        int state = 0;//提交状态
        sqlconnect pool = sqlconnect.getInstance();
        Connection connection = pool.getConnection();
        String sql = "DELETE FROM replys WHERE replyId = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1,reply.getReplyId());
            state = ps.executeUpdate();
            ps.close();
        }catch (SQLException e){
            e.printStackTrace();
        }
        pool.freeConnection(connection);
        return state;
    }

    public Reply getReply(int id) {
        sqlconnect pool = sqlconnect.getInstance();
        Connection connection = pool.getConnection();
        String sql = "SELECT * FROM replys WHERE replyId = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1,id);
            ResultSet rs = ps.executeQuery();
            rs.next();
            reply.setReplyId(rs.getInt("replyId"));
            reply.setIsRead(rs.getInt("isRead"));
            reply.setMessageId(rs.getInt("messageId"));
            reply.setReplyTo(rs.getInt("replyTo"));
            reply.setAutherId(rs.getInt("autherId"));
            reply.setAutherName(rs.getString("autherName"));
            reply.setAutherHeadPic(rs.getString("autherHeadPic"));
            reply.setReplyToUserName(rs.getString("replyToUserName"));
            reply.setContent(EscapeUnescape.unescape(rs.getString("content")));
            reply.setMessageTitle(rs.getString("messageTitle"));
            reply.setMessageDigest(rs.getString("messageDigest"));
            reply.setCreatTime(rs.getString("creatTime"));
            reply.setMessageAuther(rs.getString("messageAuther"));
            reply.setDigest(rs.getString("digest"));
            ps.close();
        }catch (SQLException e){
            e.printStackTrace();
        }
        pool.freeConnection(connection);
        return reply;
    }

    public Reply[] getReplyByMessage(int messageId) {
        sqlconnect pool = sqlconnect.getInstance();
        Connection connection = pool.getConnection();
        String sql = "SELECT * FROM replys WHERE messageId = ?";
        Reply[] replys = null;
        int n = 0;
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1,messageId);
            ResultSet rs = ps.executeQuery();
            rs.next();
            rs.last();
            n = rs.getRow();
            replys = new Reply[n];
            rs.beforeFirst();
            n = 0;
            while (rs.next()){
                Reply reply = new Reply();
                reply.setReplyId(rs.getInt("replyId"));
                reply.setIsRead(rs.getInt("isRead"));
                reply.setMessageId(rs.getInt("messageId"));
                reply.setReplyTo(rs.getInt("replyTo"));
                reply.setAutherId(rs.getInt("autherId"));
                reply.setAutherName(rs.getString("autherName"));
                reply.setAutherHeadPic(rs.getString("autherHeadPic"));
                reply.setReplyToUserName(rs.getString("replyToUserName"));
                reply.setContent(EscapeUnescape.unescape(rs.getString("content")));
                reply.setMessageTitle(rs.getString("messageTitle"));
                reply.setMessageDigest(rs.getString("messageDigest"));
                reply.setCreatTime(rs.getString("creatTime"));
                reply.setMessageAuther(rs.getString("messageAuther"));
                reply.setDigest(rs.getString("digest"));
                replys[n++] = reply;
            }
            ps.close();
        }catch (SQLException e){
            e.printStackTrace();
        }
        pool.freeConnection(connection);
        return replys;
    }

    public Reply[] getReplyByAutner(int autherId) {
        sqlconnect pool = sqlconnect.getInstance();
        Connection connection = pool.getConnection();
        String sql = "SELECT * FROM replys WHERE autherId = ? ORDER BY creatTime DESC";
        Reply[] replys = null;
        int n = 0;
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1,autherId);
            ResultSet rs = ps.executeQuery();
            rs.next();
            rs.last();
            n = rs.getRow();
            replys = new Reply[n];
            rs.beforeFirst();
            n = 0;
            while (rs.next()){
                Reply reply = new Reply();
                reply.setReplyId(rs.getInt("replyId"));
                reply.setIsRead(rs.getInt("isRead"));
                reply.setMessageId(rs.getInt("messageId"));
                reply.setReplyTo(rs.getInt("replyTo"));
                reply.setAutherId(rs.getInt("autherId"));
                reply.setAutherName(rs.getString("autherName"));
                reply.setAutherHeadPic(rs.getString("autherHeadPic"));
                reply.setReplyToUserName(rs.getString("replyToUserName"));
                reply.setContent(EscapeUnescape.unescape(rs.getString("content")));
                reply.setMessageTitle(rs.getString("messageTitle"));
                reply.setMessageDigest(rs.getString("messageDigest"));
                reply.setCreatTime(rs.getString("creatTime"));
                reply.setMessageAuther(rs.getString("messageAuther"));
                reply.setDigest(rs.getString("digest"));
                replys[n++] = reply;
            }
            ps.close();
        }catch (SQLException e){
            e.printStackTrace();
        }
        pool.freeConnection(connection);
        return replys;
    }

}
