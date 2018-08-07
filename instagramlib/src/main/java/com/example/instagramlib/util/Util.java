package com.example.instagramlib.util;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import okhttp3.Cookie;

/**
 * InstagramLib API
 *
 * @author Miljan Rakita on 8/7/18.
 * rakitamiljan@yahoo.com
 */
public final class Util {


    /**
     * Creates json object from cookies array
     *
     * @param cookies
     * @return
     */
    public static JSONArray cookieJarToJson(List<Cookie> cookies) throws JSONException {

        JSONArray array = new JSONArray();

        for (Cookie cookie : cookies) {
            JSONObject object = new JSONObject();

            object.put("name", cookie.name());
            object.put("value", cookie.value());
            object.put("domain", cookie.domain());
            object.put("path", cookie.path());
            object.put("expires", cookie.expiresAt());
            object.put("secure", cookie.secure());
            object.put("httpOnly", cookie.httpOnly());

            array.put(object);
        }

        return array;
    }

}
