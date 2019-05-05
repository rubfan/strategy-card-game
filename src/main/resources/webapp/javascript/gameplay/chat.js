var lastTime = null;
function createChatMessageList(dataObject) {
    var messagesList = JSON.parse(dataObject);
    var content = "";
    var currentTime = null;
    for (var i in messagesList) {
        var fromAccId = messagesList[i]['fromUserId'];
        if(fromAccId == getUserId()){
            content += '<div class="row-left">';
            content += '<div class="left-user">' + getUserName() + '</div>';
            content += '<div class="left-msg">' + messagesList[i]['text'] + '</div>';
        } else if(fromAccId == getEnemyUserId()){
            content += '<div class="row-right">';
            content += '<div class="right-msg">' + messagesList[i]['text'] + '</div>';
            content += '<div class="right-user">' + getEnemyUserName() + '</div>';
        }
        content += '</div>';
        currentTime = messagesList[i]['time'];
    }
    var chat = document.getElementById("chat_items");
    chat.innerHTML = content;
    if(currentTime != null && currentTime != lastTime) {
        document.getElementById("chat_container").scrollTop = document.getElementById("chat_container").scrollHeight;
    }
    lastTime = currentTime;
}

function clearMessageTextBox() {
    document.getElementById('message').value = "";
}

function onEnterMessageTextBox(event) {
    event.preventDefault();
    if (event.keyCode === 13) {
        document.getElementById("send_message").click();
    }
}
