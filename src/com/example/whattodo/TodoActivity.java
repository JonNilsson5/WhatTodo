package com.example.whattodo;

import android.app.Activity;
import android.app.Dialog;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;

import com.mobeta.android.dslv.DragSortListView;

public class TodoActivity extends Activity {
	public static final String PREFS_NAME = "MyPrefsFile";

	private DragSortListView listView;
	private TodoAdapter adapter;
	private TodoItemsStorage todoItemStorage;
	private Dialog dialog;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_todo);
		setupViews();
		loadStoredData();
		setupListView();
	}

	private DragSortListView.DropListener onDrop = new DragSortListView.DropListener() {
		@Override
		public void drop(int from, int to) {
			if (from != to) {
				TodoItem item = (TodoItem) adapter.getItem(from);
				todoItemStorage.moveItem(item, to);
				adapter.refreshItems(todoItemStorage.getItems());
			}
		}
	};

	private DragSortListView.RemoveListener onRemove = new DragSortListView.RemoveListener() {
		@Override
		public void remove(int which) {
			removeItem((TodoItem) adapter.getItem(which));
		}
	};

	private void loadStoredData() {
		SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
		todoItemStorage = new TodoItemsStorage(settings);
	}

	private void setupViews() {
		listView = (DragSortListView) findViewById(R.id.listView);
	}

	private void setupListView() {
		adapter = new TodoAdapter(this, todoItemStorage.getItems());

		listView.setDropListener(onDrop);
		listView.setRemoveListener(onRemove);
		listView.setAdapter(adapter);

		listView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				TodoItem item = (TodoItem) listView.getItemAtPosition(arg2);
				item.setChecked(!item.getChecked());
				todoItemStorage.save();
				adapter.refreshItems(todoItemStorage.getItems());
			}
		});
	}

	private void addItem(TodoItem item) {
		todoItemStorage.addItem(item);
	}

	private void removeItem(TodoItem item) {
		todoItemStorage.removeItem(item);
		adapter.refreshItems(todoItemStorage.getItems());
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.todo_menu, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.menuAdd: {
			addNewItem();
			return true;
		}
		}
		return super.onOptionsItemSelected(item);
	}

	private void addNewItem() {
		AddNewTodoItemView addNewTodoItemView = new AddNewTodoItemView(this);
		dialog = UIUtils.createModal(addNewTodoItemView, this);
		addNewTodoItemView
				.setOnSaveListener(new AddNewTodoItemView.OnSaveListener() {
					@Override
					public void onSave(String string) {
						addItem(new TodoItem(string, todoItemStorage
								.getNextId()));
						if (dialog != null) {
							dialog.dismiss();
							dialog = null;
						}
					}
				});
	}
}
