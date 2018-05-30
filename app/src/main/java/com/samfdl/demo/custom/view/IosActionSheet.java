package com.samfdl.demo.custom.view;

import android.app.Dialog;
import android.content.Context;
import android.view.Display;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.samfdl.demo.R;

public class IosActionSheet {
    private Dialog mDialog;

    public IosActionSheet(Context context, View contentView) {
        if (context == null)
            return;
        mDialog = new Dialog(context, R.style.ActionSheetDialogStyle);
        mDialog.setContentView(contentView);

        Window window = mDialog.getWindow();
        WindowManager m = window.getWindowManager();
        Display display = m.getDefaultDisplay(); // 获取屏幕宽、高用
        WindowManager.LayoutParams params = window.getAttributes(); // 获取对话框当前的参数值
        params.width = display.getWidth();
        window.setAttributes(params);
        window.setGravity(Gravity.BOTTOM);  //此处可以设置dialog显示的位置
        window.setWindowAnimations(R.style.ActionSheetDialogAnimation);  //添加动画
    }

    public void dismiss() {
        if (mDialog != null && mDialog.isShowing()) {
            mDialog.dismiss();
        }
    }

    public void show() {
        if (mDialog != null && !mDialog.isShowing()) {
            mDialog.show();
        }
    }

    public static Builder createBuilder(Context context) {
        return new Builder(context);
    }

    public static class Builder implements View.OnClickListener {
        private Context mContext;
        private ActionSheetListener mListener;
        IosActionSheet iosActionSheet = null;

        public Builder(Context context) {
            mContext = context;
        }

        public Builder setListener(ActionSheetListener listener) {
            this.mListener = listener;
            return this;
        }

        public IosActionSheet show() {
            //创建View,设置监听器等
            View view = View.inflate(mContext, R.layout.dialog_custom_iosactionsheet, null);

            TextView save = view.findViewById(R.id.save);
            save.setOnClickListener(this);

            LinearLayout wechat = view.findViewById(R.id.wechat);
            wechat.setOnClickListener(this);
            LinearLayout friends = view.findViewById(R.id.friends);
            friends.setOnClickListener(this);
            LinearLayout weibo = view.findViewById(R.id.weibo);
            weibo.setOnClickListener(this);
            LinearLayout qq = view.findViewById(R.id.qq);
            qq.setOnClickListener(this);

            //取消按钮
            TextView cancel = view.findViewById(R.id.cancel);
            cancel.setOnClickListener(this);
            iosActionSheet = new IosActionSheet(mContext, view);
            iosActionSheet.show();

            return iosActionSheet;
        }

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.save:
                    if (mListener != null) {
                        mListener.onClick(R.id.save);
                    }
                    break;
                case R.id.wechat:
                    if (mListener != null) {
                        mListener.onClick(R.id.wechat);
                    }
                    break;
                case R.id.friends:
                    if (mListener != null) {
                        mListener.onClick(R.id.friends);
                    }
                    break;
                case R.id.weibo:
                    if (mListener != null) {
                        mListener.onClick(R.id.weibo);
                    }
                    break;
                case R.id.qq:
                    if (mListener != null) {
                        mListener.onClick(R.id.qq);
                    }
                    break;
                case R.id.cancel:
                    if (iosActionSheet != null) {
                        iosActionSheet.dismiss();
                    }
                    break;
            }
        }
    }

    public interface ActionSheetListener {
        void onClick(int index);
    }
}