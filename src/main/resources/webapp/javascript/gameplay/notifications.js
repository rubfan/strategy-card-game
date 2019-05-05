var notificationFullList = {};
var notificationList = {};

const NOTIFICATION_VIEW = (o) => `
    <div id="notification${o.id}" class="notification" style="animation: fade 8s ${o.index}s ease">
        <div style="font-size: 25px; color: #0055ff">${o.name}</div><br>
        <b>${o.description}</b>
    </div>`;

function prepareNotificationFullList(dataObject) {
    var jsonNotificationFullList = JSON.parse(dataObject);
    notificationFullList = {};
    for (var num in jsonNotificationFullList) {
        notificationFullList[jsonNotificationFullList[num]['notificationDto']['id']] = jsonNotificationFullList[num];
    }
}

function createNotificationList(model) {
    notificationList = JSON.parse(model);
    var content = "";
    var index = 0.5;
    for (var i in notificationList) {
        content += NOTIFICATION_VIEW({
            id: i,
            index: index,
            name: notificationList[i]['name'],
            description: notificationList[i]['description']
        });
        index += 0.5;
    }
    setView("notification_container", content);
    setTimeout(function () {
        setView("notification_container", '');
    }, index * 1000 + 8000);
}

