var currentUser = {};

function getView(viewId) {
    return document.getElementById(viewId).innerHTML;
}

function setView(viewId, view) {
    document.getElementById(viewId).innerHTML = view;
}
