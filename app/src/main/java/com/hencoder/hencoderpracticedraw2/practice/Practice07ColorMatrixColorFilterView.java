package com.hencoder.hencoderpracticedraw2.practice;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.hencoder.hencoderpracticedraw2.R;

public class Practice07ColorMatrixColorFilterView extends View {
    Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    Bitmap bitmap;
    float[] colors;

    public Practice07ColorMatrixColorFilterView(Context context) {
        super(context);
    }

    public Practice07ColorMatrixColorFilterView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public Practice07ColorMatrixColorFilterView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    {
        bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.batman);
        //自定义矩阵
//        colors = new float[]{-1, 0, 0, 0, 255,
//                0, -1, 0, 0, 255,
//                0, 0, -1, 0, 255,
//                0, 0, 0, 1, 0};

        // 使用 setColorFilter() 设置一个 ColorMatrixColorFilter
        // 用 ColorMatrixColorFilter.setSaturation() 把饱和度去掉
        /*4x5的矩阵
         [ a, b, c, d, e,
           f, g, h, i, j,
           k, l, m, n, o,
           p, q, r, s, t ]

             R’ = a*R + b*G + c*B + d*A + e;
             G’ = f*R + g*G + h*B + i*A + j;
             B’ = k*R + l*G + m*B + n*A + o;
             A’ = p*R + q*G + r*B + s*A + t;
         */
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        ColorMatrix colorMatrix = new ColorMatrix();
        colorMatrix.setSaturation(0);//设置饱和度0-1之间取值
//        colorMatrix.setScale(1,2,1,1);//设置rgba的比例，上一个联系中的凸显绿色也可用这个比例来完成
//        colorMatrix.set(colors);//设置自定义举证
        paint.setColorFilter(new ColorMatrixColorFilter(colorMatrix));
        canvas.drawBitmap(bitmap, 0, 0, paint);
    }
}
