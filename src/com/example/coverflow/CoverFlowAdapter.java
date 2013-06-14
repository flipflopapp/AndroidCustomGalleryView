package com.example.coverflow;

import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

public class CoverFlowAdapter extends BaseAdapter {
	int mGalleryItemBackground;
	private Context mContext;

	private Integer[] mImageIds = {
			R.drawable.img1,
			R.drawable.img2,
			R.drawable.img3,
			R.drawable.img4,
			R.drawable.img5,
			R.drawable.img6,
			R.drawable.img7,
			R.drawable.img8,
			R.drawable.img1,
			R.drawable.img2,
			R.drawable.img3,
			R.drawable.img4,
			R.drawable.img5,
			R.drawable.img6,
			R.drawable.img7,
			R.drawable.img8
	};

	public CoverFlowAdapter(Context c) {
		mContext = c;
	}
	public int getCount() {
		return mImageIds.length;
	}

	public Object getItem(int position) {
		return position;
	}

	public long getItemId(int position) {
		return position;
	}

	public View getView(int position, View convertView, ViewGroup parent) {

		//Use this code if you want to load from resources
		ImageView i = new ImageView(mContext);
		i.setImageResource(mImageIds[position]);
		i.setLayoutParams(new CoverFlow.LayoutParams(130, 130));
		i.setScaleType(ImageView.ScaleType.CENTER_INSIDE); 

		//Make sure we set anti-aliasing otherwise we get jaggies
		BitmapDrawable drawable = (BitmapDrawable) i.getDrawable();
		drawable.setAntiAlias(true);
		return i;

		//return mImages[position];
	}
	/** Returns the size (0.0f to 1.0f) of the views 
	 * depending on the 'offset' to the center. */ 
	public float getScale(boolean focused, int offset) { 
		/* Formula: 1 / (2 ^ offset) */ 
		return Math.max(0, 1.0f / (float)Math.pow(2, Math.abs(offset))); 
	} 
}
