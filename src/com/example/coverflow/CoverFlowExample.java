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
package com.example.coverflow;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Display;

public class CoverFlowExample extends Activity {

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		Display display = getWindowManager().getDefaultDisplay();
	    DisplayMetrics metrics = new DisplayMetrics();
	    display.getMetrics(metrics);
		GlobalPrefs.setDensity(metrics.density);
		GlobalPrefs.setScreenWidth(metrics.widthPixels);

		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
		CoverFlow coverFlow;
		coverFlow = (CoverFlow) findViewById(R.id.coverflow);
		//coverFlow = new CoverFlow(this);
		CoverFlowAdapter coverAdapter =  new CoverFlowAdapter(this);

		coverFlow.setAdapter(coverAdapter);

		coverFlow.setSpacing(-25);
		coverFlow.setSelection(4, true);
		coverFlow.setAnimationDuration(1000);


		//setContentView(coverFlow);
	}

}
