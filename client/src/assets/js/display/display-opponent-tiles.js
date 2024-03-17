"use strict";

function createOpponentTiles() {
    fetchFromServer(`/games/${_gameId}`)
        .then(gameState => gameState.players.forEach(function (player) {
            if (player.name !== _playerName) {
                const $html =
                    `    <article>
                            <div class="pawn"><img src="assets/media/${player.name}.png" title="blue" alt="title"></div>
                            <div class="opponentInfo">
                                <h3>${player.name}</h3>
                                <p>Balance: ${player.money}</p>
                                <p> JailCards: ${player.getOutJailCards} </p>
                            </div>
                        </article>`;
                document.querySelector('section#opponents').insertAdjacentHTML('beforeend', $html);
            }
        }));
}
