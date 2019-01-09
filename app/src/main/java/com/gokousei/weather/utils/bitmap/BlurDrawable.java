package com.gokousei.weather.utils.bitmap;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.renderscript.Allocation;
import android.renderscript.Element;
import android.renderscript.RenderScript;
import android.renderscript.ScriptIntrinsicBlur;

public class BlurDrawable {

    private static BlurDrawable instance = new BlurDrawable();

    private static final int SCALE = 8;
    private int alpha;

    private BlurDrawable() {
        alpha = 0;
    }

    public static BlurDrawable getInstance() {
        return instance;
    }

    private Drawable[] drawablesArray = new Drawable[2];
    private LayerDrawable layerDrawable;

    public LayerDrawable BlurDrawable(Context context, Resources resources, Bitmap bitmap) {
        drawablesArray[0] = new BitmapDrawable(resources, bitmap);
        drawablesArray[1] = new BitmapDrawable(resources, doBlur(context, bitmap, 25));
        drawablesArray[1].setAlpha(alpha);
        layerDrawable = new LayerDrawable(drawablesArray);
        layerDrawable.setLayerInset(0, 0, 0, 0, 0);
        layerDrawable.setLayerInset(1, 0, 0, 0, 0);
        return layerDrawable;
    }

    /**
     * 使用Android RenderScript进行图片模糊处理
     *
     * @param context Context
     * @param bitmap  待处理的图片
     * @param radius  模糊度
     * @return 返回处理的图片
     */
    private Bitmap doBlur(Context context, Bitmap bitmap, float radius) {
        Bitmap inputBitmap = Bitmap.createScaledBitmap(bitmap,
                bitmap.getWidth() / SCALE, bitmap.getHeight() / SCALE, false);
        RenderScript script = RenderScript.create(context);
        Allocation input = Allocation.createFromBitmap(script, inputBitmap, Allocation.MipmapControl.MIPMAP_NONE,
                Allocation.USAGE_SCRIPT);
        Allocation output = Allocation.createTyped(script, input.getType());
        ScriptIntrinsicBlur intrinsicBlur = ScriptIntrinsicBlur.create(script, Element.U8_4(script));
        intrinsicBlur.setRadius(radius);
        intrinsicBlur.setInput(input);
        intrinsicBlur.forEach(output);
        output.copyTo(inputBitmap);
        script.destroy();
        return inputBitmap;
    }

    public BlurDrawable setAlpha(int alpha) {
        this.alpha = alpha;
        return this;
    }
}
