package com.cart.web;

import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONArray;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.cart.instrument.JSExecutionTracer;
import com.cart.instrument.Trace;
import com.google.gson.Gson;
import com.google.gson.JsonArray;

@Component
@RestController
public class RESTController {

	private static final Logger logger = LogManager.getLogger(RESTController.class);

	@CrossOrigin
	@RequestMapping(value = "/getExecutionTrace", method = RequestMethod.POST)
	@ResponseBody
	public String ping(HttpEntity<String> httpEntity, HttpServletResponse response) throws Exception {
		String reqBody = httpEntity.getBody().toString();
//		Gson gson = new Gson();
//		JsonArray jsonObject = gson.fromJson(reqBody, JsonArray.class);
	
		JSExecutionTracer.addPoint(new String(reqBody));
		
	
		return "pong";
	}
	

	@CrossOrigin
	@RequestMapping(value = "/ping", method = RequestMethod.GET)
	@ResponseBody
	public String ping2(HttpEntity<String> httpEntity, HttpServletResponse response) throws Exception {
		
		Trace trace = Trace.parse(JSExecutionTracer.points);
		System.out.print(trace.getDeclaration());
		System.out.print(trace.getData(JSExecutionTracer.points));
		
		return "pong";
	}
	


}
