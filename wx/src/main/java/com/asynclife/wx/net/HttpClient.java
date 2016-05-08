package com.asynclife.wx.net;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.asynclife.wx.service.AccessTokenService;

//@Component
public class HttpClient {

	private final Map<String, String> urlVariableMap = new ConcurrentHashMap<String, String>();
	
	@Autowired
	AccessTokenService accessTokenService;

	@Autowired
	RestTemplate restTemplate;
	
	
	public String doGet(String url) {
		return restTemplate.getForObject(url, String.class, urlVariablesAll(null));
	}
	
	public String doGet(String url, Map<String, String> urlVariables) {
		return restTemplate.getForObject(url, String.class, urlVariablesAll(urlVariables));
	}
	
	public String doPost(String url, String body) {
		return restTemplate.postForObject(url, body, String.class, urlVariablesAll(null));
	}
	
	public String doPost(String url, String body, Map<String, String> urlVariables) {
		return restTemplate.postForObject(url, body, String.class, urlVariablesAll(urlVariables));
	}
	
	public  Map<String, String> urlVariablesAll(Map<String, String> urlVariables) {
		if(urlVariables != null) {
			urlVariableMap.putAll(urlVariables);
		}
		urlVariableMap.put("ACCESS_TOKEN", accessTokenService.getAccessToken());
		return urlVariableMap;
	}
	
	
}
