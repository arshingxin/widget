package com.widget;


import android.R;
import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.CheckBox;

import com.variable.utility.UICalculator;

/**
 * CustomCheckBox
 * @author star
 */
public class CustomCheckBox extends CheckBox {
	private static final boolean DEBUG=false;
	private static final String TAG=CustomCheckBox.class.getSimpleName();

	private static final int MARGIN=20;
	private static final int PADDING=25;

	public static final int CHECKBOX_WHITE_STYLE=0;
	public static final int CHECKBOX_BLACK_STYLE=1;

	private int mStyle=CHECKBOX_WHITE_STYLE;

	public void setStyle(int style) {
		mStyle=style;
		init();
	}

	public CustomCheckBox(Context context) {
		super(context);
		init();
	}

	public CustomCheckBox(Context context, AttributeSet attrs) {
		super(context, attrs);
		init();
	}

	public CustomCheckBox(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		init();
	}

	public void init() {
		this.setButtonDrawable(R.color.transparent);
		Drawable d=null;
		d = getResources().getDrawable(com.widget.R.drawable.background_checkbox);
		d.setBounds(0, 0, (int) UICalculator.getRatioWidth((Activity) getContext(), MARGIN), (int) UICalculator.getRatioWidth((Activity) getContext(), MARGIN));
		setCompoundDrawables(d, null, null, null);
		this.setPadding(0, 0, 0, 0);
	}

	@Override
	public int getCompoundPaddingLeft() {
		return (int) UICalculator.getRatioWidth((Activity) getContext(), PADDING);
	}
}
