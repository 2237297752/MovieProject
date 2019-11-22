package com.bw.movie.model.bean;

import java.util.List;

public class CinecismBean {
    /**
     * result : [{"commentContent":"非常棒！很喜欢！！！","commentHeadPic":"http://172.17.8.100/images/movie/head_pic/2019-08-16/20190816101933.jpg","commentId":28,"commentTime":1565847185000,"commentUserId":13437,"commentUserName":"辰峰","greatNum":0,"isGreat":0,"replyHeadPic":["http://172.17.8.100/images/movie/head_pic/2019-08-16/20190816101933.jpg","http://172.17.8.100/images/movie/head_pic/2019-08-16/20190816101933.jpg","http://172.17.8.100/images/movie/head_pic/2019-08-16/20190816101933.jpg"],"replyNum":3,"score":8},{"commentContent":"","commentHeadPic":"http://172.17.8.100/images/movie/head_pic/2019-08-16/20190816101933.jpg","commentId":26,"commentTime":1565846041000,"commentUserId":13437,"commentUserName":"辰峰","greatNum":0,"isGreat":0,"replyHeadPic":["http://172.17.8.100/images/movie/head_pic/2019-08-16/20190816101933.jpg","http://172.17.8.100/images/movie/head_pic/2019-08-16/20190816101933.jpg","http://172.17.8.100/images/movie/head_pic/2019-08-16/20190816101933.jpg"],"replyNum":3,"score":0},{"commentContent":"挺好的","commentHeadPic":"http://172.17.8.100/images/movie/head_pic/2019-08-16/20190816101933.jpg","commentId":10,"commentTime":1565782004000,"commentUserId":13437,"commentUserName":"辰峰","greatNum":0,"isGreat":0,"replyHeadPic":[],"replyNum":0,"score":0}]
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
         * commentContent : 非常棒！很喜欢！！！
         * commentHeadPic : http://172.17.8.100/images/movie/head_pic/2019-08-16/20190816101933.jpg
         * commentId : 28
         * commentTime : 1565847185000
         * commentUserId : 13437
         * commentUserName : 辰峰
         * greatNum : 0
         * isGreat : 0
         * replyHeadPic : ["http://172.17.8.100/images/movie/head_pic/2019-08-16/20190816101933.jpg","http://172.17.8.100/images/movie/head_pic/2019-08-16/20190816101933.jpg","http://172.17.8.100/images/movie/head_pic/2019-08-16/20190816101933.jpg"]
         * replyNum : 3
         * score : 8
         */

        private String commentContent;
        private String commentHeadPic;
        private int commentId;
        private long commentTime;
        private int commentUserId;
        private String commentUserName;
        private int greatNum;
        private int isGreat;
        private int replyNum;
        private Double score;
        private List<String> replyHeadPic;

        public String getCommentContent() {
            return commentContent;
        }

        public void setCommentContent(String commentContent) {
            this.commentContent = commentContent;
        }

        public String getCommentHeadPic() {
            return commentHeadPic;
        }

        public void setCommentHeadPic(String commentHeadPic) {
            this.commentHeadPic = commentHeadPic;
        }

        public int getCommentId() {
            return commentId;
        }

        public void setCommentId(int commentId) {
            this.commentId = commentId;
        }

        public long getCommentTime() {
            return commentTime;
        }

        public void setCommentTime(long commentTime) {
            this.commentTime = commentTime;
        }

        public int getCommentUserId() {
            return commentUserId;
        }

        public void setCommentUserId(int commentUserId) {
            this.commentUserId = commentUserId;
        }

        public String getCommentUserName() {
            return commentUserName;
        }

        public void setCommentUserName(String commentUserName) {
            this.commentUserName = commentUserName;
        }

        public int getGreatNum() {
            return greatNum;
        }

        public void setGreatNum(int greatNum) {
            this.greatNum = greatNum;
        }

        public int getIsGreat() {
            return isGreat;
        }

        public void setIsGreat(int isGreat) {
            this.isGreat = isGreat;
        }

        public int getReplyNum() {
            return replyNum;
        }

        public void setReplyNum(int replyNum) {
            this.replyNum = replyNum;
        }

        public Double getScore() {
            return score;
        }

        public void setScore(Double score) {
            this.score = score;
        }

        public List<String> getReplyHeadPic() {
            return replyHeadPic;
        }

        public void setReplyHeadPic(List<String> replyHeadPic) {
            this.replyHeadPic = replyHeadPic;
        }
    }
}
