/*
 * Copyright 2015 Michael Bel
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.app.application.cells;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.annotation.StringRes;
import android.util.TypedValue;
import android.view.Gravity;
import android.widget.FrameLayout;
import android.widget.TextView;

import org.app.material.AndroidUtilities;
import org.app.material.widget.LayoutHelper;

public class TextCell extends FrameLayout {

    private TextView mTextView;
    private TextView mValueView;

    private static Paint mPaint;
    private boolean needDivider = false;

    public TextCell(Context context) {
        super(context);

        AndroidUtilities.bind(context);

        this.setBackgroundColor(0xFFFFFFFF);

        if (mPaint == null) {
            mPaint = new Paint();
            mPaint.setColor(0xffd9d9d9);
            mPaint.setStrokeWidth(1);
        }

        mTextView = new TextView(context);
        mTextView.setTextColor(0xFF000000);
        mTextView.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 16);
        mTextView.setGravity(Gravity.START | Gravity.CENTER_VERTICAL);
        mTextView.setPadding(AndroidUtilities.dp(16), 0, AndroidUtilities.dp(16), 0);
        mTextView.setLayoutParams(LayoutHelper.makeFrame(context, LayoutHelper.WRAP_CONTENT, LayoutHelper.WRAP_CONTENT, Gravity.START | Gravity.CENTER_VERTICAL));
        addView(mTextView);

        mValueView = new TextView(context);
        mValueView.setTextColor(0xFF757575);
        mValueView.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 16);
        mValueView.setGravity(Gravity.START | Gravity.CENTER_VERTICAL);
        mValueView.setPadding(AndroidUtilities.dp(16), 0, AndroidUtilities.dp(16), 0);
        mValueView.setLayoutParams(LayoutHelper.makeFrame(context, LayoutHelper.WRAP_CONTENT, LayoutHelper.WRAP_CONTENT, Gravity.END | Gravity.CENTER_VERTICAL));
        addView(mValueView);
    }

    public TextCell setText(@StringRes int resId) {
        mTextView.setText(getResources().getString(resId));
        return this;
    }

    public TextCell setValue(@StringRes int resId) {
        mValueView.setText(getResources().getString(resId));
        return this;
    }

    public TextCell setValue(String value) {
        mValueView.setText(value);
        return this;
    }

    public TextCell setDivider(boolean divider) {
        needDivider = divider;
        return this;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        if (needDivider) {
            canvas.drawLine(getPaddingLeft(), getHeight() - 1, getWidth() - getPaddingRight(), getHeight() - 1, mPaint);
        }
    }

    @Override
    protected void onMeasure(int wMeasureSpec, int hMeasureSpec) {
        super.onMeasure(wMeasureSpec, MeasureSpec.makeMeasureSpec(AndroidUtilities.dp(54 + (needDivider ? 1 : 0)), MeasureSpec.EXACTLY));
    }
}