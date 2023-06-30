package cc.antx.cloud.dns.api.domain;

import cc.antx.cloud.dns.api.Common;
import cc.antx.cloud.dns.utils.Output;
import com.alibaba.fastjson.JSONObject;
import com.aliyun.alidns20150109.Client;
import com.aliyun.alidns20150109.models.*;
import com.aliyun.tea.TeaException;
import com.aliyun.teautil.models.RuntimeOptions;

public class DescribeDomains {
    /**
     * 根据传入域名获取域名当前的NS服务器列表，以及NS是否属于阿里云解析管理
     * 必填参数: domainName
     *
     * @return 删除结果
     */
    public static JSONObject describeDomains(JSONObject params) {
        try {
            Client client = Common.createClient(System.getenv("ALIBABA_CLOUD_ACCESS_KEY_ID"), System.getenv("ALIBABA_CLOUD_ACCESS_KEY_SECRET"));
            DescribeDomainsRequest describeDomainsRequest = DescribeDomainsRequest.build(params);
            RuntimeOptions runtime = new RuntimeOptions();
            DescribeDomainsResponse response = client.describeDomainsWithOptions(describeDomainsRequest, runtime);
            DescribeDomainsResponseBody responseBody = response.getBody();
            JSONObject info = new JSONObject(true);
            info.putAll(responseBody.toMap());
            return Output.success(info);
        } catch (TeaException error) {
            String code = error.getCode();
            String message = error.getMessage();
            return Output.error(code, message);
        } catch (Exception exception) {
            return Output.error(500, "ServerError", "Server Error");
        }
    }
}
