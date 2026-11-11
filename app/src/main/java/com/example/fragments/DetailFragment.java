package com.example.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.fragment.app.Fragment;
import org.json.JSONObject;

public class DetailFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_detail, container, false);
    }

    // fragment retrieves data from intent
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        Intent intent = getActivity().getIntent();
        int position = intent.getIntExtra("position", -1);

        // if a row is tapped in portrait show that recipe
        if (position != -1) {
            showRecipe(position);
        }
    }

    public void showRecipe(int position) {
        JSONObject recipe = MainActivity.readJSON(getActivity()).optJSONObject(position);

        TextView name = (TextView) getActivity().findViewById(R.id.recipeName);
        TextView ingredients = (TextView) getActivity().findViewById(R.id.recipeIngredients);
        TextView method = (TextView) getActivity().findViewById(R.id.recipeMethod);

        name.setText(recipe.optString("name"));
        ingredients.setText("Ingredients:\n" + recipe.optString("ingredients"));
        method.setText("Method:\n" + recipe.optString("method"));
    }
}