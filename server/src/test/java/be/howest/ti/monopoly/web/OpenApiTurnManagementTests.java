package be.howest.ti.monopoly.web;

import be.howest.ti.monopoly.logic.ServiceAdapter;
import be.howest.ti.monopoly.logic.implementation.Game;
import io.vertx.junit5.VertxTestContext;
import org.junit.jupiter.api.Test;


class OpenApiTurnManagementTests extends OpenApiTestsBase {

    @Test
    void rollDice(final VertxTestContext testContext) {
        service.setDelegate(new ServiceAdapter(){
            @Override
            public Game rollDice(String gameID, String playerName) {
                return null;
            }
        });
        post(
                testContext,
                "/games/001/players/Alice/dice",
                "001-Alice",
                response -> assertOkResponse(response)
        );
    }

    @Test
    void rollDiceUnauthorized(final VertxTestContext testContext) {
        post(
                testContext,
                "/games/game-id/players/Alice/dice",
                null,
                response -> assertErrorResponse(response, 401)
        );
    }

    @Test
    void declareBankruptcy(final VertxTestContext testContext) {
        service.setDelegate(new ServiceAdapter(){
            @Override
            public Game declareBankruptcy(String gameId, String playerName) {
                return null;
            }
        });

        post(
                testContext,
                "/games/000/players/Alice/bankruptcy",
                "000-Alice",
                response -> assertOkResponse(response)
        );
    }

    @Test
    void declareBankruptcyUnauthorized(final VertxTestContext testContext){

        service.setDelegate(new ServiceAdapter(){
            @Override
            public Game declareBankruptcy(String gameId, String playerName) {
                return null;
            }
        });

        post(
                testContext,
                "/games/game-id/players/Alice/bankruptcy",
                null,
                response -> assertErrorResponse(response, 401)
        );
    }
}
