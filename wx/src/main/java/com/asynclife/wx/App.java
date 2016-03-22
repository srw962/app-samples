package com.asynclife.wx;

import java.nio.charset.Charset;
import java.util.ListIterator;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.embedded.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import com.asynclife.wx.config.AppConfig;
import com.asynclife.wx.filter.MyFilter;

/**
 * Hello world!
 *
 */
@SpringBootApplication
public class App {
	
	public static void main(String[] args) {
		 SpringApplication.run(App.class, args);
	}
	
	@Bean
	public RestTemplate restTemplateUseUtf8() {
		RestTemplate restTemplate = new RestTemplate();
		for(ListIterator<HttpMessageConverter<?>> iter = restTemplate.getMessageConverters().listIterator(); iter.hasNext(); ) {
			HttpMessageConverter<?> covt = iter.next();
			if(covt instanceof StringHttpMessageConverter) {
				iter.remove();
				iter.add(new StringHttpMessageConverter(Charset.forName(AppConfig.DEFAULT_ENCODING)));
			}
		}
		return restTemplate;
	}
	
	@Bean
	public FilterRegistrationBean requestParmasFilterRegistration() {
		FilterRegistrationBean registration = new FilterRegistrationBean();
		registration.setFilter(new MyFilter());
		registration.addUrlPatterns("/*");
		registration.setName("requestParmasRegistrationBean");
		return registration;
	}
	
}
