package com.antgul.antgul_android.ui.community.recyclerView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.antgul.antgul_android.databinding.ItemPostRecyclerBinding;
import com.antgul.antgul_android.model.Post;

import org.jetbrains.annotations.NotNull;

public class SearchHolder extends CommunityHolder {
    private CommunityAdapter.OnItemClickListener itemClickListener;
    public ItemPostRecyclerBinding itemPostRecyclerBinding;

    public SearchHolder(@NonNull @NotNull ItemPostRecyclerBinding itemPostRecyclerBinding, CommunityAdapter.OnItemClickListener itemClick) {
        super(itemPostRecyclerBinding.getRoot());

        this.itemPostRecyclerBinding = itemPostRecyclerBinding;
        this.itemClickListener = itemClick;
    }

    public void onBind(Post post) {
        itemPostRecyclerBinding.getRoot().setOnClickListener(v -> {
            int pos = getAdapterPosition();
            if (pos != RecyclerView.NO_POSITION) {
                if (itemClickListener != null) {
                    itemClickListener.onItemClick(v, pos);
                }
            }
        });
        itemPostRecyclerBinding.itemPostTitle.setText(post.getTitle());
        itemPostRecyclerBinding.itemPostContent.setText(post.getContent());
        itemPostRecyclerBinding.itemPostNickname.setText(post.getWriterNickname());
        itemPostRecyclerBinding.itemPostTime.setText(post.getCreateAt());
    }
}
