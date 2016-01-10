/**
 * Created by xubitao on 1/9/16.
 */

var Contracts = {
    init: function (_link) {
        Contracts.loadContracts(_link);
        Contracts.setValidator();
        Contracts.bindContractCreateButton(_link);
    },
    bindContractCreateButton: function (_link) {
        $('#create-contract-button').click(function () {
            $('#contract-modal-title').html("创建Contract");
            $('#create-contract-form').data('bootstrapValidator').resetForm(true);
            Contracts.bindResetSaveButton(_link);
            $('#createOrUpdateContractsModel').modal();
        });
        $('#contractsModel').modal();

    },
    bindResetSaveButton: function (_link) {
        $('#contract-resetBtn').unbind();
        $('#contract-resetBtn').click(function () {
            $('#create-contract-form').data('bootstrapValidator').resetForm(true);
        });
        $("#contract-save-button").unbind();
        $("#contract-save-button").click(function () {
            Contracts.create(_link);
        });
    },
    setValidator: function () {
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
    create: function (_link) {
        Contracts.save(_link, _link, HttpType.POST);
    },
    update: function (_link, _contractLink) {
        Contracts.save(_link, _contractLink, HttpType.PUT);
    },
    save: function (_selfLink, _contractLink, _type) {
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
            url: _selfLink,
            type: _type,
            data: contract,
            success: function () {
                Contracts.loadContracts(_contractLink);
                $('#createOrUpdateContractsModel').modal('hide');
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
                Contracts.loadContracts(_contractsLink);
            }
        })
    });
    $('#confirmDeleteModel').modal();
}
function openContractUpdateModal(_selfLink, _contractLink) {
    Racoon.restful({
        url: _selfLink,
        success: function (_data) {
            var contract = _data;
            $('#contract-modal-title').html("更新" + contract.name);

            $('#contract-name').val(contract.name);
            $('#contract-url').val(contract.url);
            $('#contract-request').val(contract.request);
            $('#contract-response').val(contract.response);
            $('#contract-desc').val(contract.desc);

            $("#contract-save-button"). unbind();
            $("#contract-save-button").click(function () {
                Contracts.update(_selfLink, _contractLink);
            });
            $('#createOrUpdateContractsModel').modal();
        }
    })
}
function contractsOptionsFormatter(_links) {
    var options = $("<div></div>");
    var span = $("<span class='button-dropdown' data-buttons='dropdown'></span>");
    var button = "<button class='button button-rounded'><i class='fa fa-bars'></i> 操作 </button>";
    var ul = $("<ul class='button-dropdown-list'></ul>");
    var updateLink = $("<li><a class='pirate-link' onclick='openContractUpdateModal(\"" + Racoon.getLink(_links, "self") + "\",\"" + Racoon.getLink(_links, "contracts") + "\")'>更新</a></li>");
    var deleteLink = $("<li><a class='pirate-link' onclick='openConfirmDeleteContractModal(\"" + Racoon.getLink(_links, "self") + "\",\"" + Racoon.getLink(_links, "contracts") + "\")'>删除</a></li>"
    );
    ul.append(updateLink);
    ul.append(deleteLink);

    span.append(button);
    span.append(ul);
    options.append(span);
    return options.html();
}