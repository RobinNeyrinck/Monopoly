"use strict";

function displayAvailablePawns(){
    const pawnColors = ['Black', 'Blue', 'Green', 'Pink', 'Purple', 'Red', 'White', 'Yellow'];
    let $html = "";
    const $pawnsContainer = document.querySelector("#pawns");
    $pawnsContainer.innerHTML = "";
    pawnColors.forEach(function(pawn){
        $html += `<img src="assets/media/${pawn}.png" alt="${pawn}" title="${pawn}">`;
    });
    $pawnsContainer.insertAdjacentHTML('beforeend', $html);
}
