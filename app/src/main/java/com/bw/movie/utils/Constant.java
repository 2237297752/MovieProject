package com.bw.movie.utils;


import android.os.Environment;

import com.bw.movie.view.App;

public class Constant {
    public final static String APP_ROOT_PATH = Environment.getExternalStorageDirectory().getAbsolutePath() + "/" + App.getsAppContext().getPackageName();
    public final static String DOWNLOAD_DIR = "/downlaod/";


}
