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
package com.example.instagramlib.model;

/**
 * InstagramLib API
 *
 * @author Miljan Rakita on 7/18/18.
 * rakitamiljan@yahoo.com
 */
public class InstagramLoginPayload {

    private String username;
    private String phone_id;
    private String _csrftoken;
    private String guid;
    private String device_id;
    private String password;
    private int login_attempt_account = 0;

    InstagramLoginPayload(String username, String password, String phone_id, String _csrftoken, String guid, String device_id, int login_attempt_account) {
        this.username = username;
        this.password = password;
        this.phone_id = phone_id;
        this._csrftoken = _csrftoken;
        this.guid = guid;
        this.device_id = device_id;
        this.login_attempt_account = login_attempt_account;
    }

}
