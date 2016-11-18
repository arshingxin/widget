package com.widget;

import android.content.Context;
import android.util.AttributeSet;

import com.github.clans.fab.FloatingActionButton;
import com.variable.skin.object.SkinKey;
import com.variable.utility.SkinUtility;

/**
 * Created by star on 2016/4/28.
 */
public class CustomFloatingActionButtonV1 extends FloatingActionButton{
    private final boolean SHADOW_STATUS = false;
    /**
     * {@link SIZE_NORMAL}
     * {@link SIZE_MINI}
     */
    private final int TYPE = SIZE_NORMAL;
    private final int PADDING = 20;

    public CustomFloatingActionButtonV1(Context context) {
        super(context);
        init();
    }

    public CustomFloatingActionButtonV1(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public CustomFloatingActionButtonV1(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    private void init() {
        setShowShadow(SHADOW_STATUS);
        setButtonSize(TYPE);
        setScaleType(ScaleType.CENTER_INSIDE);
        setColorNormal(SkinUtility.getColor(SkinKey.EVENT_FLOATING_ACTION_BUTTON_NORMAL));
        setColorPressed(SkinUtility.getColor(SkinKey.EVENT_FLOATING_ACTION_BUTTON_PRESSED));
        setShadowColor(SkinUtility.getColor(SkinKey.EVENT_FLOATING_ACTION_BUTTON_SHADOW));
        setImageResource(SkinUtility.getColor(SkinKey.COMMON_EDIT));
        setPadding(PADDING, PADDING, PADDING, PADDING);
    }

    public void setPadding(int padding){
        setPadding(padding,padding,padding,padding);
        requestLayout();
        postInvalidate();
    }
}
