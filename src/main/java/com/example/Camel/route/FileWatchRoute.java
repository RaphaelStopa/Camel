package com.example.Camel.route;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class FileWatchRoute  extends RouteBuilder {

    // Copy the path of the folder with the files
    private String path = "C:\\Users\\Stopa\\Desktop\\teste\\";


    @Override
    public void configure() throws Exception {
        // I commented, if it is null it will give an error
        // accepts recursive=false and events=CREATE,MODIFY and DELETE
//        from("file-watch: " + path)
//                .log("Evento: ${header.CamelFileEventType} Arquivo: ${header.CamelFileName}");
    }
}
