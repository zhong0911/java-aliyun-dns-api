let domains = describeDomains({});
domains = domains['success'] ? domains['info']['Domains']['Domain'] : {};


const App = {
    data() {
        return {
            domains: domains,
        }
    },
    methods: {
        checkDomain() {
            checkDomain();
        },
        addDomain() {
            $("#add-domain-modal").modal("show").on('hidden.bs.modal', function () {
                $('form input').val('');
            });
        },
        submitAddDomain() {
            let res = submitAddDomain();
            if (res) {
                this.domains.unshift({
                    DomainName: res['domainName'],
                    Tags: {Tag: []},
                    RecordCount: 0,
                    DnsServers: {DnsServer: []},
                    VersionCode: "mianfei"
                });
            }
        },
        deleteDomain(domainName) {
            console.log(domainName)
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