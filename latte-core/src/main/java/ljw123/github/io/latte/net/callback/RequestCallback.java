package ljw123.github.io.latte.net.callback;

import android.os.Handler;

import ljw123.github.io.latte.ui.LatteLoader;
import ljw123.github.io.latte.ui.LoaderStyle;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * @author xiaofo on 2018/6/23.
 */

public class RequestCallback implements Callback<String> {
    public static Handler handler = new Handler();
    private final IRequest mRequest;
    private final ISuccess mSuccess;
    private final IFailure mFailure;
    private final IError mError;
    private final LoaderStyle LOADER_STYLE;

    public RequestCallback(IRequest request, ISuccess success, IFailure failure, IError error, LoaderStyle loaderstyle) {
        this.mRequest = request;
        this.mSuccess = success;
        this.mFailure = failure;
        this.mError = error;
        this.LOADER_STYLE = loaderstyle;
    }

    @Override
    public void onResponse(Call<String> call, Response<String> response) {
        if (response.isSuccessful()) {
            if (call.isExecuted()) {
                if (mSuccess != null) {
                    mSuccess.onSuccess(response.body());
                }
            }
        } else {
            if (mError != null) {
                mError.onError(response.code(), response.message());
            }
        }
        onRequestFinish();
    }

    @Override
    public void onFailure(Call call, Throwable t) {
        if (mFailure != null) {
            mFailure.onFailure();
        }
        if (mRequest != null) {
            mRequest.onRequestEnd();
        }
        onRequestFinish();
    }

    void onRequestFinish() {
        if (LOADER_STYLE != null) {
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    LatteLoader.stopLoading();
                }
            }, 1000);
        }
    }


}
