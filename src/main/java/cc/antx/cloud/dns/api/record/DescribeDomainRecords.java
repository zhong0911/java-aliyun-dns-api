package cc.antx.cloud.dns.api.record;

import cc.antx.cloud.dns.utils.Output;
import com.alibaba.fastjson.JSONObject;
import com.aliyun.alidns20150109.Client;
import com.aliyun.alidns20150109.models.*;
import com.aliyun.tea.TeaException;
import com.aliyun.teaopenapi.models.Config;
import com.aliyun.teautil.models.RuntimeOptions;

public class DescribeDomainRecords {
    public static com.aliyun.alidns20150109.Client createClient(String accessKeyId, String accessKeySecret) throws Exception {
        Config config = new Config()
                .setAccessKeyId(accessKeyId)
                .setAccessKeySecret(accessKeySecret);
        config.endpoint = "alidns.cn-hangzhou.aliyuncs.com";
        return new Client(config);
    }

    public static JSONObject describeDomainRecords(JSONObject params) throws Exception {
        Client client = DescribeDomainRecords.createClient(System.getenv("ALIBABA_CLOUD_ACCESS_KEY_ID"), System.getenv("ALIBABA_CLOUD_ACCESS_KEY_SECRET"));
        DescribeDomainRecordsRequest describeDomainRecordsRequest = DescribeDomainRecordsRequest.build(params);
        RuntimeOptions runtime = new RuntimeOptions();
        try {
            DescribeDomainRecordsResponse response = client.describeDomainRecordsWithOptions(describeDomainRecordsRequest, runtime);
            DescribeDomainRecordsResponseBody responseBody = response.getBody();
            JSONObject info = new JSONObject(responseBody.getDomainRecords().toMap());
            info.put("pageNumber", responseBody.getPageNumber());
            info.put("pageSize", responseBody.getPageSize());
            return Output.success(info);
        } catch (TeaException error) {
            String code = error.getCode();
            String message = error.getMessage();
            return Output.error(code, message);
        }
    }
}
