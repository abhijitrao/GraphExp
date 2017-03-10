package com.ajsoft.graphexp.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.ProgressDialog;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Gravity;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

public class Base extends Activity {
	boolean conn = false;
	Builder alertDialog;
	ProgressDialog progressDialog;
	public AlertDialog dialog = null ;
	
	public float[] yData={5,10,15,45};
	
	public String[] mMonths = new String[] { "Jan", "Feb", "Mar","April"};

//	protected String[] mParties = new String[] { "Party A", "Party B", "Party C", "Party D", "Party E", "Party F",
//	"Party G", "Party H", "Party I", "Party J", "Party K", "Party L", "Party M", "Party N", "Party O",
//	"Party P", "Party Q", "Party R", "Party S", "Party T", "Party U", "Party V", "Party W", "Party X",
//	"Party Y", "Party Z" };

	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	
	
//	public void setFont(EditText et){
//		Typeface font = Typeface.createFromAsset(this.getAssets(),
//	            "fonts/CircularStd-Book.otf");
//		et.setTypeface(font);
//		et.setTextColor(getResources().getColor(R.color.text_color));
//	}
//	public void setFont(Button btn,String text){
//		Typeface font = Typeface.createFromAsset(this.getAssets(),
//	            "fonts/CircularStd-Book.otf");
//		btn.setTypeface(font);
//		btn.setText(toTitleCase(text));
//		btn.setTextColor(Color.parseColor("#FFFFFF"));
//	}
//	public void setFont(TextView tv){
//		Typeface font = Typeface.createFromAsset(this.getAssets(),
//	            "fonts/CircularStd-Book.otf");
//		tv.setTypeface(font);
//		tv.setTextColor(getResources().getColor(R.color.text_color));
//	}
	
	
	public void hideKeybord(Activity act){
		InputMethodManager inputManager = (InputMethodManager) getSystemService(act.INPUT_METHOD_SERVICE);
		inputManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
		
	}
	
//	public boolean isConnected() {
//
//		if (isOnline() == false) {
//			showAlertInternet(getSt(R.string.internet_error_tag), getSt(R.string.internet_error_msg), false);
//			return false;
//		} else {
//			return true;
//		}
//	}

//	public void showToast(String txt,Boolean status){
//		// Inflate the Layout
//		LayoutInflater lInflater = (LayoutInflater)getSystemService(
//	            Activity.LAYOUT_INFLATER_SERVICE);
//	    //LayoutInflater inflater = context.getLayoutInflater();
//		
//	    //View layout = lInflater.inflate(R.layout.custom_toast,(ViewGroup) findViewById(R.id.custom_toast_layout_id));
//		View layout=lInflater.inflate(R.layout.custom_toast, null);  
//		TextView a=(TextView)layout.findViewById(R.id.tv_toast_msg);
//		ImageView b=(ImageView)layout.findViewById(R.id.imageView1);
//		//layout.setBackgroundResource((status) ? R.drawable.toast_bg : R.drawable.toast_bg_red);
//		b.setImageResource((status) ? R.drawable.success : R.drawable.fail);
//	    a.setText(txt);
//	    //a.setTextColor((status) ? getResources().getColor(R.color.icon_green) : getResources().getColor(R.color.icon_red));
//		// Create Custom Toast
//	    Toast toast = new Toast(Base.this);
//	    toast.setGravity(Gravity.CENTER, 0, 0);
//	    toast.setDuration(Toast.LENGTH_LONG);
//	    toast.setView(layout);
//	    toast.show();
//  }
	
	
	
//	public void AlertInternet(Activity act) {
//
//		final AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(act);
//
//		// ...Irrelevant code for customizing the buttons and title
//		LayoutInflater inflater = act.getLayoutInflater();
//
//		View v = inflater.inflate(R.layout.custiom_ans_dialog, null);
//		dialogBuilder.setView(v);
//		dialogBuilder.setCancelable(false);
//		Button nextButton = (Button) v.findViewById(R.id.btn_ans_next);
//		nextButton.setText("OK");
//		TextView tvAnsTitle = (TextView) v.findViewById(R.id.tv_ans_title);
//		TextView tvAnsBody = (TextView) v.findViewById(R.id.tv_ans_body);
//
//		tvAnsTitle.setText("Alert!!!");
//		tvAnsBody.setText("Please Check Your Internet Connection.");
//
//		// if decline button is clicked, close the custom dialog
//		nextButton.setOnClickListener(new OnClickListener() {
//			@Override
//			public void onClick(View v) {
//				// Close dialog
//				dialog.dismiss();
//				
//			}
//		});
//
//		dialog = dialogBuilder.create();
//		dialog.show();
//
//	}
//	
	
	
	
	
	public static String toTitleCase(String givenString) {
		String[] arr = givenString.split(" ");
		StringBuffer sb = new StringBuffer();

		for (int i = 0; i < arr.length; i++) {
			sb.append(Character.toUpperCase(arr[i].charAt(0))).append(arr[i].substring(1)).append(" ");
		}
		return sb.toString().trim();
	}

	

