package com.example.paymentapi;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@SpringBootTest
class ApiSpringBootPaymentApplicationTests {

	@Test
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
	}

}
