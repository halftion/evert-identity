package Users;
import sqlConnection.sqlconnect;

import java.sql.*;

public class userdao {
    private User user;
    PreparedStatement ps = null;

    public userdao(User user){
        this.user = user;
    }

    public int insert(){
        int state = 0;//提交状态
        sqlconnect pool = sqlconnect.getInstance();
        Connection connection = pool.getConnection();
        String sql = "INSERT INTO users (sexNumber,age,userName,password,headPic,signature,address,phoneNumber,introduction,profession,coverPic,control,state) VALUE (?,?,?,?,?,?,?,?,?,?,?,?,?)";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1,user.getSexNumber());
            ps.setInt(2,user.getSexNumber());
            ps.setString(3,user.getUserName());
            ps.setString(4,user.getPassword());
            ps.setString(5,user.getHeadPic());
            ps.setString(6,user.getSignature());
            ps.setString(7,user.getAddress());
            ps.setString(8,user.getPhoneNumber());
            ps.setString(9,user.getIntroduction());
            ps.setString(10,user.getProfession());
            ps.setString(11,user.getCoverPic());
            ps.setInt(12,user.getControl());
            ps.setInt(13,user.getState());
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
        String sql = "UPDATE users SET sexNumber = ?,age = ?,userName = ?,password = ?,headPic = ?,signature = ?,address = ?,phoneNumber = ?,introduction = ?,profession = ?,coverPic = ?,control = ?,state = ? WHERE userid = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(14,user.getUserid());
            ps.setInt(1,user.getSexNumber());
            ps.setInt(2,user.getSexNumber());
            ps.setString(3,user.getUserName());
            ps.setString(4,user.getPassword());
            ps.setString(5,user.getHeadPic());
            ps.setString(6,user.getSignature());
            ps.setString(7,user.getAddress());
            ps.setString(8,user.getPhoneNumber());
            ps.setString(9,user.getIntroduction());
            ps.setString(10,user.getProfession());
            ps.setString(11,user.getCoverPic());
            ps.setInt(12,user.getControl());
            ps.setInt(13,user.getState());
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
        String sql = "DELETE FROM users WHERE userid = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1,user.getUserid());
            state = ps.executeUpdate();
            ps.close();
        }catch (SQLException e){
            e.printStackTrace();
        }
        pool.freeConnection(connection);
        return state;
    }

    public User getUser(int id){
        sqlconnect pool = sqlconnect.getInstance();
        Connection connection = pool.getConnection();
        String sql = "SELECT * FROM users WHERE userid = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1,id);
            ResultSet rs = ps.executeQuery();
            rs.next();
            user.setUserid(rs.getInt("userid"));
            user.setAge(rs.getInt("age"));
            user.setSexNumber(rs.getInt("sexNumber"));
            user.setUserName(rs.getString("userName"));
            user.setPassword(rs.getString("password"));
            user.setHeadPic(rs.getString("headPic"));
            user.setSignature(rs.getString("signature"));
            user.setAddress(rs.getString("address"));
            user.setPhoneNumber(rs.getString("phonenumber"));
            user.setIntroduction(rs.getString("introduction"));
            user.setProfession(rs.getString("profession"));
            user.setCoverPic(rs.getString("coverPic"));
            user.setControl(rs.getInt("control"));
            user.setState(rs.getInt("state"));
            ps.close();
        }catch (SQLException e){
            e.printStackTrace();
        }
        pool.freeConnection(connection);
        return user;
    }

    public User getUser(String phone){
        sqlconnect pool = sqlconnect.getInstance();
        Connection connection = pool.getConnection();
        String sql = "SELECT * FROM users WHERE phoneNumber = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1,phone);
            ResultSet rs = ps.executeQuery();
            rs.next();
            user.setUserid(rs.getInt("userid"));
            user.setAge(rs.getInt("age"));
            user.setSexNumber(rs.getInt("sexNumber"));
            user.setUserName(rs.getString("userName"));
            user.setPassword(rs.getString("password"));
            user.setHeadPic(rs.getString("headPic"));
            user.setSignature(rs.getString("signature"));
            user.setAddress(rs.getString("address"));
            user.setPhoneNumber(rs.getString("phonenumber"));
            user.setIntroduction(rs.getString("introduction"));
            user.setProfession(rs.getString("profession"));
            user.setCoverPic(rs.getString("coverPic"));
            user.setControl(rs.getInt("control"));
            user.setState(rs.getInt("state"));
            ps.close();
        }catch (SQLException e){
            e.printStackTrace();
        }
        pool.freeConnection(connection);
        return user;
    }

    public boolean checkuser(String phone){
        boolean state = false;//提交状态
        sqlconnect pool = sqlconnect.getInstance();
        Connection connection = pool.getConnection();
        String sql = "SELECT * FROM users WHERE phoneNumber = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1,phone);
            ResultSet rs = ps.executeQuery();
            if(rs.next()) state = true;
            ps.close();
        }catch (SQLException e){
            e.printStackTrace();
        }
        pool.freeConnection(connection);
        return state;
    }

    public User[] getUsers(){
        sqlconnect pool = sqlconnect.getInstance();
        Connection connection = pool.getConnection();
        String sql = "SELECT * FROM users";
        User users[] = null;
        int n = 0;
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            rs.last();
            n = rs.getRow();
            users = new User[n];
            rs.beforeFirst();
            n = 0;
            while(rs.next()){
                User user = new User();
                user.setUserid(rs.getInt("userid"));
                user.setAge(rs.getInt("age"));
                user.setSexNumber(rs.getInt("sexNumber"));
                user.setUserName(rs.getString("userName"));
                user.setPassword(rs.getString("password"));
                user.setHeadPic(rs.getString("headPic"));
                user.setSignature(rs.getString("signature"));
                user.setAddress(rs.getString("address"));
                user.setPhoneNumber(rs.getString("phonenumber"));
                user.setIntroduction(rs.getString("introduction"));
                user.setProfession(rs.getString("profession"));
                user.setCoverPic(rs.getString("coverPic"));
                user.setControl(rs.getInt("control"));
                user.setState(rs.getInt("state"));
                users[n++] = user;
            }
            ps.close();
        }catch (SQLException e){
            e.printStackTrace();
        }
        pool.freeConnection(connection);
        return users;
    }

}
