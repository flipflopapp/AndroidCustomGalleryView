package com.example.coverflow;

/*
 * Copyright (C) 2010 Neil Davies
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * 
 * This code is base on the Android Gallery widget and was Created 
 * by Neil Davies neild001 'at' gmail dot com to be a Coverflow widget
 * 
 * @author Neil Davies
 */

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Transformation;
import android.widget.Gallery;
import android.widget.ImageView;

public class CoverFlow extends Gallery {

	Arrangement arr;
	/**
	 * Graphics Camera used for transforming the matrix of ImageViews
	 */
	//private Camera mCamera = new Camera();

	/**
	 * The maximum angle the Child ImageView will be rotated by
	 */
	private int mMaxRotationAngle = 60;

	/**
	 * The maximum zoom on the centre Child
	 */
	private int mMaxZoom = -120;

	/**
	 * The Centre of the Coverflow
	 */
	private int mCoveflowCenter;

	private float mZAxis = 100.0f;

	public CoverFlow(Context context) {
		super(context);
		this.setStaticTransformationsEnabled(true);
	}

	public CoverFlow(Context context, AttributeSet attrs) {
		super(context, attrs);
		this.setStaticTransformationsEnabled(true);
		TypedArray a = context.obtainStyledAttributes(attrs,
				R.styleable.CoverFlow);
		int val = a.getInteger(R.styleable.CoverFlow_orientation, 0);
		if(val==0){
			arr = new Vertical();
		}
		else if(val==1){
			arr = new SemiCircular();
		}
		a.recycle();
	}

	public CoverFlow(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		this.setStaticTransformationsEnabled(true);
		TypedArray a = context.obtainStyledAttributes(attrs,
				R.styleable.CoverFlow);
		int val = a.getInteger(R.styleable.CoverFlow_orientation, 0);
		if(val==0){
			arr = new Vertical();
		}
		else if(val==1){
			arr = new SemiCircular();
		}
		a.recycle();
	}

	/**
	 * Get the max rotational angle of the image
	 * 
	 * @return the mMaxRotationAngle
	 */
	public int getMaxRotationAngle() {
		return mMaxRotationAngle;
	}

	/**
	 * Set the max rotational angle of each image
	 * 
	 * @param maxRotationAngle
	 *            the mMaxRotationAngle to set
	 */
	public void setMaxRotationAngle(int maxRotationAngle) {
		mMaxRotationAngle = maxRotationAngle;
	}

	/**
	 * Get the Max zoom of the centre image
	 * 
	 * @return the mMaxZoom
	 */
	public int getMaxZoom() {
		return mMaxZoom;
	}

	/**
	 * Set the max zoom of the centre image
	 * 
	 * @param maxZoom
	 *            the mMaxZoom to set
	 */
	public void setMaxZoom(int maxZoom) {
		mMaxZoom = maxZoom;
	}

	public float getmZAxis() {
		return mZAxis;
	}

	public void setmZAxis(float mZAxis) {
		this.mZAxis = mZAxis;
	}

	/**
	 * Get the Centre of the Coverflow
	 * 
	 * @return The centre of this Coverflow.
	 */
	private int getCenterOfCoverflow() {
		return (getWidth() - getPaddingLeft() - getPaddingRight()) / 2
				+ getPaddingLeft();
	}

	/**
	 * Get the Centre of the View
	 * 
	 * @return The centre of the given view.
	 */
	private static int getCenterOfView(View view) {
		return view.getLeft() + view.getWidth() / 2;
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see #setStaticTransformationsEnabled(boolean)
	 */
	protected boolean getChildStaticTransformation(View child, Transformation t) {
		final int childCenter = getCenterOfView(child);
		final int childWidth = child.getWidth();
		int rotationAngle = 0;

		t.clear();
		t.setTransformationType(Transformation.TYPE_MATRIX);

		if (childCenter == mCoveflowCenter) {
			arr.transformImageBitmap((ImageView) child, t, 0, 0,this);
		} else {
			rotationAngle = (int) (((float) (mCoveflowCenter - childCenter) / childWidth) * mMaxRotationAngle);
			if (Math.abs(rotationAngle) > mMaxRotationAngle) {
				rotationAngle = (rotationAngle < 0) ? -mMaxRotationAngle
						: mMaxRotationAngle;
			}
			arr.transformImageBitmap((ImageView) child, t, rotationAngle, (childCenter - mCoveflowCenter),this);
		}

		return true;
	}

	/**
	 * This is called during layout when the size of this view has changed. If
	 * you were just added to the view hierarchy, you're called with the old
	 * values of 0.
	 * 
	 * @param w
	 *            Current width of this view.
	 * @param h
	 *            Current height of this view.
	 * @param oldw
	 *            Old width of this view.
	 * @param oldh
	 *            Old height of this view.
	 */
	protected void onSizeChanged(int w, int h, int oldw, int oldh) {
		mCoveflowCenter = getCenterOfCoverflow();
		super.onSizeChanged(w, h, oldw, oldh);
	}


	@Override
	public boolean onDown(MotionEvent e) {
		getParent().requestDisallowInterceptTouchEvent(true);
		//Log.e("tag", "old x"+e.getX()+"old y"+e.getY());
		float tempx = Math.abs(e.getX());
		float tempy = Math.abs(e.getY());
		float div = (float) (GlobalPrefs.getScreenWidth()/3.6);
		e.offsetLocation((-tempx+tempy-div),-tempy+tempx+arr.getDelta()/3);
		//Log.e("tag", "new x"+e.getX()+"new y"+e.getY());
		return super.onDown(e);
	}	

	@Override
	public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX,
			float velocityY) {
		return super.onFling(e1, e2, velocityY, velocityX);
	}

	@Override
	public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX,
			float distanceY) {

		getParent().requestDisallowInterceptTouchEvent(true);

		return super.onScroll(e1, e2, distanceY, distanceX);
	}
}