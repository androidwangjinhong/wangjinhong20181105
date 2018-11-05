package com.bwie.wangjinhong20181105;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;

/**
 * data:2018/11/5
 * author:王金洪(王金洪)
 * function
 */
public class Zhuanpan extends View implements View.OnClickListener{
    //初始化文字
    private String[] context = new String[]{"参与奖","谢谢参与","一等奖","二等奖","三等奖","四等奖"};

    private RotateAnimation rotateAnimation;
    //设置画笔化画转盘
    private Paint strpaint;
    //设置画笔写字
    private Paint textpaint;
    //设置宽
    private int width;

    private int mpadding;

    private String str="start";

    private RectF rectF;

    private Boolean isSan = false;

    public Zhuanpan(Context context) {
        this( context,null);
    }

    public Zhuanpan(Context context, @Nullable AttributeSet attrs) {
        this( context, attrs ,0);
    }

    public Zhuanpan(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super( context, attrs, defStyleAttr );
        initAnima();
        initpaint();

        setOnClickListener( this );
    }

    private void initAnima() {
       rotateAnimation = new RotateAnimation( 0f, 360f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f );
       rotateAnimation.setRepeatCount( -1 );
       rotateAnimation.setFillAfter( true );
    }

    private void initpaint() {

        strpaint = new Paint();
        strpaint.setStyle( Paint.Style.STROKE);
        strpaint.setAntiAlias( true );
        strpaint.setColor(Color.WHITE);
        strpaint.setStrokeWidth( 5 );

        textpaint = new Paint();
        textpaint.setStyle( Paint.Style.STROKE);
        textpaint.setAntiAlias( true );
        textpaint.setColor( Color.WHITE );
        textpaint.setStrokeWidth( 3 );
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        setMeasuredDimension( 300,300 );
        width = getMeasuredWidth();
        mpadding=5;
        initRect();
    }

    private void initRect() {
       rectF = new RectF(0,0,width,width);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw( canvas );
        textpaint.setStyle( Paint.Style.STROKE );
        canvas.drawCircle( width/2,width/2,width/2-mpadding,textpaint );
        textpaint.setStyle( Paint.Style.FILL );

        initArc(canvas);
        //设置里面小圆
        textpaint.setColor( Color.RED );
        textpaint.setStyle( Paint.Style.FILL );
        canvas.drawCircle( width/2,width/2,50,textpaint );

        textpaint.setColor( Color.WHITE );
        textpaint.setTextSize( 20 );
        Rect rect = new Rect();
        textpaint.getTextBounds(  str,0,str.length(),rect);
        int widths = rect.width();
        int height = rect.height();
        canvas.drawText( str,width/2-25+25-widths/2,width/2+height/2,textpaint );

    }
    //设置转盘各自的颜色
    public int[] Colors= new int[]{Color.parseColor( "#0000FF" ),Color.parseColor( "#7FFF00" ),Color.parseColor( "#008B8B" ),Color.parseColor( "#FFF8DC" ),Color.parseColor( "#A9A9A9" ),Color.parseColor( "#8B0000" )};
    //展示出来
    private void initArc(Canvas canvas) {
        //展示转盘
        for(int i = 0 ; i<6;i++){
        textpaint.setColor( Colors[i] );
        canvas.drawArc( rectF,(i-1)*60+60,60,true,textpaint );
        }
        //展示图片
        for (int i = 0 ;i<6;i++){
            textpaint.setColor( Color.BLACK );
            Path path = new Path();
            path.addArc( rectF,(i-1)*60+60,60 );
            canvas.drawTextOnPath( context[i],path,60,60,textpaint );
        }

    }

    @Override
    public void onClick(View view) {
        if(!isSan){
          isSan=true;
          rotateAnimation.setDuration( 1000 );
          rotateAnimation.setInterpolator( new LinearInterpolator(  ) );
          startAnimation( rotateAnimation );
        }else{
            isSan=false;
            stopAime();
        }
    }

    private void stopAime() {
        clearAnimation();
    }


}
