package be.howest.ti.monopoly.web;

import be.howest.ti.monopoly.logic.ServiceAdapter;
import io.vertx.junit5.VertxTestContext;
import org.junit.jupiter.api.Test;


class OpenApiPrisonTests extends OpenApiTestsBase {

    @Test
    void getOutOfJailFine(final VertxTestContext testContext) {
        service.setDelegate(new ServiceAdapter(){
            @Override
            public boolean getOutOfJailFine(String gameId, String playerName) {
                return true;
            }
        });
        post(
                testContext,
                "/games/000/prison/Alice/fine",
                "000-Alice",
                response -> assertOkResponse(response)
        );
    }

    @Test
    void getOutOfJailFineUnauthorized(final VertxTestContext testContext) {
        service.setDelegate(new ServiceAdapter(){
            @Override
            public boolean getOutOfJailFine(String gameId, String playerName) {
                return true;
            }
        });
        post(
                testContext,
                "/games/game-id/prison/Alice/fine",
                null,
                response -> assertErrorResponse(response, 401)
        );
    }

    @Test
    void getOutOfJailFree(final VertxTestContext testContext) {
        service.setDelegate(new ServiceAdapter() {
            @Override
            public boolean useOutOfJailCard(String gameId, String playerName) {
                return true;
            }
        });
        post(
                testContext,
                "/games/000/prison/Alice/free",
                "000-Alice",
                response -> assertOkResponse(response)
        );
    }

    @Test
    void getOutOfJailFreeUnauthorized(final VertxTestContext testContext) {
        post(
                testContext,
                "/games/game-id/prison/Alice/free",
                null,
                response -> assertErrorResponse(response, 401)
        );
    }
}
