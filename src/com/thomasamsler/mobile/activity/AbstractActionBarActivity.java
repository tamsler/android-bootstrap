/*
 * Copyright 2012, 2013 Thomas Amsler
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * 
 */

package com.thomasamsler.mobile.activity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.thomasamsler.mobile.AppConstants;
import com.thomasamsler.mobile.MainApplication;
import com.thomasamsler.mobile.R;

public abstract class AbstractActionBarActivity extends Activity implements AppConstants {

	protected MainApplication mMainApplication;

	private List<String> mMoreListItems;
	private ListView mMoreListView;
	protected TextView mActionBarTitleTextView;

	protected LinearLayout mMoreLinearLayout;

	// Previous View Tap Target
	protected View mActionBarPreviousView;

	// Action Bar
	protected ImageView mActionBarPreviousImageView;
	protected ProgressBar mActionBarProgressBar;

	// Split Action Bar
	protected ImageButton mSplitActionBarScanImageButton;

	// Listener(s)
	private OnItemClickListener moreItemListenerRef;

	public abstract void onMoreListItemClicked(int position);

	@Override
	public void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);

		mMainApplication = (MainApplication) getApplication();

		// Listeners(s)
		moreItemListenerRef = moreOnItemClickListener();

		// Action Bar
		mActionBarPreviousImageView = (ImageView) findViewById(R.id.iv_action_bar_previous);
		mActionBarPreviousView = (View) findViewById(R.id.v_action_bar_previous);

		mActionBarTitleTextView = (TextView) findViewById(R.id.tv_action_bar_title);
		mActionBarProgressBar = (ProgressBar) findViewById(R.id.pb_action_bar);

		mMoreLinearLayout = (LinearLayout) findViewById(R.id.ll_action_bar_more);

		// Split Action Bar
		mSplitActionBarScanImageButton = (ImageButton) findViewById(R.id.ib_split_action_bar_share);

		// More Menu
		mMoreListItems = new ArrayList<String>();
		mMoreListView = (ListView) findViewById(R.id.lv_action_bar_more);
		mMoreListView.setAdapter(new ArrayAdapter<String>(this, R.layout.action_bar_more_item, mMoreListItems));
		mMoreListView.setOnItemClickListener(moreItemListenerRef);
	}

	@Override
	public boolean onKeyUp(int keyCode, KeyEvent event) {

		super.onKeyUp(keyCode, event);

		if (keyCode == KeyEvent.KEYCODE_MENU) {

			toggleMoreMenuActions(null);
		}

		return true;
	}

	/*
	 * Allow extending Activity to set more list items
	 */
	protected void setMoreListItems(String[] items) {

		mMoreListItems.addAll(Arrays.asList(items));
	}

	/*
	 * Allow extending Activity to hide the previous button
	 */
	protected void hideActionBarPreviousButton() {

		mActionBarPreviousImageView.setVisibility(View.INVISIBLE);
		mActionBarPreviousView.setVisibility(View.GONE);
	}

	/*
	 * OnClickListener for clicking on previous view on action bar
	 */
	public void showPreviousActivity(View view) {

		setResult(RESULT_CANCELED);
		finish();
	}

	/*
	 * OnClickListener for toggling the more menu on action bar
	 */
	public void toggleMoreMenuActions(View view) {

		if (mMoreLinearLayout.getVisibility() == View.VISIBLE) {

			mMoreLinearLayout.setVisibility(View.GONE);
		}
		else {

			mMoreLinearLayout.setVisibility(View.VISIBLE);
		}
	}

	/*
	 * onItemClick listener for the more menu
	 */
	private OnItemClickListener moreOnItemClickListener() {

		return new OnItemClickListener() {

			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

				mMoreLinearLayout.setVisibility(View.GONE);
				onMoreListItemClicked(position);
			}
		};
	}

}
