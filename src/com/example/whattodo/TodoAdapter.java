package com.example.whattodo;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.ClipData;
import android.content.ClipDescription;
import android.content.Context;
import android.content.ClipData.Item;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.View.DragShadowBuilder;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class TodoAdapter extends BaseAdapter{

	private List<TodoItem> items = new ArrayList<TodoItem>();
	private Context context;
	
	public TodoAdapter (Context context, List<TodoItem> items)
	{
		this.items = items;
		this.context = context;
	}
	
	public void RefreshItems(List<TodoItem> items)
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
		convertView.setOnLongClickListener(new View.OnLongClickListener() {
			@Override
			public boolean onLongClick(View v) {
//				ImageView iv= new ImageView(context);
//				
//				
//				CharSequence text = ((TextView)v.findViewById(R.id.tv)).getText();
//				ClipData.Item item = new ClipData.Item(text);
//				String[] mimetypes = new String[1];
//				mimetypes[0] = ClipDescription.MIMETYPE_TEXT_PLAIN;
//				ClipData dragData = new ClipData(text, mimetypes, item);
//				
//				View.DragShadowBuilder myShadow = new DragShadowBuilder(convertView);
				return false;
			}
		});
		return convertView;
	}
	
	private static class MyDragShadowBuilder extends View.DragShadowBuilder {
		
		
		private static Drawable shadow;
		
		public MyDragShadowBuilder(View v)
		{
			super(v);
			
			shadow = new ColorDrawable(Color.GRAY);
			
		}
		@Override
		public void onProvideShadowMetrics (Point size, Point touch)
		{
			int width, height;
			
			width = getView().getWidth() / 2;
			height = getView().getHeight() / 2;
			shadow.setBounds(0,0,width, height);
			size.set(width, height);
			touch.set(width, height);
		
		}
		
		@Override
		public void onDrawShadow (Canvas canvas)
		{
			shadow.draw(canvas);
		}

	}

	public void remove(TodoItem item) {
		// TODO Auto-generated method stub
		
	}
}


