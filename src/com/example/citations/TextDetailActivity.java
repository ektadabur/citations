package com.example.citations;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class TextDetailActivity extends Activity {

	private String TAG=TextDetailActivity.class.getCanonicalName();
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		// to attach this java file to
		setContentView(R.layout.activity_detail_text);
		
		// to set background image of activity_detail_text.
		final RelativeLayout lay=(RelativeLayout) findViewById(R.id.rel1);
		
		// to get current intent.
		Intent intent=getIntent();
		// to get value of current category.
		String cat=intent.getStringExtra("cat");
		String cat1=getIntent().getExtras().getString("cat");
		//Toast.makeText(TextDetailActivity.this,cat1, Toast.LENGTH_LONG).show();
		//to set background image dynamically.
		if(cat1.equals("Art"))
		{
			Toast.makeText(this, "HAKUNA~MATATA", Toast.LENGTH_LONG).show();
			lay.setBackgroundResource(R.drawable.images);
			Toast.makeText(this, "DON`T WORRY, BE HAPPY", Toast.LENGTH_LONG).show();
		}
		if(cat1.equals("Attitude"))
		{
			Toast.makeText(this, "HAKUNA~MATATA", Toast.LENGTH_LONG).show();
			lay.setBackgroundResource(R.drawable.attitude);
			Toast.makeText(this, "DON`T WORRY, BE HAPPY", Toast.LENGTH_LONG).show();
		}
		if(cat1.equals("Birthday"))
		{
			Toast.makeText(this, "HAKUNA~MATATA", Toast.LENGTH_LONG).show();
			lay.setBackgroundResource(R.drawable.image7);
			Toast.makeText(this, "DON`T WORRY, BE HAPPY", Toast.LENGTH_LONG).show();
		}
		if(cat1.equals("Business"))
		{
			Toast.makeText(this, "HAKUNA~MATATA", Toast.LENGTH_LONG).show();
			lay.setBackgroundResource(R.drawable.image9);
			Toast.makeText(this, "DON`T WORRY, BE HAPPY", Toast.LENGTH_LONG).show();
		}
		if(cat1.equals("Friendship"))
		{
			Toast.makeText(this, "HAKUNA~MATATA", Toast.LENGTH_LONG).show();
			lay.setBackgroundResource(R.drawable.image5);
			Toast.makeText(this, "DON`T WORRY, BE HAPPY", Toast.LENGTH_LONG).show();
		}
		if(cat1.equals("Happiness"))
		{
			Toast.makeText(this, "HAKUNA~MATATA", Toast.LENGTH_LONG).show();
			lay.setBackgroundResource(R.drawable.image4);
			Toast.makeText(this, "DON`T WORRY, BE HAPPY", Toast.LENGTH_LONG).show();
		}
		if(cat1.equals("Hardwork"))
		{
			Toast.makeText(this, "HAKUNA~MATATA", Toast.LENGTH_LONG).show();
			lay.setBackgroundResource(R.drawable.image10);
			Toast.makeText(this, "DON`T WORRY, BE HAPPY", Toast.LENGTH_LONG).show();
		}
		if(cat1.equals("Nature"))
		{
			Toast.makeText(this, "HAKUNA~MATATA", Toast.LENGTH_LONG).show();
			lay.setBackgroundResource(R.drawable.nature8);
			Toast.makeText(this, "DON`T WORRY, BE HAPPY", Toast.LENGTH_LONG).show();
		}
		String [] ss={cat};
		// ??
		final Spinner spin=(Spinner)findViewById(R.id.spinner1);
		ArrayAdapter<String> aa = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, ss);
		//???
	    aa.setDropDownViewResource( android.R.layout.simple_spinner_dropdown_item );

	  // ArrayAdapter<String>aa=new ArrayAdapter<String>(null, 0,ss);
		//aa.add(cat);
		spin.setAdapter(aa);
		
	final TextView txt=(TextView)findViewById(R.id.txt_quote);
		
		//pass your quote id as per req
	Log.d(TAG, "before set text");
    final DatabaseHandler db =new DatabaseHandler(getApplicationContext());
	    //set text quotation to the Text View named as txt_Quote in activity and txt in java class. 
        // the whole string is a concatenation of functions defined in database handler class.
		txt.setText(db.getRandomTextQuote(db.getCategoryID(spin.getSelectedItem().toString())).getQuote());
		// to print the position of selected item.
		Log.d(TAG, "after set text" + spin.getSelectedItemPosition());
		Button bswitch=(Button)findViewById(R.id.button_switch);
		bswitch.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				txt.setText(db.getRandomTextQuote(db.getCategoryID(spin.getSelectedItem().toString())).getQuote());
				// this is for clicking switch button we can prompt with a new random Quotation from data base.
				
			}
		});
		
	}
	
	
	
   // this function is for fuctionality contained by our menu option.
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
	    // Handle item selection either send via sms and email.
	    switch (item.getItemId()) {
	    case R.id.sms:
	        sendSMS();
	        return true;
	    case R.id.email:
	    	sendEmail();
	    	return true;
	    default:
	        return super.onOptionsItemSelected(item);
	    }
	}

	private void sendSMS()
	{
		TextView tv=(TextView)findViewById(R.id.txt_quote);
		// to call the intent of sms activity from this current intent.
		Intent intent =new Intent(this,TextSMSActivity.class);
		// passing textual quotation with d intent.
		intent.putExtra("SMS",tv.getText().toString());
		//this log is printing selected textual quotation to the log cat.
		Log.d(TAG,tv.getText().toString());
		startActivity(intent);
		
	}

	private void sendEmail()
	{
		TextView tv=(TextView)findViewById(R.id.txt_quote);
		Intent intent =new Intent(this,TextEmailActivity.class);
		intent.putExtra("E-mail",tv.getText().toString());
		Log.d(TAG,tv.getText().toString());
		startActivity(intent);
		
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.text_detail_activity, menu);
		return true;
	}

}
