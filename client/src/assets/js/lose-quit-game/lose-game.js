'use strict';

function loseGame() {
    fetchFromServer(`/games/${_gameId}`)
        .then(game => {
            game.players.forEach(player => {
                if (player.name === _playerName) {
                    if (player.bankrupt) {
                        document.querySelector('.current-tile article').classList.add('lost-game');
                        document.querySelector('.inventory article').classList.add('lost-game');
                        hideButtons();
                        opponentLostGame(player);
                    }
                }
            });
        });
}

function hideButtons() {
    document.querySelectorAll('.current-tile button').forEach(button => {
        button.style.pointerEvents = "none";
    });
    document.querySelectorAll('.inventory button').forEach(button => {
        button.style.pointerEvents = "none";
    });
}

function opponentLostGame(player) {
    const bankruptPlayers = [];
    const $opponents = document.querySelectorAll('section#opponents article');
    bankruptPlayers.push(player.name);
    $opponents.forEach(opponent => {
        bankruptPlayers.forEach(player => {
            if (player === opponent.querySelector('div h3').innerText) {
                opponent.classList.add('lost-game');
            }
        });
    });
}
