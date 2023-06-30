package cc.antx.cloud.dns.api.record;

import cc.antx.cloud.dns.api.Common;
import cc.antx.cloud.dns.utils.Output;
import com.alibaba.fastjson.JSONObject;
import com.aliyun.alidns20150109.Client;
import com.aliyun.alidns20150109.models.DescribeDomainRecordInfoRequest;
import com.aliyun.alidns20150109.models.DescribeDomainRecordInfoResponse;
import com.aliyun.alidns20150109.models.DescribeDomainRecordInfoResponseBody;
import com.aliyun.tea.TeaException;
import com.aliyun.teautil.models.RuntimeOptions;

public class DescribeDomainRecordInfo {

    /**
     * 获取解析记录的详细信息
     * 必填参数: recordId
     *
     * @param params 参数
     * @return 记录信息
     */
    public static JSONObject describeDomainRecordInfo(JSONObject params) {
        try {
            Client client = Common.createClient(System.getenv("ALIBABA_CLOUD_ACCESS_KEY_ID"), System.getenv("ALIBABA_CLOUD_ACCESS_KEY_SECRET"));
            DescribeDomainRecordInfoRequest describeDomainRecordInfoRequest = DescribeDomainRecordInfoRequest.build(params);
            RuntimeOptions runtime = new RuntimeOptions();
            DescribeDomainRecordInfoResponse response = client.describeDomainRecordInfoWithOptions(describeDomainRecordInfoRequest, runtime);
            DescribeDomainRecordInfoResponseBody responseBody = response.getBody();
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
