package com.amazon.awslabs;

import com.amazon.speech.speechlet.lambda.SpeechletRequestStreamHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashSet;
import java.util.Set;

/**
 * Lambda handler.
 */
public final class RestaurantCityRequestStreamHandler extends SpeechletRequestStreamHandler {
    private static final String APPLICATION_ID = "APPLICATION_ID";
    private static final Logger logger = LoggerFactory.getLogger(RestaurantCityRequestStreamHandler.class);

    private static final Set<String> supportedApplicationIds;
    static {
        supportedApplicationIds = new HashSet<>();
        try {
            final String applicationId = System.getenv(APPLICATION_ID);
            logger.info("Application ID: {}", applicationId);
            supportedApplicationIds.add(applicationId);

        } catch (NullPointerException | SecurityException error) {
            logger.warn("Failed to get {} environment variable with exception: {}", APPLICATION_ID,
                    error.getLocalizedMessage());
        }
    }

    public RestaurantCityRequestStreamHandler() {
        super(new RestaurantCitySpeechlet(), supportedApplicationIds);
    }
}
