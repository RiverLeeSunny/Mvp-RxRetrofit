package top.riversunny.mvp_rxretrofit.ui.presenter;


import android.support.v4.app.Fragment;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Iterator;

import rx.Subscriber;
import top.riversunny.mvp_rxretrofit.model.TopicModel;
import top.riversunny.mvp_rxretrofit.mvp.MvpBasePresenter;
import top.riversunny.mvp_rxretrofit.net.NetManager;
import top.riversunny.mvp_rxretrofit.ui.MainActivity;
import top.riversunny.mvp_rxretrofit.ui.fragment.ITopicsView;
import top.riversunny.mvp_rxretrofit.ui.fragment.TopicsFragment;

import static android.R.id.list;

/**
 * Created by lijiang on 2017/8/22.
 */

public class TopicsPresenter extends MvpBasePresenter implements ITopicsPresenter{
    private static final String TAG = "TopicsPresenter";
    private ITopicsView iTopicsView;
    private Subscriber subscriber;

    public TopicsPresenter(ITopicsView iTopicsView){
        this.iTopicsView = iTopicsView;
    }

    @Override
    public void getLatestTopics() {

    }

    @Override
    public void getHotTopics() {
        final TopicsFragment fragment = (TopicsFragment)getView();
        fragment.showLoading();
         subscriber = new Subscriber<ArrayList<TopicModel>>() {
            @Override
            public void onCompleted() {
                Log.d(TAG, "onCompleted");
                fragment.hideLoading();

            }

            @Override
            public void onError(Throwable e) {
                Log.d(TAG, e.getMessage());
            }

            @Override
            public void onNext(ArrayList<TopicModel> topicModels) {
                Log.d(TAG,"onNext");
                String s = "";
                Iterator it1 = topicModels.iterator();
                while(it1.hasNext()){
                    TopicModel model = (TopicModel)it1.next();
                    s = s + model.toString();
                }
                fragment.setQuestData(s);
            }

        };
        NetManager.getInstance().getHotTopics(subscriber);

    }
}
