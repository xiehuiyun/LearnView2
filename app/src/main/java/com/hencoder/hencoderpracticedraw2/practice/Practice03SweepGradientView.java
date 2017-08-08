package com.hencoder.hencoderpracticedraw2.practice;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.SweepGradient;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

public class Practice03SweepGradientView extends View {
    Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);

    public Practice03SweepGradientView(Context context) {
        super(context);
    }

    public Practice03SweepGradientView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public Practice03SweepGradientView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    {
        // 用 Paint.setShader(shader) 设置一个 SweepGradient
        // SweepGradient 的参数：圆心坐标：(300, 300)；颜色：#E91E63 到 #2196F3
        //注释的代码：
        //可能是NULL。颜色数组中每个对应颜色的相对位置，从0开始，结束为1.0。
        // 如果这些值不是单调的，那么绘图可能会产生意想不到的结果。
        // 如果位置是空的，那么颜色就会自动间隔。
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        paint.setShader(new SweepGradient(300,300, Color.parseColor("#E91E63"),Color.parseColor("#2196F3")));
//        int[] colors = new int[]{Color.parseColor("#E91E63"), Color.parseColor("#2196F3")};
//        float[] positions = {0.5f, 1};
//        paint.setShader(new SweepGradient(300, 300, colors, positions));
        canvas.drawCircle(300, 300, 200, paint);

    }
}
