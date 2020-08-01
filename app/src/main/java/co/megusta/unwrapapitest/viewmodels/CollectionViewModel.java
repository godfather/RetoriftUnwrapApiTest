package co.megusta.unwrapapitest.viewmodels;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import co.megusta.unwrapapitest.models.ApiWrapper;
import co.megusta.unwrapapitest.models.Collection;
import co.megusta.unwrapapitest.network.Api;
import co.megusta.unwrapapitest.network.ApiError;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CollectionViewModel extends ViewModel {
    private static final String TOKEN = "Bearer …";

    private final MutableLiveData<List<Collection>> collections = new MutableLiveData<>();
    private final MutableLiveData<Boolean> loading = new MutableLiveData<>();
    private final MutableLiveData<Boolean> collectionError = new MutableLiveData<>();
    private final MutableLiveData<List<ApiError>> apiErrors = new MutableLiveData<>();

    private Call<ResponseBody> call;

    public CollectionViewModel() {
        fetchCollections();
    }

    public LiveData<List<Collection>> getCollections() {
        return collections;
    }

    public LiveData<Boolean> getLoading() {
        return loading;
    }

    public LiveData<Boolean> getError() {
        return collectionError;
    }

    public LiveData<List<ApiError>> getApiErrorResponse() {
        return apiErrors;
    }

    private void fetchCollections() {
        loading.setValue(true);
        call = Api.getInstance().getCollections(TOKEN);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    collectionError.setValue(false);
                    Gson gson = new Gson();
                    ApiWrapper<List<Collection>> apiResponse = null;

                    apiResponse = gson.fromJson(response.body().string(), new TypeToken<ApiWrapper<List<Collection>>>(){}.getType());
                    apiErrors.setValue(apiResponse.errors);
                    collections.setValue(apiResponse.data);

                    loading.setValue(false);
                    call = null;
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.e(getClass().getSimpleName(), "Error loading repos", t);
                collectionError.setValue(true);
                loading.setValue(false);
                call = null;
            }
        });
    }

    @Override
    protected void onCleared() {
        if (call != null) {
            call.cancel();
            call = null;
        }
    }

}
