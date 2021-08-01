package com.antgul.antgul_android.base;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewbinding.ViewBinding;

import com.antgul.antgul_android.MainActivity;

import org.jetbrains.annotations.NotNull;

public abstract class BaseFragment<VB extends ViewBinding> extends Fragment {

    protected final String TAG = this.getClass().getSimpleName();

    protected VB binding;
    protected abstract VB getViewBinding(@NonNull LayoutInflater inflater, @Nullable ViewGroup container);
    protected abstract void initView();
    protected abstract void initClickListener();

    protected MainActivity mainActivity;

    @Override
    public void onAttach(@NonNull @NotNull Context context) {
        super.onAttach(context);
        mainActivity = (MainActivity) getActivity();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.i(TAG, "++onCreateView"); //이런 식으로 모든 생명 주기에, 태그 달기

        binding = getViewBinding(inflater, container);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Log.i(TAG, "++onViewCreated"); //이런 식으로 모든 생명 주기에, 태그 달기
        initView();
        initClickListener();
    }

    public void showToast(String message) {
        Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.i(TAG, "++onDestroyView");
        binding = null;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mainActivity = null;
    }
}
