"use strict";


function addClickEventToPawnSelection() {
    displayAvailablePawns();
    const $pawns = document.querySelectorAll(_pawnsIdImages);
    $pawns.forEach(function ($pawn) {
        $pawn.addEventListener('click', choosePawn);
    });
}

function choosePawn(e) {
    const $pawns = document.querySelectorAll(_pawnsIdImages);
    const pawnClassSelected = 'selected-pawn';
    $pawns.forEach(function ($pawn) {
        const pawnColor = e.target.getAttribute('alt');
        if ($pawn.classList.contains(pawnClassSelected)) {
            $pawn.classList.remove(pawnClassSelected);
            $pawn.setAttribute('src', `assets/media/${$pawn.getAttribute('alt')}.png`);
        }
        e.target.classList.add(pawnClassSelected);
        e.target.setAttribute('src', `assets/media/${pawnColor}-selected.png`);
    });
    document.querySelector("#confirm-color").addEventListener("click", chooseAmountOfPlayers);
}

function chooseAmountOfPlayers() {
    showActiveUiElement(_startPage);
    const $amountOfPlayersButton = document.querySelectorAll('#start-page ul li button');
    $amountOfPlayersButton.forEach(function ($button) {
        $button.addEventListener('click', getValueFromButton);
    });
    document.querySelector('#go-back').classList.remove('hidden');
}

function getValueFromButton(e) {
    document.querySelector('#go-back').classList.add('hidden');
    e.target.classList.add('selected-amount');
    showActiveUiElement(_listOfGames);
    processConnection();
}

function processConnection() {
    const amountOfPlayers = parseInt(document.querySelector('.selected-amount').value);
    const colorOfPlayer = document.querySelector('.selected-pawn').getAttribute('alt');
    fetchFromServer("/games", "GET")
        .then(res => console.log(res));
    saveToStorage("playerColor", colorOfPlayer);
    createGame(amountOfPlayers);
}

function createGame(amountOfPlayers) {
    const bodyParams = {
        "prefix": _config.gamePrefix,
        "numberOfPlayers": amountOfPlayers
    };
    fetchFromServer('/games', 'POST', bodyParams).then(game => {
        console.log(game);
    });
    fetchFromServer('/games', "GET")
        .then(games => displayGamesInList(games));
}

function displayGamesInList(games){
    showActiveUiElement(_listOfGames);
    const $listOfGames = document.querySelector("#list-of-games ul");
    $listOfGames.innerHTML = "";
    games.forEach(function(game) {
        const $html = `<li> ${game.gameId} -> ${game.players.length}/${game.numberOfPlayers}
        <button type="button" id="${game.gameId}"> JOIN </button></li>`;
        $listOfGames.insertAdjacentHTML("beforeend", $html);
    });
    document.querySelectorAll("#list-of-games ul button").forEach(function($button){
        const buttonId = $button.getAttribute('id');
        $button.addEventListener("click", () => joinGame(buttonId, loadFromStorage("playerColor")));
    });
}

function joinGame(gameId, colorOfPlayer) {
    const requestBody = {
        "playerName": colorOfPlayer
    };
    fetchFromServer(`/games/${gameId}/players`,'POST',requestBody)
        .then(game => {
            _token = game.playerToken;
            saveToStorage('token', game.playerToken);
            saveToStorage('gameId', `${gameId}`);
            saveToStorage('playerColor', `${colorOfPlayer}`);
            console.log(game.playerToken);
            console.log(gameId);
            console.log(colorOfPlayer);
            checkIfGameStarted(gameId);
        });
}

function checkIfGameStarted(gameId){
    fetchFromServer(`/games/${gameId}`,'GET')
        .then(gameState => {
            console.log(gameState);
            if (gameState.started){
                checkIfPlayersAreLoggedIn();
            } else {
                setTimeout(()=> checkIfGameStarted(gameId), _config.timeDelay);
            }
        });
}

function goBack(e){
    e.preventDefault();
    const $pawns = document.querySelectorAll('#pawns img');
    $pawns.forEach(function ($pawn){
        $pawn.setAttribute('src', `assets/media/${$pawn.getAttribute('alt')}.png`);
    });
    showActiveUiElement('#pawn-page');
}

function goBack2(e){
    e.preventDefault();
    showActiveUiElement("#start-page");
}
