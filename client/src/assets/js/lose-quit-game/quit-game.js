"use strict";

function quitGame(e){
    e.preventDefault();
    makeUserBankrupt();
    localStorage.clear();
    document.querySelector('#pawn-page').classList.remove('hidden');
    document.querySelector('.basic-info').classList.add('hidden');
    document.querySelector('.timeline-container').classList.add('hidden');
    location.reload();
}

function makeUserBankrupt(){
    fetchFromServer(`/games/${_gameId}/players/${_playerName}/bankruptcy`, 'POST')
        .then(res => console.log(res));
}
