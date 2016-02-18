package com.onix.control;


import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.ImageButton;


public class ColorizeButton extends ImageButton {

    private static final int BUTTON_COLOR_PRESSED = Color.WHITE;
    private static final int BUTTON_COLOR_NORMAL = Color.GRAY;
    private static final int BUTTON_COLOR_DISABLE = Color.LTGRAY;
    private static final int BUTTON_COLOR_FOCUSED = Color.BLUE;
    private static final String TAG = ColorizeButton.class.getSimpleName();
    public static final int DEF_MAX_SIZE_WHEN_LOW_MEMORY = 150;
    private Drawable mDrawable = getDrawable();
    private int mCurrentButtonColor;
    private int mNormalColor = BUTTON_COLOR_NORMAL;
    private int mPressedColor = BUTTON_COLOR_PRESSED;
    private int mDisableColor = BUTTON_COLOR_DISABLE;
    private int mFocusedColor = BUTTON_COLOR_FOCUSED;
    private int mMaxSizeWhenLowMemory;
    private boolean DEBUG = false;
    private int mResourceImage = 0;

    public ColorizeButton(Context context) {
        super(context);
        initView(context, null);
    }

    public ColorizeButton(Context context, AttributeSet attrs, int deffStyle) {
        super(context, attrs, deffStyle);
        initView(context, attrs);
    }

