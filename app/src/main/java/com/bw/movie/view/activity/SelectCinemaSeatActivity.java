package com.bw.movie.view.activity;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.alipay.sdk.app.PayTask;
import com.bumptech.glide.Glide;
import com.bw.movie.R;
import com.bw.movie.base.BaseActivity;
import com.bw.movie.contract.SelectCinemaSeatContract;
import com.bw.movie.model.api.ApiService;
import com.bw.movie.model.bean.Alipay;
import com.bw.movie.model.bean.ChooseSeatBean;
import com.bw.movie.model.bean.CinemaSchedBean;
import com.bw.movie.model.bean.FilmDetailBean;
import com.bw.movie.model.bean.FilmPayTicketXiaBean;
import com.bw.movie.model.bean.PayFilmBean;
import com.bw.movie.presenter.SelectCinemaSeatPresenter;
import com.bw.movie.utils.RetrofitUtils;
import com.bw.movie.view.App;
import com.bw.movie.view.adapter.SelectSchedAdapter;
import com.bw.movie.view.adapter.XuanZuoAdapter;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.google.gson.Gson;
import com.tencent.mm.opensdk.modelpay.PayReq;

import java.net.URI;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.xml.transform.Result;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;

//选择座位页
public class SelectCinemaSeatActivity extends BaseActivity<SelectCinemaSeatContract.SelectCinemaSeatView, SelectCinemaSeatPresenter<SelectCinemaSeatContract.SelectCinemaSeatView>> implements SelectCinemaSeatContract.SelectCinemaSeatView {

    private int SDK_PAY_FLAG;
    @BindView(R.id.selectfilmseat_fanhui)
    ImageView selectfilmseatFanhui;
    @BindView(R.id.name_selectfilmseat)
    TextView nameSelectfilmseat;
    @BindView(R.id.jcvideo_selectfilmseat)
    JCVideoPlayer jcvideoSelectfilmseat;
    @BindView(R.id.cb_cinemaseatkexuan)
    CheckBox cbCinemaseatkexuan;
    @BindView(R.id.cb_cinemaseatyishou)
    CheckBox cbCinemaseatyishou;
    @BindView(R.id.cb_cinemaseat_xuanzhong)
    CheckBox cbCinemaseatXuanzhong;
    @BindView(R.id.recycle_selectSchedseat)
    RecyclerView recycleSelectSchedseat;
    @BindView(R.id.btn_selectseat)
    Button btnSelectseat;
    @BindView(R.id.recycle_selectseat)
    RecyclerView recycleSelectseat;
    @BindView(R.id.btn_purchaseOrder)
    Button btnPurchaseOrder;
    private int hallId;
    private int userId;
    private String sessionId;
    private String seat;
    private String orderId;
    private long sum;
    private double zf;
    private CheckBox wxzf;
    private double fare;

    @Override
    public void getSelectHeraldSeatView(String selectherdString) {
        Gson gson = new Gson();
        FilmDetailBean filmDetailBean = gson.fromJson(selectherdString, FilmDetailBean.class);
        ArrayList<FilmDetailBean.ResultBean.ShortFilmListBean> shortFilmList = (ArrayList<FilmDetailBean.ResultBean.ShortFilmListBean>) filmDetailBean.getResult().getShortFilmList();

        jcvideoSelectfilmseat.setUp(shortFilmList.get(0).getVideoUrl(), null);
        Glide.with(SelectCinemaSeatActivity.this).load(shortFilmList.get(0).getImageUrl()).into(jcvideoSelectfilmseat.ivThumb);
        nameSelectfilmseat.setText(filmDetailBean.getResult().getName());
    }

    //播放停止
    @Override
    public void onPause() {
        super.onPause();
        JCVideoPlayer.releaseAllVideos();
    }
    //activity销毁的时候释放资源，播放器停止播放
    @Override
    public void onDestroy() {
        super.onDestroy();
        try {
            jcvideoSelectfilmseat.releaseAllVideos();
        } catch (Exception e) {
        }
    }

