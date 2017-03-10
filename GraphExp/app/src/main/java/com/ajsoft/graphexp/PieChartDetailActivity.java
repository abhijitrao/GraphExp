package com.ajsoft.graphexp;

import com.ajsoft.graphexp.util.BaseActivity;
import com.ajsoft.graphexp.util.L;

import android.os.Bundle;
import android.widget.TextView;

public class PieChartDetailActivity extends BaseActivity{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_pie_chart_detail);
		setHeading("Chart Detail");
		String Detail=getIntent().getExtras().getString("getVal");
		String ItemDetail=getIntent().getExtras().getString("ItemName");
		initUi(Detail,ItemDetail);
	}

	private void initUi(String detail,String item) {
		
		TextView tv=(TextView)findViewById(R.id.textView1);
		
		tv.setText(item+"\n"+"Value: "+detail);
	}
}
