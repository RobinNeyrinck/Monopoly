"use strict";

function getAllPlayerInfo() {
    fetchFromServer(`/games/${_gameId}`)
        .then(game => {
            const opponentsList = [];
            const yourColorInfo = game.players.find(index => index.name === _playerName);
            game.players.forEach(function (indexOfPlayers) {
                if (indexOfPlayers.name !== _playerName && indexOfPlayers.bankrupt) {
                    opponentsList.push(indexOfPlayers);
                }
            });
            if (yourColorInfo.bankrupt && game.ended) {
                displayLoser();
            }
            checkForPlayerBankruptcy(opponentsList, game);
        });
}

function checkForPlayerBankruptcy(opponentsList, game) {
    if (opponentsList.length === game.numberOfPlayers - 1) {
        displayWinner();
    }
}

function displayWinner(){
    document.querySelector("#leave-game").classList.remove("hidden");
    displayEndGameScreen("YOU WIN!!", "winning-screen");
}

function displayLoser(){
    document.querySelector("#leave-game").classList.remove("hidden");
    displayEndGameScreen("YOU LOSE!!", "losing-screen");
}

function displayEndGameScreen(title, src){
    const $winOrLoseScreen = document.querySelector('#end-game-screen');
    const $html = `<h2>${title}</h2>
                <img src="assets/media/${src}.png" alt="${src}" title="${src}">`;
    $winOrLoseScreen.insertAdjacentHTML("beforeend", `${$html}`);
    showActiveUiElement("#end-game-screen");
}
