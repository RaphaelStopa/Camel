package com.example.Camel.route;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class TimerRoute extends RouteBuilder {
    @Override
    public void configure() throws Exception {
        // supports two time formats
        from("timer:my-timer?period=2000&repeatCount=2&time=2022-07-07 14:20:20")
                .log("Hora: ${date:now:HH:mm:ss}");
    }
}
