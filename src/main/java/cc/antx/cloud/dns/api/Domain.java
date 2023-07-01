package cc.antx.cloud.dns.api;

import cc.antx.cloud.dns.utils.Output;
import com.alibaba.fastjson.JSONObject;
import com.aliyun.alidns20150109.Client;
import com.aliyun.alidns20150109.models.*;
import com.aliyun.tea.TeaException;
import com.aliyun.teautil.models.RuntimeOptions;

public class Domain {
    /**
     * 根据传入参数添加域名
     * 必填参数: domainName
     *
     * @return 添加结果
     */
    public static JSONObject addDomain(JSONObject params) {
        try {
            Client client = Common.createClient(System.getenv("ALIBABA_CLOUD_ACCESS_KEY_ID"), System.getenv("ALIBABA_CLOUD_ACCESS_KEY_SECRET"));
            AddDomainRequest addDomainRequest = AddDomainRequest.build(params);
            RuntimeOptions runtime = new RuntimeOptions();
            AddDomainResponse response = client.addDomainWithOptions(addDomainRequest, runtime);
            AddDomainResponseBody responseBody = response.getBody();
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

    /**
     * 根据传入参数删除域名
     * 必填参数: domainName
     *
     * @return 删除结果
     */
    public static JSONObject deleteDomain(JSONObject params) {
        try {
            Client client = Common.createClient(System.getenv("ALIBABA_CLOUD_ACCESS_KEY_ID"), System.getenv("ALIBABA_CLOUD_ACCESS_KEY_SECRET"));
            DeleteDomainRequest deleteDomainRequest = DeleteDomainRequest.build(params);
            RuntimeOptions runtime = new RuntimeOptions();
            DeleteDomainResponse response = client.deleteDomainWithOptions(deleteDomainRequest, runtime);
            DeleteDomainResponseBody responseBody = response.getBody();
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

    /**
     * 根据传入参数查询指定域名的信息
     * 必填参数: domainName
     *
     * @return 删除结果
     */
    public static JSONObject describeDomainInfo(JSONObject params) {
        try {
            Client client = Common.createClient(System.getenv("ALIBABA_CLOUD_ACCESS_KEY_ID"), System.getenv("ALIBABA_CLOUD_ACCESS_KEY_SECRET"));
            DescribeDomainInfoRequest describeDomainInfoRequest = DescribeDomainInfoRequest.build(params);
            RuntimeOptions runtime = new RuntimeOptions();
            DescribeDomainInfoResponse response = client.describeDomainInfoWithOptions(describeDomainInfoRequest, runtime);
            DescribeDomainInfoResponseBody responseBody = response.getBody();
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

    /**
     * 根据传入域名获取域名当前的NS服务器列表，以及NS是否属于阿里云解析管理
     * 必填参数: domainName
     *
     * @return 删除结果
     */
    public static JSONObject describeDomainNs(JSONObject params) {
        try {
            Client client = Common.createClient(System.getenv("ALIBABA_CLOUD_ACCESS_KEY_ID"), System.getenv("ALIBABA_CLOUD_ACCESS_KEY_SECRET"));
            DescribeDomainNsRequest describeDomainNsRequest = DescribeDomainNsRequest.build(params);
            RuntimeOptions runtime = new RuntimeOptions();
            DescribeDomainNsResponse response = client.describeDomainNsWithOptions(describeDomainNsRequest, runtime);
            DescribeDomainNsResponseBody responseBody = response.getBody();
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

    /**
     * 根据传入参数查询该用户的域名列表
     * 无必填参数
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
