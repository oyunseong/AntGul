package com.antgul.antgul_android;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.antgul.antgul_android.base.BaseFragment;
import com.antgul.antgul_android.databinding.FragmentBoardBinding;

public class BoardFragment extends BaseFragment<FragmentBoardBinding> {
    MainActivity mainActivity;

    // 프래그먼트가 액티비티에 올라는 순간 호출됩니다.
    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        mainActivity = (MainActivity)getActivity();
    }

    //
    @Override
    public void onDetach() {
        super.onDetach();

        mainActivity = null;
    }

    @Override
    protected FragmentBoardBinding getViewBinding(@NonNull LayoutInflater inflater, @Nullable ViewGroup container) {
        return FragmentBoardBinding.inflate(inflater, container, false);
    }

    @Override
    protected void setUpView() {
        //init code..
        binding.textBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              //  showToast("클릭");
                // 관심종목설정 호출
                mainActivity.callFragment(4);
            }
        });
    }
}
