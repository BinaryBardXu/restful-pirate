/**
 * Created by xubitao on 1/2/16.
 */
var Racoon = {
    restful: function (_options) {
        $.ajax({
            type: _options.type || "GET",
            url: _options.url,
            dataType: "json",
            success: function (_data) {
                _options.success(_data);
            }
        });
    }
};