/**
 * Created by xubitao on 1/9/16.
 */

var Providers = {
    init: function () {
        this.bindCreateButton();
        this.loadProviders();
        this.bindProvidersRefreshButton();
    },
    loadProviders: function () {
        Racoon.restful({
            url: GlobalConfig.entrance,
            before: function () {
                $('#table').bootstrapTable('showLoading');
            },
            success: function (_data) {
                var providers = _data.providers;
                $('#table').bootstrapTable('load', providers);
                $('#table').bootstrapTable('hideLoading');
            }
        })
    },
    bindCreateButton: function () {
        $("#create-button-provider").unbind();
        $("#create-button-provider").click(function () {
            Providers.initModal(GlobalConfig.entrance);
        });
    },
    bindProvidersRefreshButton: function () {
        $("#providers-refresh-button").unbind();
        $("#providers-refresh-button").click(function () {
            Providers.loadProviders();
        });
    },
    initModal: function (_link) {
        $('#create-provider-form').bootstrapValidator({
            live: 'enabled',
            fields: {
                name: {
                    validators: {
                        notEmpty: {
                            message: 'Provider名称不可以为空'
                        }
                    }
                },
                version: {
                    validators: {
                        notEmpty: {
                            message: 'Provider的版本号不可以为空'
                        }
                    }
                },
                consumerKey: {
                    validators: {
                        notEmpty: {
                            message: 'consumerKey不可以为空'
                        }
                    }
                }
            }
        });
        $('#create-provider-form').data('bootstrapValidator').resetForm(true);
        $('#createProvidersModel').modal();
        $('#provider-resetBtn').click(function () {
            $('#create-provider-form').data('bootstrapValidator').resetForm(true);
        });
        $("#provider-create-button"). unbind();
        $("#provider-create-button").click(function () {
            Providers.save(_link);
        });
    },
    save: function (_link) {
        var validResult = $('#create-provider-form').data('bootstrapValidator').validate().isValid();
        if (!validResult) {
            return;
        }
        var provider = {};
        provider.name = $('#provider-name').val();
        provider.version = $('#provider-version').val();
        provider.consumerKey = $('#provider-consumerKey').val();
        Racoon.restful({
            url: _link,
            type: "POST",
            data: provider,
            success: function () {
                Providers.init();
                $('#createProvidersModel').modal('hide');
            }
        });
    }
};
function openConfirmDeleteModal(_link) {
    $('#delete-button').unbind();
    $('#delete-button').click(function () {
        Racoon.restful({
            url: _link,
            type: 'DELETE',
            success: function () {
                $('#confirmDeleteModel').modal('hide');
                Providers.loadProviders();
            }
        })
    });
    $('#confirmDeleteModel').modal();
}

function openContractsModal(_link) {
    Contracts.init(_link);
}
function optionsFormatter(_links) {
    var options = $("<div></div>");
    var span = $("<span class='button-dropdown' data-buttons='dropdown'></span>");
    var button = "<button class='button button-rounded'><i class='fa fa-bars'></i> 操作 </button>";
    var ul = $("<ul class='button-dropdown-list'></ul>");
    var deleteLink = $("<li><a class='pirate-link' onclick='openConfirmDeleteModal(\"" + Racoon.getLink(_links, "self") + "\")'>删除</a></li>");
    var contractLink = $("<li><a class='pirate-link' onclick='openContractsModal(\"" + Racoon.getLink(_links, "contracts") + "\")'>契约</a></li>");
    ul.append(contractLink);
    ul.append(deleteLink);

    span.append(button);
    span.append(ul);
    options.append(span);
    return options.html();
}