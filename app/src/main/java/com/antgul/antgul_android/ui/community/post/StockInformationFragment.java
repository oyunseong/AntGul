package com.antgul.antgul_android.ui.community.post;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.antgul.antgul_android.base.BaseFragment;
import com.antgul.antgul_android.databinding.FragmentStockInfoBinding;
import com.antgul.antgul_android.model.Post;
import com.antgul.antgul_android.model.PostCase;
import com.antgul.antgul_android.ui.community.recyclerView.RecyclerCommunityAdapter;
import com.antgul.antgul_android.util.RecyclerDecorationHeight;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

import static com.antgul.antgul_android.base.ApplicationClass.POSTS_COLLECTION;

public class StockInformationFragment extends BaseFragment<FragmentStockInfoBinding> {
    private RecyclerCommunityAdapter mAdapter;
    private ArrayList<Post> postList;
    private RecyclerView.LayoutManager layoutManager;

    @Override
    protected FragmentStockInfoBinding getViewBinding(@NonNull LayoutInflater inflater, @Nullable ViewGroup container) {
        return FragmentStockInfoBinding.inflate(inflater, container, false);
    }

    @Override
    protected void initView() {
        postList = new ArrayList<>();
        mAdapter = new RecyclerCommunityAdapter(postList, PostCase.STOCK_INFO);
        layoutManager = new LinearLayoutManager(getLayoutInflater().getContext());
        binding.recycler.setLayoutManager(layoutManager);
        binding.recycler.addItemDecoration(new RecyclerDecorationHeight(3));
        binding.recycler.setAdapter(mAdapter);
        getPosts();
        mAdapter.notifyDataSetChanged();
    }

    @Override
    protected void initClickListener() {
        onClickItem();
    }

    private void onClickItem() {
        mAdapter.setOnItemClickListener(new RecyclerCommunityAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View v, int pos) {
                showToast(pos + "번 클릭");
            }
        });
    }

    private void getPosts() {
        Log.i(TAG, "getPost");
        progressDialog.showProgress();
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
}
