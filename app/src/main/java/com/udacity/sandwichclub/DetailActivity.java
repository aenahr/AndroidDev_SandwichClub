package com.udacity.sandwichclub;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.udacity.sandwichclub.model.Sandwich;
import com.udacity.sandwichclub.utils.JsonUtils;

public class DetailActivity extends AppCompatActivity {

    public static final String EXTRA_POSITION = "extra_position";
    private static final int DEFAULT_POSITION = -1;

    // nsert text view fields from the xml
    private TextView mAlsoKnownAs;
    private TextView mPlaceOfOrigin;
    private TextView mDescription;
    private TextView mIngredients;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        ImageView ingredientsIv = findViewById(R.id.image_iv);
        // initialize views to the activity_detail layout objects
        mAlsoKnownAs = (TextView)findViewById(R.id.also_known_tv);
        mPlaceOfOrigin = (TextView)findViewById(R.id.origin_tv);
        mDescription = (TextView)findViewById(R.id.description_tv);
        mIngredients = (TextView)findViewById(R.id.ingredients_tv);

        Intent intent = getIntent();
        if (intent == null) {
            closeOnError();
        }

        int position = intent.getIntExtra(EXTRA_POSITION, DEFAULT_POSITION);
        if (position == DEFAULT_POSITION) {
            // EXTRA_POSITION not found in intent
            closeOnError();
            return;
        }

        String[] sandwiches = getResources().getStringArray(R.array.sandwich_details);
        String json = sandwiches[position];
        Sandwich sandwich = JsonUtils.parseSandwichJson(json);
        //Toast.makeText(this, json, Toast.LENGTH_LONG).show();
        if (sandwich == null) {
            // Sandwich data unavailable
            closeOnError();
            return;
        }

        populateUI(sandwich);
        Picasso.with(this)
                .load(sandwich.getImage())
                .into(ingredientsIv);

        setTitle(sandwich.getMainName());
    }

    private void closeOnError() {
        finish();
        Toast.makeText(this, R.string.detail_error_message, Toast.LENGTH_SHORT).show();
    }

    private void populateUI(Sandwich s) {
        // add data from sandwich class to the TextViews
        for(int i = 0; i < s.getAlsoKnownAs().size();i++){
            if(i == s.getAlsoKnownAs().size()-1){
                mAlsoKnownAs.append(s.getAlsoKnownAs().get(i) );
            }else{
                mAlsoKnownAs.append(s.getAlsoKnownAs().get(i) + ", ");
            }
        }

        mPlaceOfOrigin.setText(s.getPlaceOfOrigin());
        mDescription.setText(s.getDescription());

        for(int i = 0; i < s.getIngredients().size();i++){
            if(i == s.getIngredients().size()-1){
                mIngredients.append(s.getIngredients().get(i) );
            }else{
                mIngredients.append(s.getIngredients().get(i) + ", ");
            }
        }
    }
}
