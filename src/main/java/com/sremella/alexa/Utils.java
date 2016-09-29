package com.sremella.alexa;

import com.amazon.speech.speechlet.SpeechletResponse;
import com.amazon.speech.ui.PlainTextOutputSpeech;
import com.amazon.speech.ui.Reprompt;
import com.amazon.speech.ui.SimpleCard;
import com.sremella.alexa.sdk.FixedSpeechletResponse;

public class Utils {
    public static SpeechletResponse response(String title, String text, String repromptText) {
        // Create the Simple card content.
        SimpleCard card = new SimpleCard();
        card.setTitle(title);
        card.setContent(text);

        // Create the plain text output.
        PlainTextOutputSpeech speech = new PlainTextOutputSpeech();
        speech.setText(text);

        if (repromptText != null) {
            // Create reprompt
            PlainTextOutputSpeech repromptSpeech = new PlainTextOutputSpeech();
            repromptSpeech.setText(repromptText);
            Reprompt reprompt = new Reprompt();
            reprompt.setOutputSpeech(repromptSpeech);

            return FixedSpeechletResponse.ask().withText(text).withReprompt(reprompt).withCard(card).build();
        } else {
            return FixedSpeechletResponse.tell().withText(text).withCard(card).build();
        }
    }

    public static SpeechletResponse response(String title, String text) {
        return response(title, text, null);
    }
}
