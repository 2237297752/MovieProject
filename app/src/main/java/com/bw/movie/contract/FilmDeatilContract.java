package com.bw.movie.contract;

public interface FilmDeatilContract {
    /**
     * v层
     */
    public interface FilmDetailView{

        public void getSuggestData(String filmdetailString);
        public void getHeraldData(String videoString);
        public void getStillData(String stillString);
        public void getCineCimsData(String cinecismString);
    }

    /**
     * m层
     */
    public interface FilmDetailModel{
        public void requestSuggestData(int movieId, CallsuggestBack callsuggestBack);
        public interface CallsuggestBack{
            public void getsuggestResult(String filmdetailString);
        }

        public void requestHeraldData(int movieId, CallheraldBack callheraldBack);
        public interface CallheraldBack{
            public void getheraldResult(String videoString);
        }

        public void requestStillData(int movieId, CallstillBack callstillBack);
        public interface CallstillBack{
            public void getstillResult(String stillString);
        }

        public void requestCineCimsData(int movieId, int count, int page, CallCineCimsBack callCineCimsBack);
        public interface CallCineCimsBack{
            public void getCineCimsResult(String cinecismString);
        }
    }
}
