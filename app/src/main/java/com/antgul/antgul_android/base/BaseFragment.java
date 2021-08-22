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
import com.antgul.antgul_android.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

import org.jetbrains.annotations.NotNull;

//TODO 생명주기 로그달고, 각 콜백이 언제 왜! 호출되는지 확인하기.
public abstract class BaseFragment<VB extends ViewBinding> extends Fragment {
    protected final String TAG = this.getClass().getSimpleName();
    protected VB binding;
    protected MainActivity mainActivity;
    public ProgressDialog progressDialog;
    protected FirebaseAuth mAuth;
    protected FirebaseUser currentUser;
    protected FirebaseFirestore db = FirebaseFirestore.getInstance();

    protected abstract VB getViewBinding(@NonNull LayoutInflater inflater, @Nullable ViewGroup container);

    protected abstract void initView();

    protected abstract void initClickListener();

    @Override
    public void onAttach(@NonNull @NotNull Context context) {
        super.onAttach(context);
        Log.i(TAG, "++onAttach");
        mainActivity = (MainActivity) getActivity();
    }

    @Override
    public void onCreate(@Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(TAG, "++onCreate");
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.i(TAG, "++onCreateView");
        binding = getViewBinding(inflater, container);
        mAuth = FirebaseAuth.getInstance();
        currentUser = mAuth.getCurrentUser();
        progressDialog = new ProgressDialog(requireContext());
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
        Log.i(TAG, "++onDetach");
        mainActivity = null;
    }

    public void replaceFragment(Fragment fragment) {
        Log.i(TAG, "++replaceFragment");
        requireActivity().getSupportFragmentManager().beginTransaction()
                .setCustomAnimations(R.anim.fade_in, R.anim.fade_out, R.anim.fade_in, R.anim.fade_out)
                .replace(R.id.main_activity_frame, fragment)
                .commit();
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.i(TAG, "++onStart");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.i(TAG, "++onResume");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.i(TAG, "++onPause");
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.i(TAG, "++onStop()");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i(TAG, "++onDestroy");
        binding = null;
    }
}
