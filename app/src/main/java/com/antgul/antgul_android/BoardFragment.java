package com.antgul.antgul_android;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.antgul.antgul_android.base.BaseFragment;
import com.antgul.antgul_android.databinding.FragmentBoardBinding;
import com.antgul.antgul_android.databinding.FragmentNotionBinding;

public class BoardFragment extends BaseFragment<FragmentBoardBinding> {
    private MainActivity mainActivity;
    private FragmentBoardBinding binding;

    // 액티비티위에 올라오는 순간 호출됩니다.
    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        activity = (MainActivity)getActivity();
        showToast("게시판 프래그먼트 호출");


    }

    @Override
    public void onDetach() {
        super.onDetach();

        activity
    }

    @Override
    protected FragmentBoardBinding getViewBinding(@NonNull LayoutInflater inflater, @Nullable ViewGroup container) {
        return null;
    }

    @Override
    protected void setUpView() {

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull  LayoutInflater inflater, @Nullable  ViewGroup container, @Nullable  Bundle savedInstanceState) {


        return inflater.inflate(R.layout.fragment_board,container,false);
    }
}
