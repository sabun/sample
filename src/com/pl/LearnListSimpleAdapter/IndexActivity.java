package com.pl.LearnListSimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.ListActivity;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageView;
import android.widget.SimpleAdapter;
import android.widget.SimpleAdapter.ViewBinder;
import android.widget.TextView;
import android.widget.Toast;

import com.pl.LearnListSimpleAdapter.library.ImageLibraries;

public class IndexActivity extends ListActivity implements OnItemClickListener {
    String[] zodiacs = new String[] { "Aries", "Taurus", "Gemini",
    	"Cancer", "Leo", "Virgo", 
    	"Libra", "Scorpio", "Sagittarius", 
    	"Capricorn", "Aquarius", "Pisces" };

    List<Map<String, Object>> data = new ArrayList<Map<String, Object>>();

    Context _context;
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this._context = this;
        
        String[] from = new String[] { "image", "text" };
        int[] to = new int[] { R.id.listItemImage, R.id.listItemText };

        for (int i = 0; i < zodiacs.length; i++) {
        	Map<String, Object> temp = new HashMap<String, Object>();
        	temp.put("image", ImageLibraries.getImageFromResources(this._context, zodiacs[i]));
        	temp.put("text", zodiacs[i]);
        	
        	data.add(temp);
        }

        getListView().setOnItemClickListener(this);
        SimpleAdapter simpleAdapter = new SimpleAdapter(this, data, R.layout.list_item, from, to);
        simpleAdapter.setViewBinder(new simpleAdapteViewBinder());
        setListAdapter(simpleAdapter);
    }

    public class simpleAdapteViewBinder implements ViewBinder {
		@Override
		public boolean setViewValue(View view, Object data,
				String textRepresentation) {

			if ((view instanceof ImageView) && (data instanceof Bitmap)) {
				ImageView imageView = (ImageView)view;
				Bitmap bitmap = (Bitmap)data;
				imageView.setImageBitmap(bitmap);
				
				return true;
			}
			return false;
		}
    	
    }

	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
		TextView clickedItemList = (TextView)arg1.findViewById(R.id.listItemText);
		Toast.makeText(_context, "Click on " + clickedItemList.getText(), Toast.LENGTH_SHORT).show();
	}
}