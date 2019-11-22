package com.bw.movie.presenter;

import com.bw.movie.base.BasePresenter;

import com.bw.movie.contract.PersonDetailContract;
import com.bw.movie.model.PersonDetailModel;

import java.io.File;
import java.util.List;

import okhttp3.MultipartBody;

public class PersonDetailPresenter<V extends PersonDetailContract.PersonDetailView> extends BasePresenter<V> {


    private final PersonDetailModel model;

    public PersonDetailPresenter() {
        //创建m层
        model = new PersonDetailModel();
    }
    public void requestPersonDetailTouXiangInfo(int userId, String sessionId, List<MultipartBody.Part> image){
        model.requestPersonDetailTouXiangData(userId,sessionId,image,new PersonDetailContract.PersonDetailModel.CallPersonDetailTouXiangBack() {
            @Override
            public void getPersonDetailTouXiangResult(String touXiangString) {
                getView().getPersonDetailTouXiangView(touXiangString);
            }
        });
    }

}
