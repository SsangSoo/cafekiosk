package sample.cafekiosk.unit.beverage;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

class AmericanoTest {

    @Test
    void getName() {
        Americano americano = new Americano();

        // 이름이 "아메리카노"인지 검증
        assertEquals(americano.getName(), "아메리카노"); // JUnit5의 API
        assertThat(americano.getName()).isEqualTo("아메리카노"); // AssertJ의 API
    }

    @Test
    void getPrice() {
        Americano americano = new Americano();

        assertEquals(americano.getPrice(), 4000);       // JUnit5의 API
        assertThat(americano.getPrice()).isEqualTo(4000);   // AssertJ의 API
    }

}