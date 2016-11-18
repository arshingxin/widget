package com.widget;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.variable.skin.object.SkinKey;
import com.variable.utility.SkinUtility;

public class CustomDialog extends Dialog {

    private View layout;

    private DialogInterface.OnClickListener
            positiveButtonClickListener,
            neutralButtonClickListener,
            negativeButtonClickListener;

    public interface SyncButtononClickListener{
        public void onClick(Button BTN);
    }

    public void setSyncButtononClickListener(SyncButtononClickListener listener)
    {
        mSyncButtononClickListener = listener;
    }

    private static SyncButtononClickListener mSyncButtononClickListener;

    public CustomDialog(Context context) {
        super(context);
    }

    public CustomDialog(Context context, int theme) {
        super(context, theme);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        if (layout.findViewById(R.id.dialog_button_confirm) != null) {
            ((Button) layout.findViewById(R.id.dialog_button_confirm)).setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    if (positiveButtonClickListener != null) {
                        positiveButtonClickListener.onClick(CustomDialog.this, DialogInterface.BUTTON_POSITIVE);
                    }
                    CustomDialog.this.dismiss();
                }
            });
        }
        if (layout.findViewById(R.id.dialog_button_cancel) != null) {
            ((Button) layout.findViewById(R.id.dialog_button_cancel)).setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    if (negativeButtonClickListener != null) {
                        negativeButtonClickListener.onClick(CustomDialog.this, DialogInterface.BUTTON_NEGATIVE);
                    }
                    CustomDialog.this.dismiss();
                }
            });
        }

        if (layout.findViewById(R.id.dialog_button_center) != null) {
            ((Button) layout.findViewById(R.id.dialog_button_center)).setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    if (neutralButtonClickListener != null) {
                        neutralButtonClickListener.onClick(CustomDialog.this, DialogInterface.BUTTON_NEUTRAL);
                    }

                }
            });
        }

        this.setContentView(layout);
    }

    void setLayout(View layout) {
        this.layout = layout;
    }

    void setPositiveButtonClickListener(DialogInterface.OnClickListener onClickListener) {
        this.positiveButtonClickListener = onClickListener;
    }

    void setNeutralButtonClickListener(DialogInterface.OnClickListener onClickListener) {
        this.neutralButtonClickListener = onClickListener;
    }

    void setNegativeButtonClickListener(DialogInterface.OnClickListener onClickListener) {
        this.negativeButtonClickListener = onClickListener;
    }

    /**
     * Helper class for creating a custom dialog
     */
    public static class Builder {
        private Context context;
        private View layout;
        private int style=0;
        private int position=0;
        private String title;
        private String message;
        private String positiveButtonText;
        private String negativeButtonText;
        private String neutralButtonText;
        //判斷是否顯示返回按鈕
        private boolean cancelable = true;
        private boolean isShowSyncBTN = false;
        private View view_context;
        private String[] items;
        private ArrayAdapter<String> adapter;

        private DialogInterface.OnCancelListener onCancelListener;
        private DialogInterface.OnKeyListener 	  onKeyListener;
        private DialogInterface.OnClickListener  positiveButtonClickListener,negativeButtonClickListener,neutralButtonClickListener;
        //listview OnItemClickListener
        private AdapterView.OnItemClickListener   OnItemClickListener;

        private String title_2_left = "";
        private String title_2_right = "";

        private String title_3_right = "";
        private String title_3_center = "";
        private String title_3_left = "";

        private int dialog_style = 0;

        private int Gravity = android.view.Gravity.CENTER;

        public Builder(Context context,int style) {
            this.style=style;
            this.context = context;
        }
        public Builder(Context context) {
            this.style=0;
            this.context = context;
        }
        public Builder setPosition(int position) {
            this.position = position;
            return this;
        }

        public Builder setTitle(String title) {
            this.title = title;
            return this;
        }

        public Builder setTitle2(String left,String right) {
            this.title_2_left = left;
            this.title_2_right = right;
            return this;
        }

        public Builder setTitle3(String left,String center,String right) {
            this.title_3_left = left;
            this.title_3_center = center;
            this.title_3_right = right;
            return this;
        }

        /**
         * style = 2 為 2階版本模式(白色),預設0為3階版本
         * @param style
         */
        public void setStyle(int style)
        {
            this.dialog_style = style;
        }

        public Builder setItems(String[] data) {
            this.items = data;
            return this;
        }

        public void setGravity(int gravity)
        {
            this.Gravity = gravity;
        }

        public Builder setItems(String[] data , AdapterView.OnItemClickListener listener) {
            this.items = data;
            this.OnItemClickListener = listener;
            return this;
        }

        public Builder setView(View view) {
            this.view_context = view;
            return this;
        }

        public Builder setMessage(String msg)
        {
            this.message = msg;
            return this;
        }

        public Builder setCancelable(boolean cancelable) {
            this.cancelable = cancelable;
            return this;
        }

        public Builder setShowSyncBTN(boolean isShowSyncBTN) {
            this.isShowSyncBTN = isShowSyncBTN;
            return this;
        }

        public Builder setOnCancelListener(DialogInterface.OnCancelListener listener) {
            this.onCancelListener = listener;
            return this;
        }

        public Builder setOnKeyListener(DialogInterface.OnKeyListener listener) {
            this.onKeyListener = listener;
            return this;
        }

        public Builder setPositiveButton(int positiveButtonText,
                                         DialogInterface.OnClickListener listener) {
            this.positiveButtonText = (String) context
                    .getText(positiveButtonText);
            this.positiveButtonClickListener = listener;
            return this;
        }

        public Builder setPositiveButton(CharSequence positiveButtonText,
                                         DialogInterface.OnClickListener listener) {
            this.positiveButtonText = positiveButtonText.toString();
            this.positiveButtonClickListener = listener;
            return this;
        }

        public Builder setNegativeButton(int negativeButtonText,
                                         DialogInterface.OnClickListener listener) {
            this.negativeButtonText = (String) context
                    .getText(negativeButtonText);
            this.negativeButtonClickListener = listener;
            return this;
        }

        public Builder setNegativeButton(CharSequence negativeButtonText,
                                         DialogInterface.OnClickListener listener) {
            this.negativeButtonText = negativeButtonText.toString();
            this.negativeButtonClickListener = listener;
            return this;
        }

        public Builder setNeutralButton(int neutralButtonText,
                                        DialogInterface.OnClickListener listener) {
            this.neutralButtonText = (String) context
                    .getText(neutralButtonText);
            this.neutralButtonClickListener = listener;
            return this;
        }

        public Builder setNeutralButton(CharSequence neutralButtonText,
                                        DialogInterface.OnClickListener listener) {
            this.neutralButtonText = neutralButtonText.toString();
            this.neutralButtonClickListener = listener;
            return this;
        }

        public Dialog create() {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            // instantiate the dialog with the custom Theme
            CustomDialog dialog = new CustomDialog(context,R.style.CustomDialogStyle);

//            if(dialog_style == 2)
//            {
//                layout = inflater.inflate(R.layout.custom_dialog_v2, null);
//                //title背景色
//                layout.findViewById(R.id.mitake_dialog_layout_title).setBackgroundColor(SkinUtility.getColor(SkinKey.Z07));
//            }
//            else
//            {
                layout = inflater.inflate(R.layout.custom_dialog, null);
                //title背景色
                layout.findViewById(R.id.mitake_dialog_layout_title).setBackgroundColor(SkinUtility.getColor(SkinKey.DEFAULT_COLOR));
//            }

            // set the dialog title
            if(!title_2_left.equals("") || !title_2_right.equals(""))
            {
                LinearLayout l1 = (LinearLayout) (layout.findViewById(R.id.layout_dialog_title_one));
                l1.setVisibility(View.GONE);

                LinearLayout l2 = (LinearLayout) (layout.findViewById(R.id.layout_dialog_title_two));
                l2.setVisibility(View.VISIBLE);
                TextView tv_lstr = (TextView) (layout.findViewById(R.id.mitake_dialog_title2_left));
                tv_lstr.setText(title_2_left);
                TextView tv_rstr = (TextView) (layout.findViewById(R.id.mitake_dialog_title2_right));
                tv_rstr.setText(title_2_right);
            }
            else if(!title_3_left.equals("") || !title_3_right.equals("") || !title_3_center.equals(""))
            {
                LinearLayout l1 = (LinearLayout) (layout.findViewById(R.id.layout_dialog_title_one));
                l1.setVisibility(View.GONE);

                LinearLayout l3 = (LinearLayout) (layout.findViewById(R.id.layout_dialog_title_three));
                l3.setVisibility(View.VISIBLE);
                TextView tv_lstr = (TextView) (layout.findViewById(R.id.mitake_dialog_title3_left));
                tv_lstr.setText(title_3_left);
                TextView tv_rstr = (TextView) (layout.findViewById(R.id.mitake_dialog_title3_right));
                tv_rstr.setText(title_3_right);
                TextView tv_cstr = (TextView) (layout.findViewById(R.id.mitake_dialog_title3_center));
                tv_cstr.setText(title_3_center);

            }
            else if (title != null && !title.equals("")) {
                ((TextView) layout.findViewById(R.id.mitake_dialog_title)).setText(title);
            } else {
                // if no dialog title just set the visibility to GONE
                layout.findViewById(R.id.mitake_dialog_layout_title).setVisibility(View.GONE);
                //顯示上方元獎
                ((LinearLayout) layout.findViewById(R.id.mitake_dialog_context)).setBackgroundResource(R.drawable.custom_dialog_title_bg);
            }

            LinearLayout layout_button = ((LinearLayout) layout.findViewById(R.id.mitake_dialog_layout_button));
            ImageView iv_context_divider =  (ImageView) layout.findViewById(R.id.mitake_dialog_context_divider);
            ImageView iv_button_divider =  (ImageView) layout.findViewById(R.id.dialog_button_divider);

            if (message != null)
            {
                TextView tv_msg = (TextView) layout.findViewById(R.id.mitake_dialog_context_message);
                tv_msg.setVisibility(View.VISIBLE);
                tv_msg.setGravity(Gravity);
                tv_msg.setText(message);
            }
            else if(view_context != null)
            {
                ((TextView) layout.findViewById(R.id.mitake_dialog_context_message)).setVisibility(View.GONE);
                ((LinearLayout) layout.findViewById(R.id.mitake_dialog_context))
                        .addView(view_context, new LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.MATCH_PARENT));
            }
            else if(items != null)
            {

                if(style==1){
                    //adapter = new ArrayAdapter<String>(context, android.R.layout.simple_list_item_single_choice, items);
                    adapter = new ArrayAdapter<String>(context, R.layout.list_item_single_choice_view, items);
                }else{
                    adapter = new ArrayAdapter<String>(context, R.layout.mitake_dialog_listview_item, items);
                }


                ListView list = new ListView(context);
                if(style==1){
                    list.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
                    list.setItemChecked(position, true);
                }else
                {
                    list.setSelection(position);
                }
                //listview分隔線
                ColorDrawable divider = new ColorDrawable(context.getResources().getColor(R.color.dilog_divider_color));
                //當items項目只有一個不用顯示分隔線.
                if(items.length > 1)
                {
                    list.setDivider(divider);
                }
                list.setDividerHeight(1);

                list.setAdapter(adapter);
                if(style==1){
                    list.setItemChecked(position, true);
                }else
                {
                    list.setSelection(position);
                }
                list.setOnItemClickListener(OnItemClickListener);
                if(!cancelable)
                {
                    iv_context_divider.setVisibility(View.GONE);
                    layout_button.setVisibility(View.GONE);
                    ((LinearLayout) layout.findViewById(R.id.mitake_dialog_context)).setBackgroundResource(R.drawable.custom_dialog_button_bg);
                }
                else
                {
                    //隱藏按鈕分隔線
                    iv_button_divider.setVisibility(View.GONE);
                }

                ((TextView) layout.findViewById(R.id.mitake_dialog_context_message)).setVisibility(View.GONE);
                ((LinearLayout) layout.findViewById(R.id.mitake_dialog_context))
                        .addView(list, new LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.MATCH_PARENT));
            }


            // set the OnCancelListener
            dialog.setCancelable(cancelable);
            if (cancelable)
                dialog.setCanceledOnTouchOutside(true);
            if (onCancelListener != null)
                dialog.setOnCancelListener(onCancelListener);
            // set the onKeyListener
            if (onKeyListener != null)
                dialog.setOnKeyListener(onKeyListener);


            // set the confirm button
            if (positiveButtonText != null) {
                ((Button) layout.findViewById(R.id.dialog_button_confirm)).setText(positiveButtonText);
                ((Button) layout.findViewById(R.id.dialog_button_confirm)).setBackgroundResource(SkinUtility.getColor(SkinKey.W03));
                ((Button) layout.findViewById(R.id.dialog_button_confirm)).requestFocus();
                if (positiveButtonClickListener != null)
                    dialog.setPositiveButtonClickListener(positiveButtonClickListener);
            } else {
                // if no confirm button just set the visibility to GONE
                layout.findViewById(R.id.dialog_button_confirm).setVisibility(View.GONE);
            }
            // set the cancel button
            if (negativeButtonText != null) {
                ((Button) layout.findViewById(R.id.dialog_button_cancel)).setText(negativeButtonText);
                ((Button) layout.findViewById(R.id.dialog_button_cancel)).setBackgroundResource(SkinUtility.getColor(SkinKey.W00));
                if (negativeButtonClickListener != null)
                    dialog.setNegativeButtonClickListener(negativeButtonClickListener);
            } else {
                // if no cancel button just set the visibility to GONE
                layout.findViewById(R.id.dialog_button_cancel).setVisibility(View.GONE);
            }

            // set the center Button
            if (neutralButtonText != null) {
                ((Button) layout.findViewById(R.id.dialog_button_center)).setVisibility(View.VISIBLE);

                ((ImageView) layout.findViewById(R.id.dialog_button_divider2)).setVisibility(View.VISIBLE);

                ((Button) layout.findViewById(R.id.dialog_button_center)).setText(neutralButtonText);
                if (neutralButtonClickListener != null)
                    dialog.setNeutralButtonClickListener(neutralButtonClickListener);
            } else {
                // if no confirm button just set the visibility to GONE
                layout.findViewById(R.id.dialog_button_center).setVisibility(View.GONE);
                ((ImageView) layout.findViewById(R.id.dialog_button_divider2)).setVisibility(View.GONE);
            }

            dialog.setLayout(layout);
            return dialog;
        }

        public Dialog show() {
            Dialog d = create();
            d.show();
            return d;
        }
    }

}
