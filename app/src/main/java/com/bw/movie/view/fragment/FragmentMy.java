package com.bw.movie.view.fragment;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bw.movie.R;
import com.bw.movie.view.activity.PersonalDetailsActivity;
import com.bw.movie.view.activity.ShezhiActivity;
import com.facebook.drawee.view.SimpleDraweeView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class FragmentMy extends Fragment {

    @BindView(R.id.ll_my)
    LinearLayout llMy;
    @BindView(R.id.my_image)
    SimpleDraweeView myImage;
    @BindView(R.id.gengduo_gushi)
    ImageView gengduoGushi;
    @BindView(R.id.gengduo_dianyingpiao)
    ImageView gengduoDianyingpiao;
    @BindView(R.id.my_ll_one)
    LinearLayout myLlOne;
    @BindView(R.id.btn_my_guanzhu)
    LinearLayout btnMyGuanzhu;
    @BindView(R.id.btn_my_yvyue)
    LinearLayout btnMyYvyue;
    @BindView(R.id.btn_goupiaojilu)
    LinearLayout btnGoupiaojilu;
    @BindView(R.id.btn_kanguodedianying)
    LinearLayout btnKanguodedianying;
    @BindView(R.id.btn_my_pinglun)
    LinearLayout btnMyPinglun;
    @BindView(R.id.btn_yijianfankui)
    LinearLayout btnYijianfankui;
    @BindView(R.id.btn_my_shezhi)
    LinearLayout btnMyShezhi;
    Unbinder unbinder;
    @BindView(R.id.myname_card)
    TextView mynameCard;
    @BindView(R.id.card_view)
    CardView cardView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_my, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        SharedPreferences sp = getActivity().getSharedPreferences("config", getActivity().MODE_PRIVATE);
        String nickName = sp.getString("nickName", "");
        String headPic = sp.getString("headPic", "");
        myImage.setImageURI(headPic);
        mynameCard.setText(nickName);

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.card_view, R.id.gengduo_gushi, R.id.gengduo_dianyingpiao, R.id.btn_my_guanzhu, R.id.btn_my_yvyue, R.id.btn_goupiaojilu, R.id.btn_kanguodedianying, R.id.btn_my_pinglun, R.id.btn_yijianfankui, R.id.btn_my_shezhi})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.card_view:
                startActivity(new Intent(getActivity(), PersonalDetailsActivity.class));
                break;
            case R.id.gengduo_gushi:
                break;
            case R.id.gengduo_dianyingpiao:
                break;
            case R.id.btn_my_guanzhu:
                break;
            case R.id.btn_my_yvyue:
                break;
            case R.id.btn_goupiaojilu:
                break;
            case R.id.btn_kanguodedianying:
                break;
            case R.id.btn_my_pinglun:
                break;
            case R.id.btn_yijianfankui:
                break;
            case R.id.btn_my_shezhi:
                startActivity(new Intent(getContext(), ShezhiActivity.class));
                break;
        }
    }
}
