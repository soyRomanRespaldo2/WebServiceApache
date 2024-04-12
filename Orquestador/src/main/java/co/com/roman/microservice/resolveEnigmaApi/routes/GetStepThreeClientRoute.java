package co.com.roman.microservice.resolveEnigmaApi.routes;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.dataformat.JsonLibrary;
import org.springframework.stereotype.Component;

import co.com.roman.microservice.resolveEnigmaApi.model.client.ClientJsonApiBodyResponseSuccess;


@Component
public class GetStepThreeClientRoute extends RouteBuilder {

	@Override
	public void configure() throws Exception {
		from("direct:get-step-three").routeId("getStepThree").setHeader(Exchange.HTTP_METHOD, constant("POST"))
				.setHeader(Exchange.CONTENT_TYPE, constant("application/json"))
				.to("freemarker:templates/GetStepThreeClientTemplate.ftl")

				.hystrix().hystrixConfiguration().executionTimeoutInMilliseconds(2000).end()
				.to("http4://localhost:8083/v1/getOneEnigma/getStep").convertBodyTo(String.class).unmarshal()
				.json(JsonLibrary.Jackson, ClientJsonApiBodyResponseSuccess[].class)

				.process(new Processor() {

					@Override
					public void process(Exchange exchange) throws Exception {
						ClientJsonApiBodyResponseSuccess stepThreeResponse = (ClientJsonApiBodyResponseSuccess) exchange
								.getIn().getBody(ClientJsonApiBodyResponseSuccess.class);
						if (stepThreeResponse.getData().get(0).getStep().equalsIgnoreCase("3")) {
							exchange.setProperty("Step3", stepThreeResponse.getData().get(0).getStepDescription());
						} else {
							exchange.setProperty("Error", "0005");
							exchange.setProperty("descError", "Step three is not valid");
						}

					}

				}).endHystrix().onFallback().process(new Processor() {
					@Override
					public void process(Exchange exchange) throws Exception {
						exchange.setProperty("Error", "0006");
						exchange.setProperty("descError", "Error consulting the step three");
					}
				}).end().log("Response code: ${exchangeProperty[Error]}")
				.log("Response error description: ${exchangeProperty[descError]}")
				.log("Response description ${exchangeProperty[Step3]}");
	}
}
