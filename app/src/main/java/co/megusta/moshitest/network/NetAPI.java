package co.megusta.moshitest.network;

import java.util.List;

import co.megusta.moshitest.models.ApiWrapper;
import co.megusta.moshitest.models.Collection;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;

public interface NetAPI {
    @GET("me/collections")
    Call<ResponseBody> getCollections(@Header("Authorization") String auth);
}
