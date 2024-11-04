package com.vinsguru.customerportfolio;

import com.vinsguru.customerportfolio.domain.Ticker;
import com.vinsguru.customerportfolio.domain.TradeAction;
import com.vinsguru.customerportfolio.dto.StockTradeRequest;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.util.Objects;

@SpringBootTest
@AutoConfigureWebTestClient
class CustomerServiceApplicationTests {

    private static final Logger log = LoggerFactory.getLogger(CustomerServiceApplicationTests.class);

    @Autowired
    private WebTestClient client;

    @Test
    void customerInformationTest() {
        getCustomer(1, HttpStatus.OK)
                .jsonPath("$.name").isEqualTo("Sam")
        .jsonPath("$.balance").isEqualTo("10000")
        .jsonPath("$.holdings").isEmpty();
    }

    @Test
    void buyAndSell() {
        var buyRequest = new StockTradeRequest(Ticker.GOOGLE, 100, 5, TradeAction.BUY);
        trade(2, buyRequest, HttpStatus.OK)
                .jsonPath("$.balance").isEqualTo(9500);
    }

    private WebTestClient.BodyContentSpec getCustomer(Integer customerId, HttpStatus expectedStatus) {
        return this.client
                .get()
                .uri("/customers/{customerId}", customerId)
                .exchange()
                .expectStatus().isEqualTo(expectedStatus)
                .expectBody()
                .consumeWith(response -> {
                    log.info("Response: {}", new String(Objects.requireNonNull(response.getResponseBody())));
                });
    }

    private WebTestClient.BodyContentSpec trade(Integer customerId, StockTradeRequest request, HttpStatus expectedStatus) {
        return this.client
                .post()
                .uri("/customers/{customerId}/trade", customerId)
                .bodyValue(request)
                .exchange()
                .expectStatus().isEqualTo(expectedStatus)
                .expectBody()
                .consumeWith(response -> {
                    log.info("Response: {}", new String(Objects.requireNonNull(response.getResponseBody())));
                });
    }

}
