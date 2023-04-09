package com.example.hungntph19080_assignment_mob403.Activity.Main.TabsFragmnet.Home;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.hungntph19080_assignment_mob403.Adapter.ComicAdapter;
import com.example.hungntph19080_assignment_mob403.Model.Comic;
import com.example.hungntph19080_assignment_mob403.R;

import com.example.hungntph19080_assignment_mob403.databinding.FragmentHomePageBinding;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;

public class HomePageFragment extends Fragment {
    FragmentHomePageBinding binding;
    RecyclerView rcvListComic;
    ArrayList<Comic> list;
    ImageButton imgSearch;
    SearchFragment searchFragment;
    String url_api = "https://f21oe9-8080.csb.app/images";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentHomePageBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();

        init();
        VolleyGetComic();

        imgSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((FragmentActivity) view.getContext()).getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.frame_layout_home_page, searchFragment).addToBackStack(String.valueOf(this))
                        .commit();
            }
        });
        return view;

    }

    private void init() {
        list = new ArrayList<>();
        rcvListComic = binding.recyclerViewListsComic;
        imgSearch = binding.imgNextSearch;
        searchFragment = new SearchFragment();
    }

    void VolleyGetComic() {
        RequestQueue queue = Volley.newRequestQueue(getContext());
        StringRequest request = new StringRequest(Request.Method.GET, url_api, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Gson gson = new Gson();
                Type listType = new TypeToken<List<Comic>>() {
                }.getType();
                list = gson.fromJson(response, listType);

//                Log.d("listTruyen", "onResponse: "+list);

                GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 2);
                rcvListComic.setLayoutManager(gridLayoutManager);
                ComicAdapter adapter = new ComicAdapter(list, getContext());
                rcvListComic.setAdapter(adapter);
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