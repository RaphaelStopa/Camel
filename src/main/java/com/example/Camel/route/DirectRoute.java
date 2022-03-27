package com.example.Camel.route;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

import java.io.File;

// used to connect routes
@Component
public class DirectRoute extends RouteBuilder {

    @Override
    public void configure() throws Exception {
        // This one
        from("direct:log-file").log("log: ${header.CamelFileName}").to("direct:log-file2");

        from("direct:log-file2").process(new FileProcessorTwo());

        // called this one
        from("file://C:\\Users\\Stopa\\Desktop\\teste\\").to("direct:log-file");
    }
}

class FileProcessorTwo implements Processor{

    @Override
    public void process(Exchange exchange) throws Exception {
        File file = exchange.getIn().getBody(File.class);
        System.out.println("Processor: " + file.getName());
    }
}
