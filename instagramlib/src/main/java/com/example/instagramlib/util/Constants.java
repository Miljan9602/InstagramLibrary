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
package com.example.instagramlib.util;

/**
 * InstagramLib API
 *
 * @author Miljan Rakita on 7/18/18.
 * rakitamiljan@yahoo.com
 */
public interface Constants {

    String ENCODING = "UTF-8";

    String CHARSET_NAME = "UTF-8";

    String HASH_ALGORITHM = "HmacSHA256";

    String STRING_CHARSET_NAME = "ISO-8859-1";

    String HTTPS = "https";
    String HTTP = "http";


    String POST_METHOD = "POST";
    String GET_METHOD = "GET";

    String STATUS_OK = "ok";

    // Errors
    String ERROR_INVALID_CREDENTIALS = "You must provide a username and password to login.";
    String ERROR_REQUIRES_LOGIN = "Need to login first!";
    String ERROR_NULLABLE_CONTEXT = "Context cant be null.";
}
