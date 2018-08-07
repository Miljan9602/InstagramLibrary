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

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.example.instagramlib.model.database.InstagramDatabaseUser;

import lombok.Getter;

/**
 * InstagramLib API
 *
 * @author Miljan Rakita on 8/8/18.
 * rakitamiljan@yahoo.com
 */
public interface IInstagramDatabaseIUser {

    /**
     * Saves user into database.
     *
     * @param user which needs to be saved.
     */
    void saveInstagramDatabaseUser(@NonNull final InstagramDatabaseUser user);


    /**
     * load user from database.
     *
     * @param username for user search.
     * @return
     */
    @Nullable
    InstagramDatabaseUser loadInstagramDatabaseUser(final String username);

    void updateInstagramDatabaseUser(@NonNull final InstagramDatabaseUser user);
}
