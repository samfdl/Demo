package com.samfdl.demo.custom.view;

import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.samfdl.demo.R;

public class IosDialog {
    private Dialog mDialog;

    public IosDialog(Context context, View contentView) {
        if (context == null)
            return;
        mDialog = new Dialog(context, R.style.IosDialog);
        mDialog.setContentView(contentView);
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
        IosDialog iosActionSheet = null;

        public Builder(Context context) {
            mContext = context;
        }

        public Builder setListener(ActionSheetListener listener) {
            this.mListener = listener;
            return this;
        }

        public IosDialog show() {
            //创建View,设置监听器等
            View view = View.inflate(mContext, R.layout.dialog_custom_iosdialog, null);

            TextView text = view.findViewById(R.id.text);
            text.setOnClickListener(this);

            //取消按钮
            TextView cancel = view.findViewById(R.id.cancel);
            cancel.setOnClickListener(this);
            iosActionSheet = new IosDialog(mContext, view);
            iosActionSheet.show();

            return iosActionSheet;
        }

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.text:
                    if (mListener != null) {
                        mListener.onClick(R.id.text);
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