    @Override
    public void getStudioSeatView(String hallString) {
        Gson gson = new Gson();
        ChooseSeatBean chooseSeatBean = gson.fromJson(hallString, ChooseSeatBean.class);
        List<ChooseSeatBean.ResultBean> result = chooseSeatBean.getResult();
        final List<ChooseSeatBean.ResultBean> list = new ArrayList<>();
        List<List<ChooseSeatBean.ResultBean>> beans = new ArrayList<>();
        int a = 1;
        for (int i = 0; i < result.size(); i++) {
            String row = result.get(i).getRow();
            final int r = Integer.parseInt(row);
            if (r == a && i + 1 != result.size()) {
                list.add(result.get(i));
            } else if (i + 1 == result.size()) {
                list.add(result.get(i));
                beans.add(list);
            } else if (r - 1 == a) {
                beans.add(list);
                list.clear();
                a++;
                list.add(result.get(i));
            }
        }
        recycleSelectseat.setLayoutManager(new LinearLayoutManager(SelectCinemaSeatActivity.this, LinearLayoutManager.VERTICAL, false));
        XuanZuoAdapter adapter = new XuanZuoAdapter(R.layout.xuanzuo_item, beans);
        recycleSelectseat.setAdapter(adapter);
        adapter.setCallBack(new XuanZuoAdapter.CallBack() {
            @Override
            public void getBack(String s) {
                seat = s;
                Toast.makeText(SelectCinemaSeatActivity.this, s, Toast.LENGTH_SHORT).show();
                for (int i = 0; i < result.size(); i++) {
                    if (result.get(i).getStatus() == 3) {
                        sum++;
                    }
                }
                //设置价格
                if (sum != 0) {
                    zf = sum * fare;
                    btnPurchaseOrder.setText("支付￥:" + sum * fare + "元");
                    btnPurchaseOrder.setVisibility(View.VISIBLE);
                    btnSelectseat.setVisibility(View.INVISIBLE);
                    sum = 0;
                } else if (sum == 0) {
                    btnSelectseat.setVisibility(View.VISIBLE);
                    btnPurchaseOrder.setVisibility(View.INVISIBLE);
                }
            }
        });

    }

