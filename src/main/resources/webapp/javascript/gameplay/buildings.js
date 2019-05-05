var buildingFullList = {};

const IMG_BUILDINGS_URL = {
    "1": {url:"images/buildings/granary_building.png", x: -10, y: 325}, // Амбар
    "2": {url:"images/buildings/chicken_house_building.png", x: 125, y: 335}, // Курятник
    "3": {url:"images/buildings/incubator_building.png", x: 250, y: 335}, // Инкубатор
    "4": {url:"images/buildings/cleaning_center_building.png", x: 57, y: 275}, // Клининг Центр
    "5": {url:"images/buildings/chicken_yard_building.png", x: 180, y: 275}, // Куринный Двор
    "6": {url:"images/buildings/cock_house_building.png", x: 300, y: 275}, // Петушатня
    "7": {url:"images/buildings/cock_barrack_building.png", x: -5, y: 230}, // Петушинные бараки
    "8": {url:"images/buildings/quarry_building.png", x: 170, y: 390}, // Каменоломня
    "9": {url:"images/buildings/hammer_stonecutter_building.png", x: 110, y: 210}, // Молото-Каменотес
    "10": {url:"images/buildings/throwing_school_building.png", x: 230, y: 210}, // Школа Метания
    "11": {url:"images/buildings/blast_furnace_building.png", x: 50, y: 190}, // Доменная печь
    "12": {url:"images/buildings/smithy_building.png", x: 300, y: 200}, // Кузница
    "13": {url:"images/buildings/axe_school_building.png", x: 165, y: 175}, // Школа Топора
    "14": {url:"images/buildings/wood_logger_building.png", x: 290, y: 410}, // Лесорубка
    "15": {url:"images/buildings/carpentry_building.png", x: 0, y: 150}, // Плотницкая
    "16": {url:"images/buildings/joinery-building.png", x: 100, y: 120}, // Столярка
    "17": {url:"images/buildings/alchemical_lab_building.png", x: 240, y: 130}, // Алхимическая лаба
    "18": {url:"images/buildings/armory_building.png", x: 0, y: 80}, // Оружейная
    "19": {url:"images/buildings/ballistic_base_building.png", x: 190, y: 70}, // Балистическая лаба
    "20": {url:"images/buildings/sanctuary_building.png", x: 80, y: 50}, // Алтарь
    "21": {url:"images/buildings/magic_school_building.png", x: 0, y: 0}, // Школа колдовства
    "22": {url:"images/buildings/ziggurat_building.png", x: 140, y: 0} // Зиккурат
};

const BUILDING_VIEW = (o) => `
    <div class="building-selector" style="position: absolute; ${o.pos}: ${o.x}px; bottom: ${o.y}px"
        onmousemove="prepareBuildingTooltip(${o.id}); showTooltip(event)" onmouseout="hideTooltip()">
        <img class="building-img" src="${o.url}">
        <span class="big-text-number">${o.number}</span>
    </div>`;

const BUILDING_TOOLTIP_HEADER_VIEW = (o) => `
    <b style="font-size: 20px; color: #ff8000">Building: ${o.name}</b>
    <br>  (${o.description})<br><br>
    <br>This Building produces the following resources:<br>`;

const BUILDING_TOOLTIP_BODY_VIEW = (o) => `
    <img class="small-icon" src="${o.img}">
    ${o.name}  (${o.description})
    <b style="color: ${o.color}">${o.numPerMin}</b> per minute <br>`;

function prepareBuildingFullList(model) {
    var jsonBuildingFullList = JSON.parse(model);
    buildingFullList = {};
    for (var num in jsonBuildingFullList) {
        buildingFullList[jsonBuildingFullList[num]['buildingDto']['id']] = jsonBuildingFullList[num];
    }
}

function createBuildingList(model) {
    setView("building_items", prepareBuildingList(model, "left"));
}

function createEnemyBuildingList(model) {
    setView("enemy_building_items", prepareBuildingList(model, "right"));
}

function prepareBuildingList(model, pos) {
    var userBuildingList = JSON.parse(model);
    var content = "";
    for (var i in userBuildingList) {
        var id = userBuildingList[i]['buildingId'];
        var building = IMG_BUILDINGS_URL[buildingFullList[id]['buildingDto']['id']];
        content += BUILDING_VIEW({
            id: id,
            pos: pos,
            x: building['x'],
            y: building['y'],
            url: building['url'],
            number: userBuildingList[i]['number']
        });
    }
    return content;
}

function prepareBuildingTooltip(num) {
    var content = BUILDING_TOOLTIP_HEADER_VIEW({
        name: buildingFullList[num]['buildingDto']['name'].split('_').join(' '),
        description: buildingFullList[num]['buildingDto']['description']
    });
    for (var numRes in buildingFullList[num]['productDtoList']) {
        content += BUILDING_TOOLTIP_BODY_VIEW({
            img: IMG_RESOURCES_URL[buildingFullList[num]['productDtoList'][numRes]['id']],
            name: buildingFullList[num]['productDtoList'][numRes]['name'],
            description: buildingFullList[num]['productDtoList'][numRes]['description'],
            color: (buildingFullList[num]['productDtoList'][numRes]['numPerSec'] > 0 ? '#7cff03' : '#ff8000'),
            numPerMin: buildingFullList[num]['productDtoList'][numRes]['numPerSec']
        });
    }
    setView("tooltip_component", content);
}