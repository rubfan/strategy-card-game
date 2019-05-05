var cardFullList = {};
var currentTabId = 1;

function prepareCardFullList(dataObject) {
    var jsonCardFullList = JSON.parse(dataObject);
    cardFullList = {};
    for (var num in jsonCardFullList) {
        cardFullList[jsonCardFullList[num]['cardDto']['id']] = jsonCardFullList[num];
    }
}

function createCardList(dataObject) {
    var userCardList = JSON.parse(dataObject);
    var content = "";
    var tabGroupId = -1;
    for (var i in userCardList) {
        var id = userCardList[i];
        var groupId = cardFullList[id]['cardGroupDto']['id'];
        if(tabGroupId !== groupId) {
            if(tabGroupId >= 0) {
                content += '</div></div></div>';
            }
            tabGroupId = groupId;
            content += '<div class="tab" onclick="changeCurrentTabId(' + groupId + ')">';
            content += '<input type="radio" id="tab-' + groupId + '" name="tab-group" '+
                (groupId === currentTabId ? 'checked' : '') + '>';
            content += '<label for="tab-' + groupId + '">' + cardFullList[id]['cardGroupDto']['name'] + '</label>';
            content += '<div class="content">';
            content += '<div class="cards">';
        }
        content +=  '<button class="glow-effect card-button"'+
            ' onmousemove="prepareCardTooltip(' + id + '); showTooltip(event)"' +
            ' onmouseout="hideTooltip()"' +
            ' onclick="applyCard(' + id + ')">' +
            //'<img class="card-img" src="' + IMG_CARDS_URL[cardFullList[id]['cardDto']['id']] + '">' +
            '<span>' + cardFullList[id]['cardDto']['name'].split('_').join(' ') + '</span>' +
            '</button>';
    }
    content += '</div></div></div>';
    document.getElementById("cards_container").innerHTML = content;
}

function changeCurrentTabId(id) {
    currentTabId = id;
}

function applyCard(cardId) {
    restRequest("GET", REST_API_URL + "/user/" + getUserId() + "/card/" + cardId + "/apply", function (dataObject) {
        playSoundFx('sounds/chicken_cluck_single.mp3');
    });
}

function prepareCardTooltip(num) {
    var content = '<b style="font-size: 20px; color: #ff8000">Card: '
        + cardFullList[num]['cardDto']['name'].split('_').join(' ') + '</b><br>';
    content += ' (' + cardFullList[num]['cardDto']['description'] + ')<br><br>';

    content += prepareP1BuildingQuantityDtoList(num);
    content += prepareP2BuildingQuantityDtoList(num);
    content += prepareP1ResourceQuantityDtoList(num);
    content += prepareP2ResourceQuantityDtoList(num);
    content += prepareP1UpgradeQuantityDtoList(num);
    content += prepareP2UpgradeQuantityDtoList(num);
    content += prepareNecessaryBuildingQuantityDtoList(num);
    content += prepareNecessaryUpgradeQuantityDtoList(num);

    document.getElementById("tooltip_component").innerHTML = content;
}

function prepareP1BuildingQuantityDtoList(num) {
    var list = cardFullList[num]['p1BuildingQuantityDtoList'];
    var content = '';
    if(list != undefined) {
        content += '<br>This card subtracts or adds you the following Buildings:' + '<br>';
        for (var i in list) {
            content += '<img class="small-icon" src="' + IMG_BUILDINGS_URL[list[i]['id']]['url'] + '">';
            content += ' ' + list[i]['name'];
            content += ' (' + list[i]['description'] + ') ';
            content += ' <b style="color: #7cff03">' + list[i]['quantity'] + '</b><br>';
        }
    }
    return '';
}

function prepareP2BuildingQuantityDtoList(num) {
    var list = cardFullList[num]['p2BuildingQuantityDtoList'];
    var content = '';
    if(list != undefined) {
        content += '<br>This card takes the following Buildings from your opponent:' + '<br>';
        for (var i in list) {
            content += '<img class="small-icon" src="' + IMG_BUILDINGS_URL[list[i]['id']]['url'] + '">';
            content += ' ' + list[i]['name'];
            content += ' (' + list[i]['description'] + ') ';
            content += ' <b style="color: #ff0600">' + list[i]['quantity'] + '</b><br>';
        }
    }
    return content;
}

function prepareP1ResourceQuantityDtoList(num) {
    var list = cardFullList[num]['p1ResourceQuantityDtoList'];
    var content = '';
    if(list != undefined) {
        content += '<br>This card subtracts or adds you the following Resources:' + '<br>';
        for (var i in list) {
            content += '<img class="small-icon" src="' + IMG_RESOURCES_URL[list[i]['id']] + '">';
            content += ' ' + list[i]['name'];
            content += ' (' + list[i]['description'] + ')';
            content += ' <b style="color: #7cff03">' + list[i]['quantity'] + '</b><br>';
        }
    }
    return content;
}

function prepareP2ResourceQuantityDtoList(num) {
    var list = cardFullList[num]['p2ResourceQuantityDtoList'];
    var content = '';
    if(list != undefined) {
        content += '<br>This card takes the following Resources from your opponent:' + '<br>';
        for (var i in list) {
            content += '<img class="small-icon" src="' + IMG_RESOURCES_URL[list[i]['id']] + '">';
            content += ' ' + list[i]['name'];
            content += ' (' + list[i]['description'] + ')';
            content += ' <b style="color: #ff0600">' + list[i]['quantity'] + '</b><br>';
        }
    }
    return content;
}

function prepareP1UpgradeQuantityDtoList(num) {
    var list = cardFullList[num]['p1UpgradeQuantityDtoList'];
    var content = '';
    if(list != undefined) {
        content += '<br>This card subtracts or adds you the following Upgrades:' + '<br>';
        for (var i in list) {
            content += ' ' + list[i]['name'];
            content += ' (' + list[i]['description'] + ')';
            content += ' <b style="color: #7cff03">' + list[i]['quantity'] + '</b><br>';
        }
    }
    return content;
}

function prepareP2UpgradeQuantityDtoList(num) {
    var list = cardFullList[num]['p2UpgradeQuantityDtoList'];
    var content = '';
    if(list != undefined) {
        content += '<br>This card takes the following Upgrades from your opponent:' + '<br>';
        for (var i in list) {
            content += ' ' + list[i]['name'];
            content += ' (' + list[i]['description'] + ')';
            content += ' <b style="color: #ff0600">' + list[i]['quantity'] + '</b><br>';
        }
    }
    return content;
}

function prepareNecessaryBuildingQuantityDtoList(num) {
    var list = cardFullList[num]['necessaryBuildingQuantityDtoList'];
    var content = '';
    if(list != undefined) {
        content += '<br>This card requires the following Buildings:' + '<br>';
        for (var i in list) {
            content += '<img class="small-icon" src="' + IMG_BUILDINGS_URL[list[i]['id']]['url'] + '">';
            content += ' ' + list[i]['name'];
            content += ' (' + list[i]['description'] + ')';
            content += ' <b style="color: #ff8000">' + list[i]['quantity'] + '</b><br>';
        }
    }
    return content;
}

function prepareNecessaryUpgradeQuantityDtoList(num) {
    var list = cardFullList[num]['necessaryUpgradeQuantityDtoList'];
    var content = '';
    if(list != undefined) {
        content += '<br>This card requires the following Upgrades:' + '<br>';
        for (var i in list) {
            content += ' ' + list[i]['name'];
            content += ' (' + list[i]['description'] + ')';
            content += ' <b style="color: #ff8000">' + list[i]['quantity'] + '</b><br>';
        }
    }
    return content;
}
