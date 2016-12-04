$(function () {
    if (!getTokenFromCookie())
        processLogin();
});

function processLogin() {
    assignGetTokenLink();
    var token;
    if (token = extractTokenFromURL)
        saveTokenInCookie(token);
}

function assignGetTokenLink() {
    var webappRedirectURI = document.location.href;

    var tokenQuery = '/oauth/authorize?'
            + 'response_type=token'
            + '&client_id=vseprosto'
            + '&redirect_uri=' + webappRedirectURI
            + '&scope=read';

    var getAccessTokenLink = document.getElementById("get-access-token");

    // The below implies we have the auth.server on the same host as the application, so
    //TODO: use static auth.server URL to build getAccessTokenLink once we`ve finally hosted it somewhere
    var webappHostname = document.location.origin;
    if (getAccessTokenLink)
        getAccessTokenLink.setAttribute('href', webappHostname + tokenQuery);
}

function extractTokenFromURL() {
    var hash = document.location.hash;
    var token;

    var match = hash.match(/access_token=(\w+)/);
    return !!match && match[1];
}

//uses jQuery Cookie plugin
function saveTokenInCookie(token) {
    $.cookie('access-token', token);
}

function getTokenFromCookie() {
    return $.cookie('access-token');
}

function securedAjax(url) {
    var result;
    $.ajax({
        url: url,
        beforeSend: function (xhr) {
            xhr.setRequestHeader('Authorization', "Bearer " + getTokenFromCookie());
            xhr.setRequestHeader('Accept', "application/json");
        },
        success: function (response) {
            result = response;
        }
    });
    return result;
}