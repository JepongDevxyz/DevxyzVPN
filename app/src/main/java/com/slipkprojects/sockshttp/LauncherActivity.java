package com.slipkprojects.sockshttp;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import xyz.devxyz.vpn.R;
import com.slipkprojects.sockshttp.activities.BaseActivity;
import android.os.Handler;
import android.os.Looper;
import android.view.View;

/**
 * @author anuragdhunna
 */
 

import android.app.Application;
import android.os.CountDownTimer;
import android.widget.ProgressBar;
import android.widget.TextView;



public class LauncherActivity extends BaseActivity
{
	private static final long COUNTER_TIME = 1;
	private long secondsRemaining;
	private Handler handler = new Handler();
    int sleep = 0;
    private ProgressBar text;
	Handler handlerz = new Handler();    
    int status = 0;

	private TextView text2;
    private TextView text3;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_splash);
        //createTimer(COUNTER_TIME);
		text = ((ProgressBar)findViewById(R.id.progress_bar));
        text2 = ((TextView) findViewById(R.id.value123));
		text3 = ((TextView) findViewById(R.id.value1));
        
		new Thread(new Runnable() {
                @Override
                public void run() {
                    while (status < 100) {

                        status += 1;

                        try {
                            Thread.sleep(40);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }

                        handlerz.post(new Runnable() {
                                @Override
                                public void run() {

                                    text.setProgress(status);
                                    text2.setText(String.valueOf(status));
                                    if (status == 1) {
                                        //text3.setText("Loadding... 10%");
                                    }
                                    if (status == 5) {
                                        //text3.setText("Loadding.... 20%");
                                    }
                                    if (status == 10) {
                                        //text3.setText("Loadding..... 30%");
                                    }
                                    if (status == 15) {
                                        //text3.setText("Loadding...... 40%");
                                    }
                                    if (status == 20) {
                                        //text3.setText("Loadding....... 50%");
                                    }
                                    if (status == 25) {
                                        //text3.setText("Loadding........ 60%");
                                    }
                                    if (status == 50) {
                                        //text3.setText("Loadding......... 70%");
                                    }
                                    if (status == 55) {
                                        //text3.setText("Loadding.......... 80%");
                                    }
                                    if (status == 60) {
                                        //text3.setText("Loadding........... 90%");
                                    }
                                    if (status == 70) {
                                        //text3.setText("Loadding............. 100%");
                                    }
                                    if (status == 100) {
                                        createTimer(COUNTER_TIME);
                                        text.setVisibility(View.GONE);
                                        text2.setVisibility(View.GONE);
                                        text3.setVisibility(View.GONE);
                                    }
                                }
                            });
                    }
                }
            }).start();
		
    }
	
	private void createTimer(long seconds) {
		//final TextView counterTextView = findViewById(R.id.timer);

		CountDownTimer countDownTimer =
			new CountDownTimer(seconds * 1000, 500) {
			@Override
			public void onTick(long millisUntilFinished) {
				secondsRemaining = ((millisUntilFinished / 1000) + 1);
				//counterTextView.setText("App is done loading in: " + secondsRemaining);
			}

			@Override
			public void onFinish() {
				secondsRemaining = 0;
				//counterTextView.setText("Done.");

				Application application = getApplication();

				// If the application is not an instance of MyApplication, log an error message and
				// start the MainActivity without showing the app open ad.
				if (!(application instanceof MyApplication)) {
					//Toast.makeText(LauncherActivity.this, "hoy inatay", Toast.LENGTH_SHORT).show();
					new Handler(Looper.getMainLooper()).postDelayed(new Runnable(){

							@Override
							public void run() {
								Intent intent = new Intent(LauncherActivity.this, MainActivity.class);
								intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
								startActivity(intent);
								finish();
							}
						}, 1000);
					return;
				}

				// Show the app open ad.
				((MyApplication) application)
					.showAdIfAvailable(LauncherActivity.this,new MyApplication.OnShowAdCompleteListener() {
						@Override
						public void onShowAdComplete() {
							new Handler(Looper.getMainLooper()).postDelayed(new Runnable(){

									@Override
									public void run() {
										Intent intent = new Intent(LauncherActivity.this, MainActivity.class);
										intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
										startActivity(intent);
										finish();
									}
								}, 1000);
							//Toast.makeText(LauncherActivity.this, "hoy inatay2", Toast.LENGTH_SHORT).show();
						}
                    });
			}
        };
		countDownTimer.start();
	}
}
