package nuu.im.liyao.helloworld;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity {

	private final static String MY_MESSAGE = "nuu.im.sendmessage";
	private Button send_broadcast;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        send_broadcast = (Button)findViewById(R.id.button1);
        send_broadcast.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {

				// µù¥Ubroadcast¨Æ¥ó
				registerReceiver(mBroadcast, new IntentFilter(MY_MESSAGE));
				Intent intent = new Intent();
				intent.setAction(MY_MESSAGE);
				sendBroadcast(intent);
			}
		});
        
    }
    
    private BroadcastReceiver mBroadcast = new BroadcastReceiver(){

		@Override
		public void onReceive(Context context, Intent intent) {
			if(MY_MESSAGE.equals(intent.getAction())){
				Log.v("tag", "got message.");
				
				new AlertDialog.Builder(MainActivity.this)
				.setMessage("Receive Message.")
				.setPositiveButton("OK", new DialogInterface.OnClickListener() {
					
					@Override
					public void onClick(DialogInterface dialog, int which) {
						unregisterReceiver(mBroadcast);
					}
				})
				.show();
			}
			else if("test".equals(intent.getAction())){
				Log.v("tag", "test");
			}
		}
    	
    };
    

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
}
