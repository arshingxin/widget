package com.widget;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;

import com.github.clans.fab.FloatingActionMenu;
import com.variable.skin.object.SkinKey;
import com.variable.utility.SkinUtility;

/**
 * Created by star on 2016/4/28.
 */
public class CustomFloatingActionMenu extends FloatingActionMenu {
    public CustomFloatingActionMenu(Context context) {
        super(context);
        init();
    }

    public CustomFloatingActionMenu(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public CustomFloatingActionMenu(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        setMenuButtonColorNormal(SkinUtility.getColor(SkinKey.EVENT_FLOATING_ACTION_BUTTON_NORMAL));
        setMenuButtonColorPressed(SkinUtility.getColor(SkinKey.EVENT_FLOATING_ACTION_BUTTON_PRESSED));
    }
}
