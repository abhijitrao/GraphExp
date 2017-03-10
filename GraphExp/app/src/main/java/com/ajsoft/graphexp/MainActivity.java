package com.ajsoft.graphexp;


import com.ajsoft.graphexp.util.Base;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;

public class MainActivity extends Base {
	String MenuList[] = { "PieChartExp","BarChartExp","CollasingLayoutEx"};
	ListView lv;
	ImageView img;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		lv = (ListView) findViewById(R.id.lv_main);
		img = (ImageView) findViewById(R.id.iv_ani);

		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.listlayout, MenuList);
		lv.setAdapter(adapter);

		
		lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {

			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				// String posValue=MenuList[position];
				String posValue = parent.getItemAtPosition(position).toString();
				try {
					Class ourClass = Class.forName("com.ajsoft.graphexp." + posValue);
					Intent i = new Intent(MainActivity.this, ourClass);
					i.putExtra("title", posValue);
					startActivity(i);
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
	}

}
