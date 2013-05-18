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

package com.thomasamsler.mobile.adapter;

import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.thomasamsler.mobile.R;
import com.thomasamsler.mobile.model.Person;

public class PersonAdapter extends ArrayAdapter<Person> {
	
	private Context mContext;
	private int mLayoutRsourceId;
	private List<Person> mPersons;

	public PersonAdapter(Context context, int layoutResourceId, List<Person> persons) {
		
		super(context, layoutResourceId, persons);
		
		this.mContext = context;
		this.mLayoutRsourceId = layoutResourceId;
		this.mPersons = persons;
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		
		View row = convertView;
		PersonHolder holder = null;
		
		if(null == row) {
			
			LayoutInflater infater = ((Activity) mContext).getLayoutInflater();
			row = infater.inflate(mLayoutRsourceId, parent, false);
			
			holder = new PersonHolder();
			holder.name = (TextView) row.findViewById(R.id.tv_person_name);
			
			row.setTag(R.string.view_tag_key_holder, holder);
		}
		else {
			
			holder = (PersonHolder) row.getTag(R.string.view_tag_key_holder);
		}
		
		Person person = mPersons.get(position);
		
		row.setTag(R.string.view_tag_key_data, person);
		
		holder.name.setText(person.name);
		
		return row;
	}
	
	static class PersonHolder {
		
		TextView name;
	}
}
