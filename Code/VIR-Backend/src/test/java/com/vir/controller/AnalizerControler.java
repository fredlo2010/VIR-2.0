package com.vir.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.fileUpload;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class AnalizerControler {

	// @formatter:off
	
	@Autowired
	private MockMvc mockMvc;

	@Test
	public void analizeText_Endpoint_Returns200Ok() throws Exception {
		final String url = "/analize?type=text";
		final String word = "test";
		final String expected = "{\"words\":[{\"id\":1308,\"value\":\"test\",\"category\":\"hi\",\"initialValue\":null}]}";
		mockMvc.perform(post(url).content(word))
			.andDo(print())
			.andExpect(status().isOk())
			.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
			.andExpect(content().json(expected));
	}

	@Test
	public void analizePdf_Endpoint_Returns200Ok() throws Exception {
		final String url = "/analize?type=pdf";
		final MockMultipartFile file = new MockMultipartFile("file", "myFile.txt", "text/plain", "hello".getBytes());
		mockMvc.perform(
				fileUpload(url)
				.file(file)
				.param("type", "pdf"))
			.andDo(print())
			.andExpect(status().isOk());
	}
	// @formatter:on
}
