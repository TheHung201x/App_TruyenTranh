package com.example.hungntph19080_assignment_mob403.Adapter;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.hungntph19080_assignment_mob403.Activity.Main.TabsFragmnet.Home.ShowComicFragment;
import com.example.hungntph19080_assignment_mob403.Activity.Main.TabsFragmnet.Home.HomePageFragment;
import com.example.hungntph19080_assignment_mob403.Model.Comic;
import com.example.hungntph19080_assignment_mob403.R;

import java.util.ArrayList;
import java.util.List;

public class ComicAdapter extends RecyclerView.Adapter<ComicAdapter.ViewHolder> implements Filterable {
    List<Comic> comicListOld;
    List<Comic> comicList;
    Context context;

    public ComicAdapter(List<Comic> comicListOld, Context context) {
        this.comicListOld = comicListOld;
        this.comicList = comicListOld;
        this.context = context;
    }

    @NonNull
    @Override
    public ComicAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_comic,
                parent, false);
        return new ComicAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Glide.with(context)
                .load(comicList.get(position).getCoverImageComic())
                .centerCrop()
                .placeholder(R.drawable.ic_launcher_background)
                .into(holder.coverImage);

        ShowComicFragment fragment = new ShowComicFragment();
        HomePageFragment homePageFragment = new HomePageFragment();
        Bundle bundle = new Bundle();
        bundle.putString("Id", String.valueOf(comicList.get(position).getId()));
        bundle.putString("CoverImage", comicList.get(position).getCoverImageComic());
        bundle.putString("Name", comicList.get(position).getNameComic());
        bundle.putString("Description", comicList.get(position).getDescriptionComic());
        bundle.putString("Author", comicList.get(position).getAuthorComic());
        bundle.putString("PublishYear", comicList.get(position).getPublishYearComic());
        bundle.putParcelableArrayList("ListImage", comicList.get(position).getListsImageComic());
        fragment.setArguments(bundle);

        Log.d("testList", "onBindViewHolder: " + comicList.get(position).getListsImageComic());

        holder.coverImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((FragmentActivity) view.getContext()).getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.frame_layout_home_page, fragment).addToBackStack(String.valueOf(homePageFragment))
                        .commit();
            }
        });
        holder.tvName.setText(comicList.get(position).getNameComic());

    }

    @Override
    public int getItemCount() {
        return comicList.size();
    }



    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView coverImage;
        TextView tvName;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            coverImage = (ImageView) itemView.findViewById(R.id.imageComic);
            tvName = (TextView) itemView.findViewById(R.id.tvNameComicImage);
        }
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String searcgNameComic = charSequence.toString();
                if (searcgNameComic.isEmpty()) {
                    comicList = comicListOld;
                } else {
                    List<Comic> list = new ArrayList<>();
                    for (Comic book : comicListOld) {
                        if (book.getNameComic().toLowerCase().contains(searcgNameComic.toLowerCase())) {
                            list.add(book);
                        }
                    }
                    comicList = list;
                }

                FilterResults filterable = new FilterResults();
                filterable.values = comicList;

                return filterable;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                comicList = (List<Comic>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }
}
