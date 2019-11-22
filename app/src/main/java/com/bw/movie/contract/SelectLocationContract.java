package com.bw.movie.contract;

/**
 * @Auther: 王胜源
 * @Date: 2019/11/15 20:55
 * @Description:
 */
public interface SelectLocationContract {
    /**
     * v层
     */
    public interface SelectLocationView{
        public void getSelectTopView(String topString);
        public void getSelectLeftView(String locationString);
        public void getSelectRightView(String locationreight);
        public void getSelectTimeView(String timeString);
        public void getSelectTimeCinemaView(String timeCinemaString);
        public void getSelectPriceView(String priceString);
    }
    /**
     * m层
     */
    public interface SelectLocationModel{
        public void requestSelectTopData(int movieId, CallSelectTopBack callSelectTopBack);
        public interface CallSelectTopBack{
            public void getSelectTopResult(String topString);
        }

        public void requestSelectLeftData(CallSelectLeftBack callSelectLeftBack);
        public interface CallSelectLeftBack{
            public void getSelectLeftResult(String locationString);
        }

        public void requestSelectRightData(int regionId, int movieId, int count, int page, CallSelectRightBack callSelectRightBack);
        public interface CallSelectRightBack{
            public void getSelectRightResult(String locationreight);
        }

        public void requestSelectTimeData(CallSelectTimeBack callSelectTimeBack);
        public interface CallSelectTimeBack{
            public void getSelectTimeResult(String timeString);
        }

        public void requestSelectTimeCinemaData(int movieId, String data, int count, int page, CallSelectTimeCinemaBack callSelectTimeCinemaBack);
        public interface CallSelectTimeCinemaBack{
            public void getSelectTimeCinemaResult(String timeCinemaString);
        }

        public void requestSelectPriceData(int movieId, int count, int page, CallSelectPriceBack callSelectPriceBack);
        public interface CallSelectPriceBack{
            public void getSelectPriceResult(String priceString);
        }
    }
}
