package com.bw.movie.contract;

public interface CinemaContract {
    interface CinemaView{
        void getBannerView(String bannerString);
        void getPlayingView(String playingString);
        void getWillView(String willString);
        void getHotView(String hotString);
        void getBtnyuyueView(String yuyueFilmString);
    }
    interface CinemaModel{
        void requestBannerData(CallBannerBack callBannerBack);
        interface CallBannerBack{
            void getBannerResult(String bannerString);
        }
        void requestPlayingData(int count, int page, CallPlayingBack callPlayingBack);
        interface CallPlayingBack{
            void getPlayingResult(String playingString);
        }
        void requestWillData(int count, int page, int userId, String sessionId, CallWillBack callWillBack);
        interface CallWillBack{
            void getWillResult(String willString);
        }
        void requestHotData(int count, int page, CallHotBack callHotBack);
        interface CallHotBack{
            void getHotResult(String hotString);
        }
        void requestBtnyuyueData(int userId, String sessionId, int movieId, CallBtnyuyueBack callBtnyuyueBack);
        interface CallBtnyuyueBack{
            void getBtnyuyueResult(String yuyueFilmString);
        }
    }
}
