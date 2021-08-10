package com.antgul.antgul_android.ui.community;

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
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;

import org.jetbrains.annotations.NotNull;

public class WriteBoardFragment extends BaseFragment<FragmentWriteBoardBinding> {
    @Override
    protected FragmentWriteBoardBinding getViewBinding(@NonNull @NotNull LayoutInflater inflater, @Nullable ViewGroup container) {
        return FragmentWriteBoardBinding.inflate(inflater,container,false);
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initClickListener() {
        String text = binding.title.getText().toString();
        FirebaseUser firebaseUser;
        binding.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseUser post = mAuth.getCurrentUser();
                    postTest(post, text);

            }
        });

    }
    private void postTest(FirebaseUser firebaseUser, String title) {
        // 폴더(Collection) - 파일...(Document) - 내용(key-value...)
        // boards - docID 자동생성 - 게시물 커스텀 객체
        Post post = new Post();
        post.setTitle(title);
//        post.setPostId(firebaseUser.getUid());

        DocumentReference usersReference = db.collection("Post").document();
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
