/**
 * Created by xubitao on 1/15/16.
 */

var Entrance = {
    init: function () {
        Racoon.restful({
            url: GlobalConfig.entrance,
            success: function (_data) {
                var links = _data.links;
                Providers.init(Racoon.getLink(links, "providers"));
                Records.init(Racoon.getLink(links, "missedRecords"));
                Statistics.init(Racoon.getLink(links, "statistic"));
            }
        })
    }
};