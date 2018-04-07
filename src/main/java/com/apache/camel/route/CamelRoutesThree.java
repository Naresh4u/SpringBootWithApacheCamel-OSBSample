/*package com.apache.camel.route;

import java.util.HashMap;
import java.util.Map;

import org.apache.camel.CamelContext;
import org.apache.camel.Exchange;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.impl.DefaultCamelContext;
import org.apache.camel.impl.DefaultExchange;
import org.apache.camel.util.ExchangeHelper;
import org.json.simple.JSONObject;
import org.springframework.stereotype.Component;

@Component
public class CamelRoutesThree extends RouteBuilder {
	
	@Override
	public void configure() throws Exception {
		from("direct:secondRoute")
        .setHeader(Exchange.HTTP_METHOD,simple("POST"))
        .to("http://localhost:8888/carParking/getCarDetailsByCarNo");
  
			JSONObject obj = new JSONObject();
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

		}

}
*/