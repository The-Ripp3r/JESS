package com.example.jess.ui.myPosts;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class MyPostsViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public MyPostsViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is community fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}