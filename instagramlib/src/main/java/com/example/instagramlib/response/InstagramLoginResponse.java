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

package com.example.instagramlib.response;

import android.support.annotation.Nullable;

import com.example.instagramlib.model.instagram.InstagramLoggedUser;
import com.example.instagramlib.model.instagram.InstagramLoginChallenge;

import lombok.Getter;
import lombok.Setter;

/**
 * InstagramLib API
 *
 * @author Miljan Rakita on 7/19/18.
 * rakitamiljan@yahoo.com
 */
public class InstagramLoginResponse extends StatusResponse {

    @Nullable
    @Setter
    @Getter
    private InstagramLoggedUser loggedUser;


    @Setter
    @Getter
    /**
     * If passwordValid is false, then user entered wrong password.
     */
    private boolean passwordValid;


    @Setter
    @Getter
    /**
     *  If usernameValid is false, then user entered wrong password.
     */
    private boolean usernameValid;

    @Getter
    @Setter
    @Nullable
    private InstagramLoginChallenge loginChallenge;


    public InstagramLoginResponse(String status, int statusCode, InstagramLoggedUser loggedUser) {
        super(status, statusCode);
        this.loggedUser = loggedUser;
        this.passwordValid = true;
        this.usernameValid = true;
        this.loginChallenge = null;
    }


    public InstagramLoginResponse(String status, int statusCode, String message, InstagramLoginChallenge loginChallenge) {
        super(status, statusCode, message);
        this.loginChallenge = loginChallenge;
        this.passwordValid = true;
        this.usernameValid = true;
        this.loggedUser = null;
    }

    public InstagramLoginResponse(String status, int statusCode, String message, boolean passwordValid, boolean usernameValid) {
        super(status, statusCode, message);
        this.passwordValid = passwordValid;
        this.usernameValid = usernameValid;
        this.loggedUser = null;
        this.loginChallenge = null;
    }


    @Override
    public String toString() {
        return "InstagramLoginResponse{" +
                "loggedUser=" + loggedUser +
                ", passwordValid=" + passwordValid +
                ", usernameValid=" + usernameValid +
                ", loginChallenge=" + loginChallenge +
                '}';
    }
}
