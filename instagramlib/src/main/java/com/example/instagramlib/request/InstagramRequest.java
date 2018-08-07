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

import com.example.instagramlib.Instagram;

import java.io.IOException;

import lombok.Getter;
import lombok.Setter;

/**
 * InstagramLib API
 *
 * @author Miljan Rakita on 7/18/18.
 * rakitamiljan@yahoo.com
 */
public abstract class InstagramRequest<T> {

    private static final String TAG = InstagramRequest.class.getSimpleName();

    @Setter
    @Getter
    protected Instagram instagram;

    public void setInstagram(Instagram instagram) {
        this.instagram = instagram;
    }

    public abstract String getUrl();

    public abstract String getMethod();

    public String getPayload() {
        return null;
    }

    public abstract T parseResult(int var1, String var2);

    public abstract T execute() throws IOException;

    public boolean requiresLogin() {
        return true;
    }

}
