package ljw123.github.io.latte.ec.icon;

import com.joanzapata.iconify.Icon;

/**
 * @author xiaofo on 2018/6/21.
 */

public enum EcIcons implements Icon {
    icon_scan('\ue64b'),
    icon_ali_pay('\ue694');
    private char character;

    EcIcons(char character) {
        this.character = character;
    }

    @Override
    public String key() {
        return name().replace('_', '-');
    }

    @Override
    public char character() {
        return character;
    }
}
