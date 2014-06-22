package com.example.whattodo;

public class TodoItem
{
	private String whatTodo;
	private final long id;

	public TodoItem (String whatTodo, long id)
	{
		this.id = id;
		this.setWhatTodo(whatTodo);
	}

	public String getWhatTodo() {
		return whatTodo;
	}

	public void setWhatTodo(String whatTodo) {
		this.whatTodo = whatTodo;
	}
	
	public long getId()
	{
		return id;
	}
}