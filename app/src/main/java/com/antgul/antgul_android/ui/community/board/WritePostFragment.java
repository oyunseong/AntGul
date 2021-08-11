package com.antgul.antgul_android.ui.community.board;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.antgul.antgul_android.base.BaseFragment;
import com.antgul.antgul_android.databinding.FragmentWriteBoardBinding;
import com.antgul.antgul_android.model.Post;
import com.antgul.antgul_android.model.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import org.jetbrains.annotations.NotNull;

public class WritePostFragment extends BaseFragment<FragmentWriteBoardBinding> {
    @Override
    protected FragmentWriteBoardBinding getViewBinding(@NonNull @NotNull LayoutInflater inflater, @Nullable ViewGroup container) {
        return FragmentWriteBoardBinding.inflate(inflater, container, false);
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initClickListener() {
        binding.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                writePost();
                showToast("버튼 클릭");
            }
        });

    }

    private void writePost() {
        String title = binding.title.getText().toString();
        setPost(title);
    }

    private void setPost(String title) {

        // 폴더(Collection) - 파일...(Document) - 내용(key-value...)
        // boards - docID 자동생성 - 게시물 커스텀 객체
        Post post = new Post();
        post.setTitle(title);
//        post.setWriterId(firebaseUser.getUid());

        DocumentReference usersReference = db.collection("boards").document();
        usersReference
                .set(post)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Log.d(TAG, "DocumentSnapshot successfully updated!");
                        progressDialog.hideProgress();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w(TAG, "Error updating document", e);
                        progressDialog.hideProgress();
                    }
                });
    }

}
