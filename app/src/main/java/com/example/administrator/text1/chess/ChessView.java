package com.example.administrator.text1.chess;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.example.administrator.text1.R;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by WeiZ on 2016/11/21.
 */

public class ChessView extends View {
    private int MaxLine = 10;//棋盘行数
    private int mPanelWidth;
    private float mLineHeight;
    private Paint mPaint = new Paint();
    private Bitmap mWithtP;
    private Bitmap mBlackP;
    private float ratioPieceOfLineHeight = 3 * 1.0f / 4;
    private List<Point> mWhiteArray = new ArrayList<Point>(), mBlackArray = new ArrayList<Point>();
    private boolean mIsWhite = false;

    public ChessView(Context context, AttributeSet attrs) {
        super(context, attrs);
        setBackgroundColor(Color.parseColor("#BBAD6E"));
        init();

    }

    private void init() {
        mPaint.setColor(0x88000000);
        mPaint.setAntiAlias(true);
        mPaint.setDither(true);
        mPaint.setStyle(Paint.Style.STROKE);

        mWithtP = BitmapFactory.decodeResource(getResources(), R.mipmap.stone_w2);
        mBlackP = BitmapFactory.decodeResource(getResources(), R.mipmap.stone_b1);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);

        int heigthSize = MeasureSpec.getSize(heightMeasureSpec);
        int heigthMode = MeasureSpec.getMode(heightMeasureSpec);

        int width = Math.min(widthSize, heigthSize);
        //父容器是ScorlView时，以防height或width是0
        if (widthMode == MeasureSpec.UNSPECIFIED) {
            width = heigthSize;
        } else if (heigthMode == MeasureSpec.UNSPECIFIED) width = widthSize;
        setMeasuredDimension(width, width);//设置View的长宽
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mPanelWidth = w;
        mLineHeight = (mPanelWidth * 1.0f) / MaxLine;
        int pieceWide = (int) (mLineHeight * ratioPieceOfLineHeight);
        mWithtP = Bitmap.createScaledBitmap(mWithtP, pieceWide, pieceWide, false);//笔记：如果是放大图片，filter决定是否平滑，如果是缩小图片，filter无影响
        mBlackP = Bitmap.createScaledBitmap(mBlackP, pieceWide, pieceWide, false);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {


        int action = event.getAction();
        if(action == MotionEvent.ACTION_UP) {
            int x = (int) event.getX();
            int y = (int) event.getY();

            Point p = makeSurePiece(x, y);

            if(mWhiteArray.contains(p) || mBlackArray.contains(p))
                return false;

            if(mIsWhite)
                mWhiteArray.add(p);
            else
                mBlackArray.add(p);

            invalidate(); // 请求重绘
            mIsWhite = !mIsWhite;

        }
        return true;
    }

    private Point makeSurePiece(int x, int y) {
        return new Point((int) (x / mLineHeight), (int) (y / mLineHeight));
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //画棋盘
        int w = mPanelWidth;
        float lineHeight = mLineHeight;
        float startX, x, y;
        x = (float) (w - 0.5 * lineHeight);
        startX = (float) (0.5 * lineHeight);
        for (int i = 0; i < MaxLine; i++) {
            y = (float) (0.5 * lineHeight + i * lineHeight);
            canvas.drawLine(startX, y, x, y, mPaint);//画横线
            canvas.drawLine(y, startX, y, x, mPaint);//竖线
        }
        //画棋子
         //为了效率，mWhitePoint.size()直接提前获取，不用每次获取
            for(int i = 0, n = mWhiteArray.size(); i < n; i++ ) {
                Point whitePoint = mWhiteArray.get(i);
                canvas.drawBitmap(mWithtP,
                        (whitePoint.x + (1-ratioPieceOfLineHeight)/2)*mLineHeight,
                        (whitePoint.y + (1-ratioPieceOfLineHeight)/2)*mLineHeight, null);
            }
        for(int i = 0, n = mBlackArray.size(); i < n; i++ ) {
            Point blackPoint = mBlackArray.get(i);
            canvas.drawBitmap(mBlackP,
                    (blackPoint.x + (1-ratioPieceOfLineHeight)/2)*mLineHeight,
                    (blackPoint.y + (1-ratioPieceOfLineHeight)/2)*mLineHeight, null);
        }
    }
}
