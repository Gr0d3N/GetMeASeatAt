package com.amazon.awslabs.cloudfoody;

import com.amazon.speech.json.SpeechletRequestEnvelope;
import com.amazon.speech.slu.Intent;
import com.amazon.speech.slu.Slot;
import com.amazon.speech.speechlet.IntentRequest;
import com.amazon.speech.speechlet.LaunchRequest;
import com.amazon.speech.speechlet.SessionEndedRequest;
import com.amazon.speech.speechlet.SessionStartedRequest;
import com.amazon.speech.speechlet.SpeechletResponse;
import com.amazon.speech.speechlet.SpeechletV2;
import com.amazon.speech.ui.OutputSpeech;
import com.amazon.speech.ui.PlainTextOutputSpeech;
import com.amazon.speech.ui.Reprompt;
import com.amazon.speech.ui.SimpleCard;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Speechlet to handle requests to find a restaurant in a city using Unofficial OpenTable API.
 */
public class RestaurantCitySpeechlet implements SpeechletV2 {
    private static final Logger logger = LoggerFactory.getLogger(RestaurantCitySpeechlet.class);

    @Override
    public void onSessionStarted(SpeechletRequestEnvelope<SessionStartedRequest> requestEnvelope) {
        logger.info("onSessionStarted requestId={}, sessionId={}", requestEnvelope.getRequest().getRequestId(),
                requestEnvelope.getSession().getSessionId());
    }

    @Override
    public SpeechletResponse onIntent(final SpeechletRequestEnvelope<IntentRequest> requestEnvelope) {
        IntentRequest request = requestEnvelope.getRequest();
        logger.info("onIntent requestId={}, sessionId={}", request.getRequestId(),
                requestEnvelope.getSession().getSessionId());

        Intent intent = request.getIntent();
        String intentName = (intent != null) ? intent.getName() : null;

        if ("CheckRestaurantIntent".equals(intentName)) {
            return getCheckRestaurantResponse(intent);
        } else if ("AMAZON.HelpIntent".equals(intentName)) {
            return getHelpResponse();
        } else {
            return getAskResponse("CloudFoody", "This is unsupported. Please try something else.");
        }
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
        outputSpeech.setText("Hello, welcome to cloud foody. Name a restaurant to check reservations availability.");
        return SpeechletResponse.newTellResponse(outputSpeech);
    }

    private SpeechletResponse getHelpResponse() {
        String speechText = "You can me if restaurant X takes reservations!";
        return getAskResponse("CloudFoody", speechText);
    }

    private SpeechletResponse getAskResponse(String cardTitle, String speechText) {
        SimpleCard card = getSimpleCard(cardTitle, speechText);
        PlainTextOutputSpeech speech = getPlainTextOutputSpeech(speechText);
        Reprompt reprompt = getReprompt(speech);

        return SpeechletResponse.newAskResponse(speech, reprompt, card);
    }

    private SimpleCard getSimpleCard(String title, String content) {
        SimpleCard card = new SimpleCard();
        card.setTitle(title);
        card.setContent(content);

        return card;
    }

    private PlainTextOutputSpeech getPlainTextOutputSpeech(String speechText) {
        PlainTextOutputSpeech speech = new PlainTextOutputSpeech();
        speech.setText(speechText);

        return speech;
    }

    private Reprompt getReprompt(OutputSpeech outputSpeech) {
        Reprompt reprompt = new Reprompt();
        reprompt.setOutputSpeech(outputSpeech);

        return reprompt;
    }

    private SpeechletResponse getCheckRestaurantResponse(Intent intent) {
        String restaurantName = getRestaurantName(intent);
        restaurantName = restaurantName.replaceAll(" ", "+");
        logger.info("Found restaurant name ", restaurantName);
        ArrayList<Restaurant> restaurants = new ArrayList<>();
        try {
            restaurants = OpenTable.getRestaurantsByName(restaurantName);
        } catch (final IOException exception) {
            logger.warn("count not find restaurant name ", restaurantName);
        }

        String speechText;

        if (restaurants.isEmpty()) {
            speechText = "I could not find this restaurant in Open Table website for reservations.";
        } else {
            Restaurant restaurant = restaurants.get(0);
            speechText = "I found " + restaurant.getName() + ". The restaurant is located in " + restaurant.getArea()
                    + " and the phone number is " + restaurant.getPhone();
        }

        // Create the Simple card content.
        SimpleCard card = getSimpleCard("CloudFoody", speechText);

        // Create the plain text output.
        PlainTextOutputSpeech speech = getPlainTextOutputSpeech(speechText);

        logger.info("speechTest: ", speechText);
        return SpeechletResponse.newTellResponse(speech, card);
    }

    private String getRestaurantName(Intent intent) {
        Slot restaurantNameSlot = intent.getSlot("restaurant");
        return restaurantNameSlot.getValue();
    }
}
