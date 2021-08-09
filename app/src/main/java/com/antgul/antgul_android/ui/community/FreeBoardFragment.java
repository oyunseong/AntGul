package com.antgul.antgul_android.ui.community;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.antgul.antgul_android.base.BaseFragment;
import com.antgul.antgul_android.model.Community;
import com.antgul.antgul_android.databinding.FragmentFreeBoardBinding;

import java.util.ArrayList;

public class FreeBoardFragment extends BaseFragment<FragmentFreeBoardBinding> {

    private RecyclerViewCommunityAdapter mAdapter;
    private ArrayList<Community> communityList;
    private RecyclerView.LayoutManager layoutManager;

    @Override
    protected FragmentFreeBoardBinding getViewBinding(@NonNull LayoutInflater inflater, @Nullable ViewGroup container) {
        return FragmentFreeBoardBinding.inflate(inflater, container, false);
    }

    @Override
    protected void initView() {
        communityList = new ArrayList<>();
        mAdapter = new RecyclerViewCommunityAdapter(communityList);
        layoutManager = new LinearLayoutManager(getLayoutInflater().getContext());
        binding.freeBoardRecycler.setLayoutManager(layoutManager);
        binding.freeBoardRecycler.setAdapter(mAdapter);

        for (int i = 0; i < 30; i++)    {
            addItem("item" + i, "2분 전", "게시글 내용 입니다.");
        }
        mAdapter.notifyDataSetChanged();
    }

    @Override
    protected void initClickListener() {
        mAdapter.setOnItemClickListener(new RecyclerViewCommunityAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View v, int pos) {
                showToast(pos+"번 클릭");
                //TODO 포지션값 넘겨주기 + replaceFragment() 로 교체. 프레그먼트 데이터 전달 및 받기 검색. 객체를 넘길거면 추가 구현 필요. Parcelable.
                //mainActivity.callFragment(MainActivity.FRAGMENT_DETAIL_BOARD);
            }
        });
    }

    public void addItem(String nickName, String time, String content) {
        Community community = new Community(nickName, time, content);
        community.setTitle(nickName);
        community.setTime(time);
        community.setContent(content);
        mAdapter.addItem(community);
    }

}
