package info.devloper.Placement;

import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Paint;

import com.squareup.picasso.Transformation;

/**
 * Created by Shivam Patel on 10-04-2017.
 */

public class CicleTransform implements Transformation
{
    @Override
    public Bitmap transform(Bitmap source) {
        int size = Math.min(source.getWidth(), source.getHeight());

        int x = (source.getWidth() - size) / 2;
        int y = (source.getHeight() - size) / 2;

        Bitmap squaredBitmap = Bitmap.createBitmap(source, x, y, size, size);
        if (squaredBitmap != source) {
            source.recycle();
        }

        Bitmap bitmap = Bitmap.createBitmap(size, size, source.getConfig());

        Canvas canvas = new Canvas(bitmap);
        Paint paint = new Paint();
        //Paint npaint = new Paint();
        BitmapShader shader = new BitmapShader(squaredBitmap,
                BitmapShader.TileMode.CLAMP, BitmapShader.TileMode.CLAMP);
        paint.setShader(shader);
        paint.setAntiAlias(true);
       // npaint.setColor(Color.RED);
       // npaint.setStyle(Paint.Style.STROKE);
        //npaint.setColor(Color.parseColor("#2980b9"));
      //  npaint.setStrokeWidth(20);


        float r = size / 2f;
        float r1= (float) (r+0.5);

        canvas.drawCircle(r, r, r, paint);
       // canvas.drawCircle(r,r,r,npaint);
        squaredBitmap.recycle();
        return bitmap;
    }

    @Override
    public String key() {
        return "circle";
    }
}
