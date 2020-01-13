package messages;

import sqlConnection.sqlconnect;

import java.sql.*;

public class Messagedao {
    private message Message;
    PreparedStatement ps = null;

    public Messagedao(message Message){
        this.Message = Message;
    }

    public int insert(){
        int state = 0;//提交状态
        sqlconnect pool = sqlconnect.getInstance();
        Connection connection = pool.getConnection();
        String sql = "INSERT INTO messages (likeNumber,autherid,title,content,creatTime,auther,headPic,digest) VALUE (?,?,?,?,?,?,?,?)";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1,Message.getLikeNumber());
            ps.setInt(2,Message.getAutherid());
            ps.setString(3,Message.getTitle());
            ps.setString(4,Message.getContent());
            ps.setString(5,Message.getCreatTime());
            ps.setString(6,Message.getAuther());
            ps.setString(7,Message.getHeadpic());
            ps.setString(8,Message.getDigest());
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
        String sql = "UPDATE messages SET likeNumber = ?,autherid = ?,title = ?,content = ?,creatTime = ?,auther = ?,headPic = ?,digest = ? WHERE messageid = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1,Message.getLikeNumber());
            ps.setInt(2,Message.getAutherid());
            ps.setString(3,Message.getTitle());
            ps.setString(4,Message.getContent());
            ps.setString(5,Message.getCreatTime());
            ps.setString(6,Message.getAuther());
            ps.setString(7,Message.getHeadpic());
            ps.setString(8,Message.getDigest());
            ps.setInt(9,Message.getMessageid());
            state = ps.executeUpdate();
            ps.close();
        }catch (SQLException e){
            e.printStackTrace();
        }
        pool.freeConnection(connection);
        return state;
    }

    public int delete(){
        int state = 0;
        sqlconnect pool = sqlconnect.getInstance();
        Connection connection = pool.getConnection();
        String sql = "DELETE FROM messages WHERE messageid = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1,Message.getMessageid());
            state = ps.executeUpdate();
            ps.close();
        }catch (SQLException e){
            e.printStackTrace();
        }
        pool.freeConnection(connection);
        return state;
    }

    public message getmessage(int id){
        sqlconnect pool = sqlconnect.getInstance();
        Connection connection = pool.getConnection();
        String sql = "SELECT * FROM messages WHERE messageid = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1,id);
            ResultSet rs = ps.executeQuery();
            rs.next();
            Message.setMessageid(rs.getInt("messageid"));
            Message.setLikeNumber(rs.getInt("likeNumber"));
            Message.setAutherid(rs.getInt("autherid"));
            Message.setTitle(rs.getString("title"));
            Message.setContent(EscapeUnescape.unescape(rs.getString("content")));
            Message.setCreatTime(rs.getString("creatTime"));
            Message.setHeadpic(rs.getString("headPic"));
            Message.setAuther(rs.getString("auther"));
            Message.setDigest(rs.getString("digest"));
            ps.close();
        }catch (SQLException e){
            e.printStackTrace();
        }
        pool.freeConnection(connection);
        return Message;
    }

    public message[] getmessages(String phone){
        int n = 0;
        sqlconnect pool = sqlconnect.getInstance();
        Connection connection = pool.getConnection();
        String sql2 = "SELECT * FROM users WHERE phoneNumber = ?";
        String sql = "SELECT * FROM messages WHERE autherid = ? ORDER BY creatTime DESC";
        message Messages[] = null;
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            PreparedStatement ps2 = connection.prepareStatement(sql2);
            ps2.setString(1,phone);
            ResultSet rs2 = ps2.executeQuery();
            rs2.next();
            int userid = rs2.getInt("userid");
            ps.setInt(1,userid);
            ResultSet rs = ps.executeQuery();
            rs.last();
            n = rs.getRow();
            Messages = new message[n];
            rs.beforeFirst();
            n = 0;
            while (rs.next()){
                message Message = new message();
                Message.setMessageid(rs.getInt("messageid"));
                Message.setLikeNumber(rs.getInt("likeNumber"));
                Message.setAutherid(rs.getInt("autherid"));
                Message.setTitle(rs.getString("title"));
                Message.setDigest(rs.getString("digest"));
                Message.setCreatTime(rs.getString("creatTime"));
                Message.setHeadpic(rs.getString("headPic"));
                Message.setAuther(rs.getString("auther"));
                Messages[n++] = Message;
            }
            ps.close();
        }catch (SQLException e){
            e.printStackTrace();
        }
        pool.freeConnection(connection);
        return Messages;
    }

    public message[] getmessagesByAutherid(int autherid){
        int n = 0;
        sqlconnect pool = sqlconnect.getInstance();
        Connection connection = pool.getConnection();
        String sql = "SELECT * FROM messages WHERE autherid = ? ORDER BY creatTime DESC";
        message Messages[] = null;
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1,autherid);
            ResultSet rs = ps.executeQuery();
            rs.last();
            n = rs.getRow();
            Messages = new message[n];
            rs.beforeFirst();
            n = 0;
            while (rs.next()){
                message Message = new message();
                Message.setMessageid(rs.getInt("messageid"));
                Message.setLikeNumber(rs.getInt("likeNumber"));
                Message.setAutherid(rs.getInt("autherid"));
                Message.setTitle(rs.getString("title"));
                Message.setDigest(rs.getString("digest"));
                Message.setCreatTime(rs.getString("creatTime"));
                Message.setHeadpic(rs.getString("headPic"));
                Message.setAuther(rs.getString("auther"));
                Messages[n++] = Message;
            }
            ps.close();
        }catch (SQLException e){
            e.printStackTrace();
        }
        pool.freeConnection(connection);
        return Messages;
    }

    public message[] getmessages(int page){
        int n = 0;
        sqlconnect pool = sqlconnect.getInstance();
        Connection connection = pool.getConnection();
        String sql = "SELECT * FROM messages ORDER BY creatTime DESC limit ?,10 ";
        message Messages[] = null;
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1,page * 10);
            ResultSet rs = ps.executeQuery();
            rs.last();
            n = rs.getRow();
            Messages = new message[n];
            rs.beforeFirst();
            n = 0;
            while (rs.next()){
                message Message = new message();
                Message.setMessageid(rs.getInt("messageid"));
                Message.setLikeNumber(rs.getInt("likeNumber"));
                Message.setAutherid(rs.getInt("autherid"));
                Message.setTitle(rs.getString("title"));
                Message.setDigest(rs.getString("digest"));
                Message.setCreatTime(rs.getString("creatTime"));
                Message.setHeadpic(rs.getString("headPic"));
                Message.setAuther(rs.getString("auther"));
                Messages[n++] = Message;
            }
            ps.close();
        }catch (SQLException e){
            e.printStackTrace();
        }
        pool.freeConnection(connection);
        return Messages;
    }

    public message[] search(String string){
        int n = 0;
        sqlconnect pool = sqlconnect.getInstance();
        Connection connection = pool.getConnection();
        String sql = "SELECT * FROM messages WHERE title LIKE ? OR auther LIKE ? OR digest LIKE ?";
        message Messages[] = null;
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1,"%"+string+"%");
            ps.setString(2,"%"+string+"%");
            ps.setString(3,"%"+string+"%");
            ResultSet rs = ps.executeQuery();
            rs.last();
            n = rs.getRow();
            Messages = new message[n];
            rs.beforeFirst();
            n = 0;
            while (rs.next()){
                message Message = new message();
                Message.setMessageid(rs.getInt("messageid"));
                Message.setLikeNumber(rs.getInt("likeNumber"));
                Message.setAutherid(rs.getInt("autherid"));
                Message.setTitle(rs.getString("title"));
                Message.setContent(EscapeUnescape.unescape(rs.getString("content")));
                Message.setCreatTime(rs.getString("creatTime"));
                Message.setHeadpic(rs.getString("headPic"));
                Message.setAuther(rs.getString("auther"));
                Message.setDigest(rs.getString("digest"));
                Messages[n++] = Message;
            }
            ps.close();
        }catch (SQLException e){
            e.printStackTrace();
        }
        pool.freeConnection(connection);
        return Messages;
    }

}
