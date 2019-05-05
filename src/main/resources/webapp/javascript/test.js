const testEndpoints = {
    testAccountAchievementController: {
        getUserAchievementsList: () => endpoints.getUserAchievementsList({userId: 1}, showJsonResult)
    },
    testAccountBuildingController: {
        clearAccountBuildingList: () => endpoints.clearAccountBuildingList({userId: 1}, showStringResult),
        getAccountBuildingList: () => endpoints.getAccountBuildingList({userId: 1}, showJsonResult)
    },
    testAccountCardController: {
        applyCard: () => endpoints.applyCard({userId: 1, cardId: 1}, showJsonResult),
        getAllowUserCardList: () => endpoints.getAllowUserCardList({userId: 1}, showJsonResult)
    },
    testAccountController: {
        getAccount: () => endpoints.getAccount(showJsonResult)
    },
    testAccountNotificationController: {
        clearAccountNotificationList: () => endpoints.clearAccountNotificationList({userId: 1}, showJsonResult),
        getAccountRecentNotificationList: () => endpoints.getAccountRecentNotificationList({userId: 1}, showJsonResult)
    },
    testAccountResourceController: {
        clearAccountResourceList: () => endpoints.clearAccountResourceList({userId: 1}, showJsonResult),
        getAccountResourceList: () => endpoints.getAccountResourceList({userId: 1}, showJsonResult)
    },
    testAccountUpgradeController: {
        clearAccountUpgradeList: (params, callback) => endpoints.clearAccountUpgradeList({userId: 1}, showJsonResult),
        getAccountUpgradeList: (params, callback) => endpoints.getAccountUpgradeList({userId: 1}, showJsonResult)
    },
    testAchievementController: {
        getAllAchievementList: (callback) => endpoints.getAllAchievementList(showJsonResult)
    },
    testBuildingController: {
        getAllBuildingList: () => endpoints.getAllBuildingList(showJsonResult)
    },
    testCardController: {
        getAllCardList: () => endpoints.getAllCardList(showJsonResult)
    },
    testMessageController: {
        getMessageList: () => endpoints.getMessageList(1, 2, new Date(), showJsonResult),
        sendMessage: () => endpoints.sendMessage(showJsonResult)
    },
    testNotificationController: {
        getAllNotificationList: () => endpoints.getAllNotificationList(showJsonResult)
    },
    testResourceController: {
        getAllResourceList: () => endpoints.ResourceController.getAllResourceList(showJsonResult)
    },
    testRoomController: {
        getAllRoomList: (callback) => endpoints.RoomController.getAllRoomList(showJsonResult),
    },
    testAccountRoomController: {
        getAccountRoomList: () => endpoints.RoomController.getAccountRoomList(showJsonResult),
        joinRoom: (roomId, userId, callback) => endpoints.RoomController.joinRoom(1, 1, showStringResult),
        leaveRoom: (roomId, userId, callback) => endpoints.RoomController.leaveRoom(1, 1, showStringResult)
    },
    testUpgradeController: {
        getAllUpgradeList: (callback) => endpoints.getAllUpgradeList(showJsonResult)
    },
    testUserController: {
        createNewUser: (callback, body) => endpoints.UserController.createNewUser(showStringResult, body),
        loginUser: (callback, body) => endpoints.UserController.loginUser(showStringResult, body),
        logoutUser: (callback) => endpoints.logoutUser(showJsonResult)
    }
};

function showJsonResult(dataObject) {
    console.log(JSON.parse(dataObject));
    setView("result", renderJSON(JSON.parse(dataObject), 1));

}
function showStringResult(res) {
    console.log(res);
    setView("result", res);
}

function renderJSON(obj, numTabs) {
    'use strict';
    var keys = [], retValue = "";
    for (var key in obj) {
        if (typeof obj[key] === 'object') {
            retValue += `<div><i style="color: black">${".".repeat(numTabs * 4)}</i><b style="color: #4b7caf">${key}</b>`;
            retValue += renderJSON(obj[key], ++numTabs);
            numTabs--;
            retValue += "</div>";
        } else {
            retValue += `<div><i style="color: black">${".".repeat(numTabs * 4)}</i><b style="color: #4b7caf">${key}</b>`;
            retValue += `<b style="color: #ffd000"> = </b>${obj[key]}</div>`;
        }
        keys.push(key);
    }
    return retValue;
}
