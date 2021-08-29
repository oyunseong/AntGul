package com.antgul.antgul_android.ui.community.recyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.antgul.antgul_android.LikeStockAdapter;
import com.antgul.antgul_android.databinding.ItemCommentRecyclerBinding;
import com.antgul.antgul_android.databinding.ItemLikeStockBinding;
import com.antgul.antgul_android.model.Comment;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class CommentAdapter extends RecyclerView.Adapter<CommentAdapter.ViewHolder> {
    private ArrayList<Comment> commentList;

    public CommentAdapter(ArrayList<Comment> commentList){
        this.commentList = commentList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(ItemCommentRecyclerBinding.inflate(LayoutInflater.from(parent.getContext()),
                parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull ViewHolder holder, int position) {
        Comment comment = commentList.get(position);
        holder.binding.commentNickname.setText(comment.getWriterNickname());
        holder.binding.commentContent.setText(comment.getComment());
        holder.binding.commentTime.setText(comment.getCreateAt());
//        holder.binding.commentLikeCount.setText(comment.getLikeList());
    }

    @Override
    public int getItemCount() {
        return commentList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ItemCommentRecyclerBinding binding;
        public ViewHolder(@NonNull  ItemCommentRecyclerBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }

}
