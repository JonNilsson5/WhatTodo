package com.example.whattodo;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

public class TodoAdapter extends BaseAdapter {

	private List<TodoItem> items = new ArrayList<TodoItem>();
	private Context context;

	public TodoAdapter(Context context, List<TodoItem> items) {
		this.items = items;
		this.context = context;
	}

	public void refreshItems(List<TodoItem> items) {
		this.items = items;
		notifyDataSetChanged();
		notifyDataSetInvalidated();
	}

	@Override
	public int getCount() {
		return items.size();
	}

	@Override
	public Object getItem(int arg0) {
		return items.get(arg0);
	}

	@Override
	public long getItemId(int arg0) {
		return items.get(arg0).getId();
	}

	@Override
	public boolean isEnabled(int position) {
		return true;
	}

	@Override
	public boolean hasStableIds() {
		return true;
	}

	@Override
	public View getView(int arg0, View convertView, ViewGroup parent) {
		if (convertView == null) {
			convertView = ((Activity) context).getLayoutInflater().inflate(
					R.layout.todo_row, parent, false);
		}

		TodoItem item = items.get(arg0);

		((TextView) convertView.findViewById(R.id.textView)).setText(item
				.getWhatTodo());
		((CheckBox) convertView.findViewById(R.id.checkBox)).setChecked(item
				.getChecked());

		return convertView;
	}
}
