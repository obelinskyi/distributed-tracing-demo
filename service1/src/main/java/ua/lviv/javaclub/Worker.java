package ua.lviv.javaclub;

import org.springframework.cloud.sleuth.SpanCustomizer;
import org.springframework.cloud.sleuth.annotation.ContinueSpan;
import org.springframework.cloud.sleuth.annotation.SpanTag;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
public class Worker {

    private final SpanCustomizer spanCustomizer;

    public Worker(SpanCustomizer spanCustomizer) {
        this.spanCustomizer = spanCustomizer;
    }

    @ContinueSpan(log = "Take data from the DB")
    @Async
    public String doWork(@SpanTag("Tag key") String arg) {
        spanCustomizer.tag("demo", "javaclub");

        spanCustomizer.event("Getting data from the DB");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        spanCustomizer.event("Data retrieved");
        return "Test work";
    }
}
