package cc.antx.cloud.dns.api;

import cc.antx.cloud.dns.utils.Output;
import com.alibaba.fastjson.JSONObject;
import com.aliyun.alidns20150109.Client;
import com.aliyun.alidns20150109.models.*;
import com.aliyun.tea.TeaException;
import com.aliyun.teautil.models.RuntimeOptions;

public class Record {

    /**
     * 根据传入参数添加解析记录
     * 必填参数: domainName, RR, type, value
     *
     * @param params 参数
     * @return 记录添加结果
     */
    public static JSONObject addDomainRecord(JSONObject params) {
        try {
            Client client = Common.createClient(System.getenv("ALIBABA_CLOUD_ACCESS_KEY_ID"), System.getenv("ALIBABA_CLOUD_ACCESS_KEY_SECRET"));
            AddDomainRecordRequest addDomainRecordRequest = AddDomainRecordRequest.build(params);
            RuntimeOptions runtime = new RuntimeOptions();
            AddDomainRecordResponse response = client.addDomainRecordWithOptions(addDomainRecordRequest, runtime);
            AddDomainRecordResponseBody responseBody = response.getBody();
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
         * 根据传入参数删除解析记录
         * 必填参数: recordId
         *
         * @param params 参数
         * @return 记录删除结果
         */
        public static JSONObject deleteDomainRecord(JSONObject params) {
            try {
                Client client = Common.createClient(System.getenv("ALIBABA_CLOUD_ACCESS_KEY_ID"), System.getenv("ALIBABA_CLOUD_ACCESS_KEY_SECRET"));
                DeleteDomainRecordRequest deleteDomainRecordRequest = DeleteDomainRecordRequest.build(params);
                RuntimeOptions runtime = new RuntimeOptions();
                DeleteDomainRecordResponse response = client.deleteDomainRecordWithOptions(deleteDomainRecordRequest, runtime);
                DeleteDomainRecordResponseBody responseBody = response.getBody();
                JSONObject info = new JSONObject(true);
                info.putAll(responseBody.toMap());
                return Output.success(info);
            } catch (TeaException teaException) {
                String code = teaException.getCode();
                String message = teaException.getMessage();
                return Output.error(code, message);
            } catch (Exception exception) {
                return Output.error(500, "ServerError", "Server Error");
            }
        }    /**
     * 根据传入参数删除主机记录对应的解析记录
     * 必填参数: domainName, RR
     *
     * @param params 参数
     * @return 记录删除结果
     */
    public static JSONObject deleteSubDomainRecords(JSONObject params) {
        try {
            Client client = Common.createClient(System.getenv("ALIBABA_CLOUD_ACCESS_KEY_ID"), System.getenv("ALIBABA_CLOUD_ACCESS_KEY_SECRET"));
            DeleteSubDomainRecordsRequest deleteSubDomainRecordsRequest = DeleteSubDomainRecordsRequest.build(params);
            RuntimeOptions runtime = new RuntimeOptions();
            DeleteSubDomainRecordsResponse response = client.deleteSubDomainRecordsWithOptions(deleteSubDomainRecordsRequest, runtime);
            DeleteSubDomainRecordsResponseBody responseBody = response.getBody();
            JSONObject info = new JSONObject(true);
            info.putAll(responseBody.toMap());
            return Output.success(info);
        } catch (TeaException teaException) {
            String code = teaException.getCode();
            String message = teaException.getMessage();
            return Output.error(code, message);
        } catch (Exception exception) {
            return Output.error(500, "ServerError", "Server Error");
        }
    } /**
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
    } /**
     * 获取解析记录列表
     * 必填参数: domainName
     *
     * @param params 参数
     * @return 解析记录列表
     */
    public static JSONObject describeDomainRecords(JSONObject params) {
        try {
            Client client = Common.createClient(System.getenv("ALIBABA_CLOUD_ACCESS_KEY_ID"), System.getenv("ALIBABA_CLOUD_ACCESS_KEY_SECRET"));
            DescribeDomainRecordsRequest describeDomainRecordsRequest = DescribeDomainRecordsRequest.build(params);
            RuntimeOptions runtime = new RuntimeOptions();
            DescribeDomainRecordsResponse response = client.describeDomainRecordsWithOptions(describeDomainRecordsRequest, runtime);
            DescribeDomainRecordsResponseBody responseBody = response.getBody();
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
     * 根据传入参数获取域名的解析操作日志
     * 必填参数: domainName
     *
     * @param params 参数
     * @return 解析操作日志
     */
    public static JSONObject describeRecordLogs(JSONObject params) {
        try {
            Client client = Common.createClient(System.getenv("ALIBABA_CLOUD_ACCESS_KEY_ID"), System.getenv("ALIBABA_CLOUD_ACCESS_KEY_SECRET"));
            DescribeRecordLogsRequest describeRecordLogsRequest = DescribeRecordLogsRequest.build(params);
            RuntimeOptions runtime = new RuntimeOptions();
            DescribeRecordLogsResponse response = client.describeRecordLogsWithOptions(describeRecordLogsRequest, runtime);
            DescribeRecordLogsResponseBody responseBody = response.getBody();
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
    } /**
     * 根据传入参数获取某个固定子域名的所有解析记录列表
     * 必填参数: subDomain
     *
     * @param params 参数
     * @return 子域名解析记录列表
     */
    public static JSONObject describeSubDomainRecords(JSONObject params) {
        try {
            Client client = Common.createClient(System.getenv("ALIBABA_CLOUD_ACCESS_KEY_ID"), System.getenv("ALIBABA_CLOUD_ACCESS_KEY_SECRET"));
            DescribeSubDomainRecordsRequest describeSubDomainRecordsRequest = DescribeSubDomainRecordsRequest.build(params);
            RuntimeOptions runtime = new RuntimeOptions();
            DescribeSubDomainRecordsResponse response = client.describeSubDomainRecordsWithOptions(describeSubDomainRecordsRequest, runtime);
            DescribeSubDomainRecordsResponseBody responseBody = response.getBody();
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
    }/**
     * 生成txt记录。用于域名、子域名找回、添加子域名验证、批量找回等功能
     * 必填参数: domainName, type
     * 说明: type的值: ADD_SUB_DOMAIN, RETRIEVAL
     *
     * @param params 参数
     * @return 返回结果
     */
    public static JSONObject getTxtRecordForVerify(JSONObject params) {
        try {
            Client client = Common.createClient(System.getenv("ALIBABA_CLOUD_ACCESS_KEY_ID"), System.getenv("ALIBABA_CLOUD_ACCESS_KEY_SECRET"));
            GetTxtRecordForVerifyRequest getTxtRecordForVerifyRequest = GetTxtRecordForVerifyRequest.build(params);
            RuntimeOptions runtime = new RuntimeOptions();
            GetTxtRecordForVerifyResponse response = client.getTxtRecordForVerifyWithOptions(getTxtRecordForVerifyRequest, runtime);
            GetTxtRecordForVerifyResponseBody responseBody = response.getBody();
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
    }/**
     * 根据传入参数设置解析记录状态
     * 必填参数: recordId, status
     *
     * @param params 参数
     * @return 修改结果
     */
    public static JSONObject setDomainRecordStatus(JSONObject params) {
        try {
            Client client = Common.createClient(System.getenv("ALIBABA_CLOUD_ACCESS_KEY_ID"), System.getenv("ALIBABA_CLOUD_ACCESS_KEY_SECRET"));
            SetDomainRecordStatusRequest setDomainRecordStatusRequest = SetDomainRecordStatusRequest.build(params);
            RuntimeOptions runtime = new RuntimeOptions();
            SetDomainRecordStatusResponse response = client.setDomainRecordStatusWithOptions(setDomainRecordStatusRequest, runtime);
            SetDomainRecordStatusResponseBody responseBody = response.getBody();
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
    } /**
     * 修改域名解析记录
     * 必填参数: recordId, RR, type, value
     *
     * @param params 参数
     * @return 修改结果
     */
    public static JSONObject updateDomainRecord(JSONObject params) {
        try {
            Client client = Common.createClient(System.getenv("ALIBABA_CLOUD_ACCESS_KEY_ID"), System.getenv("ALIBABA_CLOUD_ACCESS_KEY_SECRET"));
            UpdateDomainRecordRequest updateDomainRecordRequest = UpdateDomainRecordRequest.build(params);
            RuntimeOptions runtime = new RuntimeOptions();
            UpdateDomainRecordResponse response = client.updateDomainRecordWithOptions(updateDomainRecordRequest, runtime);
            UpdateDomainRecordResponseBody responseBody = response.getBody();
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
    }/**
     * 修改域名解析记录的备注
     * 必填参数: recordId
     * 可选参数: remark
     *
     * @param params 参数
     * @return 修改结果
     */
    public static JSONObject updateDomainRecordRemark(JSONObject params) {
        try {
            Client client = Common.createClient(System.getenv("ALIBABA_CLOUD_ACCESS_KEY_ID"), System.getenv("ALIBABA_CLOUD_ACCESS_KEY_SECRET"));
            UpdateDomainRecordRemarkRequest updateDomainRecordRemarkRequest = UpdateDomainRecordRemarkRequest.build(params);
            RuntimeOptions runtime = new RuntimeOptions();
            UpdateDomainRecordRemarkResponse response = client.updateDomainRecordRemarkWithOptions(updateDomainRecordRemarkRequest, runtime);
            UpdateDomainRecordRemarkResponseBody responseBody = response.getBody();
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
