package com.example.hungntph19080_assignment_mob403.Adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.hungntph19080_assignment_mob403.Model.Favorite;
import com.example.hungntph19080_assignment_mob403.R;

import java.util.List;

public class FavoriteAdapter extends RecyclerView.Adapter<FavoriteAdapter.ViewHolder> {
    Context context;
    List<Favorite> list;

    public FavoriteAdapter(Context context, List<Favorite> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public FavoriteAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_favorite,
                parent, false);
        return new FavoriteAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Glide.with(context)
                .load(list.get(position).getCoverImageComicFavorite())
                .centerCrop()
                .placeholder(R.drawable.ic_launcher_background)
                .into(holder.coverImageComicFavorite);


        Log.d("testList", "onBindViewHolder: "+list.get(position).getListsImageComicFavorite());

        holder.btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                ((FragmentActivity) view.getContext()).getSupportFragmentManager()
//                        .beginTransaction()
//                        .replace(R.id.frame_layout_home_page, fragment).addToBackStack(String.valueOf(homePageFragment))
//                        .commit();
                Toast.makeText(context, "Okela",Toast.LENGTH_SHORT).show();
            }
        });
        holder.tvNameComicFavorite.setText(list.get(position).getNameComicFavorite());
        holder.tvDescriptionComicFavorite.setText(list.get(position).getDescriptionComicFavorite());
        holder.tvAuthorComicFavorite.setText(list.get(position).getAuthorComicFavorite());
        holder.tvPublishYearComicFavorite.setText(list.get(position).getPublishYearComicFavorite());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView coverImageComicFavorite;
        TextView tvNameComicFavorite, tvDescriptionComicFavorite, tvAuthorComicFavorite, tvPublishYearComicFavorite;
        ImageButton btnDelete;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            coverImageComicFavorite = (ImageView) itemView.findViewById(R.id.imgComicFavorite);
            tvNameComicFavorite = (TextView) itemView.findViewById(R.id.tvNameComicFavorite);
            tvDescriptionComicFavorite = (TextView) itemView.findViewById(R.id.tvDescriptionComicFavorite);
            tvAuthorComicFavorite = (TextView) itemView.findViewById(R.id.tvAuthorComicFavorite);
            tvPublishYearComicFavorite = (TextView) itemView.findViewById(R.id.tvPublishYearComicFavorite);
            btnDelete = (ImageButton) itemView.findViewById(R.id.btnDeleteComicFavorite);
        }
    }
}
