package com.kubra.alexa;

import com.amazon.speech.speechlet.SpeechletResponse;
import com.amazon.speech.ui.PlainTextOutputSpeech;
import com.amazon.speech.ui.SimpleCard;

public class Utils {
    public static SpeechletResponse response(String title, String text) {
        PlainTextOutputSpeech outputSpeech = new PlainTextOutputSpeech();
        outputSpeech.setText(text);
        SpeechletResponse response = SpeechletResponse.newTellResponse(outputSpeech);
        SimpleCard card = new SimpleCard();
        card.setContent(outputSpeech.getText());
        card.setTitle(title);
        response.setCard(card);
        return response;
    }
}
