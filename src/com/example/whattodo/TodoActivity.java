package com.example.whattodo;

import com.example.whattodo.views.AddNewTodoItemView;

import android.app.Activity;
import android.app.Dialog;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;

import com.mobeta.android.dslv.*;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 * 
 * @see SystemUiHider
 */
public class TodoActivity extends Activity {
	public static final String PREFS_NAME = "MyPrefsFile";

	private DragSortListView listView;
	private TodoAdapter adapter;
	private TodoItemsStorage todoItemStorage;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_todo);
		initializeViews();
		loadStoredData();
		initializeAdapter();
	}

	private DragSortListView.DropListener onDrop = new DragSortListView.DropListener()
	{
	    @Override
	    public void drop(int from, int to)
	    {
	        if (from != to)
	        {
	            TodoItem item = (TodoItem) adapter.getItem(from);
	            todoItemStorage.MoveItem(item, to);
	            adapter.RefreshItems(todoItemStorage.GetItems());
	        }
	    }
	};
	
	
	private void loadStoredData() {
		SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
		todoItemStorage = new TodoItemsStorage(settings);
	}

	//Get all the view fields
	private void initializeViews()
	{
		listView = (DragSortListView) findViewById(R.id.listView);
	}
	
	private void initializeAdapter() {
		adapter = new TodoAdapter(this, todoItemStorage.GetItems());
		DragSortController controller = new DragSortController(listView);
		controller.setRemoveEnabled(false);
	    controller.setSortEnabled(true);
	    controller.setDragInitMode(1);
	            //controller.setRemoveMode(removeMode);

	    listView.setFloatViewManager(controller);
	    listView.setOnTouchListener(controller);
	    listView.setDragEnabled(true);
		listView.setDropListener(onDrop);
		listView.setAdapter(adapter);
	}
	
	private void MoveItem(TodoItem item, int toPosition)
	{
		todoItemStorage.MoveItem(item, toPosition);
		adapter.RefreshItems(todoItemStorage.GetItems());
	}
	
	private void AddItem(TodoItem item)
	{
		todoItemStorage.AddItem(item);
		adapter.RefreshItems(todoItemStorage.GetItems());
	}
	
	private void RemoveItem(TodoItem item)
	{
		todoItemStorage.RemoveItem(item);
		adapter.RefreshItems(todoItemStorage.GetItems());
	}

	Handler mHideHandler = new Handler();
	Runnable mHideRunnable = new Runnable() {
		@Override
		public void run() {
		}
	};

	private Dialog dialog;
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{
		getMenuInflater().inflate (R.menu.todo_menu, menu);
		return true;
	}
	
	@Override
	public boolean onOptionsItemSelected (MenuItem item)
	{
		switch (item.getItemId())
		{
			case R.id.menuAdd:
				{
					addNewItem();
					return true;
				}
			case R.id.menuEdit:
				{
					edit();
					return true;
				}
		}
		return super.onOptionsItemSelected (item);
	}

	private void edit() {
		// TODO Auto-generated method stub
		
	}

	private void addNewItem() {
		AddNewTodoItemView addNewTodoItemView = new AddNewTodoItemView (this);
		dialog = UIUtils.CreateModal(addNewTodoItemView, this);
		addNewTodoItemView.setOnSaveListener(new AddNewTodoItemView.OnSaveListener()
		{
			@Override
			public void onSave(String string)
			{
				AddItem(new TodoItem(string, todoItemStorage.getNextId()));
				if(dialog != null)
				{
					dialog.dismiss();
					dialog = null;
				}
			}
		}
		);
		
	}
}
