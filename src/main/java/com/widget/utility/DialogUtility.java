package com.widget.utility;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;

import com.variable.key.SharePreferenceKey;
import com.variable.object.CommonInfo;
import com.variable.object.ShowMode;
import com.variable.utility.CommonUtility;
import com.widget.CustomDialog;
import com.widget.R;

import java.lang.reflect.Field;
import java.util.Properties;

/**
 * 20150716[star] create
 */
public class DialogUtility {
    private final String TAG = DialogUtility.class.getSimpleName();
    private final boolean DEBUG = false;

    public final static String DIALOG_MENU_CONTENT = "menu";
    public final static String DIALOG_TITLE = "title";
    public final static String DIALOG_MESSAGE = "message";
    public final static String DIALOG_BACK = "back";
    public final static String DIALOG_BUTTON_POSITIVE = "positive";
    public final static String DIALOG_BUTTON_NEGATIVE = "negative";

    public static void showMenuAlertDialog(final Context context, Bundle data, AdapterView.OnItemClickListener listener,int style,int position)
    {
        final CustomDialog alertDialog;
        CustomDialog.Builder builder = new CustomDialog.Builder(context,style);
        builder.setItems(data.getStringArray(DIALOG_MENU_CONTENT), listener);
        builder.setPosition(position);
        if(data.getString(DIALOG_TITLE) != null)
        {
            builder.setTitle(data.getString(DIALOG_TITLE));
        }
        builder.setNegativeButton(data.containsKey(DIALOG_BACK) ? data.getString(DIALOG_BACK) : "", new DialogInterface.OnClickListener()
        {
            public void onClick(DialogInterface dialog, int which)
            {
                dialog.dismiss();
            }
        });
        alertDialog = (CustomDialog) builder.create();
        alertDialog.setCanceledOnTouchOutside(true);
        if(CommonInfo.showMode==ShowMode.ORIGINAL){
            alertDialog.getWindow().getAttributes().windowAnimations = R.style.phone_Animation_Dialog_BottomIn300;
            alertDialog.getWindow().getAttributes().gravity = Gravity.CENTER_HORIZONTAL | Gravity.BOTTOM;
        }
        ((Activity)context).runOnUiThread(new Runnable() {
            @Override
            public void run() {
                alertDialog.show();
            }
        });
    }

    public static CustomDialog showMenuAlertDialog(final Context context, Bundle data, AdapterView.OnItemClickListener listener)
    {
        final CustomDialog alertDialog;
        CustomDialog.Builder builder = new CustomDialog.Builder(context);
        builder.setItems(data.getStringArray(DIALOG_MENU_CONTENT), listener);
        if(data.getString(DIALOG_TITLE) != null)
        {
            builder.setTitle(data.getString(DIALOG_TITLE));
        }
        builder.setNegativeButton(data.containsKey(DIALOG_BACK) ? data.getString(DIALOG_BACK) : "", new DialogInterface.OnClickListener()
        {
            public void onClick(DialogInterface dialog, int which)
            {
                dialog.dismiss();
            }
        });
        alertDialog = (CustomDialog) builder.create();
        alertDialog.setCanceledOnTouchOutside(true);
        if(CommonInfo.showMode== ShowMode.ORIGINAL){
            alertDialog.getWindow().getAttributes().windowAnimations = R.style.phone_Animation_Dialog_BottomIn300;
            alertDialog.getWindow().getAttributes().gravity = Gravity.CENTER_HORIZONTAL | Gravity.BOTTOM;
        }
        ((Activity)context).runOnUiThread(new Runnable() {
            @Override
            public void run() {
                alertDialog.show();
            }
        });
        return alertDialog;
    }

    public static void showPageAlertDialog(final Context context, Bundle data, AdapterView.OnItemClickListener listener,int position)
    {
        final CustomDialog alertDialog;
        CustomDialog.Builder builder = new CustomDialog.Builder(context);
        builder.setItems(data.getStringArray(DIALOG_MENU_CONTENT), listener);
        if(data.getString(DIALOG_TITLE) != null)
        {
            builder.setTitle(data.getString(DIALOG_TITLE));
        }
        builder.setNegativeButton(data.containsKey(DIALOG_BACK) ? data.getString(DIALOG_BACK) : "", new DialogInterface.OnClickListener()
        {
            public void onClick(DialogInterface dialog, int which)
            {
                dialog.dismiss();
            }
        });
        builder.setPosition(position);
        alertDialog = (CustomDialog) builder.create();
        alertDialog.setCanceledOnTouchOutside(true);
        ((Activity)context).runOnUiThread(new Runnable() {
            @Override
            public void run() {
                alertDialog.show();
            }
        });
    }

