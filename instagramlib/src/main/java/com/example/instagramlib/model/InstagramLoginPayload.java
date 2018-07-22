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

import lombok.Builder;
import lombok.Getter;

/**
 * InstagramLib API
 *
 * @author Miljan Rakita on 7/18/18.
 * rakitamiljan@yahoo.com
 */
public class InstagramLoginPayload {

    @Getter
    private String username;
    @Getter
    private String phone_id;
    @Getter
    private String csrftoken;
    @Getter
    private String guid;
    @Getter
    private String device_id;
    @Getter
    private String password;
    @Getter
    private int login_attempt_account = 0;

    @Builder
    InstagramLoginPayload(String username, String password, String phone_id, String _csrftoken, String guid, String device_id) {
        this.username = username;
        this.password = password;
        this.phone_id = phone_id;
        this.csrftoken = _csrftoken;
        this.guid = guid;
        this.device_id = device_id;
    }

}
