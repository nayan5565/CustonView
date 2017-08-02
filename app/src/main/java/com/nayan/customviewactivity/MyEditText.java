package com.nayan.customviewactivity;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.widget.EditText;

/**
 * Created by Nayan on 8/2/2017.
 */
public class MyEditText  extends EditText {
    //comment

    public MyEditText(Context context) {
        super(context);
        init();
    }

    public MyEditText(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    public MyEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    void init() {
        // There may be initial text in the field,
        handleTextIcon();

        //if text changes, take care of the TextIcon
        this.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                handleTextIcon();
            }

            @Override
            public void afterTextChanged(Editable arg0) {
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
        });
    }

    public BitmapDrawable writeOnDrawable(String text) {

        //get xml drawable
        Drawable drawable = getResources().getDrawable(R.drawable.rounded_rectange);

        //calculate width of label text icon **base on pure assumptions
        //nothing fancy just taken padding of 60 and assumed width of each text letter to be 22
        int width=60+text.length()*22;

        //convert xml drawable to bitmap
        Bitmap bitmap = Bitmap.createBitmap(width, 100, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
        drawable.draw(canvas);

        //prepare paint to draw
        Paint paint = new Paint();
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(Color.RED);
        paint.setTextSize(40);

        //start padding of 30 left y value is pure assumption
        canvas.drawText(text, 30, (int)(bitmap.getHeight() * 0.65), paint);
        BitmapDrawable bmd = new BitmapDrawable(bitmap);
        bmd.setBounds(0, 0, bmd.getIntrinsicWidth() , bmd.getIntrinsicHeight() );
        return bmd;
    }

    void handleTextIcon() {
        if (this.getText().toString().equals("")) {
            //remove textIcon from view when string is empty
            this.setCompoundDrawables(this.getCompoundDrawables()[0], this.getCompoundDrawables()[1], null, this.getCompoundDrawables()[3]);
        } else {
            //add textIcon to view
            this.setCompoundDrawables(this.getCompoundDrawables()[0], this.getCompoundDrawables()[1], writeOnDrawable(this.getText().toString()), this.getCompoundDrawables()[3]);
        }
    }
}
