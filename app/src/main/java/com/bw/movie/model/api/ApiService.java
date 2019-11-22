package com.bw.movie.model.api;

import java.util.List;

import io.reactivex.Observable;
import okhttp3.MultipartBody;
import okhttp3.ResponseBody;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;
import retrofit2.http.Streaming;
import retrofit2.http.Url;

public interface ApiService {
    //发送邮箱
    //http://172.17.8.100/movieApi/user/v2/sendOutEmailCode
    @FormUrlEncoded
    @POST("movieApi/user/v2/sendOutEmailCode")
    public Observable<ResponseBody> sendOutEmailCode(@Field("email") String email);

    //注册
    //http://172.17.8.100/movieApi/user/v2/register
    @POST("movieApi/user/v2/register")
    @FormUrlEncoded
    public Observable<ResponseBody> obtainRegistInfo(@Field("nickName") String nickNameRegist,@Field("pwd") String pwdRegist,@Field("email") String captch,@Field("code") String codeRegist);

    //登录
    //http://172.17.8.100/movieApi/user/v2/login
    @POST("movieApi/user/v2/login")
    @FormUrlEncoded
    public Observable<ResponseBody> obtainLoginInfo(@Field("email") String useEmail,@Field("pwd") String usePwd);

    //轮播图
    //http://172.17.8.100/movieApi/tool/v2/banner
    @GET("movieApi/tool/v2/banner")
    public Observable<ResponseBody> obtainBannerInfo();

    //正在热映
    //http://172.17.8.100/movieApi/movie/v2/findReleaseMovieList
    @GET("movieApi/movie/v2/findReleaseMovieList")
    public Observable<ResponseBody> obtainPlayingInfo(@Query("count") int count,@Query("page") int page);

    //即将上映
    //http://172.17.8.100/movieApi/movie/v2/findComingSoonMovieList
    @GET("movieApi/movie/v2/findComingSoonMovieList")
    public Observable<ResponseBody> obtainWillInfo(@Query("count") int count, @Query("page") int page, @Header("userId") int userId,@Header("sessionId") String sessionId);

    //热门电影
    //http://172.17.8.100/movieApi/movie/v2/findHotMovieList
    @GET("movieApi/movie/v2/findHotMovieList")
    public Observable<ResponseBody> obtainHotInfo(@Query("count") int count,@Query("page") int page);

    //影片搜索
    //http://172.17.8.100/movieApi/movie/v2/findMovieByKeyword
    @GET("movieApi/movie/v2/findMovieByKeyword")
    public Observable<ResponseBody> obtainScoutInfo(@Query("keyword") String keyword,@Query("count") int count,@Query("page") int page);

    //查询电影详情
    //http://172.17.8.100/movieApi/movie/v2/findMoviesDetail
    @GET("movieApi/movie/v2/findMoviesDetail")
    public Observable<ResponseBody> obtainFilmdetailsInfo(@Query("movieId") int movieId);

    //关注电影
    //http://172.17.8.100/movieApi/movie/v1/verify/followMovie
    @GET("movieApi/movie/v1/verify/followMovie")
    public Observable<ResponseBody> obtainForFilmFllowInfo(@Header("userId")int userId,@Header("sessionId")String sessionId,@Query("movieId") int movieId);

    //取消关注电影
    //http://172.17.8.100/movieApi/movie/v1/verify/cancelFollowMovie
    @GET("movieApi/movie/v1/verify/cancelFollowMovie")
    public Observable<ResponseBody> obtainForFilmUnFllowInfo(@Header("userId")int userId,@Header("sessionId")String sessionId,@Query("movieId") int movieId);


    //根据电影id查询电影评论
    //http://172.17.8.100/movieApi/movie/v2/findAllMovieComment?movieId=20&page=1&count=4
    @GET("movieApi/movie/v2/findAllMovieComment")
    public Observable<ResponseBody> obtainCinecismInfo(@Query("movieId") int movieId,@Query("count") int count,@Query("page") int page);

    //根据电影id,区域id 查询播放影院信息
    //http://172.17.8.100/movieApi/movie/v2/findCinemasInfoByRegion
    @GET("movieApi/movie/v2/findCinemasInfoByRegion")
    public Observable<ResponseBody> obtainFilmLocaDetailCinemaInfo(@Query("movieId") int movieId,@Query("regionId") int regionId,@Query("count") int count,@Query("page") int page);

    //查询一周排期的时间
    //http://172.17.8.100/movieApi/tool/v2/findDateList
    @GET("movieApi/tool/v2/findDateList")
    public Observable<ResponseBody> obtainFilmDetailTimeInfo();

    //根据电影id，时间 查询播放影院信息
    //http://172.17.8.100/movieApi/movie/v2/findCinemasInfoByDate
    @GET("movieApi/movie/v2/findCinemasInfoByDate")
    public Observable<ResponseBody> obtainFilmDetailTimeCinemaInfo(@Query("movieId") int movieId, @Query("data") String data, @Query("count") int count, @Query("page") int page);

    //根据电影价格查询播放影院信息
    //http://172.17.8.100/movieApi/movie/v2/findCinemasInfoByPrice
    @GET("movieApi/movie/v2/findCinemasInfoByPrice")
    public Observable<ResponseBody> obtainFilmDetailSmallPriceInfo(@Query("movieId") int movieId,@Query("count") int count,@Query("page") int page);

    //添加对影片的评论
    //http://172.17.8.100/movieApi/movie/v1/verify/movieComment
    @POST("movieApi/movie/v1/verify/movieComment")
    @FormUrlEncoded
    public Observable<ResponseBody> obtainAddWriteFilmInfo(@Header("userId")int userId,@Header("sessionId")String sessionId,@Field("movieId") int movieId,@Field("commentContent") String write_film,@Field("score") double douscore);

