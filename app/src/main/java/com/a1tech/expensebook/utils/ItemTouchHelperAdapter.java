package com.a1tech.expensebook.utils;

public interface ItemTouchHelperAdapter {

    void onItemMove(int fromPosition, int toPosition);


    void onItemDismiss(int position);
}