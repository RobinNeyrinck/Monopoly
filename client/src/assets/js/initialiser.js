"use strict";

let _token = null;
const _pawnsIdImages = "#pawns img";
const _startPage = "#start-page";
const _listOfGames = "#list-of-games";
const _jailButtons = "#jail-buttons";
const _playerName = loadFromStorage('playerColor');
const _gameId = loadFromStorage('gameId');
const _uiElementSelectors = [_startPage, '#pawn-page', _listOfGames, '.timeline-container', '.basic-info', '#end-game-screen'];

document.addEventListener('DOMContentLoaded', init);

function init() {
    if (loadFromStorage('token')) {
        _token = loadFromStorage('token');
        checkIfPlayersAreLoggedIn();
    }
    addClickEventToPawnSelection();
    getAllTileNumbers();
    document.querySelector('#dice').addEventListener('click', rollDice);
    document.querySelector('#go-back').addEventListener('click', goBack);
    document.querySelector('#go-back2').addEventListener('click', goBack2);
    document.querySelector('button#quit-game').addEventListener('click', quitGame);
    document.querySelector('#leave-game').addEventListener("click", quitGame);
}

function getTilesFromServer() {
    fetchFromServer('/tiles', 'GET')
        .then(tiles => {
            _config.tiles = tiles;
            displayTiles(tiles);
        }).catch(errorHandler);
}