    /**
     * 顯示兩個按鈕的Menu
     * @param context
     * @param title
     * @param data
     * @param listener
     * @param positiveButtonName
     * @param positiveButtonListener
     * @param negativeButtonName
     * @param negativeButtonListener
     * @param cancelListener
     */
    public static void showTwoButtonMenuAlertDialog(final Context context,
                                                    String title,
                                                    Bundle data,
                                                    AdapterView.OnItemClickListener listener,
                                                    String positiveButtonName,
                                                    DialogInterface.OnClickListener positiveButtonListener,
                                                    String negativeButtonName,
                                                    DialogInterface.OnClickListener negativeButtonListener,
                                                    DialogInterface.OnCancelListener cancelListener)
    {
        final CustomDialog alertDialog;
        CustomDialog.Builder builder = new CustomDialog.Builder(context);
        builder.setItems(data.getStringArray(DIALOG_MENU_CONTENT), listener);
        if(data.getString(DIALOG_TITLE) != null)
        {
            builder.setTitle(data.getString(DIALOG_TITLE));
        }
        builder.setNegativeButton(data.containsKey(DIALOG_BACK) ? data.getString(DIALOG_BACK) : "", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        alertDialog = (CustomDialog) builder.create();
        alertDialog.setCanceledOnTouchOutside(true);
        if(CommonInfo.showMode==ShowMode.ORIGINAL){
            alertDialog.getWindow().getAttributes().windowAnimations = R.style.phone_Animation_Dialog_BottomIn300;
            alertDialog.getWindow().getAttributes().gravity = Gravity.CENTER_HORIZONTAL | Gravity.BOTTOM;
        }
        ((Activity)context).runOnUiThread(new Runnable() {
            @Override
            public void run() {
                alertDialog.show();
            }
        });
    }

    public static void showMenuAlertDialog(final Context context, Bundle data, boolean hasBack, AdapterView.OnItemClickListener listener)
    {
        final CustomDialog alertDialog = new CustomDialog(context);
        CustomDialog.Builder builder = new CustomDialog.Builder(context);
        builder.setItems(data.getStringArray(DIALOG_MENU_CONTENT), listener);
        if(data.getString(DIALOG_TITLE) != null)
        {
            builder.setTitle(data.getString(DIALOG_TITLE));
        }

        if(hasBack) {
            data.putString(DIALOG_BACK, CommonUtility.getMessageProperties(context).getProperty("BACK", ""));
        }

        builder.setNegativeButton(data.containsKey(DIALOG_BACK) ? data.getString(DIALOG_BACK) : "", new DialogInterface.OnClickListener()
        {
            public void onClick(DialogInterface dialog, int which)
            {
                dialog.dismiss();
            }
        });

        builder.setCancelable(hasBack);

//        alertDialog = (CustomDialog) builder.create();
        alertDialog.setCanceledOnTouchOutside(true);
        if(CommonInfo.showMode==ShowMode.ORIGINAL){
            alertDialog.getWindow().getAttributes().windowAnimations = R.style.phone_Animation_Dialog_BottomIn300;
            alertDialog.getWindow().getAttributes().gravity = Gravity.CENTER_HORIZONTAL | Gravity.BOTTOM;
        }
        ((Activity)context).runOnUiThread(new Runnable() {
            @Override
            public void run() {
                alertDialog.show();
            }
        });
    }


    /**
     * 顯示帶有選項的AlertDialog
     * @param context
     * @param data 選項資料
     * @param title 標題顯示
     * @param back 是否要含有返回按鈕, true:含有返回按鈕, false:不含有返回按鈕
     * @param listener 選項listener
     * @return AlertDialog
     * <li>需自行加上.show()才會顯示
     */
    public static void showMenuAlertDialog(final Context context, String[] data, String title, boolean back, AdapterView.OnItemClickListener listener)
    {
        Bundle b = new Bundle();
        b.putStringArray(DIALOG_MENU_CONTENT, data);
        b.putString(DIALOG_TITLE, title);
        if(back) {
            b.putString(DIALOG_BACK, CommonUtility.getMessageProperties(context).getProperty("BACK",""));
        }
        showMenuAlertDialog(context, b, back , listener);
    }

    /**
     * 顯示帶有選項的AlertDialog
     * @param context
     * @param data 選項資料
     * @param title 標題顯示
     * @param back 是否要含有返回按鈕, true:含有返回按鈕, false:不含有返回按鈕
     * @param backText 自訂back文字
     * @param listener 選項listener
     */
    public static void showMenuAlertDialog(final Context context, String[] data, String title, boolean back, String backText, AdapterView.OnItemClickListener listener)
    {
        Bundle b = new Bundle();
        b.putStringArray(DIALOG_MENU_CONTENT, data);
        b.putString(DIALOG_TITLE, title);
        if(back) {
            b.putString(DIALOG_BACK, backText);
        }
        showMenuAlertDialog(context, b, back, listener);
    }

    public static void showNormalAlertDialog(final Context context, Bundle data, DialogInterface.OnClickListener listener)
    {
        final CustomDialog alertDialog;
        CustomDialog.Builder builder = new CustomDialog.Builder(context);
        builder.setTitle(data.getString(DIALOG_TITLE) == null ? CommonUtility.getMessageProperties(context).getProperty("MSG_NOTIFICATION","") : data.getString(DIALOG_TITLE));
        builder.setMessage(data.getString(DIALOG_MESSAGE) == null ? "" : data.getString("message"));
        builder.setNegativeButton(data.getString(DIALOG_BUTTON_POSITIVE) == null ? CommonUtility.getMessageProperties(context).getProperty("BACK", "") : data.getString(DIALOG_BUTTON_POSITIVE), listener);
        alertDialog = (CustomDialog)builder.create();
        alertDialog.setCanceledOnTouchOutside(true);
        ((Activity)context).runOnUiThread(new Runnable() {
            @Override
            public void run() {
                alertDialog.show();
            }
        });
    }

    public static void showNormalAlertDialog(final Context context, Bundle data, DialogInterface.OnClickListener positiveListener, DialogInterface.OnClickListener negativeListener)
    {
        final CustomDialog alertDialog;
        CustomDialog.Builder builder = new CustomDialog.Builder(context);
        builder.setTitle(data.getString(DIALOG_TITLE) == null ? CommonUtility.getMessageProperties(context).getProperty("MSG_NOTIFICATION","") : data.getString(DIALOG_TITLE));
        builder.setMessage(data.getString(DIALOG_MESSAGE) == null ? "" : data.getString("message"));
        builder.setPositiveButton(data.getString(DIALOG_BUTTON_POSITIVE) == null ? CommonUtility.getMessageProperties(context).getProperty("CONFIRM", "") : data.getString(DIALOG_BUTTON_POSITIVE), positiveListener);
        builder.setNegativeButton(data.getString(DIALOG_BUTTON_NEGATIVE) == null ? CommonUtility.getMessageProperties(context).getProperty("BACK", "") : data.getString(DIALOG_BUTTON_NEGATIVE), negativeListener);
        alertDialog = (CustomDialog)builder.create();
        alertDialog.setCanceledOnTouchOutside(true);
        ((Activity)context).runOnUiThread(new Runnable() {
            @Override
            public void run() {
                alertDialog.show();
            }
        });
    }

    public static void showNormalAlertDialog(final Context context, Bundle data, DialogInterface.OnClickListener positiveListener, DialogInterface.OnClickListener negativeListener, DialogInterface.OnCancelListener cancelListener)
    {
        final CustomDialog alertDialog;
        CustomDialog.Builder builder = new CustomDialog.Builder(context);
        builder.setTitle(data.getString(DIALOG_TITLE) == null ? CommonUtility.getMessageProperties(context).getProperty("MSG_NOTIFICATION","") : data.getString(DIALOG_TITLE));
        builder.setMessage(data.getString(DIALOG_MESSAGE) == null ? "" : data.getString("message"));
        builder.setPositiveButton(data.getString(DIALOG_BUTTON_POSITIVE) == null ? CommonUtility.getMessageProperties(context).getProperty("CONFIRM", "") : data.getString(DIALOG_BUTTON_POSITIVE), positiveListener);
        builder.setNegativeButton(data.getString(DIALOG_BUTTON_NEGATIVE) == null ? CommonUtility.getMessageProperties(context).getProperty("BACK", "") : data.getString(DIALOG_BUTTON_NEGATIVE), negativeListener);
        builder.setOnCancelListener(cancelListener);

        alertDialog = (CustomDialog)builder.create();
        alertDialog.setCanceledOnTouchOutside(true);
        ((Activity)context).runOnUiThread(new Runnable() {
            @Override
            public void run() {
                alertDialog.show();
            }
        });
    }

    public static void showSimpleAlertDialog(Context context, String message, DialogInterface.OnClickListener listener)
    {
        showSimpleAlertDialog(context, message, listener, true);
    }

    /**
     * 帶有icon的AlertDialog
     * @param context
     * @param message
     * @param icon
     * @param listener
     */
    public static void showSimpleAlertDialog(Context context, String message, int icon, DialogInterface.OnClickListener listener)
    {
        showSimpleAlertDialog(context, message, icon, listener, true);
    }

    /**
     * @param context
     * @param message
     * @param listener
     * @param cancelable
     */
    public static void showSimpleAlertDialog(Context context,String message,DialogInterface.OnClickListener listener, boolean cancelable)
    {
        final CustomDialog alertDialog;
        CustomDialog.Builder builder = new CustomDialog.Builder(context);
        builder.setTitle(CommonUtility.getMessageProperties(context).getProperty("MSG_NOTIFICATION",""));
        builder.setMessage(message == null ? "" : message);
        builder.setNegativeButton(CommonUtility.getMessageProperties(context).getProperty("OK",""),listener);
        builder.setCancelable(cancelable);
        alertDialog = (CustomDialog) builder.create();
        /**
         * 20150112[李欣駿]
         * 將點選外部自動關閉視窗動作改同讀cancelable設定,解決當預期不讓使用者按實體返回鍵關閉視窗,但按視窗外卻可關閉。
         */
        alertDialog.setCanceledOnTouchOutside(cancelable);
        ((Activity)context).runOnUiThread(new Runnable() {
            @Override
            public void run() {
                alertDialog.show();
            }
        });
    }

    /**
     * @param context
     * @param message
     * @param resDrawable
     * @param listener
     * @param cancelable
     * @return
     */
    public static void showSimpleAlertDialog(Context context,String message, int resDrawable, DialogInterface.OnClickListener listener, boolean cancelable)
    {
        final CustomDialog alertDialog;
        CustomDialog.Builder builder = new CustomDialog.Builder(context);
        builder.setTitle(CommonUtility.getMessageProperties(context).getProperty("MSG_NOTIFICATION",""));
        builder.setMessage(message == null ? "" : message);
        builder.setNegativeButton(CommonUtility.getMessageProperties(context).getProperty("OK", ""), listener);
        builder.setCancelable(cancelable);
        alertDialog =(CustomDialog) builder.create();
        alertDialog.setCanceledOnTouchOutside(true);
        ((Activity)context).runOnUiThread(new Runnable() {
            @Override
            public void run() {
                alertDialog.show();
            }
        });
    }


//    public static CustomDialog showOneButtonAlertDialog(Context context,
//                                                        String title,
//                                                        String message,
//                                                        String positiveButtonName,
//                                                        DialogInterface.OnClickListener positiveButtonListener,
//                                                        boolean cancelable)
//    {
//        CustomDialog alertDialog;
//        CustomDialog.Builder builder = new CustomDialog.Builder(context);
//
//        builder.setTitle(title== null ? "" : title);
//        builder.setMessage(message == null ? "" : message);
//        builder.setNegativeButton(positiveButtonName, positiveButtonListener);
//        builder.setCancelable(cancelable);
//        alertDialog = (CustomDialog)builder.create();
//        alertDialog.setCanceledOnTouchOutside(true);
//        return alertDialog;
//    }

    /**
     * 單按鈕Dialog
     * @param context
     * @param title null: 訊息通知
     * @param message 顯示內容
     * @param positiveButtonName 按鈕顯示文字
     * @param positiveButtonListener 按鈕listener
     * @param cancelable false: 不可取消, true: 可取消
     */
    public static void showOneButtonAlertDialog(Context context,
                                                String title,
                                                String message,
                                                String positiveButtonName,
                                                DialogInterface.OnClickListener positiveButtonListener,
                                                boolean cancelable)
    {
        final CustomDialog alertDialog;
        CustomDialog.Builder builder = new CustomDialog.Builder(context);

        builder.setTitle(title == null ? CommonUtility.getMessageProperties(context).getProperty("MSG_NOTIFICATION","") : title);
        builder.setMessage(message == null ? "" : message);
        builder.setPositiveButton(positiveButtonName, positiveButtonListener);
        builder.setCancelable(cancelable);
        alertDialog = (CustomDialog)builder.create();
        alertDialog.setCanceledOnTouchOutside(cancelable);
        ((Activity)context).runOnUiThread(new Runnable() {
            @Override
            public void run() {
                alertDialog.show();
            }
        });
    }

    /**
     *
     * @param context
     * @param title
     * @param view
     * @param positiveButtonName
     * @param positiveButtonListener
     * @param cancelable
     * @return
     */
    public static CustomDialog showOneButtonAlertDialog(Context context,
                                                        String title,
                                                        View view,
                                                        String positiveButtonName,
                                                        DialogInterface.OnClickListener positiveButtonListener,
                                                        boolean cancelable)
    {
        CustomDialog alertDialog;
        CustomDialog.Builder builder = new CustomDialog.Builder(context);

        builder.setTitle(title == null ? "" : title);
        builder.setView(view);
        builder.setPositiveButton(positiveButtonName, positiveButtonListener);
        builder.setCancelable(cancelable);
        alertDialog = (CustomDialog)builder.create();
        alertDialog.setCanceledOnTouchOutside(true);
        return alertDialog;
    }

    /**
     * 呈現有左右兩個按鍵的Dialog,並且不能按返回鍵刪除此Dialog
     * @param context
     * @param message
     * @param positiveButtonName
     * @param positiveButtonListener
     * @param negativeButtonName
     * @param negativeButtonListener
     * @return
     */
    public static CustomDialog showTwoButtonAlertDialog(Context context,
                                                        String message,
                                                        String positiveButtonName,
                                                        DialogInterface.OnClickListener positiveButtonListener,
                                                        String negativeButtonName,
                                                        DialogInterface.OnClickListener negativeButtonListener)
    {
        CustomDialog alertDialog;
        CustomDialog.Builder builder = new CustomDialog.Builder(context);
        builder.setTitle(CommonUtility.getMessageProperties(context).getProperty("MSG_NOTIFICATION",""));
        builder.setMessage(message == null ? "" : message);
        builder.setPositiveButton(positiveButtonName == null? "":positiveButtonName, positiveButtonListener);
        builder.setNegativeButton(negativeButtonName == null? "":negativeButtonName, negativeButtonListener);
        builder.setCancelable(false);
        alertDialog = (CustomDialog) builder.create();
        alertDialog.setCanceledOnTouchOutside(true);
        return alertDialog;
    }

    public static CustomDialog showTwoButtonAlertDialog(Context context,
                                                        String message,
                                                        String positiveButtonName,
                                                        DialogInterface.OnClickListener positiveButtonListener,
                                                        String negativeButtonName,
                                                        DialogInterface.OnClickListener negativeButtonListener,
                                                        DialogInterface.OnCancelListener cancelListener)
    {
        CustomDialog alertDialog;
        CustomDialog.Builder builder = new CustomDialog.Builder(context);
        builder.setTitle(CommonUtility.getMessageProperties(context).getProperty("MSG_NOTIFICATION",""));
        builder.setMessage(message == null ? "" : message);
        builder.setPositiveButton(positiveButtonName, positiveButtonListener);
        builder.setNegativeButton(negativeButtonName, negativeButtonListener);
        builder.setOnCancelListener(cancelListener);
        alertDialog = (CustomDialog) builder.create();
        alertDialog.setCanceledOnTouchOutside(true);
        return alertDialog;
    }

    /**
     * @param context
     * @param title
     * @param message
     * @param positiveButtonName
     * @param positiveButtonListener
     * @param negativeButtonName
     * @param negativeButtonListener
     * @return
     */
    public static CustomDialog showTwoButtonAlertDialog(Context context,
                                                        String title,
                                                        String message,
                                                        String positiveButtonName,
                                                        DialogInterface.OnClickListener positiveButtonListener,
                                                        String negativeButtonName,
                                                        DialogInterface.OnClickListener negativeButtonListener)
    {
        CustomDialog alertDialog;
        CustomDialog.Builder builder = new CustomDialog.Builder(context);
        builder.setTitle(title == null ? "" : title);
        builder.setMessage(message == null ? "" : message);
        builder.setPositiveButton(positiveButtonName, positiveButtonListener);
        builder.setNegativeButton(negativeButtonName, negativeButtonListener);
        alertDialog = (CustomDialog)builder.create();
        alertDialog.setCanceledOnTouchOutside(true);
        return alertDialog;
    }

    public static CustomDialog showTwoButtonAlertDialog(Context context,
                                                        String title,
                                                        String message,
                                                        String positiveButtonName,
                                                        DialogInterface.OnClickListener positiveButtonListener,
                                                        String negativeButtonName,
                                                        DialogInterface.OnClickListener negativeButtonListener,
                                                        DialogInterface.OnCancelListener cancelListener)
    {
        CustomDialog alertDialog;
        CustomDialog.Builder builder = new CustomDialog.Builder(context);
        builder.setTitle(title==null?"":title);
        builder.setMessage(message == null ? "" : message);
        builder.setPositiveButton(positiveButtonName, positiveButtonListener);
        builder.setNegativeButton(negativeButtonName, negativeButtonListener);
        builder.setOnCancelListener(cancelListener);
        alertDialog = (CustomDialog) builder.create();
        alertDialog.setCanceledOnTouchOutside(true);
        return alertDialog;
    }

    /**
     *
     * @param context
     * @param icon -999不設定
     * @param title
     * @param message
     * @param positiveButtonName
     * @param positiveButtonListener
     * @param negativeButtonName
     * @param negativeButtonListener
     * @return
     */
    public static CustomDialog showTwoButtonAlertDialog(Context context,
                                                        int icon,
                                                        String title,
                                                        String message,
                                                        String positiveButtonName,
                                                        DialogInterface.OnClickListener positiveButtonListener,
                                                        String negativeButtonName,
                                                        DialogInterface.OnClickListener negativeButtonListener)
    {
        CustomDialog alertDialog;
        CustomDialog.Builder builder = new CustomDialog.Builder(context);
        builder.setTitle(title==null?"":title);
        builder.setMessage(message == null ? "" : message);
        builder.setPositiveButton(positiveButtonName, positiveButtonListener);
        builder.setNegativeButton(negativeButtonName, negativeButtonListener);
        builder.setCancelable(false);
        alertDialog = (CustomDialog)builder.create();
        alertDialog.setCanceledOnTouchOutside(true);
        return alertDialog;
    }

    public static CustomDialog showTwoButtonAlertDialog(Context context,
                                                        int icon,
                                                        String title,
                                                        String message,
                                                        String positiveButtonName,
                                                        DialogInterface.OnClickListener positiveButtonListener,
                                                        String negativeButtonName,
                                                        DialogInterface.OnClickListener negativeButtonListener,
                                                        DialogInterface.OnCancelListener cancelListener)
    {
        CustomDialog alertDialog;
        CustomDialog.Builder builder = new CustomDialog.Builder(context);
        builder.setTitle(title==null?"":title);
        builder.setMessage(message == null ? "" : message);
        builder.setPositiveButton(positiveButtonName, positiveButtonListener);
        builder.setNegativeButton(negativeButtonName, negativeButtonListener);
        builder.setOnCancelListener(cancelListener);
        alertDialog = (CustomDialog)builder.create();
        alertDialog.setCanceledOnTouchOutside(true);
        return alertDialog;
    }

    /**
     *
     * @param context
     * @param icon -999不設定
     * @param title
     * @param message
     * @param positiveButtonName
     * @param positiveButtonListener
     * @param negativeButtonName
     * @param negativeButtonListener
     * @param cancelable
     * @return
     */
    public static CustomDialog showTwoButtonAlertDialog(Context context,
                                                        int icon,
                                                        String title,
                                                        String message,
                                                        String positiveButtonName,
                                                        DialogInterface.OnClickListener positiveButtonListener,
                                                        String negativeButtonName,
                                                        DialogInterface.OnClickListener negativeButtonListener,
                                                        boolean cancelable)
    {
        CustomDialog alertDialog;
        CustomDialog.Builder builder = new CustomDialog.Builder(context);
        builder.setTitle(title==null?"":title);
        builder.setMessage(message == null ? "" : message);
        builder.setPositiveButton(positiveButtonName, positiveButtonListener);
        builder.setNegativeButton(negativeButtonName, negativeButtonListener);
        builder.setCancelable(cancelable);
        alertDialog = (CustomDialog)builder.create();
        alertDialog.setCanceledOnTouchOutside(cancelable);
        return alertDialog;
    }

    public static CustomDialog showTwoButtonAlertDialog(Context context,
                                                        int icon,
                                                        String title,
                                                        String message,
                                                        String positiveButtonName,
                                                        DialogInterface.OnClickListener positiveButtonListener,
                                                        String negativeButtonName,
                                                        DialogInterface.OnClickListener negativeButtonListener,
                                                        DialogInterface.OnCancelListener cancelListener,
                                                        boolean cancelable)
    {
        CustomDialog alertDialog;
        CustomDialog.Builder builder = new CustomDialog.Builder(context);
        builder.setTitle(title==null?"":title);
        builder.setMessage(message == null ? "" : message);
        builder.setPositiveButton(positiveButtonName, positiveButtonListener);
        builder.setNegativeButton(negativeButtonName, negativeButtonListener);
        builder.setOnCancelListener(cancelListener);
        builder.setCancelable(cancelable);
        alertDialog = (CustomDialog)builder.create();
        alertDialog.setCanceledOnTouchOutside(true);
        return alertDialog;
    }

    /**
     * @param context
     * @param title
     * @param view
     * @param positiveButtonName
     * @param positiveButtonListener
     * @param negativeButtonName
     * @param negativeButtonListener
     * @return
     */
    public static CustomDialog showTwoButtonViewDialog(Context context,
                                                       String title,
                                                       View view,
                                                       String positiveButtonName,
                                                       DialogInterface.OnClickListener positiveButtonListener,
                                                       String negativeButtonName,
                                                       DialogInterface.OnClickListener negativeButtonListener)
    {
        return showTwoButtonViewDialog(context, -999, title, view, positiveButtonName, positiveButtonListener, negativeButtonName, negativeButtonListener, false);
    }

    /**
     * @param context
     * @param LeftTitleText
     * @param RightTitleText
     * @param view
     * @param positiveButtonName
     * @param positiveButtonListener
     * @param negativeButtonName
     * @param negativeButtonListener
     * @return
     */
    public static CustomDialog showTwoButtonViewDialog(Context context,
                                                       String LeftTitleText,
                                                       String RightTitleText,
                                                       View view,
                                                       String positiveButtonName,
                                                       DialogInterface.OnClickListener positiveButtonListener,
                                                       String negativeButtonName,
                                                       DialogInterface.OnClickListener negativeButtonListener
    )
    {
        CustomDialog alertDialog = null;
        CustomDialog.Builder builder = new CustomDialog.Builder(context);

        builder.setTitle2(LeftTitleText, RightTitleText);

        builder.setView(view);
        builder.setPositiveButton(positiveButtonName, positiveButtonListener);

        if(null != negativeButtonName && null != negativeButtonListener)
        {
            builder.setNegativeButton(negativeButtonName, negativeButtonListener);
        }

        alertDialog = (CustomDialog)builder.create();
        alertDialog.setCanceledOnTouchOutside(true);
        if(CommonInfo.showMode==ShowMode.ORIGINAL){
            alertDialog.getWindow().getAttributes().windowAnimations = R.style.phone_Animation_Dialog_BottomIn300;
            alertDialog.getWindow().getAttributes().gravity = Gravity.CENTER_HORIZONTAL | Gravity.BOTTOM;
        }
        return alertDialog;
    }

    /**
     * @param context
     * @param titleid
     * @param view
     * @param positiveButtonName
     * @param positiveButtonListener
     * @param negativeButtonName
     * @param negativeButtonListener
     * @param cancelable
     * @return
     */
    public static CustomDialog showTwoButtonViewDialog(Context context,
                                                       int titleid,
                                                       View view,
                                                       String positiveButtonName,
                                                       DialogInterface.OnClickListener positiveButtonListener,
                                                       String negativeButtonName,
                                                       DialogInterface.OnClickListener negativeButtonListener,
                                                       boolean cancelable)
    {
        CustomDialog alertDialog = null;
        alertDialog.setTitle(titleid);
        CustomDialog.Builder builder = new CustomDialog.Builder(context);


        builder.setView(view);
        builder.setPositiveButton(positiveButtonName, positiveButtonListener);

        if(null != negativeButtonName && null != negativeButtonListener)
        {
            builder.setNegativeButton(negativeButtonName, negativeButtonListener);
        }

        builder.setCancelable(cancelable);
        alertDialog = (CustomDialog)builder.create();
        alertDialog.setCanceledOnTouchOutside(true);
        if(CommonInfo.showMode==ShowMode.ORIGINAL){
            alertDialog.getWindow().getAttributes().windowAnimations = R.style.phone_Animation_Dialog_BottomIn300;
            alertDialog.getWindow().getAttributes().gravity = Gravity.CENTER_HORIZONTAL | Gravity.BOTTOM;
        }
        return alertDialog;
    }

    /**
     * @param context
     * @param icon
     * @param title
     * @param view
     * @param positiveButtonName
     * @param positiveButtonListener
     * @param negativeButtonName
     * @param negativeButtonListener
     * @param cancelable
     * @return
     */
    public static CustomDialog showTwoButtonViewDialog(Context context,
                                                       int icon,
                                                       String title,
                                                       View view,
                                                       String positiveButtonName,
                                                       DialogInterface.OnClickListener positiveButtonListener,
                                                       String negativeButtonName,
                                                       DialogInterface.OnClickListener negativeButtonListener,
                                                       boolean cancelable)
    {
        CustomDialog alertDialog;
        CustomDialog.Builder builder = new CustomDialog.Builder(context);

        builder.setTitle(title);
        builder.setView(view);
        builder.setPositiveButton(positiveButtonName, positiveButtonListener);

        if(null != negativeButtonName && null != negativeButtonListener)
        {
            builder.setNegativeButton(negativeButtonName, negativeButtonListener);
        }

        builder.setCancelable(cancelable);
        alertDialog = (CustomDialog)builder.create();
        alertDialog.setCanceledOnTouchOutside(true);
        if(CommonInfo.showMode==ShowMode.ORIGINAL){
            alertDialog.getWindow().getAttributes().windowAnimations = R.style.phone_Animation_Dialog_BottomIn300;
            alertDialog.getWindow().getAttributes().gravity = Gravity.CENTER_HORIZONTAL | Gravity.BOTTOM;
        }
        return alertDialog;
    }

    public static CustomDialog showTwoButtonViewDialog(Context context,
                                                       int icon,
                                                       String title,
                                                       View view,
                                                       String positiveButtonName,
                                                       DialogInterface.OnClickListener positiveButtonListener,
                                                       String negativeButtonName,
                                                       DialogInterface.OnClickListener negativeButtonListener,
                                                       DialogInterface.OnCancelListener cancelListener,
                                                       boolean cancelable)
    {
        CustomDialog alertDialog;
        CustomDialog.Builder builder = new CustomDialog.Builder(context);
        builder.setTitle(title);
        builder.setView(view);
        builder.setPositiveButton(positiveButtonName, positiveButtonListener);
        if(null != negativeButtonName && null != negativeButtonListener)
        {
            builder.setNegativeButton(negativeButtonName, negativeButtonListener);
        }
        builder.setOnCancelListener(cancelListener);
        builder.setCancelable(cancelable);
        alertDialog = (CustomDialog) builder.create();
        alertDialog.setCanceledOnTouchOutside(true);
        if(CommonInfo.showMode==ShowMode.ORIGINAL){
            alertDialog.getWindow().getAttributes().windowAnimations = R.style.phone_Animation_Dialog_BottomIn300;
            alertDialog.getWindow().getAttributes().gravity = Gravity.CENTER_HORIZONTAL | Gravity.BOTTOM;
        }
        return alertDialog;
    }

    public static CustomDialog showOneButtonItemsDialog(Context context,
//            int icon,
                                                        String title,
                                                        String[] items,
                                                        AdapterView.OnItemClickListener onItemClickListener,
                                                        int checkedItem,
                                                        String positiveButtonName,
                                                        DialogInterface.OnClickListener positiveButtonListener,
//            DialogInterface.OnClickListener negativeButtonListener,
                                                        boolean cancelable)
    {
        CustomDialog alertDialog;
        CustomDialog.Builder builder = new CustomDialog.Builder(context);

//        if(-999 != icon)
//        {
//            builder.setIcon(icon);
//        }

        builder.setTitle(title);
        if(items!=null) {
            builder.setStyle(1);
            builder.setItems(items, onItemClickListener);
//            builder.setSingleChoiceItems(items, checkedItem, positiveButtonListener);
        }

        if(null != positiveButtonName && null != positiveButtonListener)
        {
            builder.setPositiveButton(positiveButtonName, positiveButtonListener);
        }

        builder.setCancelable(cancelable);
        alertDialog = (CustomDialog)builder.create();
        alertDialog.setCanceledOnTouchOutside(true);
        if(CommonInfo.showMode==ShowMode.ORIGINAL){
            alertDialog.getWindow().getAttributes().windowAnimations = R.style.phone_Animation_Dialog_BottomIn300;
            alertDialog.getWindow().getAttributes().gravity = Gravity.CENTER_HORIZONTAL | Gravity.BOTTOM;
        }
        return alertDialog;
    }

    public static CustomDialog showOneButtonItemsDialog(Context context,
//            int icon,
                                                        String title,
                                                        String[] items,
                                                        AdapterView.OnItemClickListener onItemClickListener,
                                                        int checkedItem,
                                                        DialogInterface.OnClickListener positiveButtonListener,
                                                        String negativeButtonName,
                                                        DialogInterface.OnClickListener negativeButtonListener,
                                                        DialogInterface.OnCancelListener cancelListener,
                                                        boolean cancelable)
    {
        CustomDialog alertDialog;
        CustomDialog.Builder builder = new CustomDialog.Builder(context);
//        if(-999 != icon)
//        {
//            builder.setIcon(icon);
//        }
        builder.setTitle(title);
        if(items!=null) {
            builder.setStyle(1);
            builder.setItems(items, onItemClickListener);
//            builder.setSingleChoiceItems(items, checkedItem, positiveButtonListener);
        }
        if(null != negativeButtonName && null != negativeButtonListener)
        {
            builder.setNegativeButton(negativeButtonName, negativeButtonListener);
        }
        builder.setOnCancelListener(cancelListener);
        builder.setCancelable(cancelable);
        alertDialog = (CustomDialog)builder.create();
        alertDialog.setCanceledOnTouchOutside(true);
        if(CommonInfo.showMode==ShowMode.ORIGINAL){
            alertDialog.getWindow().getAttributes().windowAnimations = R.style.phone_Animation_Dialog_BottomIn300;
            alertDialog.getWindow().getAttributes().gravity = Gravity.CENTER_HORIZONTAL | Gravity.BOTTOM;
        }
        return alertDialog;
    }

    public static CustomDialog showSimpleAlertDialog(final Context context,String message) {
        CustomDialog alertDialog;
        CustomDialog.Builder builder = new CustomDialog.Builder(context);
        builder.setTitle(CommonUtility.getMessageProperties(context).getProperty("MSG_NOTIFICATION", ""));
        builder.setMessage(message == null ? "" : message);
        builder.setNegativeButton(CommonUtility.getMessageProperties(context).getProperty("OK", ""), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        alertDialog = (CustomDialog)builder.create();
        alertDialog.setCanceledOnTouchOutside(true);
        return alertDialog;
    }

    /**
     * 訊息視窗含文字位置設定
     * @param context
     * @param message
     * @param gravity
     * @return
     */
    public static CustomDialog showSimpleAlertDialogWithGravity(final Context context,String message,int gravity) {
        CustomDialog alertDialog;
        CustomDialog.Builder builder = new CustomDialog.Builder(context);
        builder.setTitle(CommonUtility.getMessageProperties(context).getProperty("MSG_NOTIFICATION", ""));
        builder.setMessage(message == null ? "" : message);
        builder.setGravity(gravity);
        builder.setNegativeButton(CommonUtility.getMessageProperties(context).getProperty("OK", ""), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        alertDialog = (CustomDialog)builder.create();
        alertDialog.setCanceledOnTouchOutside(true);
        return alertDialog;
    }

    public static CustomDialog.Builder showCustomAlertDialog(final Context activity)
    {
        CustomDialog.Builder builder = new CustomDialog.Builder(activity);
        return builder;
    }

    public static void showPopFromBottomDialog(Dialog dialog, Drawable drawable)
    {
        dialog.show();
        Window window = dialog.getWindow();
        window.setGravity(Gravity.BOTTOM);
        window.setWindowAnimations(R.style.phone_Animation_Dialog_BottomIn300);
        window.setBackgroundDrawable(drawable);
        window.getDecorView().getLayoutParams().width = ViewGroup.LayoutParams.MATCH_PARENT;
        window.getDecorView().getLayoutParams().height = ViewGroup.LayoutParams.WRAP_CONTENT;
        WindowManager windowManager = window.getWindowManager();
        windowManager.updateViewLayout(window.getDecorView(), window.getDecorView().getLayoutParams());
    }

    /**
     * 導覽列-加入自選
     * @param activity
     * @param title  		標題
     * @param items			listview item
     * @param back 			是否有返回鍵
     * @param listener
     * @return
     */
    public static CustomDialog showCustomListAlertDialog(Context activity
            , String title
            , String[] items
            , boolean back
            , AdapterView.OnItemClickListener listener){

        return showCustomListAlertDialog(activity, title, items, back, listener, false);
    }

    /**
     * 20150414 [Weide Lai] 加入自選同步按鈕參數
     * @param activity
     * @param title
     * @param items
     * @param back
     * @param listener
     * @param showSyncBTN
     * @return
     */
    public static CustomDialog showCustomListAlertDialog(Context activity
            , String title
            , String[] items
            , boolean back
            , AdapterView.OnItemClickListener listener
            , boolean showSyncBTN){
        CustomDialog alertDialog;
        CustomDialog.Builder builder = new CustomDialog.Builder(activity);
        builder.setTitle(title==null?"":title);
        builder.setItems(items, listener);

        if(back)
        {
            builder.setNegativeButton(CommonUtility.getMessageProperties(activity).getProperty("BACK",""), new DialogInterface.OnClickListener()
            {
                public void onClick(DialogInterface dialog, int which)
                {
                    dialog.dismiss();
                }
            });
        }

        builder.setCancelable(back);
        builder.setShowSyncBTN(showSyncBTN);

        alertDialog = (CustomDialog) builder.create();
        alertDialog.setCanceledOnTouchOutside(true);
        if(CommonInfo.showMode==ShowMode.ORIGINAL){
            alertDialog.getWindow().getAttributes().windowAnimations = R.style.phone_Animation_Dialog_BottomIn300;
            alertDialog.getWindow().getAttributes().gravity = Gravity.CENTER_HORIZONTAL | Gravity.BOTTOM;
        }
        return alertDialog;
    }

    public static CustomDialog showSingleChoiceAlertDialog(Context activity
            , String title
            , String[] items
            , AdapterView.OnItemClickListener onItemClickListener
            , int checkedId
            , String negativeButtonName
            , DialogInterface.OnClickListener negativeButtonListener ) {
        CustomDialog alertDialog;
        CustomDialog.Builder builder = new CustomDialog.Builder(activity);
        builder.setTitle(title==null?"":title);
        builder.setStyle(1);
        builder.setItems((String[]) items, onItemClickListener);
        builder.setNegativeButton(negativeButtonName == null ? "" : negativeButtonName, negativeButtonListener);
        alertDialog = (CustomDialog)builder.create();
        alertDialog.setCanceledOnTouchOutside(true);
        if(CommonInfo.showMode==ShowMode.ORIGINAL){
            alertDialog.getWindow().getAttributes().windowAnimations = R.style.phone_Animation_Dialog_BottomIn300;
            alertDialog.getWindow().getAttributes().gravity = Gravity.CENTER_HORIZONTAL | Gravity.BOTTOM;
        }
        return alertDialog;
    }

    public static CustomDialog showSingleChoiceAlertDialog(Context activity
            , String title
            , String[] items
            , AdapterView.OnItemClickListener onItemClickListener
            , int checkedId
            , String negativeButtonName
            , DialogInterface.OnClickListener negativeButtonListener
            , DialogInterface.OnCancelListener cancelListener) {
        CustomDialog alertDialog;
        CustomDialog.Builder builder = new CustomDialog.Builder(activity);
        builder.setTitle(title==null?"":title);
        builder.setStyle(1);
        builder.setItems(items, onItemClickListener);
        builder.setNegativeButton(negativeButtonName == null ? "" : negativeButtonName, negativeButtonListener);
        builder.setOnCancelListener(cancelListener);
        alertDialog = (CustomDialog)builder.create();
        alertDialog.setCanceledOnTouchOutside(true);
        if(CommonInfo.showMode==ShowMode.ORIGINAL){
            alertDialog.getWindow().getAttributes().windowAnimations = R.style.phone_Animation_Dialog_BottomIn300;
            alertDialog.getWindow().getAttributes().gravity = Gravity.CENTER_HORIZONTAL | Gravity.BOTTOM;
        }
        return alertDialog;
    }

    /**
     * 三個按鈕的Dialog
     * @param context
     * @param LeftTitleText
     * @param CenterTitleText
     * @param RightTitleText
     * @param view
     * @param positiveButtonName
     * @param positiveButtonListener
     * @param neutralButtonName
     * @param neutralButtonListener
     * @param negativeButtonName
     * @param negativeButtonListener
     * @return
     */
    public static CustomDialog showThreeButtonViewDialog_ThreeTitle(Context context,
                                                                    String LeftTitleText,
                                                                    String CenterTitleText,
                                                                    String RightTitleText,
                                                                    View view,
                                                                    String positiveButtonName,
                                                                    DialogInterface.OnClickListener positiveButtonListener,
                                                                    String neutralButtonName,
                                                                    DialogInterface.OnClickListener neutralButtonListener,
                                                                    String negativeButtonName,
                                                                    DialogInterface.OnClickListener negativeButtonListener
    )
    {
        CustomDialog alertDialog = null;
        CustomDialog.Builder builder = new CustomDialog.Builder(context);

        builder.setTitle3(LeftTitleText, CenterTitleText, RightTitleText);

        builder.setView(view);
        builder.setPositiveButton(positiveButtonName, positiveButtonListener);

        if(null != negativeButtonName && null != negativeButtonListener)
        {
            builder.setNegativeButton(negativeButtonName, negativeButtonListener);
        }

        if(null != neutralButtonName && null != neutralButtonListener)
        {
            builder.setNeutralButton(neutralButtonName, neutralButtonListener);
        }

        alertDialog = (CustomDialog)builder.create();
        alertDialog.setCanceledOnTouchOutside(true);
        return alertDialog;
    }

    public static CustomDialog showThreeButtonViewDialog(Context context,
                                                         String title,
                                                         String message,
                                                         String leftButtonName,
                                                         DialogInterface.OnClickListener leftButtonListener,
                                                         String centerButtonName,
                                                         DialogInterface.OnClickListener centerButtonListener,
                                                         String rightButtonName,
                                                         DialogInterface.OnClickListener rightButtonListener,
                                                         boolean cancelable)
    {
        CustomDialog alertDialog;
        CustomDialog.Builder builder = new CustomDialog.Builder(context);
        builder.setTitle(title==null?"":title);
        builder.setMessage(message == null ? "" : message);
        builder.setPositiveButton(leftButtonName, leftButtonListener);
        builder.setNeutralButton(centerButtonName, centerButtonListener);
        builder.setNegativeButton(rightButtonName, rightButtonListener);
        builder.setCancelable(cancelable);
        alertDialog = (CustomDialog)builder.create();
        alertDialog.setCanceledOnTouchOutside(cancelable);
        return alertDialog;
    }

    /**
     * 保持dialog不會消失
     * @param tdialog  目標dialog
     * @param isKeep   false:dialog不會消失,true:dialog會消失
     */
    public static void keepDialog(CustomDialog tdialog,boolean isKeep)
    {
        try {
            Field field = tdialog.getClass().getSuperclass().getDeclaredField("mShowing");
            field.setAccessible(true);
            field.set(tdialog, isKeep);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 顯示離開程式的Dialog
     * @param activity
     */
    public static void showAppExitDilog(final Activity activity){
        Properties properties = CommonUtility.getMessageProperties(activity);
        showTwoButtonAlertDialog(activity
                , properties.getProperty("EXIT_APP_ASK"), properties.getProperty("CONFIRM"), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                CommonUtility.getSharedPreferences(activity).edit().putString(SharePreferenceKey.RECORD_USER_EXIT_TIME
                        , CommonUtility.getCurrentDate(activity)).commit();
                dialog.dismiss();
                System.exit(0);
            }
        }, properties.getProperty("CANCEL"), new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        }, new DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialog) {
                dialog.dismiss();
            }
        }).show();
    }

