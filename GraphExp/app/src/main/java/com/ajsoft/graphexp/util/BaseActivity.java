package com.ajsoft.graphexp.util;

/**
 * Created by Abhijit on 3/3/2016.
 */

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

import com.ajsoft.graphexp.R;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.ActionBarActivity;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.text.style.RelativeSizeSpan;
import android.text.style.StyleSpan;
import android.text.style.SubscriptSpan;
import android.util.Log;
import android.view.Gravity;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

public class BaseActivity extends ActionBarActivity {
	boolean conn = false;
	Builder alertDialog;
	ProgressDialog progressDialog;
	AlertDialog dialog = null;

	public float[] yData = { 5, 10, 15, 45 };

	public String[] mMonths = new String[] { "Cheque", "Cash", "Bank Draft", "Due" };

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	protected void setHeading(String title) {
		SpannableString s = new SpannableString("  " + title);
		// s.setSpan(new TypefaceSpan(this, "CircularStd-Book.otf"), 0,
		// s.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
		s.setSpan(new ForegroundColorSpan(Color.parseColor("#8ceaff")), 0, s.length(),
				Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
		s.setSpan(new StyleSpan(Typeface.BOLD), 0, s.length(), 0);
		s.setSpan(new RelativeSizeSpan(1.1f), 0, s.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
		getSupportActionBar().setDisplayShowHomeEnabled(true);
		getSupportActionBar().setIcon(R.drawable.ic_launcher);
		getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#ffffff")));
		getSupportActionBar().setTitle(s);

	}

	public void hideKeybord(Activity act) {
		InputMethodManager inputManager = (InputMethodManager) getSystemService(act.INPUT_METHOD_SERVICE);
		inputManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);

	}

	// public void ExitAlertBox(Activity Act) {
	//
	// final AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(Act);
	//
	// // ...Irrelevant code for customizing the buttons and title
	// LayoutInflater inflater = Act.getLayoutInflater();
	//
	// View v=inflater.inflate(R.layout.custom_dialog, null);
	// dialogBuilder.setView(v);
	// Button quitButton = (Button) v.findViewById(R.id.button1);
	// Button ResultButton = (Button) v.findViewById(R.id.button2);
	// Button cancelButton = (Button) v.findViewById(R.id.button3);
	// // if decline button is clicked, close the custom dialog
	// quitButton.setOnClickListener(new OnClickListener() {
	// @Override
	// public void onClick(View v) {
	// // Close dialog
	// dialog.dismiss();
	// finish();
	// }
	// });
	// ResultButton.setOnClickListener(new OnClickListener() {
	// @Override
	// public void onClick(View v) {
	// // Close dialog
	// dialog.dismiss();
	// }
	// });
	// cancelButton.setOnClickListener(new OnClickListener() {
	// @Override
	// public void onClick(View v) {
	// // Close dialog
	// dialog.dismiss();
	// }
	// });
	// dialog = dialogBuilder.create();
	// dialog.show();
	//
	// }
	//

	public SpannableStringBuilder subScr(int text) {

		SpannableStringBuilder cs = new SpannableStringBuilder(String.valueOf(text));
		cs.setSpan(new SubscriptSpan(), 0, 1, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
		cs.setSpan(new RelativeSizeSpan(0.75f), 0, 1, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

		return cs;
	}

	// public boolean isConnected() {
	//
	// if (isOnline() == false) {
	// showAlertInternet(getSt(R.string.internet_error_tag),
	// getSt(R.string.internet_error_msg), false);
	// return false;
	// } else {
	// return true;
	// }
	// }

	// ProgressDialog progressDialog; I have declared earlier.
	public void showPDialog(String msg) {
		progressDialog = new ProgressDialog(BaseActivity.this);
		progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
		// progressDialog.setIndeterminateDrawable(getResources().getDrawable(R.drawable.dialog_animation));
		progressDialog.setMessage(msg);
		progressDialog.setIndeterminate(false);
		progressDialog.setCancelable(false);
		progressDialog.show();
	}

	public void dismissPDialog() {
		if (progressDialog != null)
			progressDialog.dismiss();
	}

	public static String toTitleCase(String givenString) {
		String[] arr = givenString.split(" ");
		StringBuffer sb = new StringBuffer();

		for (int i = 0; i < arr.length; i++) {
			sb.append(Character.toUpperCase(arr[i].charAt(0))).append(arr[i].substring(1)).append(" ");
		}
		return sb.toString().trim();
	}

	public boolean isConnectionAvailable() {

		if (isOnline() == false) {
			return false;
		} else {
			return true;
		}
	}

	public String LoadPref(String key) {
		SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(BaseActivity.this);
		String data = sharedPreferences.getString(key, "");
		return data;
	}

	public void SavePref(String key, String value) {
		SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(BaseActivity.this);

		SharedPreferences.Editor editor = sharedPreferences.edit();
		editor.putString(key, value);
		editor.commit();

	}

	public String convert(String res) {
		String UrlEncoding = null;
		try {
			UrlEncoding = URLEncoder.encode(res, "UTF-8");

		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		}
		return UrlEncoding;
	}

	public boolean isOnline() {
		boolean haveConnectedWifi = false;
		boolean haveConnectedMobile = false;

		ConnectivityManager cm = (ConnectivityManager) getSystemService(BaseActivity.this.CONNECTIVITY_SERVICE);
		NetworkInfo[] netInfo = cm.getAllNetworkInfo();
		for (NetworkInfo ni : netInfo) {
			if (ni.getTypeName().equalsIgnoreCase("WIFI"))
				if (ni.isConnected())
					haveConnectedWifi = true;
			if (ni.getTypeName().equalsIgnoreCase("MOBILE"))
				if (ni.isConnected())
					haveConnectedMobile = true;
		}
		if (haveConnectedWifi == true || haveConnectedMobile == true) {
			Log.e("Log-Wifi", String.valueOf(haveConnectedWifi));
			Log.e("Log-Mobile", String.valueOf(haveConnectedMobile));
			conn = true;
		} else {
			Log.e("Log-Wifi", String.valueOf(haveConnectedWifi));
			Log.e("Log-Mobile", String.valueOf(haveConnectedMobile));
			conn = false;
		}

		return conn;
	}

	public String getSt(int id) {

		return getResources().getString(id);
	}

	private void EnableMobileIntent() {
		Intent intent = new Intent();
		intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		intent.setAction(android.provider.Settings.ACTION_SETTINGS);
		startActivity(intent);

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
		Toast toast = Toast.makeText(BaseActivity.this, txt, Toast.LENGTH_SHORT);
		toast.setGravity(Gravity.CENTER | Gravity.CENTER, 0, 0);
		toast.show();
	}

	// // enter fix Date eg. dd-MM-yyyy
	// // enter dateFormat eg. dd-MM-yyyy,yyyy-MM-dd,
	// public String convertFormateDate(String Date, String dateFormat) {
	// // String s = "12:18:00";
	// String finalDate = Date;
	// String Day = Date.substring(0, 2);
	// String middle = Date.substring(2, 3);
	// String Month = Date.substring(3, 5);
	// String Year = Date.substring(6, 10);
	//
	// switch (dateFormat) {
	// case "dd-MM-yyyy":
	// finalDate = Day + middle + Month + middle + Year;
	// break;
	// case "yyyy-MM-dd":
	// finalDate = Year + middle + Month + middle + Day;
	// break;
	// case "MM-dd-yyyy":
	// finalDate = Month + middle + Day + middle + Year;
	// break;
	// default:
	// finalDate = "Date Format Incorrest";
	// }
	// return finalDate;
	// }

	// SpannableStringBuilder cs = new SpannableStringBuilder("X3 + X2");
	// cs.setSpan(new SubscriptSpan(), 1, 3,
	// Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
	// cs.setSpan(new RelativeSizeSpan(0.75f), 1, 2,
	// Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
	// cs.setSpan(new SubscriptSpan(), 6, 7,
	// Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
	// cs.setSpan(new RelativeSizeSpan(0.75f), 6, 7,
	// Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
}