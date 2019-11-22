package com.bw.movie.contract;


public interface MovieDetailContract {

     interface MovieDetailView{
         void getCinemaDetailTopView(String cinemaDetailString);
         void getCinemaDetailSpeakView(String cinemadetailSpeakString);
         void getCinemaDetailGuanzhuView(String gunazhuString);
         void getCinemaDetailWeiGuanzhuView(String weiString);
         void getFilmPaiDataView(String paiDataString);
    }

     interface MovieDetailModel{
         void requestCinemaDetailTopData(int userId, String sessionId, int cinemaId, CallCinemaDetailTopBack callCinemaDetailTopBack);
         interface CallCinemaDetailTopBack{
             void getCinemaDetailTopResult(String cinemaDetailString);
        }

         void requestCinemaDetailSpeakData(int userId, String sessionId, int cinemaId, int count, int page, CallCinemaDetailSpeakBack callCinemaDetailSpeakBack);
         interface CallCinemaDetailSpeakBack{
             void getCinemaDetailSpeakResult(String cinemadetailSpeakString);
        }

         void requestCinemaDetailGuanzhuData(int userId, String sessionId, int cinemaId, CallCinemaDetailGuanzhuBack callCinemaDetailGuanzhuBack);
         interface CallCinemaDetailGuanzhuBack{
             void getCinemaDetailGuanzhuResult(String gunazhuString);
        }

         void requestCinemaDetailWeiGuanzhuData(int userId, String sessionId, int cinemaId, CallCinemaDetailWeiGuanzhuBack callCinemaDetailWeiGuanzhuBack);
         interface CallCinemaDetailWeiGuanzhuBack{
             void getCinemaDetailWeiGuanzhuResult(String weiString);
        }

         void requestFilmPaiDataData(int cinemaId, int count, int page, CallFilmPaiDataBack callFilmPaiDataBack);
         interface CallFilmPaiDataBack{
             void getFilmPaiDataResult(String paiDataString);
        }
    }
}
