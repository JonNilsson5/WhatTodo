package com.example.whattodo;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import android.content.SharedPreferences;

public class TodoItemsStorage {
	private static final String TODO_ITEMS_STRING = "todoItems";
	private static final String CURRENT_ID_STRING = "currentId";
	private SharedPreferences settings;
	private List<TodoItem> items;
	private Gson gson = new Gson();
	private int currentId;
	
	public TodoItemsStorage(SharedPreferences settings)
	{
		this.settings = settings;
		Load();
	}
	
	public List<TodoItem> GetItems()
	{
		return items;
	}
	
	public void SetItems(List<TodoItem> items)
	{
		this.items = items;
		Save();
	}
	
	public void AddItem(TodoItem item)
	{
		items.add(0, item);
		Save();
	}
	
	public void MoveItem(TodoItem item, int toPosition)
	{
		items.remove(item);
		items.add(toPosition, item);
		Save();
	}
	
	public void Clear()
	{
		items.clear();
		Save();
	}
	
	public void RemoveItem(TodoItem item)
	{
		items.remove(item);
		Save();
	}
	
	public void Load ()
	{
		String serializedItems = settings.getString(TODO_ITEMS_STRING, "[]");
		Type type = new TypeToken<ArrayList<TodoItem>>() {}.getType();
		items = gson.fromJson(serializedItems, type);
		currentId = settings.getInt(CURRENT_ID_STRING, 0);	
	}
	
	public void Save ()
	{
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
