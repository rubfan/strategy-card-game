const POWER = "24";
var resourceFullList = {};
var resourcePerMin =[];
var resourceEnemyPerMin =[];
var lastUserDate = new Date();
var lastEnemyDate = new Date();

const IMG_RESOURCES_URL = {
    "1": "images/resources/wheat_resource.png",
    "2": "images/resources/egg_resource.png",
    "3": "images/resources/chick_resource.png",
    "4": "images/resources/droppings_resource.png",
    "5": "images/resources/worker_resource.png",
    "6": "images/resources/recruit_resource.png",
    "7": "images/resources/warrior_resource.png",
    "8": "images/resources/stone_resource.png",
    "9": "images/resources/hammer_resource.png",
    "10": "images/resources/throwing_cock_resource.png",
    "11": "images/resources/metal_resource.png",
    "12": "images/resources/sword_resource.png",
    "13": "images/resources/axe_resource.png",
    "14": "images/resources/wood_resource.png",
    "15": "images/resources/archer_resource.png",
    "16": "images/resources/spear_resource.png",
    "17": "images/resources/gunpowder_resource.png",
    "18": "images/resources/machine_gun_resource.png",
    "19": "images/resources/bazooka_resource.png",
    "20": "images/resources/mana_resource.png",
    "21": "images/resources/wizard_resource.png",
    "22": "images/resources/prophet_resource.png",
    "23": "images/resources/experience_resource.png",
    "24": "images/resources/power_resource.png"
};

const IMG_KING_URL = {
    won: "images/power_chicken_won.png",
    lost: "images/power_chicken_lost.png",
    plaing: "images/power_chicken_plaing.png"
};

var myImage = IMG_KING_URL.plaing, enemyImage = myImage;
var myPower = 0, enemyPower = 0;

const RESOURCE_TABLE_VIEW = (content) => `
    <table class="resource-table">
        ${content}
    </table>`;

const RESOURCE_ROW_VIEW = (o) => `
    <tr>
        ${o.leftContent}
        <td colspan="${o.span}" style="width:100%"></td>
        ${o.rightContent}
    </tr>`;

const RESOURCE_CELLS_VIEW = (o) => `
    <td>
        <div id="p${o.playerNum}res${o.id}" class="resource-item">
            <img class="resource-item-img" width="55px" height="55px" src="${o.img}" 
                onmousemove="prepareResourceTooltip(${o.id}); showTooltip(event)" onmouseout="hideTooltip()">
            <span id="${o.prefix}${o.id}" class="small-text"></span>
        </div>
    </td>`;

const KING_VIEW = (o) => `
    <div class="bottom"></div>
    <div class="middle" style="height: ${o.cylinderSize}px"></div>
    <div class="top" style="top: ${o.cylinderSizeBottom}px;"></div>
    <b class="power-percent" style="line-height: ${o.cylinderSize}px;">${o.percent}%</b>
    <img src="${o.img}" class="${o.className}" style="bottom: ${o.power}px">`;

const RESOURCE_TOOLTIP_VIEW = (o) => `<b style="font-size: 20px; color: #ff8000">Resource: ${o.name}</b><br> (${o.description})`;

function prepareResourceFullList(dataObject) {
    var jsonResourceFullList = JSON.parse(dataObject);
    resourceFullList = {};
    for (var num in jsonResourceFullList) {
        resourceFullList[jsonResourceFullList[num]['id']] = jsonResourceFullList[num];
    }
    createHtmlTableResources();
}

setInterval(() => {
    refreshResources(resourcePerMin, lastUserDate, "resource_quantity");
    refreshResources(resourceEnemyPerMin, lastEnemyDate, "resource_enemy_quantity");
}, 1000);

