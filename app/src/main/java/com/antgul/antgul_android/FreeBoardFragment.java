package com.antgul.antgul_android;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.antgul.antgul_android.base.BaseFragment;
import com.antgul.antgul_android.data.Board;
import com.antgul.antgul_android.databinding.FragmentFreeBoardBinding;
import com.antgul.antgul_android.ui.board.RecyclerViewBoardAdapter;

import java.util.ArrayList;

public class FreeBoardFragment extends BaseFragment<FragmentFreeBoardBinding> {
    private RecyclerViewBoardAdapter recyclerViewBoardAdapter;
    ArrayList<Board> mData;
    RecyclerView.LayoutManager layoutManager;


    @Override
    protected FragmentFreeBoardBinding getViewBinding(@NonNull LayoutInflater inflater, @Nullable ViewGroup container) {
        return FragmentFreeBoardBinding.inflate(inflater,container,false);
    }

    @Override
    protected void setUpView() {
        mData = new ArrayList<>();
        recyclerViewBoardAdapter = new RecyclerViewBoardAdapter(mData);
        layoutManager = new LinearLayoutManager(getLayoutInflater().getContext());
        binding.freeBoardRecycler.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.freeBoardRecycler.setAdapter(recyclerViewBoardAdapter);

        for (int i = 0; i < 30; i++) {
            addItem("item"+i,"2분 전","게시글 내용 입니다.");
        }
        recyclerViewBoardAdapter.notifyDataSetChanged();
    }
    public void addItem(String nickName, String time, String content)
    {
        Board board = new Board(nickName,time,content);
        board.setNickName(nickName);
        board.setTime(time);
        board.setContent(content);

        recyclerViewBoardAdapter.addItem(board);

    }
}
