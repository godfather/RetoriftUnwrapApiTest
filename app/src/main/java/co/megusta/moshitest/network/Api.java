package co.megusta.moshitest.network;

import retrofit2.Retrofit;
import retrofit2.converter.moshi.MoshiConverterFactory;

public class Api {
    private static final String BASE_URL = "https://livreto-api.beta.livreto.co/v1/";
    private static  Retrofit retrofit;
    private static NetAPI netAPI;

    public static NetAPI getInstance() {
        if(netAPI != null) {
            return netAPI;
        }

        if(retrofit == null) {
            initializeRetrofit();
        }

        netAPI = retrofit.create(NetAPI.class);
        return netAPI;
    }


    private static void initializeRetrofit() {
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
//                .addConverterFactory(MoshiConverterFactory.create())
                .build();
    }

    private Api() {}

}
