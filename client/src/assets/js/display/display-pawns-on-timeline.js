"use strict";

function getPawnLocation(game) {
    fetchFromServer('/tiles', 'GET')
        .then(tiles => {
                const $timeline = document.querySelectorAll('.timeline div');
                $timeline.forEach(tile => {
                    game.players.forEach(player => {
                        const currentTile =  tiles.find(index => index.position === player.position);
                        if (parseInt(tile.dataset.tileid) === parseInt(currentTile.position)) {
                            tile.style.borderColor = `${player.name}`;
                        }
                    });
                });
        });
}
