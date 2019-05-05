const ACHIEVEMENTS_TABLE_BODY = (o) => `
    <tr>
        <td>${o.id}</td>
        <td>${o.name}</td>
        <td>${o.description}</td>
    </tr>`;

function showAchievements() {
    endpoints.UserAchievementController.getUserAchievementsList({userId: currentUser['id']}, function(model) {
        var achievementTableBody = "";
        for (var x in model) {
            achievementTableBody += ACHIEVEMENTS_TABLE_BODY({
                id: model[x]['id'],
                name: model[x]['name'],
                description: model[x]['description']
            });
        }
        if(!achievementTableBody) return;
        setView("achievementTableBody", achievementTableBody);
    });
}

//=============================
//============START============
//=============================
showAchievements();


