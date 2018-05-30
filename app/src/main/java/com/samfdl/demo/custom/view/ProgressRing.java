package com.samfdl.demo.custom.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.support.annotation.IntRange;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.samfdl.demo.R;

import java.text.DecimalFormat;

/**
 * <pre>
 *     author: Blankj
 *     blog  : http://blankj.com
 *     time  : 2017/07/10
 *     desc  :
 * </pre>
 */
public class ProgressRing extends View {
    private int mMeasureWidth;
    private int mMeasureHeight;

    private RectF pRectF;

    // 背景颜色
    private int bgColor;
    // 背景圆弧弧度
    private int startAngle;
    private int sweepAngle;
    // 圆弧宽度
    private float progressWidth;

    // 进度的颜色
    private int progressStartColor;
    private int progressEndColor;

    // 画笔
    private Paint bgPaint = new Paint(Paint.ANTI_ALIAS_FLAG | Paint.DITHER_FLAG);
    private Paint progressPaint = new Paint(Paint.ANTI_ALIAS_FLAG | Paint.DITHER_FLAG);
    private Paint textPaint = new Paint(Paint.ANTI_ALIAS_FLAG | Paint.DITHER_FLAG);

    // 文字区域
    private Rect mTextRect;
    private float textSize;

    private String mCount = "0.000";

    // 总进度
    private int progress;
    /**
     * 1% 所占的角度
     */
    private float unitAngle;

    /**
     * 当前所画到的进度
     */
    private int curProgress = 0;

    private boolean showAnim;

    public ProgressRing(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.ProgressRing);

        bgColor = ta.getColor(R.styleable.ProgressRing_pr_bg_color, Color.LTGRAY);

        startAngle = ta.getInt(R.styleable.ProgressRing_pr_start_angle, 135);
        sweepAngle = ta.getInt(R.styleable.ProgressRing_pr_sweep_angle, 270);

        progressWidth = ta.getDimension(R.styleable.ProgressRing_pr_progress_width, 8f);

        progressStartColor = ta.getColor(R.styleable.ProgressRing_pr_progress_start_color, Color.YELLOW);
        progressEndColor = ta.getColor(R.styleable.ProgressRing_pr_progress_end_color, progressStartColor);

        progress = ta.getInt(R.styleable.ProgressRing_pr_progress, 0);

        textSize = ta.getDimension(R.styleable.ProgressRing_pr_text_size, 12f);

        showAnim = ta.getBoolean(R.styleable.ProgressRing_pr_show_anim, true);
        ta.recycle();

        unitAngle = (float) (sweepAngle / 100.0);

        bgPaint.setStyle(Paint.Style.STROKE);
        bgPaint.setStrokeCap(Paint.Cap.ROUND);
        bgPaint.setStrokeWidth(progressWidth);

        progressPaint.setStyle(Paint.Style.STROKE);
        progressPaint.setStrokeCap(Paint.Cap.ROUND);
        progressPaint.setStrokeWidth(progressWidth);

        mTextRect = new Rect();
        textPaint.setTextSize(textSize);
        textPaint.setColor(Color.WHITE);
        textPaint.setStyle(Paint.Style.FILL);
        textPaint.setAntiAlias(true);//抗锯齿
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        mMeasureWidth = getMeasuredWidth();
        mMeasureHeight = getMeasuredHeight();
        if (pRectF == null) {
            float halfProgressWidth = progressWidth / 2;
            pRectF = new RectF(halfProgressWidth + getPaddingLeft(),
                    halfProgressWidth + getPaddingTop(),
                    mMeasureWidth - halfProgressWidth - getPaddingRight(),
                    mMeasureHeight - halfProgressWidth - getPaddingBottom());
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        if (!showAnim) {
            curProgress = progress;
        }

        drawBg(canvas);
        drawProgress(canvas);
        drawText(canvas);

        if (curProgress < progress) {
            curProgress++;
            postInvalidate();
        }
    }

    // 画全部圆弧背景
    private void drawBg(Canvas canvas) {
        // 从起始角度画到全部角度
        bgPaint.setColor(bgColor);
        canvas.drawArc(pRectF,
                startAngle,
                sweepAngle,
                false,
                bgPaint);
    }

    private void drawProgress(Canvas canvas) {
        int end = (int) (curProgress * unitAngle);
        // 从起始角度画到当前角度，一度一度地画，因为每一度的颜色都渐变
        for (int i = 0; i <= end; i++) {
            progressPaint.setColor(getGradient(i / (float) end, progressStartColor, progressEndColor));
            canvas.drawArc(pRectF,
                    startAngle + i,
                    1,
                    false,
                    progressPaint);
        }
    }

    // 画文字进度
    private void drawText(Canvas canvas) {
        double count = curProgress * 18 / 100f;
        DecimalFormat decimalFormat = new DecimalFormat("0.000");
        String mText = decimalFormat.format(count) + " g";
        if (curProgress == progress) {
            mText = mCount + " g";
        }
        textPaint.getTextBounds(mText, 0, mText.length(), mTextRect);

        //底部居中显示
        canvas.drawText(mText, (mMeasureWidth - mTextRect.width()) * 0.5f,
                mMeasureHeight - mTextRect.height(), textPaint);
    }

    public void setProgress(@IntRange(from = 0, to = 100) int progress) {
        this.progress = progress;
        invalidate();
    }

    public void setText(String count) {
        mCount = count;
        double count0 = Double.valueOf(count);
        this.progress = (int) (count0 / 18 * 100);

        // 清零重画
        curProgress = 0;

        invalidate();
    }

    // 获得每一度的颜色值
    public int getGradient(float fraction, int startColor, int endColor) {
        if (fraction > 1) {
            fraction = 1;
        }

        int alphaStart = Color.alpha(startColor);
        int redStart = Color.red(startColor);
        int blueStart = Color.blue(startColor);
        int greenStart = Color.green(startColor);

        int alphaEnd = Color.alpha(endColor);
        int redEnd = Color.red(endColor);
        int blueEnd = Color.blue(endColor);
        int greenEnd = Color.green(endColor);

        int alphaDifference = alphaEnd - alphaStart;
        int redDifference = redEnd - redStart;
        int blueDifference = blueEnd - blueStart;
        int greenDifference = greenEnd - greenStart;

        int alphaCurrent = (int) (alphaStart + fraction * alphaDifference);
        int redCurrent = (int) (redStart + fraction * redDifference);
        int blueCurrent = (int) (blueStart + fraction * blueDifference);
        int greenCurrent = (int) (greenStart + fraction * greenDifference);

        return Color.argb(alphaCurrent, redCurrent, greenCurrent, blueCurrent);
    }
}