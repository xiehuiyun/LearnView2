package com.hencoder.hencoderpracticedraw2.practice;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.ComposeShader;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.Shader;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.hencoder.hencoderpracticedraw2.R;

public class Practice05ComposeShaderView extends View {
    Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);

    public Practice05ComposeShaderView(Context context) {
        super(context);
    }

    public Practice05ComposeShaderView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public Practice05ComposeShaderView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    {
        setLayerType(LAYER_TYPE_SOFTWARE, null); // 硬件加速下 ComposeShader 不能使用两个同类型的 Shader

        // 用 Paint.setShader(shader) 设置一个 ComposeShader
        // Shader 1: BitmapShader 图片：R.drawable.batman
        //参考：https://juejin.im/post/596baf5f6fb9a06bb15a3df9

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        BitmapShader shader1 = new BitmapShader(BitmapFactory.decodeResource(getResources(), R.drawable.batman), Shader.TileMode.REPEAT, Shader.TileMode.REPEAT);
        BitmapShader shader2 = new BitmapShader(BitmapFactory.decodeResource(getResources(), R.drawable.batman_logo), Shader.TileMode.REPEAT, Shader.TileMode.REPEAT);
        paint.setShader(new ComposeShader(shader1, shader2, PorterDuff.Mode.DST_IN));
        canvas.drawCircle(200, 200, 200, paint);

        paint.setShader(new ComposeShader(shader1, shader2, PorterDuff.Mode.DST_OUT));
        canvas.drawCircle(580, 200, 200, paint);

        paint.setShader(new ComposeShader(shader1, shader2, PorterDuff.Mode.SRC_IN));
        canvas.drawCircle(1000, 200, 200, paint);

        paint.setShader(new ComposeShader(shader1, shader2, PorterDuff.Mode.SRC_ATOP));
        canvas.drawCircle(200, 600, 200, paint);

        paint.setShader(new ComposeShader(shader1, shader2, PorterDuff.Mode.DARKEN));
        canvas.drawCircle(580, 600, 200, paint);

        paint.setShader(new ComposeShader(shader1, shader2, PorterDuff.Mode.LIGHTEN));
        canvas.drawCircle(1000, 600, 200, paint);
    }
}