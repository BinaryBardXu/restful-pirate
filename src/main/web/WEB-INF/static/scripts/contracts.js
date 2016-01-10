/**
 * Created by xubitao on 1/9/16.
 */

var Contracts = {
    init: function (_link) {
        Contracts.loadContracts(_link);
        Contracts.initModal(_link);

    },
    initModal: function (_link) {
        $('#create-contract-button').click(function () {
            $('#create-contract-form').data('bootstrapValidator').resetForm(true);
            $('#createContractsModel').modal();
        });
        $('#contractsModel').modal();
        $('#contract-resetBtn').click(function () {
            $('#create-contract-form').data('bootstrapValidator').resetForm(true);
        });
        $("#contract-create-button").click(function () {
            Contracts.save(_link);
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
    },
    loadContracts: function (_link) {
        $('#contracts-table').bootstrapTable('showLoading');
        Racoon.restful({
            url: _link,
            success: function (_data) {
                var contracts = _data.contracts;
                $('#contracts-table').bootstrapTable('load', contracts);
                $('#contracts-table').bootstrapTable('hideLoading');
            }
        });
    },
    refresh: function (_link) {
        $('#contracts-table').bootstrapTable('showLoading');
        Racoon.restful({
            url: _link,
            success: function (_data) {
                var contracts = _data.contracts;
                $('#contracts-table').bootstrapTable('refresh', contracts);
                $('#contracts-table').bootstrapTable('hideLoading');
            }
        });
    },
    save: function (_link) {
        var validResult = $('#create-contract-form').data('bootstrapValidator').validate().isValid();
        if (!validResult) {
            return;
        }
        var contract = {};
        contract.name = $('#contract-name').val();
        contract.url = $('#contract-url').val();
        contract.request = $('#contract-request').val();
        contract.response = $('#contract-response').val();
        contract.desc = $('#contract-desc').val();
        Racoon.restful({
            url: _link,
            type: "POST",
            data: contract,
            success: function () {
                Contracts.init(_link);
                $('#createContractsModel').modal('hide');
            }
        });
    }
};
function openConfirmDeleteContractModal(_selfLink, _contractsLink) {
    $('#modal-body').html("确定要删除?");
    $('#delete-button').unbind();
    $('#delete-button').click(function () {
        Racoon.restful({
            url: _selfLink,
            type: 'DELETE',
            success: function () {
                $('#confirmDeleteModel').modal('hide');
                Contracts.init(_contractsLink);
            }
        })
    });
    $('#confirmDeleteModel').modal();
}
function contractsOptionsFormatter(_links) {
    var options = $("<div></div>");
    var span = $("<span class='button-dropdown' data-buttons='dropdown'></span>");
    var button = "<button class='button button-rounded'><i class='fa fa-bars'></i> 操作 </button>";
    var ul = $("<ul class='button-dropdown-list'></ul>");
    var deleteLink = $("<li><a class='pirate-link' onclick='openConfirmDeleteContractModal(\"" + Racoon.getLink(_links, "self") + "\",\"" + Racoon.getLink(_links, "contracts") + "\")'>删除</a></li>"
    );
    ul.append(deleteLink);

    span.append(button);
    span.append(ul);
    options.append(span);
    return options.html();
}