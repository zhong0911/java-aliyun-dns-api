function describeDomains(params) {
    return postAPI("describeDomains", params)
}

function addDomain(params) {
    return postAPI("addDomain", params);
}

function deleteDomain(params) {
    return postAPI("deleteDomain", params);
}

function describeDomainNs(params) {
    return postAPI("describeDomainNs", params);
}


let _result = {};

function postAPI(action, params) {
    params.action = action;
    $.ajax({
        url: "/api/domain",
        data: params,
        dataType: "json",
        async: false,
        success: function (data) {
            _result = data;
            console.log(data);
        }
    });
    return _result;
}
/*
 <thead>
                        <tr>
                            <th>域名</th>
                            <th>标签</th>
                            <th>记录数</th>
                            <th>DNS服务器地址</th>
                            <th>付费版本</th>
                            <th>操作</th>
                        </tr>
                        </thead>
 */

/*

 <tr :id="item['DomainName'].replace('.', '-_')">
                                    <td><a class="text-decoration-none"
                                           :href="'/dns/record/' + item['DomainName']"><small>{{ item['DomainName']}}</small></a>
                                    </td>
                                    <td>
                                        <small><a class="text-decoration-none" href="javascript:void(0)"><img
                                                src="https://image.antx.cc/svg/tag.svg" alt="" width="16"
                                                height="16"></a></small>
                                    </td>
                                    <td>
                                        <small>{{ item['RecordCount'] }}</small>
                                    </td>
                                    <td>
                                        <small v-html="checkNS(item['DomainName'])"></small>
                                    </td>
                                    <td>
                                        <small>{{ item['VersionCode'] === "mianfei" ? '免费版' : '付费版' }}</small>
                                    </td>
                                    <td>
                                        <a class="text-decoration-none small"
                                           :href="'/dns/record/' + item['DomainName']">解析设置</a> |
                                        <a class="text-decoration-none small" href="javascript:void(0)"
                                           onclick="">域名检测</a> |
                                        <a class="text-decoration-none small link dropdown-toggle" type="button"
                                           data-bs-toggle="dropdown"><small>更多</small></a>
                                        <div class="dropdown-menu">
                                            <a class="text-decoration-none dropdown-item link small"
                                               href="javascript:void(0)"
                                               @click="upgradeDomain(item['DomainName'])"><small>升级</small></a>
                                            <a class="text-decoration-none dropdown-item link small"
                                               href="javascript:void(0)"
                                               @click="deleteDomain(item['DomainName'])"><small>删除</small></a>
                                        </div>
                                    </td>
                                </tr>
 */