package com.antgul.antgul_android.ui.community.post;

import static com.antgul.antgul_android.base.ApplicationClass.POSTS_COLLECTION;
import static com.antgul.antgul_android.base.ApplicationClass.USERS_COLLECTION;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.antgul.antgul_android.base.BaseFragment;
import com.antgul.antgul_android.databinding.FragmentDetailBoardBinding;
import com.antgul.antgul_android.model.Post;
import com.antgul.antgul_android.model.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;

import org.jetbrains.annotations.NotNull;

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

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            documentId = getArguments().getString("docId");
        }
    }

    @Override
    protected void initView() {

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getDetailPost();
    }

    @Override
    protected void initClickListener() {
//        binding.boardLikeButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//            }
//        });
    }

    public void getDetailPost() {
        Log.i(TAG, "getPost");
        progressDialog.showProgress();
        db.collection(POSTS_COLLECTION)
                .document(documentId)
                .get()
                .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull @NotNull Task<DocumentSnapshot> task) {
                        DocumentSnapshot document = task.getResult();
                        if (document != null && document.exists()) {
                            Log.d(TAG, "DocumentSnapshot data: " + document.getData());
                            Post post = document.toObject(Post.class);
                            if (post != null) getWriter(post);
                        } else {
                            Log.d(TAG, "get failed with ", task.getException());
                        }
                    }
                });
    }

    public void getWriter(Post post) {
        db.collection(USERS_COLLECTION)
                .document(post.getWriterId())
                .get()
                .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull @NotNull Task<DocumentSnapshot> task) {
                        DocumentSnapshot document = task.getResult();
                        if (document != null && document.exists()) {
                            Log.d(TAG, "DocumentSnapshot data: " + document.getData());
                            User user = document.toObject(User.class);
                            if (user != null) {
//                                binding.boardNickName.setText(user.nickname);
//                                binding.boardContent.setText(post.getContent());
//                                binding.boardTime.setText(post.getCreateAt());
                                // TODO UI 업데이트 후 항목 추가
                            }
                        } else {
                            Log.d(TAG, "get failed with ", task.getException());
                        }
                        progressDialog.hideProgress();
                    }
                });
    }
}
