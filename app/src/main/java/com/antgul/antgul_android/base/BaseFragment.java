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

//TODO 생명주기 로그달고, 각 콜백이 언제 왜! 호출되는지 확인하기.
public abstract class BaseFragment<VB extends ViewBinding> extends Fragment {

    protected final String TAG = this.getClass().getSimpleName();
    protected VB binding;
    protected MainActivity mainActivity;

    protected abstract VB getViewBinding(@NonNull LayoutInflater inflater, @Nullable ViewGroup container);
    protected abstract void initView();
    protected abstract void initClickListener();

    @Override
    public void onAttach(@NonNull @NotNull Context context) {
        super.onAttach(context);
        Log.i(TAG,"onAttach");
        mainActivity = (MainActivity) getActivity();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.i(TAG, "++onCreateView");
        binding = getViewBinding(inflater, container);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Log.i(TAG, "++onViewCreated");
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
        Log.i(TAG,"onDetach");
        mainActivity = null;
    }
}
