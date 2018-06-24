package ljw123.github.io.latte.ui;

import android.content.Context;
import android.text.TextUtils;

import com.wang.avi.AVLoadingIndicatorView;
import com.wang.avi.Indicator;

import java.util.WeakHashMap;

/**
 * @author xiaofo on 2018/6/24.
 */

public class LoaderCreator {
    private static final WeakHashMap<String, Indicator> LOADING_MAP = new WeakHashMap<>();

     static AVLoadingIndicatorView create(Context context, String type) {
        AVLoadingIndicatorView avLoadingIndicatorView = new AVLoadingIndicatorView(context);
        if (LOADING_MAP.get(type) == null) {
            Indicator indicator = getIndecator(type);
            LOADING_MAP.put(type, indicator);
        }
        avLoadingIndicatorView.setIndicator(LOADING_MAP.get(type));
        return avLoadingIndicatorView;
    }

    private static Indicator getIndecator(String name) {
        if (TextUtils.isEmpty(name)) {
            return null;
        }
        StringBuilder packageName = new StringBuilder();
        if (!name.contains(".")) {
//            如果传入的不是全类名 那么就拼接成全类名
            packageName.append(AVLoadingIndicatorView.class.getPackage().getName())
                    .append(".indicators")
                    .append(".")
            ;
        }
        packageName.append(name);
        try {
            Class<?> aClass = Class.forName(packageName.toString());
            return (Indicator) aClass.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
