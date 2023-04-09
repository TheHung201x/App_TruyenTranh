package com.example.hungntph19080_assignment_mob403.Activity.Main.TabsFragmnet.Home;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.hungntph19080_assignment_mob403.Adapter.DetailsAdapter;
import com.example.hungntph19080_assignment_mob403.databinding.FragmentDetailsComicBinding;

import java.util.ArrayList;
import java.util.List;

public class DetailsComicFragment extends Fragment {
    FragmentDetailsComicBinding binding;
    RecyclerView rcvListDetails;
    List list;
    DetailsAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentDetailsComicBinding.inflate(getLayoutInflater());

        init();

        Bundle bundle = this.getArguments();
        List listImage = bundle.getParcelableArrayList("ListImage");

        for (Object test: listImage) {
            list.add(test);
        }
            Log.d("abcde", "onCreateView: "+list);

        LinearLayoutManager linearLayoutManager =new LinearLayoutManager(getContext());
        rcvListDetails.setLayoutManager(linearLayoutManager);

        adapter = new DetailsAdapter(getContext(), list);
        rcvListDetails.setAdapter(adapter);

        View view = binding.getRoot();
        return  view;
    }

    private void init(){
        rcvListDetails = binding.rcvListImageDetails;
        list = new ArrayList();
    }
}