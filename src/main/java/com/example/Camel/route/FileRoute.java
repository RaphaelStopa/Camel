package com.example.Camel.route;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

import java.io.File;

@Component
public class FileRoute extends RouteBuilder {

    // Copy the path of the folder with the files
    private String path = "C:\\Users\\Stopa\\Desktop\\teste\\";

    @Override
    public void configure() throws Exception {
        // Specifies the component and path
        // if you don't have the folder create it by default
        // if I use ?delete=true after the input it won't create the .camel
        // possible to move what will enter the input, just put it after the input ?move=${date:now:yyyyMMdd}/copy-${file:name}
        // ?noop=true, if a similar file has already been processed it does nothing
        // ?recursive=true, watch the subfolders
        // ?excludeExt=algo, excludes files with these intentions and has the include too
        // schedule the first scan = ?timeUnit=SECONDS&initialDelay=1 and ?delay=10000&repeatCount=3
        // it is possible to filter the files that will be used, ?filterFile=${file:algo} < 34534
        // in the output if you put ?flatten=true, it removes from all subfolders and plays in the main one. Important by the recursive and delete in input
        from("file://" + path + "input")
                .log("file:  ${header.CamelFileName} - Path: ${header.CamelFilePath}")
                .log("${file:name}")
                .bean("fileComponent")
                .process(new FileProcessor())
                .choice().when(simple("${header.CamelFileLenfyh < 564}"))
                // possible to put the bean here to simply process bean:fileComponent
                .to("file://" + path + "output")
                // down here it only exists because I put the choice
                .otherwise()
                .process(new FileProcessor());
    }
}

@Component
class FileComponent {
    public void log(File file){
        System.out.println("FileComponent: " + file.getName());
    }
}


class FileProcessor implements Processor {

    @Override
    public void process(Exchange exchange) throws Exception {
        System.out.println("Processor: " + exchange.getIn().getBody());
    }
}