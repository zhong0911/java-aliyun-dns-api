package cc.antx.cloud.dns.mapper;

import cc.antx.cloud.dns.entity.Account;

import java.sql.*;

public class AccountMapper {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/cloud";
    private static final String DB_USERNAME = "root";
    private static final String DB_PASSWORD = "12345678";

    public static Account getAccountByUsername(String username) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Account account = null;

        try {
            conn = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
            String sql = "SELECT * FROM account WHERE username = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, username);
            rs = stmt.executeQuery();

            if (rs.next()) {
                account = new Account();
                account.setId(rs.getInt("id"));
                account.setUsername(rs.getString("username"));
                account.setPassword(rs.getString("password"));
                account.setSalt(rs.getString("salt"));
                account.setEmail(rs.getString("email"));
                account.setPhone(rs.getString("phone"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (stmt != null) {
                    stmt.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return account;
    }

}
