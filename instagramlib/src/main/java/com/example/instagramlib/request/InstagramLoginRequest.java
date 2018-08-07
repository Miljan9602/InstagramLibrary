package com.example.instagramlib.request;

import android.util.Log;

import com.example.instagramlib.model.InstagramLoggedUser;
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

    private static final String TAG = "InstagramLoginRequest";
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
    public InstagramLoginResponse parseResult(int resultCode, String status) {
        Log.d(TAG, "parseResult: " + resultCode);
        Log.d(TAG, "parseResult: " + status);
        Log.d(TAG, "parseResult: " + instagram.getAllCookies());

        return new InstagramLoginResponse("ok", resultCode, new InstagramLoggedUser());
    }

    public boolean requiresLogin() {
        return false;
    }
}
