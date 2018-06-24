package ljw123.github.io.latte.delegates;

import android.content.Context;
import android.widget.Toast;

import ljw123.github.io.latte.app.Latte;

/**
 * @author xiaofo on 2018/6/22.
 */

public abstract class LatteDelegate extends PermissionCheckerDelegate {
   public void showToast(String msg){
        Toast.makeText((Context) Latte.getApplication(), msg, Toast.LENGTH_SHORT).show();

    }
}
