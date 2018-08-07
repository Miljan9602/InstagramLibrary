package com.example.instagramlib.request;

import android.util.Log;

import com.example.instagramlib.model.payload.InstagramSyncFeaturesPayload;
import com.example.instagramlib.response.StatusResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.NonNull;

import static android.content.ContentValues.TAG;

/**
 * InstagramLib API
 *
 * @author Miljan Rakita on 7/19/18.
 * rakitamiljan@yahoo.com
 */
public class InstagramSyncFeaturesRequest extends InstagramPostRequest<StatusResponse> {

    private static final String TAG = "InstagramSyncFeaturesRe";
    private InstagramSyncFeaturesPayload payload;

    public InstagramSyncFeaturesRequest(@NonNull InstagramSyncFeaturesPayload payload) {
        if (payload == null) {
            throw new NullPointerException("Payload can't be null.");
        } else {
            this.payload = payload;
        }
    }

    @Override
    public String getUrl() {
        return "qe/sync/";
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
    public StatusResponse parseResult(int resultCode, String status) {
        Log.d(TAG, "parseResult: " + resultCode);
        Log.d(TAG, "parseResult: " + status);
        return new StatusResponse(status, resultCode);
    }
}
