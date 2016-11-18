package com.widget;

import android.content.Context;
import android.util.AttributeSet;

import com.melnykov.fab.FloatingActionButton;
import com.variable.skin.object.SkinKey;
import com.variable.utility.SkinUtility;

/**
 * 自訂Floating Action Button
 * @author Star
 */
public class CustomFloatingActionButton extends FloatingActionButton {
    private final boolean SHADOW_STATUS = false;
    private final int TYPE = FloatingActionButton.TYPE_NORMAL;
    private final int PADDING = 20;

    public CustomFloatingActionButton(Context context) {
        super(context);
        init();
    }

    public CustomFloatingActionButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public CustomFloatingActionButton(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    private void init() {
        setPadding(PADDING, PADDING, PADDING, PADDING);
        setShadow(SHADOW_STATUS);
        setType(TYPE);
        setScaleType(ScaleType.CENTER_INSIDE);
        setColorNormal(SkinUtility.getColor(SkinKey.EVENT_FLOATING_ACTION_BUTTON_NORMAL));
        setColorPressed(SkinUtility.getColor(SkinKey.EVENT_FLOATING_ACTION_BUTTON_PRESSED));
        setImageResource(android.R.drawable.ic_menu_edit);
    }

    public void setPadding(int padding){
        setPadding(padding,padding,padding,padding);
        requestLayout();
        invalidate();
    }
}
