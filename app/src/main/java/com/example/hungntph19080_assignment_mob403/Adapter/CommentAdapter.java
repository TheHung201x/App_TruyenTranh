package com.example.hungntph19080_assignment_mob403.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hungntph19080_assignment_mob403.Model.Comment;
import com.example.hungntph19080_assignment_mob403.R;

import java.util.List;

public class CommentAdapter extends RecyclerView.Adapter<CommentAdapter.ViewHolder> {
    Context context;
    List<Comment> list;

    public CommentAdapter(Context context, List<Comment> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public CommentAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_comment,
                parent, false);
        return new CommentAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CommentAdapter.ViewHolder holder, int position) {

            holder.userComment.setText(list.get(position).getNameUserComment());
            holder.contentComment.setText(list.get(position).getContentComment());
            holder.dateComment.setText(list.get(position).getDateComment());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView userComment, contentComment, dateComment;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            userComment = itemView.findViewById(R.id.nameUserComment);
            contentComment = itemView.findViewById(R.id.contentComment);
            dateComment = itemView.findViewById(R.id.dateComment);
        }
    }
}
