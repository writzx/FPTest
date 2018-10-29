package com.arik.fptest;

import android.graphics.Bitmap;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

/**
 * A placeholder fragment containing a simple view.
 */
public class FPActivityFragment extends Fragment {
    static ImageView fpImage;

    public FPActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_fp, container, false);

        if (root != null && getActivity() != null) {
            fpImage = root.findViewById(R.id.fpImage);
        }

        return root;
    }
}
