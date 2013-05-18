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
import java.util.List;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;

import com.thomasamsler.mobile.R;
import com.thomasamsler.mobile.adapter.PersonAdapter;
import com.thomasamsler.mobile.model.Person;

public class MainActivity extends AbstractActionBarActivity {

	// Action Bar More Menu Items (more_actions.xml)
	private static final int MORE_A = 0;
	private static final int MORE_B = 1;

	// List View
	private ListView mPersonListView;
	private List<Person> mPersonList;
	private PersonAdapter mPersonAdapter;


	@Override
	public void onCreate(Bundle savedInstanceState) {

		setContentView(R.layout.activity_main);
		super.onCreate(savedInstanceState);

		/*
		 * Hide the action bar's previous button
		 */
		hideActionBarPreviousButton();

		mPersonList = new ArrayList<Person>();
		mPersonAdapter = new PersonAdapter(this, R.layout.list_view_person_item, mPersonList);
		mPersonListView = (ListView) findViewById(R.id.lv_persons);
		mPersonListView.setAdapter(mPersonAdapter);

		// Add Action Bar more list items
		String[] actions = getResources().getStringArray(R.array.more_actions_main);
		setMoreListItems(actions);

		// Setup Split Action Bar View
		mSplitActionBarScanImageButton.setVisibility(View.VISIBLE);

		// DEBUG
		mPersonList.add(new Person("Thomas Amsler"));
	}

	@Override
	public void onMoreListItemClicked(int position) {

		switch (position) {

		case MORE_A:
			Log.i(LOG_DEBUG, "MORE A");
			break;

		case MORE_B:
			Log.i(LOG_DEBUG, "MORE B");
			break;
		}
	}
	
	/*
	 * OnClickListener for Persons list view items
	 */
	public void showPerson(View view) {
		
		Person person = (Person) view.getTag(R.string.view_tag_key_data);

		Intent intent = new Intent(MainActivity.this, PersonActivity.class);
		intent.putExtra(PERSON_ACTIVITY_EXTRA_KEY, person.name);
		startActivity(intent);
	}


	/*
	 * OnClickListener for doing something
	 */
	public void doSomeAction(View view) {
		
		Log.i(LOG_DEBUG, "doing something");
	}
}
