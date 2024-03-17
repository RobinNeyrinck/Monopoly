"use strict";

function setUpInventory(game) {
    displayOwnPawn();
    displayOwnOwnedProperties(game);
    loopOverPlayersToFindOwnOutOfJailCards(game);
    displayBalance(game);
}

function displayOwnPawn() {
    const $playerPawn = document.querySelector('#personal-pawn');
    $playerPawn.setAttribute('src', `assets/media/${_playerName}.png`);
}

function displayOwnOwnedProperties(game) {
    const players = game.players;
    const $assets = document.querySelector('#owned-assets');
    $assets.innerHTML = "";
    let $html = '';
    players.forEach(player => {
        if (player.name === _playerName) {
            if (player.properties.length === 0) {
                $html = `<li>No properties bought</li>`;
            } else {
                player.properties.forEach(property => {
                    $html += `<li>${property.name}</li>`;
                });
            }
        }
    });
    $assets.insertAdjacentHTML('beforeend', $html);
}

function loopOverPlayersToFindOwnOutOfJailCards(game) {
    const $outOfJailCards = document.querySelector('div#right-side-inventory p');
    game.players.forEach(player => {
        if (player.name === _playerName){
            $outOfJailCards.innerHTML = `Out of jail cards: ${player.getOutJailCards}`;
        }
    });
}

function displayBalance(game){
    for (let i = 0; i < game.numberOfPlayers; i++) {
        if (game.players[i].name === _playerName){
            const playerBalance = game.players[i].money;
            document.querySelector('#left-side-inventory p').innerHTML = `Balance: $${playerBalance}`;
        }
    }
}
