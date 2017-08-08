package com.hencoder.hencoderpracticedraw2.practice;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.LightingColorFilter;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.hencoder.hencoderpracticedraw2.R;

public class Practice06LightingColorFilterView extends View {
    Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    Bitmap bitmap;

    public Practice06LightingColorFilterView(Context context) {
        super(context);
    }

    public Practice06LightingColorFilterView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public Practice06LightingColorFilterView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    {
        bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.batman);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        /*
            LightingColorFilter(int mul, int add)：
            计算方式：【mul的初始化是0xffffff,add的初始化是0x000000】
            R' = R * mul.R / 0xff + add.R
            G' = G * mul.G / 0xff + add.G
            B' = B * mul.B / 0xff + add.B
            假设去点红色则令R'=0,设置mul的R为0
         */
        // 使用 Paint.setColorFilter() 来设置 LightingColorFilter
        // 第一个 LightingColorFilter：去掉红色部分
        LightingColorFilter colorFilter = new LightingColorFilter(0x00ffff, 0x000000);
        paint.setColorFilter(colorFilter);
        canvas.drawBitmap(bitmap, 0, 0, paint);

        // 第二个 LightingColorFilter：增强绿色部分,原理是设置add.G,因为add的值是加
        LightingColorFilter filter = new LightingColorFilter(0xffffff,0x003000);
        paint.setColorFilter(filter);
        canvas.drawBitmap(bitmap, bitmap.getWidth() + 100, 0, paint);
    }
}