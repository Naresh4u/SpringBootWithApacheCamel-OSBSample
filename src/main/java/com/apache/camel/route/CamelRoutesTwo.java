package com.apache.camel.route;

import java.util.HashMap;
import java.util.Map;

import org.apache.camel.CamelContext;
import org.apache.camel.Exchange;
import org.apache.camel.Message;
import org.apache.camel.Processor;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.impl.DefaultCamelContext;
import org.apache.camel.impl.DefaultExchange;
import org.apache.camel.model.rest.RestBindingMode;
import org.apache.camel.util.ExchangeHelper;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CamelRoutesTwo extends RouteBuilder {
	
	@Autowired
	ProducerTemplate template;
	
	@Autowired
	CamelContext context;
	
	    
	@Override
	public void configure() throws Exception {
		CamelContext context = new DefaultCamelContext();
	    try {
	        ProducerTemplate template = context.createProducerTemplate();
	        context.start();

	        Exchange exchange = template
	                .request(
	                        "http://localhost:8888/carParking/getCarDetailsByCarNo",
	                        new Processor() {
	                            public void process(Exchange exchange)
	                                    throws Exception {
	                            }
	                        });

	        if (null != exchange) {
	            Message out = exchange.getOut();
	            System.out.println(out.getBody().toString());
	            int responseCode = out.getHeader(Exchange.HTTP_RESPONSE_CODE,
	                    Integer.class);
	            System.out.println("Response: " + String.valueOf(responseCode));
	        }

	        Thread.sleep(1000 * 3);
	        context.stop();
	    } catch (Exception ex) {
	        System.out.println("Exception: " + ex);
	    }

	    System.out.println("DONE!!");

		 from("direct:secondRoute")
		   .log("Camel body: ${body}");		
		
			
	
		// Approach 1

		/*restConfiguration().component("servlet").bindingMode(RestBindingMode.json);
        JSONObject obj = new JSONObject();
        obj.put("carNo", "1234");
        
        rest().post("getCarInfo").consumes("application/json").produces("application/json").type(Object.class)
        .to("direct:getCarInfo");
        rest("/testing").post("getJson2XJson").consumes("application/json").produces("application/json").type(Object.class)
        .to("direct:getJson2XJson");
   
        
      //Rest service call implementation
        from("direct:getCarInfo")
        .process(exchange -> {
        	 obj.clear();
        	Map reqValue=exchange.getIn().getBody(Map.class);
        	reqValue.forEach((k,v)->{
        		obj.put(k, v);
        	});
        	
        	System.out.println("The Map Value is ::"+obj.toString());
        	template = context.createProducerTemplate();
		     String headerValue = "application/json";
		        Map<String, Object> headers = new HashMap<String,Object>();
		        headers.put("Content-Type", headerValue);
		        Object result = template.requestBodyAndHeaders("http://localhost:8888/carParking/getCarDetailsByCarNo", obj.toString(), headers, String.class);
		        String response = ExchangeHelper.convertToType(exchange, String.class, result);
		       
		        JSONParser parsers = new JSONParser(); 
			    JSONObject resJsons = (JSONObject) parsers.parse(response);
			    
		        System.out.println("Get Car Parking Response : "+resJsons);
		        obj.clear();
		        
            exchange.getIn().setBody(resJsons);
        });
       
*/
		
		// Approach 2
		
		
		/*	JSONObject obj = new JSONObject();
	        obj.put("carNo", "1234");
	        CamelContext context = new DefaultCamelContext();
	        context.addRoutes(new RouteBuilder() {
	            public void configure() {
	                from("direct:secondRoute")
	                .setHeader(Exchange.HTTP_METHOD,simple("POST"))
	                .to("http://localhost:8888/carParking/getCarDetailsByCarNo");
	            }
	        });

	        context.start();
	        
	        ProducerTemplate template = context.createProducerTemplate();
	        String headerValue = "application/json";

	        Map<String, Object> headers = new HashMap<String,Object>();
	        headers.put("Content-Type", headerValue);

	        Object result = template.requestBodyAndHeaders("direct:secondRoute", obj.toString(), headers, String.class);
	        Exchange exchange = new DefaultExchange(context); 
	        String response = ExchangeHelper.convertToType(exchange, String.class, result); 
	        System.out.println("Response : "+response);
	        context.stop();

		*/}

}
