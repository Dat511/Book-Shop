package DAO;

import Models.UserDTO;
import Utils.DBUtils;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DAO_Users {

    public static DAO_Users INSTANCE = new DAO_Users();
    public Connection con;
    public String status = "OK";

    public DAO_Users() {
        try {
            con = DBUtils.getConnection();
            System.out.println("Kết nối database: " + (con != null && !con.isClosed()));
        } catch (SQLException e) {
            status = "Error ar connection" + e.getMessage();
            System.out.println(status);
        }
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    private int getNextId() {
        int nextId = 1;

        try {
            con = DBUtils.getConnection();
            String sql = "SELECT MAX(id) AS max_id FROM tblUser";
            PreparedStatement stmt = con.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                int maxId = rs.getInt("max_id");
                nextId = maxId + 1;
            }
        } catch (SQLException ex) {
        }

        return nextId;
    }

    public boolean Register(UserDTO user) {
        boolean isRegistered = false;

        try {
            con = DBUtils.getConnection();

            int newId = getNextId();
            user.setId(newId);

            String insertSql = "INSERT INTO tblUser (id, username, fullname, email, password, phone_number, role) VALUES (?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement stmt = con.prepareStatement(insertSql);
            stmt.setInt(1, user.getId());
            stmt.setString(2, user.getUsername());
            stmt.setString(3, user.getFullname());
            stmt.setString(4, user.getEmail());
            stmt.setString(5, user.getPassword());
            stmt.setString(6, user.getPhoneNumber());
            stmt.setBoolean(7, user.isRole());

            int rowAffected = stmt.executeUpdate();
            if (rowAffected > 0) {
                isRegistered = true;
            }
        } catch (SQLException ex) {
            System.err.println("SQL Error: " + ex.getMessage());
            System.err.println("SQL State: " + ex.getSQLState());
            System.err.println("Error Code: " + ex.getErrorCode());
        }

        return isRegistered;
    }

    public UserDTO login(String email, String password) {

        UserDTO user = null;
        try {
            String sql = "SELECT * FROM tblUser";
            sql += " WHERE username = ? AND password = ?";

            PreparedStatement stm = con.prepareStatement(sql);
            stm.setString(1, email);
            stm.setString(2, password);
            ResultSet rs = stm.executeQuery();

            if (rs != null) {
                if (rs.next()) {
                    user = new UserDTO();
                    user.setId(rs.getInt("id"));
                    user.setUsername(rs.getString("username"));
                    user.setPassword(rs.getString("password"));
                    user.setEmail(rs.getString("email"));
                    user.setFullname(rs.getString("fullname"));
                    user.setPhoneNumber(rs.getString("phone_number"));
                    user.setRole(rs.getBoolean("role"));
                }
            }

        } catch (SQLException ex) {
        }

        return user;
    }

    public boolean updateUsers(int id, String username, String fullname, String email, String phoneNumber, String password, boolean role) {
        String sql = "  UPDATE tblUser SET id = ?, username = ?, fullname = ?, email = ?, phone_number = ?, password = ?, role = ?";
        try {
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setInt(1, id);
            pst.setString(2, username);
            pst.setString(3, fullname);
            pst.setString(4, email);
            pst.setString(5, phoneNumber);
            pst.setString(6, password);
            pst.setBoolean(7, role);
            return pst.executeUpdate() > 0;
        } catch (SQLException e) {
            return false;
        }
    }

    public List<UserDTO> loadAllUsers() {
        List<UserDTO> user = new ArrayList<>();
        String sql = "SELECT * FROM tblUser";
        try {
            PreparedStatement pst = con.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                user.add(new UserDTO(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getBoolean(7)
                ));
            }
        } catch (SQLException e) {
            status = "Error ar read User " + e.getMessage();
        }
        return user;
    }

    //deleteUsers
    public void deleteUser(String id) {
        String sql = "DELETE FROM tblUser WHERE id = ?";
        try {
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setInt(1, Integer.parseInt(id));
            pst.executeUpdate();
        } catch (NumberFormatException | SQLException e) {
            status = "Error ar read User " + e.getMessage();
        }
    }

    public UserDTO getUserById(int userId) {
        String sql = "SELECT * FROM tblUser WHERE id = ?";
        try {
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setInt(1, userId);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                return new UserDTO(
                        rs.getInt("id"),
                        rs.getString("username"),
                        rs.getString("fullname"),
                        rs.getString("email"),
                        rs.getString("phone_number"),
                        rs.getString("password"),
                        rs.getBoolean("role")
                );
            }
        } catch (SQLException e) {
        }
        return null;
    }

    public boolean changeRoleUser(int id) {
        String sql = "UPDATE tblUser SET role = CASE WHEN role = 1 THEN 0 ELSE 1 END WHERE id = ?";
        try (PreparedStatement pst = con.prepareStatement(sql);) {
            pst.setInt(1, id); // Set the user ID
            int affectedRows = pst.executeUpdate(); // Execute the update
            if (affectedRows > 0) {
                System.out.println("User role updated successfully.");
            } else {
                System.out.println("User not found or no change needed.");
            }
        } catch (SQLException e) {
            return false;
        }
        return true;
    }

    public boolean updateUser(int id, String username, String fullname, String email, String phoneNumber, String password, Boolean role) {
        StringBuilder sql = new StringBuilder("UPDATE tblUser SET ");
        boolean first = true;

        if (username != null) {
            sql.append("username = ?");
            first = false;
        }
        if (fullname != null) {
            if (!first) {
                sql.append(", ");
            }
            sql.append("fullname = ?");
            first = false;
        }
        if (email != null) {
            if (!first) {
                sql.append(", ");
            }
            sql.append("email = ?");
            first = false;
        }
        if (phoneNumber != null) {
            if (!first) {
                sql.append(", ");
            }
            sql.append("phone_number = ?");
            first = false;
        }
        if (password != null) {
            if (!first) {
                sql.append(", ");
            }
            sql.append("password = ?"); 
            first = false;
        }
        if (role != null) {
            if (!first) {
                sql.append(", ");
            }
            sql.append("role = ?");
        }

        sql.append(" WHERE id = ?");

        try {
            PreparedStatement pst = con.prepareStatement(sql.toString());
            int paramIndex = 1;

            if (username != null) {
                pst.setString(paramIndex++, username);
            }
            if (fullname != null) {
                pst.setString(paramIndex++, fullname);
            }
            if (email != null) {
                pst.setString(paramIndex++, email);
            }
            if (phoneNumber != null) {
                pst.setString(paramIndex++,password);
            }
            if (password != null) {
                pst.setString(paramIndex++,  phoneNumber);
            }
            if (role != null) {
                pst.setBoolean(paramIndex++, role);
            }
            pst.setInt(paramIndex, id);

            return pst.executeUpdate() > 0;
        } catch (SQLException e) {
            return false;
        }
    }

    public static void main(String[] args) {
        DAO_Users u = new DAO_Users();
        u.updateUser(4, "thuan", "adsfas", "fasdf", "fasdfas", "123", false);
        System.out.println(u.getUserById(4).toString());
    }
}
