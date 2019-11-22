package com.bw.movie.presenter;

import com.bw.movie.base.BasePresenter;
import com.bw.movie.contract.WriteDiscussContract;
import com.bw.movie.model.WriteDiscussModel;

public class WriteDiscussPresenter<V extends WriteDiscussContract.WriteDiscussView> extends BasePresenter<V> {


    private final WriteDiscussModel model;

    public WriteDiscussPresenter() {
        //创建m层
        model = new WriteDiscussModel();
    }
    public void requestWriteFilmInfo(int userId, String sessionId, int movieId, String write_film, double douscore){
       model.requestWriteFilmData(userId,sessionId,movieId,write_film,douscore,new WriteDiscussContract.WriteDiscussModel.CallWriteFilmBack() {
           @Override
           public void getWriteFilmResult(String writeFilmString) {
               getView().getWriteFilmView(writeFilmString);
           }
       });
    }
}
