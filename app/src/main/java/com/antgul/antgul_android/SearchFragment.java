package com.antgul.antgul_android;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.antgul.antgul_android.base.BaseFragment;
import com.antgul.antgul_android.databinding.FragmentSearchBinding;
import com.antgul.antgul_android.model.Post;
import com.antgul.antgul_android.model.PostCase;
import com.antgul.antgul_android.ui.community.post.PostDetailFragment;
import com.antgul.antgul_android.ui.community.recyclerView.CommunityAdapter;
import com.antgul.antgul_android.util.RecyclerDecorationHeight;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

import static com.antgul.antgul_android.base.ApplicationClass.POSTS_COLLECTION;

public class SearchFragment extends BaseFragment<FragmentSearchBinding> {
    private CommunityAdapter mAdapter;
    private ArrayList<Post> postList;
    private RecyclerView.LayoutManager layoutManager;

    @Override
    protected FragmentSearchBinding getViewBinding(@NonNull @NotNull LayoutInflater inflater, @Nullable @org.jetbrains.annotations.Nullable ViewGroup container) {
        return FragmentSearchBinding.inflate(inflater,container,false);
    }

    @Override
    protected void initView() {
        postList = new ArrayList<>();
        mAdapter = new CommunityAdapter(postList, PostCase.POST_SEARCH);
        layoutManager = new LinearLayoutManager(getLayoutInflater().getContext());
        binding.searchRecycler.setLayoutManager(layoutManager);
        binding.searchRecycler.addItemDecoration(new RecyclerDecorationHeight(3));
        binding.searchRecycler.setAdapter(mAdapter);
        getPosts("null");
        mAdapter.notifyDataSetChanged();
    }

    @Override
    protected void initClickListener() {
        binding.searchOkButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //TODO 해쉬태그를 입력했을 경우 파이어베이스에서 해쉬태그와 일치하는 Post들을 리사이클러뷰에 보여준다.
                String hashTag = binding.searchEt.getText().toString();
                getPosts(hashTag);
            }
        });
        mAdapter.setOnItemClickListener(new CommunityAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View v, int pos) {
                Bundle bundle = new Bundle();
                bundle.putString("docId", postList.get(pos).getDocumentId());
                FragmentTransaction transaction = requireActivity().getSupportFragmentManager().beginTransaction();
                PostDetailFragment postDetailFragment = new PostDetailFragment();
                postDetailFragment.setArguments(bundle);
                transaction.replace(R.id.activity_main_container, postDetailFragment);
                transaction.addToBackStack(null).commit();
            }
        });

    }

    private void getPosts(String hashTags) {
        Log.i(TAG, "getPost");
        progressDialog.showProgress();
        db.collection(POSTS_COLLECTION)
                .whereEqualTo("hashTags", hashTags)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Log.d(TAG, document.getId() + " => " + document.getData());
                                Post post = document.toObject(Post.class);
                                post.setDocumentId(document.getId());
                                postList.add(post);
                            }
                            mAdapter.notifyDataSetChanged();
                        } else {
                            Log.d(TAG, "Error getting documents: ", task.getException());
                            showToast("검색 실패");
                        }
                        progressDialog.hideProgress();
                    }
                });
    }
}
