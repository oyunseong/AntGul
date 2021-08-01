package com.antgul.antgul_android.ui.board;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.antgul.antgul_android.base.BaseFragment;
import com.antgul.antgul_android.model.Board;
import com.antgul.antgul_android.databinding.FragmentFreeBoardBinding;

import java.util.ArrayList;

public class FreeBoardFragment extends BaseFragment<FragmentFreeBoardBinding> {
    private RecyclerViewBoardAdapter mAdapter;
    ArrayList<Board> mData;
    RecyclerView.LayoutManager layoutManager;


    @Override
    protected FragmentFreeBoardBinding getViewBinding(@NonNull LayoutInflater inflater, @Nullable ViewGroup container) {
        return FragmentFreeBoardBinding.inflate(inflater,container,false);
    }

    @Override
    protected void setUpView() {
        mData = new ArrayList<>();
        mAdapter = new RecyclerViewBoardAdapter(mData);
        layoutManager = new LinearLayoutManager(getLayoutInflater().getContext());
        binding.freeBoardRecycler.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.freeBoardRecycler.setAdapter(mAdapter);

        recyclerClick();
        tempDataCreate();

        mAdapter.notifyDataSetChanged();
    }

    private void recyclerClick(){
        mAdapter.setOnItemClickListener((v, pos) -> showToast(pos+"번 클릭"));
    }

    private void tempDataCreate(){
        for (int i = 0; i < 30; i++) {
            addItem("item"+i,"2분 전","게시글 내용 입니다.");
        }
    }

    public void addItem(String nickName, String time, String content)
    {
        Board board = new Board(nickName,time,content);
        board.setNickName(nickName);
        board.setTime(time);
        board.setContent(content);

        mAdapter.addItem(board);

    }
}