    @Override
    public void getFilmSchedulingView(String schedString) {
        Gson gson = new Gson();
        CinemaSchedBean cinemaSchedBean = gson.fromJson(schedString, CinemaSchedBean.class);
        ArrayList<CinemaSchedBean.ResultBean> schedBeanResult = (ArrayList<CinemaSchedBean.ResultBean>) cinemaSchedBean.getResult();

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(SelectCinemaSeatActivity.this, LinearLayoutManager.HORIZONTAL, false);
        recycleSelectSchedseat.setLayoutManager(linearLayoutManager);

        SelectSchedAdapter selectSchedAdapter = new SelectSchedAdapter(R.layout.item_selectschedadapter, schedBeanResult);
        recycleSelectSchedseat.setAdapter(selectSchedAdapter);

        selectSchedAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                SharedPreferences dsp = getSharedPreferences("selectseat", MODE_PRIVATE);
                int schallId = dsp.getInt("schallId", 0);
                mPresenter.requestStudioSeatInfo(schallId);
                fare = schedBeanResult.get(position).getFare();
            }
        });
    }

    @Override
    public void getFilmPayTicketXiaView(String payTicketString) {
        Gson gson = new Gson();
        FilmPayTicketXiaBean filmPayTicketXiaBean = gson.fromJson(payTicketString, FilmPayTicketXiaBean.class);
        orderId = filmPayTicketXiaBean.getOrderId();
        if (payTicketString.indexOf("0000") != -1) {
            Dialog dialog = new Dialog(SelectCinemaSeatActivity.this, R.style.DialogTheme);
            //设置布局
            View inflate = View.inflate(SelectCinemaSeatActivity.this, R.layout.popzhifuway, null);
            Button btn_popprice = inflate.findViewById(R.id.btn_popprice);
            CheckBox cb_weixin = inflate.findViewById(R.id.cb_weixin);
            CheckBox cb_zhifubao = inflate.findViewById(R.id.cb_zhifubao);
            dialog.setContentView(inflate);
            Window window = dialog.getWindow();
            //设置弹出位置
            window.setGravity(Gravity.BOTTOM);
            window.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            btn_popprice.setText("立即支付");
            dialog.show();
            cb_weixin.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ApiService apiService = RetrofitUtils.getInstance().createService(ApiService.class);
                    apiService.obtainPayFilmInfo(userId, sessionId, 1, orderId)
                            .subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribe(new Consumer<ResponseBody>() {
                                @Override
                                public void accept(ResponseBody body) throws Exception {
                                    String weiString = body.string();
                                    Gson weigson = new Gson();
                                    PayFilmBean payFilmBean = weigson.fromJson(weiString, PayFilmBean.class);
                                    PayReq req = new PayReq();
                                    req.appId = payFilmBean.getAppId();
                                    req.partnerId = payFilmBean.getPartnerId();
                                    req.prepayId = payFilmBean.getPrepayId();
                                    req.nonceStr = payFilmBean.getNonceStr();
                                    req.timeStamp = payFilmBean.getTimeStamp();
                                    req.packageValue = payFilmBean.getPackageValue();
                                    req.sign = payFilmBean.getSign();
                                    req.extData = "app data"; // optional
                                    App.api.sendReq(req);
                                }
                            });
                }
            });
            cb_zhifubao.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    RetrofitUtils.getInstance()
                            .createService(ApiService.class)
                            .obtainPayFilmInfo(userId,sessionId,2,orderId)
                            .subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribe(new Consumer<ResponseBody>() {
                                @Override
                                public void accept(ResponseBody responseBody) throws Exception {
                                    final String zhifubaoInfo = responseBody.string();
                                    Gson gson1 = new Gson();
                                    final Alipay list = gson1.fromJson(zhifubaoInfo, Alipay.class);
                                    new Thread(new Runnable() {
                                        @Override
                                        public void run() {
                                            PayTask alipay = new PayTask(SelectCinemaSeatActivity.this);
                                            Map<String, String> result = alipay.payV2(list.getResult(), true);
                                            Message msg = new Message();
                                            msg.what = SDK_PAY_FLAG;
                                            msg.obj = result;
                                            mHandler.sendMessage(msg);
                                        }
                                    }).start();
                                    dialog.dismiss();

                                }
                            });
                }
            });
        }
        Toast.makeText(this, payTicketString, Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void initData() {
        SharedPreferences tsp = getSharedPreferences("config", MODE_PRIVATE);
        userId = tsp.getInt("userId", 0);
        sessionId = tsp.getString("sessionId", "");
        int movieId = tsp.getInt("movieId", 0);
        Intent intent = getIntent();
        int cinemaId = intent.getIntExtra("cinemaId", 0);
        mPresenter.requestSelectHeraldSeatInfo(movieId);
        mPresenter.requestFilmSchedulingInfo(movieId, cinemaId);
        mPresenter.requestStudioSeatInfo(hallId);
    }

    @Override
    protected SelectCinemaSeatPresenter<SelectCinemaSeatContract.SelectCinemaSeatView> createPresenter() {
        return new SelectCinemaSeatPresenter<>();
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_selectcinemaseat;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick({R.id.selectfilmseat_fanhui, R.id.cb_cinemaseatkexuan, R.id.cb_cinemaseatyishou, R.id.cb_cinemaseat_xuanzhong, R.id.btn_purchaseOrder})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.selectfilmseat_fanhui:
                finish();
                break;
            case R.id.cb_cinemaseatkexuan:
                break;
            case R.id.cb_cinemaseatyishou:
                break;
            case R.id.cb_cinemaseat_xuanzhong:
                break;
            case R.id.btn_purchaseOrder:
                SharedPreferences dsp = getSharedPreferences("selectseat", MODE_PRIVATE);
                int id = dsp.getInt("schallId", 0);
                String scaseat = dsp.getString("scaseat", "");
                String sign = MD5(userId + "" + id + "movie");
                Log.i("gg", "购票下单签名:" + sign);
                mPresenter.requestFilmPayTicketXiaInfo(userId, sessionId, id, scaseat, sign);
                break;
        }
    }

    //MD5加密
    public static String MD5(String sourceStr) {
        String result = "";
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(sourceStr.getBytes());
            byte b[] = md.digest();
            int i;
            StringBuffer buf = new StringBuffer("");
            for (int offset = 0; offset < b.length; offset++) {
                i = b[offset];
                if (i < 0)
                    i += 256;
                if (i < 16)
                    buf.append("0");
                buf.append(Integer.toHexString(i));
            }
            result = buf.toString();
        } catch (NoSuchAlgorithmException e) {
            System.out.println(e);
        }
        return result;
    }
    @SuppressLint("HandlerLeak")
    private Handler mHandler = new Handler() {
        @SuppressWarnings("unused")
        public void handleMessage(Message msg) {
           /* @SuppressWarnings("unchecked")
            AuthResult authResult = new AuthResult((Map<String, String>) msg.obj, true);
            String resultStatus = authResult.getResultStatus();

            // 判断resultStatus 为“9000”且result_code
            // 为“200”则代表授权成功，具体状态码代表含义可参考授权接口文档
            if (TextUtils.equals(resultStatus, "9000") && TextUtils.equals(authResult.getResultCode(), "200")) {
                // 获取alipay_open_id，调支付时作为参数extern_token 的value
                // 传入，则支付账户为该授权账户
                //Toast.makeText(MainActivity.this, getString(R.string.auth_success) + authResult, Toast.LENGTH_SHORT).show();
            } else {
                // 其他状态值则为授权失败
                //Toast.makeText(MainActivity.this, getString(R.string.auth_failed) + authResult, Toast.LENGTH_SHORT).show();
            }*/
        }
    };
}
