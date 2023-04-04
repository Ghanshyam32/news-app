package com.ghanshyam.vivanews;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Tech extends Fragment {

    String api = "PASTE-YOUR-KEY";
    ArrayList<Model> models;
    Adapter adapter;
    String country = "in";
    private RecyclerView recyclerViewTech;
    private String category = "technology";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.technologyfragment, null);

        recyclerViewTech = v.findViewById(R.id.recyclerTechnology);
        models = new ArrayList<>();
        recyclerViewTech.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new Adapter(getContext(), models);
        recyclerViewTech.setAdapter(adapter);

        findNews();

        return v;

    }

    private void findNews() {

        ApiUtilities.getApiInterface().getCategoryNews(country, category, 100, api).enqueue(new Callback<MainNews>() {
            @Override
            public void onResponse(Call<MainNews> call, Response<MainNews> response) {
                if(response.isSuccessful()){
                    models.addAll(response.body().getArticles());
                    adapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<MainNews> call, Throwable t) {

            }
        });

    }
}
