package com.antgul.antgul_android.ui.community.recyclerView;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.antgul.antgul_android.databinding.ItemStockInfoRecyclerBinding;
import com.antgul.antgul_android.model.Post;

public class ViewHolderPost extends CommunityItemView {
    private ItemStockInfoRecyclerBinding binding;
    private RecyclerCommunityAdapter.OnItemClickListener itemClickListener = null;

    public ViewHolderPost(@NonNull ItemStockInfoRecyclerBinding binding, RecyclerCommunityAdapter.OnItemClickListener itemClickListener) {
        super(binding.getRoot());
        this.binding = binding;
        this.itemClickListener = itemClickListener;
    }

    public void onBind(Post post) {
        itemView.setOnClickListener(new View.OnClickListener() {
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
        binding.stockTitle.setText(post.getTitle());
    }


//                @Override
//                public void onClick(View v) {
//                    itemClickListener.onItemClick(v,position);
//                }
//            });
    //클릭리스너
//        title.setText(this.post.getTitle());

//        Post post = mData.get(position);
//        holder.itemStockInfoRecyclerBinding.title.setText(post.getTitle());
////        holder.itemStockInfoRecyclerBinding.hashtag.setText(post.getHashTags());
////        holder.itemStockInfoRecyclerBinding.brokerage.setText(post.getWriterId());

}


