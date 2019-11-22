package com.bw.movie.contract;

public interface ScoutContract {
    /**
     * v层
     */
    public interface ScoutView{
        public void getScoutView(String scoutString);
    }
    /**
     * m层
     */
    public interface ScoutModel{
        public void requestScoutData(String keyword, int count, int page, CallScoutBack callScoutBack);
        public interface CallScoutBack{
            public void getScoutResult(String scoutString);
        }
    }
}
