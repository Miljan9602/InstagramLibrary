package com.example.instagramlib.request;

import android.util.Log;

import com.example.instagramlib.response.StatusResponse;

import static android.content.ContentValues.TAG;

/**
 * InstagramLib API
 *
 * @author Miljan Rakita on 7/19/18.
 * rakitamiljan@yahoo.com
 */
public class InstagramGetRecentActivityRequest extends InstagramGetRequest<StatusResponse> {

    public String getUrl() {
        return "news/inbox/?";
    }

    public StatusResponse parseResult(int resultCode, String content) {
        Log.d(TAG, "parseResult: " + resultCode);
        Log.d(TAG, "parseResult: " + content);
        return new StatusResponse();
    }

}