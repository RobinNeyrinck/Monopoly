"use strict";

const _taxPosition1 = 4;
const _taxPosition2 = 38;

function payTaxes(game){
    const didntPayTax = !game.currentPlayer.hasPayedTax;
    const currentPlayer = game.currentPlayer.name === _playerName;
    const onTaxTile = game.currentPlayer.position === _taxPosition1 || game.currentPlayer.position === _taxPosition2;
    if (currentPlayer && didntPayTax && onTaxTile){
        fetchFromServer(`/games/${_gameId}/players/${_playerName}/tax/estimate`, "POST")
            .then(res => console.log(res));
    }
}
