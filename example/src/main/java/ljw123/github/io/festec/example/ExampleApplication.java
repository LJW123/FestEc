package ljw123.github.io.festec.example;

import android.app.Application;

import com.joanzapata.iconify.fonts.FontAwesomeModule;

import ljw123.github.io.latte.app.Latte;
import ljw123.github.io.latte.ec.icon.FontEcModule;

/**
 * @author xiaofo on 2018/6/21.
 */

public class ExampleApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Latte.init(this)
//                .withApiHost("127.0.0.1/")
                .withIcon(new FontAwesomeModule())
                .withIcon(new FontEcModule())
                .configure();
    }
}
