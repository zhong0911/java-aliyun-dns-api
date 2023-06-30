package cc.antx.cloud.dns.api;

import com.aliyun.alidns20150109.Client;
import com.aliyun.teaopenapi.models.Config;

public class Common {
    public static com.aliyun.alidns20150109.Client createClient(String accessKeyId, String accessKeySecret) throws Exception {
        Config config = new Config()
                .setAccessKeyId(accessKeyId)
                .setAccessKeySecret(accessKeySecret);
        config.endpoint = "alidns.cn-hangzhou.aliyuncs.com";
        return new Client(config);
    }
}
