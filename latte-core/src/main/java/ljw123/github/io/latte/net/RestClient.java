package ljw123.github.io.latte.net;

import android.content.Context;

import java.util.WeakHashMap;

import ljw123.github.io.latte.net.callback.IError;
import ljw123.github.io.latte.net.callback.IFailure;
import ljw123.github.io.latte.net.callback.IRequest;
import ljw123.github.io.latte.net.callback.ISuccess;
import ljw123.github.io.latte.net.callback.RequestCallback;
import ljw123.github.io.latte.ui.LatteLoader;
import ljw123.github.io.latte.ui.LoaderStyle;
import okhttp3.RequestBody;
import retrofit2.Call;

/**
 * @author xiaofo on 2018/6/22.
 */

public class RestClient {
    private static final WeakHashMap<String, Object> PARAMS = RestCreator.getParams();
    private final String URL;
    private final IRequest REQUEST;
    private final ISuccess SUCCESS;
    private final IFailure FAILURE;
    private final IError ERROR;
    private final RequestBody BODY;
    private final Context CONTEXT;
    private final LoaderStyle LOADERSTYLE;

    RestClient(String url,
               WeakHashMap<String, Object> params,
               IRequest request,
               ISuccess success,
               IFailure failure,
               IError error,
               RequestBody body,
               Context context,
               LoaderStyle loaderStyle) {
        this.URL = url;
        this.CONTEXT = context;
        this.LOADERSTYLE = loaderStyle;
//        TODO 参数  静态的 每次都putAll 不会造成参数异常吗?
        PARAMS.putAll(params);
        this.REQUEST = request;
        this.SUCCESS = success;
        this.FAILURE = failure;
        this.ERROR = error;
        this.BODY = body;
    }

    public static RestClientBuilder builder() {
        return new RestClientBuilder();
    }

    public final void get() {
        request(HttpMethod.GET);
    }

    private void request(HttpMethod method) {
        RestService restService = RestCreator.RestServiceHolder.REST_SERVICE;
        Call<String> call = null;
        if (REQUEST != null) {
            REQUEST.onRequestStart();
        }
        if (LOADERSTYLE != null) {
            LatteLoader.showLoading(CONTEXT, LOADERSTYLE);
        }
        switch (method) {
            case GET:
                call = restService.get(URL, PARAMS);
                break;
            case POST:
                call = restService.post(URL, PARAMS);
                break;
            case PUT:
                call = restService.put(URL, PARAMS);
                break;
            case DELETE:
                call = restService.delete(URL, PARAMS);
                break;
            default:
                break;
        }
        if (call != null) {
            call.enqueue(getRequestCallback());
        }

    }

    private RequestCallback getRequestCallback() {
        return new RequestCallback(REQUEST, SUCCESS, FAILURE, ERROR, LOADERSTYLE);
    }

    public final void post() {
        request(HttpMethod.POST);
    }

    public final void put() {
        request(HttpMethod.PUT);
    }

    public final void delete() {
        request(HttpMethod.DELETE);
    }
}
