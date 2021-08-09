package com.antgul.antgul_android.util;

import android.graphics.Rect;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.jetbrains.annotations.NotNull;

public class RecyclerDecorationWidth extends RecyclerView.ItemDecoration {
    private final int divWidth;

    public RecyclerDecorationWidth(int divWidth) {
        this.divWidth = divWidth;
    }

    @Override
    public void getItemOffsets(@NonNull @NotNull Rect outRect, @NonNull @NotNull View view, @NonNull @NotNull RecyclerView parent, @NonNull @NotNull RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        outRect.right = divWidth;
    }
}
