package com.example.whattodo;

public class TodoItem {
	private String whatTodo;
	private boolean done;
	private final long id;

	public TodoItem(String whatTodo, long id) {
		this.id = id;
		this.setWhatTodo(whatTodo);
		this.done = false;
	}

	public String getWhatTodo() {
		return whatTodo;
	}

	public void setWhatTodo(String whatTodo) {
		this.whatTodo = whatTodo;
	}

	public long getId() {
		return id;
	}

	@Override
	public String toString() {
		return whatTodo;
	}

	public boolean getChecked() {
		return done;
	}

	public void setChecked(boolean done) {
		this.done = done;
	}
}