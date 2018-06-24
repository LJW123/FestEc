package ljw123.github.io.latte.net;

import android.content.Context;

import java.util.WeakHashMap;

import ljw123.github.io.latte.net.callback.IError;
import ljw123.github.io.latte.net.callback.IFailure;
import ljw123.github.io.latte.net.callback.IRequest;
import ljw123.github.io.latte.net.callback.ISuccess;
import ljw123.github.io.latte.ui.LatteLoader;
import ljw123.github.io.latte.ui.LoaderStyle;
import okhttp3.RequestBody;

/**
 * @author xiaofo on 2018/6/22.
 */

public class RestClientBuilder {
    private static final WeakHashMap<String, Object> PARAMS = RestCreator.getParams();
    private String mUrl;
    private IRequest mRequest;
    private ISuccess mSuccess;
    private IFailure mFailure;
    private IError mError;
    private RequestBody mBody;
    private Context mContext;
    private LoaderStyle mLoaderStyle;

    /**
     * 不带修饰符 同包内可以使用new关键字 不同包不能使用new关键字
     */
    RestClientBuilder() {
    }

    public RestClientBuilder url(String url) {
        this.mUrl = url;
        return this;
    }

    public RestClientBuilder loader(Context context,LoaderStyle loaderStyle) {
        mContext = context;
        mLoaderStyle = loaderStyle;
        return this;
    }  public RestClientBuilder loader(Context context) {
        mContext = context;
        mLoaderStyle = LatteLoader.getDefaultType();
        return this;
    }

    public RestClientBuilder params(WeakHashMap<String, Object> params) {
        PARAMS.putAll(params);
        return this;
    }

    public RestClientBuilder params(String key, Object value) {
        PARAMS.put(key, value);
        return this;
    }

    public RestClientBuilder request(IRequest request) {
        this.mRequest = request;
        return this;
    }

    public RestClientBuilder success(ISuccess success) {
        this.mSuccess = success;
        return this;
    }

    public RestClientBuilder failure(IFailure failure) {
        this.mFailure = failure;
        return this;
    }

    public RestClientBuilder error(IError error) {
        this.mError = error;
        return this;
    }

    public RestClientBuilder body(RequestBody body) {
        this.mBody = body;
        return this;
    }

    public RestClient build() {
        return new RestClient(mUrl, PARAMS, mRequest, mSuccess, mFailure, mError, mBody, mContext, mLoaderStyle);
    }
}
