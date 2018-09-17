package com.samfdl.demo.lib.x5;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Message;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.tencent.smtt.export.external.interfaces.IX5WebChromeClient;
import com.tencent.smtt.export.external.interfaces.JsPromptResult;
import com.tencent.smtt.export.external.interfaces.JsResult;
import com.tencent.smtt.sdk.WebChromeClient;
import com.tencent.smtt.sdk.WebView;
import com.tencent.smtt.sdk.WebViewClient;

import java.util.Map;

/**
 * Created by caiying on 22/04/2016.
 */
public class X5WebChromeClient extends WebChromeClient {
    private X5WebView x5WebView;
    private X5WebChromeClientInterface mX5WebChromeClientInterface;

    public X5WebChromeClient(X5WebView x5WebView) {
        this.x5WebView = x5WebView;
    }

    public void setX5WeChromeInterface(X5WebChromeClientInterface x5WebChromeClientInterface) {
        this.mX5WebChromeClientInterface = x5WebChromeClientInterface;
    }

    @Override
    public boolean onJsConfirm(WebView arg0, String arg1, String arg2, JsResult arg3) {
        return super.onJsConfirm(arg0, arg1, arg2, arg3);
    }

    View myVideoView;
    View myNormalView;
    IX5WebChromeClient.CustomViewCallback callback;

    /**
     * 全屏播放配置
     */
    @Override
    public void onShowCustomView(View view, IX5WebChromeClient.CustomViewCallback customViewCallback) {
//            FrameLayout normalView = (FrameLayout) ((Activity) getContext()).findViewById(R.id.web_filechooser);
//            ViewGroup viewGroup = (ViewGroup) normalView.getParent();
//            viewGroup.removeView(normalView);
//            viewGroup.addView(view);
        myVideoView = view;
//            myNormalView = normalView;
        callback = customViewCallback;
    }

    @Override
    public void onHideCustomView() {
        if (callback != null) {
            callback.onCustomViewHidden();
            callback = null;
        }
        if (myVideoView != null) {
            ViewGroup viewGroup = (ViewGroup) myVideoView.getParent();
            viewGroup.removeView(myVideoView);
            viewGroup.addView(myNormalView);
        }
    }

//    @Override
//    public void openFileChooser(ValueCallback<Uri> uploadfile, String acceptType, String captureType) {
////        Intent i = new Intent(Intent.ACTION_GET_CONTENT);
////        i.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
////        i.addCategory(Intent.CATEGORY_OPENABLE);
////        i.setType("*/*");
////        ((Activity) (x5WebView.getContext())).startActivityForResult(Intent.createChooser(i, "choose files"), X5WebView.FILE_CHOOSER);
////        super.openFileChooser(uploadFile, acceptType, captureType);
//        PhotoPickerIntent intent = new PhotoPickerIntent(x5WebView.getContext());
//        intent.setPhotoCount(9);
//        intent.setShowCamera(true);
//        intent.setShowGif(true);
//        ((Activity) (x5WebView.getContext())).startActivityForResult(intent, X5WebView.FILE_CHOOSER);
//    }

    /**
     * webview 的窗口转移
     */
    @Override
    public boolean onCreateWindow(WebView arg0, boolean arg1, boolean arg2, Message msg) {
        if (X5WebView.isSmallWebViewDisplayed) {

            WebView.WebViewTransport webViewTransport = (WebView.WebViewTransport) msg.obj;
            WebView webView = new WebView(x5WebView.getContext()) {

                protected void onDraw(Canvas canvas) {
                    super.onDraw(canvas);
                    Paint paint = new Paint();
                    paint.setColor(Color.GREEN);
                    paint.setTextSize(15);
                    canvas.drawText("新建窗口", 10, 10, paint);
                }

            };
            webView.setWebViewClient(new WebViewClient() {
                public boolean shouldOverrideUrlLoading(WebView arg0, String arg1) {
                    arg0.loadUrl(arg1);
                    return true;
                }

            });
            FrameLayout.LayoutParams lp = new FrameLayout.LayoutParams(400, 600);
            lp.gravity = Gravity.CENTER_HORIZONTAL | Gravity.CENTER_VERTICAL;
            x5WebView.addView(webView, lp);
            webViewTransport.setWebView(webView);
            msg.sendToTarget();
        }
        return true;
    }

    @Override
    public boolean onJsAlert(WebView arg0, String arg1, String arg2, JsResult arg3) {
        /**
         * 这里写入你自定义的window alert
         */
        // AlertDialog.Builder builder = new Builder(getContext());
        // builder.setTitle("X5内核");
        // builder.setPositiveButton("确定", new
        // DialogInterface.OnClickListener() {
        //
        // @Override
        // public void onClick(DialogInterface dialog, int which) {
        // // TODO Auto-generated method stub
        // dialog.dismiss();
        // }
        // });
        // builder.show();
        // arg3.confirm();
        // return true;
        Log.i("yuanhaizhou", "setX5webview = null");
        return super.onJsAlert(null, "www.baidu.com", "aa", arg3);
    }

    /**
     * 对应js 的通知弹框 ，可以用来实现js 和 android之间的通信
     */
    @Override
    public boolean onJsPrompt(WebView arg0, String arg1, String arg2, String arg3, JsPromptResult arg4) {
        // 在这里可以判定js传过来的数据，用于调起android native 方法
        if (isMsgPrompt(arg1)) {
            return onJsPrompt(arg2, arg3);
        }
        return super.onJsPrompt(arg0, arg1, arg2, arg3, arg4);
    }

    @Override
    public void onReceivedTitle(WebView view, final String title) {
        super.onReceivedTitle(view, title);
        Log.i("yuanhaizhou", "webpage title is " + title);

        if (mX5WebChromeClientInterface != null) {
            mX5WebChromeClientInterface.onReceivedTitle(view, title);
        }
    }

    @Override
    public void onProgressChanged(WebView webView, int newProgress) {
        super.onProgressChanged(webView, newProgress);
        if (mX5WebChromeClientInterface != null) {
            mX5WebChromeClientInterface.onProgressChanged(webView, newProgress);
        }
    }

    /**
     * 当webchromeClient收到 web的prompt请求后进行拦截判断，用于调起本地android方法
     *
     * @param methodName 方法名称
     * @param blockName  区块名称
     * @return true ：调用成功 ； false ：调用失败
     */
    private boolean onJsPrompt(String methodName, String blockName) {
        String tag = SecurityJsBridgeBundle.BLOCK + blockName + "-" + SecurityJsBridgeBundle.METHOD + methodName;
        Map<String, Object> mJsBridges = x5WebView.getJsBridges();
        if (mJsBridges != null && mJsBridges.containsKey(tag)) {
            ((SecurityJsBridgeBundle) mJsBridges.get(tag)).onCallMethod();
            return true;
        } else {
            return false;
        }
    }

    /**
     * 判定当前的prompt消息是否为用于调用native方法的消息
     *
     * @param msg 消息名称
     * @return true 属于prompt消息方法的调用
     */
    private boolean isMsgPrompt(String msg) {
        return msg != null && msg.startsWith(SecurityJsBridgeBundle.PROMPT_START_OFFSET);
    }
}
