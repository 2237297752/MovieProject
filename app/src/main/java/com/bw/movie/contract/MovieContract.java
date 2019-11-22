package com.bw.movie.contract;


public interface MovieContract {

     interface MovieView{
         void getRecommendView(String recommendString);
         void getNearbyView(String nearbyString);
         void getDistrictView(String locationString);
         void getDistrictCinemaView(String locationCinemaString);
    }

     interface MovieModel{
         void requestRecommendData(int count, int page, CallRecommendBack callRecommendBack);
         interface CallRecommendBack{
             void getRecommendResult(String recommendString);
        }

         void requestNearbyData(int userId, String sessionId, int page, int count, CallNearbyBack callNearbyBack);
         interface CallNearbyBack{
             void getNearbyResult(String nearbyString);
        }

         void requestDistrictData(CallDistrictBack callDistrictBack);
         interface CallDistrictBack{
             void getDistrictResult(String locationString);
        }

         void requestDistrictCinemaData(int regionId, CallDistrictCinemaBack callDistrictCinemaBack);
         interface CallDistrictCinemaBack{
             void getDistrictCinemaResult(String locationCinemaString);
        }
    }
}
