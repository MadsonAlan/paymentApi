package com.example.paymentapi;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
class ApiSpringBootPaymentApplicationTests {

	@Autowired
	private MockMvc mockMvc;

	@Test
	public void GetPaymentsList() throws Exception {
		this.mockMvc.perform(get("/payment/all")).andDo(print()).andExpect(status().isOk());
	}

	@Test
	public void AddNewPayment() throws Exception {
		this.mockMvc.perform(post("/api/cliente/pessoafisica/post")
		.contentType(MediaType.APPLICATION_JSON)
		.content("teste"))
		.andDo(print())
		.andExpect(status().is2xxSuccessful());
	}

}
