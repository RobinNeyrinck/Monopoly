package be.howest.ti.monopoly.web;

import be.howest.ti.monopoly.logic.ServiceAdapter;
import be.howest.ti.monopoly.logic.implementation.Game;
import io.vertx.junit5.VertxTestContext;
import org.junit.jupiter.api.Test;


class OpenApiBuyingPropertyTests extends OpenApiTestsBase {

    @Test
    void buyProperty(final VertxTestContext testContext) {
        service.setDelegate(new ServiceAdapter(){
            @Override
            public boolean buyProperty(String gameId, String playerName, String propertyName) {
                return true;
            }
        });
        post(
                testContext,
                "/games/000/players/Alice/properties/some-property",
                "000-Alice",
                response -> assertOkResponse(response)
        );
    }

    @Test
    void buyPropertyUnauthorized(final VertxTestContext testContext) {
        service.setDelegate(new ServiceAdapter(){
            @Override
            public boolean buyProperty(String gameId, String playerName, String propertyName) {
                return false;
            }
        });
        post(
                testContext,
                "/games/game-id/players/Alice/properties/some-property",
                null,
                response -> assertErrorResponse(response, 401)
        );
    }

    @Test
    void dontBuyProperty(final VertxTestContext testContext) {
        service.setDelegate(new ServiceAdapter(){
            @Override
            public boolean dontBuyProperty(String gameId, String playerName, String propertyName) {
                return false;
            }
        });
        delete(
                testContext,
                "/games/000/players/Alice/properties/some-property",
                "000-Alice",
                response -> assertOkResponse(response)
        );
    }

    @Test
    void dontBuyPropertyUnauthorized(final VertxTestContext testContext) {
        service.setDelegate(new ServiceAdapter(){
            @Override
            public boolean dontBuyProperty(String gameId, String playerName, String propertyName) {
                return false;
            }
        });
        delete(
                testContext,
                "/games/game-id/players/Alice/properties/some-property",
                null,
                response -> assertErrorResponse(response, 401)
        );
    }
}
