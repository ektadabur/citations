package com.example.citations;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

public class TextEmailActivity extends Activity{
	String TAG="TextEmailActivity";
	public static final int PICK_CONTACT = 1;//?
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_text_email);
		// to set background image of activity_text_email.
		final LinearLayout lay5=(LinearLayout) findViewById(R.id.linearLayout1); 
		 lay5.setBackgroundResource(R.drawable.download); 
		//to get current intent..
		Intent intent=getIntent();
		// to get Quotation as email-text..
		String emailtext=intent.getStringExtra("E-mail");
		Log.d(TAG,emailtext);
		
		// get ids of all fields of activity_text_email.
		Button buttonSend = (Button) findViewById(R.id.buttonSend);
		final EditText textTo = (EditText) findViewById(R.id.editTextTo);
		final EditText textSubject = (EditText) findViewById(R.id.editTextSubject);
		final EditText textMessage = (EditText) findViewById(R.id.editTextMessage);
		// set Quotation which is in emailtext as string to the textMessage.
		textMessage.setText(emailtext);
 
		buttonSend.setOnClickListener(new View.OnClickListener() {
 
			@Override
			public void onClick(View v) {
			
 
			  String to = textTo.getText().toString();
			  String subject = textSubject.getText().toString();
			  String message = textMessage.getText().toString();
          //creating  an intent for email-activity.
			  Intent email = new Intent(Intent.ACTION_SEND);
			  email.putExtra(Intent.EXTRA_EMAIL, new String[]{ to});
			  //email.putExtra(Intent.EXTRA_CC, new String[]{ to});
			  //email.putExtra(Intent.EXTRA_BCC, new String[]{to});
			  email.putExtra(Intent.EXTRA_SUBJECT, subject);
			  email.putExtra(Intent.EXTRA_TEXT, message);
               //need this to prompts email client only
			  email.setType("message/rfc822");
                //chossing an email-client like gmail,yahoo....
			  startActivity(Intent.createChooser(email, "Choose an Email client :"));
 
			}
		});
	
		     
	
	/*Button b_send=(Button)findViewById(R.id.buttonSend);
	b_send.setOnClickListener(new View.OnClickListener() {
		
		@Override
		public void onClick(View arg0) {
			// On clicking send button Email Manager should listen our request for 
			//using their services to send a email. 
			 
			SmsManager smsManager = SmsManager.getDefault();
			smsManager.sendTextMessage(textTo.getText().toString(), null,
					textTo.getText().toString(), null, null);
		}
	});*/
	     
	
	

	}
	
	 @Override
	  public void onActivityResult(int reqCode, int resCode, Intent data) {
	    super.onActivityResult(reqCode, resCode, data);

	    switch(reqCode) {
	      case (PICK_CONTACT) : {
	        if (resCode == Activity.RESULT_OK) {
	          Uri contactData = data.getData();
	          Cursor c = getContentResolver().query(contactData, null, null, null, null);
	          c.moveToFirst();
	          String name = c.getString(c.getColumnIndexOrThrow(
	                          ContactsContract.Contacts.DISPLAY_NAME_PRIMARY));
	          c.close();
	          EditText tv = (EditText)findViewById(R.id.editTextMessage);
	          tv.setText(name);
	        }
	        break;
	      }
	      default: break;
	    }
	  }
	
	 
			

}
