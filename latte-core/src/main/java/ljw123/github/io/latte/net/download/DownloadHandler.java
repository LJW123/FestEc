package ljw123.github.io.latte.net.download;

import android.os.AsyncTask;

import java.util.WeakHashMap;

import ljw123.github.io.latte.net.RestCreator;
import ljw123.github.io.latte.net.callback.IError;
import ljw123.github.io.latte.net.callback.IFailure;
import ljw123.github.io.latte.net.callback.IRequest;
import ljw123.github.io.latte.net.callback.ISuccess;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * @author xiaofo on 2018/6/24.
 */

public class DownloadHandler {
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

    public DownloadHandler(String url, IRequest request, String downloadDir, String extension, String name, ISuccess success, IFailure failure, IError error) {
        this.URL = url;
        this.REQUEST = request;
        this.DOWNLOAD_DIR = downloadDir;
        this.EXTENSION = extension;
        this.NAME = name;
        this.SUCCESS = success;
        this.FAILURE = failure;
        this.ERROR = error;
    }
    public void handleDownload(){
        if (REQUEST!=null) {
            REQUEST.onRequestStart();
        }
        RestCreator.RestServiceHolder.REST_SERVICE
                .download(URL,PARAMS)
                .enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        if (response.isSuccessful()) {
                            final ResponseBody body = response.body();
                            SaveFileTask saveFileTask = new SaveFileTask(SUCCESS, REQUEST);
                            saveFileTask.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR,DOWNLOAD_DIR,response,NAME);
                            if (saveFileTask.isCancelled()) {
                                if (REQUEST!=null) {
                                    REQUEST.onRequestEnd();
                                }
                            }

                        }else{
                            if (ERROR!=null) {
                                ERROR.onError(response.code(),response.message());
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        if (FAILURE!=null) {
                            FAILURE.onFailure();
                        }
                    }
                });
    }
}
