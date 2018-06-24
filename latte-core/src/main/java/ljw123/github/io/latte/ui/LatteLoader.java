package ljw123.github.io.latte.ui;

import android.content.Context;
import android.support.v7.app.AppCompatDialog;
import android.view.Window;
import android.view.WindowManager;

import com.wang.avi.AVLoadingIndicatorView;

import java.util.ArrayList;

import ljw123.github.io.latte.R;
import ljw123.github.io.latte.util.DeimenUtil;

/**
 * @author xiaofo on 2018/6/24.
 */

public class LatteLoader {

    public static final int LOADER_SIZE_SCALE = 8;
    private static final ArrayList<AppCompatDialog> LOADERS = new ArrayList<>();

    public static void showLoading(Context context) {
        showLoading(context, getDefaultType().name());
    }

    public static void showLoading(Context context, String type) {
        AppCompatDialog appCompatDialog = new AppCompatDialog(context, R.style.dialog);
        AVLoadingIndicatorView avLoadingIndicatorView = LoaderCreator.create(context, type);
        appCompatDialog.setContentView(avLoadingIndicatorView);
        Window window = appCompatDialog.getWindow();
        if (window != null) {
            WindowManager.LayoutParams attributes = window.getAttributes();
            attributes.width = DeimenUtil.getScreenWidth() / LOADER_SIZE_SCALE;
            attributes.height = DeimenUtil.getScreenHeight() / LOADER_SIZE_SCALE;
        }
        LOADERS.add(appCompatDialog);
        appCompatDialog.show();
    }

    public static LoaderStyle getDefaultType() {
        return LoaderStyle.BallClipRotatePulseIndicator;
    }

    public static void showLoading(Context context, Enum<LoaderStyle> loaderStyle) {
        showLoading(context, loaderStyle.name());
    }

    public static void stopLoading() {
        if (LOADERS != null) {
            for (AppCompatDialog loader : LOADERS) {
                if (loader != null) {
                    if (loader.isShowing()) {
//                        该方法可以执行一些cancel回调
                        loader.cancel();
//                        loader直接消失,无回调可执行
//                        loader.dismiss();
                    }
                }
            }
        }
    }
}
