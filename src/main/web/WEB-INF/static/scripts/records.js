/**
 * Created by xubitao on 1/9/16.
 */

var Records = {
    init: function (_link) {
        Records.loadRecords(_link);
        $('#recordsModel').modal();
    },
    loadRecords: function (_link) {
        $('#records-table').bootstrapTable('showLoading');
        Racoon.restful({
            url: _link,
            success: function (_data) {
                var contracts = _data.contracts;
                $('#records-table').bootstrapTable('load', contracts);
                $('#records-table').bootstrapTable('hideLoading');
                $('[data-toggle="popover"]').popover();
            }
        });
    },
    loadMissedRecords: function (_link) {
        $('#records-table').bootstrapTable('showLoading');
        Racoon.restful({
            url: _link,
            success: function (_data) {
                var contracts = _data.contracts;
                $('#missedRecords-table').bootstrapTable('load', contracts);
                $('#missedRecords-table').bootstrapTable('hideLoading');
                var recordsLink = Racoon.getLink(_data.links, "records");
                Records.bindSearchEvent(recordsLink);
                $('[data-toggle="popover"]').popover();
            }
        });
    },
    bindSearchEvent: function (_recordsLink) {
        $('#missed-record-search-input').val("");
        $('#missed-record-search-input').unbind();
        $('#missed-record-search-input').bind("change", function () {
            var consumerKey = $('#missed-record-search-input').val();
            if (consumerKey == "consumerKey↵") return;
            Records.searchByConsumerKey(_recordsLink, consumerKey);
        });
    },
    searchByConsumerKey: function (_recordsLink, _consumerKey) {
        $('#records-table').bootstrapTable('showLoading');
        Racoon.restful({
            url: _recordsLink,
            data: {consumerKey: _consumerKey},
            success: function (_data) {
                var contracts = _data.contracts;
                $('#missedRecords-table').bootstrapTable('load', contracts);
                $('#missedRecords-table').bootstrapTable('hideLoading');
            }
        });
    },
    openMissedRecords: function (_link) {
        Records.loadMissedRecords(_link);
        $('#missedRecordsModel').modal();
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
                Records.loadRecords(_contractsLink);
            }
        })
    });
    $('#confirmDeleteModel').modal();
}
function dddFormatter(_links) {
    var options = $("<div></div>");
    var span = $("<span class='button-dropdown' data-buttons='dropdown'></span>");
    var button = "<button class='button button-rounded'><i class='fa fa-bars'></i> 操作 </button>";
    var ul = $("<ul class='button-dropdown-list'></ul>");
    var recordsLink = $("<li><a class='pirate-link' onclick='openContractUpdateModal(\"" + Racoon.getLink(_links, "self") + "\",\"" + Racoon.getLink(_links, "records") + "\")'>记录</a></li>");
    var updateLink = $("<li><a class='pirate-link' onclick='openContractUpdateModal(\"" + Racoon.getLink(_links, "self") + "\",\"" + Racoon.getLink(_links, "contracts") + "\")'>更新</a></li>");
    var deleteLink = $("<li><a class='pirate-link' onclick='openConfirmDeleteContractModal(\"" + Racoon.getLink(_links, "self") + "\",\"" + Racoon.getLink(_links, "contracts") + "\")'>删除</a></li>"
    );
    ul.append(recordsLink);
    ul.append(updateLink);
    ul.append(deleteLink);

    span.append(button);
    span.append(ul);
    options.append(span);
    return options.html();
}