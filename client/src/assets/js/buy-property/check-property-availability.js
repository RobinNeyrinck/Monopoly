"use strict";

function canUserPurchase(game) {
    if (game.currentPlayer.name === _playerName && game.directSale){
        fetchFromServer("/tiles")
            .then(tiles => {
                tiles.forEach(tile => {
                    if (tile.position === game.currentPlayer.position){
                        document.querySelector('#buttons').classList.remove('hidden');
                        document.querySelector('#purchase-property')
                            .addEventListener('click', () => purchaseProperty(tile, game.currentPlayer));
                        document.querySelector('#remove-property')
                            .addEventListener('click', () => removeProperty(tile, game.currentPlayer));
                    }
                });
            });
    }
}
