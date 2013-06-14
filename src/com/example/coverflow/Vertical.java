package com.example.coverflow;
//This class transforms gallery class from horizontal to vertical
import android.graphics.Camera;
import android.graphics.Matrix;
import android.view.animation.Transformation;
import android.widget.ImageView;

public class Vertical implements Arrangement{
	Camera mCamera = new Camera();
	private int del;
	@Override
	public void transformImageBitmap(ImageView child, Transformation t, int i,
			int j, CoverFlow c) {
		// TODO Auto-generated method stub
		mCamera.save();
		Matrix imageMatrix = t.getMatrix();
		final int imageHeight = child.getLayoutParams().height;
		final int imageWidth = child.getLayoutParams().width;
		final int rotation = Math.abs(i);

		mCamera.translate(0.0f, 0.0f, 100.0f);

		// As the angle of the view gets less, zoom in
		if (rotation <60) {
			float zoomAmount = (float) (-120 + (rotation * 1.5));
			mCamera.translate(0.0f, 0.0f, zoomAmount);
		}
		setDelta(c.getWidth()/2);
		mCamera.rotateY(i);
		mCamera.getMatrix(imageMatrix);
		imageMatrix.preTranslate(-(imageWidth / 2), -(imageHeight / 2));
		float tx =-j;
		//tx = (float) Math.sqrt(getCenterOfCoverflow()*getCenterOfCoverflow()-dx*dx);
		final int ty = (int) (Math.floor( (2*(double)j)/c.getWidth() * child.getHeight() ));
		imageMatrix.postTranslate(tx+c.getWidth()/5, (imageHeight / 2) + ty);
		mCamera.restore();
	}
	private void setDelta(int i) {
		// TODO Auto-generated method stub
		del=i;
	}
	@Override
	public int getDelta() {
		// TODO Auto-generated method stub
		return del;
	}


}
