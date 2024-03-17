"use strict";

function refreshUI() {
    fetchFromServer(`/games/${_gameId}`)
        .then(game => {
            getPawnLocation(game);
            displayCurrentTile(game);
            canUserPurchase(game);
            getNameOfPropertyAfterBuying(game);
            setUpInventory(game);
            showButtonRollDiceWhenTurn(game);
            checkForCurrentBalance(game);
            checkIfUserIsJailed(game);
            checkIfPlayersHaveProperties(game);
            payTaxes(game);
        });
    setTimeout(() => refreshUI(), _config.timeDelay);
}
