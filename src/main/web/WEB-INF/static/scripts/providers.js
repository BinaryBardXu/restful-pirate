/**
 * Created by xubitao on 1/9/16.
 */
function confirmDeleteOption(_link) {
    $('#delete-button').click(function () {
        Racoon.restful({
            url: _link,
            type: 'DELETE',
            success: function () {
                $('#confirmDeleteModel').modal('hide');
                loadProviders();
            }
        })
    });
    $('#confirmDeleteModel').modal();
}

function optionsFormatter(_links) {
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