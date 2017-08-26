package top.riversunny.mvp_rxretrofit.mvp;

/**
 * Created by lijiang on 2017/8/19.
 */

public interface Presenter<V> {
    /**
     * Presenter与View建立连接
     *
     * @param mvpView 与此Presenter相对应的View
     */
    void attachView(V mvpView);

    /**
     * Presenter与View连接断开
     */
    void detachView();
}
