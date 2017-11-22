package com.amazon.awslabs.cloudfoody;

import com.amazon.speech.json.SpeechletRequestEnvelope;
import com.amazon.speech.speechlet.IntentRequest;
import com.amazon.speech.speechlet.LaunchRequest;
import com.amazon.speech.speechlet.SessionEndedRequest;
import com.amazon.speech.speechlet.SessionStartedRequest;
import com.amazon.speech.speechlet.SpeechletResponse;
import com.amazon.speech.speechlet.SpeechletV2;
import com.amazon.speech.ui.PlainTextOutputSpeech;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Speechlet to handle requests to find a restaurant in a city using Unofficial OpenTable API.
 */
public class RestaurantCitySpeechlet implements SpeechletV2 {
    private static final Logger logger = LoggerFactory.getLogger(RestaurantCitySpeechlet.class);

    @Override
    public void onSessionStarted(SpeechletRequestEnvelope<SessionStartedRequest> requestEnvelope) {
        logger.info("Starting a new session. Request ID: \"{}\". Session ID: \"{}\"".format(
                requestEnvelope.getRequest().getRequestId(),
                requestEnvelope.getSession().getSessionId()));
    }

    @Override
    public SpeechletResponse onIntent(final SpeechletRequestEnvelope<IntentRequest> requestEnvelope) {
        return getWelcomeResponse();
    }

    @Override
    public SpeechletResponse onLaunch(final SpeechletRequestEnvelope<LaunchRequest> requestEnvelope) {
        return getWelcomeResponse();
    }

    @Override
    public void onSessionEnded(final SpeechletRequestEnvelope<SessionEndedRequest> requestEnvelope) {
        // no-op.
    }

    private SpeechletResponse getWelcomeResponse() {
        final PlainTextOutputSpeech outputSpeech = new PlainTextOutputSpeech();
        outputSpeech.setText("Hello, welcome to Serverless");
        return SpeechletResponse.newTellResponse(outputSpeech);
    }
}
