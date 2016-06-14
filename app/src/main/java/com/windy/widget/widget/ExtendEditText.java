package com.windy.widget.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.support.annotation.ColorInt;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.windy.widget.R;


/**
 * Date： 2016/6/3
 * Description:
 * 扩展的EditText
 * EditText左右两边添加 TextView，可以显示文字图片，也可以隐藏
 * 中间EditText只写了部分监听，以后有需要再补上
 * @author WangLizhi
 * @version 1.0
 */
public class ExtendEditText extends LinearLayout{
    protected TextView mLeftText;
    protected EditText mEditText;
    protected TextView mRightText;

    public ExtendEditText(Context context) {
        this(context, null);
    }

    public ExtendEditText(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ExtendEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        //生成子控件
        mLeftText = new TextView(context);
        addView(mLeftText, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);

        mEditText = new EditText(context,attrs);
        mEditText.setBackgroundDrawable(null);//取消xml的背景布局
        mEditText.setGravity(Gravity.CENTER_VERTICAL);
        addView(mEditText, new LayoutParams(0, ViewGroup.LayoutParams.WRAP_CONTENT, 1));

        mRightText = new TextView(context);
        addView(mRightText, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT);

        //初始化xml属性
        initAttrs(context, attrs, defStyleAttr);

    }

    /**
     * 初始化xml属性
     *
     * @param context      上下文
     * @param attrs        属性
     * @param defStyleAttr 默认属性
     */
    private void initAttrs(Context context, AttributeSet attrs, int defStyleAttr) {

        //获取自定义属性
        TypedArray typedArray = context.getTheme().obtainStyledAttributes(
                attrs, R.styleable.ExtendEditText, defStyleAttr, 0);
        //padding
        int leftPaddingLeft = 0;
        int leftPaddingTop = 0;
        int leftPaddingRight = 0;
        int leftPaddingBottom = 0;

        int midPaddingLeft = 0;
        int midPaddingTop = 0;
        int midPaddingRight = 0;
        int midPaddingBottom = 0;

        int rightPaddingLeft = 0;
        int rightPaddingTop = 0;
        int rightPaddingRight = 0;
        int rightPaddingBottom = 0;
        //drawable
        Drawable rightDrawable = null;
        Drawable leftDrawable = null;

        int count = typedArray.getIndexCount();
        int attr = -1;
        for (int index = 0; index < count; index++) {
            attr = typedArray.getIndex(index);
            switch (attr) {
                case R.styleable.ExtendEditText_left_drawable:
                    leftDrawable = typedArray.getDrawable(attr);
                    break;
                case R.styleable.ExtendEditText_left_text:
                    setLeftText(typedArray.getString(attr));
                    break;
                case R.styleable.ExtendEditText_left_textColor:
                    setLeftTextColor(typedArray.getColor(attr, 0));
                    break;
                case R.styleable.ExtendEditText_left_textSize:
                    setLeftTextSize(typedArray.getDimensionPixelSize(attr, 12));
                    break;
                case R.styleable.ExtendEditText_left_drawablePadding:
                    setLeftDrawablePadding(typedArray.getDimensionPixelSize(attr, 0));
                    break;
                case R.styleable.ExtendEditText_left_padding:
                    int padding = typedArray.getDimensionPixelSize(attr, 0);
                    leftPaddingLeft = padding;
                    leftPaddingTop = padding;
                    leftPaddingRight = padding;
                    leftPaddingBottom = padding;
                    break;
                case R.styleable.ExtendEditText_left_paddingLeft:
                    leftPaddingLeft = typedArray.getDimensionPixelSize(attr, 0);
                    break;
                case R.styleable.ExtendEditText_left_paddingTop:
                    leftPaddingTop = typedArray.getDimensionPixelSize(attr, 0);
                    break;
                case R.styleable.ExtendEditText_left_paddingRight:
                    leftPaddingRight = typedArray.getDimensionPixelSize(attr, 0);
                    break;
                case R.styleable.ExtendEditText_left_paddingBottom:
                    leftPaddingBottom = typedArray.getDimensionPixelSize(attr, 0);
                    break;

                //right
                case R.styleable.ExtendEditText_right_drawable:
                    rightDrawable = typedArray.getDrawable(attr);
                    break;
                case R.styleable.ExtendEditText_right_text:
                    setRightText(typedArray.getString(attr));
                    break;
                case R.styleable.ExtendEditText_right_textColor:
                    setRightTextColor(typedArray.getColor(attr, 0));
                    break;
                case R.styleable.ExtendEditText_right_textSize:
                    setRightTextSize(typedArray.getDimensionPixelSize(attr, 12));
                    break;
                case R.styleable.ExtendEditText_right_drawablePadding:
                    setRightDrawablePadding(typedArray.getDimensionPixelSize(attr, 0));
                    break;
                case R.styleable.ExtendEditText_right_padding:
                    int rightPadding = typedArray.getDimensionPixelSize(attr, 0);
                    rightPaddingLeft = rightPadding;
                    rightPaddingTop = rightPadding;
                    rightPaddingRight = rightPadding;
                    rightPaddingBottom = rightPadding;
                    break;
                case R.styleable.ExtendEditText_right_paddingLeft:
                    rightPaddingLeft = typedArray.getDimensionPixelSize(attr, 0);
                    break;
                case R.styleable.ExtendEditText_right_paddingTop:
                    rightPaddingTop = typedArray.getDimensionPixelSize(attr, 0);
                    break;
                case R.styleable.ExtendEditText_right_paddingRight:
                    rightPaddingRight = typedArray.getDimensionPixelSize(attr, 0);
                    break;
                case R.styleable.ExtendEditText_right_paddingBottom:
                    rightPaddingBottom = typedArray.getDimensionPixelSize(attr, 0);
                    break;
            }
        }
        typedArray.recycle();
        //set padding
        setLeftPadding(leftPaddingLeft, leftPaddingTop, leftPaddingRight, leftPaddingBottom);
        //暂时中间的EditText padding设置为0
        setEditPadding(midPaddingLeft, midPaddingTop, midPaddingRight, midPaddingBottom);
        setRightPadding(rightPaddingLeft, rightPaddingTop, rightPaddingRight, rightPaddingBottom);
        //set drawable
        setLeftDrawable(leftDrawable);
        setRightDrawable(rightDrawable);
//        mEditText.setInputType(InputType.TYPE_NUMBER_VARIATION_NORMAL);
    }
//-------------------------------------右边控件-----------------------------------------

