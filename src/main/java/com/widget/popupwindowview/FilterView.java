package com.widget.popupwindowview;

import android.app.Activity;
import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ListView;

import com.variable.EventBus.FilterViewEventBus;
import com.variable.object.CreateType;
import com.variable.skin.object.SkinKey;
import com.variable.utility.CommonUtility;
import com.variable.utility.SkinUtility;
import com.variable.utility.UICalculator;
import com.widget.CustomButton;
import com.widget.R;
import com.widget.popupwindowview.adapter.FilterViewAdapter;

import org.greenrobot.eventbus.EventBus;

/**
 * 顯示EventExpand篩選列表
 * @author Star
 */
public class FilterView extends View {
    private final boolean DEBUG = false;
    private final String TAG = FilterView.class.getSimpleName();

    private Activity activity;
    private View layout;
    private ListView listView;
    private FilterViewAdapter adapter;
    private CustomButton confirm;
    private int width;
    private String showFormType;

    public FilterView(Context context) {
        super(context);
    }

    public FilterView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public FilterView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public FilterView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    public FilterView(Activity activity, String showFormType, int width) {
        super(activity);
        this.activity = activity;
        this.showFormType = showFormType;
        this.width = width;
        onCreate();
        onCreateView();
    }

    public View getView(){
        return layout;
    }

    private void onCreate() {

    }

    private void onCreateView() {
        layout = activity.getLayoutInflater().inflate(R.layout.custom_filter_view_layout, null);

        listView = (ListView) layout.findViewById(R.id.listview);
        adapter = new FilterViewAdapter(activity);
        adapter.setItem(CreateType.KEY, showFormType);
        adapter.setWidth(width);
        listView.setAdapter(adapter);

        confirm = (CustomButton) layout.findViewById(R.id.confirm);
        UICalculator.setAutoText(confirm
                , CommonUtility.getMessageProperties(activity).getProperty("CONFIRM")
                , width, UICalculator.getRatioWidth(activity, 14)
                , SkinUtility.getColor(SkinKey.COLOR_DARK_GRAY));
        confirm.setBackgroundResource(SkinUtility.getColor(SkinKey.W03));
        confirm.setTextColor(SkinUtility.getColor(SkinKey.COLOR_WHITE));
        confirm.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                EventBus.getDefault().post(new FilterViewEventBus(adapter.getShowFormType()));
            }
        });
    }
}
