package com.kubra.alexa.math;

import com.amazon.speech.slu.Intent;
import com.amazon.speech.speechlet.IntentRequest;
import com.amazon.speech.speechlet.Session;
import com.amazon.speech.speechlet.SpeechletResponse;
import org.springframework.stereotype.Service;

import static com.kubra.alexa.Utils.response;

@Service
public class MathIntents {
    public SpeechletResponse multiply(IntentRequest request, Session session) {
        Intent intent = request.getIntent();
        int result = Integer.valueOf(intent.getSlot("x").getValue()) * Integer.valueOf(intent.getSlot("y").getValue());
        return response("Multiplication", "the multiplication result is " + result);
    }

    public SpeechletResponse divide(IntentRequest request, Session session) {
        Intent intent = request.getIntent();
        int result = Integer.valueOf(intent.getSlot("x").getValue()) / Integer.valueOf(intent.getSlot("y").getValue());
        return response("Division", "the division result is " + result);
    }
}
