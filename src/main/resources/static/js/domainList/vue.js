let domainsInfo = describeDomains({});
domainsInfo = domainsInfo['success'] ? domainsInfo['info']['Domains']['Domain'] : {};

let ns;
let domains = [];
if (domainsInfo !== {}) {
    for (let i in domainsInfo) {
        domains[i] = domainsInfo[i]['DomainName'];
    }
}

const App = {
    data() {
        return {
            domainsInfo: domainsInfo,
            ns: ns,
            domainInfo: {}
        }
    },
    methods: {
        getTableRow(domainInfo) {
            getTableRow(domainInfo);
        },
        checkNS(domainName) {
            if (checkDomainNs(domainName)) {
                return `<img src="https://image.antx.cc/svg/right-ok.svg" alt="" width="16" height="16"> <span class="text-danger">正常</span>`;
            } else {
                return `<img src="https://image.antx.cc/svg/warning.svg" alt="" width="16" height="16"> <span class="text-warning">未使用系统分配DNS地址</span>`;
            }
        },
        checkDomain() {
            return checkDomain();
        },
        addDomain() {
            $("#add-domain-modal").modal("show").on('hidden.bs.modal', function () {
                $("#input-domain-name-error").html("");
                $('#input-domain-name').val('');
            });
        },
        submitAddDomain() {
            let res = submitAddDomain();
            if (res) {
                this.domainInfo = ({
                    DomainName: res['domainName'],
                    Tags: {Tag: []},
                    RecordCount: 0,
                    DnsServers: {DnsServer: []},
                    VersionCode: "mianfei"
                });
                $("#tbody").prepend(getTableRow(this.domainInfo))
            }

        },
        deleteDomain(domainName) {
            $("#delete-domain-name-text").text(domainName);
            $("#delete-domain-modal").modal("show").on('hidden.bs.modal', function () {
                $('form input').val('');
            });
        }, submitDeleteDomain() {
            submitDeleteDomain();
        },
    }
}

Vue.createApp(App).mount('#app');

function checkDomain() {
    let domainName = $("#input-domain-name").val();
    if (domainName) {
        if (isDomainName(domainName)) {
            $("#input-input-domain-name-error").html('');
            return true;
        } else {
            $("#input-input-domain-name-error").html(`${error} 域名格式错误`);
            return false;
        }
    } else {
        $("#input-input-domain-name-error").html(`${error} 域名不可为空`);
        return false;
    }
}

function submitAddDomain() {
    if (checkDomain()) {
        let domainName = $("#input-domain-name").val();
        let data = addDomain({domainName: domainName});
        if (data['success']) {
            $prompt.success("添加成功");
            $("#add-domain-modal").modal("hide");
            return {success: true, domainName: domainName};
        } else {
            let html;
            switch (data['code']) {
                case"InvalidDomainName.Format" :
                    html = '域名格式错误';
                    break;
                case"InvalidDomainName.Duplicate" :
                    html = '域名已存在权威域名列表中，无需再次添加，请在权威域名列表中搜索并查看。';
                    break;
                case "InvalidDomainName.Suffix":
                    html = '很抱歉，云解析不支持该域名后缀解析，建议您使用常规后缀。';
                    break;
                case "DomainAddedByOthers":
                    html = '该域名在其他账号下已存在，无法添加';
                    break;
                case "InvalidDomainName.Unregistered":
                    html = `该域名未注册，<a href="https://domain.buy.cloud.antx.cc/?doamin=${domainName}">注册域名</a>。`;
                    break;
                default:
                    html = '';
            }
            if (html) $("#input-domain-name-error").html(`${error} ${html}`);
            else $prompt.error(`添加失败, 原因: ${data['message']}, Code: ${data['code']}`);
            return {success: false, domainName: domainName};
        }
    }
}

function submitDeleteDomain() {
    let domainName = $("#delete-domain-name-text").text();
    let data = deleteDomain({domainName: domainName});
    if (data['success']) {
        $prompt.success('删除成功');
        $("#" + domainName.replace(".", "-_")).remove();
        $("#delete-domain-modal").modal('hide');
    } else {
        $prompt.error('删除失败');
    }
}

function checkDomainNs(domainName) {
    let res = describeDomainNs({domainName: domainName});
    if (res['success']) {
        let expectDnsServers = res['info']['ExpectDnsServers']['ExpectDnsServer'];
        let dnsServers = res['info']['DnsServers']['DnsServer'];
        return expectDnsServers.every(server => dnsServers.includes(server));
    } else return false;
}


function getTableRow(domainInfo) {
    console.log(domainInfo);
    return `
    <tr id="${domainInfo['DomainName'].replace('.', '-_')}">
      <td><a class="text-decoration-none" href="/dns/record/${domainInfo['DomainName']}"><small>${domainInfo['DomainName']}</small></a></td>
      <td><small><a class="text-decoration-none" href="javascript:void(0)"><img src="https://image.antx.cc/svg/tag.svg" alt="" width="16" height="16"></a></small></td>
      <td><small>${domainInfo['RecordCount']}</small></td>
      <td><small v-html="checkNS(domainInfo['DomainName'])"></small></td>
      <td><small>${domainInfo['VersionCode'] === "mianfei" ? '免费版' : '付费版'}</small></td>
      <td>
        <a class="text-decoration-none small" href="/dns/record/${domainInfo['DomainName']}">解析设置</a> |
        <a class="text-decoration-none small" href="javascript:void(0)" onclick="">域名检测</a> |
        <a class="text-decoration-none small link dropdown-toggle" type="button" data-bs-toggle="dropdown"><small>更多</small></a>
        <div class="dropdown-menu">
          <a class="text-decoration-none dropdown-domainInfo link small" href="javascript:void(0)" @click="upgradeDomain(${domainInfo['DomainName']})"><small>升级</small></a>
          <a class="text-decoration-none dropdown-domainInfo link small" href="javascript:void(0)" @click="deleteDomain(domainInfo['DomainName'])"><small>删除</small></a>
        </div>
      </td>
    </tr>
  `;
}