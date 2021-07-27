// camel-k: language=java
import org.apache.camel.Exchange;
import org.apache.camel.builder.RouteBuilder;

public class PamIntegration extends RouteBuilder {
  @Override
  public void configure() throws Exception {

      from("amqp:queue:shelveRestockRequiredAddress")
        .routeId("Receive")
        .log("Receieve Message: ${body}")
        .setHeader(Exchange.HTTP_METHOD, constant("POST"))
        .setHeader(Exchange.CONTENT_TYPE, constant("application/json"))
        .setHeader("ACCEPT", constant("application/json"))
        .setHeader("Authorization", constant("Basic YWRtaW46cGFzc3dvcmQ="))
        .to("http://rhpam-kieserver:8080/services/rest/server/containers/Retail_Demo_1.0.5-SNAPSHOT/processes/Retail_Demo.shelve-restock/instances");
  }
}
