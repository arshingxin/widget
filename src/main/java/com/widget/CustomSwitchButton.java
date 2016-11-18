package com.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;

import com.variable.skin.object.SkinKey;
import com.variable.utility.SkinUtility;

/**
 * @author Star
 */
public class CustomSwitchButton extends LinearLayout{

    public interface OnCheckedChangeListener{
        void onCheckedChanged(View convertView, boolean isChecked);
    }

    private OnCheckedChangeListener onCheckedChangeListener;
    private boolean isChecked = false;
    private View convertView;

    public CustomSwitchButton(Context context) {
        super(context);
        init(context);
    }

    public CustomSwitchButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public CustomSwitchButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    public CustomSwitchButton(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context);
    }

    private void init(Context context) {
        setGravity(Gravity.CENTER_VERTICAL);
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = inflater.inflate(R.layout.custom_switch_button, null);
        addView(convertView);
    }

    @Override
    public void setTag(Object tag) {
        super.setTag(tag);
        convertView.setTag(tag);
    }

    public void setOnCheckedChangeListener(OnCheckedChangeListener l){
        onCheckedChangeListener = l;
        if(onCheckedChangeListener!=null){
            convertView.findViewById(R.id.switch_layout).setOnTouchListener(new OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    if(MotionEvent.ACTION_DOWN==event.getAction()){
                        setChecked(!isChecked);
                        return true;
                    }else{
                        return false;
                    }
                }
            });
        }
    }

    /**
     * 切換Switch
     * false:關閉, true:開啟
     * @param isChecked
     */
    public void setChecked(boolean isChecked){
        this.isChecked = isChecked;
        onCheckedChangeListener.onCheckedChanged(convertView, isChecked);
        if(true==isChecked){
            convertView.findViewById(R.id.switch_layout).setBackgroundResource(SkinUtility.getColor(SkinKey.SETTING_BG_SWITCH_ON));
            convertView.findViewById(R.id.switch_on).setVisibility(View.VISIBLE);
            convertView.findViewById(R.id.switch_off).setVisibility(View.INVISIBLE);
        }else{
            convertView.findViewById(R.id.switch_layout).setBackgroundResource(SkinUtility.getColor(SkinKey.SETTING_BG_SWITCH_OFF));
            convertView.findViewById(R.id.switch_on).setVisibility(View.INVISIBLE);
            convertView.findViewById(R.id.switch_off).setVisibility(View.VISIBLE);
        }
        requestLayout();
        postInvalidate();
    }

    /**
     * 設定開關圓點的大小
     * @param w
     */
    public void setThumbSize(int w){
        convertView.findViewById(R.id.switch_on).getLayoutParams().width = w;
        convertView.findViewById(R.id.switch_on).getLayoutParams().height = w;
        convertView.findViewById(R.id.switch_off).getLayoutParams().width = w;
        convertView.findViewById(R.id.switch_off).getLayoutParams().height = w;
        requestLayout();
        postInvalidate();
    }
}
