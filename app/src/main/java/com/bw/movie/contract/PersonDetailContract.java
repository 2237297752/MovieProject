package com.bw.movie.contract;

import java.util.List;

import okhttp3.MultipartBody;

public interface PersonDetailContract {
    /**
     * v层
     */
    public interface PersonDetailView{
        public void getPersonDetailTouXiangView(String touXiangString);
    }
    /**
     * m层
     */
    public interface PersonDetailModel{
        public void requestPersonDetailTouXiangData(int userId, String sessionId, List<MultipartBody.Part> image, CallPersonDetailTouXiangBack callPersonDetailTouXiangBack);
        public interface CallPersonDetailTouXiangBack{
            public void getPersonDetailTouXiangResult(String touXiangString);
        }
    }
}
