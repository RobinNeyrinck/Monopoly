"use strict";

function checkIfPlayersHaveProperties(game) {
    const opponents = [];
    game.players.forEach(player => {
        if (player.name !== _playerName) {
            opponents.push(player);
        }
    });
    const yourInfo = game.players.find(player => player.name === _playerName);
    opponentOnProperty(opponents, yourInfo);
}

function opponentOnProperty(opponents, yourInfo) {
    opponents.forEach(opponent => {
        yourInfo.properties.forEach(youPropertyInfo => {
            if (opponent.position === youPropertyInfo.position) {
                const $collectRentBtn = document.querySelector('#collect-rent');
                $collectRentBtn.classList.remove('hidden');
                $collectRentBtn.addEventListener('click', () => receiveRent(_playerName, opponent.position, opponent.name));
            }
        });
    });
}

function receiveRent(yourName, positionOfOpponent, opponentName) {
    fetchFromServer("/tiles", "GET")
        .then(tiles => {
       const nameOfTile = tiles.find(tile => tile.position === positionOfOpponent).name;
           fetchFromServer(`/games/${_gameId}/players/${yourName}/properties/${nameOfTile}/visitors/${opponentName}/rent`, 'DELETE')
               .then(res => console.log(res));
    });
}
