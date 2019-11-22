package com.bw.movie.presenter;

import com.bw.movie.base.BasePresenter;
import com.bw.movie.contract.SelectCinemaSeatContract;
import com.bw.movie.model.SelectCinemaSeatModel;

public class SelectCinemaSeatPresenter<V extends SelectCinemaSeatContract.SelectCinemaSeatView> extends BasePresenter<V> {


    private final SelectCinemaSeatModel model;

    public SelectCinemaSeatPresenter() {
        //创建m层
        model = new SelectCinemaSeatModel();
    }
    public void requestSelectHeraldSeatInfo(int movieId){
        model.requestSelectHeraldSeatData(movieId,new SelectCinemaSeatContract.SelectCinemaSeatModel.CallSelectHeraldSeatBack() {
            @Override
            public void getSelectHeraldSeatResult(String selectherdString) {
                getView().getSelectHeraldSeatView(selectherdString);
            }
        });
    }

    public void requestStudioSeatInfo(int hallId){
        model.requestStudioSeatData(hallId,new SelectCinemaSeatContract.SelectCinemaSeatModel.CallStudioSeatBack() {
            @Override
            public void getStudioSeatResult(String hallString) {
                getView().getStudioSeatView(hallString);
            }
        });
    }

    public void requestFilmSchedulingInfo(int movieId, int cinemaId){
        model.requestFilmSchedulingData(movieId,cinemaId,new SelectCinemaSeatContract.SelectCinemaSeatModel.CallFilmSchedulingBack() {
            @Override
            public void getFilmSchedulingResult(String schedString) {
                getView().getFilmSchedulingView(schedString);
            }
        });
    }

    public void requestFilmPayTicketXiaInfo(int userId, String sessionId, int hallId, String seat, String sign){
        model.requestFilmPayTicketXiaData(userId,sessionId,hallId,seat,sign,new SelectCinemaSeatContract.SelectCinemaSeatModel.CallFilmPayTicketXiaBack() {
            @Override
            public void getFilmPayTicketXiaResult(String payTicketString) {
                getView().getFilmPayTicketXiaView(payTicketString);
            }
        });
    }

}
