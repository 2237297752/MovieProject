package com.bw.movie.contract;

public interface FilmContract {
    /**
     * v层
     */
    public interface FilmView{
        public void getBannerView(String bannerString);
        public void getPlayingView(String playingString);
        public void getWillView(String willString);
        public void getHotView(String hotString);
        public void getBtnyuyueView(String yuyueFilmString);
    }
    /**
     * m层
     */
    public interface FilmModel{
        public void requestBannerData(CallBannerBack callBannerBack);
        public interface CallBannerBack{
            public void getBannerResult(String bannerString);
        }

        public void requestPlayingData(int count, int page, CallPlayingBack callPlayingBack);
        public interface CallPlayingBack{
            public void getPlayingResult(String playingString);
        }

        public void requestWillData(int count, int page, int userId, String sessionId, CallWillBack callWillBack);
        public interface CallWillBack{
            public void getWillResult(String willString);
        }

        public void requestHotData(int count, int page, CallHotBack callHotBack);
        public interface CallHotBack{
            public void getHotResult(String hotString);
        }

        public void requestBtnyuyueData(int userId, String sessionId, int movieId, CallBtnyuyueBack callBtnyuyueBack);
        public interface CallBtnyuyueBack{
            public void getBtnyuyueResult(String yuyueFilmString);
        }

    }
}
