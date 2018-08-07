package com.example.instagramlib.request;

import android.util.Log;

import com.example.instagramlib.model.instagram.InstagramLoggedUser;
import com.example.instagramlib.model.instagram.InstagramLoginChallenge;
import com.example.instagramlib.model.instagram.InstagramLoginPayload;
import com.example.instagramlib.response.InstagramLoginResponse;
import com.example.instagramlib.util.InstagramErrorType;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.json.JSONException;
import org.json.JSONObject;

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
    public InstagramLoginResponse parseResult(int resultCode, String content) {
        Log.d(TAG, "parseResult: " + resultCode);
        Log.d(TAG, "parseResult: " + content);
        Log.d(TAG, "parseResult: " + instagram.getAllCookies());

        InstagramLoginResponse response = null;

        try {
            JSONObject object = new JSONObject(content);

            String status = object.getString("status");
            String message = object.has("message") ? object.getString("message") : null;

            // If error exist.
            if (object.has("error_type")) {

                switch (object.getString("error_type")) {

                    /**
                     * Need to determine if problem is in password or username
                     * instagram has the same error_type for these two cases.
                     */
                    case InstagramErrorType.ERROR_TYPE_BAD_PASSWORD:

                        if (message != null && message.contains("username"))
                            response = new InstagramLoginResponse(status, resultCode, message, true, false);
                        else
                            response = new InstagramLoginResponse(status, resultCode, message, false, true);
                        break;


                    case InstagramErrorType.ERROR_TYPE_CHALLENGE_REQUIRED:
                        object = object.getJSONObject("challenge");

                        InstagramLoginChallenge challenge = new InstagramLoginChallenge(
                                object.getString("url"),
                                object.getString("api_path"),
                                object.getBoolean("hide_webview_header"),
                                object.getBoolean("lock"),
                                object.getBoolean("logout"),
                                object.getBoolean("native_flow")
                        );

                        response = new InstagramLoginResponse(status, resultCode, message, challenge);
                        break;

                }
            } else {
                object = object.getJSONObject("logged_in_user");
                String pk = String.valueOf(object.getLong("pk"));
                String username = object.getString("username");
                String fullName = object.getString("full_name");

                InstagramLoggedUser loggedUser = new InstagramLoggedUser(username, pk, fullName);
                response = new InstagramLoginResponse("ok", resultCode, loggedUser);
            }

        } catch (JSONException e) {
            Log.e(TAG, "parseResult: ", e);
        }

        return response;
    }

    public boolean requiresLogin() {
        return false;
    }
}
