/**
 * Copyright (C) 2018 Rakita Miljan (rakitamiljan@yahoo.com)
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.instagramlib.request;

import android.util.Log;

import com.example.instagramlib.response.StatusResponse;
import com.example.instagramlib.util.InstagramUtil;

import java.io.IOException;

/**
 * InstagramLib API
 *
 * @author Miljan Rakita on 7/19/18.
 * rakitamiljan@yahoo.com
 */
public class InstagramFetchHeadersRequest extends InstagramGetRequest<StatusResponse> {

    private static final String TAG = InstagramFetchHeadersRequest.class.getSimpleName();

    @Override
    public String getUrl() {
        return "si/fetch_headers/?challenge_type=signup&guid=" + InstagramUtil.generateUuid(false);
    }

    @Override
    public StatusResponse parseResult(int resultCode, String content) {
        Log.d(TAG, "parseResult: " + resultCode);
        Log.d(TAG, "parseResult: " + content);
        return new StatusResponse(content, resultCode);
    }

    public boolean requiresLogin() {
        return false;
    }
}
