package ljw123.github.io.latte.app;

import android.content.Context;

import java.util.HashMap;

/**
 * @author xiaofo on 2018/6/21.
 */

public final class Latte {
  public static Configurator init(Context context){
      getConfigurations().put(ConfigType.APPLICATION_CONTEXT.name(),context.getApplicationContext());
      return Configurator.getInstance();
  }

    public static HashMap<String, Object> getConfigurations() {
        return Configurator.getInstance().getLatteConfigs();
    }

    public static Context getApplicationContext(){
      return (Context) getConfigurations().get(ConfigType.APPLICATION_CONTEXT.name());
    }
}
