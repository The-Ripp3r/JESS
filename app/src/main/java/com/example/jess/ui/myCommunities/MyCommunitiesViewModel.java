package com.example.jess.ui.myCommunities;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class MyCommunitiesViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public MyCommunitiesViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is community fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}