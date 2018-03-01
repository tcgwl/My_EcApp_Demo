package com.archer.lib.ec.icon;

import com.joanzapata.iconify.Icon;

/**
 * Created by Archer on 2018/2/6.
 */

public enum EcIcons implements Icon {
    icon_scan('\ue64b'),
    icon_ali_pay('\ue64c');

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
