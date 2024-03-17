"use strict";

function checkIfUserIsJailed(game){
    const playerInfo = game.players.find(player => player.name === _playerName);
    if (playerInfo.jailed){
        document.querySelector(_jailButtons).classList.remove('hidden');
        document.querySelector('#pay-fine').addEventListener('click', () => payFine);
        document.querySelector('#jail-card').addEventListener('click', () => useJailCard);
    }
}

function payFine(){
    fetchFromServer(`/games/${_gameId}/prison/${_playerName}/fine`, 'POST')
        .then(res => console.log(res));
    document.querySelector(_jailButtons).classList.add('hidden');
}

function useJailCard(){
    fetchFromServer(`/games/${_gameId}/prison/${_playerName}/free`, 'POST')
        .then(res => console.log(res));
    document.querySelector(_jailButtons).classList.add('hidden');
}