function refreshResources(resPerMin, lastDate, view) {
    for (var i in resPerMin) {
        var id = resPerMin[i]['id'];
        var quantity = resPerMin[i]['quantity'];
        var perMin = resPerMin[i]['per_min'];
        var now = new Date().getTime();
        var delta = Math.abs(now - lastDate) / 1000;
        var resResult = Math.floor(quantity + perMin / 60 * delta).toString();
        setView(view + id, resResult);
    }
}

function createHtmlTableResources() {
    var content = '';
    var tdNum = 9;
    var resId = 1;
    for (var i = 0; i < 4; i++) {
        var leftRow = '';
        for (var j = 0; j < tdNum; j++) {
            const id = (resId + j);
            leftRow += RESOURCE_CELLS_VIEW({
                id: id,
                img: IMG_RESOURCES_URL[id.toString()],
                playerNum: '1',
                prefix: 'resource_quantity'
            });
        }
        var rightRow = '';
        for (var k = 0; k < tdNum; k++) {
            const id = (resId + (tdNum - k - 1));
            rightRow += RESOURCE_CELLS_VIEW({
                id: id,
                img: IMG_RESOURCES_URL[id.toString()],
                playerNum: '2',
                prefix: 'resource_enemy_quantity'
            });
        }
        resId += tdNum;
        tdNum -= 2;
        content += RESOURCE_ROW_VIEW({
            leftContent: leftRow,
            span: (i * 4 + 1),
            rightContent: rightRow
        });
    }
    setView("resource_container", RESOURCE_TABLE_VIEW(content));
}

function createResourceList(dataObject, resPerMinList, elemId) {
    var userResourceList = JSON.parse(dataObject);
    var num = 1;
    var power = 0;
    for (var i in userResourceList) {
        var id = userResourceList[i]['resourceId'];
        var quantity = userResourceList[i]['resourceNumber']; //['quantity'];
        var perMin = userResourceList[i]['resourcePerMin'];
        resPerMinList.push({id: id, quantity: quantity, per_min: perMin});
        for (var j = num; j < id; j++) {
            document.getElementById(elemId + j).style.visibility = 'hidden';
        }
        if (resourceFullList[id]['id'] == POWER) {
            power = quantity;
        }
        num = id + 1;
    }
    return power;
}

function createUserResourceList(dataObject) {
    resourcePerMin = [];
    myPower = createResourceList(dataObject, resourcePerMin, "p1res");
    createKings();
    lastUserDate = new Date();
}

function createEnemyResourceList(dataObject) {
    resourceEnemyPerMin = [];
    enemyPower = createResourceList(dataObject, resourceEnemyPerMin, "p2res");
    createKings();
    lastEnemyDate = new Date();
}

function prepareResourceTooltip(num) {
    setView("tooltip_component", RESOURCE_TOOLTIP_VIEW({
        name: resourceFullList[num]['name'].split('_').join(' '),
        description: resourceFullList[num]['description']
    }));
}

function createKings() {
    if (myPower >= 100 && enemyPower < 100) {
        myImage = IMG_KING_URL.won;
        enemyImage = IMG_KING_URL.lost;
    } else if (myPower < 100 && enemyPower >= 100) {
        myImage = IMG_KING_URL.lost;
        enemyImage = IMG_KING_URL.won;
    } else if (myPower > 0 && enemyPower <= 0) {
        myImage = IMG_KING_URL.won;
        enemyImage = IMG_KING_URL.lost;
    } else if (myPower <= 0 && enemyPower > 0) {
        myImage = IMG_KING_URL.lost;
        enemyImage = IMG_KING_URL.won;
    }
    setView("left_king", getCylinder(myPower, myImage, "left-chicken-img"));
    setView("right_king", getCylinder(enemyPower, enemyImage, "right-chicken-img"));
}

function getCylinder(percent, img, className) {
    percent = percent % 100;
    return KING_VIEW({
        cylinderSize: percent * 3,
        cylinderSizeBottom: (-(percent * 3) - 15),
        percent: percent,
        img: img,
        className: className,
        power: (percent * 3 - 8)
    });
}