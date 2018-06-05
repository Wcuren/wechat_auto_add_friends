package com.lj.autoapp;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonParser;

/**
 * Created by lulingjie on 2018/5/30.
 */

public class JsonUtil {
    private static final String TAG = "JsonUtil";

    public static ContactsModel[] get(String json) {
        Log.d(TAG, "json string is:" + json);
        JsonParser parser = new JsonParser();
        JsonArray jsonArray = parser.parse(json).getAsJsonArray();
        Gson gson = new Gson();
        ContactsModel[] contactsModels= new ContactsModel[jsonArray.size()];
        for (int i = 0; i < jsonArray.size(); i++) {
            contactsModels[i] = gson.fromJson(jsonArray.get(i), ContactsModel.class);
            Log.d(TAG, "contact no." + i + " is:" + contactsModels[i].toString());
        }
        return contactsModels;
    }
}
