package com.bw.movie.contract;

/**
 * @Auther: 王胜源
 * @Date: 2019/11/15 9:44
 * @Description:
 */
public interface WriteDiscussContract {
    /**
     * v层
     */
    public interface WriteDiscussView{
        public void getWriteFilmView(String writeFilmString);
    }
    /**
     * m层
     */
    public interface WriteDiscussModel{
        public void requestWriteFilmData(int userId, String sessionId, int movieId, String write_film, double douscore, CallWriteFilmBack callWriteFilmBack);
        public interface CallWriteFilmBack{
            public void getWriteFilmResult(String writeFilmString);
        }
    }
}
