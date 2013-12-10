package com.example.citations;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class AddTextQuoteActivity extends Activity {
	
	private String TAG=AddTextQuoteActivity.class.getCanonicalName();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add_text_quote);
		// to get ids of 2 fields of activity_add_text_quote.
		  Button b_save=(Button)findViewById(R.id.b_save);
		 final EditText ed=(EditText)findViewById(R.id.et_save);
		 // to set background image of activity_text_sms.
		 final LinearLayout lay3=(LinearLayout) findViewById(R.id.lin1); 
		 lay3.setBackgroundResource(R.drawable.download); 
		 // to create object of database.
		 final DatabaseHandler db=new DatabaseHandler(getApplicationContext());
		 // to get category list in list data item.
		  List<Categories> catsList=db.getAllCategories();
		  //to create array List of category.
		  ArrayList<String>catss=new ArrayList<String>();
			for(Categories cat:catsList)
			{
				Log.d("Categories",cat.getCategory());
				catss.add(cat.getCategory());
			}
			
		  
		  final Spinner spinn = (Spinner) findViewById(R.id.spin_text_save);
		    ArrayAdapter<String> adapter = new ArrayAdapter<String>(
		            this, android.R.layout.simple_spinner_item, catss);
		    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		    spinn.setAdapter(adapter); 
		    Toast.makeText(AddTextQuoteActivity.this, "Click on Art, For changing category of saving Quotation. ",
		    		Toast.LENGTH_LONG).show(); 
		 
		  b_save.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
			
				  // if(ed.getText().toString()!="")
				if(!ed.getText().toString().equals(""))
	                {
				   String cattid=spinn.getSelectedItem().toString();
				    Log.d("Spinner Selected cat", cattid);
				  final  int cid=db.getCategoryID(cattid);
				  Log.d("Spinner Selected cat id", " "+ cid);
				
				Quotes quote=new Quotes();
					
				
				quote.setCAT_ID(cid);
				quote.setQuote(ed.getText().toString());
				quote.setTYPE(0);
				
				db.addQuote(quote);
				
				
				
				Toast.makeText(AddTextQuoteActivity.this, "Quote Added Success fully", Toast.LENGTH_LONG).show();
				Log.d("Quote Added" , "Quote Added Success fully" + ed.getText().toString() +"category ID" + cid);
				Log.d("Quotes in this category " , " tottal quotes " + db.getQuotesCount(cid, 0));
				ed.setText("");
	                }
	                else
	                {
	                   
	                    Toast.makeText(AddTextQuoteActivity.this, "You can not save  a blank Quote.", Toast.LENGTH_LONG).show();
	                                                       
	                }
				
			}
		});
		  
	}
	
	
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
		EditText tv=(EditText)findViewById(R.id.et_save);
		Intent intent =new Intent(this,TextSMSActivity.class);
		intent.putExtra("SMS",tv.getText().toString());
		Log.d(TAG,tv.getText().toString());
		startActivity(intent);
		
	}

	private void sendEmail()
	{
		EditText tv=(EditText)findViewById(R.id.et_save);
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
