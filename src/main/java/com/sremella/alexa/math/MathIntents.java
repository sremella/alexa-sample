package com.sremella.alexa.math;

import com.amazon.speech.slu.Intent;
import com.amazon.speech.speechlet.IntentRequest;
import com.amazon.speech.speechlet.Session;
import com.amazon.speech.speechlet.SpeechletResponse;
import org.springframework.stereotype.Service;

import static com.sremella.alexa.Utils.response;

@Service
public class MathIntents {
    private static final String ADDITION_TOTAL = "addition_total";

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

    public SpeechletResponse additionStart(IntentRequest request, Session session) {
        String prompt = "Please state your first number.";
        return response("AdditionStart", prompt, prompt);
    }

    public SpeechletResponse addition(IntentRequest request, Session session) {
        int existing =  session.getAttribute(ADDITION_TOTAL) == null ? 0 : (int) session.getAttribute(ADDITION_TOTAL);
        int total = existing + Integer.valueOf(request.getIntent().getSlot("x").getValue());
        session.setAttribute(ADDITION_TOTAL, total);
        String prompt = String.format("Your total is %d. Would you like to add another number?", total);
        return response("Addition", prompt, prompt);
    }
}
