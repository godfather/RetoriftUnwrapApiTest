package co.megusta.unwrapapitest.network;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;

public interface NetAPI {
    @GET("me/collections")
    Call<ResponseBody> getCollections(@Header("Authorization") String auth);
}
