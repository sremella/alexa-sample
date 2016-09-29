package com.sremella;

import com.amazon.speech.speechlet.Speechlet;
import com.amazon.speech.speechlet.servlet.SpeechletServlet;
import com.sremella.alexa.DefaultSpeechlet;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;

import javax.servlet.Servlet;

@SpringBootApplication
@EnableAutoConfiguration
public class AlexaSampleApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(AlexaSampleApplication.class, args);
	}

	@SuppressWarnings("serial")
	@Bean
	public Servlet alexaServlet() {
		SpeechletServlet servlet = new SpeechletServlet();
		servlet.setSpeechlet(defaultSpeechlet());
		return servlet;
	}

	@Bean
	public Speechlet defaultSpeechlet() {
		return new DefaultSpeechlet();
	}

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(AlexaSampleApplication.class);
	}
}
