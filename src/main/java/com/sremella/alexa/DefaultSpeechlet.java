package com.sremella.alexa;

import com.amazon.speech.speechlet.*;
import com.sremella.alexa.math.MathIntents;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import static com.sremella.alexa.Utils.response;

public class DefaultSpeechlet implements Speechlet {
    private static final Logger logger = LoggerFactory.getLogger(DefaultSpeechlet.class);

    @Autowired
    private MathIntents mathIntents;

    @Override
    public void onSessionStarted(SessionStartedRequest request, Session session) throws SpeechletException {
        logger.debug("onSessionStarted requestId={}, sessionId={}", request.getRequestId(),
                session.getSessionId());
    }

    @Override
    public SpeechletResponse onLaunch(LaunchRequest request, Session session) throws SpeechletException {
        logger.debug("onLaunch requestId={}, sessionId={}", request.getRequestId(),
                session.getSessionId());
        String prompt = "I can perform additions, multiplications and divisions. Please " +
                "tell me what you would like to do.";
        return response("Welcome", "Hello, welcome to Jarvis. " + prompt, prompt);
    }

    @Override
    public SpeechletResponse onIntent(IntentRequest request, Session session) throws SpeechletException {
        logger.debug("onIntent requestId={}, sessionId={}", request.getRequestId(),
                session.getSessionId());
        switch (request.getIntent().getName()){
            case Intents.MULTIPLY:
                return mathIntents.multiply(request, session);
            case Intents.DIVIDE:
                return mathIntents.divide(request, session);
            case Intents.ADDITION_START:
                return mathIntents.additionStart(request, session);
            case Intents.ADDITION:
                return mathIntents.addition(request, session);
            default:
                return response("Sorry", "Sorry Dave, I cannot do that.");
        }
    }

    @Override
    public void onSessionEnded(SessionEndedRequest request, Session session) throws SpeechletException {
        logger.debug("onSessionEnded requestId={}, sessionId={}", request.getRequestId(),
                session.getSessionId());
    }

}
