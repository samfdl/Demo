package com.samfdl.demo.custom.view;

import android.app.Dialog;
import android.content.Context;
import android.view.Display;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.samfdl.demo.R;

public class IosActionSheetLogout {
    private Dialog mDialog;

    public IosActionSheetLogout(Context context, View contentView) {
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
        IosActionSheetLogout iosActionSheet = null;

        public Builder(Context context) {
            mContext = context;
        }

        public Builder setListener(ActionSheetListener listener) {
            this.mListener = listener;
            return this;
        }

        public IosActionSheetLogout show() {
            //创建View,设置监听器等
            View view = View.inflate(mContext, R.layout.dialog_custom_iosactionsheet_logout, null);

            TextView logout = view.findViewById(R.id.logout);
            logout.setOnClickListener(this);

            //取消按钮
            TextView cancel = view.findViewById(R.id.cancel);
            cancel.setOnClickListener(this);
            iosActionSheet = new IosActionSheetLogout(mContext, view);
            iosActionSheet.show();

            return iosActionSheet;
        }

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.logout:
                    if (mListener != null) {
                        mListener.onClick(R.id.logout);
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