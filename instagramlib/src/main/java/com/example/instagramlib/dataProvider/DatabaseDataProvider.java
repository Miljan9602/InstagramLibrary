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

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.example.instagramlib.IInstagramDatabaseIUser;
import com.example.instagramlib.database.InstagramUserCursorWrapper;
import com.example.instagramlib.database.InstagramUserDatabaseHelper;
import com.example.instagramlib.database.InstagramUserDbSchema;
import com.example.instagramlib.model.database.InstagramDatabaseUser;

import static com.example.instagramlib.database.InstagramUserDbSchema.InstagramUserTable.Cols.COOKIES;
import static com.example.instagramlib.database.InstagramUserDbSchema.InstagramUserTable.Cols.PK;
import static com.example.instagramlib.database.InstagramUserDbSchema.InstagramUserTable.Cols.PROFILE_PIC_URL;
import static com.example.instagramlib.database.InstagramUserDbSchema.InstagramUserTable.Cols.USERNAME;

/**
 * InstagramLib API
 *
 * @author Miljan Rakita on 8/8/18.
 * rakitamiljan@yahoo.com
 */
public class DatabaseDataProvider implements IInstagramDatabaseIUser {

    private final Context context;
    private SQLiteDatabase database;

    public DatabaseDataProvider(Context context) {
        this.context = context;
        database = new InstagramUserDatabaseHelper(context).getWritableDatabase();
    }

    private static ContentValues getContentValues(InstagramDatabaseUser user) {

        ContentValues contentValues = new ContentValues();

        contentValues.put(USERNAME, user.getUsername());
        contentValues.put(PK, user.getPk());
        contentValues.put(COOKIES, user.getCookies());
        contentValues.put(PROFILE_PIC_URL, user.getProfilePicUrl());

        return contentValues;
    }

    @Override
    public void saveInstagramDatabaseUser(@NonNull InstagramDatabaseUser user) {

        if (user == null) return;


        // If user does not exist. Insert new user. Othervise just update cookie.
        if (loadInstagramDatabaseUser(user.getUsername()) == null) {
            database.insert(InstagramUserDbSchema.InstagramUserTable.NAME, null, getContentValues(user));
        } else {
            updateInstagramDatabaseUser(user);
        }
    }

    @Nullable
    @Override
    public InstagramDatabaseUser loadInstagramDatabaseUser(String username) {
        InstagramUserCursorWrapper cursor = queryCursor(USERNAME + " = ?", new String[]{username});

        InstagramDatabaseUser user = null;

        try {
            // got user
            if (cursor.moveToFirst()) {
                user = cursor.getUser();
            }
        } finally {
            cursor.close();
        }
        return user;
    }

    @Override
    public void updateInstagramDatabaseUser(@NonNull InstagramDatabaseUser user) {

        if (user == null) return;

        database.update(InstagramUserDbSchema.InstagramUserTable.NAME, getContentValues(user), USERNAME + " = ?", new String[]{user.getUsername()});
    }

    /**
     * @param whereClaus
     * @param whereArgs
     * @return cursor for the passed parameters.
     */
    private InstagramUserCursorWrapper queryCursor(@Nullable String whereClaus, @Nullable String[] whereArgs) {

        Cursor cursor = database.query(
                InstagramUserDbSchema.InstagramUserTable.NAME,
                null, // Columns - null means select all columns
                whereClaus,
                whereArgs,
                null, // group by
                null, // having
                null // order by
        );

        return new InstagramUserCursorWrapper(cursor);
    }

}
