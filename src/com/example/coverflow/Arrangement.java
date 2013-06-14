package com.example.coverflow;
//Interface for different variations

import android.view.animation.Transformation;
import android.widget.ImageView;

public interface Arrangement {
	void transformImageBitmap(ImageView child, Transformation t, int i, int j, CoverFlow coverFlow);
	int getDelta();
}
