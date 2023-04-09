package com.example.hungntph19080_assignment_mob403.Adapter;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.hungntph19080_assignment_mob403.Activity.Main.TabsFragmnet.Home.ShowComicFragment;
import com.example.hungntph19080_assignment_mob403.Activity.Main.TabsFragmnet.Home.HomePageFragment;
import com.example.hungntph19080_assignment_mob403.Model.Comic;
import com.example.hungntph19080_assignment_mob403.R;

import java.util.List;

public class ImageAdapter extends RecyclerView.Adapter<ImageAdapter.ViewHolder> {
    Context context;
    List<Comic> comicList;

    public ImageAdapter(Context context, List<Comic> comicList) {
        this.context = context;
        this.comicList = comicList;
    }

    @NonNull
    @Override
    public ImageAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_image,
                parent, false);
        return new ImageAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Glide.with(context)
                .load(comicList.get(position).getCoverImageComic())
                .centerCrop()
                .placeholder(R.drawable.ic_launcher_background)
                .into(holder.imgBackground);

        ShowComicFragment fragment = new ShowComicFragment();
        HomePageFragment homePageFragment = new HomePageFragment();
        Bundle bundle = new Bundle();
        bundle.putString("CoverImage", comicList.get(position).getCoverImageComic());
        bundle.putString("Name", comicList.get(position).getNameComic());
        bundle.putString("Description", comicList.get(position).getDescriptionComic());
        bundle.putString("Author", comicList.get(position).getAuthorComic());
        bundle.putString("PublishYear", comicList.get(position).getPublishYearComic());
        fragment.setArguments(bundle);

        holder.imgBackground.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ((FragmentActivity) view.getContext()).getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.frame_layout_home_page, fragment).addToBackStack(String.valueOf(homePageFragment))
                        .commit();
            }
        });

    }

    @Override
    public int getItemCount() {
        return comicList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imgBackground;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imgBackground = (ImageView) itemView.findViewById(R.id.imgBackground);
        }
    }
}
