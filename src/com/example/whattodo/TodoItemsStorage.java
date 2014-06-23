package com.example.whattodo;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class TodoItemsStorage {
	private static final String TODO_ITEMS_STRING = "todoItems";
	private static final String CURRENT_ID_STRING = "currentId";
	private SharedPreferences settings;
	private List<TodoItem> items;
	private Gson gson = new Gson();
	private int currentId;

	public TodoItemsStorage(SharedPreferences settings) {
		this.settings = settings;
		load();
	}

	public List<TodoItem> getItems() {
		return items;
	}

	public void setItems(List<TodoItem> items) {
		this.items = items;
		save();
	}

	public void addItem(TodoItem item) {
		items.add(0, item);
		save();
	}

	public void moveItem(TodoItem item, int toPosition) {
		items.remove(item);
		items.add(toPosition, item);
		save();
	}

	public void clear() {
		items.clear();
		save();
	}

	public void removeItem(TodoItem item) {
		items.remove(item);
		save();
	}

	public void load() {
		String serializedItems = settings.getString(TODO_ITEMS_STRING, "[]");
		Type type = new TypeToken<ArrayList<TodoItem>>() {
		}.getType();
		items = gson.fromJson(serializedItems, type);
		currentId = settings.getInt(CURRENT_ID_STRING, 0);
	}

	public void save() {
		String serializedItems = gson.toJson(items);
		SharedPreferences.Editor editor = settings.edit();
		editor.putString(TODO_ITEMS_STRING, serializedItems);
		editor.putInt(CURRENT_ID_STRING, currentId);
		editor.commit();
	}

	public long getNextId() {
		currentId++;
		return ++currentId;
	}
}
