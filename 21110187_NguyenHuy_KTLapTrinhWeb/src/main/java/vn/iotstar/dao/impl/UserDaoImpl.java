package vn.iotstar.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import vn.iotstar.configs.DBConnectMySQL;
import vn.iotstar.configs.DBConnectSQL;
import vn.iotstar.dao.IUserDao;
import vn.iotstar.models.UserModel;

public class UserDaoImpl extends DBConnectMySQL implements IUserDao {

    public Connection conn = null;
    public PreparedStatement ps = null;
    public ResultSet rs = null;

    @Override
    // Lấy ra tất cả user
    public List<UserModel> findAll() {
        String sql = "SELECT * FROM users";
        List<UserModel> list = new ArrayList<UserModel>();
        try {
            conn = new DBConnectSQL().getConnection();
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new UserModel(
                        rs.getString("username"),
                        rs.getString("password"),
                        rs.getString("phone"),
                        rs.getString("fullname"),
                        rs.getString("email"),
                        rs.getBoolean("admin"),
                        rs.getBoolean("active"),
                        rs.getString("images")
                ));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    // Lấy ra một user dựa vào id
    @Override
    public UserModel FindById(int id) {
        String sql = "SELECT * FROM users WHERE id = ? ";
        try {
            conn = new DBConnectSQL().getConnection();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            while (rs.next()) {
                UserModel user = new UserModel(
                        rs.getString("username"),
                        rs.getString("password"),
                        rs.getString("phone"),
                        rs.getString("fullname"),
                        rs.getString("email"),
                        rs.getBoolean("admin"),
                        rs.getBoolean("active"),
                        rs.getString("images")
                );
                return user;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    // Thêm một user mới
    public void insert(UserModel user) {
        String sql = "INSERT INTO users (username, email, password, images, fullname, phone, roleid, createDate, admin, active) " +
                     "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try {
            conn = super.getDatabaseConnection();
            ps = conn.prepareStatement(sql);

            ps.setString(1, user.getUsername());
            ps.setString(2, user.getEmail());
            ps.setString(3, user.getPassword());
            ps.setString(4, user.getImages());
            ps.setString(5, user.getFullname());
            ps.setString(6, user.getPhone());
            ps.setDate(8, new java.sql.Date(System.currentTimeMillis())); // Thời gian tạo user
            ps.setBoolean(9, user.isAdmin());  // Admin
            ps.setBoolean(10, user.isActive()); // Active

            ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        try {
            IUserDao userDao = new UserDaoImpl();
            System.out.println(userDao.FindById(1));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public UserModel findByUserName(String username) {
        String sql = "SELECT * FROM users WHERE username = ? ";
        try {
            conn = new DBConnectSQL().getConnection();
            ps = conn.prepareStatement(sql);
            ps.setString(1, username);
            rs = ps.executeQuery();
            while (rs.next()) {
                UserModel user = new UserModel(
                        rs.getString("username"),
                        rs.getString("password"),
                        rs.getString("phone"),
                        rs.getString("fullname"),
                        rs.getString("email"),
                        rs.getBoolean("admin"),
                        rs.getBoolean("active"),
                        rs.getString("images")
                );
                return user;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
