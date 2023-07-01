function describeDomains(params) {
    return postAPI("describeDomains", params)
}

function addDomain(params) {
    return postAPI("addDomain", params);
}

function deleteDomain(params) {
    return postAPI("deleteDomain", params);
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