package com.alfilambda;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.gremlin.*;
import com.gremlin.aws.AwsApplicationCoordinatesResolver;

import java.time.Duration;
import java.time.Instant;
import java.util.Map;

public class AlfiDemoHandler implements RequestHandler<Map<String,String>, String> {

    private final GremlinService gremlinService;

    public AlfiDemoHandler() {
        final GremlinServiceFactory factory = new GremlinServiceFactory(new GremlinCoordinatesProvider() {
            @Override
            public ApplicationCoordinates initializeApplicationCoordinates() {
                ApplicationCoordinates coords = AwsApplicationCoordinatesResolver.inferFromEnvironment()
                        .orElseThrow(IllegalStateException::new);
                return coords;
            }
        });
        gremlinService = factory.getGremlinService();
    }

    @Override
    public String handleRequest(Map<String, String> input, Context context) {
        Instant start = Instant.now();
        TrafficCoordinates trafficCoordinates = new TrafficCoordinates.Builder()
                .withType(this.getClass().getSimpleName())
                .withField("method", "handleRequest")
                .build();
        gremlinService.applyImpact(trafficCoordinates);
        LambdaLogger logger = context.getLogger();
        Instant finish = Instant.now();
        long timeElapsed = Duration.between(start, finish).toMillis();  //in millis
        logger.log(String.format("Lambda took %s millis", timeElapsed));
        return new String("200 OK");
    }
}