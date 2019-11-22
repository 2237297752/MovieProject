package com.bw.movie.model.bean;

import java.util.List;

public class FilmDetailBean {
    /**
     * result : {"commentNum":1,"duration":"130分钟","imageUrl":"http://172.17.8.100/images/movie/stills/ws/ws1.jpg","movieActor":[{"name":"周润发","photo":"http://172.17.8.100/images/movie/actor/ws/zhourunfa.jpg","role":"画家"},{"name":"郭富城","photo":"http://172.17.8.100/images/movie/actor/ws/guofucheng.jpg","role":"李问"},{"name":"张静初","photo":"http://172.17.8.100/images/movie/actor/ws/zhangjingchu.jpg","role":"阮文"},{"name":"冯文娟","photo":"http://172.17.8.100/images/movie/actor/ws/fengwenjuan.jpg","role":"秀清"},{"name":"廖启智","photo":"http://172.17.8.100/images/movie/actor/ws/liaoqizhi.jpg","role":"鑫叔"}],"movieDirector":[{"name":"庄文强","photo":"http://172.17.8.100/images/movie/director/ws/1.jpg"}],"movieId":20,"movieType":"动作 / 惊悚 / 犯罪","name":"无双","placeOrigin":"中国大陆,中国香港","posterList":["http://172.17.8.100/images/movie/stills/ws/ws1.jpg","http://172.17.8.100/images/movie/stills/ws/ws3.jpg","http://172.17.8.100/images/movie/stills/ws/ws2.jpg","http://172.17.8.100/images/movie/stills/ws/ws4.jpg","http://172.17.8.100/images/movie/stills/ws/ws5.jpg","http://172.17.8.100/images/movie/stills/ws/ws6.jpg"],"releaseTime":1537545600000,"score":8.6,"shortFilmList":[{"imageUrl":"http://172.17.8.100/images/movie/stills/ws/ws3.jpg","videoUrl":"http://172.17.8.100/video/movie/ws/ws1.mp4"},{"imageUrl":"http://172.17.8.100/images/movie/stills/ws/ws2.jpg","videoUrl":"http://172.17.8.100/video/movie/ws/ws2.mp4"},{"imageUrl":"http://172.17.8.100/images/movie/stills/ws/ws4.jpg","videoUrl":"http://172.17.8.100/video/movie/ws/ws3.mp4"}],"summary":"以代号\u201c画家\u201d（周润发 饰）为首的犯罪团伙，掌握了制造伪钞技术，难辨真伪，并在全球进行交易获取利益，引起警方高度重视。然而\u201c画家\u201d和其他成员的身份一直成谜，警方的破案进度遭受到了前所未有的挑战。在关键时刻，擅长绘画的李问（郭富城 饰）打开了破案的突破口，而\u201c画家\u201d的真实身份却让众人意想不到\u2026\u2026","whetherFollow":2}
     * message : 查询成功
     * status : 0000
     */

    private ResultBean result;
    private String message;
    private String status;

    public ResultBean getResult() {
        return result;
    }

    public void setResult(ResultBean result) {
        this.result = result;
    }

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

    public static class ResultBean {
        /**
         * commentNum : 1
         * duration : 130分钟
         * imageUrl : http://172.17.8.100/images/movie/stills/ws/ws1.jpg
         * movieActor : [{"name":"周润发","photo":"http://172.17.8.100/images/movie/actor/ws/zhourunfa.jpg","role":"画家"},{"name":"郭富城","photo":"http://172.17.8.100/images/movie/actor/ws/guofucheng.jpg","role":"李问"},{"name":"张静初","photo":"http://172.17.8.100/images/movie/actor/ws/zhangjingchu.jpg","role":"阮文"},{"name":"冯文娟","photo":"http://172.17.8.100/images/movie/actor/ws/fengwenjuan.jpg","role":"秀清"},{"name":"廖启智","photo":"http://172.17.8.100/images/movie/actor/ws/liaoqizhi.jpg","role":"鑫叔"}]
         * movieDirector : [{"name":"庄文强","photo":"http://172.17.8.100/images/movie/director/ws/1.jpg"}]
         * movieId : 20
         * movieType : 动作 / 惊悚 / 犯罪
         * name : 无双
         * placeOrigin : 中国大陆,中国香港
         * posterList : ["http://172.17.8.100/images/movie/stills/ws/ws1.jpg","http://172.17.8.100/images/movie/stills/ws/ws3.jpg","http://172.17.8.100/images/movie/stills/ws/ws2.jpg","http://172.17.8.100/images/movie/stills/ws/ws4.jpg","http://172.17.8.100/images/movie/stills/ws/ws5.jpg","http://172.17.8.100/images/movie/stills/ws/ws6.jpg"]
         * releaseTime : 1537545600000
         * score : 8.6
         * shortFilmList : [{"imageUrl":"http://172.17.8.100/images/movie/stills/ws/ws3.jpg","videoUrl":"http://172.17.8.100/video/movie/ws/ws1.mp4"},{"imageUrl":"http://172.17.8.100/images/movie/stills/ws/ws2.jpg","videoUrl":"http://172.17.8.100/video/movie/ws/ws2.mp4"},{"imageUrl":"http://172.17.8.100/images/movie/stills/ws/ws4.jpg","videoUrl":"http://172.17.8.100/video/movie/ws/ws3.mp4"}]
         * summary : 以代号“画家”（周润发 饰）为首的犯罪团伙，掌握了制造伪钞技术，难辨真伪，并在全球进行交易获取利益，引起警方高度重视。然而“画家”和其他成员的身份一直成谜，警方的破案进度遭受到了前所未有的挑战。在关键时刻，擅长绘画的李问（郭富城 饰）打开了破案的突破口，而“画家”的真实身份却让众人意想不到……
         * whetherFollow : 2
         */

