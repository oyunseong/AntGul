package com.antgul.antgul_android.ui.community.post;

import android.os.Bundle;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.antgul.antgul_android.R;
import com.antgul.antgul_android.base.BaseFragment;
import com.antgul.antgul_android.databinding.FragmentFreeBoardBinding;
import com.antgul.antgul_android.model.Post;
import com.antgul.antgul_android.model.PostCase;
import com.antgul.antgul_android.ui.community.recyclerView.CommunityAdapter;
import com.antgul.antgul_android.ui.start.login.LoginFragment;
import com.antgul.antgul_android.util.RecyclerDecorationHeight;
import com.google.firebase.firestore.QueryDocumentSnapshot;

import java.util.ArrayList;

import static com.antgul.antgul_android.base.ApplicationClass.POSTS_COLLECTION;

public class PostFragment extends BaseFragment<FragmentFreeBoardBinding> {
    private CommunityAdapter mAdapter;
    private ArrayList<Post> postList;

    @Override
    protected FragmentFreeBoardBinding getViewBinding(@NonNull LayoutInflater inflater, @Nullable ViewGroup container) {
        return FragmentFreeBoardBinding.inflate(inflater, container, false);
    }

    @Override
    protected void initView() {
        postList = new ArrayList<>();
        mAdapter = new CommunityAdapter(postList, PostCase.STOCK_INFO);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getLayoutInflater().getContext());
        binding.freeBoardRecycler.setLayoutManager(layoutManager);
        binding.freeBoardRecycler.addItemDecoration(new RecyclerDecorationHeight(3));
        binding.freeBoardRecycler.setAdapter(mAdapter);
        getPosts();
        mAdapter.notifyDataSetChanged();
    }

    @Override
    protected void initClickListener() {
        // TODO 뒤로가기 했을 때 커뮤니티 프래그먼트가 제대로 안띄워짐
        mAdapter.setOnItemClickListener(new CommunityAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View v, int pos) {
                Bundle bundle = new Bundle();
                bundle.putString("docId", postList.get(pos).getDocumentId());
                FragmentTransaction transaction = requireActivity().getSupportFragmentManager().beginTransaction();
                PostDetailFragment postDetailFragment = new PostDetailFragment();
                transaction.setCustomAnimations(R.anim.fade_in,R.anim.fade_out,R.anim.fade_in,R.anim.fade_out);
                transaction.add(R.id.activity_main_container, postDetailFragment);
                transaction.addToBackStack(null).commit();
                postDetailFragment.setArguments(bundle);
            }
        });
        binding.writeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (currentUser != null) {
                    mainActivity.addFragment(R.id.activity_main_container, new PostWriteFragment());
                }else{
                    //TODO "로그인하시겠습니까?" 라고 출력하는 커스텀 다이얼로그 제작 필요
                    showToast("로그인이 필요한 기능입니다.");
                    mainActivity.addFragment(R.id.activity_main_container,new LoginFragment());
                }
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
                    Log.i(TAG, "onComplete");
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
