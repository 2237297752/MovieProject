package com.bw.movie.contract;

public interface CinemaDetailContract {
    /**
     * v层
     */
    public interface CinemaDetailView{
        public void getCinemaDetailTopView(String cinemaDetailString);
        public void getCinemaDetailSpeakView(String cinemadetailSpeakString);
        public void getCinemaDetailGuanzhuView(String gunazhuString);
        public void getCinemaDetailWeiGuanzhuView(String weiString);
        public void getFilmPaiDataView(String paiDataString);
    }
    /**
     * m层
     */
    public interface CinemaDetailModel{
        public void requestCinemaDetailTopData(int userId, String sessionId, int cinemaId, CallCinemaDetailTopBack callCinemaDetailTopBack);
        public interface CallCinemaDetailTopBack{
            public void getCinemaDetailTopResult(String cinemaDetailString);
        }

        public void requestCinemaDetailSpeakData(int userId, String sessionId, int cinemaId, int count, int page, CallCinemaDetailSpeakBack callCinemaDetailSpeakBack);
        public interface CallCinemaDetailSpeakBack{
            public void getCinemaDetailSpeakResult(String cinemadetailSpeakString);
        }

        public void requestCinemaDetailGuanzhuData(int userId, String sessionId, int cinemaId, CallCinemaDetailGuanzhuBack callCinemaDetailGuanzhuBack);
        public interface CallCinemaDetailGuanzhuBack{
            public void getCinemaDetailGuanzhuResult(String gunazhuString);
        }

        public void requestCinemaDetailWeiGuanzhuData(int userId, String sessionId, int cinemaId, CallCinemaDetailWeiGuanzhuBack callCinemaDetailWeiGuanzhuBack);
        public interface CallCinemaDetailWeiGuanzhuBack{
            public void getCinemaDetailWeiGuanzhuResult(String weiString);
        }

        public void requestFilmPaiDataData(int cinemaId, int count, int page, CallFilmPaiDataBack callFilmPaiDataBack);
        public interface CallFilmPaiDataBack{
            public void getFilmPaiDataResult(String paiDataString);
        }
    }
}
