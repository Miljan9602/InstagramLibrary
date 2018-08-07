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
public class InstagramAutoCompleteUserListRequest extends InstagramGetRequest<StatusResponse> {

    private static final String TAG = "InstagramAutoCompleteUs";
    public String getUrl() {
        return "friendships/autocomplete_user_list/";
    }

    public StatusResponse parseResult(int resultCode, String status) {
        Log.d(TAG, "parseResult: " + resultCode);
        Log.d(TAG, "parseResult: " + status);
        return new StatusResponse(status, resultCode);
    }
}
