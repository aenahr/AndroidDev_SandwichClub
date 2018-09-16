package com.udacity.sandwichclub.utils;
import com.udacity.sandwichclub.model.Sandwich;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class JsonUtils {

    public static final String JSON_OBJECT_KEY = "name";
    public static final String JSON_MAINNAME_KEY = "mainName";
    public static final String JSON_ORIGIN_KEY = "placeOfOrigin";
    public static final String JSON_DESCRIPTION_KEY = "description";
    public static final String JSON_IMAGE_KEY = "image";
    public static final String JSON_KNOWNAS_KEY = "alsoKnownAs";
    public static final String JSON_INGREDIENTS_KEY = "ingredients";

    public static Sandwich parseSandwichJson(String json) {

        try{
            // turn string into a JSON object
            JSONObject oSandwich = new JSONObject(json);
            JSONObject oName = oSandwich.optJSONObject(JSON_OBJECT_KEY);

            // begin parsing through the JSON
            String mainName = oName.optString(JSON_MAINNAME_KEY);
            String placeOfOrigin = oSandwich.optString(JSON_ORIGIN_KEY);
            String description = oSandwich.optString(JSON_DESCRIPTION_KEY);
            String image = oSandwich.optString(JSON_IMAGE_KEY);

            // For alsoKnownAs and Ingredients, they are both arrays so parse through the data
            JSONArray oKnownAs = oName.optJSONArray(JSON_KNOWNAS_KEY);
            List<String> alsoKnownAs = new ArrayList<String>();
            for(int i = 0; i < oKnownAs.length(); i++){
                alsoKnownAs.add(oKnownAs.getString(i));
            }

            JSONArray oIngredients = oSandwich.getJSONArray(JSON_INGREDIENTS_KEY);
            List<String> ingredients = new ArrayList<String>();
            for(int i = 0; i < oIngredients.length(); i++){
                ingredients.add(oIngredients.getString(i));
            }

            // add all parsed data to Sandwich class
            Sandwich s = new Sandwich(mainName, alsoKnownAs, placeOfOrigin, description, image, ingredients);
            return s;

        }catch(JSONException e){
            e.printStackTrace();
            return null;
        }
    }
}
