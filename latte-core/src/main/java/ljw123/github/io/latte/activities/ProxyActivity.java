package ljw123.github.io.latte.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.ContentFrameLayout;

import ljw123.github.io.latte.R;
import ljw123.github.io.latte.delegates.LatteDelegate;
import me.yokeyword.fragmentation.SupportActivity;

/**
 * @author xiaofo on 2018/6/21.
 */

public abstract class ProxyActivity extends SupportActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initContainer(savedInstanceState);
    }

    private void initContainer(@Nullable Bundle savedInstanceState) {
        final ContentFrameLayout contentFrameLayout = new ContentFrameLayout(this);
        contentFrameLayout.setId(R.id.delegate_container);
        setContentView(contentFrameLayout);
        if (savedInstanceState == null) {
            loadRootFragment(R.id.delegate_container, setRootDelegate());
        }
    }

    public abstract LatteDelegate setRootDelegate();

    @Override
    protected void onDestroy() {
        super.onDestroy();
//        程序退出 垃圾回收
        System.gc();
        System.runFinalization();
    }
}
