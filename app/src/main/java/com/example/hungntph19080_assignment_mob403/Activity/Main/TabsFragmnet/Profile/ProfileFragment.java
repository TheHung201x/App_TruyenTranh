package com.example.hungntph19080_assignment_mob403.Activity.Main.TabsFragmnet.Profile;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.hungntph19080_assignment_mob403.Activity.LoginActivity;
import com.example.hungntph19080_assignment_mob403.databinding.FragmentProfileBinding;

public class ProfileFragment extends Fragment {
    FragmentProfileBinding binding;
    Button btnLogOut;
    TextView tvUsernameProfile, tvEmailProfile, tvFullNameProfile;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentProfileBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();

        init();
        SharedPreferences sharedPref = getContext().getSharedPreferences("MyPreferences", Context.MODE_PRIVATE);

        String user = sharedPref.getString("username", "");
        String email = sharedPref.getString("email", "");
        String fullname = sharedPref.getString("fullname", "");

        tvUsernameProfile.setText(user);
        tvEmailProfile.setText(email);
        tvFullNameProfile.setText(fullname);
//
//        btnEdit.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
//                LayoutInflater inflater = LayoutInflater.from(getContext());
//                View view1 = inflater.inflate(R.layout.dialog_edit_user,null);
//                builder.setView(view1);
//                AlertDialog dialog = builder.create();
//                dialog.show();
//            }
//        });

        btnLogOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), LoginActivity.class);
                startActivity(intent);
                getActivity().finish();
            }
        });

        return view;
    }

    private void init() {
        btnLogOut = binding.btnLogOut;
        tvUsernameProfile = binding.tvUsernameProfile;
        tvEmailProfile = binding.tvEmailProfile;
        tvFullNameProfile = binding.tvFulllNameProfile;
    }
}