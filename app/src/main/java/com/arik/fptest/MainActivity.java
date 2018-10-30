package com.arik.fptest;

import android.graphics.Bitmap;
import android.hardware.usb.UsbDevice;
import android.hardware.usb.UsbManager;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;

import com.arik.fm220sdk.FPInterface;
import com.arik.fm220sdk.FPSDK;
import com.bumptech.glide.Glide;

public class MainActivity extends AppCompatActivity {
    private boolean cancelled = false;
    private boolean running = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);

        final ImageView iView = findViewById(R.id.imageView);

        final UsbDevice dev  = getIntent().getParcelableExtra(UsbManager.EXTRA_DEVICE);

        fab.setOnClickListener(view -> {
            if (running) {
                cancelled = true;
                running = false;
                return;
            }
            if (dev != null) {
                new Thread(() -> {
                    running = true;
                    cancelled = false;
                    try (FPSDK sdk = FPSDK.with(MainActivity.this).from(dev)) {
                        sdk.startPreview(new FPInterface() {
                            @Override
                            public void onImage(Bitmap image, int nfiq) {
                                runOnUiThread(() -> {
                                    Glide.with(MainActivity.this).load(image).into(iView);
                                });
                            }

                            @Override
                            public boolean cancelled() {
                                return cancelled;
                            }
                        });
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }).start();
            }
        });
    }
}