        private int commentNum;
        private String duration;
        private String imageUrl;
        private int movieId;
        private String movieType;
        private String name;
        private String placeOrigin;
        private long releaseTime;
        private double score;
        private String summary;
        private int whetherFollow;
        private List<MovieActorBean> movieActor;
        private List<MovieDirectorBean> movieDirector;
        private List<String> posterList;
        private List<ShortFilmListBean> shortFilmList;

        public int getCommentNum() {
            return commentNum;
        }

        public void setCommentNum(int commentNum) {
            this.commentNum = commentNum;
        }

        public String getDuration() {
            return duration;
        }

        public void setDuration(String duration) {
            this.duration = duration;
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

        public String getMovieType() {
            return movieType;
        }

        public void setMovieType(String movieType) {
            this.movieType = movieType;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPlaceOrigin() {
            return placeOrigin;
        }

        public void setPlaceOrigin(String placeOrigin) {
            this.placeOrigin = placeOrigin;
        }

        public long getReleaseTime() {
            return releaseTime;
        }

        public void setReleaseTime(long releaseTime) {
            this.releaseTime = releaseTime;
        }

        public double getScore() {
            return score;
        }

        public void setScore(double score) {
            this.score = score;
        }

        public String getSummary() {
            return summary;
        }

        public void setSummary(String summary) {
            this.summary = summary;
        }

        public int getWhetherFollow() {
            return whetherFollow;
        }

        public void setWhetherFollow(int whetherFollow) {
            this.whetherFollow = whetherFollow;
        }

        public List<MovieActorBean> getMovieActor() {
            return movieActor;
        }

        public void setMovieActor(List<MovieActorBean> movieActor) {
            this.movieActor = movieActor;
        }

        public List<MovieDirectorBean> getMovieDirector() {
            return movieDirector;
        }

        public void setMovieDirector(List<MovieDirectorBean> movieDirector) {
            this.movieDirector = movieDirector;
        }

        public List<String> getPosterList() {
            return posterList;
        }

        public void setPosterList(List<String> posterList) {
            this.posterList = posterList;
        }

        public List<ShortFilmListBean> getShortFilmList() {
            return shortFilmList;
        }

        public void setShortFilmList(List<ShortFilmListBean> shortFilmList) {
            this.shortFilmList = shortFilmList;
        }

        public static class MovieActorBean {
            /**
             * name : 周润发
             * photo : http://172.17.8.100/images/movie/actor/ws/zhourunfa.jpg
             * role : 画家
             */

            private String name;
            private String photo;
            private String role;

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getPhoto() {
                return photo;
            }

            public void setPhoto(String photo) {
                this.photo = photo;
            }

            public String getRole() {
                return role;
            }

            public void setRole(String role) {
                this.role = role;
            }
        }

        public static class MovieDirectorBean {
            /**
             * name : 庄文强
             * photo : http://172.17.8.100/images/movie/director/ws/1.jpg
             */

            private String name;
            private String photo;

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getPhoto() {
                return photo;
            }

            public void setPhoto(String photo) {
                this.photo = photo;
            }
        }

        public static class ShortFilmListBean {
            /**
             * imageUrl : http://172.17.8.100/images/movie/stills/ws/ws3.jpg
             * videoUrl : http://172.17.8.100/video/movie/ws/ws1.mp4
             */

            private String imageUrl;
            private String videoUrl;

            public String getImageUrl() {
                return imageUrl;
            }

            public void setImageUrl(String imageUrl) {
                this.imageUrl = imageUrl;
            }

            public String getVideoUrl() {
                return videoUrl;
            }

            public void setVideoUrl(String videoUrl) {
                this.videoUrl = videoUrl;
            }
        }
    }
}
