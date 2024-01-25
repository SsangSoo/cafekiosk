package sample.cafekiosk.spring.api.controller.product;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import sample.cafekiosk.spring.api.controller.product.dto.request.ProductCreateRequest;
import sample.cafekiosk.spring.api.service.product.ProductService;
import sample.cafekiosk.spring.domain.ProductSellingStatus;
import sample.cafekiosk.spring.domain.ProductType;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(controllers = ProductController.class) // 컨트롤러를 테스트하기 위해 컨트롤러 관련 빈들만 올릴 수 있는 가벼운 애너테이션이다. 테스트 하고 하자는 컨트롤러를 명시해준다.
class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc; // 서비스 Layer 하위로는 Mocking처리를 할 것이고, 그를 도와주는 프레임워크로 MockMvc가 있다.

    @MockBean   // 스프링 컨테이너에 Mock 객체를 넣어주는 역할을 한다.
    private ProductService productService;  // MockBean을 넣어주지 않으면, @WebMvcTest 를 사용할 때 Service 클래스가 없다고 나올 것이다.

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @DisplayName("신규 상품을 등록한다.")
    void createProduct() throws Exception {
        // given
        ProductCreateRequest request = ProductCreateRequest.builder()
                .type(ProductType.HANDMADE)
                .sellingStatus(ProductSellingStatus.SELLING)
                .name("아메리카노")
                .price(4000)
                .build();


        // when // then
        mockMvc.perform(post("/api/v1/products/new")
                        .content(objectMapper.writeValueAsString(request))
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().isOk());

    }


}