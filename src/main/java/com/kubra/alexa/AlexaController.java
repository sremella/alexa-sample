package com.kubra.alexa;

import com.amazon.speech.Sdk;
import com.amazon.speech.json.SpeechletRequestEnvelope;
import com.amazon.speech.json.SpeechletResponseEnvelope;
import com.amazon.speech.speechlet.*;
import com.kubra.alexa.math.MathIntents;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import static com.kubra.alexa.Utils.response;

@Controller
@RequestMapping
public class AlexaController implements Speechlet {
    @Autowired
    private MathIntents mathIntents;

    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public String hello(){
        return "hello world";
    }

    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    public SpeechletResponseEnvelope request(@RequestBody SpeechletRequestEnvelope incoming) throws SpeechletException {
        SpeechletRequest request = incoming.getRequest();
        SpeechletResponse speechletResponse = null;
        if(request instanceof IntentRequest) {
            speechletResponse = onIntent((IntentRequest) request, incoming.getSession());
        }

        SpeechletResponseEnvelope response = new SpeechletResponseEnvelope();
        response.setResponse(speechletResponse);
        response.setVersion(Sdk.VERSION);
        return response;
    }

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
