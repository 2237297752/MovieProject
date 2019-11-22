package com.bw.movie.contract;

public interface FilmDetailHomeContract {
    /**
     * v层
     */
    public interface FilmDetailHomeView{
        //电影详情
        public void getFilmDetailHomeView(String filmdetailString);
        //关注
        public void getForFilmDetailfollowHomeView(String forFilmFllowString);
        //取消关注
        public void getForFilmDetailunfollowHomeView(String forFilmUnFollow);
    }
    /**
     * m层
     */
    public interface FilmDetailHomeModel{
        //电影详情
        public void requestFilmDetailHomeData(int movieId, CallFilmDetailHomeBack callFilmDetailHomeBack);
        public interface CallFilmDetailHomeBack{
            public void getFilmDetailHomeResult(String filmdetailString);
        }

        //关注
        public void requestForFilmDetailfollowHomeData(int userId, String sessionId, int movieId, CallForFilmDetailfollowHomeBack callForFilmDetailfollowHomeBack);
        public interface CallForFilmDetailfollowHomeBack{
            public void getForFilmDetailfollowHomeResult(String forFilmFllowString);
        }

        //取消关注
        public void requestForFilmDetailunfollowHomeData(int userId, String sessionId, int movieId, CallForFilmDetailunfollowHomeBack callForFilmDetailunfollowHomeBack);
        public interface CallForFilmDetailunfollowHomeBack{
            public void getForFilmDetailunfollowHomeResult(String forFilmUnFollow);
        }
    }
}
