"use strict";

function purchaseProperty(tile, player) {
    if (tile.cost <= player.money) {
        fetchFromServer(`/games/${_gameId}/players/${player.name}/properties/${tile.name}`, 'POST')
            .then(res => console.log(res));
        addHiddenClass();
    }
}

function addHiddenClass() {
    document.querySelector('section.current-tile div#buttons').classList.add('hidden');
}

function removeProperty(tile, player) {
    fetchFromServer(`/games/${_gameId}/players/${player.name}/properties/${tile.name}`, 'DELETE')
        .then(res => console.log(res));
    addHiddenClass();
}

function getNameOfPropertyAfterBuying(game) {
    game.players.forEach(function (player) {
        player.properties.forEach(function (propertyOfPlayer) {
            claimPropertyDisplay(propertyOfPlayer.position, player.name);
        });
    });
}

function claimPropertyDisplay(propertyOfPlayer, playerColor) {
    const $timeline = document.querySelectorAll('.timeline div');
    $timeline.forEach(function (tile) {
        if (parseInt(tile.dataset.tileid) === propertyOfPlayer) {
            tile.style.backgroundColor = `${playerColor}`;
        }
    });
}
