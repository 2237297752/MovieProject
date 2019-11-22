package com.bw.movie.callback;

/**
 * Describe：DownloadCallBack
 * Author：fan
 * Data：2019/11/7
 * Time:9:08
 */

public interface DownloadCallBack {

      void onProgress(int progress);

    void onCompleted();

    void onError(String msg);

}
