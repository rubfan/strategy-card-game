const RESOURCE_URL = window.location.protocol + "//" + window.location.host;
const REST_API_URL = RESOURCE_URL + "/rest";
const METHOD_GET = "GET";
const METHOD_POST = "POST";
const METHOD_PUT = "PUT";
const METHOD_DELETE = "DELETE";

const static = {
    loginPage: () => window.location.replace(RESOURCE_URL + '/login.html'),
    logoutPage: () => window.location.replace(RESOURCE_URL + '/login.html'),
    roomsPage: () => window.location.replace(RESOURCE_URL + '/rooms.html'),
    gameplayPage: () => window.location.replace(RESOURCE_URL + '/gameplay.html'),
    achievementsPage: () => window.location.replace(RESOURCE_URL + '/achievements.html'),
    testPage: () => window.location.replace(RESOURCE_URL + '/test.html')
};

const endpoints = {
    AccountAchievementController: {
        getAccountAchievementsList: (accountId, callback) => restRequest(METHOD_GET, `${REST_API_URL}/account/${accountId}/achievement/list`, callback)
    },
    AccountBuildingController: {
        clearAccountBuildingList: (accountId, callback) => restRequest(METHOD_GET, `${REST_API_URL}/account/${accountId}/building/list/clear`, callback),
        getAccountBuildingList: (accountId, callback) => restRequest(METHOD_GET, `${REST_API_URL}/account/${accountId}/building/list`, callback)
    },
    AccountCardController: {
        applyCard: (playerAccountId, enemyAccountId, cardId, callback) => restRequest(METHOD_GET, `${REST_API_URL}/account/player/${playerAccountId}/enemy/${enemyAccountId}/card/${cardId}/apply`, callback),
        getAllowCardList: (accountId, callback) => restRequest(METHOD_GET, `${REST_API_URL}/account/${accountId}/card/list`, callback)
    },
    AccountController: {
        getAccount: (callback) => restRequest(METHOD_GET, `${REST_API_URL}/account/info`, callback)
    },
    AccountNotificationController: {
        clearAccountNotificationList: (accountId, callback) => restRequest(METHOD_GET, `${REST_API_URL}/account/${accountId}/notification/list/clear`, callback),
        getAccountRecentNotificationList: (accountId, callback) => restRequest(METHOD_GET, `${REST_API_URL}/account/${accountId}/notification/list`, callback)
    },
    AccountResourceController: {
        clearAccountResourceList: (accountId, callback) => restRequest(METHOD_GET, `${REST_API_URL}/account/${accountId}/resource/list/clear`, callback),
        getAccountResourceList: (accountId, callback) => restRequest(METHOD_GET, `${REST_API_URL}/account/${accountId}/resource/list`, callback)
    },
    AccountUpgradeController: {
        clearAccountUpgradeList: (accountId, callback) => restRequest(METHOD_GET, `${REST_API_URL}/account/${accountId}/upgrade/list/clear`, callback),
        getAccountUpgradeList: (accountId, callback) => restRequest(METHOD_GET, `${REST_API_URL}/account/${accountId}/upgrade/list`, callback)
    },
    AchievementController: {
        getAllAchievementList: (callback) => restRequest(METHOD_GET, `${REST_API_URL}/achievement/list`, callback)
    },
    BuildingController: {
        getAllBuildingList: (callback) => restRequest(METHOD_GET, `${REST_API_URL}/building/list`, callback)
    },
    CardController: {
        getAllCardList: (callback) => restRequest(METHOD_GET, `${REST_API_URL}/card/list`, callback)
    },
    MessageController: {
        getMessageList: (fromAccountId, toAccountId, from_time, callback) => restRequest(METHOD_GET, `${REST_API_URL}/account/message/time/${from_time}/player/${fromAccountId}/enemy/${toAccountId}/list`, callback),
        sendMessage: (body, callback) => restRequest(METHOD_POST, `${REST_API_URL}/account/message/send`, callback, body)
    },
    NotificationController: {
        getAllNotificationList: (callback) => restRequest(METHOD_GET, `${REST_API_URL}/notification/list`, callback)
    },
    ResourceController: {
        getAllResourceList: (callback) => restRequest(METHOD_GET, `${REST_API_URL}/resource/list`, callback)
    },
    RoomController: {
        getAllRoomList: (callback) => restRequest(METHOD_GET, `${REST_API_URL}/room/list`, callback),
    },
    AccountRoomController: {
        getAccountRoomList: (callback) => restRequest(METHOD_GET, `${REST_API_URL}/account/room/list`, callback),
        joinRoom: (roomId, accountId, callback) => restRequest(METHOD_GET, `${REST_API_URL}/account/${accountId}/room/${roomId}/join`, callback),
        leaveRoom: (roomId, accountId, callback) => restRequest(METHOD_GET, `${REST_API_URL}/account/${accountId}/room/${roomId}/leave`, callback)
    },
    UpgradeController: {
        getAllUpgradeList: (callback) => restRequest(METHOD_GET, `${REST_API_URL}/upgrade/list`, callback)
    },
    UserController: {
        createNewUser: (callback, body) => restRequest(METHOD_POST, `${REST_API_URL}/user/new`, callback, body),
        loginUser: (callback, body) => restRequest(METHOD_POST, `${REST_API_URL}/user/login`, callback, body),
        logoutUser: (callback) => restRequest(METHOD_GET, `${REST_API_URL}/user/logout`, callback)
    }
};

function restRequest(method, url, callback, body) {
    console.log("restRequest method: " + method + " url: " + url + (body ? " body:" : ""));
    if (body) console.log(body);
    var httpRequest = new XMLHttpRequest();
    httpRequest.onreadystatechange = function() {
        console.log("status: " + this.status);
        if (this.readyState == 4 && (this.status == 200 || this.status == 201)) {
            if(callback && this.responseText) {
                console.log("executed callback with data object: " + callback.name);
                callback(this.responseText);
                //callback(JSON.parse(this.responseText));
            } else if (callback) {
                console.log("executed callback: " + callback.name);
                callback();
            }
        }
    };
    httpRequest.open(method, url, true);
    httpRequest.setRequestHeader('Accept', 'application/json');
    httpRequest.setRequestHeader("Content-type", "application/json");
    switch(method) {
        case METHOD_GET: httpRequest.send(null); break;
        case METHOD_POST: httpRequest.send(JSON.stringify(body)); break;
        case METHOD_PUT: httpRequest.send(JSON.stringify(body)); break;
        case METHOD_DELETE: httpRequest.send(null); break;
    }
}

function getCookie(key) {
    var match = document.cookie.match(new RegExp(key + '=([^;]+)'));
    if (match) return match[1];
    return null;
}
function removeCookie(key) {
    document.cookie = key + "=";
}
function setCookie(key, value) {
    document.cookie = key + "=" + value;
}