    /**
     * 设置右边的图片
     *
     * @param rightDrawable 右边图片
     */
    public void setRightDrawable(Drawable rightDrawable) {
        if (rightDrawable == null && TextUtils.isEmpty(mRightText.getText().toString())) {
            mRightText.setVisibility(View.GONE);
        } else {
            mRightText.setVisibility(View.VISIBLE);
        }
        mRightText.setCompoundDrawablesWithIntrinsicBounds(rightDrawable, null, null, null);
    }

    /**
     * 设置右边文字
     *
     * @param text 文字
     */
    public void setRightText(String text) {
        if (TextUtils.isEmpty(text) && mRightText.getCompoundDrawables()[0] == null) {
            mRightText.setVisibility(View.GONE);
        } else {
            mRightText.setVisibility(View.VISIBLE);
        }
        mRightText.setText(text);
    }

    public void setRightTextColor(@ColorInt int color) {
        mRightText.setTextColor(color);
    }

    public void setRightTextSize(float size) {
        mRightText.setTextSize(size);
    }

    public void setRightPadding(int left, int top, int right, int bottom) {
        mRightText.setPadding(left, top, right, bottom);
    }

    public void setRightDrawablePadding(int pad) {
        mRightText.setCompoundDrawablePadding(pad);
    }

    public void setRightClickListener(OnClickListener listener){
        mRightText.setOnClickListener(listener);
    }

    public void setRightVisibility(int visibility){
        mRightText.setVisibility(visibility);
    }

//-------------------------------------左边控件--------------------------------------------

    /**
     * 设置左边的图片
     *
     * @param leftDrawable 左边图片
     */
    public void setLeftDrawable(Drawable leftDrawable) {
        if (leftDrawable == null && TextUtils.isEmpty(mLeftText.getText().toString())) {
            mLeftText.setVisibility(View.GONE);
        } else {
            mLeftText.setVisibility(View.VISIBLE);
        }
        mLeftText.setCompoundDrawablesWithIntrinsicBounds(leftDrawable, null, null, null);
    }

    /**
     * 设置左边文字
     *
     * @param text 文字
     */
    public void setLeftText(String text) {
        if (TextUtils.isEmpty(text) && mLeftText.getCompoundDrawables()[0] == null) {
            mLeftText.setVisibility(View.GONE);
        } else {
            mLeftText.setVisibility(View.VISIBLE);
        }
        mLeftText.setText(text);
    }

    public void setLeftTextColor(@ColorInt int color) {
        mLeftText.setTextColor(color);
    }

    public void setLeftTextSize(float size) {
        mLeftText.setTextSize(size);
    }

    public void setLeftPadding(int left, int top, int right, int bottom) {
        mLeftText.setPadding(left, top, right, bottom);
    }

    public void setLeftDrawablePadding(int pad) {
        mLeftText.setCompoundDrawablePadding(pad);
    }

    public void setLeftClickListener(OnClickListener listener){
        mLeftText.setOnClickListener(listener);
    }

    public void setLeftVisibility(int visibility){
        mLeftText.setVisibility(visibility);
    }

//------------------------------------中间EditText-----------------------------

    public void setEditText(CharSequence text) {
        mEditText.setText(text);
    }

    public void setEditTextColor(@ColorInt int color) {
        mEditText.setTextColor(color);
    }

    public void setEditTextSize(float size){
        mEditText.setTextSize(size);
    }

    public void setEditHint(CharSequence hintText) {
        mEditText.setHint(hintText);
    }

    public void setEditHintColor(@ColorInt int color){
        mEditText.setHintTextColor(color);
    }

    public void setEditPadding(int left, int top, int right, int bottom) {
        mEditText.setPadding(left, top, right, bottom);
    }

    public void setInputType(int type){
        mEditText.setInputType(type);
    }

    public void addTextChangedListener(TextWatcher watcher){
        mEditText.addTextChangedListener(watcher);
    }

    public void setOnclickListener(OnClickListener listener){
        mEditText.setOnClickListener(listener);
    }

    public void setOnFocusChangeListener(OnFocusChangeListener listener){
        mEditText.setOnFocusChangeListener(listener);
    }
}
