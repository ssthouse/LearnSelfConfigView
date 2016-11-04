package com.ssthouse.learnselfconfigview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by ssthouse on 04/11/2016.
 */

public class CustomView extends View {

    public CustomView(Context context) {
        this(context, null);
    }

    public CustomView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
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
}
