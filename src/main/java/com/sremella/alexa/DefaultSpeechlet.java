package com.sremella.alexa;

import com.amazon.speech.speechlet.*;
import com.sremella.alexa.math.MathIntents;
import org.springframework.beans.factory.annotation.Autowired;

import static com.sremella.alexa.Utils.response;

public class DefaultSpeechlet implements Speechlet {
    @Autowired
    private MathIntents mathIntents;

    @Override
    public void onSessionStarted(SessionStartedRequest request, Session session) throws SpeechletException {

    }

    @Override
    public SpeechletResponse onLaunch(LaunchRequest request, Session session) throws SpeechletException {
        return null;
    }

    @Override
    public SpeechletResponse onIntent(IntentRequest request, Session session) throws SpeechletException {
        switch (request.getIntent().getName()){
            case Intents.MULTIPLY:
                return mathIntents.multiply(request, session);
            case Intents.DIVIDE:
                return mathIntents.divide(request, session);
            default:
                return response("Sorry", "Sorry Dave, I cannot do that.");
        }
    }

    @Override
    public void onSessionEnded(SessionEndedRequest request, Session session) throws SpeechletException {

    }

}
