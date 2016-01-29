/**
 * Created by xubitao on 1/9/16.
 */

var Records = {
    init: function (_link) {
        Records.bindMissedRecordsButton(_link);
    },
    bindMissedRecordsButton: function (_link) {
        $("#missed-record-button").unbind();
        $("#missed-record-button").click(function () {
            Records.openMissedRecords(_link);
        });
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
                $("#recordsModel").modal();
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
                var recordsLink = Racoon.getLink(_data._links, "records");
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

function hitFormatter(_hit) {
    if (_hit == 1) {
        return "<span class='glyphicon glyphicon-ok-sign pirate-hit' ></span>";
    }
    return "<span class='glyphicon glyphicon-remove-sign pirate-missed' ></span>";
}
