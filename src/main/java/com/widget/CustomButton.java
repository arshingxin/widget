package com.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.Button;

import com.variable.skin.object.SkinKey;
import com.variable.utility.SkinUtility;

/**
 * CustomButton 自訂按鈕
 * @author 20141001[star]
 */
public class CustomButton extends Button {
	
    public CustomButton(Context context) {
        super(context);
        init();
    }

    public CustomButton(Context context, AttributeSet attrs) {
    	super(context, attrs);
        init();
    }

    public CustomButton(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }
    
    private void init() {
    	setTextColor(SkinUtility.getColor(SkinKey.COLOR_WHITE));
    	setBackgroundResource(SkinUtility.getColor(SkinKey.W00));
    }
}
