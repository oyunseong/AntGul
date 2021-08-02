package com.antgul.antgul_android.ui.board;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.antgul.antgul_android.base.BaseFragment;
import com.antgul.antgul_android.model.Board;
import com.antgul.antgul_android.databinding.FragmentFreeBoardBinding;
import com.antgul.antgul_android.ui.board.RecyclerViewBoardAdapter;

import java.util.ArrayList;

public class FreeBoardFragment extends BaseFragment<FragmentFreeBoardBinding> {

    private RecyclerViewBoardAdapter recyclerViewBoardAdapter;
    private ArrayList<Board> mData;
    private RecyclerView.LayoutManager layoutManager;

    @Override
    protected FragmentFreeBoardBinding getViewBinding(@NonNull LayoutInflater inflater, @Nullable ViewGroup container) {
        return FragmentFreeBoardBinding.inflate(inflater, container, false);
    }

    @Override
    protected void initView() {
        mData = new ArrayList<>();
        recyclerViewBoardAdapter = new RecyclerViewBoardAdapter(mData);
        layoutManager = new LinearLayoutManager(getLayoutInflater().getContext());
        binding.freeBoardRecycler.setLayoutManager(layoutManager);
        binding.freeBoardRecycler.setAdapter(recyclerViewBoardAdapter);

        for (int i = 0; i < 30; i++) {
            addItem("item" + i, "2분 전", "게시글 내용 입니다.");
        }
        recyclerViewBoardAdapter.notifyDataSetChanged();
    }

    @Override
    protected void initClickListener() {

    }

    public void addItem(String nickName, String time, String content) {
        Board board = new Board(nickName, time, content);
        board.setNickName(nickName);
        board.setTime(time);
        board.setContent(content);

        recyclerViewBoardAdapter.addItem(board);

    }
}
