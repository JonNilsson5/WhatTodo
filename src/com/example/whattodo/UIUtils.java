package com.example.whattodo;

import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;

public final class UIUtils {

	public static Dialog createModal (View view, Context context)
	{
		FrameLayout wrapper = new FrameLayout (context);
		int horizontalPadding = context.getResources().getDimensionPixelSize(R.dimen.modal_horizontal_margin);
		int verticalPadding = context.getResources().getDimensionPixelSize(R.dimen.modal_vertical_margin);
		wrapper.setPadding (horizontalPadding, verticalPadding, horizontalPadding, verticalPadding);
		wrapper.addView (view);

		Dialog dialog = new Dialog (context, R.style.myDialogTheme);
		dialog.setContentView (wrapper); // This must be done before window layout is set
		Window dialogWindow = dialog.getWindow();
		dialogWindow.clearFlags (WindowManager.LayoutParams.FLAG_DIM_BEHIND);
		dialogWindow.setBackgroundDrawableResource(R.layout.modal_background);
		dialogWindow.setLayout (ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
//		dialogWindow.SetWindowAnimations (Resource.Style.DialogFadeInOut);
		dialog.show ();

		return dialog;
	}
}
