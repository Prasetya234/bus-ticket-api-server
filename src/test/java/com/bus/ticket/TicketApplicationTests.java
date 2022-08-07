package com.bus.ticket;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;

import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.util.StopWatch;

import java.util.Date;


@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
class TicketApplicationTests {

	private final static Logger LOGGER = LoggerFactory.getLogger(TicketApplicationTests .class);
	private MockMvc mockMvc;

	@Autowired
	public TicketApplicationTests(MockMvc mockMvc) {
		this.mockMvc = mockMvc;
	}

	@Test
	public void shouldReturnDefaultMessage() throws Exception {
		StopWatch stopWatch = new StopWatch("Testing REST API performances");
		for(int i=1; i<=5; i++) {
			stopWatch.start("Test iteration " + i);
			this.mockMvc.perform(get("/")).andDo(print()).andExpect(status().isOk())
					.andExpect(content().string(containsString("Start Tiket Bus BackEnd Aplication At " + new Date())));
			stopWatch.stop();
		}
		LOGGER.info(stopWatch.prettyPrint());
	}
}
