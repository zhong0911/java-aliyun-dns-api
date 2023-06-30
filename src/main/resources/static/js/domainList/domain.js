let _describeDomainsRes = {};

function describeDomains(params) {
    params.action = "describeDomains";
    $.ajax({
        url: "/api/domain",
        data: params,
        dataType: "json",
        async: false,
        success: function (data) {
            _describeDomainsRes = data;
            console.log(data);
        }
    });
    return _describeDomainsRes;
}