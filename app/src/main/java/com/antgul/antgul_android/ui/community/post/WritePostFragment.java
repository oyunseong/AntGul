package com.antgul.antgul_android.ui.community.post;

import static com.antgul.antgul_android.base.ApplicationClass.POSTS_COLLECTION;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.antgul.antgul_android.base.BaseFragment;
import com.antgul.antgul_android.databinding.FragmentWriteBoardBinding;
import com.antgul.antgul_android.model.Post;
import com.antgul.antgul_android.util.TimeStamp;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;

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

    private void onClickCancelButton() {
        binding.cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mainActivity.visibleMainFrame();
    }

    private void writePost() {
        String title = binding.title.getText().toString();
        String content = binding.content.getText().toString();
        //비회원인 경우, 글쓰기 불가능
        if (currentUser != null) {
            setPost(title, content);
        } else {
            //로그인 화면으로 유도.
        }
    }

    private void setPost(String title, String content) {
        // 폴더(Collection) - 파일...(Document) - 내용(key-value...)
        // boards - docID 자동생성 - 게시물 커스텀 객체
        TimeStamp timeStamp = new TimeStamp();
        String time = timeStamp.getTime();
        Post post = new Post();
        post.setWriterId(currentUser.getUid());
        post.setTitle(title);
        post.setContent(content);
        post.setCategory(1);
        post.setCreateAt(time);

        DocumentReference usersReference = db.collection(POSTS_COLLECTION).document(currentUser.getUid());
        usersReference
                .set(post)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Log.i(TAG, "DocumentSnapshot successfully updated!");
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
