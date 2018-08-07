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

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.instagramlib.database.InstagramUserDbSchema.InstagramUserTable;
import com.example.instagramlib.database.InstagramUserDbSchema.InstagramUserTable.Cols;

import static com.example.instagramlib.database.InstagramUserDbSchema.InstagramUserTable.Cols.*;

/**
 * InstagramLib API
 *
 * @author Miljan Rakita on 8/7/18.
 * rakitamiljan@yahoo.com
 */
public class InstagramUserDatabaseHelper extends SQLiteOpenHelper {

    private static final int VERSION = 1;
    private static final String DATABASE_NAME = "instagramUser.db";

    public InstagramUserDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + InstagramUserTable.NAME + "(" +
                " id integer primary key autoincrement, " +
                USERNAME + ", " +
                PK + ", " +
                COOKIES + ", " +
                PROFILE_PIC_URL +
                ")"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.i(getClass().getName(), "upgrade called old version: " + String.valueOf(oldVersion) + " new version: " + String.valueOf(newVersion));
    }
}
