package com.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;

import com.google.zxing.common.BitMatrix;

/**
 * 畫QRCode
 * Created by star on 2016/5/31.
 */
public class DrawQRCode extends View {
    private BitMatrix bitmatrix;
    private Paint paint;

    public DrawQRCode(Context context) {
        super(context);
        init();
    }

    public DrawQRCode(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public DrawQRCode(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public DrawQRCode(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    public DrawQRCode(Context context, BitMatrix bitmatrix){
        super(context);
        init();

        this.bitmatrix = bitmatrix;
    }

    private void init() {
        if(paint==null){
            paint = new Paint();
        }
    }

    public void setBitmatrix(BitMatrix bitmatrix){
        init();

        this.bitmatrix = bitmatrix;
        requestLayout();
        postInvalidate();
    }

    @Override
    public void onDraw(Canvas c){
        if(bitmatrix==null) return;

        c.drawColor(Color.WHITE);
        Rect bounds = c.getClipBounds();
        int w = bounds.width();
        int h = bounds.height();

        int imageSize = bitmatrix.getHeight();
        int blockSize = w/imageSize;

        int top_offset = (h - imageSize*blockSize)/2;
        int left_offset = (w - imageSize*blockSize)/2;

        for(int i=0;i<imageSize;i++){
            for(int j=0;j<imageSize;j++){
                if(bitmatrix.get(i, j)){
                    c.drawRect(left_offset + i*blockSize, top_offset + j*blockSize
                            , left_offset+(i+1)*blockSize,top_offset+ (j+1)*blockSize, paint);
                }
            }
        }
    }
}
