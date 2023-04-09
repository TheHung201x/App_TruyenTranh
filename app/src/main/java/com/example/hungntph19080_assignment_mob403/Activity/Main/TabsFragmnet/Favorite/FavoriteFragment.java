package com.example.hungntph19080_assignment_mob403.Activity.Main.TabsFragmnet.Favorite;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.hungntph19080_assignment_mob403.databinding.FragmentFavoriteBinding;

import java.util.ArrayList;


public class FavoriteFragment extends Fragment {
    FragmentFavoriteBinding binding;
    RecyclerView rcvFavorite;
    ArrayList list;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentFavoriteBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();

        init();

        Bundle bundle = this.getArguments();
//        String s = bundle.getString("abc");
        Log.d("LayLIST", "onCreateView: "+bundle);

//        LinearLayoutManager linearLayoutManager
//                = new LinearLayoutManager(getContext());
//        rcvFavorite.setLayoutManager(linearLayoutManager);
//
//        FavoriteAdapter adapter = new FavoriteAdapter(getContext(),list);
//        rcvFavorite.setAdapter(adapter);

        return view;
    }

    private void init() {
        rcvFavorite = binding.recyclerViewFavorite;
        list = new ArrayList<>();
    }
}