    public ColorizeButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context, attrs);
    }

    private void initView(Context context, AttributeSet attrs) {
        mMaxSizeWhenLowMemory = DEF_MAX_SIZE_WHEN_LOW_MEMORY;
        if (attrs != null) {
            TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.state, 0, 0);
            try {
                mNormalColor = ta.getColor(R.styleable.state_state_normal, BUTTON_COLOR_NORMAL);
                mPressedColor = ta.getColor(R.styleable.state_state_pressed, BUTTON_COLOR_PRESSED);
                mDisableColor = ta.getColor(R.styleable.state_state_disable, BUTTON_COLOR_DISABLE);
                mFocusedColor = ta.getColor(R.styleable.state_state_focused, BUTTON_COLOR_FOCUSED);
            } finally {
                ta.recycle();
            }
        }
    }

    public void enableDebug(boolean enable) {
        DEBUG = enable;
    }

    public int getMaxSizeWhenOutOffMemory() {
        return mMaxSizeWhenLowMemory;
    }

    public void setMaxSizeWhenOutOffMemory(int size) {
        mMaxSizeWhenLowMemory = size;
    }

    public int getDisabledColor() {
        return mDisableColor;
    }

    public void setDisabledColor(int mDisableColor) {
        this.mDisableColor = mDisableColor;
    }

    public int getPressedColor() {
        return mPressedColor;
    }

    public void setPressedColor(int mPressedColor) {
        this.mPressedColor = mPressedColor;
    }

    public int getNormalColor() {
        return mNormalColor;
    }

    public void setNormalColor(int normalColor) {
        this.mNormalColor = normalColor;
    }

    public int getButtonColor() {
        return mCurrentButtonColor;
    }

    public void setButtonColor(int buttonColor) {
        mCurrentButtonColor = buttonColor;
    }

    public Drawable getImage() {
        return getDrawable();
    }

    public int getFocusedColor() {
        return mFocusedColor;
    }

    public void setFocusedColor(int mFocusedColor) {
        this.mFocusedColor = mFocusedColor;
    }


    public void setColorizedImage(Bitmap bitmap) {
        try {
            setColorizedImage(new BitmapDrawable(getResources(), bitmap));
        } catch (DrawableNotSupportedException e) {
            e.printStackTrace();
        }
    }

    public void setColorizedImage(int mResourceImage) throws DrawableNotSupportedException {
        this.mResourceImage = mResourceImage;
        Drawable drawable;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP_MR1) {
            drawable = getContext().getResources().getDrawable(mResourceImage, getContext().getTheme());
        } else {
            drawable = getResources().getDrawable(mResourceImage);
        }
        setColorizedImage(drawable);
    }

    public void setColorizedImage(Drawable drawable) throws DrawableNotSupportedException {
        if (drawable instanceof BitmapDrawable) {
            this.mDrawable = drawable;
        } else {
            throw new DrawableNotSupportedException(getContext().getString(com.onix.control.R.string.err_type_of_resource));
        }
    }

    @Override
    protected void drawableStateChanged() {
        if (isPressed()) {
            setButtonColor(mPressedColor);
            if (mPressedColor == 0) {
                LOG(getContext().getString(R.string.err_forgot_pressed_color));
            }
        } else {
            setButtonColor(mNormalColor);
            if (mNormalColor == 0) {
                LOG(getContext().getString(R.string.err_forgot_normal_color));
            }
            if (!isClickable()) {
                setButtonColor(mDisableColor);
                if (mDisableColor == 0) {
                    LOG(getContext().getString(R.string.err_forgot_disable_color));
                }
            }
        }
        if (isFocusableInTouchMode() || isFocused()) {
            setButtonColor(mFocusedColor);
            if (mFocusedColor == 0) {
                LOG(getContext().getString(R.string.err_forgot_focused_color));
            }
        }
        processImage();
        super.drawableStateChanged();
    }

    private void processImage() {
        if (mDrawable != null) {
            try {
                setImageDrawable(new BitmapDrawable(getResources(), processImage(((BitmapDrawable) mDrawable).getBitmap(),getButtonColor())));
                LOG(getContext().getString(R.string.successful));
            } catch (OutOfMemoryError e) {
                LOG(getContext().getString(R.string.out_of_memory_error));
            }
        } else {
            LOG(getContext().getString(R.string.err_set_src));
        }
    }

    private Bitmap processImage(Bitmap bitmap, int color) {
        try {
            Bitmap filteredBitmap = filterImage(bitmap, color);
            LOG(getContext().getString(R.string.successful));
            return filteredBitmap;
        } catch (OutOfMemoryError error) {
            // try to scale image
            try {
                Bitmap scaledBitmap = scaleBitmap(bitmap);
                Bitmap filteredBitmap = filterImage(scaledBitmap, color);
                LOG(getContext().getString(R.string.out_of_memory_error));
                return filteredBitmap;
            } catch (OutOfMemoryError er) {
                return null;
            }
        }
    }
    private Bitmap scaleBitmap(Bitmap bitmap) {
        if (mMaxSizeWhenLowMemory <= 0)
            mMaxSizeWhenLowMemory = DEF_MAX_SIZE_WHEN_LOW_MEMORY;
        int height = bitmap.getHeight();
        int width = bitmap.getWidth();
        float coefficient;
        if (height < width) {
            coefficient = (float) height / mMaxSizeWhenLowMemory;
        } else {
            coefficient = (float) width / mMaxSizeWhenLowMemory;
        }
        Bitmap scaledBitmap = Bitmap.createScaledBitmap(bitmap, (int) (width / coefficient), (int) (height / coefficient), true);
        return scaledBitmap;
    }

    private Bitmap filterImage(Bitmap original, int color) {
        Bitmap invertBitmap = Bitmap.createBitmap(original.getWidth(),
                original.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(invertBitmap);
        Paint paint = new Paint();
        paint.setColorFilter(new ColorMatrixColorFilter(getInvertColor()));
        canvas.drawBitmap(original, 0, 0, paint);
        Bitmap bitmap = Bitmap.createBitmap(original.getWidth(),
                original.getHeight(), Bitmap.Config.ARGB_8888);
        canvas = new Canvas(bitmap);
        paint = new Paint();
        paint.setColorFilter(new ColorMatrixColorFilter(getColorMatrix(color)));
        canvas.drawBitmap(invertBitmap, 0, 0, paint);
        return bitmap;
    }

    private ColorMatrix getColorMatrix(int color) {
        return new ColorMatrix(new float[]{
                1, 0, 0, 0, (color >> 16) & 0xFF,
                0, 1, 0, 0, (color >> 8) & 0xFF,
                0, 0, 1, 0, (color) & 0xFF,
                0, 0, 0, 1, 0
        });
    }

    private ColorMatrix getInvertColor() {
        return new ColorMatrix(new float[]{
                -1, 0, 0, 0, 255,
                0, -1, 0, 0, 255,
                0, 0, -1, 0, 255,
                0, 0, 0, 1, 0
        });
    }

    private void LOG(String message) {
        if (DEBUG) {
            Log.e(TAG, message);
        }
    }

    @Override
    public Parcelable onSaveInstanceState()
    {
        Bundle bundle = new Bundle();
        bundle.putParcelable("controlState", super.onSaveInstanceState());
        bundle.putInt("mNormalColor", this.mNormalColor);
        bundle.putInt("mPressedColor", this.mPressedColor);
        bundle.putInt("mDisableColor", this.mDisableColor);
        bundle.putInt("mFocusedColor", this.mFocusedColor);
        bundle.putInt("mResourceImage", mResourceImage);
        return bundle;
    }

    @Override
    public void onRestoreInstanceState(Parcelable state)
    {
        if (state instanceof Bundle)
        {
            Bundle bundle = (Bundle) state;
            this.mNormalColor = bundle.getInt("mNormalColor");
            this.mPressedColor = bundle.getInt("mPressedColor");
            this.mDisableColor = bundle.getInt("mDisableColor");
            this.mFocusedColor = bundle.getInt("mFocusedColor");
            this.mResourceImage = bundle.getInt("mResourceImage");
            if(mResourceImage != 0) {
                mDrawable = getResources().getDrawable(mResourceImage);
            }
            state = bundle.getParcelable("controlState");

        }
        super.onRestoreInstanceState(state);
    }

}

