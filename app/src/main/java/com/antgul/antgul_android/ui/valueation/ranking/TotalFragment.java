package com.antgul.antgul_android.ui.valueation.ranking;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.antgul.antgul_android.base.BaseFragment;
import com.antgul.antgul_android.databinding.FragmentIndicatorBinding;
import com.antgul.antgul_android.model.Stock;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class TotalFragment extends BaseFragment<FragmentIndicatorBinding> {
    private ArrayList<Stock> stockList;
    private RecyclerTotalAdapter mAdapter;

    @Override
    protected FragmentIndicatorBinding getViewBinding(@NonNull @NotNull LayoutInflater inflater, @Nullable @org.jetbrains.annotations.Nullable ViewGroup container) {
        return FragmentIndicatorBinding.inflate(inflater,container,false);
    }

    @Override
    protected void initView() {
        stockList = new ArrayList<>();
        mAdapter = new RecyclerTotalAdapter(stockList);
        binding.indicatorRecycler.setLayoutManager(new LinearLayoutManager(getLayoutInflater().getContext()));
        binding.indicatorRecycler.setAdapter(mAdapter);

        for(int i=0; i<30; i++)
        {
            addItem("삼성전자");
        }

        mAdapter.notifyDataSetChanged();
    }

    @Override
    protected void initClickListener() {

    }
    public void addItem(String stockName) {
        Stock stock = new Stock();
        stock.setStockName(stockName);
        mAdapter.addItem(stock);
    }

    private void getPosts() {
        Log.i(TAG, "getPost");
        progressDialog.showProgress();
        db.collection("valueation")
//                .whereEqualTo("category", 1)
//                .orderBy("createAt", Query.Direction.DESCENDING)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Log.d(TAG, document.getId() + " => " + document.getData());
                                Stock stock = document.toObject(Stock.class);
                                stockList.add(stock);
                            }
                            mAdapter.notifyDataSetChanged();
                        } else {
                            Log.d(TAG, "Error getting documents: ", task.getException());
                        }
                        progressDialog.hideProgress();
                    }
                });
    }
}
