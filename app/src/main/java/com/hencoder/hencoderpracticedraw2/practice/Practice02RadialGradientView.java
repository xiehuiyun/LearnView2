package com.hencoder.hencoderpracticedraw2.practice;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RadialGradient;
import android.graphics.Shader;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

public class Practice02RadialGradientView extends View {
    Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);

    public Practice02RadialGradientView(Context context) {
        super(context);
    }

    public Practice02RadialGradientView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public Practice02RadialGradientView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    {
        // 用 Paint.setShader(shader) 设置一个 RadialGradient
        // RadialGradient 的参数：圆心坐标：(300, 300)；半径：200；颜色：#E91E63 到 #2196F3
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        paint.setShader(new RadialGradient(150,150,100,Color.parseColor("#E91E63"),Color.parseColor("#2196F3"), Shader.TileMode.CLAMP));
        canvas.drawCircle(150, 150, 100, paint);

        paint.setShader(new RadialGradient(400,150,35,Color.parseColor("#E91E63"),Color.parseColor("#2196F3"), Shader.TileMode.MIRROR));
        canvas.drawCircle(400, 150, 100, paint);

        paint.setShader(new RadialGradient(650,150,50,Color.parseColor("#E91E63"),Color.parseColor("#2196F3"), Shader.TileMode.REPEAT));
        canvas.drawCircle(650, 150, 100, paint);

        paint.setTextSize(36);
        canvas.drawText("分别是：Shader.TileMode.CLAMP--延伸渐变",0,31,10,300,paint);

        canvas.drawText("Shader.TileMode.MIRROR--镜像倒影",0,27,145,350,paint);

        canvas.drawText("Shader.TileMode.REPEAT--重复",0,26,145,400,paint);
    }
}
