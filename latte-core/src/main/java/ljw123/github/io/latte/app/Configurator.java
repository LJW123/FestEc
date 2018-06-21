package ljw123.github.io.latte.app;

import com.joanzapata.iconify.IconFontDescriptor;
import com.joanzapata.iconify.Iconify;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * @author xiaofo on 2018/6/21.
 */

public class Configurator {
    private static final HashMap<String, Object> LATTE_CONFIGS = new HashMap<>();
    private static final ArrayList<IconFontDescriptor> ICONS = new ArrayList<>();

    private Configurator() {
        LATTE_CONFIGS.put(ConfigType.CONFIG_READY.name(), false);
    }

     static Configurator getInstance() {
        return Holder.INSTANCE;
    }


    final HashMap<String, Object> getLatteConfigs() {
        return LATTE_CONFIGS;
    }


    public final Configurator withApiHost(String host) {
        LATTE_CONFIGS.put(ConfigType.API_HOST.name(), host);
        return this;
    }

    public final Configurator withIcon(IconFontDescriptor iconFontDescriptor) {
        ICONS.add(iconFontDescriptor);
        return this;
    }

    public void configure() {
        initIcons();
        LATTE_CONFIGS.put(ConfigType.CONFIG_READY.name(), true);
    }

    private void initIcons() {
        if (ICONS.size() > 0) {
            Iconify.IconifyInitializer iconifyInitializer = Iconify.with(ICONS.get(0));
            for (int i = 1; i < ICONS.size(); i++) {
                iconifyInitializer.with(ICONS.get(i));
            }
        }
    }

    @SuppressWarnings("unchecked")
    public <T> T getConfiguration(Enum<ConfigType> configTypeEnum) {
        checkConfiguration();
        return (T) LATTE_CONFIGS.get(configTypeEnum.name());
    }

    private void checkConfiguration() {
        final boolean ready = (boolean) LATTE_CONFIGS.get(ConfigType.CONFIG_READY.name());
        if (!ready) {
            throw new RuntimeException(" Configuration is not ready , call configure()");
        }
    }

    private static class Holder {
        private static final Configurator INSTANCE = new Configurator();
    }
}
