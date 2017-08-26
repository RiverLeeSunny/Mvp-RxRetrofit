package top.riversunny.mvp_rxretrofit.mvp;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by lijiang on 2017/8/19.
 */

public abstract class MvpBaseActivity <V,T extends MvpBasePresenter> extends AppCompatActivity {
    private static final String TAG = "TAG";
    //presenter对象
    private T mPresenter;

    protected ProgressDialog progressDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //创建presenter
        mPresenter = createPresenter();
        //view与presenter建立关联
        mPresenter.attachView((V)this);
        Log.i(TAG, "attachView-mPresenter="+mPresenter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i(TAG, "onDestroy()-mPresenter="+mPresenter);
        mPresenter.detachView();
    }

    protected abstract  T createPresenter();
}

