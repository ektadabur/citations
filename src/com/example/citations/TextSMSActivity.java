package com.example.citations;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Contacts.People;
import android.provider.ContactsContract;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

public class TextSMSActivity extends Activity {

	String TAG="TextSMSActivity";
	public static final int PICK_CONTACT = 1;
	private  EditText etnum;
	
	  
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_text_sms);
		// to set background image of activity_text_sms.
		 final LinearLayout lay4=(LinearLayout) findViewById(R.id.line2); 
		 lay4.setBackgroundResource(R.drawable.download); 
		 //to get current intent..
		Intent intent=getIntent();
		// to get SMS intent transferred here from calling activity..
		String smstext=intent.getStringExtra("SMS");
		Log.d(TAG,smstext);
		// to get the id of edit text in etsms..
		final EditText etsms=(EditText)findViewById(R.id.et_text);
		// to get the id of edit text in etnum for phone-number.
		etnum = (EditText)findViewById(R.id.et_number);
		//to set Quotation as sms in etsms field.
		etsms.setText(smstext);
		// to get id of pick button. and set event on clicking of button.
		Button b_pick=(Button)findViewById(R.id.b_pick_contact);
		b_pick.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				 Intent intent = new Intent(Intent.ACTION_PICK, ContactsContract.Contacts.CONTENT_URI);
			        startActivityForResult(intent, PICK_CONTACT);
			        // code to get contact of contact-List of Phone.
			}
		});
		     

	// to get id of send button. and set event on clicking of button.
	Button b_send=(Button)findViewById(R.id.b_sms_send);
	b_send.setOnClickListener(new View.OnClickListener() {
		
		@Override
		public void onClick(View arg0) {
			// On clicking send button SMS Manager should listen our request for 
						//using their services to send a email
			 
			
			SmsManager smsManager = SmsManager.getDefault();
			
			smsManager.sendTextMessage(etnum.getText().toString(), null, etsms.getText().toString(), null, null);
			//smsManager.sendTextMessage("5556", null, etsms.getText().toString(), null, null);
			
		}
	});
	     
	
	

	}
	// startActivityForResult(intent, PICK_CONTACT);
	 @Override
	  public void onActivityResult(int reqCode, int resCode, Intent data) {
	    super.onActivityResult(reqCode, resCode, data);

	     if (resCode == RESULT_OK) {  
	        switch (reqCode) {  
	        case PICK_CONTACT:
	       
	        	 Uri returnUri = data.getData();
	        	 Cursor cursor = getContentResolver().query(returnUri, null, null, null, null);
	        	    
	        	    if(cursor.moveToNext()){
	        	     int columnIndex_ID = cursor.getColumnIndex(ContactsContract.Contacts._ID);
	        	     String contactID = cursor.getString(columnIndex_ID);
	        	     
	        	     int columnIndex_HASPHONENUMBER = cursor.getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER);
	        	     String stringHasPhoneNumber = cursor.getString(columnIndex_HASPHONENUMBER);
	        	     
	        	     if(stringHasPhoneNumber.equalsIgnoreCase("1")){
	        	      Cursor cursorNum = getContentResolver().query(
	        	        ContactsContract.CommonDataKinds.Phone.CONTENT_URI, 
	        	        null, 
	        	        ContactsContract.CommonDataKinds.Phone.CONTACT_ID + "=" + contactID, 
	        	        null, 
	        	        null);
	        	      
	        	      //Get the first phone number
	        	      if(cursorNum.moveToNext()){
	        	       int columnIndex_number = cursorNum.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER);
	        	       String stringNumber = cursorNum.getString(columnIndex_number);
	        	       etnum.setText(stringNumber);
	        	      }
	        	      
	        	     }else{
	        	      etnum.setText("NO Phone Number");
	        	     }
	        	     
	        	     
	        	    }else{
	        	     Toast.makeText(getApplicationContext(), "NO data!", Toast.LENGTH_LONG).show();
	        	    }
	        	   
	        	  
	        
	        }
	    }
	
	 }
	    
}
