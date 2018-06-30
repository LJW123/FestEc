package ljw123.github.io.latte.util;

import android.content.res.Resources;
import android.util.DisplayMetrics;

import ljw123.github.io.latte.app.Latte;

/**
 * @author xiaofo on 2018/6/24.
 */

public class DeimenUtil {
    /**
     * 获取屏幕宽度
     *
     * @return 屏幕宽度 单位:像素
     */
    public static int getScreenWidth() {
        Resources resources = Latte.getApplicationContext().getResources();
        DisplayMetrics displayMetrics = resources.getDisplayMetrics();
        return displayMetrics.widthPixels;
    }

    /**
     * 获取屏幕高度
     *
     * @return 屏幕高度 单位:像素
     */
    public static int getScreenHeight() {
        Resources resources = Latte.getApplicationContext().getResources();
        DisplayMetrics displayMetrics = resources.getDisplayMetrics();
        return displayMetrics.heightPixels;
    }
}
