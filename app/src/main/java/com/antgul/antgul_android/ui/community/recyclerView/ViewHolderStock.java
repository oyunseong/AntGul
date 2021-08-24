package com.antgul.antgul_android.ui.community.recyclerView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.antgul.antgul_android.databinding.ItemStockInfoRecyclerBinding;
import com.antgul.antgul_android.model.Post;

import org.jetbrains.annotations.NotNull;

public class ViewHolderStock extends CommunityItemView {
    private RecyclerCommunityAdapter.OnItemClickListener itemClickListener;
    private ItemStockInfoRecyclerBinding itemStockInfoRecyclerBinding;

    public ViewHolderStock(@NonNull @NotNull ItemStockInfoRecyclerBinding itemStockInfoRecyclerBinding, RecyclerCommunityAdapter.OnItemClickListener itemClick) {
        super(itemStockInfoRecyclerBinding.getRoot());
        this.itemStockInfoRecyclerBinding = itemStockInfoRecyclerBinding;
        this.itemClickListener = itemClick;
    }

    public void onBind(Post post) {
        itemStockInfoRecyclerBinding.getRoot().setOnClickListener(v -> {
            int pos = getAdapterPosition();
            if (pos != RecyclerView.NO_POSITION) {
                if (itemClickListener != null) {
                    itemClickListener.onItemClick(v, pos);
                }
            }
        });
    }
}
