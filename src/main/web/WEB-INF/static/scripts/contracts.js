/**
 * Created by xubitao on 1/9/16.
 */
function openContractModel(_link) {
    Racoon.restful({
        url: _link,
        success: function (_data) {
            var contracts = _data.contracts;
            $('#contracts-table').bootstrapTable('load', contracts);
        }
    });
    $('#create-contract-button').click(function () {
        $('#create-contract-form').data('bootstrapValidator').resetForm(true);
        $('#createContractsModel').modal();
    });
    $('#contractsModel').modal();
    $('#resetBtn').click(function () {
        $('#create-contract-form').data('bootstrapValidator').resetForm(true);
    });
    $('#create-contract-form').bootstrapValidator({
        live: 'enabled',
        fields: {
            name: {
                validators: {
                    notEmpty: {
                        message: '契约名称不可以为空'
                    }
                }
            },
            url: {
                validators: {
                    notEmpty: {
                        message: 'url不可以为空'
                    }
                }
            },
            request: {
                validators: {
                    notEmpty: {
                        message: '请求体不可以为空'
                    }
                }
            },
            response: {
                validators: {
                    notEmpty: {
                        message: '响应体不可以为空'
                    }
                }
            },
            desc: {}
        }
    });
}

function contractsOptionsFormatter(_links) {
    var options = $("<div></div>");
    var span = $("<span class='button-dropdown' data-buttons='dropdown'></span>");
    var button = "<button class='button button-rounded'><i class='fa fa-bars'></i> 操作 </button>";
    var ul = $("<ul class='button-dropdown-list'></ul>");
    var deleteLink = $("<li><a class='pirate-link' onclick='confirmDeleteOption(\"" + Racoon.getLink(_links, "self") + "\")'>删除</a></li>");
    var contractLink = $("<li><a class='pirate-link' onclick='openContractModel(\"" + Racoon.getLink(_links, "contracts") + "\")'>契约</a></li>");
    ul.append(deleteLink);
    ul.append(contractLink);

    span.append(button);
    span.append(ul);
    options.append(span);
    return options.html();
}