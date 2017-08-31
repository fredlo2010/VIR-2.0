package com.vir.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.vir.model.Text;
import com.vir.service.TextProcessorService;

@RestController
@RequestMapping("/analize")
public class AnalizerController {

	@Autowired
	private TextProcessorService textProcessorService;

	@PostMapping(params = "type=text", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public Text analizeText(@RequestBody(required = true) String text) {
		return textProcessorService.process(text);
	}

	@PostMapping(params = "type=pdf", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public Text analizePdf(
			@RequestParam(name = "file", required = true) MultipartFile file,
			@RequestParam(name = "type", required = true) String type) {
		
		return null ;
	}
}