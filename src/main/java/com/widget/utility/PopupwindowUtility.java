package com.widget.utility;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;

import com.variable.skin.object.SkinKey;
import com.variable.utility.SkinUtility;
import com.variable.utility.UICalculator;
import com.widget.R;
import com.widget.popupwindowview.FilterView;
import com.widget.popupwindowview.SearchView;

/**
 * Popupwindow工具
 * @author Star
 */
public class PopupwindowUtility extends PopupWindow {
    private static final String TAG = PopupwindowUtility.class.getSimpleName();
    private static final boolean DEBUG = false;

    /**
     * 顯示篩選功能
     * @param activity
     * @param showFormType
     * @param anchorView
     * @param backgroundResource 0:預設SkinKey.W08
     * @return
     */
    public static PopupWindow getFilterWindow(final Activity activity, String showFormType, RelativeLayout anchorView, int backgroundResource){
        final PopupWindow popWindow = new PopupWindow(activity);
        popWindow.setFocusable(true);
        popWindow.setOutsideTouchable(false);

        int width = UICalculator.getWidth(activity)/2;
        View filterView = new FilterView(activity, showFormType, width).getView();
        if(0==backgroundResource){
            filterView.setBackgroundResource(SkinUtility.getColor(SkinKey.W08));
        }else{
            filterView.setBackgroundResource(backgroundResource);
        }
        popWindow.setWidth(width);
        popWindow.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        popWindow.setContentView(filterView);
        popWindow.setAnimationStyle(R.style.Animations_PopDownMenu_Right);
        popWindow.showAsDropDown(anchorView, 0, 0);

        return popWindow;
    }

    /**
     * 搜尋畫面
     * @param activity
     * @param anchorView
     * @param backgroundResource 0:預設SkinKey.W08
     * @return
     */
    public static PopupWindow getSearchWindow(final Activity activity, RelativeLayout anchorView, int backgroundResource){
        final PopupWindow popWindow = new PopupWindow(activity);
        popWindow.setFocusable(true);
        popWindow.setOutsideTouchable(false);

        View searchView = new SearchView(activity).getView();
        if(0==backgroundResource){
            searchView.setBackgroundResource(SkinUtility.getColor(SkinKey.W08));
        }else{
            searchView.setBackgroundResource(backgroundResource);
        }
        popWindow.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        popWindow.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        popWindow.setContentView(searchView);
        popWindow.setAnimationStyle(R.style.Animations_PopDownMenu_Right);
        popWindow.showAsDropDown(anchorView, 0, 0);

        return popWindow;
    }
}
