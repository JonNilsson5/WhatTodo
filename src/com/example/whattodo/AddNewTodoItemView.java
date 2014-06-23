package com.example.whattodo;

import android.app.Activity;
import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;

import com.example.whattodo.R;

public class AddNewTodoItemView extends RelativeLayout {

	private OnSaveListener onSaveListener;

	public AddNewTodoItemView(Context context) {
		this(context, null, 0);
	}

	public AddNewTodoItemView(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
	}

	public AddNewTodoItemView(Context context, AttributeSet attrs,
			int defStyleAttr) {
		super(context, attrs, defStyleAttr);

		((Activity) context).getLayoutInflater().inflate(
				R.layout.add_new_todo_item, this);

		findViewById(R.id.add).setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				addClicked();
			}
		});
	}

	private void addClicked() {
		if (onSaveListener != null)
			onSaveListener.onSave(((EditText) findViewById(R.id.description))
					.getText().toString());
	}

	public void setOnSaveListener(OnSaveListener listener) {
		onSaveListener = listener;
	}

	public static abstract class OnSaveListener {
		public abstract void onSave(String todoItemDescription);
	}
}
