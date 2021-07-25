package com.antgul.antgul_android;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.antgul.antgul_android.databinding.FragmentBoardBinding;

public class BoardFragment extends Fragment {
    private MainActivity mainActivity;
    private FragmentBoardBinding binding;


    public BoardFragment(){

    }


    @Nullable
    @Override
    public View onCreateView(@NonNull  LayoutInflater inflater, @Nullable  ViewGroup container, @Nullable  Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_board,container,false);
    }
}
