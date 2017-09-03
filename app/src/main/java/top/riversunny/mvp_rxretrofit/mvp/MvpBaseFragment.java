package top.riversunny.mvp_rxretrofit.mvp;

import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;

/**
 * Created by lijiang on 2017/8/22.
 */

public abstract class MvpBaseFragment<V,T extends MvpBasePresenter> extends Fragment {
    private static final String TAG = "MvpBaseFragment";
    //presenter对象
    private T mPresenter;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //创建presenter
        mPresenter = createPresenter();
        //view与presenter建立关联
        mPresenter.attachView((V)this);
        Log.i(TAG, "attachView-mPresenter="+mPresenter);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i(TAG, "onDestroy()-mPresenter="+mPresenter);
        mPresenter.detachView();
    }

    protected abstract  T createPresenter();

}
