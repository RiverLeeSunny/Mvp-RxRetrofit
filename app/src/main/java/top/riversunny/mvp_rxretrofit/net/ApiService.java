package top.riversunny.mvp_rxretrofit.net;


import java.util.ArrayList;

import retrofit2.http.GET;
import top.riversunny.mvp_rxretrofit.model.TopicModel;
import rx.Observable;


/**
 * Created by lijiang on 2017/8/24.
 */

public interface ApiService {
    @GET("topics/hot.json")
    Observable<ArrayList<TopicModel>> getHotTopics();
}