	public String LoadPref(String key) {
		SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(Base.this);
		String data = sharedPreferences.getString(key, "");
		return data;
	}

	public void SavePref(String key, String value) {
		SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(Base.this);

		SharedPreferences.Editor editor = sharedPreferences.edit();
		editor.putString(key, value);
		editor.commit();

	}

	
	
	
	public String getSt(int id) {

		return getResources().getString(id);
	}

	

	public String viewTime() {
		Calendar c = Calendar.getInstance();
		SimpleDateFormat df = new SimpleDateFormat("h:mm a");
		df.setTimeZone(TimeZone.getTimeZone("Asia/Kolkata"));
		return df.format(c.getTime());

	}

	public String getDate() {
		Calendar c = Calendar.getInstance();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		String formattedDate = df.format(c.getTime());
		return formattedDate;
	}

	public String getTime12() {
		Calendar c = Calendar.getInstance();
		SimpleDateFormat df = new SimpleDateFormat("h:mm a");
		df.setTimeZone(TimeZone.getTimeZone("Asia/Kolkata"));
		return df.format(c.getTime());

	}

	public String getTime24() {
		Calendar c = Calendar.getInstance();
		SimpleDateFormat df1 = new SimpleDateFormat("H:mm:ss");
		df1.setTimeZone(TimeZone.getTimeZone("Asia/Kolkata"));
		return df1.format(c.getTime());
	}

	public String getTime() {
		Calendar c = Calendar.getInstance();
		SimpleDateFormat df = new SimpleDateFormat("H:mm:ss");
		df.setTimeZone(TimeZone.getTimeZone("Asia/Kolkata"));
		return df.format(c.getTime());
	}

	// give format like dd-MMM-yyyy,dd/MMM/yyyy
	public String viewDate(String format) {
		Calendar c = Calendar.getInstance();
		SimpleDateFormat df = new SimpleDateFormat(format);
		String formattedDate = df.format(c.getTime());
		return formattedDate;
	}

	public String vDate() {
		Calendar c = Calendar.getInstance();
		SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
		String formattedDate = df.format(c.getTime());
		return formattedDate;
	}

	// Enter Format eg.dd-MMM-yyyy,dd/MM/yyyy
	public String viewFormatDate(String format) {
		Calendar c = Calendar.getInstance();
		SimpleDateFormat df = new SimpleDateFormat(format);
		String formattedDate = df.format(c.getTime());
		return formattedDate;

	}

	// missing 0 issue resolve
	// enter Date eg. dd-MM-yyyy
	// enter dateFormat eg. dd-MM-yyyy
	public String currectFormateDate(String Date, String dateFormat) {
		SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
		Date cDate = null;
		try {
			cDate = sdf.parse(Date);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String formattedDate = sdf.format(cDate);
		return formattedDate;
	}

	public void showToast(String txt) {
		// Inflate the Layout
		Toast toast = Toast.makeText(Base.this, txt, Toast.LENGTH_SHORT);
		toast.setGravity(Gravity.CENTER | Gravity.CENTER, 0, 0);
		toast.show();
	}

}