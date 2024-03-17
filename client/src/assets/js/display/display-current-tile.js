"use strict";

function displayCurrentTile(game) {
    const playerInfo = game.players.find(index => index.name === _playerName);
    checkCurrentTile(playerInfo);
}

function checkCurrentTile(currentPlayer) {
    const $displayCurrentTile = document.querySelector('.basic-info div.current-tile');
    fetchFromServer('/tiles', 'GET').then(tiles => {
        $displayCurrentTile.innerHTML = "";
        const tile = tiles.find(tile => tile.position === currentPlayer.position);
        switch (tile.type){
            case "Street":
                $displayCurrentTile.insertAdjacentHTML('beforeend', displayStreet(tile));
                break;
            case "RailRoad":
                $displayCurrentTile.insertAdjacentHTML('beforeend', displayRailRoadOrUtility(tile));
                break;
            case "Utility":
                $displayCurrentTile.insertAdjacentHTML('beforeend', displayRailRoadOrUtility(tile));
                break;
            case "Jail":
                $displayCurrentTile.insertAdjacentHTML('beforeend', displayJail(tile));
                break;
            default:
                $displayCurrentTile.insertAdjacentHTML('beforeend', displayNonPurchasableTiles(tile));
                break;
        }
    });
}

function displayRailRoadOrUtility(tile) {
    let $html = "";
    $html = `
             <h4>${tile.name}</h4>
             <h5>Cost: $${tile.cost}</h5>`;
    return $html;
}

function displayStreet(tile) {
    let $html = "";
    $html = `<h4>${tile.name}</h4>
             <h5>Cost: $${tile.cost}</h5>
            <h5>Rent: $${tile.rent}</h5>`;
    return $html;
}

function displayJail(tile) {
    let $html = "";
    $html = `<h4>${tile.name}</h4>`;
    return $html;
}

function displayNonPurchasableTiles(tile) {
    let $html = "";
    $html = `
                <h4>${tile.name}</h4>`;
    return $html;
}
