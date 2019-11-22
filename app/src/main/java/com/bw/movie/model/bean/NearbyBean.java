package com.bw.movie.model.bean;

import java.util.List;

public class NearbyBean {
    /**
     * result : [{"address":"上地南口华联商厦4F","commentTotal":0,"distance":1707,"followCinema":2,"id":18,"logo":"http://172.17.8.100/images/movie/logo/ctjh.jpg","name":"橙天嘉禾影城北京上地店"},{"address":"清河中街68号五彩城购物中心东区7层","commentTotal":0,"distance":3633,"followCinema":2,"id":22,"logo":"http://172.17.8.100/images/movie/logo/CGVyc.jpg","name":"CGV影城"},{"address":"悦秀路99号二层大地影院","commentTotal":1,"distance":3996,"followCinema":2,"id":19,"logo":"http://172.17.8.100/images/movie/logo/ddyy.jpg","name":"大地影院-北京海淀西三旗物美"},{"address":"育知东路30号华联商厦4层","commentTotal":0,"distance":5513,"followCinema":2,"id":20,"logo":"http://172.17.8.100/images/movie/logo/wmyc.jpg","name":"北京沃美影城"},{"address":"黄平路19号院龙旗购物中心3层","commentTotal":6,"distance":5753,"followCinema":2,"id":17,"logo":"http://172.17.8.100/images/movie/logo/blgj.jpg","name":"保利国际影城北京龙旗广场店"},{"address":"中关村广场购物中心津乐汇三层（鼎好一期西侧）","commentTotal":0,"distance":6012,"followCinema":2,"id":12,"logo":"http://172.17.8.100/images/movie/logo/mjhlyc.jpg","name":"美嘉欢乐影城中关村店"},{"address":"中关村大街19号新中关购物中心B1层","commentTotal":0,"distance":6608,"followCinema":2,"id":15,"logo":"http://172.17.8.100/images/movie/logo/jy.jpg","name":"金逸北京中关村店"},{"address":"中关村大街28号","commentTotal":0,"distance":6865,"followCinema":2,"id":16,"logo":"http://172.17.8.100/images/movie/logo/hdjy.jpg","name":"海淀剧院"},{"address":"远大路1号B座5层魔影国际影城","commentTotal":0,"distance":8303,"followCinema":2,"id":4,"logo":"http://172.17.8.100/images/movie/logo/mygj.jpg","name":"魔影国际影城"},{"address":"远大路1号金源时代购物中心5层东首","commentTotal":5,"distance":8600,"followCinema":2,"id":11,"logo":"http://172.17.8.100/images/movie/logo/xmgj.jpg","name":"星美国际影城"},{"address":"湖景东路11号新奥购物中心B1(东A口)","commentTotal":3,"distance":9403,"followCinema":2,"id":5,"logo":"http://172.17.8.100/images/movie/logo/CGVxx.jpg","name":"CGV星星影城"},{"address":"新街口外大街25号","commentTotal":0,"distance":11262,"followCinema":2,"id":14,"logo":"http://172.17.8.100/images/movie/logo/zygj.jpg","name":"中影国际影城北京小西天店"},{"address":"复兴路69号五棵松卓展时代百货五层东侧","commentTotal":0,"distance":13461,"followCinema":2,"id":13,"logo":"http://172.17.8.100/images/movie/logo/bjalclgj.jpg","name":"北京耀莱成龙国际影城"},{"address":"滨河路乙1号雍和航星园74-76号楼","commentTotal":15,"distance":14619,"followCinema":2,"id":1,"logo":"http://172.17.8.100/images/movie/logo/qcgx.jpg","name":"青春光线电影院"},{"address":"广顺北大街16号望京华彩商业中心B1","commentTotal":0,"distance":15182,"followCinema":2,"id":10,"logo":"http://172.17.8.100/images/movie/logo/hyxd.jpg","name":"华谊兄弟影院"},{"address":"前门大街大栅栏街36号","commentTotal":0,"distance":17565,"followCinema":2,"id":2,"logo":"http://172.17.8.100/images/movie/logo/dgl.jpg","name":"大观楼电影院"},{"address":"三丰北里2号楼悠唐广场B1层","commentTotal":0,"distance":17734,"followCinema":2,"id":8,"logo":"http://172.17.8.100/images/movie/logo/bn.jpg","name":"北京博纳影城朝阳门旗舰店"},{"address":"崇文门外大街18号国瑞城首层、地下二层","commentTotal":0,"distance":18616,"followCinema":2,"id":9,"logo":"http://172.17.8.100/images/movie/logo/blh.jpg","name":"北京百老汇影城国瑞购物中心店"},{"address":"天桥南大街3号楼一层、二层（天桥艺术大厦南侧）","commentTotal":0,"distance":18967,"followCinema":2,"id":3,"logo":"http://172.17.8.100/images/movie/logo/sd.jpg","name":"首都电影院"},{"address":"建国门外大街1号国贸商城区域三地下一层3B120","commentTotal":1,"distance":19720,"followCinema":2,"id":7,"logo":"http://172.17.8.100/images/movie/logo/blg.jpg","name":"北京百丽宫影城"},{"address":"十八里店西直河商业中心东融国际影城","commentTotal":0,"distance":29146,"followCinema":2,"id":21,"logo":"http://172.17.8.100/images/movie/logo/drgjyc.jpg","name":"东融国际影城西直河店"},{"address":"建国路93号万达广场三层","commentTotal":516,"distance":858143,"followCinema":2,"id":6,"logo":"http://172.17.8.100/images/movie/logo/wd.jpg","name":"北京CBD万达广场店"}]
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
         * address : 上地南口华联商厦4F
         * commentTotal : 0
         * distance : 1707
         * followCinema : 2
         * id : 18
         * logo : http://172.17.8.100/images/movie/logo/ctjh.jpg
         * name : 橙天嘉禾影城北京上地店
         */

        private String address;
        private int commentTotal;
        private int distance;
        private int followCinema;
        private int id;
        private String logo;
        private String name;

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public int getCommentTotal() {
            return commentTotal;
        }

        public void setCommentTotal(int commentTotal) {
            this.commentTotal = commentTotal;
        }

        public int getDistance() {
            return distance;
        }

        public void setDistance(int distance) {
            this.distance = distance;
        }

        public int getFollowCinema() {
            return followCinema;
        }

        public void setFollowCinema(int followCinema) {
            this.followCinema = followCinema;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
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
    }
}
