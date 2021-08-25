package com.antgul.antgul_android.ui.community.recyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.antgul.antgul_android.databinding.ItemPostRecyclerBinding;
import com.antgul.antgul_android.model.Post;
import com.antgul.antgul_android.model.PostCase;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class CommunityAdapter extends RecyclerView.Adapter<CommunityHolder> {
    private ArrayList<Post> postList= null;
    private final PostCase post_type;
    public OnItemClickListener itemClickListener = null;

    public CommunityAdapter(ArrayList<Post> postList, PostCase post_type){
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
    public CommunityHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        if(post_type == PostCase.STOCK_INFO){
            return new StockHolder(ItemPostRecyclerBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false),
                    itemClickListener);
        }else if(post_type == PostCase.FREE_BOARD){
            return new PostHolder(ItemPostRecyclerBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false),
                    itemClickListener);
        }else if(post_type== PostCase.POST_SEARCH){
            return new SearchHolder(ItemPostRecyclerBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false),
                    itemClickListener);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull CommunityHolder holder, int position) {
        if(holder instanceof StockHolder){
            StockHolder stockHolder = (StockHolder)holder;
            stockHolder.onBind(postList.get(position));
        }else if(holder instanceof PostHolder){
            PostHolder postHolder = (PostHolder)holder;
            postHolder.onBind(postList.get(position));
        }else if(holder instanceof SearchHolder){
            SearchHolder searchHolder = (SearchHolder)holder;
            searchHolder.onBind(postList.get(position));
        }
    }

    @Override
    public int getItemCount() {
        return postList.size();
    }
}
