package com.widget.popupwindowview;

import android.app.Activity;
import android.content.Context;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;

import com.rengwuxian.materialedittext.MaterialEditText;
import com.variable.EventBus.SearchViewEventBus;
import com.variable.skin.object.SkinKey;
import com.variable.utility.CommonUtility;
import com.variable.utility.SkinUtility;
import com.variable.utility.ToastUtility;
import com.variable.utility.UICalculator;
import com.widget.CustomButton;
import com.widget.R;

import java.util.Properties;

import org.greenrobot.eventbus.EventBus;

/**
 * 搜尋畫面
 * @author Star
 */
public class SearchView extends View {
    private final boolean DEBUG = false;
    private final String TAG = SearchView.class.getSimpleName();
    private final int TEXT_SIZE = 16;
    private final int INPUT_AT_LEAST_TEXT_COUNT=1;

    private Activity activity;
    private View layout;
    private MaterialEditText input;
    private CustomButton search;
    private Properties messageProperties;

    public SearchView(Context context) {
        super(context);
    }

    public SearchView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public SearchView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public SearchView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    public SearchView(Activity activity) {
        super(activity);
        this.activity = activity;
        onCreate();
        onCreateView();
    }

    public View getView(){
        return layout;
    }

    private void onCreate() {
        messageProperties = CommonUtility.getMessageProperties(activity);
    }

    private void onCreateView() {
        layout = activity.getLayoutInflater().inflate(R.layout.custom_search_view_layout, null);

        input = (MaterialEditText) layout.findViewById(R.id.input);
        input.setTextSize(TypedValue.COMPLEX_UNIT_PX, UICalculator.getRatioWidth(activity, TEXT_SIZE));
        input.setSingleLine();
        input.setHint(messageProperties.getProperty("SEARCH_VIEW_INPUT_SOME_CONTENT"));
        input.setFloatingLabelText(messageProperties.getProperty("SEARCH_VIEW_INPUT_SOME_CONTENT"));
        input.setMinCharacters(INPUT_AT_LEAST_TEXT_COUNT);

        search = (CustomButton) layout.findViewById(R.id.search);
        UICalculator.setAutoText(search
                , messageProperties.getProperty("SEARCH")
                , UICalculator.getWidth(activity)
                , UICalculator.getRatioWidth(activity, TEXT_SIZE)
                , SkinUtility.getColor(SkinKey.COLOR_WHITE));
        search.setBackgroundResource(SkinUtility.getColor(SkinKey.W06));
        search.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                activity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (input.getText().toString().length() >= INPUT_AT_LEAST_TEXT_COUNT) {
                            EventBus.getDefault().post(new SearchViewEventBus(input.getText().toString()));
                        } else {
                            ToastUtility.showMessage(activity, messageProperties.getProperty("SEARCH_VIEW_INPUT_SOME_CONTENT"));
                        }
                    }
                });
            }
        });
    }
}
