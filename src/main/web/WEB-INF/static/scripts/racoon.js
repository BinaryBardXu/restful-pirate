/**
 * Created by xubitao on 1/2/16.
 */

var host = "http://localhost:8080";
var Racoon = {
    restful: function (_options) {
        $.ajax({
            type: _options.type || "GET",
            url: _options.url,
            dataType: _options.dataType || "json",
            async: _options.async || true,
            data: JSON.stringify(_options.data),
            contentType: "application/json",
            before: function () {
                if (_options.before != undefined) {
                    _options.before();
                }
            },
            success: function (_data) {
                toastr.clear();
                toastr.remove();
                if (this.type == "GET") {
                    toastr.success(' 加载成功!');
                }
                if (this.type == "POST") {
                    toastr.success(' 创建成功!');
                }
                if (this.type == "DELETE") {
                    toastr.success(' 删除成功!');
                }
                if (this.type == "UPDATE") {
                    toastr.success(' 修改成功!');
                }

                _options.success(_data);
            },
            complete: function (XHR, TS) {
                if (XHR.status == 500) {
                    var httpErrorModel = $("#httpErrorModel");
                    if (httpErrorModel.html() == undefined) {
                        loadErrorModel(XMLHttpRequest.responseJSON.message)
                    }
                    $('#errorModel').modal();
                }
                if (XHR.status == 201) {
                    _options.success();
                }
            }
        });
    },
    getLink: function (_links, _rel) {
        for (var index in _links) {
            var link = _links[index];
            if (link.rel == _rel) {
                return link.href;
            }
        }
    }
};

function loadErrorModel(_message) {
    Racoon.restful({
        url: host + "/static/html/errorModel.html",
        dataType: "html",
        async: false,
        success: function (_data) {
            var errorModel = $(_data);
            errorModel.find("#errorMessage").html(_message);
            $("body").append(errorModel);
            $('#errorModel').modal();
        }
    })
}
