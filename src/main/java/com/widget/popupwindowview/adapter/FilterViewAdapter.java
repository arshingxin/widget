package com.widget.popupwindowview.adapter;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.variable.object.CreateType;
import com.variable.skin.object.SkinKey;
import com.variable.utility.CommonUtility;
import com.variable.utility.SkinUtility;
import com.variable.utility.UICalculator;
import com.widget.R;

import java.util.Properties;

/**
 * @author Star
 */
public class FilterViewAdapter extends BaseAdapter{
    private final boolean DEBUG = false;
    private final String TAG = FilterViewAdapter.class.getSimpleName();
    private final int ICON_WIDTH = 18;
    private final int TEXT_SIZE = 18;

    private Activity activity;
    private Properties messageProperties;
    private String[] items;
    private int iconWidth;
    private Bundle checkPosition;
    private int width;
    private String showFormType;

    private class ViewHolder {
        ImageView check;
        TextView cat;
    }

    public FilterViewAdapter(Activity activity) {
        this.activity = activity;
        messageProperties = CommonUtility.getMessageProperties(activity);
        iconWidth = Math.round(UICalculator.getRatioWidth(activity, ICON_WIDTH));
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public void setItem(String[] items, String showFormType){
        checkPosition = new Bundle();
        this.items = items;
        this.showFormType = showFormType;
        int count = items.length;
        for (int i=0;i<count;i++){
            if(showFormType.contains(CreateType.SHOW_ALL)){ //包含有全部，就都設為選取
                checkPosition.putBoolean(Integer.toString(i), true);
            }else{
                if(showFormType.contains(CreateType.KEY[i])){
                    checkPosition.putBoolean(Integer.toString(i), true);
                }else{
                    checkPosition.putBoolean(Integer.toString(i), false);
                }
            }
        }
    }

    public String getShowFormType(){
        boolean hasSelect = false;
        int count = checkPosition.size();
        StringBuilder sb = new StringBuilder();
        for (int i=0;i<count;i++){
            if(checkPosition.getBoolean(Integer.toString(i))){
                hasSelect = true;
                sb.append(CreateType.KEY[i]).append(",");
            }
        }
        //去掉最後面的逗號
        if(hasSelect){
            showFormType = sb.toString().substring(0, sb.toString().length()-1);
        }
        return showFormType;
    }

    @Override
    public int getCount() {
        return null==items?0:items.length;
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final ViewHolder holder;
        if(convertView==null){
            convertView = activity.getLayoutInflater().inflate(R.layout.item_filter_view_layout,parent,false);

            holder = new ViewHolder();
            holder.check = (ImageView) convertView.findViewById(R.id.check);
            holder.check.setImageResource(R.drawable.icon_check_button_normal);
            holder.check.getLayoutParams().width = iconWidth;
            holder.check.getLayoutParams().height = iconWidth;

            holder.cat = (TextView) convertView.findViewById(R.id.cat);
            holder.cat.setTextColor(SkinUtility.getColor(SkinKey.COLOR_DARK_GRAY));
            holder.cat.setSingleLine();

            convertView.setTag(holder);
        }else{
            holder = (ViewHolder) convertView.getTag();
        }
        holder.check.setImageResource(R.drawable.icon_check_button_normal);
        holder.cat.setText(messageProperties.getProperty("DEFAULT_TEXT_VALUE"));
        convertView.setOnClickListener(null);

        if(checkPosition.getBoolean(Integer.toString(position))){
            holder.check.setImageResource(R.drawable.icon_check_button_pressed);
        }
        UICalculator.setAutoText(holder.cat
                , messageProperties.getProperty("FILTER_VIEW_ITEM_KEY_"+items[position])
                , width - iconWidth
                , UICalculator.getRatioWidth(activity, TEXT_SIZE));

        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (CreateType.KEY[position].equals(CreateType.SHOW_ALL)) { //點選到全部
                    int count = checkPosition.size();
                    if (checkPosition.getBoolean(Integer.toString(position))) { //如果全選原本有選取，將全部設為沒有選取
                        for (int i = 0; i < count; i++) {
                            checkPosition.putBoolean(Integer.toString(i), false);
                        }
                    } else { //否則全部設為選取
                        for (int i = 0; i < count; i++) {
                            checkPosition.putBoolean(Integer.toString(i), true);

                        }
                    }
                    activity.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            notifyDataSetChanged();
                        }
                    });
                } else { //不是點到全部
                    //將全部設為沒選取
                    checkPosition.putBoolean(Integer.toString(0), false);
                    if (checkPosition.getBoolean(Integer.toString(position))) { //原本有選取的改為沒有選取
                        checkPosition.putBoolean(Integer.toString(position), false);
                        activity.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                notifyDataSetChanged();
                            }
                        });
                    } else { //原本沒有選取的改成有選取
                        checkPosition.putBoolean(Integer.toString(position), true);
                        activity.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                notifyDataSetChanged();
                            }
                        });
                    }
                }
            }
        });
        return convertView;
    }
}
