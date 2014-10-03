package com.pl.LearnListSimpleAdapter.library;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

public class ImageLibraries {
	/**
	 * Get Resource in Drawable with filename without extension as parameter.
	 * @param resourceName
	 * @return
	 */
	public static Bitmap getImageFromResources(Context context, String fileName) {
		Bitmap result = null;
		
		try {
			String currentNameSpace = context.getPackageName();
			int resourceID = context.getResources().getIdentifier(currentNameSpace + ":drawable/" + fileName.toLowerCase(), null, null);
			result = BitmapFactory.decodeResource(context.getResources(), resourceID);
		} catch (Exception ex) {
			Log.e("getImageFromResources: ", ex.getMessage() + ", @" + ex.getClass().getName());
			return null;
		}
		
		return result;
	}
}