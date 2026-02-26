package de.techwende.yourcallsheet;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.server.servlet.context.ServletWebServerInitializedEvent;
import org.springframework.context.ApplicationListener;

/**
 * The Application Server Starter.
 */
@Slf4j
@SpringBootApplication
public class YourcallsheetApplication implements ApplicationListener<ServletWebServerInitializedEvent> {

    /**
     * Entrypoint of program.
     *
     * @param args the args main is called with
     */
    public static void main(String[] args) {
        SpringApplication.run(YourcallsheetApplication.class, args);
    }

    @Override
    public void onApplicationEvent(ServletWebServerInitializedEvent event) {
        int port = event.getWebServer().getPort();
        log.info("Application started on port {}", port);
    }
}
