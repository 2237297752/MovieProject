package com.bw.movie.model.bean;

import java.util.List;

public class PaiDataBean {
    /**
     * result : [{"director":"罗森·马歇尔·瑟伯","imageUrl":"http://172.17.8.100/images/movie/stills/mtyj/mtyj1.jpg","movieId":2,"name":"摩天营救","score":8.5,"starring":"道恩·强森,昆凌,文峰,黄经汉"},{"director":"闫非","imageUrl":"http://172.17.8.100/images/movie/stills/xhssf/xhssf1.jpg","movieId":3,"name":"西虹市首富","score":8.6,"starring":"沈腾,宋芸烨,张晨光,艾伦,常远"}]
     * message : 查询成功
     * status : 0000
     */

    private String message;
    private String status;
    private List<ResultBean> result;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<ResultBean> getResult() {
        return result;
    }

    public void setResult(List<ResultBean> result) {
        this.result = result;
    }

    public static class ResultBean {
        /**
         * director : 罗森·马歇尔·瑟伯
         * imageUrl : http://172.17.8.100/images/movie/stills/mtyj/mtyj1.jpg
         * movieId : 2
         * name : 摩天营救
         * score : 8.5
         * starring : 道恩·强森,昆凌,文峰,黄经汉
         */

        private String director;
        private String imageUrl;
        private int movieId;
        private String name;
        private double score;
        private String starring;

        public String getDirector() {
            return director;
        }

        public void setDirector(String director) {
            this.director = director;
        }

        public String getImageUrl() {
            return imageUrl;
        }

        public void setImageUrl(String imageUrl) {
            this.imageUrl = imageUrl;
        }

        public int getMovieId() {
            return movieId;
        }

        public void setMovieId(int movieId) {
            this.movieId = movieId;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public double getScore() {
            return score;
        }

        public void setScore(double score) {
            this.score = score;
        }

        public String getStarring() {
            return starring;
        }

        public void setStarring(String starring) {
            this.starring = starring;
        }
    }
}
