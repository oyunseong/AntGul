package com.antgul.antgul_android.ui.community.recyclerView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.antgul.antgul_android.databinding.ItemStockInfoRecyclerBinding;
import com.antgul.antgul_android.model.Post;

import org.jetbrains.annotations.NotNull;

public class ViewHolderStock extends CommunityItemView {
    private RecyclerCommunityAdapter.OnItemClickListener itemClickListener;
    private ItemStockInfoRecyclerBinding itemStockInfoRecyclerBinding;

    public ViewHolderStock(@NonNull @NotNull ItemStockInfoRecyclerBinding itemStockInfoRecyclerBinding) {
        super(itemStockInfoRecyclerBinding.getRoot());
        this.itemStockInfoRecyclerBinding = itemStockInfoRecyclerBinding;

        itemStockInfoRecyclerBinding.getRoot().setOnClickListener(v -> {
            int pos = getAdapterPosition();
            if (pos != RecyclerView.NO_POSITION) {
                if (itemClickListener != null) {
                    itemClickListener.onItemClick(v, pos);
                }
            }
        });
    }


    public void onBind(Post post) {
        Post post1 = post;

//        Post post = mData.get(position);
//        holder.itemStockInfoRecyclerBinding.title.setText(post.getTitle());
////        holder.itemStockInfoRecyclerBinding.hashtag.setText(post.getHashTags());
////        holder.itemStockInfoRecyclerBinding.brokerage.setText(post.getWriterId());
    }
}
