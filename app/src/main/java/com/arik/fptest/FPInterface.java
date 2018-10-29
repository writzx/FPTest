package com.arik.fptest;

import android.graphics.Bitmap;

public interface FPInterface {
    void onImage(Bitmap image, int nfiq);
    boolean cancelled();
}
