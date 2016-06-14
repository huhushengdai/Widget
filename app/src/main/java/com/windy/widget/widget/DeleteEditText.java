package com.windy.widget.widget;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.View;

/**
 * Date： 2016/6/14
 * Description:
 * 点击右边控件，清空EditText
 *
 * @author WangLizhi
 * @version 1.0
 */
public class DeleteEditText extends ExtendEditText implements TextWatcher, View.OnClickListener {
    private View.OnClickListener mRightListener;
    private TextWatcher mTextWatcher;

    public DeleteEditText(Context context) {
        this(context, null);
    }

    public DeleteEditText(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public DeleteEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setRightClickListener(this);
        addTextChangedListener(this);
    }

    @Override
    public void onClick(View v) {
        if (mRightListener != null) {
            mRightListener.onClick(v);
        }
        if (v == mRightText) {
            setEditText(null);
            setRightVisibility(View.GONE);
        }
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        if (mTextWatcher != null) {
            mTextWatcher.beforeTextChanged(s, start, count, after);
        }
    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        if (mTextWatcher != null) {
            mTextWatcher.onTextChanged(s, start, before, count);
        }
    }

    @Override
    public void afterTextChanged(Editable s) {
        if (mTextWatcher != null) {
            mTextWatcher.afterTextChanged(s);
        }
        if (s != null && s.length() > 0) {
            setRightVisibility(View.VISIBLE);
        }
    }

    @Override
    public void setRightClickListener(OnClickListener listener) {
        mRightListener = listener;
    }

    @Override
    public void addTextChangedListener(TextWatcher watcher) {
        mTextWatcher = watcher;
    }
}
