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

import android.os.Bundle;
import android.widget.TextView;

import com.thomasamsler.mobile.R;



public class PersonActivity extends AbstractActionBarActivity {

	private TextView mPersonTextView;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		
		setContentView(R.layout.activity_person);
		super.onCreate(savedInstanceState);
		
		mPersonTextView = (TextView) findViewById(R.id.tv_person);
		
		String personName = getIntent().getStringExtra(PERSON_ACTIVITY_EXTRA_KEY);
		mPersonTextView.setText(personName);
		
	}
	
	
	@Override
	public void onMoreListItemClicked(int position) {

		// TODO Auto-generated method stub

	}

}
