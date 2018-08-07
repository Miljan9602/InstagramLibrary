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
package com.example.instagramlib.database;

import android.database.Cursor;
import android.database.CursorWrapper;

import com.example.instagramlib.model.database.InstagramDatabaseUser;

import static com.example.instagramlib.database.InstagramUserDbSchema.InstagramUserTable.Cols.COOKIES;
import static com.example.instagramlib.database.InstagramUserDbSchema.InstagramUserTable.Cols.PK;
import static com.example.instagramlib.database.InstagramUserDbSchema.InstagramUserTable.Cols.PROFILE_PIC_URL;
import static com.example.instagramlib.database.InstagramUserDbSchema.InstagramUserTable.Cols.USERNAME;

/**
 * InstagramLib API
 *
 * @author Miljan Rakita on 8/7/18.
 * rakitamiljan@yahoo.com
 */
public class InstagramUserCursorWrapper extends CursorWrapper {


    public InstagramUserCursorWrapper(Cursor cursor) {
        super(cursor);
    }

    public InstagramDatabaseUser getUser() {
        String username = getString(getColumnIndex(USERNAME));
        String pk = getString(getColumnIndex(PK));
        String cookies = getString(getColumnIndex(COOKIES));
        String profilePicUrl = getString(getColumnIndex(PROFILE_PIC_URL));

        return new InstagramDatabaseUser(username, pk, cookies, profilePicUrl);
    }
}
