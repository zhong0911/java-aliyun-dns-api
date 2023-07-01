package cc.antx.cloud.dns.entity;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

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
        JSONObject json = new JSONObject(true);
        json.put("UserId", this.userId);
        json.put("Domains", this.domains);
        return json.toJSONString();
    }

    public Map toMap() {
        return new HashMap<>(JSON.parseObject(toString()));
    }

    public JSONObject toJSONObject() {
        JSONObject jsonObject = new JSONObject(true);
        jsonObject.putAll(toMap());
        return jsonObject;
    }
}
