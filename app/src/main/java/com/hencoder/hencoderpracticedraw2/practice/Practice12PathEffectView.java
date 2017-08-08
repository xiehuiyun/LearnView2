package com.hencoder.hencoderpracticedraw2.practice;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.ComposePathEffect;
import android.graphics.CornerPathEffect;
import android.graphics.DashPathEffect;
import android.graphics.DiscretePathEffect;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathDashPathEffect;
import android.graphics.SumPathEffect;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

public class Practice12PathEffectView extends View {
    Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    Path path = new Path();
    CornerPathEffect cornerPathEffect;
    DiscretePathEffect discretePathEffect;
    DashPathEffect dashPathEffect;
    PathDashPathEffect pathDashPathEffect;


    public Practice12PathEffectView(Context context) {
        super(context);
    }

    public Practice12PathEffectView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public Practice12PathEffectView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    {
        paint.setStyle(Paint.Style.STROKE);

        path.moveTo(50, 100);
        path.rLineTo(50, 100);
        path.rLineTo(80, -150);
        path.rLineTo(100, 100);
        path.rLineTo(70, -120);
        path.rLineTo(150, 80);

        //它的构造方法 CornerPathEffect(float radius) 的参数 radius 是圆角的半径。
        cornerPathEffect = new CornerPathEffect(20);

        /*第二处：DiscretePathEffect,参数是段长  和偏离
        DiscretePathEffect 具体的做法是，把绘制改为使用定长的线段来拼接，并且在拼接的时候对路径进行随机偏离。
        它的构造方法 DiscretePathEffect(float segmentLength, float deviation) 的两个参数中，
        segmentLength 是用来拼接的每个线段的长度， deviation 是偏离量。这两个值设置得不一样，
        显示效果也会不一样，具体的你自己多试几次就明白了，这里不再贴更多的图。*/
        discretePathEffect = new DiscretePathEffect(20, 5);

        /*
        它的构造方法 DashPathEffect(float[] intervals, float phase) 中， 第一个参数 intervals 是一个数组，
        它指定了虚线的格式：数组中元素必须为偶数（最少是 2 个），按照「画线长度、空白长度、画线长度、空白长度」……的顺序排列，
        例如上面代码中的 20, 5, 10, 5 就表示虚线是按照「画 20 像素、空 5 像素、画 10 像素、空 5 像素」的模式来绘制；
        第二个参数 phase 是虚线的偏移量。
         */
        float[] intervals = {20f, 10f, 5f, 10f};
        dashPathEffect = new DashPathEffect(intervals, 0);

        /*第四处：PathDashPathEffect
         它的构造方法 PathDashPathEffect(Path shape, float advance, float phase, PathDashPathEffect.Style style) 中，
         shape 参数是用来绘制的 Path ；
         advance 是两个相邻的 shape 段之间的间隔，不过注意，这个间隔是两个 shape 段的起点的间隔，而不是前一个的终点和后一个的起点的距离；
         phase 和 DashPathEffect 中一样，是虚线的偏移；
         最后一个参数 style，是用来指定拐弯改变的时候 shape 的转换方式。
         style 的类型为 PathDashPathEffect.Style ，是一个 enum ，具体有三个值：TRANSLATE：位移             ROTATE：旋转                    MORPH：变体*/
        Path dispath = new Path();
        dispath.lineTo(20, -30);
        dispath.lineTo(40, 0);
        dispath.close();
        pathDashPathEffect = new PathDashPathEffect(dispath, 50, 0, PathDashPathEffect.Style.MORPH);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        // 使用 Paint.setPathEffect() 来设置不同的 PathEffect

        // 第一处：CornerPathEffect
        paint.setPathEffect(cornerPathEffect);
        canvas.drawPath(path, paint);

        canvas.save();
        canvas.translate(500, 0);
        //第二处：DiscretePathEffect
        paint.setPathEffect(discretePathEffect);
        canvas.drawPath(path, paint);
        canvas.restore();

        canvas.save();
        canvas.translate(0, 200);
        // 第三处：DashPathEffect
        paint.setPathEffect(dashPathEffect);
        canvas.drawPath(path, paint);
        canvas.restore();

        canvas.save();
        canvas.translate(500, 200);
        //第四处：PathDashPathEffect
        paint.setPathEffect(pathDashPathEffect);
        canvas.drawPath(path, paint);
        canvas.restore();

        canvas.save();
        canvas.translate(0, 400);
        // 第五处：SumPathEffect,这是一个组合效果类的 PathEffect

        SumPathEffect sumPathEffect = new SumPathEffect(discretePathEffect, dashPathEffect);
        paint.setPathEffect(sumPathEffect);
        canvas.drawPath(path, paint);
        canvas.restore();

        canvas.save();
        canvas.translate(500, 400);
        /*第六处：ComposePathEffect
        它的构造方法 ComposePathEffect(PathEffect outerpe, PathEffect innerpe) 中的两个 PathEffect 参数，
        innerpe 是先应用的， outerpe 是后应用的。
        所以上面的代码就是「先偏离，再变虚线」。而如果把两个参数调换，就成了「先变虚线，再偏离」。*/
        ComposePathEffect composePathEffect = new ComposePathEffect(dashPathEffect, discretePathEffect);
        paint.setPathEffect(composePathEffect);
        canvas.drawPath(path, paint);
        canvas.restore();
    }
}
