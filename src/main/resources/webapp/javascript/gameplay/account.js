var userInfo = {};
var userName = '';
var enemyName = '';
var userId = '';
var enemyUserId = '';
var roomId = '';

const USER_INFO = (roomName, roomDescription) => `Current Room: <br>${roomName}<br>(${roomDescription})`;
const USER_NAMES = (userName, enemyName) => `<h1 style="color: #00c135">${userName}</h1>VS<h1 style="color: #eb3214">${enemyName}</h1>`;

function prepareUserInfo(dataObject) {
    userInfo = JSON.parse(dataObject);
    userName = null;
    enemyName = null;
    userId = null;
    enemyUserId = null;
    roomId = null;
    if(userInfo['id'] != undefined) {
        userId = userInfo['id'];
        if(userInfo['room'] != undefined) {
            roomId = userInfo['room']["id"];
            if(userInfo['room']['user2'] != undefined && userInfo['room']['user2'] != undefined ) {
                if(userInfo['room']['user1']['id'] == userId) {
                    enemyUserId = userInfo['room']['user2']['id'];
                    userName = userInfo['room']['user1']['user']['name'];
                    enemyName = userInfo['room']['user2']['user']['name'];
                } else if(userInfo['room']['user2']['id'] == userId) {
                    enemyUserId = userInfo['room']['user1']['id'];
                    userName = userInfo['room']['user2']['user']['name'];
                    enemyName = userInfo['room']['user1']['user']['name'];
                }
                createUserInfo();
            }
        }
    }
}

function createUserInfo() {
    var content = userInfo['room'] != undefined ? USER_INFO(userInfo['room']['name'], userInfo['room']['description']) : "";
    content += USER_NAMES(getUserName(), getEnemyUserName());
    setView("user_info", content);
}

function getUserId() {
    return userId;
}

function getEnemyUserId() {
    return enemyUserId;
}

function getUserName() {
    return userName;
}

function getEnemyUserName() {
    return enemyName;
}

function getRoomId() {
    return roomId;
}