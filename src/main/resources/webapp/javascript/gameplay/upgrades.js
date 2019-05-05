var upgradeFullList = {};

function prepareUpgradeFullList(dataObject) {
    var jsonUpgradeFullList = JSON.parse(dataObject);
    upgradeFullList = {};
    for (var num in jsonUpgradeFullList) {
        upgradeFullList[jsonUpgradeFullList[num]['upgradeDto']['id']] = jsonUpgradeFullList[num];
    }
}

function createUpgradeList(dataObject) {
    var userUpgradeList = JSON.parse(dataObject);
    var content = "";
    for (var i in userUpgradeList) {
        var id = userUpgradeList[i]['upgradeId'];
        content +=  '<button class="glow-effect update-button tooltip-left"'+
            ' onmousemove="prepareUpgradeTooltip(' + id + '); showTooltip(event)"' +
            ' onmouseout="hideTooltip()">' +
            upgradeFullList[id]['upgradeDto']['name'].split('_').join(' ') + '<br>' +
            '<span class="big-text-number">' + userUpgradeList[i]['quantity'] + '</span>' +
            '</button>';
    }
    //document.getElementById("upgrade_items").innerHTML = content;
}

function createEnemyUpgradeList(dataObject) {
    var userUpgradeList = JSON.parse(dataObject);
    var content = "";
    for (var i in userUpgradeList) {
        var id = userUpgradeList[i]['upgradeId'];
        content +=  '<button class="glow-effect update-button"'+
            ' onmousemove="prepareUpgradeTooltip(' + id + '); showTooltip(event)"' +
            ' onmouseout="hideTooltip()">' +
            upgradeFullList[id]['upgradeDto']['name'].split('_').join(' ') + '<br>' +
            '<span class="big-text-number">' + userUpgradeList[i]['quantity'] + '</span>' +
            '</button>';
    }
    //document.getElementById("enemy_upgrade_items").innerHTML = content;
}

function prepareUpgradeTooltip(num) {
    var content = '';
    content += 'Upgrade: ' + upgradeFullList[num]['upgradeDto']['name'].split('_').join(' ');
    content += ' (' + upgradeFullList[num]['upgradeDto']['description'] + ')<br><br>';
    if(upgradeFullList[num]['resourceQuantityDtoList'] != undefined) {
        content += '<br>This Upgrade is improving production of following resources:' + '<br>';
        for (var numRes in upgradeFullList[num]['resourceQuantityDtoList']) {
            content += '<img class="small-icon" src="' +
                IMG_RESOURCES_URL[upgradeFullList[num]['resourceQuantityDtoList'][numRes]['id']] + '">';
            content += ' ' + upgradeFullList[num]['resourceQuantityDtoList'][numRes]['name'];
            content += ' (' + upgradeFullList[num]['resourceQuantityDtoList'][numRes]['description'] + ')';
            content += ' to <b style="color: #7cff03">' +upgradeFullList[num]['resourceQuantityDtoList'][numRes]['quantity'] + '%</b><br>';
        }
    }
    if(upgradeFullList[num]['buildingDtoList'] != undefined) {
        content += '<br>For following buildings:' + '<br>';
        for (var numBld in upgradeFullList[num]['buildingDtoList']) {
            content += '<img class="small-icon" src="' +
                IMG_BUILDINGS_URL[upgradeFullList[num]['buildingDtoList'][numBld]['id']]['url'] + '">';
            content += ' ' + upgradeFullList[num]['buildingDtoList'][numBld]['name'];
            content += ' (' + upgradeFullList[num]['buildingDtoList'][numBld]['description'] + ')<br>';
        }
    }
    document.getElementById("tooltip_component").innerHTML = content;
}
