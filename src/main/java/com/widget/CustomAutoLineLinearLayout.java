package com.widget;

import android.app.Activity;
import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

import com.log.Logger;
import com.variable.utility.UICalculator;

import java.util.ArrayList;

/**
 * 自動換行的水平linear layout
 * Created by star on 2016/5/12.
 */
public class CustomAutoLineLinearLayout extends ViewGroup {
    private static final String TAG = CustomAutoLineLinearLayout.class.getSimpleName();
    private final int MARGIN = 5;

    private int line_height;
    private ArrayList<Integer> lineHeightList = new ArrayList();
    private int margin;

    public CustomAutoLineLinearLayout(Context context) {
        super(context);
        init(context);
    }

    public CustomAutoLineLinearLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    private void init(Context context) {
        margin = (int) UICalculator.getRatioWidth((Activity) context, MARGIN);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        assert (MeasureSpec.getMode(widthMeasureSpec) != MeasureSpec.UNSPECIFIED);

        final int width = MeasureSpec.getSize(widthMeasureSpec);

        // The next line is WRONG!!! Doesn't take into account requested MeasureSpec mode!

        int height = MeasureSpec.getSize(heightMeasureSpec) - getPaddingTop() - getPaddingBottom();


        final int count = getChildCount();
        int line_height = 0;
        lineHeightList.clear();

        int xpos = getPaddingLeft();
        int ypos = getPaddingTop();
        for (int i = 0; i < count; i++) {
            final View child = getChildAt(i);
            if (child.getVisibility() != GONE) {
                final LayoutParams lp = (LayoutParams) child.getLayoutParams();
                child.measure(
                        MeasureSpec.makeMeasureSpec(width, MeasureSpec.AT_MOST),
                        MeasureSpec.makeMeasureSpec(height, MeasureSpec.UNSPECIFIED));

                final int childw = child.getMeasuredWidth();
                line_height = Math.max(line_height, child.getMeasuredHeight() + lp.height);

                if (xpos + childw > width) {
                    xpos = getPaddingLeft();
                    ypos += line_height;
                    lineHeightList.add(line_height + margin);
                }
                xpos += childw + lp.width;

            }
        }
        this.line_height = line_height + margin;

        if (MeasureSpec.getMode(heightMeasureSpec) == MeasureSpec.UNSPECIFIED) {
            height = ypos + line_height + margin;

        } else if (MeasureSpec.getMode(heightMeasureSpec) == MeasureSpec.AT_MOST) {
            if (ypos + line_height + margin < height) {
                height = ypos + line_height + margin;

            }
        }

        setMeasuredDimension(width, height);
    }

    @Override
    protected LayoutParams generateDefaultLayoutParams() {
        return new LayoutParams(1, 1); // default of 1px spacing

    }

    @Override
    protected boolean checkLayoutParams(LayoutParams p) {
        return (p instanceof LayoutParams);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        final int count = getChildCount();
        final int width = r - l;
        int xpos = getPaddingLeft();
        int ypos = getPaddingTop();
        int lineHeightListIndex = 0;

        int centerWidthLeft = 0;

        for (int i = 0; i < count; i++) {
            final View child = getChildAt(i);
            if (child.getVisibility() != GONE) {
                final int childw = child.getMeasuredWidth();
                final int childh = child.getMeasuredHeight();
                final LayoutParams lp = (LayoutParams) child.getLayoutParams();
                if (xpos + childw > width) {
                    centerWidthLeft = (width - xpos + lp.width) / 2;
                    xpos = getPaddingLeft();
                }
                xpos += childw + lp.width + margin;
            }
        }

        xpos = getPaddingLeft()+margin;
        ypos = getPaddingTop();
        for (int i = 0; i < count; i++) {
            final View child = getChildAt(i);
            if (child.getVisibility() != GONE) {
                final int childw = child.getMeasuredWidth();
                final int childh = child.getMeasuredHeight();
                final LayoutParams lp = (LayoutParams) child.getLayoutParams();
                if (xpos + childw > width) {
                    xpos = getPaddingLeft() + margin;
                    ypos += lineHeightList.get(lineHeightListIndex);
                    lineHeightListIndex++;
                }

                child.layout(centerWidthLeft + xpos, ypos, centerWidthLeft + xpos + childw, ypos + childh);
                xpos += childw + lp.width + margin;
                Logger.d(String.valueOf(lp.width));
            }
        }

    }
}
