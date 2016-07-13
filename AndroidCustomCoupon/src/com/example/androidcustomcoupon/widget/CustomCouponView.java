package com.example.androidcustomcoupon.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.widget.LinearLayout;

import com.example.androidcustomcoupon.R;
/**
 * 
 * �������ڣ�2016-7-13 <br/>
 * �������Զ����Ż�ȯView
 */
public class CustomCouponView extends LinearLayout {

	private Paint mPaint;

    private int mWidth;
    private int mHeight;
    /**
     * Բ��Բ֮��ļ��
     */
    private float mGapSize = 8;
    /**
     * Բ�İ뾶
     */
    private float mRadius = 10;
    /**
     * ��ֱ����Բ�������ε�����
     */
    private int mVerticalCount;

    /**
     * ��ֱ�����ʼƫ�ƾ���
     */
    private int mVerticalInitSize;

    /**
     * ˮƽ����Բ�������ε�����
     */
    private int mHorizontalCount;
    /**
     * ˮƽ�����ʼƫ����
     */
    private int mHorizontalInitSize;
    /**
     * Ĭ�ϵĴ�ֱ�������ʽΪnone���������л���
     */
    private static final int DEFAULT_VERTICAL_STYLE_NONE = 0;
    /**
     * Ĭ�ϵ�ˮƽ�����ʽΪnone���������л���
     */
    private static final int DEFAULT_HORIZONTAL_STYLE_NONE = 0;
    //ˮƽ��ֱ����ı�Ե����
    private int vertical_style = DEFAULT_VERTICAL_STYLE_NONE;
    private int horizontal_style = DEFAULT_HORIZONTAL_STYLE_NONE;
    
	
	public CustomCouponView(Context context) {
		super(context);
		init(context,null);
	}
	public CustomCouponView(Context context, AttributeSet attrs) {
		super(context, attrs);
		init(context,attrs);
		
	}
	public CustomCouponView(Context context, AttributeSet attrs,
			int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		init(context,attrs);
		
	}
	
