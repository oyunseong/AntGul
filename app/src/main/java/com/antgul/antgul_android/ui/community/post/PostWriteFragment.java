package com.antgul.antgul_android.ui.community.post;

import static com.antgul.antgul_android.base.ApplicationClass.POSTS_COLLECTION;
import static com.antgul.antgul_android.base.ApplicationClass.USERS_COLLECTION;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentManager;

import com.antgul.antgul_android.R;
import com.antgul.antgul_android.base.BaseFragment;
import com.antgul.antgul_android.databinding.FragmentWriteBoardBinding;
import com.antgul.antgul_android.model.Post;
import com.antgul.antgul_android.model.User;
import com.antgul.antgul_android.ui.start.login.LoginFragment;
import com.antgul.antgul_android.util.TimeStamp;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class PostWriteFragment extends BaseFragment<FragmentWriteBoardBinding> {
    @Override
    protected FragmentWriteBoardBinding getViewBinding(@NonNull @NotNull LayoutInflater inflater, @Nullable ViewGroup container) {
        return FragmentWriteBoardBinding.inflate(inflater, container, false);
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initClickListener() {
        binding.writePostButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String title = binding.title.getText().toString();
                String content = binding.content.getText().toString();
                String hashTags = binding.title.getText().toString();

                if (currentUser != null) {
                    setPost(title, content, hashTags);
                } else {
                    mainActivity.addFragment(R.id.activity_main_container, new LoginFragment());
                }
            }
        });

        binding.cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();
                fragmentManager.beginTransaction().remove(PostWriteFragment.this).commit();
                fragmentManager.popBackStack();
            }
        });
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    private void setPost(String title, String content, String hashTags) {
        db.collection(USERS_COLLECTION)
                .document(currentUser.getUid())
                .get()
                .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull @NotNull Task<DocumentSnapshot> task) {
                        DocumentSnapshot document = task.getResult();
                        if (document != null && document.exists()) {
                            Log.d(TAG, "DocumentSnapshot data: " + document.getData());
                            User user = document.toObject(User.class);
                            if (user != null) {
                                TimeStamp timeStamp = new TimeStamp();
                                String time = timeStamp.getTime();
                                Post post = new Post();
                                post.setWriterId(currentUser.getUid());
                                post.setWriterNickname(user.getNickname());
                                post.setTitle(title);
                                post.setContent(content);
                                post.setCategory(1);
                                post.setCreateAt(time);
                                post.setHashTags(hashTags);
                                postUpLoad(post);
                            }
                        } else {
                            Log.d(TAG, "get failed with ", task.getException());
                        }
                    }
                });
    }

    private void postUpLoad(Post post) {
        DocumentReference usersReference = db.collection(POSTS_COLLECTION).document();
        usersReference
                .set(post)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Log.i(TAG, "DocumentSnapshot successfully updated!");
                        showToast("업로드 완료");
                        FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();
                        fragmentManager.beginTransaction().remove(PostWriteFragment.this).commit();
                        fragmentManager.popBackStack();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w(TAG, "Error updating document", e);
                        showToast("업로드 실패");
                        FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();
                        fragmentManager.beginTransaction().remove(PostWriteFragment.this).commit();
                        fragmentManager.popBackStack();
                    }
                });
        progressDialog.hideProgress();
    }
}
