package com.samfdl.demo.custom;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.ClickableSpan;
import android.text.style.URLSpan;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.samfdl.demo.R;

public class TextViewLinkActivity extends AppCompatActivity {
    private TextView content;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_textviewlink);

        content = findViewById(R.id.content);

        CharSequence text = content.getText();
        if (text instanceof Spannable) {
            int end = text.length();
            Spannable sp = (Spannable) content.getText();
            URLSpan[] urls = sp.getSpans(0, end, URLSpan.class);
            SpannableStringBuilder style = new SpannableStringBuilder(text);
            style.clearSpans();// should clear old spans
            for (URLSpan url : urls) {
                MyURLSpan myURLSpan = new MyURLSpan(url.getURL());
                style.setSpan(myURLSpan, sp.getSpanStart(url), sp.getSpanEnd(url), Spannable.SPAN_EXCLUSIVE_INCLUSIVE);
            }
            content.setText(style);
        }
    }

    private class MyURLSpan extends ClickableSpan {
        private String mUrl;

        MyURLSpan(String url) {
            mUrl = url;
        }

        @Override
        public void onClick(View widget) {
            Intent intent = new Intent(TextViewLinkActivity.this, WebViewActivity.class);
            intent.putExtra("projectUrl", mUrl);
            startActivity(intent);
            Toast.makeText(TextViewLinkActivity.this, mUrl, Toast.LENGTH_SHORT).show();
//            widget.setBackgroundColor(Color.parseColor("#00000000"));
        }
    }
}