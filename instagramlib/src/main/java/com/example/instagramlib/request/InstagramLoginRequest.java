package com.example.instagramlib.request;

import android.util.Log;

import com.example.instagramlib.model.InstagramLoginPayload;
import com.example.instagramlib.response.InstagramLoginResponse;
import com.example.instagramlib.response.StatusResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import static android.content.ContentValues.TAG;

/**
 * InstagramLib API
 *
 * @author Miljan Rakita on 7/19/18.
 * rakitamiljan@yahoo.com
 */
public class InstagramLoginRequest extends InstagramPostRequest<InstagramLoginResponse> {

    private InstagramLoginPayload payload;

    public InstagramLoginRequest(InstagramLoginPayload payload) {
        if (payload == null) {
            throw new NullPointerException("Payload can't be null.");
        } else {
            this.payload = payload;
        }
    }

    public String getPayload() {
        try {
            ObjectMapper mapper = new ObjectMapper();
            String payloadJson = mapper.writeValueAsString(this.payload);
            return payloadJson;
        } catch (Throwable ex) {
            try {
                throw ex;
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    @Override
    public String getUrl() {
        return "accounts/login/";
    }

    @Override
    public InstagramLoginResponse parseResult(int var1, String var2) {
        Log.d(TAG, "parseResult: " + var1);
        Log.d(TAG, "parseResult: " + var2);
        return new InstagramLoginResponse();
    }

    public boolean requiresLogin() {
        return false;
    }
}
