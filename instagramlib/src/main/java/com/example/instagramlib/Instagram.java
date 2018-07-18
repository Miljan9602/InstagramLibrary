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

package com.example.instagramlib;

import android.content.Context;


import com.example.instagramlib.request.InstagramRequest;
import com.example.instagramlib.util.InstagramUtil;
import com.franmontiel.persistentcookiejar.ClearableCookieJar;
import com.franmontiel.persistentcookiejar.PersistentCookieJar;
import com.franmontiel.persistentcookiejar.cache.SetCookieCache;
import com.franmontiel.persistentcookiejar.persistence.SharedPrefsCookiePersistor;

import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import okhttp3.Cookie;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Response;

import static com.example.instagramlib.util.Constants.*;
import static com.example.instagramlib.util.InstagramConstants.*;


/**
 * InstagramLib API
 *
 * @author Miljan Rakita on 7/18/18.
 * rakitamiljan@yahoo.com
 */
public class Instagram implements Serializable {


    @Getter
    public String username;

    @Getter
    protected String password;
    @Getter
    protected String uuid;
    @Getter
    protected String advertisingId;
    @Getter
    protected String deviceId;

    @Getter
    @Setter
    protected long userId;

    private final Set<Cookie> cookieStore = new HashSet();

    @Getter
    @Setter
    protected String rankToken;
    @Getter
    protected boolean isLoggedIn;
    @Getter
    @Setter
    protected Response lastResponse;
    protected OkHttpClient client;
    private HttpUrl url = new HttpUrl.Builder().scheme(HTTPS).host(INSTAGRAM_HOST).build();


    /**
     *
     * @param username
     * @param password
     */
    public Instagram(String username, String password) {
        this.username = username;
        this.password = password;
    }


    @Builder
    public Instagram(String username, String password, String uuid, long userId) {
        this.username = username;
        this.password = password;
        this.uuid = uuid;
        this.userId = userId;
    }


    /**
     * Setup before sending request. This has the be called everytime before login.
     *
     * @param context for writing cookies to the memory.
     */
    public void setup(@NonNull final Context context) {
        if (StringUtils.isEmpty(username) || StringUtils.isEmpty(password)) {
            throw new IllegalArgumentException("You must provide a username and password to login.");
        }

        if (context == null) {
            throw new NullPointerException("Context can't be null.");
        }

        deviceId = InstagramUtil.generateDeviceId();

        if (StringUtils.isEmpty(uuid)) uuid = InstagramUtil.generateUuid(true);

        advertisingId = InstagramUtil.generateUuid(true);

        ClearableCookieJar cookieJar = new PersistentCookieJar(new SetCookieCache(), new SharedPrefsCookiePersistor(context));
        this.client = (new OkHttpClient.Builder()).cookieJar(cookieJar).build();
    }


    /**
     * Pulls out cookie when we hold in our cookiejar.
     *
     * @param cookieName name of the cookie we need to pull.
     * @return value for the cookieName.
     */
    public Cookie getCookie(String cookieName) {
        Iterator var2 = this.client.cookieJar().loadForRequest(url).iterator();

        Cookie cookie;
        do {
            if (!var2.hasNext()) {
                return null;
            }

            cookie = (Cookie) var2.next();
        } while (!cookie.name().equalsIgnoreCase(cookieName));

        return cookie;
    }

}
