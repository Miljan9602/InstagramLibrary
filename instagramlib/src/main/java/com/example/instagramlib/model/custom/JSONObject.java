package com.example.instagramlib.model.custom;

import org.json.JSONException;
import org.json.JSONTokener;

import java.util.Map;

/**
 * InstagramLib API
 *
 * @author Miljan Rakita on 8/7/18.
 * rakitamiljan@yahoo.com
 */
public class JSONObject extends org.json.JSONObject {

    public JSONObject() {
    }

    public JSONObject(Map copyFrom) {
        super(copyFrom);
    }

    public JSONObject(JSONTokener readFrom) throws JSONException {
        super(readFrom);
    }

    public JSONObject(String json) throws JSONException {
        super(json);
    }

    public JSONObject(org.json.JSONObject copyFrom, String[] names) throws JSONException {
        super(copyFrom, names);
    }

    @Override
    public String getString(String name) {
        try {
            return super.getString(name);
        } catch (JSONException e) {
            return null;
        }
    }

    @Override
    public boolean getBoolean(String name) throws JSONException {
        try {
            return super.getBoolean(name);
        } catch (JSONException e) {
            return false;
        }
    }

    public JSONObject getJSONObject(String name) throws JSONException {
        return new JSONObject(super.getJSONObject(name).toString());
    }
}
