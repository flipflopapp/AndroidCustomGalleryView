package com.example.coverflow;

import android.graphics.Camera;
import android.graphics.Matrix;
import android.view.View;
import android.view.animation.Transformation;
import android.widget.Gallery;
import android.widget.ImageView;

public interface Arrangement {
	void transformImageBitmap(ImageView child, Transformation t, int i, int j, CoverFlow coverFlow);
	int getDelta();
}