    /**
     * 顯示傳送狀態點選後的選項列表
     * @param context
     * @param data
     * @param listener
     */
    public static CustomDialog showSendStatusMenu(final Context context
            , Bundle data
            , AdapterView.OnItemClickListener listener)
    {
        final CustomDialog alertDialog;
        CustomDialog.Builder builder = new CustomDialog.Builder(context);
        builder.setItems(data.getStringArray(DIALOG_MENU_CONTENT), listener);
        if(data.getString(DIALOG_TITLE) != null)
        {
            builder.setTitle(data.getString(DIALOG_TITLE));
        }
        builder.setNegativeButton(data.containsKey(DIALOG_BACK) ? data.getString(DIALOG_BACK) : "", new DialogInterface.OnClickListener()
        {
            public void onClick(DialogInterface dialog, int which)
            {
                dialog.dismiss();
            }
        });
        alertDialog = (CustomDialog) builder.create();
        alertDialog.setCanceledOnTouchOutside(true);
        if(CommonInfo.showMode== ShowMode.ORIGINAL){
            alertDialog.getWindow().getAttributes().windowAnimations = R.style.phone_Animation_Dialog_BottomIn300;
            alertDialog.getWindow().getAttributes().gravity = Gravity.CENTER_HORIZONTAL | Gravity.BOTTOM;
        }
        ((Activity)context).runOnUiThread(new Runnable() {
            @Override
            public void run() {
                alertDialog.show();
            }
        });
        return alertDialog;
    }
}
