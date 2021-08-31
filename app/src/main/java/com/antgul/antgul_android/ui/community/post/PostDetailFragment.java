package com.antgul.antgul_android.ui.community.post;

import static com.antgul.antgul_android.base.ApplicationClass.COMMENT_COLLECTION;
import static com.antgul.antgul_android.base.ApplicationClass.POSTS_COLLECTION;
import static com.antgul.antgul_android.base.ApplicationClass.USERS_COLLECTION;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.antgul.antgul_android.R;
import com.antgul.antgul_android.base.BaseFragment;
import com.antgul.antgul_android.databinding.FragmentDetailBoardBinding;
import com.antgul.antgul_android.model.Comment;
import com.antgul.antgul_android.model.Post;
import com.antgul.antgul_android.model.User;
import com.antgul.antgul_android.ui.community.recyclerView.CommentAdapter;
import com.antgul.antgul_android.ui.start.login.LoginFragment;
import com.antgul.antgul_android.util.TimeStamp;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.QueryDocumentSnapshot;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class PostDetailFragment extends BaseFragment<FragmentDetailBoardBinding> {
    private String documentId;
    private ArrayList<Comment> commentList;
    private CommentAdapter commentAdapter = null;
    PostFragment postFragment;

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
    public void onDestroyView() {
        super.onDestroyView();

    }

    @Override
    protected void initView() {
        commentList = new ArrayList<>();
        commentAdapter = new CommentAdapter(commentList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getLayoutInflater().getContext());
        binding.detailRecycler.setLayoutManager(layoutManager);
        binding.detailRecycler.setAdapter(commentAdapter);
        getComment();
        commentAdapter.notifyDataSetChanged();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getDetailPost();
    }

    @Override
    protected void initClickListener() {
        binding.detailCancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO remove 애니메이션 추가
                FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();
                fragmentManager.beginTransaction().remove(PostDetailFragment.this).commit();
                fragmentManager.popBackStack();
            }
        });

        binding.detailLikeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO 추천 버튼 클릭시 반응 추가
                showToast("coming soon");

            }
        });
        binding.detailHateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO 비추천 버튼 클릭시 반응 추가
                showToast("coming soon");
            }
        });
        binding.detailDeclaration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO 신고 버튼 클릭시 반응 추가
                showToast("coming soon");
            }
        });
        binding.detailOkButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String comment =binding.detailEditComment.getText().toString();
                if (currentUser != null) {
                    showToast("입력버튼 클릭");
                    setComment(comment);
                } else {
                    mainActivity.addFragment(R.id.activity_main_container, new LoginFragment());
                }
            }
        });
    }

    private void setComment(String inputComment) {
        progressDialog.showProgress();
        db.collection(USERS_COLLECTION)
                .document(currentUser.getUid())
                .get()
                .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull @NotNull Task<DocumentSnapshot> task) {
                        Log.i(TAG, "++onComplete setComment");
                        DocumentSnapshot document = task.getResult();
                        if (document != null && document.exists()) {
                            Log.i(TAG, "++document.exists");
                            User user = document.toObject(User.class);
                            if (user != null) {
                                Log.i(TAG, "++user is not null");
                                TimeStamp timeStamp = new TimeStamp();
                                String time = timeStamp.getTime();
                                Comment comment = new Comment();
                                comment.setWriterId(currentUser.getUid());
                                comment.setWriterNickname(user.getNickname());
                                comment.setComment(inputComment);
                                comment.setCreateAt(time);
                                comment.setPostUid(documentId);
                                commentUpLoad(comment);
                            }
                        } else {
                            Log.i(TAG, "get failed with "+ task.getException());
                        }
                    }
                });
    }
    private void commentUpLoad(Comment comment) {
        DocumentReference usersReference = db.collection(COMMENT_COLLECTION).document();
        usersReference
                .set(comment)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Log.i(TAG, "DocumentSnapshot successfully updated!");
                        showToast("업로드 완료");
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w(TAG, "Error updating document", e);
                        showToast("업로드 실패");
                    }
                });
        progressDialog.hideProgress();
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
                            if (post != null) {
                                getWriter(post);
                            }
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
//                                binding.detailProfileImage.setImageDrawable(user.getProfileImage());
                                binding.detailNickname.setText(user.getNickname());
                                binding.detailTime.setText(post.getCreateAt());
                                binding.detailTitle.setText(post.getTitle());
                                binding.detailContent.setText(post.getContent());

//                                binding.detailLikeButton.setText(post.getLikeCount());
//                                binding.detailHateButton.setText(post.getHateCount());
                                // TODO UI 업데이트 후 항목 추가
                            }
                        } else {
                            Log.d(TAG, "get failed with ", task.getException());
                        }
                        progressDialog.hideProgress();
                    }
                });
    }

    private void getComment() {
        Log.i(TAG, "getPost");
        db.collection(COMMENT_COLLECTION)
                .whereEqualTo("postUid",documentId)
                .get()
                .addOnCompleteListener(task -> {
                    Log.i(TAG, "onComplete");
                    if (task.isSuccessful()) {
                        for (QueryDocumentSnapshot document : task.getResult()) {
                            Log.d(TAG, document.getId() + " => " + document.getData());
                            Comment comment = document.toObject(Comment.class);
                            commentList.add(comment);
                        }
                        commentAdapter.notifyDataSetChanged();
                    } else {
                        Log.d(TAG, "Error getting documents: ", task.getException());
                    }
                });
    }
}
