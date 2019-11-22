package com.bw.movie.model;

import com.bw.movie.contract.SelectCinemaSeatContract;
import com.bw.movie.model.api.ApiService;
import com.bw.movie.utils.RetrofitUtils;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;

public class SelectCinemaSeatModel implements SelectCinemaSeatContract.SelectCinemaSeatModel{
    @Override
    public void requestSelectHeraldSeatData(int movieId, CallSelectHeraldSeatBack callSelectHeraldSeatBack) {
        ApiService apiService = RetrofitUtils.getInstance().createService(ApiService.class);
        apiService.obtainFilmdetailsInfo(movieId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<ResponseBody>() {
                    @Override
                    public void accept(ResponseBody body) throws Exception {
                        String selectherdString = body.string();
                        callSelectHeraldSeatBack.getSelectHeraldSeatResult(selectherdString);
                    }
                });
    }

    @Override
    public void requestStudioSeatData(int hallId, CallStudioSeatBack callStudioSeatBack) {
        ApiService apiService = RetrofitUtils.getInstance().createService(ApiService.class);
        apiService.obtainStudioHallInfo(hallId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<ResponseBody>() {
                    @Override
                    public void accept(ResponseBody body) throws Exception {
                        String hallString = body.string();
                        callStudioSeatBack.getStudioSeatResult(hallString);
                    }
                });
    }

    @Override
    public void requestFilmSchedulingData(int movieId, int cinemaId, CallFilmSchedulingBack callFilmSchedulingBack) {
        ApiService apiService = RetrofitUtils.getInstance().createService(ApiService.class);
        apiService.obtainCinemaSchedInfo(movieId,cinemaId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<ResponseBody>() {
                    @Override
                    public void accept(ResponseBody body) throws Exception {
                        String schedString = body.string();
                        callFilmSchedulingBack.getFilmSchedulingResult(schedString);
                    }
                });
    }

    @Override
    public void requestFilmPayTicketXiaData(int userId, String sessionId, int hallId, String seat, String sign, CallFilmPayTicketXiaBack callFilmPayTicketXiaBack) {
        ApiService apiService = RetrofitUtils.getInstance().createService(ApiService.class);
        apiService.obtainPayTicketXiaInfo(userId,sessionId,hallId,seat,sign)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<ResponseBody>() {
                    @Override
                    public void accept(ResponseBody body) throws Exception {
                        String payTicketString = body.string();
                        callFilmPayTicketXiaBack.getFilmPayTicketXiaResult(payTicketString);
                    }
                });
    }
}
