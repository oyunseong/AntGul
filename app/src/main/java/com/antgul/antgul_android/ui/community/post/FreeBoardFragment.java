package com.antgul.antgul_android.ui.community.post;

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

import com.antgul.antgul_android.R;
import com.antgul.antgul_android.base.BaseFragment;
import com.antgul.antgul_android.databinding.FragmentFreeBoardBinding;
import com.antgul.antgul_android.model.Post;
import com.antgul.antgul_android.model.PostCase;
import com.antgul.antgul_android.ui.community.CommunityFragment;
import com.antgul.antgul_android.ui.community.recyclerView.RecyclerCommunityAdapter;
import com.antgul.antgul_android.util.RecyclerDecorationHeight;
import com.google.firebase.firestore.QueryDocumentSnapshot;


import java.util.ArrayList;

import static com.antgul.antgul_android.base.ApplicationClass.POSTS_COLLECTION;

public class FreeBoardFragment extends BaseFragment<FragmentFreeBoardBinding> {
    private RecyclerCommunityAdapter mAdapter;
    private ArrayList<Post> postList;
    private RecyclerView.LayoutManager layoutManager;
    private DetailBoardFragment detailBoardFragment;
    private CommunityFragment communityFragment;

    @Override
    protected FragmentFreeBoardBinding getViewBinding(@NonNull LayoutInflater inflater, @Nullable ViewGroup container) {
        return FragmentFreeBoardBinding.inflate(inflater, container, false);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        detailBoardFragment = new DetailBoardFragment();
        communityFragment = new CommunityFragment();
    }

    @Override
    protected void initView() {
        postList = new ArrayList<>();
        mAdapter = new RecyclerCommunityAdapter(postList, PostCase.STOCK_INFO);
        layoutManager = new LinearLayoutManager(getLayoutInflater().getContext());
        binding.freeBoardRecycler.setLayoutManager(layoutManager);
        binding.freeBoardRecycler.addItemDecoration(new RecyclerDecorationHeight(3));
        binding.freeBoardRecycler.setAdapter(mAdapter);
        getPosts();
        mAdapter.notifyDataSetChanged();
    }

    @Override
    protected void initClickListener() {
        mAdapter.setOnItemClickListener(new RecyclerCommunityAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View v, int pos) {
                showToast(pos+"자유게시판 클릭");
                // TODO DetailBoardFragment 이동 후 뒤로가기 버튼을 눌렀을 때 백스택에 제대로 안쌓이는 현상
                Bundle bundle = new Bundle();
                bundle.putString("docId", postList.get(pos).getDocumentId());
                FragmentTransaction transaction = requireActivity().getSupportFragmentManager().beginTransaction();
                DetailBoardFragment detailBoardFragment = new DetailBoardFragment();
                detailBoardFragment.setArguments(bundle);
                transaction.replace(R.id.fragment_main_frame, detailBoardFragment);
                transaction.addToBackStack(null).commit();
            }
        });
        binding.writeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mainActivity.replaceDetailFragment(new WritePostFragment());
            }
        });
    }

    private void getPosts() {
        Log.i(TAG, "getPost");
        progressDialog.showProgress();
        db.collection(POSTS_COLLECTION)
                .whereEqualTo("category", 1)
//                .orderBy("createAt", Query.Direction.DESCENDING)
                .get()
                .addOnCompleteListener(task -> {
                    Log.i(TAG, "++onComplete");
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
                    }
                    progressDialog.hideProgress();
                });
    }

}
