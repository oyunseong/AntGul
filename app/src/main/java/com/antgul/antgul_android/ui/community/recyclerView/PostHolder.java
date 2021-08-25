package com.antgul.antgul_android.ui.community.recyclerView;

import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.antgul.antgul_android.MainFragment;
import com.antgul.antgul_android.R;
import com.antgul.antgul_android.databinding.ItemPostRecyclerBinding;
import com.antgul.antgul_android.model.Post;
import com.antgul.antgul_android.model.User;
import com.antgul.antgul_android.ui.start.login.NicknameFragment;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import org.jetbrains.annotations.NotNull;

import static com.antgul.antgul_android.base.ApplicationClass.USERS_COLLECTION;

public class PostHolder extends CommunityHolder {
    private CommunityAdapter.OnItemClickListener itemClickListener;
    public ItemPostRecyclerBinding itemPostRecyclerBinding;
    private FirebaseFirestore db = FirebaseFirestore.getInstance();

    public PostHolder(@NonNull @NotNull ItemPostRecyclerBinding itemPostRecyclerBinding, CommunityAdapter.OnItemClickListener itemClick) {
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
        itemPostRecyclerBinding.postTitle.setText(post.getTitle());
        itemPostRecyclerBinding.postContent.setText(post.getContent());
        itemPostRecyclerBinding.postWriter.setText(post.getWriterNickname());
    }
}
