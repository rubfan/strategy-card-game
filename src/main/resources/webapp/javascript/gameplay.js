var hours = 1;
var countDownDate = (new Date().getTime()) + (hours * 60 * 60 * 1000);
var countDownLatchCounter = 0;

//************************************************
//******************GAME STARTER******************
//************************************************
function startGame() {
    endpoints.getUserByCookie(dataObject => {
        var user = JSON.parse(dataObject);
        if(user["id"] == null) {
            static.roomsPage();
            return;
        }
        runGameCycle(user["id"]);
    });
}

function runGameCycle(userId) {
    countDownLatchCounter = 6;
    //====INIT STATIC DATA(always the same)====
    endpoints.getAllResourceList(data => {prepareResourceFullList(data); countDownLatchCounter--});
    endpoints.getAllBuildingList(data => {prepareBuildingFullList(data); countDownLatchCounter--});
    endpoints.getAllUpgradeList(data => {prepareUpgradeFullList(data); countDownLatchCounter--});
    endpoints.getAllCardList(data => {prepareCardFullList(data); countDownLatchCounter--});
    endpoints.getAllNotificationList(data => {prepareNotificationFullList(data); countDownLatchCounter--});
    endpoints.getUserInfo(userId ,data => {prepareUserInfo(data); countDownLatchCounter--});

    //====CYCLE OF DYNAMIC DATA(always changing during gameplay)====
    var si = setInterval(() => {
        if(getUserId() == null) {
            clearInterval(si);
            static.roomsPage();
            return;
        }
        if(getEnemyUserId() == null) {
            endpoints.getUser(getUserId(), prepareUserInfo);
            return;
        }
        if(countDownLatchCounter > 0) {
            return;
        }

        endpoints.getUserResourceList(getUserId(), createUserResourceList);
        endpoints.getUserResourceList(getEnemyUserId(), createEnemyResourceList);
        endpoints.getUserBuildingList(getUserId(), createBuildingList);
        endpoints.getUserBuildingList(getEnemyUserId(), createEnemyBuildingList);
        endpoints.getUserUpgradeList(getUserId(), createUpgradeList);
        endpoints.getUserUpgradeList(getEnemyUserId(), createEnemyUpgradeList);
        endpoints.getAllowUserCardList(getUserId(), createCardList);
        endpoints.getRoomMessageList(getRoomId(), createChatMessageList);
        endpoints.getUserRecentNotificationList(getUserId(), createNotificationList);

    }, 4000);

    var x = setInterval(() => {
        if(getUserId() == null) {
            clearInterval(x);
            return;
        }
        if(getEnemyUserId() == null) {
            return;
        }
        if(countDownLatchCounter > 0) {
            return;
        }

        var now = new Date().getTime();
        var distance = countDownDate - now;
        var minutes = Math.floor((distance % (1000 * 60 * 60)) / (1000 * 60));
        var seconds = Math.floor((distance % (1000 * 60)) / 1000);
        document.getElementById("count_down_time").innerHTML =
            (minutes.toString().length > 1 ? minutes : "0" + minutes) + ":" +
            (seconds.toString().length > 1 ? seconds : "0" + seconds);
        if (distance < 0) {
            clearInterval(x);
            document.getElementById("count_down_time").innerHTML = "EXPIRED";
        }
    }, 1000);
}

function playSoundFx(file) {
    var audio = new Audio(file);
    audio.play();
}

function showTooltip(event) {
    var style = document.getElementById("tooltip_component").style;
    style.left = style.right = style.top = style.bottom = null;
    if(event.clientX > window.innerWidth / 2) {
        style.right = window.innerWidth - event.clientX + "px";
    } else {
        style.left = event.clientX + "px";
    }
    if(event.clientY > window.innerHeight / 2) {
        style.bottom = window.innerHeight - event.clientY + "px";
    } else {
        style.top = event.clientY;
    }
    style.visibility = 'visible';
    style.opacity = 1;
    style.zIndex = 9999;
}

function hideTooltip() {
    document.getElementById("tooltip_component").style.visibility = 'hidden';
    document.getElementById("tooltip_component").style.opacity = 0;
    document.getElementById("tooltip_component").innerHTML = '';
}

//=============================
//============START============
//=============================
startGame();

