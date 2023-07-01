package cc.antx.cloud.dns.mapper;

import cc.antx.cloud.dns.entity.DNSDomain;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;

import static cc.antx.cloud.dns.mapper.Configuration.*;

public class DNSDomainMapper {

    public static DNSDomain getDnsDomainByUid(Integer userId) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        DNSDomain dns = null;
        try {
            conn = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
            String sql = "SELECT * FROM dns_domain WHERE user_id=?";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, userId);
            rs = stmt.executeQuery();
            JSONArray domains = new JSONArray();
            dns = new DNSDomain();
            dns.setUserId(userId);
            while (rs.next()) {
                JSONObject info = new JSONObject(true);
                info.put("DnsDomainId", rs.getInt("dns_domain_id"));
                info.put("DomainName", rs.getString("domain_name"));
                domains.add(info);
            }
            dns.setDomains(domains);
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
        return dns;
    }


    public static void main(String[] args) {
        System.out.println(getDnsDomainByUid(1));
    }
}
