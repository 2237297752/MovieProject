package com.bw.movie.contract;

public interface SelectCinemaSeatContract {
    /**
     * v层
     */
    public interface SelectCinemaSeatView{
        //顶部视频影片信息
        public void getSelectHeraldSeatView(String selectherdString);
        //座位信息
        public void getStudioSeatView(String hallString);
        //电影排期
        public void getFilmSchedulingView(String schedString);
        //购票下单
        public void getFilmPayTicketXiaView(String payTicketString);
    }
    /**
     * m层
     */
    public interface SelectCinemaSeatModel{
        public void requestSelectHeraldSeatData(int movieId, CallSelectHeraldSeatBack callSelectHeraldSeatBack);
        public interface CallSelectHeraldSeatBack{
            public void getSelectHeraldSeatResult(String selectherdString);
        }

        public void requestStudioSeatData(int hallId, CallStudioSeatBack callStudioSeatBack);
        public interface CallStudioSeatBack{
            public void getStudioSeatResult(String hallString);
        }

        public void requestFilmSchedulingData(int movieId, int cinemaId, CallFilmSchedulingBack callFilmSchedulingBack);
        public interface CallFilmSchedulingBack{
            public void getFilmSchedulingResult(String schedString);
        }

        public void requestFilmPayTicketXiaData(int userId, String sessionId, int hallId, String seat, String sign, CallFilmPayTicketXiaBack callFilmPayTicketXiaBack);
        public interface CallFilmPayTicketXiaBack{
            public void getFilmPayTicketXiaResult(String payTicketString);
        }

    }
}
