package cc.antx.cloud.dns.entity;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.util.ArrayList;

public class DNSDomain {
    private Integer userId;
    private JSONArray domains;


    public JSONArray getDomains() {
        return domains;
    }

    public void setDomains(JSONArray domains) {
        this.domains = domains;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }


    public String toString() {
        JSONObject json = new JSONObject();
        json.put("user_id", this.userId);
        json.put("domains", this.domains);
        return json.toJSONString();
    }
}
