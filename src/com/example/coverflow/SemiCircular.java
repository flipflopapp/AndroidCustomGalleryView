package com.example.coverflow;
//This class transforms gallery class into a semicircle
import android.graphics.Camera;
import android.graphics.Matrix;
import android.view.animation.Transformation;
import android.widget.ImageView;

public class SemiCircular implements Arrangement {
	Camera mCamera = new Camera();
	int del;
	SemiCircular(){
	}
	@Override
	public void transformImageBitmap(ImageView child, Transformation t, int i,
			int j,CoverFlow c) {
		// TODO Auto-generated method stub
		mCamera.save();
		Matrix imageMatrix = t.getMatrix();
		final int imageHeight = child.getLayoutParams().height;
		final int imageWidth = child.getLayoutParams().width;
		final int rotation = Math.abs(i);
		int center = (c.getHeight() - c.getPaddingTop() - c.getPaddingBottom()) / 2 + c.getPaddingTop();

		mCamera.translate(0.0f, 0.0f, 100.0f);

		// As the angle of the view gets less, zoom in
		if (rotation < 60) {
			float zoomAmount = (float) (-120 + (rotation * 1.5));
			mCamera.translate(0.0f, 0.0f, zoomAmount);
		}

		mCamera.rotateY(i);
		mCamera.getMatrix(imageMatrix);
		imageMatrix.preTranslate(0, -(imageHeight / 2));
		float delta, tx = -j - imageWidth;//this is delta to arrange the elements in one single line
		int thisc = center - center/8;//smaller radius of cicle for a better curve using formula sqrt(radius^2-x^2)
		final int ty = (int) (Math.floor( (2*(double)j)/c.getWidth() * child.getHeight() ));//y value calculated by Naval
		delta = (float) Math.sqrt((thisc*thisc)-ty*ty);//parabolic delta using circle formula to be added in x position
		//Log.e("tag", "msg"+delta);
		del=(int) delta;
		imageMatrix.postTranslate((float) (tx+delta-imageWidth/2), (imageHeight / 2) + ty);
		mCamera.restore();
	}
	@Override
	public int getDelta() {
		// TODO Auto-generated method stub
		return del*2;
	}

}
