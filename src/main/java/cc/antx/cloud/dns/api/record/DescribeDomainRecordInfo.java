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
            info.put("recordId", responseBody.getRecordId());
            info.put("domainId", responseBody.getDomainId());
            info.put("domainName", responseBody.getDomainName());
            info.put("groupId", responseBody.getGroupId());
            info.put("groupName", responseBody.getGroupName());
            info.put("line", responseBody.getLine());
            info.put("locked", responseBody.getLocked());
            info.put("priority", responseBody.getPriority());
            info.put("punyCode", responseBody.getPunyCode());
            info.put("RR", responseBody.getRR());
            info.put("remark", responseBody.getRemark());
            info.put("requestId", responseBody.getRequestId());
            info.put("status", responseBody.getStatus());
            info.put("TTL", responseBody.getTTL());
            info.put("type", responseBody.getType());
            info.put("value", responseBody.getValue());
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
