const ROOM_TABLE_BODY = (o) => `
    <tr>
        <td>${o.id}</td>
        <td>${o.name}</td>
        <td>${o.description}</td>
        <td>${o.user1}</td>
        <td>${o.startGameTime}</td>
        <td>${o.user2}</td>
    </tr>`;

const JOIN_GAME_CELL = (id) => `<button class="room-button" onclick="endpoints.RoomController.joinRoom(${id})">Join Game</button>`;

const IN_GAME_CELL_1 = () => `<img src="images/power_chicken_plaing.png" width="50px" height="50px"
    style="margin-bottom: -20px; margin-top: -20px; -webkit-transform: scaleX(-1); transform: scaleX(-1);">`;

const IN_GAME_CELL_2 = () => `<img src="images/power_chicken_plaing.png" width="50px" height="50px"
    style="margin-bottom: -20px; margin-top: -20px">`;


function showRooms() {
    requestRoomsTable();
    setInterval(requestRoomsTable, 5000);
}

function requestRoomsTable() {
    endpoints.RoomController.getAllRoomList((model) => {
        var roomsTableBody = "";
        for (var x in model) {
            roomsTableBody += ROOM_TABLE_BODY({
                id: model[x]['id'],
                name: model[x]['name'],
                description: model[x]['description'],
                user1: (model[x]['user1'] == undefined ? JOIN_GAME_CELL(model[x]['id']) : IN_GAME_CELL_1),
                startGameTime: model[x]['startGameTime'],
                user2: (model[x]['user2'] == undefined ? JOIN_GAME_CELL(model[x]['id']) : IN_GAME_CELL_2)
            });
        }
        if(!roomsTableBody) return;
        setView("roomsTableBody", roomsTableBody);
    });
}

//=============================
//============START============
//=============================
showRooms();

