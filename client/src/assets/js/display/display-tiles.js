"use strict";

function displayTiles(tiles) {
    tiles.forEach(tile => injectTileHTML(tile));
    document.querySelectorAll('.timeline div').forEach(function (element){
        element.addEventListener('click', selectTile);
    });
}

function injectTileHTML(tile) {
    const $template = document.querySelector('.timeline template').content.firstElementChild.cloneNode(true);
    $template.querySelector('p').innerHTML = tile.position;
    $template.dataset.tileid = tile.position;
    $template.dataset.tileName = tile.name;
    document.querySelector('.timeline').insertAdjacentHTML('beforeend', $template.outerHTML);
}

function getAllTileNumbers(){
    const $tiles = document.querySelectorAll('.timeline div');
    $tiles.forEach(function ($tile){
        $tile.addEventListener('click', selectTile);
    });
}
