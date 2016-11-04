package com.ssthouse.learnselfconfigview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by ssthouse on 04/11/2016.
 */

public class CustomView extends View implements View.OnClickListener {

    private int mCircleDimen;
    private int mCircleColor;

    private Paint mPaint;

    public CustomView(Context context) {
        this(context, null);
    }

    public CustomView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.CustomView);
        mCircleDimen = typedArray.getDimensionPixelSize(R.styleable.CustomView_circle_dimen, 10);
        mCircleColor = typedArray.getColor(R.styleable.CustomView_circle_color, 0xFFFFFF);
        typedArray.recycle();
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setColor(mCircleColor);

        setOnClickListener(this);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int width = getMySize(100, widthMeasureSpec);
        int height = getMySize(100, heightMeasureSpec);
        if (width < height)
            height = width;
        else
            width = height;
        setMeasuredDimension(width, height);
    }

    private int getMySize(int defaultSize, int sizeSpec) {
        int resultSize = defaultSize;
        int mode = MeasureSpec.getMode(sizeSpec);
        int size = MeasureSpec.getSize(sizeSpec);
        switch (mode) {
            //wrap_content的时候显示默认的100
            case MeasureSpec.AT_MOST:
                resultSize = size;
                break;
            //match_parent和指定xdp的时候 根据xml中指定的大小设置大小
            case MeasureSpec.EXACTLY:
                resultSize = size;
                break;
            case MeasureSpec.UNSPECIFIED:
                resultSize = defaultSize;
                break;
        }
        return resultSize;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int x = getWidth() / 2;
        int y = getHeight() / 2;
        canvas.drawCircle(x, y, mCircleDimen / 2, mPaint);
    }

    @Override
    public void onClick(View v) {
        if (mCircleDimen < Math.sqrt(2) * getWidth()) {
            mCircleDimen += 30;
        } else {
            mCircleDimen = 10;
        }
        invalidate();
    }
}
