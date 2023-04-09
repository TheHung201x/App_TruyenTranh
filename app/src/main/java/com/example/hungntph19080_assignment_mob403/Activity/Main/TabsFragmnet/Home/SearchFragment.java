package com.example.hungntph19080_assignment_mob403.Activity.Main.TabsFragmnet.Home;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.hungntph19080_assignment_mob403.Adapter.ComicAdapter;
import com.example.hungntph19080_assignment_mob403.Model.Comic;
import com.example.hungntph19080_assignment_mob403.databinding.FragmentSearchBinding;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class SearchFragment extends Fragment {
    FragmentSearchBinding binding;
    android.widget.SearchView searchNameComic;
    RecyclerView rcvSearchComic;
    ComicAdapter adapter;
    List<Comic> listComic;
    String url_api = "https://f21oe9-8080.csb.app/images";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentSearchBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();

        init();

        VolleyGetComic();

        searchNameComic.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                adapter.getFilter().filter(s);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                adapter.getFilter().filter(s);
                return false;
            }
        });

        return view;
    }

    private void init(){
        searchNameComic = binding.searchNameComic;
        rcvSearchComic = binding.recyclerViewSearchComic;
        listComic =new ArrayList<>();
        adapter = new ComicAdapter(listComic,getContext());
    }

    void VolleyGetComic() {
        RequestQueue queue = Volley.newRequestQueue(getContext());
        StringRequest request = new StringRequest(Request.Method.GET, url_api, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Gson gson = new Gson();
                Type listType = new TypeToken<List<Comic>>() {
                }.getType();
                listComic = gson.fromJson(response, listType);

                GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 2);
                rcvSearchComic.setLayoutManager(gridLayoutManager);
                ComicAdapter adapter = new ComicAdapter(listComic, getContext());
                rcvSearchComic.setAdapter(adapter);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("zzzzzz", "onErrorResponse: " + error);

            }
        });
        queue.add(request);
    }
}