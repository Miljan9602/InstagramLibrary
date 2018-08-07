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
package com.example.instagramlib.dataProvider;

import android.content.Context;

import com.example.instagramlib.IInstagramDatabaseIUser;

import lombok.Getter;

/**
 * InstagramLib API
 *
 * @author Miljan Rakita on 8/8/18.
 * rakitamiljan@yahoo.com
 */
public class DataProvider {

    private static DataProvider singletone = null;

    @Getter
    private IInstagramDatabaseIUser instagramDataProvider;

    private DataProvider(final Context context) {
        instagramDataProvider = new DatabaseDataProvider(context);
    }

    public static DataProvider getSingletone(final Context context) {

        if (singletone == null)
            singletone = new DataProvider(context);

        return singletone;
    }

}
