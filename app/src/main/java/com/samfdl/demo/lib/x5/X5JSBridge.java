package com.samfdl.demo.lib.x5;

import android.content.Context;
import android.webkit.JavascriptInterface;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by caiying on 22/04/2016.
 */
public class X5JSBridge implements WebViewJavaScriptFunction {
    /**
     * 页面跳转
     */
    private static final String PNAME_USER_DETAIL = "user_detail";      // 用户详情页面
    private static final String PNAME_CHANNEL_CHAT = "channel_chat";    // 频道聊天界面
    private static final String PNAME_REPORT_PAGE = "report_page";      // 投诉
    private static final String PNAME_CHANNEL_GROUP = "channel_group";  // 加入群组
    private static final String PNAME_COURSE_DETAIL_PAGE = "course_detail_page";  // 课程内容页面
    private static final String PNAME_HOMEWORK_PAGE = "homework_page";
    private static final String PNAME_MEIQIA_PAGE = "meiqia_page";      // 洽客服页面
    private static final String PNAME_BIND_PHONE_PAGE = "bind_phone_page";    // 绑定手机号页面

    private Context mContext;

    @Override
    public void onJsFunctionCalled(String tag) {
    }

    public X5JSBridge(Context context) {
        this.mContext = context;

        Toast.makeText(context, "jdjljd", Toast.LENGTH_SHORT).show();
    }

    /**
     * 网页画出边界后是否可以回弹（bounces）
     */
    @JavascriptInterface
    public void shouldBounces(String json) {
        // Android暂不处理，主要用于IOS设备
    }

    /**
     * 解析Json对象
     */
    private JSONObject parseJson(String json) {
        JSONObject jsonObject = null;
        try {
            jsonObject = new JSONObject(json);
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
        return jsonObject;
    }

    /**
     * 调起终端支付接口
     */
    @JavascriptInterface
    public void payforbyNative(String json) {
        Toast.makeText(mContext, "payforbyNative", Toast.LENGTH_SHORT).show();
    }
}