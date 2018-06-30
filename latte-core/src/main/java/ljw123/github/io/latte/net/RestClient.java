package ljw123.github.io.latte.net;

import android.content.Context;

import java.io.File;
import java.util.WeakHashMap;

import ljw123.github.io.latte.net.callback.IError;
import ljw123.github.io.latte.net.callback.IFailure;
import ljw123.github.io.latte.net.callback.IRequest;
import ljw123.github.io.latte.net.callback.ISuccess;
import ljw123.github.io.latte.net.callback.RequestCallback;
import ljw123.github.io.latte.net.download.DownloadHandler;
import ljw123.github.io.latte.ui.LatteLoader;
import ljw123.github.io.latte.ui.LoaderStyle;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;

/**
 * @author xiaofo on 2018/6/22.
 */

public class RestClient {
    private static final WeakHashMap<String, Object> PARAMS = RestCreator.getParams();
    private final String URL;
    private final IRequest REQUEST;
    /**
     * 下载文件地址
     */
    private final String DOWNLOAD_DIR;
    /**
     * 下载文件后缀名
     */
    private final String EXTENSION;
    private final String NAME;
    private final ISuccess SUCCESS;
    private final IFailure FAILURE;
    private final IError ERROR;
    private final RequestBody BODY;
    private final Context CONTEXT;
    private final LoaderStyle LOADERSTYLE;
    private final File FILE;


    RestClient(String url,
               WeakHashMap<String, Object> params,
               IRequest request,
               ISuccess success,
               IFailure failure,
               IError error,
               RequestBody body,
               File file,
               Context context,
               LoaderStyle loaderStyle, String download_dir, String extension, String name) {
        this.URL = url;
        this.CONTEXT = context;
        this.LOADERSTYLE = loaderStyle;
        FILE = file;
        DOWNLOAD_DIR = download_dir;
        EXTENSION = extension;
        NAME = name;
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
            case POST_RAW:
                call = restService.postRaw(URL, BODY);
                break;
            case PUT:
                call = restService.put(URL, PARAMS);
                break;
            case PUT_RAW:
                call = restService.putRaw(URL, BODY);
                break;
            case DELETE:
                call = restService.delete(URL, PARAMS);
                break;
            case UPLOAD:
                RequestBody requestBody = RequestBody.create(MediaType.parse(MultipartBody.FORM.toString()), FILE);
                MultipartBody.Part part = MultipartBody.Part.createFormData("file", FILE.getName(), requestBody);
                restService.upload(URL, part);
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
        if (BODY == null) {
            request(HttpMethod.POST);
        } else {
            if (!PARAMS.isEmpty()) {
                throw new RuntimeException(" params must be null");
            }
            request(HttpMethod.POST_RAW);
        }
    }

    public final void put() {
        if (BODY == null) {
            request(HttpMethod.PUT);
        } else {
            if (!PARAMS.isEmpty()) {
                throw new RuntimeException(" params must be null");
            }
            request(HttpMethod.PUT_RAW);
        }
    }

    public final void delete() {
        request(HttpMethod.DELETE);
    }
    public final void downlaod(){
new DownloadHandler(URL,REQUEST,DOWNLOAD_DIR,EXTENSION,NAME,SUCCESS,FAILURE,ERROR)
        .handleDownload();
    }
}
