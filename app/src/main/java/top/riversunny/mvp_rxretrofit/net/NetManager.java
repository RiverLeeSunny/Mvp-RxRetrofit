package top.riversunny.mvp_rxretrofit.net;

import android.util.Log;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.CallAdapter;
import retrofit2.Converter;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import top.riversunny.mvp_rxretrofit.model.TopicModel;

/**
 * Created by lijiang on 2017/8/24.
 */

public class NetManager {
    private static final String TAG = "NetManager";

    public static final String BASE_URL = "http://www.v2ex.com/api/";

    private static final int DEFAULT_TIMEOUT = 5;
//    private static OkHttpClient okHttpClient = new OkHttpClient();
//    private static Converter.Factory gsonConverterFactory = GsonConverterFactory.create();
//    private static CallAdapter.Factory rxJavaCallAdapterFactory = RxJavaCallAdapterFactory.create();

    private Retrofit retrofit;
    private ApiService apiService;

    //构造方法私有
    private NetManager() {
        //手动创建一个OkHttpClient并设置超时时间
        Log.d(TAG, "NetManager() begin");
        OkHttpClient.Builder httpClientBuilder = new OkHttpClient.Builder();
        httpClientBuilder.connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS);
        Log.d(TAG, "NetManager() begin 111111");
        retrofit = new Retrofit.Builder()
                .client(httpClientBuilder.build())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .baseUrl(BASE_URL)
                .build();
//         retrofit = new Retrofit.Builder()
//                .client(okHttpClient)
//                .baseUrl("http://www.v2ex.com/api")
//                .addConverterFactory(gsonConverterFactory)
//                .addCallAdapterFactory(rxJavaCallAdapterFactory)
//                .build();
        Log.d(TAG, "NetManager() begin 222222");
        apiService = retrofit.create(ApiService.class);
        Log.d(TAG, "NetManager() end");
    }

    //在访问HttpMethods时创建单例
    private static class SingletonHolder{
        private static final NetManager INSTANCE = new NetManager();
    }

    //获取单例
    public static NetManager getInstance(){
        Log.d(TAG, "getInstance");
        return SingletonHolder.INSTANCE;
    }

    public void getHotTopics(Subscriber<ArrayList<TopicModel>> subscriber){
        Log.d(TAG, "getHotTopics");
        apiService.getHotTopics()
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }
}
