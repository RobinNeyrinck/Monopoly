"use strict";

function checkForCurrentBalance(game) {
    const player = game.players.find(index => index.name === _playerName);
    if (player.money <= 0) {
        fetchFromServer(`/games/${_gameId}/players/${_playerName}/bankruptcy`, 'POST')
            .then(res => console.log(res));
    }
}
