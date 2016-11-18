package com.widget;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.CheckedTextView;

import com.variable.utility.UICalculator;

/**
 * CustomCheckedTextView
 * @author star
 */

public class CustomCheckedTextView extends CheckedTextView {
    private final boolean DEBUG = false;
    private final String TAG = CustomCheckedTextView.class.getSimpleName();
    private final int MARGIN = 20;

    public CustomCheckedTextView(Context context) {
        super(context);
        init();
    }

    public CustomCheckedTextView(Context context, AttributeSet attrs) {
    	super(context, attrs);
        init();
    }

    public CustomCheckedTextView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }
    
    public void init() {
    	Drawable d;
    	d = getResources().getDrawable(com.widget.R.drawable.background_checkbox);
    	d.setBounds(0, 0, (int) UICalculator.getRatioWidth((Activity) getContext(), MARGIN), (int) UICalculator.getRatioWidth((Activity) getContext(), MARGIN));
    	setCompoundDrawables(d, null, null, null);
    }
}

