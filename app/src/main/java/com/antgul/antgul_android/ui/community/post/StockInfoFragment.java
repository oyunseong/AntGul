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
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.antgul.antgul_android.R;
import com.antgul.antgul_android.base.BaseFragment;
import com.antgul.antgul_android.databinding.FragmentPostBinding;
import com.antgul.antgul_android.model.Post;
import com.antgul.antgul_android.model.PostCase;
import com.antgul.antgul_android.ui.community.recyclerView.CommunityAdapter;
import com.antgul.antgul_android.util.RecyclerDecorationHeight;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

import static com.antgul.antgul_android.base.ApplicationClass.POSTS_COLLECTION;

// TODO 뉴스를 올릴 예정인데 왜 종목정보인지 ??
// 자유게시판이랑 뷰가 같아서 합쳐도되는데 일단 디자인이 바뀔수도있으니까 나중에 수정
public class StockInfoFragment extends BaseFragment<FragmentPostBinding> {
    private CommunityAdapter mAdapter;
    private ArrayList<Post> postList;

    @Override
    protected FragmentPostBinding getViewBinding(@NonNull LayoutInflater inflater, @Nullable ViewGroup container) {
        return FragmentPostBinding.inflate(inflater, container, false);
    }

    @Override
    protected void initView() {
        postList = new ArrayList<>();
        mAdapter = new CommunityAdapter(postList, PostCase.STOCK_INFO);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getLayoutInflater().getContext());
        binding.postRecycler.setLayoutManager(layoutManager);
        binding.postRecycler.addItemDecoration(new RecyclerDecorationHeight(3));
        binding.postRecycler.setAdapter(mAdapter);
        getPosts();
        mAdapter.notifyDataSetChanged();
        swipeRefresh();
    }

    @Override
    protected void initClickListener() {
        mAdapter.setOnItemClickListener(new CommunityAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View v, int pos) {
                showToast(pos + "종목정보 클릭");
                // TODO 만든 프래그먼트 전환 메서드로 바꾸기
                Bundle bundle = new Bundle();
                bundle.putString("docId", postList.get(pos).getDocumentId());
                FragmentTransaction transaction = requireActivity().getSupportFragmentManager().beginTransaction();
                PostDetailFragment postDetailFragment = new PostDetailFragment();
                postDetailFragment.setArguments(bundle);
                transaction.replace(R.id.main_fragment_container, postDetailFragment);
                transaction.addToBackStack(null).commit();
            }
        });
    }

    private void getPosts() {
        Log.i(TAG, "getPost");
//        progressDialog.showProgress();
        db.collection(POSTS_COLLECTION)
                .whereEqualTo("category", 0)
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
                        }
                        progressDialog.hideProgress();
                    }
                });
    }

    private void swipeRefresh() {
        binding.postRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                postList.clear();
                getPosts();
                showToast("새로고침 완료");
                binding.postRefreshLayout.setRefreshing(false);
            }
        });
    }
}

