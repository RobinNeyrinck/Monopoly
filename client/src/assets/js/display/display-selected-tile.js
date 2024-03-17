"use strict";

function selectTile(e) {
    e.preventDefault();
    const id = e.currentTarget.dataset.tileid;
    const tiles = _config.tiles;
    const $selectedTile = document.querySelector('#selected-tile-div');
    $selectedTile.innerHTML = "";
    const clickedTile = tiles[id];
    document.querySelector('section.selected-tile').classList.remove("hidden");
    let $html = "";

    if (clickedTile.type === "Street" || clickedTile.type === "RailRoad" || clickedTile.type === "Utility") {
        $html = `
            <article class="current-tile">
                 <h3>${clickedTile.name}</h3>
                 <h5>Cost: $${clickedTile.cost}</h5>
                 <h5>Rent: $${clickedTile.rent}</h5>
            </article>`;
    } else {
        $html = `<h4>${clickedTile.name}</h4>`;
    }
    $selectedTile.insertAdjacentHTML('beforeend', $html);
}
