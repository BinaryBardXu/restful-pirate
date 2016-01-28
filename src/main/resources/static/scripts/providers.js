/**
 * Created by xubitao on 1/9/16.
 */

var Providers = {
    url: "",
    init: function (_link) {
        Providers.url = _link;
        Providers.loadProviders();

        Providers.bindCreateButton();
        Providers.bindSearchEvent();
        Providers.setValidator();
        Providers.bindProvidersRefreshButton();
    },
    loadProviders: function () {
        Racoon.restful({
            url: Providers.url,
            success: function (_data) {
                var providers = _data.providers;
                $("#providers-table").bootstrapTable('load', providers);
                $("#providers-table").bootstrapTable('hideLoading');
            }
        })
    },
    bindCreateButton: function () {
        $("#create-provider-button").unbind();
        $("#create-provider-button").click(function () {
            $('#provider-modal-title').html("创建Provider");
            Providers.bindCreateProviderButton(GlobalConfig.entrance);
        });
    },
    bindProvidersRefreshButton: function () {
        $("#providers-refresh-button").unbind();
        $("#providers-refresh-button").click(function () {
            Providers.loadProviders();
        });
    },
    bindSearchEvent: function () {
        $('#providers-search-input').val("");
        $('#providers-search-input').unbind();
        $('#providers-search-input').bind("change", function () {
            var consumerKeyOrName = $('#providers-search-input').val();
            if (consumerKeyOrName == "name/consumerKey↵") return;
            Providers.searchByKeyword(consumerKeyOrName);
        });
    },
    searchByKeyword: function (_consumerKeyOrName) {
        $('#records-table').bootstrapTable('showLoading');
        Racoon.restful({
            url: Providers.url,
            data: {keyword: _consumerKeyOrName},
            success: function (_data) {
                var providers = _data.providers;
                $("#providers-table").bootstrapTable('load', providers);
                $("#providers-table").bootstrapTable('hideLoading');
            }
        });
    },
    setValidator: function () {
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
    },
    bindCreateProviderButton: function () {
        $('#create-provider-form').data('bootstrapValidator').resetForm(true);
        $('#createOrUpdateProvidersModel').modal();
        $("#provider-resetBtn"). unbind();
        $('#provider-resetBtn').click(function () {
            $('#create-provider-form').data('bootstrapValidator').resetForm(true);
        });
        $("#provider-save-button"). unbind();
        $("#provider-save-button").click(function () {
            Providers.create(Providers.url);
        });
    },
    create: function (_link) {
        Providers.save(_link, HttpType.POST);
    },
    update: function (_link) {
        Providers.save(_link, HttpType.PUT);
    },
    save: function (_link, _type) {
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
            type: _type,
            data: JSON.stringify(provider),
            success: function () {
                Providers.loadProviders();
                $('#createOrUpdateProvidersModel').modal('hide');
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

function openProviderUpdateModal(_link) {
    Racoon.restful({
        url: _link,
        success: function (_data) {
            var provider = _data;
            $('#provider-modal-title').html("更新" + provider.name);
            $('#provider-name').val(provider.name);
            $('#provider-version').val(provider.version);
            $('#provider-consumerKey').val(provider.consumerKey);
            $("#provider-save-button"). unbind();
            $('#createOrUpdateProvidersModel').modal();
            $("#provider-save-button").click(function () {
                Providers.update(_link);
            });
        }
    })
}
function optionsFormatter(_links, _row) {
    var options = $("<div></div>");
    var span = $("<span class='button-dropdown' data-buttons='dropdown'></span>");
    var button = "<button class='button button-rounded'><i class='fa fa-bars'></i> 操作 </button>";
    var ul = $("<ul class='button-dropdown-list'></ul>");
    var contractLink = $("<li><a class='pirate-link' onclick='openContractsModal(\"" + Racoon.getLink(_links, "contracts") + "\")'>契约 <span class='badge'>" + _row.contractsCount + "</span></a></li>");
    var updateLink = $("<li><a class='pirate-link' onclick='openProviderUpdateModal(\"" + Racoon.getLink(_links, "self") + "\")'>更新</a></li>");
    var deleteLink = $("<li><a class='pirate-link' onclick='openConfirmDeleteModal(\"" + Racoon.getLink(_links, "self") + "\")'>删除</a></li>");
    ul.append(contractLink);
    ul.append(updateLink);
    ul.append(deleteLink);

    span.append(button);
    span.append(ul);
    options.append(span);
    return options.html();
}