package com.example.citations;

import java.io.IOException;
import java.io.InputStream;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import android.app.Activity;
import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore.Images;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

public class ImageSlideActivity extends Activity {
	  private static int cindex=0;
	  Bitmap nextprv;
	  String file;
	  
	  private static int RESULT_LOAD_IMAGE = 2;
		private String TAG=ImageSlideActivity.class.getCanonicalName();
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_image_slide);
		final RelativeLayout lay=(RelativeLayout) findViewById(R.id.rel4);
		String cat2=getIntent().getExtras().getString("cat");
		Intent intent=getIntent();
		 file=intent.getStringExtra("file");
		 String cat=intent.getStringExtra("cat");
		
		 Log.d("ImageDetail on create", "file "+ file);
		 
		 InputStream is = null;
	    	try {
	    		AssetManager assetManager = getAssets();
	    		int count=0;
	    		
	    	  is = this.getResources().getAssets().open(file);
	    	  
	    	} catch (IOException e) {
	    	  Log.w("EL", e);
	    	}
	    	if(cat2.equals("Art"))
			{
				Toast.makeText(this, "HAKUNA~MATATA", Toast.LENGTH_LONG).show();
				lay.setBackgroundResource(R.drawable.images);
				Toast.makeText(this, "DON`T WORRY, BE HAPPY", Toast.LENGTH_LONG).show();
			}
			if(cat2.equals("Attitude"))
			{
				Toast.makeText(this, "HAKUNA~MATATA", Toast.LENGTH_LONG).show();
				lay.setBackgroundResource(R.drawable.attitude);
				Toast.makeText(this, "DON`T WORRY, BE HAPPY", Toast.LENGTH_LONG).show();
			}
			if(cat2.equals("Birthday"))
			{
				Toast.makeText(this, "HAKUNA~MATATA", Toast.LENGTH_LONG).show();
				lay.setBackgroundResource(R.drawable.image7);
				Toast.makeText(this, "DON`T WORRY, BE HAPPY", Toast.LENGTH_LONG).show();
			}
			if(cat2.equals("Business"))
			{
				Toast.makeText(this, "HAKUNA~MATATA", Toast.LENGTH_LONG).show();
				lay.setBackgroundResource(R.drawable.image9);
				Toast.makeText(this, "DON`T WORRY, BE HAPPY", Toast.LENGTH_LONG).show();
			}
			if(cat2.equals("Friendship"))
			{
				Toast.makeText(this, "HAKUNA~MATATA", Toast.LENGTH_LONG).show();
				lay.setBackgroundResource(R.drawable.image5);
				Toast.makeText(this, "DON`T WORRY, BE HAPPY", Toast.LENGTH_LONG).show();
			}
			if(cat2.equals("Happiness"))
			{
				
				Toast.makeText(this, "HAKUNA~MATATA", Toast.LENGTH_LONG).show();
				lay.setBackgroundResource(R.drawable.image4);
				Toast.makeText(this, "DON`T WORRY, BE HAPPY", Toast.LENGTH_LONG).show();
			}
			if(cat2.equals("Hardwork"))
			{
				Toast.makeText(this, "HAKUNA~MATATA", Toast.LENGTH_LONG).show();
				lay.setBackgroundResource(R.drawable.image10);
				Toast.makeText(this, "DON`T WORRY, BE HAPPY", Toast.LENGTH_LONG).show();
			}
			if(cat2.equals("Nature"))
			{
				Toast.makeText(this, "HAKUNA~MATATA", Toast.LENGTH_LONG).show();
				lay.setBackgroundResource(R.drawable.nature8);
				Toast.makeText(this, "DON`T WORRY, BE HAPPY", Toast.LENGTH_LONG).show();
			}
    	
    Log.d("From Image Detail view","Before Setting Image");

    Bitmap image = BitmapFactory.decodeStream(is);

	ImageView ib2 = (ImageView) findViewById( R.id.imageView1);
	ib2.setImageBitmap( image);
     
    	Button bprev=(Button)findViewById(R.id.bprev);
    	Button bnext=(Button)findViewById(R.id.bnext);
    	
    	bprev.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Toast.makeText(ImageSlideActivity.this, "HAKUNA~MATATA", Toast.LENGTH_LONG).show();
				Toast.makeText(ImageSlideActivity.this, "DON`T WORRY BE HAPPY", Toast.LENGTH_LONG).show();
				
				InputStream is = null;
				try {
		    		AssetManager am = getAssets();
		    		int count=0;
		    		
		    		final String str[]=am.list("");
		    		
		    		Pattern p = Pattern.compile("(\\d+)");
		    		Matcher m = p.matcher(file);
		    		String number ="";
		    		while(m.find())
		    		{
		    			number = m.group(1);
		    		}
		    		
		    		// is = ImageSlideActivity.this.getResources().getAssets().open(str[ImageSlideActivity.getIndex()]);
		    		String prvFile = file;
		    		Log.i("###################",prvFile);
		    		Log.i("###################", number);
		    		if ((Integer.parseInt(number))==1){
		    			 Log.i("In-LOOP", number);
		    			
		    			prvFile = prvFile.replace(number, Integer.toString(Integer.parseInt(number)+ 24));
		    	    }
		    		prvFile = prvFile.replace(number, Integer.toString(Integer.parseInt(number)-1));
		    		file = prvFile;
		    		Log.i("###################",prvFile);
		    		is = ImageSlideActivity.this.getResources().getAssets().open(prvFile);
							    		 ImageSlideActivity.setIndex(ImageSlideActivity.getIndex()-1);
				    	Bitmap image = BitmapFactory.decodeStream(is);
				    	nextprv = image;
				    	ImageView ib2 = (ImageView) findViewById( R.id.imageView1);
				    	ib2.setImageBitmap( image);
				    	
		    	} catch (IOException e) {
		    	  Log.w("EL", e);
		    	}
			
			     
				
			}
		});
    	
      bnext.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Toast.makeText(ImageSlideActivity.this, "HAKUNA~MATATA", Toast.LENGTH_LONG).show();
				Toast.makeText(ImageSlideActivity.this, "DON`T WORRY BE HAPPY", Toast.LENGTH_LONG).show();
				InputStream is = null;
				try {
		    		AssetManager am = getAssets();
		    		int count=0;
		    		
		    		final String str[]=am.list("");
		    		
		    		Pattern p = Pattern.compile("(\\d+)");
		    		Matcher m = p.matcher(file);
		    		String number ="";
		    		while(m.find())
		    		{
		    			number = m.group(1);
		    		}
		    		
		    		// is = ImageSlideActivity.this.getResources().getAssets().open(str[ImageSlideActivity.getIndex()]);
		    		String prvFile = file;
		    		Log.i("###################",prvFile);
		    		//int i = Integer.parseInt(number);
		    		
		    		Log.i("###################", number);
		    		if ((Integer.parseInt(number))==25){
		    			 Log.i("In-LOOP", number);
		    			
		    			prvFile = prvFile.replace(number, Integer.toString(Integer.parseInt(number)- 24));
		    	    }
		    		Log.i("After-LOOP", number);
		    		prvFile = prvFile.replace(number, Integer.toString(Integer.parseInt(number)+ 1));
		    		file = prvFile;
		    		String nowFile = file;
		    		
		    		Log.i("now-file",nowFile);
		    		is = ImageSlideActivity.this.getResources().getAssets().open(prvFile);
							    		 ImageSlideActivity.setIndex(ImageSlideActivity.getIndex()+ 1);
					//Log.i("8888888888888888888", String.valueOf(ImageSlideActivity.getIndex()));		    		 
				    	Bitmap image = BitmapFactory.decodeStream(is);
				    	nextprv = image;
				    	ImageView ib2 = (ImageView) findViewById( R.id.imageView1);
				    	ib2.setImageBitmap( image);
		    	  
		    	} catch (IOException e) {
		    	  Log.w("EL", e);
		    	}
			
			     
				
			}
		});
    	
    	     
	}
	
	private static int getIndex()
	{
		return cindex;
	}
	
	private static void setIndex(int ii)
	{
		cindex=ii;
	}
	
	public boolean onOptionsItemSelected(MenuItem item) {
	    // Handle item selection either send via sms and email.
	    switch (item.getItemId()) {
	        case R.id.email:
	    	sendEmail();
	    	return true;
	    default:
	        return super.onOptionsItemSelected(item);
	    }
	}
	
	private void sendEmail()
	{
		/*TextView tv=(TextView)findViewById(R.id.txt_quote);
		Intent intent =new Intent(this,TextEmailActivity.class);
		intent.putExtra("E-mail",tv.getText().toString());
		Log.d(TAG,tv.getText().toString());
		startActivity(intent);*/
		String path = Images.Media.insertImage(getContentResolver(), nextprv,"title", null);
	    Uri screenshotUri = Uri.parse(path);
	    final Intent emailIntent1 = new Intent( android.content.Intent.ACTION_SEND);
	    emailIntent1.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
	    emailIntent1.putExtra(Intent.EXTRA_STREAM, screenshotUri);
	    emailIntent1.setType("image/png");
	    startActivity(Intent.createChooser(emailIntent1, "Send email using"));
		
	}
	
	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.image_slide, menu);
		return true;
	}

}
