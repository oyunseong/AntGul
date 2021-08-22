package com.antgul.antgul_android.ui.community.recyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.antgul.antgul_android.databinding.ItemStockInfoRecyclerBinding;
import com.antgul.antgul_android.model.Post;
import com.antgul.antgul_android.model.PostCase;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class RecyclerCommunityAdapter extends RecyclerView.Adapter<CommunityItemView> {
    private ArrayList<Post> postList= null;
    private final PostCase post_type;
    public OnItemClickListener itemClickListener = null;

    public RecyclerCommunityAdapter(ArrayList<Post> postList, PostCase post_type){
        this.postList = postList;
        this.post_type = post_type;
    }

    public interface OnItemClickListener {
        void onItemClick(View v, int pos);
    }

    public void setOnItemClickListener(OnItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    @NonNull
    @NotNull
    @Override
    public CommunityItemView onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        if(post_type == PostCase.STOCK_INFO){
            return new ViewHolderStock(ItemStockInfoRecyclerBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));
        }else if(post_type == PostCase.FREE_BOARD){
            return new ViewHolderPost(ItemStockInfoRecyclerBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));
        }
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull CommunityItemView holder, int position) {
        if(holder instanceof ViewHolderStock){
            ViewHolderStock viewHolderStock = (ViewHolderStock)holder;
            viewHolderStock.onBind(postList.get(position));

//            Post post = mData.get(position);
//        holder.itemStockInfoRecyclerBinding.title.setText(post.getTitle());
//        holder.itemStockInfoRecyclerBinding.hashtag.setText(post.getHashTags());
//        holder.itemStockInfoRecyclerBinding.brokerage.setText(post.getWriterId());

        }else if(holder instanceof ViewHolderPost){
            ViewHolderPost viewHolderPost = (ViewHolderPost)holder;
            viewHolderPost.onBind(postList.get(position));
        }
    }

    @Override
    public int getItemCount() {
        return postList.size();
    }
}
