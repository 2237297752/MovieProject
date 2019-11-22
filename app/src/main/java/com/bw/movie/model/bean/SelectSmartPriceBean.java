package com.bw.movie.model.bean;

import java.util.List;

public class SelectSmartPriceBean {
    /**
     * result : [{"address":"远大路1号金源时代购物中心5层东首","cinemaId":11,"logo":"http://172.17.8.100/images/movie/logo/xmgj.jpg","name":"星美国际影城","price":0.15},{"address":"中关村广场购物中心津乐汇三层（鼎好一期西侧）","cinemaId":12,"logo":"http://172.17.8.100/images/movie/logo/mjhlyc.jpg","name":"美嘉欢乐影城中关村店","price":0.16},{"address":"复兴路69号五棵松卓展时代百货五层东侧","cinemaId":13,"logo":"http://172.17.8.100/images/movie/logo/bjalclgj.jpg","name":"北京耀莱成龙国际影城","price":0.17},{"address":"中关村大街19号新中关购物中心B1层","cinemaId":15,"logo":"http://172.17.8.100/images/movie/logo/jy.jpg","name":"金逸北京中关村店","price":0.18},{"address":"新街口外大街25号","cinemaId":14,"logo":"http://172.17.8.100/images/movie/logo/zygj.jpg","name":"中影国际影城北京小西天店","price":0.21},{"address":"中关村大街28号","cinemaId":16,"logo":"http://172.17.8.100/images/movie/logo/hdjy.jpg","name":"海淀剧院","price":0.22},{"address":"清河中街68号五彩城购物中心东区7层","cinemaId":22,"logo":"http://172.17.8.100/images/movie/logo/CGVyc.jpg","name":"CGV影城","price":0.22},{"address":"育知东路30号华联商厦4层","cinemaId":20,"logo":"http://172.17.8.100/images/movie/logo/wmyc.jpg","name":"北京沃美影城","price":0.23},{"address":"悦秀路99号二层大地影院","cinemaId":19,"logo":"http://172.17.8.100/images/movie/logo/ddyy.jpg","name":"大地影院-北京海淀西三旗物美","price":0.25},{"address":"十八里店西直河商业中心东融国际影城","cinemaId":21,"logo":"http://172.17.8.100/images/movie/logo/drgjyc.jpg","name":"东融国际影城西直河店","price":0.25}]
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
         * address : 远大路1号金源时代购物中心5层东首
         * cinemaId : 11
         * logo : http://172.17.8.100/images/movie/logo/xmgj.jpg
         * name : 星美国际影城
         * price : 0.15
         */

        private String address;
        private int cinemaId;
        private String logo;
        private String name;
        private double price;

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public int getCinemaId() {
            return cinemaId;
        }

        public void setCinemaId(int cinemaId) {
            this.cinemaId = cinemaId;
        }

        public String getLogo() {
            return logo;
        }

        public void setLogo(String logo) {
            this.logo = logo;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public double getPrice() {
            return price;
        }

        public void setPrice(double price) {
            this.price = price;
        }
    }
}
