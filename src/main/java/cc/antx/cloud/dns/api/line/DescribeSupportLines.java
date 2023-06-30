package cc.antx.cloud.dns.api.line;

import cc.antx.cloud.dns.api.Common;
import cc.antx.cloud.dns.utils.Output;
import com.aliyun.tea.TeaException;
import com.alibaba.fastjson.JSONObject;
import com.aliyun.alidns20150109.Client;
import com.aliyun.alidns20150109.models.*;
import com.aliyun.teautil.models.RuntimeOptions;

public class DescribeSupportLines {
    /**
     * 查询云解析支持的所有线路列表
     * 无必填参数
     *
     * @return 线路列表
     */
    public static JSONObject describeSupportLines(JSONObject params) {
        try {
            Client client = Common.createClient(System.getenv("ALIBABA_CLOUD_ACCESS_KEY_ID"), System.getenv("ALIBABA_CLOUD_ACCESS_KEY_SECRET"));
            DescribeSupportLinesRequest describeSupportLinesRequest = DescribeSupportLinesRequest.build(params);
            RuntimeOptions runtime = new RuntimeOptions();
            DescribeSupportLinesResponse response = client.describeSupportLinesWithOptions(describeSupportLinesRequest, runtime);
            DescribeSupportLinesResponseBody responseBody = response.getBody();
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
