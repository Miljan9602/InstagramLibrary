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

import android.content.Context;
import android.preference.PreferenceManager;

/**
 * InstagramLib API
 *
 * @author Miljan Rakita on 7/18/18.
 * rakitamiljan@yahoo.com
 */

/**
 * QueryPreferences, new class which will serve as a convenient interface for reading and
 * writing the query to and from shared preferences.
 */
public class QueryPreferences {

    /**
     * @param context
     * @param KEY
     * @return
     */
    public static String getStoredQuery(Context context, String KEY) {
        return PreferenceManager.getDefaultSharedPreferences(context)
                .getString(KEY, null);
    }

    /**
     * @param context
     * @param query
     * @param KEY
     */
    public static void setStoredQuery(Context context, String query, String KEY) {
        PreferenceManager.getDefaultSharedPreferences(context)
                .edit()
                .putString(KEY, query)
                .apply();
    }


}
