package ljw123.github.io.latte.net;

import android.content.Context;

import java.io.File;
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
    private String mUrl = null;
    private IRequest mRequest = null;
    private ISuccess mSuccess = null;
    private IFailure mFailure = null;
    private IError mError = null;
    private RequestBody mBody = null;
    private Context mContext = null;
    private LoaderStyle mLoaderStyle = null;
    private File mFile = null;
    private String mDownloadDir = null;
    private String mExtension = null;
    private String mName = null;

    /**
     * 不带修饰符 同包内可以使用new关键字 不同包不能使用new关键字
     */
    RestClientBuilder() {
    }

    public RestClientBuilder downloadDir(String downloadDir) {
        mDownloadDir = downloadDir;
        return this;
    }

    public RestClientBuilder mExtension(String extension) {
        mExtension = extension;
        return this;
    }

    public RestClientBuilder name(String name) {
        mName = name;
        return this;
    }

    public RestClientBuilder url(String url) {
        this.mUrl = url;
        return this;
    }

    public RestClientBuilder loader(Context context, LoaderStyle loaderStyle) {
        mContext = context;
        mLoaderStyle = loaderStyle;
        return this;
    }

    public RestClientBuilder loader(Context context) {
        mContext = context;
        mLoaderStyle = LatteLoader.getDefaultType();
        return this;
    }

    public RestClientBuilder params(WeakHashMap<String, Object> params) {
        PARAMS.putAll(params);
        return this;
    }

    public RestClientBuilder file(File file) {
        mFile = file;
        return this;
    }

    public RestClientBuilder file(String filePath) {
        mFile = new File(filePath);
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
        return new RestClient(mUrl, PARAMS, mRequest, mSuccess, mFailure, mError, mBody, mFile,
                mContext, mLoaderStyle, mDownloadDir, mExtension, mName);
    }
}
