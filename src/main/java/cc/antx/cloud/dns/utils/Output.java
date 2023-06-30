package cc.antx.cloud.dns.utils;

import com.alibaba.fastjson.JSONObject;

public class Output {

    public static JSONObject success(String message) {
        JSONObject json = new JSONObject(true);
        json.put("success", true);
        json.put("status", 200);
        json.put("message", message);
        return json;
    }

    public static JSONObject success(JSONObject info) {
        JSONObject json = success("Operation succeeded");
        json.put("info", info);
        return json;
    }

    public static JSONObject success(String message, JSONObject info) {
        JSONObject json = success(message);
        json.put("info", info);
        return json;
    }

    public static JSONObject error(String code , String message) {
        JSONObject json = new JSONObject(true);
        json.put("success", false);
        json.put("status", 400);
        json.put("code", code);
        json.put("message", message);
        return json;
    }


    public static JSONObject error(String message) {
        JSONObject json = new JSONObject(true);
        json.put("success", false);
        json.put("status", 400);
        json.put("message", message);
        return json;
    }

    public static JSONObject error(JSONObject info) {
        JSONObject json = success("Operation failed");
        json.put("info", info);
        return json;
    }

    public static JSONObject error(String message, JSONObject info) {
        JSONObject json = success(message);
        json.put("info", info);
        return json;
    }

    public static void main(String[] args) {
        System.out.println(success(success("sdf")));
    }
}
