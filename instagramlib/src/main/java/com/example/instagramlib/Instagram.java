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

import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;
import java.net.CookieStore;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;


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

    @Getter
    @Setter
    protected String rankToken;
    @Getter
    @Setter
    protected CookieStore cookieStore;
    @Getter
    private boolean isLoggedIn;


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
    public Instagram(String username, String password, String uuid, long userId, CookieStore cookieStore) {
        this.username = username;
        this.password = password;
        this.uuid = uuid;
        this.userId = userId;
        this.cookieStore = cookieStore;
    }
    
}
