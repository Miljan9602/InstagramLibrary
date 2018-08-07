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

import com.example.instagramlib.util.Constants;
import com.example.instagramlib.util.InstagramConstants;

import java.io.IOException;
import java.net.HttpURLConnection;

import okhttp3.Request;
import okhttp3.Response;

import static com.example.instagramlib.util.Constants.*;

/**
 * InstagramLib API
 *
 * @author Miljan Rakita on 7/18/18.
 * rakitamiljan@yahoo.com
 */
public abstract class InstagramGetRequest<T> extends InstagramRequest<T> {

    private static final String TAG = "InstagramGetRequest";

    @Override
    public String getMethod() {
        return GET_METHOD;
    }

    @Override
    public T execute() throws IOException {
        Request request = (new Request.Builder()).url(InstagramConstants.API_URL + this.getUrl())
                .addHeader("X-IG-Capabilities", "36r/Bw==")
                .addHeader("X-IG-Connection-Type", "WiFi")
                .addHeader("X-IG-Connection-Speed", "33kbps")
                .addHeader("X-IG-App-ID", "124024574287414")
                .addHeader("X-IG-ABR-Connection-Speed-KBPS", "0")
                .addHeader("Connection", "close")
                .addHeader("Accept", "*/*")
                .addHeader("Cookie2", "$Version=1")
                .addHeader("Accept-Language", "en-US")
                .addHeader("User-Agent", InstagramConstants.USER_AGENT).build();

        Response response = this.instagram.getClient().newCall(request).execute();
        this.instagram.setLastResponse(response);
        int resultCode = response.code();
        String content = response.body().string();
        return this.parseResult(resultCode, content);
    }
}
