package top.riversunny.mvp_rxretrofit.mvp;

import java.lang.ref.Reference;
import java.lang.ref.WeakReference;

/**
 * Created by lijiang on 2017/8/19.
 */

public abstract class MvpBasePresenter<V> implements Presenter<V> {
    //view接口类型的弱引用
    protected Reference<V> mViewRef;
    //建立关联
    public  void attachView(V view){
        mViewRef = new WeakReference<V>(view);
    }

    public  void detachView(){
        if(mViewRef!=null){
            mViewRef.clear();
            mViewRef =null;
        }
    }

    protected V getView(){
        return  mViewRef.get();
    }

    public boolean isViewAttached(){
        return  mViewRef !=null && mViewRef.get() !=null;
    }

    /**
     * 每次调用业务请求的时候都要先调用方法检查是否与View建立连接，没有则抛出异常
     */
    public void checkViewAttached() {
        if (!isViewAttached()) {
            throw new top.riversunny.mvp_rxretrofit.mvp.MvpBasePresenter.MvpViewNotAttachedException();
        }
    }

    public static class MvpViewNotAttachedException extends RuntimeException {
        public MvpViewNotAttachedException() {
            super("请求数据前请先调用 attachView(View) 方法与View建立连接");
        }
    }
}