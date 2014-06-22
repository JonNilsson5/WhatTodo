package com.example.whattodo;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class TodoAdapter extends BaseAdapter{

	private List<TodoItem> items = new ArrayList<TodoItem>();
	private Context context;
	
	public TodoAdapter (Context context, List<TodoItem> items)
	{
		this.items = items;
		this.context = context;
	}
	
	public void refreshItems(List<TodoItem> items)
	{
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
		// TODO Auto-generated method stub
		return items.get(arg0).getId();
	}
	
	@Override
	public boolean hasStableIds()
	{
		return true;
	}
	
	@Override
	public View getView(int arg0, View convertView, ViewGroup parent) {
		if(convertView == null)
			convertView = ((Activity)context).getLayoutInflater().inflate(R.layout.todo_row, parent, false);
	
		String text = items.get(arg0).getWhatTodo();
		
		((TextView)convertView.findViewById(R.id.tv)).setText(text);
		return convertView;
	}
	
	public void remove(TodoItem item) {
		// TODO Auto-generated method stub
		
	}
}


