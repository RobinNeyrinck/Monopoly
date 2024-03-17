"use strict";

function rollDice() {
    fetchFromServer(`/games/${_gameId}`, 'GET')
        .then(game => checkIfPlayerIsOnTurn(game));
}

function checkIfPlayerIsOnTurn(game) {
    if (_playerName === game.currentPlayer.name) {
        postRoll();
    }
}

function showButtonRollDiceWhenTurn(game) {
        if (_playerName !== game.currentPlayer.name) {
            document.querySelector('#dice').classList.add('hidden');
        } else {
            document.querySelector('#dice').classList.remove('hidden');
        }
}

function postRoll() {
    fetchFromServer(`/games/${_gameId}/players/${_playerName}/dice`, 'POST')
        .then(game => {
            displayDices(game);
        });
}

function displayDices(game) {
    const firstDice = game.lastRoll.lastRoll[0];
    const secondDice = game.lastRoll.lastRoll[1];

    const $diceImage = document.querySelector('#last-roll');
    $diceImage.innerHTML = '';
    let $html = "";
    $html += `<img src="images/dice-${firstDice}.png" alt="${firstDice}" title="${firstDice}">
                <img src="images/dice-${secondDice}.png" alt="${secondDice}" title="${secondDice}">`;
    $diceImage.insertAdjacentHTML('beforeend', $html);

}
