package com.antgul.antgul_android.ui.community;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.antgul.antgul_android.base.BaseFragment;
import com.antgul.antgul_android.databinding.FragmentDetailBoardBinding;
import com.antgul.antgul_android.model.Comment;
import com.antgul.antgul_android.model.Post;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DetailBoardFragment extends BaseFragment<FragmentDetailBoardBinding> {
    private String documentId;
    private String nickname;
    private String content;
    private String writerId; //User - uid
    private String title;
    private int likeCount = 0;
    private String createAt;


    @Override
    protected FragmentDetailBoardBinding getViewBinding(@NonNull @NotNull LayoutInflater inflater, @Nullable ViewGroup container) {
        return FragmentDetailBoardBinding.inflate(inflater, container, false);
    }

    @Nullable
    @org.jetbrains.annotations.Nullable
    @Override
    public View onCreateView(@NonNull @NotNull LayoutInflater inflater, @Nullable @org.jetbrains.annotations.Nullable ViewGroup container, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        if (getArguments() != null) {
            documentId = getArguments().getString("docId");
            getDetailPost();
        }

        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initClickListener() {
        binding.boardLikeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.boardNickName.setText(documentId);
                binding.boardContent.setText(content);
            }
        });
    }

    public void getDetailPost() {
        Log.i(TAG, "getPost");
//        progressDialog.showProgress();
        db.collection("boards")
                .document(documentId)
                .get()
                .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull @NotNull Task<DocumentSnapshot> task) {
                        DocumentSnapshot document = task.getResult();
                        if (document.exists()) {
                            Log.d(TAG, "DocumentSnapshot data: " + document.getData());
                            Post post = document.toObject(Post.class);
                            content = post.getContent();
                            createAt = post.getCreateAt();
                            title = post.getTitle();
                            showToast(content);
                        } else {
                            Log.d(TAG, "get failed with ", task.getException());
                        }
//                        progressDialog.hideProgress();
                    }
                });
    }
}
