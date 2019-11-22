package com.bw.movie.view.activity;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.bigkoo.pickerview.TimePickerView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.request.RequestOptions;
import com.bw.movie.R;
import com.bw.movie.base.BaseActivity;
import com.bw.movie.contract.PersonDetailContract;
import com.bw.movie.model.bean.PersonDetailTouXiangBean;
import com.bw.movie.presenter.PersonDetailPresenter;
import com.google.gson.Gson;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

public class PersonalDetailsActivity extends BaseActivity<PersonDetailContract.PersonDetailView, PersonDetailPresenter<PersonDetailContract.PersonDetailView>> implements PersonDetailContract.PersonDetailView {

    @BindView(R.id.persondetail_fanhi)
    ImageView persondetailFanhi;
    @BindView(R.id.persondetail_name)
    TextView persondetailName;
    @BindView(R.id.persondetail_sex)
    TextView persondetailSex;
    @BindView(R.id.persondetail_data)
    TextView persondetailData;
    @BindView(R.id.persondetail_phone)
    TextView persondetailPhone;
    @BindView(R.id.persondetail_email)
    TextView persondetailEmail;
    @BindView(R.id.persondetail_imgtou)
    ImageView persondetailImgtou;
    private File cameraSavePath;//拍照照片路径
    private Uri uri;//照片uri
    private String photoPath;
    private String photoPath1;
    private  static final int REQUEST_EXTERNAL_STORAGE=1;
    private static String[] PERMISSIONS_STORAGE = {
            "android.permission.READ_EXTERNAL_STORAGE",
            "android.permission.WRITE_EXTERNAL_STORAGE" };

    private int userId;
    private String sessionId;

    @Override
    public void getPersonDetailTouXiangView(String touXiangString) {
        Gson gson = new Gson();
        PersonDetailTouXiangBean touXiangBean = gson.fromJson(touXiangString, PersonDetailTouXiangBean.class);
        if ("0000".equals(touXiangBean.getStatus())) {
            Toast.makeText(this, touXiangBean.getMessage(), Toast.LENGTH_SHORT).show();
            Glide.with(PersonalDetailsActivity.this).load(touXiangBean.getHeadPath()).apply(RequestOptions.bitmapTransform(new CircleCrop())).into(persondetailImgtou);
        }
    }

    @Override
    protected void initData() {
        SharedPreferences sp = getSharedPreferences("config", MODE_PRIVATE);
        int sex = sp.getInt("sex", 0);
        userId = sp.getInt("userId", 0);
        sessionId = sp.getString("sessionId", "");
        String lastLoginTime = sp.getString("lastLoginTime", "");
        String nickName = sp.getString("nickName", "");
        String headPic = sp.getString("headPic", "");
        String email = sp.getString("email", "");
        Glide.with(PersonalDetailsActivity.this).load(headPic).apply(RequestOptions.bitmapTransform(new CircleCrop())).into(persondetailImgtou);
        persondetailName.setText(nickName);
        if (sex == 1) {
            persondetailSex.setText("男");
        } else if (sex == 0) {
            persondetailSex.setText("女");
        }
        Date date = new Date(Long.valueOf(lastLoginTime));
        String dateStr = new SimpleDateFormat("yyyy-MM-dd").format(date);
        persondetailData.setText(dateStr);
        persondetailEmail.setText(email);
    }

    @Override
    protected PersonDetailPresenter<PersonDetailContract.PersonDetailView> createPresenter() {
        return new PersonDetailPresenter<>();
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_personaldetails;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick({R.id.persondetail_fanhi, R.id.persondetail_imgtou, R.id.persondetail_data})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.persondetail_fanhi:
                finish();
                break;
            case R.id.persondetail_imgtou:
                final Dialog dialog = new Dialog(this,R.style.DialogTheme);
                //2、设置布局
                View inflate = View.inflate(this,R.layout.popmyxiangji_layout,null);
                dialog.setContentView(inflate);
                Window window = dialog.getWindow();
                //设置弹出位置
                window.setGravity(Gravity.BOTTOM);
                window.setLayout(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);
                dialog.show();

                dialog.findViewById(R.id.tv_take_photo).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent it = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                        //1.intnet 2.请求码
                        startActivityForResult(it, 1);
                        dialog.dismiss();
                    }
                });

                dialog.findViewById(R.id.tv_take_pic).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(Intent.ACTION_PICK);
                        intent.setType("image/*");
                        startActivityForResult(intent,10);
                        dialog.dismiss();
                    }
                });
                dialog.findViewById(R.id.tv_cancel).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });
                break;
            case R.id.persondetail_data:
                TimePickerView pvTime = new TimePickerView.Builder(this, new TimePickerView.OnTimeSelectListener() {
                    @Override
                    public void onTimeSelect(Date date, View v) {
                        long time = date.getTime();
                        String format = DateFormatUtil.format(time + "");
                        persondetailData.setText(format+"");
                    }
                })
                        .setType(new boolean[]{true, true, true, false, false, false})// 默认全部显示
                        .setCancelText("取消")//取消按钮文字
                        .setSubmitText("确定")//确认按钮文字
                        .setContentSize(12)//滚轮文字大小
