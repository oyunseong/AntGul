package com.antgul.antgul_android.ui.community.board;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.antgul.antgul_android.databinding.ItemBoardRecyclerBinding;
import com.antgul.antgul_android.databinding.ItemStockInfoRecyclerBinding;
import com.antgul.antgul_android.model.Comment;
import com.antgul.antgul_android.model.Community;
import com.antgul.antgul_android.model.Post;

import java.util.ArrayList;
import java.util.List;

public class RecyclerViewCommunityAdapter extends RecyclerView.Adapter<RecyclerViewCommunityAdapter.ViewHolder> {
    private ArrayList<Post> mData = null;
    private OnItemClickListener itemClickListener = null;

    public RecyclerViewCommunityAdapter(ArrayList<Post> mData) {
        this.mData = mData;
    }

    public interface OnItemClickListener {
        void onItemClick(View v, int pos);
    }

    public void setOnItemClickListener(OnItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(ItemStockInfoRecyclerBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewCommunityAdapter.ViewHolder holder, int position) {
        Post post = mData.get(position);

//        public String postId;
//        public String title;
//        public String content;
//        public List<String> imageList;
//        public String writerId; //User - uid
//        public List<Comment> commentList;
//        public int category;
//        public String createAt;
//        public List<String> hashTags;

//        holder.boardRecyclerItemBinding.boardImage.getDrawable();
        holder.itemStockInfoRecyclerBinding.title.setText("게시글 제목");
        holder.itemStockInfoRecyclerBinding.stockName.setText("stockName");
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private final ItemStockInfoRecyclerBinding itemStockInfoRecyclerBinding;


        public ViewHolder(@NonNull ItemStockInfoRecyclerBinding itemStockInfoRecyclerBinding) {
            super(itemStockInfoRecyclerBinding.getRoot());
            this.itemStockInfoRecyclerBinding = itemStockInfoRecyclerBinding;

            itemStockInfoRecyclerBinding.getRoot().setOnClickListener(v -> {
                int pos = getAdapterPosition();
                if (pos != RecyclerView.NO_POSITION) {
                    if (itemClickListener != null) {
                        itemClickListener.onItemClick(v, pos);
                        //TODO 리사이클러뷰 아이템 클릭시 DetailBoardFragment로 이동시키기
                    }
                }
            });
        }
    }

    public void addItem(Post Post) {
        mData.add(Post);
        notifyItemInserted(getItemCount());
    }
}
