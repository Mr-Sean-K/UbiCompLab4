package com.example.fragments;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import org.json.JSONArray;
import java.util.ArrayList;

public class RecipeListFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_recipe_list, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        // read json
        JSONArray recipes = MainActivity.readJSON(getActivity());
        ArrayList<String> names = new ArrayList<>();
        for (int i = 0; i < recipes.length(); i++) {
            names.add(recipes.optJSONObject(i).optString("name")); // read name from each recipe
        }

        ListView list = (ListView) getView().findViewById(R.id.recipeList);

        // array adapter puts each name into a row in the list view
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getActivity(),
                android.R.layout.simple_list_item_1, names);
        list.setAdapter(adapter);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                FragmentActivity activity = getActivity();

                // orientation check
                if (activity.getResources().getConfiguration().orientation
                        == Configuration.ORIENTATION_LANDSCAPE) {

                    DetailFragment fragment = (DetailFragment)
                            activity.getSupportFragmentManager().findFragmentById(R.id.detailFragment);
                    fragment.showRecipe(position);
                } else {
                    Intent intent = new Intent(activity, DetailActivity.class);
                    intent.putExtra("position", position); // send which row is clicked if in portrait mode
                    startActivity(intent);
                }
            }
        });
    }
}