package ljw123.github.io.festec.example;

import ljw123.github.io.latte.activities.ProxyActivity;
import ljw123.github.io.latte.delegates.LatteDelegate;

public class ExampleActivity extends ProxyActivity {
    @Override
    public LatteDelegate setRootDelegate() {
        return new ExampleDelegate();
    }
}