	private void init(Context context, AttributeSet attrs) {
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setColor(Color.WHITE);//����Ϊ��ɫ����Ҫ���ݸ����ֵı���ɫ���е�����������Ƶ���һ���Ƚ�����ͻ��

        setWillNotDraw(false);

        if(attrs != null){
            TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.CouponStyle);
            vertical_style = ta.getInt(R.styleable.CouponStyle_vertical_style, DEFAULT_VERTICAL_STYLE_NONE);
            horizontal_style = ta.getInt(R.styleable.CouponStyle_horizontal_style, DEFAULT_HORIZONTAL_STYLE_NONE);
            ta.recycle();
        }
    }
	@Override
	protected void dispatchDraw(Canvas canvas) {
		super.dispatchDraw(canvas);
		
		 if(vertical_style == 1){//�����ֱ�����ǰ�Բ��
		        drawVerticalCircle(canvas);
		    }else if(vertical_style == 2){//��ֱ������������
		        drawVerticalTriangle(canvas);
		    }
		    if(horizontal_style == 1){//���ˮƽ�����ǰ�Բ��
		        drawHorizontalCircle(canvas);
		    }else if(horizontal_style == 2){//���ˮƽ������������
		        drawHorizontalTriangle(canvas);
		    }
	}
	
	/**
	 * ��ˮƽ�����ϻ���������
	 * @param canvas
	 */
	private void drawHorizontalTriangle(Canvas canvas) {
	    //�ȼ����ˮƽ�����ϵ�����
	    calculateHorizontalCount(0);
	    Path path = new Path();
	    float x = 0;
	    //�������沿��
	    for(int i = 0; i < mHorizontalCount; i++){
	        path.reset();
	        x = mHorizontalInitSize + i * 2 * mRadius;
	        path.moveTo(x, 0);
	        x += mRadius;
	        path.lineTo(x, mRadius);
	        x += mRadius;
	        path.lineTo(x, 0);
	        path.close();
	        canvas.drawPath(path, mPaint);
	    }
	    //�������沿��
	    x = 0;
	    for(int i = 0; i < mHorizontalCount; i++){
	        path.reset();
	        x = mHorizontalInitSize + i * 2 * mRadius;
	        path.moveTo(x, mHeight);
	        x += mRadius;
	        path.lineTo(x, mHeight - mRadius);
	        x += mRadius;
	        path.lineTo(x, mHeight);
	        path.close();
	        canvas.drawPath(path, mPaint);
	    }
	}

	/**
	 * ��ˮƽ�����ϻ���Բ��
	 * @param canvas
	 */
	private void drawHorizontalCircle(Canvas canvas) {
	    //�ȼ����ˮƽ�����ϵ�����
	    calculateHorizontalCount(mGapSize);
	    float x = mHorizontalInitSize + mGapSize + mRadius;
	    //�Ȼ������沿��
	    for(int i = 0; i < mHorizontalCount; i++){
	        canvas.drawCircle(x, 0, mRadius, mPaint);
	        x += 2 * mRadius + mGapSize;
	    }
	    //�ٻ������沿��
	    x = mHorizontalInitSize + mGapSize + mRadius;
	    for(int i = 0; i < mHorizontalCount; i++){
	        canvas.drawCircle(x, mHeight, mRadius, mPaint);
	        x += 2 * mRadius + mGapSize;
	    }
	}

	/**
	 * �ڴ�ֱ�������������
	 * @param canvas
	 */
	private void drawVerticalTriangle(Canvas canvas) {
	    //����һ�������ε������ͳ�ʼ����
	    calculateVerticalCount(0);
	    Path path = new Path();
	    float y = 0;
	    //�Ȼ����
	    for(int i = 0; i < mVerticalCount; i++){
	        path.reset();
	        y = mVerticalInitSize + i * 2 * mRadius;
	        path.moveTo(0, y);
	        y += mRadius;
	        path.lineTo(mRadius, y);
	        y += mRadius;
	        path.lineTo(0, y);
	        path.close();
	        canvas.drawPath(path, mPaint);
	    }
	    //�ٻ��ұ�
	    y = 0;
	    for(int i = 0; i < mVerticalCount; i++){
	        path.reset();
	        y = mVerticalInitSize + i * 2 * mRadius;
	        path.moveTo(mWidth, y);
	        y += mRadius;
	        path.lineTo(mWidth - mRadius, y);
	        y += mRadius;
	        path.lineTo(mWidth, y);
	        path.close();
	        canvas.drawPath(path, mPaint);
	    }
	}

	/**
	 * �ڴ�ֱ������ư�Բ��
	 * @param canvas
	 */
	private void drawVerticalCircle(Canvas canvas) {
	    //����һ��Բ�ε������ͳ�ʼƫ�ƾ���
	    calculateVerticalCount(mGapSize);
	    //���ʹ�û��������Ƴ�Բ��
	    RectF rectF = new RectF();
	    //�Ȼ����
	    for(int i = 0; i < mVerticalCount; i++){
	        rectF.left = -mRadius;
	        rectF.top = mVerticalInitSize + mGapSize * (i + 1) + i * 2 * mRadius;
	        rectF.right =  mRadius;
	        rectF.bottom = rectF.top + 2 * mRadius;
	        canvas.drawArc(rectF, -90, 180, false, mPaint);
	    }
	    //�ٻ��ұ�
	    for(int i = 0; i < mVerticalCount; i++){
	        rectF.left = mWidth - mRadius;
	        rectF.top = mVerticalInitSize + mGapSize * (i + 1) + i * 2 * mRadius;
	        rectF.right = rectF.left + 2 * mRadius;
	        rectF.bottom = rectF.top + 2 * mRadius;
	        canvas.drawArc(rectF, 90, 180, false, mPaint);
	    }
	}

	/**
	 * ���㴹ֱ������Ҫ��Բ�������ε������ͳ�ʼƫ����
	 * @param gapSize ÿ��Բ�λ�������֮��ļ��
	 */
	private void calculateVerticalCount(float gapSize){
	    mVerticalCount = (int) ((mHeight - gapSize) / (2 * mRadius + gapSize));
	    mVerticalInitSize = (int) ((mHeight - (2 * mRadius * mVerticalCount + (mVerticalCount + 1) * gapSize)) / 2);
	}

	/**
	 * ����ˮƽ������Բ�������ε������ͳ�ʼƫ����
	 * @param gapSize ÿ��Բ�λ�������֮��ļ��
	 */
	private void calculateHorizontalCount(float gapSize) {
	    mHorizontalCount = (int) ((mWidth - gapSize) / (2 * mRadius + gapSize));
	    mHorizontalInitSize = (int) ((mWidth - (2 * mRadius * mHorizontalCount + (mHorizontalCount + 1) * gapSize)) / 2);
	}
	
	@Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mWidth = w;
        mHeight = h;
    }
}
