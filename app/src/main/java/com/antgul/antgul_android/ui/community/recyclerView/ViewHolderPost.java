package com.antgul.antgul_android.ui.community.recyclerView;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.antgul.antgul_android.databinding.ItemStockInfoRecyclerBinding;
import com.antgul.antgul_android.model.Post;

public class ViewHolderPost extends CommunityItemView {
    private final ItemStockInfoRecyclerBinding itemStockInfoRecyclerBinding;
    private RecyclerCommunityAdapter.OnItemClickListener itemClickListener = null;

    public ViewHolderPost(@NonNull ItemStockInfoRecyclerBinding itemStockInfoRecyclerBinding) {
        super(itemStockInfoRecyclerBinding.getRoot());
        this.itemStockInfoRecyclerBinding = itemStockInfoRecyclerBinding;
        itemStockInfoRecyclerBinding.getRoot().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int pos = getAdapterPosition();
                if (pos != RecyclerView.NO_POSITION) {
                    if (itemClickListener != null) {
                        itemClickListener.onItemClick(v, pos);

                    }
                }
            }
        });
    }

    public void onBind(Post post) {
        Post post1 = post;
//        title.setText(this.post.getTitle());

//        Post post = mData.get(position);
//        holder.itemStockInfoRecyclerBinding.title.setText(post.getTitle());
////        holder.itemStockInfoRecyclerBinding.hashtag.setText(post.getHashTags());
////        holder.itemStockInfoRecyclerBinding.brokerage.setText(post.getWriterId());
    }
}