//                .setTitleSize(20)//标题文字大小
//                //.setTitleText("Title")//标题文字
//                .setOutSideCancelable(true)//点击屏幕，点在控件外部范围时，是否取消显示
                        .isCyclic(true)//是否循环滚动
                        .setTitleColor(Color.BLACK)//标题文字颜色
                        .setSubmitColor(Color.BLUE)//确定按钮文字颜色
                        .setCancelColor(Color.BLUE)//取消按钮文字颜色
                        //.setTitleBgColor(0xFF666666)//标题背景颜色 Night mode
                        .setBgColor(0xFF333333)//滚轮背景颜色 Night mode
////                .setDate(selectedDate)// 如果不设置的话，默认是系统时间*/
////                .setRangDate(startDate,endDate)//起始终止年月日设定
//                //.setLabel("年","月","日","时","分","秒")//默认设置为年月日时分秒
                        .isCenterLabel(false) //是否只显示中间选中项的label文字，false则每项item全部都带有label。
                        //.isDialog(true)//是否显示为对话框样式
                        .build();
                pvTime.show();
                break;
        }
    }

    public static class DateFormatUtil {
        public static String format(String date) {
            if (TextUtils.isEmpty(date))
                return null;
            SimpleDateFormat format = new SimpleDateFormat("yyyy年MM月dd日");
            Long time = new Long(date);
            return format.format(time);
        }
    }

    //相册：接收回传值
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 10 && resultCode == RESULT_OK) {
            Uri uri = data.getData();
            Glide.with(PersonalDetailsActivity.this).load(uri).apply(RequestOptions.bitmapTransform(new CircleCrop())).into(persondetailImgtou);
            String[] projection = {MediaStore.Images.Media.DATA};
            Cursor cursor = managedQuery(uri, projection, null, null, null);
            cursor.moveToNext();
            String cursorString = cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA));
            File file = new File(cursorString);
            RequestBody requestBody = RequestBody.create(MediaType.parse("multipart/form-data"), file);
            MultipartBody multipartBody = new MultipartBody.Builder().addFormDataPart("image", file.getName(), requestBody).build();
            List<MultipartBody.Part> parts = multipartBody.parts();
            mPresenter.requestPersonDetailTouXiangInfo(userId,sessionId,parts);
        }
        if (requestCode == 1 && resultCode == RESULT_OK) {
            //接受图片
            Bitmap bitmap = data.getParcelableExtra("data");


            //设置图片
            Glide.with(PersonalDetailsActivity.this).load(bitmap).apply(RequestOptions.bitmapTransform(new CircleCrop())).into(persondetailImgtou);
            Uri urixj = Uri.parse(MediaStore.Images.Media.insertImage(getContentResolver(), bitmap, null,null));
            String[] projection = {MediaStore.Images.Media.DATA};
            Cursor cursor = managedQuery(urixj, projection, null, null, null);
            cursor.moveToNext();
            String cursorString = cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA));
            File file = new File(cursorString);
            RequestBody requestBody = RequestBody.create(MediaType.parse("multipart/form-data"), file);
            MultipartBody multipartBody = new MultipartBody.Builder().addFormDataPart("image", file.getName(), requestBody).build();
            List<MultipartBody.Part> parts = multipartBody.parts();
            mPresenter.requestPersonDetailTouXiangInfo(userId,sessionId,parts);
        }
    }

    public static void checkStoragePermissions(Activity activity) {
        try {
            //监测是否有写的权限
            int permission = ActivityCompat.checkSelfPermission(activity,
                    "android.permission.WRITE_EXTERNAL_STORAGE");
            int permission1 = ActivityCompat.checkSelfPermission(activity,
                    "android.permission.READ_EXTERNAL_STORAGE");
            if (permission != PackageManager.PERMISSION_GRANTED) {
                //没有写的权限，去申请写的权限，或弹出对话框
                ActivityCompat.requestPermissions(activity, PERMISSIONS_STORAGE, REQUEST_EXTERNAL_STORAGE);
            }
            if (permission1 != PackageManager.PERMISSION_GRANTED) {
                //没有写的权限，去申请写的权限，或弹出对话框
                ActivityCompat.requestPermissions(activity, PERMISSIONS_STORAGE, REQUEST_EXTERNAL_STORAGE);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public File saveFile(Bitmap bm, String fileName) throws IOException {//将Bitmap类型的图片转化成file类型，便于上传到服务器
        String path = Environment.getExternalStorageDirectory() + "/Ask";
        File dirFile = new File(path);
        if(!dirFile.exists()){
            dirFile.mkdir();
        }
        File myCaptureFile = new File(path + fileName);
        BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(myCaptureFile));
        bm.compress(Bitmap.CompressFormat.JPEG, 80, bos);
        bos.flush();
        bos.close();
        return myCaptureFile;

    }


}
