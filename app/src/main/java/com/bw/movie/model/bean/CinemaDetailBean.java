package com.bw.movie.model.bean;

public class CinemaDetailBean {
    /**
     * result : {"address":"远大路1号金源时代购物中心5层东首","businessHoursContent":"星期一 至 星期日   早08:30:00 - 晚22:00:00","commentTotal":0,"distance":0,"followCinema":2,"id":11,"label":"3D眼镜,儿童优惠","logo":"http://172.17.8.100/images/movie/logo/xmgj.jpg","name":"星美国际影城","phone":"010-88878696","vehicleRoute":"运通101 运通114 996 79 355 365 611 539 360 374支等到远大东路下车 地铁10号线长春桥A出口"}
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
         * address : 远大路1号金源时代购物中心5层东首
         * businessHoursContent : 星期一 至 星期日   早08:30:00 - 晚22:00:00
         * commentTotal : 0
         * distance : 0
         * followCinema : 2
         * id : 11
         * label : 3D眼镜,儿童优惠
         * logo : http://172.17.8.100/images/movie/logo/xmgj.jpg
         * name : 星美国际影城
         * phone : 010-88878696
         * vehicleRoute : 运通101 运通114 996 79 355 365 611 539 360 374支等到远大东路下车 地铁10号线长春桥A出口
         */

        private String address;
        private String businessHoursContent;
        private int commentTotal;
        private int distance;
        private int followCinema;
        private int id;
        private String label;
        private String logo;
        private String name;
        private String phone;
        private String vehicleRoute;

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getBusinessHoursContent() {
            return businessHoursContent;
        }

        public void setBusinessHoursContent(String businessHoursContent) {
            this.businessHoursContent = businessHoursContent;
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

        public String getLabel() {
            return label;
        }

        public void setLabel(String label) {
            this.label = label;
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

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getVehicleRoute() {
            return vehicleRoute;
        }

        public void setVehicleRoute(String vehicleRoute) {
            this.vehicleRoute = vehicleRoute;
        }
    }
}