    //推荐影院
    //http://172.17.8.100/movieApi/cinema/v1/findRecommendCinemas
    @GET("movieApi/cinema/v1/findRecommendCinemas")
    public Observable<ResponseBody> obtainRecommendInfo(@Query("count") int count,@Query("page") int page);

    //附近影院
    //http://172.17.8.100/movieApi/cinema/v1/findNearbyCinemas
    @GET("movieApi/cinema/v1/findNearbyCinemas")
    public Observable<ResponseBody> obtainNearbyInfo(@Header("userId")int userId,@Header("sessionId")String sessionId,@Query("page")int page, @Query("count")int count);

    //影院：海淀区：查询区域列表
    //http://172.17.8.100/movieApi/tool/v2/findRegionList
    @GET("movieApi/tool/v2/findRegionList")
    public Observable<ResponseBody> obtainLocationInfo();

    //影院：海淀区：根据区域查询影院
    //http://172.17.8.100/movieApi/cinema/v2/findCinemaByRegion
    @GET("movieApi/cinema/v2/findCinemaByRegion")
    public Observable<ResponseBody> obtainLocationCinemaInfo(@Query("regionId") int regionId);

    //根据电影ID和影院ID查询电影排期列表
    //http://172.17.8.100/movieApi/movie/v2/findMovieSchedule
    @GET("movieApi/movie/v2/findMovieSchedule")
    public Observable<ResponseBody> obtainCinemaSchedInfo(@Query("movieId") int movieId,@Query("cinemaId") int cinemaId);

    //根据影厅id 查询座位信息
    //http://172.17.8.100/movieApi/movie/v2/findSeatInfo
    @GET("movieApi/movie/v2/findSeatInfo")
    public Observable<ResponseBody> obtainStudioHallInfo(@Query("hallId") int hallId);

    //预约
    //http://172.17.8.100/movieApi/movie/v2/verify/reserve
    @POST("movieApi/movie/v2/verify/reserve")
    @FormUrlEncoded
    public Observable<ResponseBody> obtainYuYueFilmInfo(@Header("userId")int userId,@Header("sessionId")String sessionId,@Field("movieId") int movieId);

    //查询电影信息明细
    //http://172.17.8.100/movieApi/cinema/v1/findCinemaInfo
    @GET("movieApi/cinema/v1/findCinemaInfo")
    public Observable<ResponseBody> obtainCinemaDetailInfo(@Header("userId")int userId,@Header("sessionId")String sessionId,@Query("cinemaId") int cinemaId);

    //查询影院用户评论列表
    //http://172.17.8.100/movieApi/cinema/v1/findAllCinemaComment
    @GET("movieApi/cinema/v1/findAllCinemaComment")
    public Observable<ResponseBody> obtainCinemaDetailSpeakInfo(@Header("userId")int userId,@Header("sessionId")String sessionId,@Query("cinemaId") int cinemaId,@Query("count")int count, @Query("page")int page);

    //查询影院下的电影排期
    //http://172.17.8.100/movieApi/cinema/v2/findCinemaScheduleList
    @GET("movieApi/cinema/v2/findCinemaScheduleList")
    public Observable<ResponseBody> obtainFilmPaiDataInfo(@Query("cinemaId") int cinemaId,@Query("count")int count, @Query("page")int page);

    //上传用户头像
    //http://172.17.8.100/movieApi/user/v1/verify/uploadHeadPic
    @Multipart
    @POST("movieApi/user/v1/verify/uploadHeadPic")
    public Observable<ResponseBody> obtainPersonDetailTouXiangInfo(@Header("userId")int userId, @Header("sessionId")String sessionId, @Part List<MultipartBody.Part> parts);

    //微信登录:http://172.17.8.100/movieApi/user/v1/weChatBindingLogin
    @FormUrlEncoded
    @POST("http://mobile.bwstudent.com/movieApi/user/v1/weChatBindingLogin")
    public Observable<ResponseBody> weChatInfo(@Field("code")String code);

    //购票下单
    //http://172.17.8.100/movieApi/movie/v2/verify/buyMovieTickets
    @POST("movieApi/movie/v2/verify/buyMovieTickets")
    @FormUrlEncoded
    public Observable<ResponseBody> obtainPayTicketXiaInfo(@Header("userId")int userId, @Header("sessionId")String sessionId,@Field("scheduleId") int scheduleId ,@Field("seat") String seat,@Field("sign") String sign);

    //支付
    //http://172.17.8.100/movieApi/movie/v2/verify/pay
    @POST("movieApi/movie/v2/verify/pay")
    @FormUrlEncoded
    public Observable<ResponseBody> obtainPayFilmInfo(@Header("userId")int userId, @Header("sessionId")String sessionId,@Field("payType") int payType,@Field("orderId") String orderId);

    //关注影院
    //http://172.17.8.100/movieApi/cinema/v1/verify/followCinema
    @GET("movieApi/cinema/v1/verify/followCinema")
    public Observable<ResponseBody> obtainGuanzhuCinemaInfo(@Header("userId")int userId, @Header("sessionId")String sessionId,@Query("cinemaId")int cinemaId);

    //取消关注影院
    //http://172.17.8.100/movieApi/cinema/v1/verify/cancelFollowCinema
    @GET("movieApi/cinema/v1/verify/cancelFollowCinema")
    public Observable<ResponseBody> obtainQuxiaoGuanzhuCinemaInfo(@Header("userId")int userId, @Header("sessionId")String sessionId,@Query("cinemaId")int cinemaId);

    //断点续传
    public static final String BASE_URL = "http://172.17.8.100/";
    @Streaming
    @GET
    Observable<ResponseBody> executeDownload(@Header("Range") String range, @Url() String url);

}
