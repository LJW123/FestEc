package ljw123.github.io.festec.example;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import ljw123.github.io.latte.delegates.LatteDelegate;
import ljw123.github.io.latte.net.RestClient;
import ljw123.github.io.latte.net.callback.IFailure;
import ljw123.github.io.latte.net.callback.IRequest;
import ljw123.github.io.latte.net.callback.ISuccess;

/**
 * @author xiaofo on 2018/6/22.
 */

public class ExampleDelegate extends LatteDelegate {
    @Override
    public Object setLayout() {
        return R.layout.delegate_example;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
        testRestClient();
    }

    void testRestClient() {
        RestClient.builder()
                .url("http://www.baidu.com")
                .loader(getContext())
                .request(new IRequest() {
                    @Override
                    public void onRequestStart() {
//                        showToast("start");

                    }

                    @Override
                    public void onRequestEnd() {
                        showToast("end");

                    }
                })
                .success(new ISuccess() {
                    @Override
                    public void onSuccess(String response) {
//                        showToast(response);
                    }
                })
                .failure(new IFailure() {
                    @Override
                    public void onFailure() {
                        showToast("failure");
                    }
                })
                .build()
                .get();
    }

}
