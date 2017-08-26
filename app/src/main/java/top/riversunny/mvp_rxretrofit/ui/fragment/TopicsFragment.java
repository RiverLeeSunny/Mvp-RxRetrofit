package top.riversunny.mvp_rxretrofit.ui.fragment;

import android.app.Activity;
import android.app.ProgressDialog;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.Bind;
import butterknife.OnClick;
import butterknife.ButterKnife;
import top.riversunny.mvp_rxretrofit.R;
import top.riversunny.mvp_rxretrofit.mvp.MvpBaseFragment;
import top.riversunny.mvp_rxretrofit.ui.presenter.TopicsPresenter;


/**
 * Created by lijiang on 2017/8/22.
 */

public class TopicsFragment extends MvpBaseFragment<ITopicsView,TopicsPresenter> implements ITopicsView {

    public static final String TAG = "TopicsFragment";
    private TopicsPresenter mPresenter;
    protected ProgressDialog progressDialog;
    @Bind(R.id.click_me_BN)
    Button clickMeBN;
    @Bind(R.id.result_TV)
    TextView resultTV;
    @Bind(R.id.swipeRefreshLayout)
    SwipeRefreshLayout swipeRefreshLayout;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        progressDialog = new ProgressDialog(getActivity());
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_topics, container, false);
        ButterKnife.bind(this, rootView);
        swipeRefreshLayout.setColorSchemeColors(Color.BLUE, Color.GREEN, Color.RED, Color.YELLOW);
        swipeRefreshLayout.setEnabled(false);
        resultTV.setMovementMethod(ScrollingMovementMethod.getInstance());
        return rootView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        //mPresenter.getHotTopics();

    }

    private void requestTopics(boolean refresh) {

    }

    public void setQuestData(String string){
        resultTV.setText(string);
    }


    @OnClick(R.id.click_me_BN)
    public void onClick() {
        mPresenter.getHotTopics();
    }


    @Override
    protected TopicsPresenter createPresenter() {
        mPresenter=new TopicsPresenter(this);
        return mPresenter;
    }

    @Override
    public void showLoading() {
        swipeRefreshLayout.setRefreshing(true);

    }

    @Override
    public void hideLoading() {
        swipeRefreshLayout.setRefreshing(false);

    }

}
