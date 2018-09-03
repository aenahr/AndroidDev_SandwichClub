package com.udacity.sandwichclub.utils;
import com.udacity.sandwichclub.model.Sandwich;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class JsonUtils {

    public static Sandwich parseSandwichJson(String json) {

        try{
            // turn string into a JSON object
            JSONObject oSandwich = new JSONObject(json);
            JSONObject oName = oSandwich.getJSONObject("name");

            // begin parsing through the JSON
            String mainName = oName.getString("mainName");
            String placeOfOrigin = oSandwich.getString("placeOfOrigin");
            String description = oSandwich.getString("description");
            String image = oSandwich.getString("image");

            // For alsoKnownAs and Ingredients, they are both arrays so parse through the data
            JSONArray oKnownAs = oName.getJSONArray("alsoKnownAs");
            List<String> alsoKnownAs = new ArrayList<String>();
            for(int i = 0; i < oKnownAs.length(); i++){
                alsoKnownAs.add(oKnownAs.getString(i));
            }

            JSONArray oIngredients = oSandwich.getJSONArray("ingredients");
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
