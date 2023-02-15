package com.example.paymentapi;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
<<<<<<< HEAD
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
=======
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
>>>>>>> 2d1f700edc109c0b766868b7a8c57788c5316af3

@SpringBootTest
@AutoConfigureMockMvc
class ApiSpringBootPaymentApplicationTests {

	@Autowired
	private MockMvc mockMvc;

	@Test
<<<<<<< HEAD
	void contextLoads() {
		PatientRecord record = PatientRecord.builder()
            .name("John Doe")
            .age(47)
            .address("New York USA")
            .build();

    Mockito.when(patientRecordRepository.save(record)).thenReturn(record);

    MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.post("/patient")
            .contentType(MediaType.APPLICATION_JSON)
            .accept(MediaType.APPLICATION_JSON)
            .content(this.mapper.writeValueAsString(record));

    mockMvc.perform(mockRequest)
            .andExpect(status().isOk())
            .andExpect(jsonPath("$", notNullValue()))
            .andExpect(jsonPath("$.name", is("John Doe")));
    }
=======
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
>>>>>>> 2d1f700edc109c0b766868b7a8c57788c5316af3
	}

}
