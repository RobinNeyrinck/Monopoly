"use strict";

function checkIfPlayersAreLoggedIn(){
    if (_token !== null || loadFromStorage('token')){
        startGame();
    } else {
        showActiveUiElement(document.querySelector("#pawn-page"));
    }
}

function displayJoinedGame() {
    const $startPage = document.querySelector(`${_startPage}`);
    const $pawnPage = document.querySelector('#pawn-page');
    document.querySelector('#list-of-games').classList.add("hidden");
    if (!$startPage.classList.contains('hidden')) {
        $startPage.classList.add('hidden');
    }
    if (!$pawnPage.classList.contains('hidden')) {
        $pawnPage.classList.add('hidden');
    }
}

function showActiveUiElement(activeElement){
    _uiElementSelectors.forEach(function(element){
        document.querySelector(element).classList.add('hidden');
    });
    document.querySelector(activeElement).classList.remove('hidden');
}

function startGame(){
    fetchFromServer(`/games/${_gameId}`, 'GET')
        .then(game => console.log(game));
    document.querySelector('.basic-info').classList.remove('hidden');
    document.querySelector('.timeline-container').classList.remove('hidden');
    document.querySelector('#start-page').classList.add('hidden');
    displayJoinedGame();
    getTilesFromServer();
    getAllPlayerInfo();
    createOpponentTiles();
    getAllTileNumbers();
    loseGame();
    refreshUI();
}
