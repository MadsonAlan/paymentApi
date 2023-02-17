package com.example.paymentApi;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
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
	public void GetPaymentsListByDebitCode() throws Exception {
		this.mockMvc.perform(get("/payment/filter/debitCode/546521")).andDo(print()).andExpect(status().isOk());
	}
	@Test
	public void GetPaymentsListByPayer() throws Exception {
		this.mockMvc.perform(get("/payment/filter/payer/012.345.678-91")).andDo(print()).andExpect(status().isOk());
	}
	@Test
	public void GetPaymentsListByPaymentStatus() throws Exception {
		this.mockMvc.perform(get("/payment/filter/paymentStatus/Pendente de Processamento")).andDo(print()).andExpect(status().isOk());
	}

}
