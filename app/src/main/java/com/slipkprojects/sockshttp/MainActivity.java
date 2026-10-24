package com.slipkprojects.sockshttp;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
//import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Spinner;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import com.slipkprojects.sockshttp.MainActivity;
import com.slipkprojects.sockshttp.activities.BaseActivity;
import com.slipkprojects.sockshttp.util.AESCrypt;
import com.slipkprojects.sockshttp.util.ConfigUtil;
import com.slipkprojects.sockshttp.util.Utils;
import com.slipkprojects.ultrasshservice.LaunchVpn;
import com.slipkprojects.ultrasshservice.config.Settings;
import com.slipkprojects.ultrasshservice.logger.ConnectionStatus;
import com.slipkprojects.ultrasshservice.logger.SkStatus;
import com.slipkprojects.ultrasshservice.tunnel.TunnelManagerHelper;
import com.slipkprojects.ultrasshservice.tunnel.TunnelUtils;
import com.slipkprojects.ultrasshservice.util.SkProtect;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import org.json.JSONException;
import org.json.JSONObject;
import com.slipkprojects.ultrasshservice.util.securepreferences.SecurePreferences;
import com.slipkprojects.ultrasshservice.util.securepreferences.SecurePreferences.Editor;
import xyz.devxyz.vpn.R;
import android.os.Looper;
import com.slipkprojects.sockshttp.activities.ConfigGeralActivity;
import com.slipkprojects.sockshttp.util.JsonRequestConfig;
import java.util.HashMap;
import android.widget.TextView;
import android.graphics.Typeface;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import com.google.android.material.textview.MaterialTextView;
import androidx.core.widget.ImageViewCompat;
import java.io.InputStream;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;
import com.slipkprojects.ultrasshservice.StatisticGraphData;
import com.slipkprojects.ultrasshservice.StatisticGraphData.DataTransferStats;
import java.security.GeneralSecurityException;
import android.widget.AdapterView;
import androidx.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.EditText;
import android.view.animation.*;
import com.slipkprojects.ultrasshservice.SocksHttpService;
import android.content.pm.PackageInfo;
import android.widget.Chronometer;
import android.os.SystemClock;
import android.widget.Chronometer.OnChronometerTickListener;
import java.util.TimerTask;
import java.util.Timer;
import android.os.Build.VERSION;
import com.slipkprojects.sockshttp.adapter.LogsAdapter;
import androidx.viewpager.widget.ViewPager;
import com.google.android.material.tabs.TabLayout;
import android.net.Uri;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import com.google.android.material.navigation.NavigationView;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.play.core.appupdate.AppUpdateManager;
import com.google.android.play.core.appupdate.AppUpdateManagerFactory;
import com.google.android.play.core.tasks.OnSuccessListener;
import com.google.android.play.core.appupdate.AppUpdateInfo;
import com.google.android.play.core.install.model.UpdateAvailability;
import com.google.android.play.core.install.model.AppUpdateType;
import android.content.IntentSender.SendIntentException;
import android.content.IntentSender;
import com.google.android.play.core.install.InstallStateUpdatedListener;
import com.google.android.play.core.install.InstallState;
import com.google.android.play.core.install.model.InstallStatus;
import com.google.android.material.snackbar.Snackbar;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import android.widget.Toast;
import android.graphics.Color;
import androidx.viewpager.widget.PagerAdapter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import android.os.PersistableBundle;
import java.util.List;
import android.content.res.Configuration;
import android.os.CountDownTimer;
import com.google.android.gms.ads.AdView;
import android.widget.RelativeLayout;
import android.app.ProgressDialog;
import com.google.android.gms.ads.interstitial.InterstitialAd;
import java.util.concurrent.TimeUnit;
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback;
import com.google.android.gms.ads.FullScreenContentCallback;
import com.google.android.gms.ads.AdError;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.rewarded.RewardedAdLoadCallback;
import com.google.android.gms.ads.OnUserEarnedRewardListener;
import com.google.android.gms.ads.rewarded.RewardItem;
import com.google.android.gms.ads.interstitial.InterstitialAd;
import com.google.android.gms.ads.rewarded.RewardedAd;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import java.util.Locale;
import android.util.Log;
import android.text.Html;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.GradientDrawable.Orientation;
import com.google.android.gms.ads.AdSize;
import com.slipkprojects.sockshttp.util.jaboler;
import android.graphics.drawable.ColorDrawable;
import android.view.Gravity;
import android.view.View.OnClickListener;
import android.app.NotificationManager;
import android.app.NotificationChannel;
import android.graphics.BitmapFactory;
import android.app.Notification;
import android.os.Build;
import com.slipkprojects.sockshttp.adapter.MyAdapter;
import com.slipkprojects.sockshttp.adapter.RecyclerItemTouch;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import android.widget.RelativeLayout;
import android.os.Vibrator;
import com.google.android.gms.ads.rewardedinterstitial.RewardedInterstitialAd;
import com.google.android.gms.ads.rewardedinterstitial.RewardedInterstitialAdLoadCallback;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.slipkprojects.ultrasshservice.tunnel.UDPThread;
import android.widget.ProgressBar;
import com.slipkprojects.ultrasshservice.config.SettingsConstants;
import android.net.VpnService;
import android.content.ActivityNotFoundException;
import com.google.android.material.snackbar.Snackbar;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts.RequestPermission;
import androidx.core.content.ContextCompat;
import android.Manifest;
import dev.shreyaspatil.MaterialDialog.BottomSheetMaterialDialog;
import dev.shreyaspatil.MaterialDialog.MaterialDialog;
import dev.shreyaspatil.MaterialDialog.interfaces.OnCancelListener;
import dev.shreyaspatil.MaterialDialog.interfaces.OnDismissListener;
import dev.shreyaspatil.MaterialDialog.interfaces.OnShowListener;
import dev.shreyaspatil.MaterialDialog.model.TextAlignment;
import dev.shreyaspatil.MaterialDialog.interfaces.DialogInterface;
import androidx.core.content.ContextCompat;
import android.net.TrafficStats;
import antonkozyriatskyi.circularprogressindicator.CircularProgressIndicator;
import androidx.core.content.ContextCompat;



public class MainActivity extends BaseActivity implements DrawerLayout.DrawerListener,View.OnClickListener, SkStatus.StateListener {
	
	private static final String TAG = MainActivity.class.getSimpleName();
	private static final String UPDATE_VIEWS = "MainUpdate";
	public static final String OPEN_LOGS = "com.slipkprojects.sockshttp:openLogs";
	private Thread udpThread;
    
    private MaterialDialog mSimpleDialog;
    private MaterialDialog mAnimatedDialog;
    private BottomSheetMaterialDialog mSimpleBottomSheetDialog;
    private BottomSheetMaterialDialog mAnimatedBottomSheetDialog;
	
	private RelativeLayout card1;
	private RelativeLayout card2;
	private TextView selectedServer;
	private ImageView selectedSImage;
	private TextView selectedPayload;
	private ImageView selectedPImage;
	private ArrayList<HashMap<String, String>> serverList = new ArrayList<HashMap<String, String>>();
    private ArrayList<HashMap<String, String>> payloadList = new ArrayList<HashMap<String, String>>(); 
	
	private DrawerLog mDrawer;
	private Settings mConfig;
	private Toolbar toolbar_main;
	private Handler mHandler;
	private LinearLayout mainLayout;
	private Button starterButton;
	private ConfigUtil config;
	private SharedPreferences prefs;
    private SharedPreferences sp2;
	private SecurePreferences sp;
	private SecurePreferences.Editor edit;
	private TextView config_ver_txt;
	private TextView app_info_text;
	private TextView stats_cont;
	private Chronometer duration;
	private long pauseOffset;
	private boolean running;
	public static TextView bytesIn;
	public static TextView bytesOut;
	
	private DrawerPanelMain mDrawerPanel;
	private AppUpdateManager mAppUpdateManager;
	private static final int RC_APP_UPDATE = 100;
	private boolean isLoading;
	public static int PICK_FILE = 1;
	private AdView adView;
	private RelativeLayout adsBanners;
	
	private static final String AD_UNIT_ID_REWARDED = "ca-app-pub-3940256099942544/5224354917";
    private static final String AD_UNIT_ID_INTERS = "ca-app-pub-3940256099942544/1033173712";
	public InterstitialAd mInterstitialAd;
	private AdView adsBannerView;
	String PASSWORD = new String(new byte[]{116, 114, 105, 45, 110, 101, 116, 115, 111, 99, 107, 101, 114, 115});
	private TextView serv_info;
	private TextView prom_info;
	
	private TextView cat;
	private LinearLayout cat_bg;
	
	private boolean mTimerEnabled;
	private CountDownTimer mCountDownTimer;
    private boolean mTimerRunning;
    private long mTimeLeftInMillis;
    private long mEndTime;
    private TextView mTextViewCountDown;
	private LinearLayout mButtonSet;
    private TextView mButtonSet_txt;
	private ProgressDialog progressDialog;
	private CountDownTimer mBtnCountDown;
    private CountDownTimer mBtnCountDown2;
	private long mTimeLeftBtn;
    private long mTimeLeftBtn2;
	private long mStartTimeInMillis;
	private RewardedAd rewardedAd;
	public static boolean mConnected;
	public static boolean mShown;
    public static boolean isCheckedd;
    private static final int START_VPN_PROFILE = 2002;
	
	private LinearLayout layout_timer;
	int counter = 0;
    
    private boolean adIsLoading;
	
	private ProgressBar progz;
    //private String resIdz;
    private DataTransferStats dataTransfer;
    private CircularProgressIndicator circularProgressBar;
    
    
	@Override
    protected void onCreate(@Nullable Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
		SkProtect.CharlieProtect();
		IntentFilter filter = new IntentFilter();
		filter.addAction(UPDATE_VIEWS);
		filter.addAction(OPEN_LOGS);
		mDrawerPanel = new DrawerPanelMain(this);
		LocalBroadcastManager.getInstance(this).registerReceiver(mActivityReceiver, filter);
		mHandler = new Handler(Looper.getMainLooper());
		mConfig = new Settings(this);
		mDrawer = new DrawerLog(this);
		sp2 = PreferenceManager.getDefaultSharedPreferences(this);
		sp = mConfig.getPrefsPrivate();
        edit = sp.edit();
		prefs = mConfig.getPrefsPrivate();
        config = new ConfigUtil(this);
		
		updateConfig(true);
		
      
		doLayout();
		doUpdateLayout();
		//new TorrentDetection(this, torrentList).init();
		Thread.setDefaultUncaughtExceptionHandler(new ExceptionHandler(this));
		mAppUpdateManager = AppUpdateManagerFactory.create(this);
		mAppUpdateManager.getAppUpdateInfo().addOnSuccessListener(new OnSuccessListener<AppUpdateInfo>()
			{
				@Override
				public void onSuccess(AppUpdateInfo result) {
					if(result.updateAvailability() == UpdateAvailability.UPDATE_AVAILABLE && result.isUpdateTypeAllowed(AppUpdateType.FLEXIBLE))
					{
						try {
							mAppUpdateManager.startUpdateFlowForResult(result, AppUpdateType.IMMEDIATE, MainActivity.this,
																	   RC_APP_UPDATE);

						} catch (IntentSender.SendIntentException e) {}}
				}
			});
		mAppUpdateManager.registerListener(installUpdatelistener);
		loadServer();
		loadNetworks();
		SUpdateView();
		PUpdateView();
		doSaveData();
		loadServerData();
        loadAd();
        loadRewardedAd();
	}
	
	public class DrawerPanelMain
	implements NavigationView.OnNavigationItemSelectedListener
	{
		private AppCompatActivity mActivity;

		public DrawerPanelMain(AppCompatActivity activity) {
			mActivity = activity;
		}


		private DrawerLayout drawerLayout;
		private ActionBarDrawerToggle toggle;

		public void setDrawer(Toolbar toolbar) {
			NavigationView drawerNavigationView = (NavigationView) mActivity.findViewById(R.id.drawerNavigationView);
			drawerLayout = (DrawerLayout) mActivity.findViewById(R.id.drawerLayoutMain);

			// set drawer
			toggle = new ActionBarDrawerToggle(mActivity,
											   drawerLayout, toolbar, R.string.open, R.string.cancel);

			drawerLayout.setDrawerListener(toggle);

			toggle.syncState();

			// set app info
			PackageInfo pinfo = Utils.getAppInfo(mActivity);
			if (pinfo != null) {
				String version_nome = pinfo.versionName;
				int version_code = pinfo.versionCode;
				String header_text = String.format("Application Version: %s (%d)", version_nome, version_code);

				View view = drawerNavigationView.getHeaderView(0);

				TextView app_info_text = view.findViewById(R.id.nav_headerAppVersion);
				app_info_text.setText(header_text);
			}

			// set navigation view
			drawerNavigationView.setNavigationItemSelectedListener(this);
		}

		public ActionBarDrawerToggle getToogle() {
			return toggle;
		}

		public DrawerLayout getDrawerLayout() {
			return drawerLayout;
		}

		@Override
		public boolean onNavigationItemSelected(@NonNull MenuItem item) {
			int id = item.getItemId();

			switch(id)
			{
				case R.id.miPhoneConfg:
					if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
						drawerLayout.closeDrawers();
					}
					String str3 = "android.intent.action.MAIN";
					if(VERSION.SDK_INT<30)
					{
						Intent in = new Intent(str3);
						in.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
						in.setClassName("com.android.settings", "com.android.settings.RadioInfo");
						mActivity.startActivity(in);
					} else {
						Intent in2 = new Intent(str3);
						in2.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
						in2.setClassName("com.android.phone", "com.android.phone.settings.RadioInfo");
						mActivity.startActivity(in2);
					}
					break;

				case R.id.miJepongVpn:
					if (drawerLayout.isDrawerOpen(GravityCompat.START))
					{
						drawerLayout.closeDrawers();
					}
					Intent jepongIntent = new Intent(mActivity, com.slipkprojects.sockshttp.activities.JepongVpnActivity.class);
					jepongIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
					mActivity.startActivity(jepongIntent);
					break;

				case R.id.miSettings:
					if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
						drawerLayout.closeDrawers();
					}
					Intent intent = new Intent(mActivity, ConfigGeralActivity.class);
					intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
					mActivity.startActivity(intent);
					break;

				

				case R.id.miUpdateResources:
					if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
						drawerLayout.closeDrawers();
					}
					updateConfig(false);
					break;


				case R.id.miNotif:
					if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
						drawerLayout.closeDrawers();
					}
					//infoz();
                    showInterstitial();
					noterz();
					break;

				case R.id.miOffline:
					if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
						drawerLayout.closeDrawers();
					}
					//infoz();
                    offlineUpdate();
					break;
			}

			return true;
		}

	}
	
	@Override
	protected void onStop() {
		if(mAppUpdateManager != null) mAppUpdateManager.unregisterListener(installUpdatelistener);
		super.onStop();
	}



	private InstallStateUpdatedListener installUpdatelistener = new InstallStateUpdatedListener()
	{
		@Override
		public void onStateUpdate(InstallState state) {
			if(state.installStatus() == InstallStatus.DOWNLOADED)
			{
				showCompleterUpdate();
			}
		}
	};

	private void showCompleterUpdate()
	{
		Snackbar snacks = Snackbar.make(findViewById(android.R.id.content), "New app is ready!",
										Snackbar.LENGTH_INDEFINITE);
		snacks.setAction("Install", new View.OnClickListener()
			{
				@Override
				public void onClick(View view) {
					mAppUpdateManager.completeUpdate();
				}
			});
		snacks.setActionTextColor(Color.parseColor("#ffffff"));
		snacks.show();
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (requestCode == PICK_FILE)
		{
			if (resultCode == RESULT_OK) {
				try {
					Uri uri = data.getData();
					String intentData = importer(uri);
					//String cipter = AESCrypt.decrypt(ConfigUtil.PASSWORD, intentData);
					File file = new File(getFilesDir(), "Config.json");
					OutputStream out = new FileOutputStream(file);
					out.write(intentData.getBytes());
					out.flush();
					out.close();
					loadServer();
					loadNetworks();
					Utils.restart_app(MainActivity.this);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		} else if(requestCode == RC_APP_UPDATE && resultCode != RESULT_OK)
		{
			Toast.makeText(MainActivity.this, "Cancel", Toast.LENGTH_SHORT).show();
		} else if (requestCode == START_VPN_PROFILE) {
            if (resultCode == Activity.RESULT_OK) {
                SharedPreferences prefs = mConfig.getPrefsPrivate();

                if (!TunnelUtils.isNetworkOnline(this)) {
                    Toast.makeText(this, "No Internet Connection", Toast.LENGTH_SHORT).show();
                    card1.setEnabled(true);
			        card2.setEnabled(true);
                } else
                    TunnelManagerHelper.startSocksHttp(this);
                    showLogWindow();
            }
        }
		super.onActivityResult(requestCode, resultCode, data);
	}

	public void offlineUpdate() {
		Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
		intent.setType("*/*");
		startActivityForResult(intent, PICK_FILE);
	}

	private String importer(Uri uri)
	{
		BufferedReader reader = null;
		StringBuilder builder = new StringBuilder();
		try
		{
			reader = new BufferedReader(new InputStreamReader(getContentResolver().openInputStream(uri)));

			String line = "";
			while ((line = reader.readLine()) != null)
			{
				builder.append(line);
			}
			reader.close();
		}
		catch (IOException e) {e.printStackTrace();}
		return builder.toString();
	} 

	
	private void doLayout() { 
		setContentView(R.layout.activity_main_drawer);
		prefs = mConfig.getPrefsPrivate();
        SharedPreferences.Editor edit = prefs.edit();
		SharedPreferences sPrefs = mConfig.getPrefsPrivate();
		toolbar_main = (Toolbar) findViewById(R.id.toolbar_main);
		//toolbar_mainz = (Toolbar) findViewById(R.id.toolbar_mainz);
		setSupportActionBar(toolbar_main);
		mDrawerPanel.setDrawer(toolbar_main);
		mDrawer.setDrawer(this);
		adsBannerView = (AdView) findViewById(R.id.adBannerMainView);
		progz = ((ProgressBar)findViewById(R.id.progress_connect));
		mainLayout = (LinearLayout) findViewById(R.id.activity_mainLinearLayout);
		layout_timer = (LinearLayout) findViewById(R.id.layTimer);
		
		starterButton = (Button) findViewById(R.id.activity_starterButtonMain);
        starterButton.setOnClickListener(this);
        
        circularProgressBar = (CircularProgressIndicator)findViewById(R.id.circular_progress);
        circularProgressBar.setMaxProgress(100);
		
        
		final SharedPreferences prefs = mConfig.getPrefsPrivate();
		sPrefs.edit().putBoolean(Settings.PROXY_USAR_DEFAULT_PAYLOAD, false).apply();
		sPrefs.edit().putInt(Settings.TUNNELTYPE_KEY, Settings.bTUNNEL_TYPE_SSH_PROXY).apply();
		config_ver_txt = (TextView)findViewById(R.id.config_version);
		config_ver_txt.setText(config.getVersion());
		PackageInfo pinfo = Utils.getAppInfo(this);
		if (pinfo != null) {
			String version_nome = pinfo.versionName;
			//int version_code = pinfo.versionCode;
			String header_text = String.format("%s", version_nome);

			//View view = drawerNavigationView.getHeaderView(0);

			app_info_text = (TextView)findViewById(R.id.appz_version);
			app_info_text.setText(header_text);
		}
		stats_cont = (TextView)findViewById(R.id.stats_cont_txt);
		
		
		duration = (Chronometer) findViewById(R.id.chronometer);
        duration.setBase(SystemClock.elapsedRealtime());
		duration.setText("00h:00m:00s");
		duration.setOnChronometerTickListener(new OnChronometerTickListener(){
				@Override
				public void onChronometerTick(Chronometer chronometer) {
					long time = SystemClock.elapsedRealtime() - chronometer.getBase();
					int h   = (int)(time /3600000);
					int m = (int)(time - h*3600000)/60000;
					int s= (int)(time - h*3600000- m*60000)/1000 ;
					String t = (h < 10 ? "0"+h: h)+"h:"+(m < 10 ? "0"+m: m)+"m:"+ (s < 10 ? "0"+s: s)+"s";
					chronometer.setText(t);
				}
			});
			
		bytesIn = (TextView) findViewById(R.id.bytes_in);
        bytesOut = (TextView) findViewById(R.id.bytes_out);
		
		mTextViewCountDown = (TextView)findViewById(R.id.timerTextView);
		mButtonSet = (LinearLayout) findViewById(R.id.btnAddTime);
        mButtonSet_txt = (TextView) findViewById(R.id.btnAddTime_txt);
		mButtonSet.setOnClickListener(this);
		
		
		if (TunnelUtils.isNetworkOnline(MainActivity.this)) {
			adsBannerView.setAdListener(new AdListener() {
					@Override
					public void onAdLoaded() {
						if (adsBannerView != null) {
                            adsBannerView.setVisibility(View.VISIBLE);
                            //adsBannerView.setEnabled(false);
                           //showInterstitial();
						}
					}
				});
			adsBannerView.loadAd(new AdRequest.Builder()
								 .build());
		}
		edit.putBoolean(Settings.PROXY_USAR_DEFAULT_PAYLOAD, !true);
		card1 = (RelativeLayout) findViewById(R.id.mainviewCardView1);
        card1.setOnClickListener(this);
		card2 = (RelativeLayout) findViewById(R.id.mainviewCardView2);
        card2.setOnClickListener(this);
		selectedServer = (TextView)findViewById(R.id.servername_c);
		selectedSImage = (ImageView)findViewById(R.id.serverimg_c);
		selectedPayload = (TextView)findViewById(R.id.payloadname_c);
		selectedPImage = (ImageView)findViewById(R.id.payloadimg_c);
		//stats_cont = (TextView)findViewById(R.id.stats_cont_txt);
		SUpdateView();
		PUpdateView();

		serv_info = (TextView)findViewById(R.id.server_inf);
		prom_info = (TextView)findViewById(R.id.promo_inf);
		cat = (TextView)findViewById(R.id.cat);
		cat_bg = (LinearLayout)findViewById(R.id.cat_bg);
        
        ActivityResultLauncher<String> requestPermissionLauncher;
        requestPermissionLauncher = registerForActivityResult(new RequestPermission(), this::onPermissionsResult);
        if (!(ContextCompat.checkSelfPermission(this, Manifest.permission.POST_NOTIFICATIONS) == PackageManager.PERMISSION_GRANTED)) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                requestPermissionLauncher.launch(Manifest.permission.POST_NOTIFICATIONS);
            }
        }
        //circularProgressBar.setProgressMax(200);
        
       // this.dataTransfer = StatisticGraphData.getStatisticData().getDataTransferStats();
		edit.putString(Settings.CUSTOM_PAYLOAD_KEY, "CONNECT [host_port] [protocol][crlf]Host: [crlf]X-Online-Host: [crlf]X-Forward-Host: [crlf]Connection: Keep-Alive[crlf][crlf]");
		edit.apply();
	}
    
    
    private void onPermissionsResult(boolean isGranted) {
        String snackBar;
        if (isGranted) {
            snackBar = "Notification Permission Granted!";
        } else {
            snackBar = "Please grant Notification permission from App Settings";
        }
        Snackbar.make(findViewById(android.R.id.content).getRootView(), snackBar, Snackbar.LENGTH_SHORT).show();
    }
	
	private void SUpdateView() {
		try {  
			serv_info.setText(config.getServersArray().getJSONObject(sp.getInt("ServerPos", 0)).getString("sInfo"));
			selectedServer.setText(config.getServersArray().getJSONObject(sp.getInt("ServerPos", 0)).getString("sInfo"));
            selectedServer.setText(config.getServersArray().getJSONObject(sp.getInt("ServerPos", 0)).getString("Name"));
            InputStream inputStream = getApplicationContext().getAssets().open("flags/" + serverList.get(sp.getInt("ServerPos", 0)).get("Flag"));            
            selectedSImage.setImageDrawable(Drawable.createFromStream(inputStream, serverList.get(sp.getInt("ServerPos", 0)).get("Flag"))); 
			cat.setText("VIP");
			cat_bg.setBackgroundResource(R.drawable.bg_gradient);
            if (inputStream != null) {          
                inputStream.close();
            }
        }catch (Exception e) {}                     
	}

	private void PUpdateView() {
		try {
			prom_info.setText(config.getNetworksArray().getJSONObject(sp.getInt("PayloadPos", 0)).getString("pInfo"));
			selectedPayload.setText(config.getNetworksArray().getJSONObject(sp.getInt("PayloadPos", 0)).getString("pInfo"));
			selectedPayload.setText(config.getNetworksArray().getJSONObject(sp.getInt("PayloadPos", 0)).getString("Name"));
			String name = config.getNetworksArray().getJSONObject(sp.getInt("PayloadPos", 0)).getString("Name").toLowerCase();
			if (name.contains("globe")) {
				selectedPImage.setImageResource(R.drawable.ic_globe);
			} else if (name.contains("smart")) {
				selectedPImage.setImageResource(R.drawable.ic_smart);
			} else if (name.contains("gtm")) {
				selectedPImage.setImageResource(R.drawable.ic_gtm);
			} else if (name.contains("tm")) {
				selectedPImage.setImageResource(R.drawable.ic_tm);
			} else if (name.contains("tnt")) {
				selectedPImage.setImageResource(R.drawable.ic_tnt);
			} else if(name.contains("sun")) {
				selectedPImage.setImageResource(R.drawable.ic_sun);
			} else if(name.contains("dito")) {
				selectedPImage.setImageResource(R.drawable.ic_dito);
			} else if(name.contains("gomo")) {
				selectedPImage.setImageResource(R.drawable.ic_gomo);
			} else if(name.contains("sts")) {
				selectedPImage.setImageResource(R.drawable.ic_sts);
			} else if(name.contains("piso")){
				selectedPImage.setImageResource(R.drawable.pisovpn);
			} else{
				selectedPImage.setImageResource(R.drawable.icon_speed);
			}
		} catch (Exception e) {}
	}
	
	

	

	@Override
    public void onPostCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onPostCreate(savedInstanceState, persistentState);
        if (mDrawerPanel.getToogle() != null)
			mDrawerPanel.getToogle().syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        if (mDrawerPanel.getToogle() != null)
			mDrawerPanel.getToogle().onConfigurationChanged(newConfig);
    }
	
	private void updateHeaderCallback() {
		DataTransferStats dataTransferStats = StatisticGraphData.getStatisticData().getDataTransferStats();
		bytesIn.setText(dataTransferStats.byteCountToDisplaySize(dataTransferStats.getTotalBytesReceived(), false));
		bytesOut.setText(dataTransferStats.byteCountToDisplaySize(dataTransferStats.getTotalBytesSent(), false));
	}
	
	private void doUpdateLayout() {
		setStarterButton(starterButton, this);
        
	}
	
	public void starts() {
        if (!running) {
            duration.setBase(SystemClock.elapsedRealtime() - pauseOffset);
            duration.start();
            running = true;
        }
    }


	public void reset() {
		duration.stop();
        duration.setBase(SystemClock.elapsedRealtime());
        pauseOffset = 0;
		running = false;
    }
	
	
	private synchronized void doSaveData() {
		try {
			SharedPreferences prefs = mConfig.getPrefsPrivate();
			SharedPreferences.Editor edit = prefs.edit();
			if (mainLayout != null && !isFinishing()){
				mainLayout.requestFocus();
			}
			int pos = sp.getInt("PayloadPos", 0);
			int pos1 = sp.getInt("ServerPos", 0);
			
			boolean directModeType = config.getNetworksArray().getJSONObject(pos).getBoolean("isSSL");
			boolean sshssltype =  config.getNetworksArray().getJSONObject(pos).getBoolean("wsPayload");
			boolean remotessltype = config.getNetworksArray().getJSONObject(pos).getBoolean("remoteHost");
			boolean slowdnstype = config.getNetworksArray().getJSONObject(pos).getBoolean("SlowDns");
			boolean isUDP = config.getNetworksArray().getJSONObject(pos).getBoolean("isUDp");
			
			if (directModeType) {
				String sni = config.getNetworksArray().getJSONObject(pos).getString("SNI");
				edit.putString(Settings.CUSTOM_SNI, jaboler.decrypt(ConfigUtil.PASSWORD,sni));
				prefs.edit().putInt(Settings.TUNNELTYPE_KEY, Settings.bTUNNEL_TYPE_SSH_SSLTUNNEL).apply();
			} else if (sshssltype) {
				prefs.edit().putInt(Settings.TUNNELTYPE_KEY, Settings.bTUNNEL_TYPE_PAY_SSL).apply();
				String payload = config.getNetworksArray().getJSONObject(pos).getString("Payload");
				String snissl = config.getNetworksArray().getJSONObject(pos).getString("SNI");
				edit.putString(Settings.CUSTOM_PAYLOAD_KEY, jaboler.decrypt(ConfigUtil.PASSWORD,payload));
				edit.putString(Settings.CUSTOM_SNI, jaboler.decrypt(ConfigUtil.PASSWORD,snissl));

			} else if (isUDP) {
            
                edit.putBoolean(Settings.PROXY_USAR_DEFAULT_PAYLOAD, true).apply();
                edit.putInt(Settings.TUNNELTYPE_KEY, Settings.bTUNNEL_TYPE_UDP).apply();
  
                
			} else if (remotessltype){
				prefs.edit().putInt(Settings.TUNNELTYPE_KEY, Settings.bTUNNEL_TYPE_SSL_RP).apply();
				String payloadrp = config.getNetworksArray().getJSONObject(pos).getString("Payload");
				String sslrp = config.getNetworksArray().getJSONObject(pos).getString("SNI");
				edit.putString(Settings.CUSTOM_PAYLOAD_KEY, jaboler.decrypt(ConfigUtil.PASSWORD,payloadrp));
				edit.putString(Settings.CUSTOM_SNI, jaboler.decrypt(ConfigUtil.PASSWORD,sslrp));

			} else if (slowdnstype){
				prefs.edit().putInt(Settings.TUNNELTYPE_KEY, Settings.bTUNNEL_TYPE_SLOWDNS).apply();        
				String dnsKey = config.getNetworksArray().getJSONObject(pos).getString("dnsKey");
				edit.putString(Settings.DNS_KEY, jaboler.decrypt(ConfigUtil.PASSWORD,dnsKey));
			} else {
				prefs.edit().putInt(Settings.TUNNELTYPE_KEY, Settings.bTUNNEL_TYPE_SSH_PROXY).apply();
				String payload = config.getNetworksArray().getJSONObject(pos).getString("Payload");
				edit.putString(Settings.CUSTOM_PAYLOAD_KEY, jaboler.decrypt(ConfigUtil.PASSWORD,payload));
			}
			edit.apply();
		} catch (GeneralSecurityException e) {
            } catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void loadServerData() {
		try {
			SharedPreferences prefs = mConfig.getPrefsPrivate();
			SharedPreferences.Editor edit = prefs.edit();
            //SharedPreferences udpprefs = mConfig.getPrefsPrivate();

            int pos1 = sp.getInt("ServerPos", 0);
            int pos2 = sp.getInt("PayloadPos", 0);


            boolean directModeType = config.getNetworksArray().getJSONObject(pos2).getBoolean("isSSL");
			boolean sshssltype = config.getNetworksArray().getJSONObject(pos2).getBoolean("wsPayload");
			boolean remotessltype = config.getNetworksArray().getJSONObject(pos2).getBoolean("remoteHost");
			boolean slowdnstype = config.getNetworksArray().getJSONObject(pos2).getBoolean("SlowDns");
			boolean isUDP = config.getNetworksArray().getJSONObject(pos2).getBoolean("isUDp");
			
            if (directModeType) {
                String ssl_port = config.getServersArray().getJSONObject(pos1).getString("SSLPort");
                edit.putString(Settings.SERVIDOR_PORTA_KEY, ssl_port);
            } else if (sshssltype) {
				String ssl_port1 = config.getServersArray().getJSONObject(pos1).getString("SSLPort");
				edit.putString(Settings.SERVIDOR_PORTA_KEY, ssl_port1);

			} else if (remotessltype) {
				String ssl_port2 = config.getServersArray().getJSONObject(pos1).getString("SSLPort");
				edit.putString(Settings.SERVIDOR_PORTA_KEY, ssl_port2);

			} else if (slowdnstype) {
				String chaveKey = config.getServersArray().getJSONObject(pos1).getString("chaveKey");
				String serverNameKey = config.getServersArray().getJSONObject(pos1).getString("serverNameKey");    
				prefs.edit().putString(Settings.CHAVE_KEY, jaboler.decrypt(ConfigUtil.PASSWORD,chaveKey)).apply();
				prefs.edit().putString(Settings.NAMESERVER_KEY, jaboler.decrypt(ConfigUtil.PASSWORD,serverNameKey)).apply();        
				

            } else if(isUDP) {
                edit.putBoolean(Settings.PROXY_USAR_DEFAULT_PAYLOAD, true).apply();
                edit.putInt(Settings.TUNNELTYPE_KEY, Settings.bTUNNEL_TYPE_UDP).apply();
				
                
                edit.putString(Settings.SERVIDOR_PORTA_KEY, ":20000-50000").apply();
			} else {
                String ssh_port = config.getServersArray().getJSONObject(pos1).getString("ServerPort");
                edit.putString(Settings.SERVIDOR_PORTA_KEY, ssh_port);
            }

			String ssh_server = config.getServersArray().getJSONObject(pos1).getString("ServerIP");
			String remote_proxy = config.getServersArray().getJSONObject(pos1).getString("ProxyIP");
			String proxy_port = config.getServersArray().getJSONObject(pos1).getString("ProxyPort");
            String ssh_user = config.getServersArray().getJSONObject(pos1).getString("ServerUser");
			String ssh_password = config.getServersArray().getJSONObject(pos1).getString("ServerPass");
            
			try {
				edit.putString(Settings.USUARIO_KEY, jaboler.decrypt(ConfigUtil.PASSWORD,ssh_user));
				edit.putString(Settings.SENHA_KEY, jaboler.decrypt(ConfigUtil.PASSWORD,ssh_password));
			} catch (GeneralSecurityException e) {
            }
            
			edit.putString(Settings.SERVIDOR_KEY, jaboler.decrypt(ConfigUtil.PASSWORD,ssh_server));
			edit.putString(Settings.PROXY_IP_KEY, jaboler.decrypt(ConfigUtil.PASSWORD,remote_proxy));
			edit.putString(Settings.PROXY_PORTA_KEY, proxy_port);
			boolean useCustomProxy = config.getNetworksArray().getJSONObject(pos2).getBoolean("CustomUse");
			String custom_proxy = config.getNetworksArray().getJSONObject(pos2).getString("CustomProxy");
			String customport = config.getNetworksArray().getJSONObject(pos2).getString("CustomPort");

            if(useCustomProxy){
                edit.putString(Settings.SERVIDOR_KEY, jaboler.decrypt(ConfigUtil.PASSWORD,custom_proxy));
				edit.putString(Settings.PROXY_IP_KEY, jaboler.decrypt(ConfigUtil.PASSWORD,custom_proxy));
				edit.putString(Settings.PROXY_PORTA_KEY, customport);
                edit.putString(Settings.SERVIDOR_PORTA_KEY, customport);
            }
			String server_name = config.getServersArray().getJSONObject(pos1).getString("Name"); // for notif
			edit.putString("SERVER_NAME", server_name);
			String payload_name = config.getNetworksArray().getJSONObject(pos2).getString("Name"); // for notif
			edit.putString("PAYLOAD_NAME", payload_name);
			edit.apply();
		} catch (GeneralSecurityException e) {
            } catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void loadServer() {
		try {

			for (int i = 0; i < config.getServersArray().length(); i++) {
				JSONObject obj = config.getServersArray().getJSONObject(i);
				HashMap<String, String> map = new HashMap<String, String>();
                map.put("Name", obj.getString("Name"));
                map.put("Flag", obj.getString("FLAG"));
				map.put("INFO", obj.getString("sInfo"));
				serverList.add(map);
				
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void loadNetworks() {
		try {
			for (int i = 0; i < config.getNetworksArray().length(); i++) {
				JSONObject obj = config.getNetworksArray().getJSONObject(i);
				HashMap<String, String> map = new HashMap<String, String>();
                map.put("Name", obj.getString("Name"));
				map.put("INFO", obj.getString("pInfo"));
				payloadList.add(map);
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void updateConfig(final boolean isOnCreate) {
		new JsonRequestConfig(this, isOnCreate, new JsonRequestConfig.Listener(){

				@Override
				public void onResponse(String result) throws Exception {
					try {
						if (!result.contains("Error on getting data")) {
							String json_data = AESCrypt.decrypt(config.PASSWORD, result);
							if (isNewVersion(json_data)) {
								newupz(result);
								welcomeNotif2();
							} else {
								if (!isOnCreate) {
									noUpdateDialog();
                                    MainActivity.toast(getApplicationContext(), R.color.gray, "No Update!");
								}
							}
						} else if(result.contains("Error on getting data") && !isOnCreate){
							errorUpdateDialog(result);
						}
					} catch (Exception e) {
						e.printStackTrace();
					}
				}

				@Override
				public void onErrorResponse(String error) {
				}

				@Override
				public void onRequestFinished() {
				}
			});
	}

	private boolean isNewVersion(String result) {
		try {
			String current = config.getVersion();
			String update = new JSONObject(result).getString("Version");
			return config.versionCompare(update, current);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return false;
	}

	private void welcomeNotif2(){

        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE); 
        Notification.Builder notification = new Notification.Builder(this);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            notification.setChannelId(getPackageName() + "Config.json");
            createNotification(notificationManager, getPackageName() + "Config.json");
        }

        notification.setContentTitle(getString(R.string.app_name))
            .setContentText(("New Config Version Available"))
            .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.drawable.main_trinet_icon))
            .setDefaults(Notification.DEFAULT_ALL)
            .setPriority(Notification.PRIORITY_HIGH)
            .setShowWhen(true)
            .setSmallIcon(R.drawable.main_trinet_icon);
        notificationManager.notify(4130,notification.getNotification());
    }
	private void welcomeNotif(){

        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE); 
        Notification.Builder notification = new Notification.Builder(this);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            notification.setChannelId(getPackageName() + "Config.json");
            createNotification(notificationManager, getPackageName() + "Config.json");
        }

        notification.setContentTitle(getString(R.string.app_name))
            .setContentText(("Config Updated"))
            .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.drawable.main_trinet_icon))
            .setDefaults(Notification.DEFAULT_ALL)
            .setPriority(Notification.PRIORITY_HIGH)
            .setShowWhen(true)
            .setSmallIcon(R.drawable.main_trinet_icon);
        notificationManager.notify(4130,notification.getNotification());
    }

    private void createNotification(NotificationManager notificationManager, String id)
    {
        NotificationChannel mNotif = new NotificationChannel(id, "Config.json", NotificationManager.IMPORTANCE_HIGH);
        mNotif.setShowBadge(true);
        notificationManager.createNotificationChannel(mNotif);
        // TODO: Implement this method
    }

	int status = 0 ;
	ProgressDialog pd;
	byte[] YourDataA;

	/*private void letUpdate(final String result){
		YourDataA=result.getBytes();
	    pd = new ProgressDialog(this);
		pd.setTitle("New Config Update");
		pd.setMessage("Updating data..");
		pd.setMax(100);
		pd.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
		pd.setOnDismissListener(new DialogInterface.OnDismissListener(){

				@Override
				public void onDismiss(DialogInterface p1) {
					try {
						File file = new File(getFilesDir(), "Config.json");
						OutputStream out = new FileOutputStream(file);
						out.write(result.getBytes());
						out.flush();
						out.close();
						Utils.restart_app(MainActivity.this);
                        MainActivity.toast(getApplicationContext(), R.color.green, "Update Success!");
					} catch (Exception e) {
						e.printStackTrace();
					}
					//.setText(getConfig());
					status = 0;
				}
			});

		new Thread(new Runnable() {

                @Override
                public void run() {
                    while (status < 100) {
                        status += 1;
                        try {
                            Thread.sleep(20);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        runOnUiThread(new Runnable() {

                                @Override
                                public void run() {
                                    pd.setProgress(status);
									if(status == 100) {
										MainActivity.toast(getApplicationContext(), R.color.green, "Updated Successfully!");
										welcomeNotif();
                                        pd.dismiss();
                                    }
                                }
							});
					}
				}
            }).start();
		pd.show();
	}*/

	private void newUpdateDialog(final String result) throws JSONException, GeneralSecurityException {
		String json_data = AESCrypt.decrypt(config.PASSWORD, result);
		String notes = new JSONObject(json_data).getString("ReleaseNotes");
		String version = new JSONObject(json_data).getString("Version");
		welcomeNotif2();
        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View inflate = inflater.inflate(R.layout.new_update, (ViewGroup) null);
        AlertDialog.Builder builer = new AlertDialog.Builder(this); 
        builer.setView(inflate);
		final TextView title = inflate.findViewById(R.id.notiftext2);
		TextView ms = inflate.findViewById(R.id.confimsg2);
		final Button bubu = inflate.findViewById(R.id.appButton3);
		final androidx.cardview.widget.CardView up_card = inflate.findViewById(R.id.update_card_layout2);
		Animation anim = AnimationUtils.loadAnimation(this, R.drawable.animation_enter);
		final Animation anim2 = AnimationUtils.loadAnimation(MainActivity.this, R.drawable.animation_leave);
		title.setText("Notification");
        ms.setText(Html.fromHtml("<b>"+"Config Version: "+version+"</b>"+"<br><br>")+notes);
        bubu.setText("UPDATE NOW");
        final AlertDialog alert = builer.create(); 
        alert.setCanceledOnTouchOutside(false);
        alert.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        alert.getWindow().setGravity(Gravity.CENTER);
        alert.getWindow().getAttributes().windowAnimations = R.style.AppAlertDialog;
        alert.show();
		bubu.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View p1) {
					//letUpdate(result);
                    try {
						File file = new File(getFilesDir(), "Config.json");
						OutputStream out = new FileOutputStream(file);
						out.write(result.getBytes());
						out.flush();
						out.close();
						Utils.restart_app(MainActivity.this);
                        MainActivity.toast(getApplicationContext(), R.color.green, "Update Success!");
					} catch (Exception e) {
						e.printStackTrace();
					}
					alert.dismiss();
					//up_card.startAnimation(anim2);
				}
			});

		alert.create();
		alert.show();
		//up_card.startAnimation(anim);
	}
    
    private void newupz(final String result) throws JSONException, GeneralSecurityException
    {
        String json_data = AESCrypt.decrypt(config.PASSWORD, result);
		String notes = new JSONObject(json_data).getString("ReleaseNotes");
		String version = new JSONObject(json_data).getString("Version");
		welcomeNotif2();
        
        mAnimatedDialog = new MaterialDialog.Builder(this)
                .setTitle("Notification")
                .setMessage(Html.fromHtml("<b>"+"Config Version: "+version+"</b>"+"<br><br>")+notes)
                .setCancelable(false)
                .setPositiveButton("Update", R.drawable.ic_download_circle, new MaterialDialog.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                       // Toast.makeText(getApplicationContext(), "Deleted!", Toast.LENGTH_SHORT).show();
                       try {
						File file = new File(getFilesDir(), "Config.json");
						OutputStream out = new FileOutputStream(file);
						out.write(result.getBytes());
						out.flush();
						out.close();
						Utils.restart_app(MainActivity.this);
                        MainActivity.toast(getApplicationContext(), R.color.green, "Update Success!");
					} catch (Exception e) {
						e.printStackTrace();
					}
                        dialogInterface.dismiss();
                    }
                })
                .setNegativeButton("Later", R.drawable.ic_cancelzx, new MaterialDialog.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int which) {
                        //Toast.makeText(getApplicationContext(), "Cancelled!", Toast.LENGTH_SHORT).show();
                        dialogInterface.dismiss();
                    }
                })
               // .setAnimation("delete_anim.json")
                .build();
                mAnimatedDialog.show();
    }
    
    private void noterz()
    {
        mAnimatedDialog = new MaterialDialog.Builder(this)
                .setTitle("Notification")
                .setMessage(Html.fromHtml("<b>"+"Config Version: "+this.config.getVersion()+"</b>"+"<br><br>")+this.config.geNote())
                .setCancelable(false)
                .setPositiveButton("Confirm", R.drawable.cnt, new MaterialDialog.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                       // Toast.makeText(getApplicationContext(), "Deleted!", Toast.LENGTH_SHORT).show();
                        dialogInterface.dismiss();
                    }
                })
                .setNegativeButton("Cancel", R.drawable.ic_cancelzx, new MaterialDialog.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int which) {
                        //Toast.makeText(getApplicationContext(), "Cancelled!", Toast.LENGTH_SHORT).show();
                        dialogInterface.dismiss();
                    }
                })
               // .setAnimation("delete_anim.json")
                .build();
                mAnimatedDialog.show();
    }
	
	
	private void notepad(){

		LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View inflate = inflater.inflate(R.layout.notif_layout, (ViewGroup) null);
		AlertDialog.Builder builer = new AlertDialog.Builder(this); 
		builer.setView(inflate);
		final TextView title = inflate.findViewById(R.id.notiftext2);
		TextView ms = inflate.findViewById(R.id.confimsg2);
		final Button bubu = inflate.findViewById(R.id.appButton3);
		final androidx.cardview.widget.CardView up_card = inflate.findViewById(R.id.update_card_layout2);
		Animation anim = AnimationUtils.loadAnimation(this, R.drawable.animation_enter);
		final Animation anim2 = AnimationUtils.loadAnimation(MainActivity.this, R.drawable.animation_leave);
		title.setText("Notification");
		ms.setText(Html.fromHtml("<b>"+"Config Version: "+this.config.getVersion()+"</b>"+"<br><br>")+this.config.geNote());
		bubu.setText("Close");
		
		final AlertDialog alert = builer.create(); 
		alert.setCanceledOnTouchOutside(false);
		alert.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
		alert.getWindow().setGravity(Gravity.CENTER);
        alert.getWindow().getAttributes().windowAnimations = R.style.AppAlertDialog;
		alert.show();
		bubu.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View p1) {
					//up_card.startAnimation(anim2);
					//showInterstitial();
					alert.dismiss();
				}
			});

		alert.create();
		alert.show();
		//up_card.startAnimation(anim);
	}
	
	private void noUpdateDialog() {
        
        mSimpleBottomSheetDialog = new BottomSheetMaterialDialog.Builder(this)
                .setTitle("No Update Available", TextAlignment.CENTER)
                .setMessage("There is a no new update found.", TextAlignment.CENTER)
                .setCancelable(false)
                .setPositiveButton("Confirm", R.drawable.cnt, new BottomSheetMaterialDialog.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        //Toast.makeText(getApplicationContext(), "Deleted!", Toast.LENGTH_SHORT).show();
                        dialogInterface.dismiss();
                    }
                })
                .setNegativeButton("Cancel", R.drawable.ic_cancelzx, new BottomSheetMaterialDialog.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int which) {
                        //Toast.makeText(getApplicationContext(), "Cancelled!", Toast.LENGTH_SHORT).show();
                        dialogInterface.dismiss();
                    }
                })
                .build();
                mSimpleBottomSheetDialog.show();
                
        /*MaterialAlertDialogBuilder ab = new MaterialAlertDialogBuilder(MainActivity.this);
		ab.setTitle("No Update Available");
		ab.setMessage("There is a no new update found.");
		ab.setPositiveButton("Close", new DialogInterface.OnClickListener(){
				@Override
				public void onClick(DialogInterface p1, int p2){
					p1.cancel();
				}
			});
		ab.show();*/
        
	}

	private void errorUpdateDialog(String error) {
		/*MaterialAlertDialogBuilder ab = new MaterialAlertDialogBuilder(MainActivity.this);
		ab.setTitle("Error on update");
		ab.setMessage("There is an error occurred when checking for update. Please contact Developer.\n" +
					  "Error:" + error);
		ab.setPositiveButton("Close", new DialogInterface.OnClickListener(){

				@Override
				public void onClick(DialogInterface p1, int p2){
					p1.cancel();
				}
			});
		ab.show();*/
        
        mAnimatedDialog = new MaterialDialog.Builder(this)
                .setTitle("Error on update")
                .setMessage("There is an error occurred when checking for update. Please contact Developer.\n" +
					  "Error:" + error)
                .setCancelable(false)
                .setPositiveButton("Confirm", R.drawable.cnt, new MaterialDialog.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                       // Toast.makeText(getApplicationContext(), "Deleted!", Toast.LENGTH_SHORT).show();
                        dialogInterface.dismiss();
                    }
                })
                .setNegativeButton("Cancel", R.drawable.ic_cancelzx, new MaterialDialog.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int which) {
                        //Toast.makeText(getApplicationContext(), "Cancelled!", Toast.LENGTH_SHORT).show();
                        dialogInterface.dismiss();
                    }
                })
               // .setAnimation("delete_anim.json")
                .build();
                mAnimatedDialog.show();
	}
	
	/**
	 * Tunnel SSH
	 */

	public void startOrStopTunnel(Activity activity) {
		if (SkStatus.isTunnelActive()) {
			TunnelManagerHelper.stopSocksHttp(activity);
			card1.setEnabled(true);
			card2.setEnabled(true);
            isCheckedd = false;
		} else {
            isCheckedd = true;
			card1.setEnabled(false);
			card2.setEnabled(false);
			launchVPN();
            circularProgressBar.setProgress(10, 100);
	}
    }
    
    private void showLogWindow() {
        Intent updateView = new Intent(MainActivity.OPEN_LOGS);
		LocalBroadcastManager.getInstance(this).sendBroadcast(updateView);
        showRewardedVideo();
    }
    
    private void launchVPN() {
		Intent intent = VpnService.prepare(this);
        if (intent != null) {
            SkStatus.updateStateString("USER_VPN_PERMISSION", "", R.string.state_user_vpn_permission,
									   ConnectionStatus.LEVEL_WAITING_FOR_USER_INPUT);
            // Start the query
            try {
                startActivityForResult(intent, START_VPN_PROFILE);
            } catch (ActivityNotFoundException ane) {
                // Shame on you Sony! At least one user reported that
                // an official Sony Xperia Arc S image triggers this exception
                SkStatus.logError(R.string.no_vpn_support_image);
                //showLogWindow();
            }
        } else {
            onActivityResult(START_VPN_PROFILE, Activity.RESULT_OK, null);
        }
    }

	public void setStarterButton(Button starterButton, Activity activity) {
		String state = SkStatus.getLastState();
		boolean isRunning = SkStatus.isTunnelActive();
		if (starterButton != null) {
			int resId;
			if (SkStatus.SSH_INICIANDO.equals(state)) {
				resId = R.string.stop2;
				starterButton.setEnabled(false);
				card1.setEnabled(false);
				card2.setEnabled(false);
                //circularProgressBar.setProgress(100, 100);
			} else if (SkStatus.SSH_PARANDO.equals(state)) {
				resId = R.string.state_stopping;
				starterButton.setEnabled(false);
                circularProgressBar.setProgress(50, 100);
			} else if (SkStatus.SSH_DESCONECTADO.equals(state)){
                starterButton.setTextColor(ContextCompat.getColor(MainActivity.this, R.color.black));
                circularProgressBar.setDotColor(Color.TRANSPARENT);
				resId = R.string.start2;
				starterButton.setEnabled(true);
				card1.setEnabled(true);
				card2.setEnabled(true);
                circularProgressBar.setProgress(0, 100);
				stop();
			} else if (SkStatus.SSH_RECONECTANDO.equals(state)) {
				resId = R.string.state_reconnecting;
				starterButton.setEnabled(true);
                circularProgressBar.setProgress(30, 100);
			} else {
				resId = isRunning ? R.string.stop2 : R.string.start2;
				starterButton.setEnabled(true);
			}
			starterButton.setText(resId);
		}
	}
	
    String resIdz;
    
	@Override
	public void onClick(View p1) {
		int id = p1.getId();
		if(id == R.id.activity_starterButtonMain) {
            
			doSaveData();
			loadServerData();
			startOrStopTunnel(this);
		String state = SkStatus.getLastState();
        if (SkStatus.isTunnelActive()) {
		//kk
        } else {
        SharedPreferences prefs = mConfig.getPrefsPrivate();
        int tunnelType = prefs.getInt(Settings.TUNNELTYPE_KEY, Settings.bTUNNEL_TYPE_SSH_DIRECT);
		if(tunnelType==Settings.bTUNNEL_TYPE_SSH_PROXY){
			SkStatus.logInfo("<b>Tunnel Type:</b> SSH Proxy Connection");
		} else if(tunnelType==Settings.bTUNNEL_TYPE_SSH_SSLTUNNEL){
			SkStatus.logInfo("<b>Tunnel Type:</b> SSL/TLS Connection");
		} else if(tunnelType==Settings.bTUNNEL_TYPE_SSL_RP){
			SkStatus.logInfo("<b>Tunnel Type:</b> SSL Proxy Connection");
		} else if(tunnelType==Settings.bTUNNEL_TYPE_PAY_SSL){
			SkStatus.logInfo("<b>Tunnel Type:</b> SSL/TLS with Payload");
		} else if(tunnelType==Settings.bTUNNEL_TYPE_SLOWDNS){
			SkStatus.logInfo("<b>Tunnel Type:</b> SLOW DNS");
		} else if(tunnelType==Settings.bTUNNEL_TYPE_UDP){
			SkStatus.logInfo("<b>Tunnel Type:</b> UDP Custom");
		}
        }
		} else if(id == R.id.btnAddTime)
		{
            //loadingAds();
			//showRewardedVideo();

		} else if(id == R.id.mainviewCardView1){
            //showInterstitial();
			LinearLayout ll = new LinearLayout(MainActivity.this);
			ll.setOrientation(1);
			TextView tv = new TextView(MainActivity.this);
			tv.setPadding(20, 20, 20, 20);
			tv.setTypeface(tv.getTypeface(), Typeface.BOLD);                    
			ll.addView(tv);
			RecyclerView rv = new RecyclerView(MainActivity.this);
			rv.setHasFixedSize(true);
			rv.setLayoutManager(new LinearLayoutManager(MainActivity.this));        
			rv.setItemAnimator(new DefaultItemAnimator());
			rv.setAdapter(new MyAdapter(MainActivity.this, true, sp.getInt("ServerPos", 0), serverList));
			rv.scrollToPosition(sp.getInt("ServerPos", 0));                
			ll.addView(rv);
			final BottomSheetDialog dialog = new BottomSheetDialog(MainActivity.this);
			dialog.setCanceledOnTouchOutside(true);
			dialog.setContentView(ll);
			dialog.show();
			rv.addOnItemTouchListener(new RecyclerItemTouch(MainActivity.this, rv, 
										  new RecyclerItemTouch.ClickListener() {
											  @Override
											  public void onClick(View view, int i) {
												  edit.putInt("ServerPos", i).apply();
												  SUpdateView();
												  dialog.dismiss();
											  }

											  @Override
											  public void onLongClick(View view, int i) {

											  }
										  }));
		}else if(id == R.id.mainviewCardView2){
            //showInterstitial();
			LinearLayout linear2 = new LinearLayout(MainActivity.this);
			linear2.setOrientation(1);
			TextView tv2 = new TextView(MainActivity.this);
			tv2.setPadding(20, 20, 20, 20);
			tv2.setTypeface(tv2.getTypeface(), Typeface.BOLD);                    
			linear2.addView(tv2);
			RecyclerView rv2 = new RecyclerView(MainActivity.this);
			rv2.setHasFixedSize(true);
			rv2.setLayoutManager(new LinearLayoutManager(MainActivity.this));        
			rv2.setItemAnimator(new DefaultItemAnimator());
			rv2.setAdapter(new MyAdapter(MainActivity.this, false, sp.getInt("PayloadPos", 0), payloadList));
			rv2.scrollToPosition(sp.getInt("PayloadPos", 0));                
			linear2.addView(rv2);
			final BottomSheetDialog dialog2 = new BottomSheetDialog(MainActivity.this);
			dialog2.setCanceledOnTouchOutside(true);
			dialog2.setContentView(linear2);
			dialog2.show();
			rv2.addOnItemTouchListener(new RecyclerItemTouch(MainActivity.this, rv2, 
										   new RecyclerItemTouch.ClickListener() {
											   @Override
											   public void onClick(View view, int i) {
												   edit.putInt("PayloadPos", i).apply();
												   PUpdateView();
												   dialog2.dismiss();
											   }

											   @Override
											   public void onLongClick(View view, int i) {

											   }
										   }));
		}
	}
    
    private void connected()
    {
        Vibrator vb_service = (Vibrator)getSystemService(Context.VIBRATOR_SERVICE);
        vb_service.vibrate(150); 

    } 

	@Override
	public void updateState(final String state, String msg, int localizedResId, final ConnectionStatus level, Intent intent) {
		mHandler.post(new Runnable() {
			
			@Override
			public void run() {
				doUpdateLayout();
				if (SkStatus.isTunnelActive()){
					if (level.equals(ConnectionStatus.LEVEL_CONNECTED)){
						stats_cont.setText(R.string.connected);
						starts();
						showRewardedVideo();
						start();
                        connected();
                        layout_timer.setVisibility(View.GONE);
						progz.setVisibility(View.GONE);
                        MainActivity.toast(getApplicationContext(), R.color.green, "Connected Successfully! 🥰");
                        circularProgressBar.setProgress(100, 100);
                        circularProgressBar.setDotColor(ContextCompat.getColor(MainActivity.this, R.color.green));
                        circularProgressBar.setProgressColor(ContextCompat.getColor(MainActivity.this, R.color.green));
                        starterButton.setTextColor(ContextCompat.getColor(MainActivity.this, R.color.green));
					}
					if (level.equals(ConnectionStatus.LEVEL_NOTCONNECTED)){
						stats_cont.setText(R.string.servicestop);
						progz.setVisibility(View.GONE);
                        circularProgressBar.setProgress(0, 100);
					}   
					if (level.equals(ConnectionStatus.LEVEL_CONNECTING_SERVER_REPLIED)){
						stats_cont.setText(R.string.authenticating);
						progz.setVisibility(View.VISIBLE);
                        circularProgressBar.setProgress(80, 100);
                        starterButton.setText("Authenticating");
                        circularProgressBar.setProgressColor(ContextCompat.getColor(MainActivity.this, R.color.blue));
                        circularProgressBar.setDotColor(ContextCompat.getColor(MainActivity.this, R.color.blue));
                        starterButton.setTextColor(ContextCompat.getColor(MainActivity.this, R.color.blue));
					}       
					if (level.equals(ConnectionStatus.LEVEL_CONNECTING_NO_SERVER_REPLY_YET)){
						stats_cont.setText(R.string.connecting);
						progz.setVisibility(View.VISIBLE);
                        circularProgressBar.setProgress(50, 100);
                        starterButton.setText("Connecting");
                        circularProgressBar.setProgressColor(ContextCompat.getColor(MainActivity.this, R.color.yellow));
                        circularProgressBar.setDotColor(ContextCompat.getColor(MainActivity.this, R.color.yellow));
                        starterButton.setTextColor(ContextCompat.getColor(MainActivity.this, R.color.yellow));
					}           
					if (level.equals(ConnectionStatus.LEVEL_AUTH_FAILED)){
						stats_cont.setText(R.string.authfailed);
						//account();
						progz.setVisibility(View.GONE);
                        circularProgressBar.setProgress(0, 100);
                        circularProgressBar.setProgressColor(ContextCompat.getColor(MainActivity.this, R.color.red));
                        circularProgressBar.setDotColor(ContextCompat.getColor(MainActivity.this, R.color.red));
                        starterButton.setTextColor(ContextCompat.getColor(MainActivity.this, R.color.red));
					}                   
					if (level.equals(ConnectionStatus.UNKNOWN_LEVEL)){
						stats_cont.setText(R.string.disconnected);
						reset();
						stop();
						progz.setVisibility(View.GONE);
                        connected();
                        showInterstitial();
                        MainActivity.toast(getApplicationContext(), R.color.red, "Disconnected Successfully! 😔");
                        //MainActivity.toast(getApplicationContext(), R.color.red, "Disconnected Successfully! 😔");
						layout_timer.setVisibility(View.GONE);
                        circularProgressBar.setProgress(0, 100);
                        circularProgressBar.setProgressColor(Color.RED);
                        //starterButton.setTextColor(ContextCompat.getColor(MainActivity.this, R.color.red));
                        circularProgressBar.setDotColor(Color.TRANSPARENT);
                        starterButton.setTextColor(ContextCompat.getColor(MainActivity.this, R.color.black));
					}
				}               
				if (level.equals(ConnectionStatus.LEVEL_NONETWORK)){
					stats_cont.setText(R.string.nonetwork);
				}       
				
			}
		});
		
		switch (state) {
			case SkStatus.SSH_CONECTADO:
				// carrega ads banner
				if (adsBannerView != null && TunnelUtils.isNetworkOnline(MainActivity.this)) {
					adsBannerView.setAdListener(new AdListener() {
							@Override
							public void onAdLoaded() {
                                
								if (adsBannerView != null && !isFinishing()) {
									adsBannerView.setVisibility(View.VISIBLE);
                                   // adsBannerView.setEnabled(false);
                                    //showInterstitial();
								}
							}
						});
					adsBannerView.postDelayed(new Runnable() {
							@Override
							public void run() {

								if (adsBannerView != null && !isFinishing()) {
									adsBannerView.loadAd(new AdRequest.Builder()
														 .build());
								}
							}
						}, 5000);
				}
			break;
		}
	}


	/**
	 * Recebe locais Broadcast
	 */

	private BroadcastReceiver mActivityReceiver = new BroadcastReceiver() {
		
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (action == null){
                return;
			}
            if (action.equals(UPDATE_VIEWS) && !isFinishing()) {
				doUpdateLayout();
			}else if (action.equals(OPEN_LOGS)) {
				if (mDrawer != null && !isFinishing()) {
					DrawerLayout drawerLayout = mDrawer.getDrawerLayout();
					
					if (!drawerLayout.isDrawerOpen(GravityCompat.END)) {
						drawerLayout.openDrawer(GravityCompat.END);
					}
				}
			}
        }
    };


	@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		int id = item.getItemId();
		if(id == R.id.miLimparLogs){
			mDrawer.clearLogs();
			return true;
		} else if(id == R.id.settings){
			startActivity(new Intent(MainActivity.this, ConfigGeralActivity.class));
			return true;
		} else if(id == R.id.downloading){
			updateConfig(false);
            showInterstitial();
			return true;
		}
		
		
		return super.onOptionsItemSelected(item);
	}
	
@Override
	public void onDrawerOpened(View view) {
		if (view.getId() == R.id.activity_mainLogsDrawerLinear) {
			toolbar_main.getMenu().clear();
			getMenuInflater().inflate(R.menu.logs_menu, toolbar_main.getMenu());
		}
	}

	@Override
	public void onDrawerClosed(View view) {
		if (view.getId() == R.id.activity_mainLogsDrawerLinear) {
			toolbar_main.getMenu().clear();
			getMenuInflater().inflate(R.menu.main_menu, toolbar_main.getMenu());
		}
	}

	@Override
	public void onDrawerStateChanged(int stateId) {}

	@Override
	public void onDrawerSlide(View view, float p2) {}

	@Override
	public void onBackPressed() {
		DrawerLayout drawerLayout = mDrawer.getDrawerLayout();
		if (mDrawerPanel.getDrawerLayout().isDrawerOpen(GravityCompat.START)) {
            mDrawerPanel.getDrawerLayout().closeDrawers();
        } else if (drawerLayout.isDrawerOpen(GravityCompat.END)) {
			drawerLayout.closeDrawer(GravityCompat.END);
		}else {
			/*MaterialAlertDialogBuilder ab = new MaterialAlertDialogBuilder(MainActivity.this);
			ab.setIcon(R.drawable.main_trinet_icon);
			ab.setTitle("Attention");
			ab.setMessage("Are you sure you want to exit the app? Click Exit to proceed.");
			ab.setPositiveButton("Exit", new DialogInterface.OnClickListener(){

					@Override
					public void onClick(DialogInterface p1, int p2){
						Utils.exitAll(MainActivity.this);
					}
				});
			ab.setNegativeButton("NO", null);
			ab.show();*/
            
            
            mSimpleBottomSheetDialog = new BottomSheetMaterialDialog.Builder(this)
                .setTitle("Attention", TextAlignment.CENTER)
                .setMessage("Are you sure you want to exit the app? Click Exit to proceed.", TextAlignment.CENTER)
                .setCancelable(false)
                .setPositiveButton("Exit", R.drawable.ic_exitzx, new BottomSheetMaterialDialog.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        //Toast.makeText(getApplicationContext(), "Deleted!", Toast.LENGTH_SHORT).show();
                        dialogInterface.dismiss();
                        Utils.exitAll(MainActivity.this);
                    }
                })
                .setNegativeButton("Cancel", R.drawable.ic_cancelzx, new BottomSheetMaterialDialog.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int which) {
                      //Toast.makeText(getApplicationContext(), "Cancelled!", Toast.LENGTH_SHORT).show();
                        dialogInterface.dismiss();
                    }
                })
                //.setAnimation("delete_anim.json")
                .build();
                mSimpleBottomSheetDialog.show();
	    }
	}

	@Override
    public void onResume() {
        super.onResume();
		mDrawer.onResume();
		SkStatus.addStateListener(this);
        
		
		new Timer().schedule(new TimerTask()
			{
				@Override
				public void run()
				{
					runOnUiThread(new Runnable()
						{
							@Override
							public void run()
							{
								//adsPopUp();
								updateHeaderCallback();
								// TODO: Implement this method
							}
						});
					// TODO: Implement this method
				}
			}, 0,1000);
		mAppUpdateManager.getAppUpdateInfo().addOnSuccessListener(new OnSuccessListener<AppUpdateInfo>()
			{
				@Override
				public void onSuccess(AppUpdateInfo result) {
					if(result.updateAvailability() == UpdateAvailability.DEVELOPER_TRIGGERED_UPDATE_IN_PROGRESS)
					{
						try {
							mAppUpdateManager.startUpdateFlowForResult(result, AppUpdateType.IMMEDIATE, MainActivity.this,
																	   RC_APP_UPDATE);

						} catch (IntentSender.SendIntentException e) {}}
				}
			});
			
		
		if (!mTimerEnabled) {
            resumeTime(); 
        }
		if(SkStatus.isTunnelActive()){
			card1.setEnabled(false);
			card2.setEnabled(false);
		} else {
			card1.setEnabled(true);
			card2.setEnabled(true);
		}
		if (adsBannerView != null) {
			adsBannerView.resume();
		}
    }

	@Override
	protected void onPause() {
		super.onPause();
		doSaveData();
		SkStatus.removeStateListener(this);
		if (adsBannerView != null) {
			adsBannerView.pause();
		}
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		
		mDrawer.onDestroy();
		LocalBroadcastManager.getInstance(this).unregisterReceiver(mActivityReceiver);	
		mTimerEnabled = false;
        if (mCountDownTimer != null) {
            mCountDownTimer.cancel();
            mCountDownTimer = null;

        }
		if (adsBannerView != null) {
			adsBannerView.destroy();
		}
	}

	private void start() {

        SharedPreferences save_time = getSharedPreferences("time", Context.MODE_PRIVATE);
        long saved_ads_time = save_time.getLong("SAVED_TIME", 0);

        if (saved_ads_time == 0) {

			
            //Toast.makeText(MainActivity.this, "Less than 1hr! || Renew Access now", Toast.LENGTH_LONG).show();

            long time = 86400;

            long millisInput = time * 1000;

            setTime(millisInput);
        }

        if (!mTimerRunning) {
            startTimer();
        }

		mConnected = true;

    }

    private void stop() {
        if (mTimerRunning) {
            pauseTimer();
        }

		mConnected = false;

    }

    private void addTime(long time){

        setTime(time);

        if (mTimerRunning){
            pauseTimer();
        }
        //startTimer();
    }


    private void resetTimer() {

        SharedPreferences save_time = getSharedPreferences("time", Context.MODE_PRIVATE);

        mStartTimeInMillis = save_time.getLong("SAVED_TIME", 0);

        mTimeLeftInMillis = mStartTimeInMillis;
        updateCountDownText();

    }

    private void pauseTimer() {
        mCountDownTimer.cancel();
        retrieveTime();
        mTimerRunning = false;

    }

    private void updateCountDownText() {
		TimeUnit timeUnit = TimeUnit.MILLISECONDS;
		long days = timeUnit.toDays(mTimeLeftInMillis);
		long hours = timeUnit.toHours(mTimeLeftInMillis) - TimeUnit.DAYS.toHours(timeUnit.toDays(mTimeLeftInMillis));  
        long minutes = timeUnit.toMinutes(mTimeLeftInMillis) - TimeUnit.HOURS.toMinutes(timeUnit.toHours(mTimeLeftInMillis));
		long seconds = timeUnit.toSeconds(mTimeLeftInMillis) - TimeUnit.MINUTES.toSeconds(timeUnit.toMinutes(mTimeLeftInMillis));
		String resultString = (days < 10 ? "0"+days: days) + ":" + (hours < 10 ? "0"+hours: hours)+":"+(minutes < 10 ? "0"+minutes: minutes)+":"+ (seconds < 10 ? "0"+seconds: seconds);
		mTextViewCountDown.setText(resultString);
    }

    private void setTime(long milliseconds) {
        SharedPreferences save_time = getSharedPreferences("time", Context.MODE_PRIVATE);

        mStartTimeInMillis = milliseconds;

        SharedPreferences.Editor time_edit = save_time.edit();

        time_edit.putLong("SAVED_TIME", mTimeLeftInMillis + mStartTimeInMillis);

        time_edit.apply();

        resetTimer();

    }

    private void resumeTime() {

        resetTimer();

        // Use this code to continue time if app close accidentally while connected

        String state = SkStatus.getLastState();

        if (SkStatus.SSH_CONECTADO.equals(state)) {

            if (!mTimerRunning) {
                startTimer();
                mConnected = true;
            }
        }

        mTimerEnabled = true;
    }

    private void retrieveTime() {
        SharedPreferences saved_current_time = getSharedPreferences("time", Context.MODE_PRIVATE);

        SharedPreferences.Editor time_edit = saved_current_time.edit();

        time_edit.putLong("SAVED_TIME", mTimeLeftInMillis);

        time_edit.apply();
    }

    private void startTimer() {
        mEndTime = System.currentTimeMillis() + mTimeLeftInMillis;
        mCountDownTimer =
			new CountDownTimer(mTimeLeftInMillis, 1000) {

			@Override
			public void onTick(long millisUntilFinished) {
				mTimeLeftInMillis = millisUntilFinished;
				retrieveTime();
				updateCountDownText();
			}

			@Override
			public void onFinish() {
				mTimerRunning = false;
				pauseTimer();
				//saved_ads_time = 0;
				resetTime();

				// Code for auto stop vpn (sockshtttp)

				//stopService();

				MainActivity.toast(
					MainActivity.this,
					R.color.red,
					"Time expired, Please addtime!");
			}
		}.start();
        mTimerRunning = true;
    }
    
    private void stopService (){
		Intent stopVPN = new Intent(SocksHttpService.TUNNEL_SSH_STOP_SERVICE);
		LocalBroadcastManager.getInstance(MainActivity.this)
			.sendBroadcast(stopVPN);
	}

	private void resetTime() {

        SharedPreferences save_time = getSharedPreferences("time", Context.MODE_PRIVATE);

        SharedPreferences.Editor editor = save_time.edit();

        editor.clear();
        editor.apply();

    }

    private void btnTimer() {

		mBtnCountDown = new CountDownTimer(50000, 1000) {

			@Override
			public void onTick(long millisUntilFinished) {
				mTimeLeftBtn = millisUntilFinished;
				mButtonSet.setEnabled(false);
				
				updateBtnText();
			}
			@Override
			public void onFinish() {
				mButtonSet.setEnabled(true);
				mButtonSet_txt.setText("+15 hours");
				
			}

		}.start();

	}

	private void updateBtnText() {
		int seconds = (int) (mTimeLeftBtn / 1000) % 60;
		String timeLeftFormatted;
		if (seconds > 0) {
			timeLeftFormatted = String.format(Locale.getDefault(),
											  "%02d", seconds);

			mButtonSet_txt.setText("Wait in "+timeLeftFormatted);
			//mButtonSet2.setText("Refresh in " + timeLeftFormatted);
		} 
	}
	
	public void loadRewardedAd() {
    if (rewardedAd == null) {
      adIsLoading = true;
    AdRequest adRequest = new AdRequest.Builder().build();
    RewardedAd.load(this, AD_UNIT_ID_REWARDED,
      adRequest, new RewardedAdLoadCallback() {
        @Override
        public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
          // Handle the error.
          Log.d(TAG, loadAdError.toString());
          rewardedAd = null;
          MainActivity.this.adIsLoading = false;
          mButtonSet_txt.setText("Failed");
          //MainActivity.toast(getApplicationContext(), R.color.red, "Rewarded is not loaded!");
        }

        @Override
        public void onAdLoaded(@NonNull RewardedAd ad) {
          rewardedAd = ad;
          Log.d(TAG, "Ad was loaded.");
          MainActivity.this.adIsLoading = false;
          mButtonSet_txt.setText("Watch Ads");
          //MainActivity.toast(getApplicationContext(), R.color.green, "Rewarded is loaded!");
        }
    });
    }
  }
	
  
  private void showRewardedVideo() {
        if (rewardedAd == null) {
            Log.d("TAG", "The rewarded ad wasn't ready yet.");
            return;
        }

        rewardedAd.setFullScreenContentCallback(
			new FullScreenContentCallback() {
				@Override
				public void onAdShowedFullScreenContent() {
					// Called when ad is shown.
				}

				@Override
				public void onAdFailedToShowFullScreenContent(AdError adError) {
					// Called when ad fails to show.
					// Don't forget to set the ad reference to null so you
					// don't show the ad a second time.
					rewardedAd = null;
				}

				@Override
				public void onAdDismissedFullScreenContent() {
					// Called when ad is dismissed.
					// Don't forget to set the ad reference to null so you
					// don't show the ad a second time.
					MainActivity.this.loadRewardedAd();
					rewardedAd = null;
					//btnTimer(false);
				}
			});
        rewardedAd.show(MainActivity.this, new OnUserEarnedRewardListener() {
				@Override
				public void onUserEarnedReward(@NonNull RewardItem rewardItem) {
					MainActivity.toast(getApplicationContext(), R.color.green, "Thank you for supporting us.");
					//MDToast mdToast = MDToast.makeText(SocksHttpMainActivity.this," 2 hours successfully added!",MDToast.LENGTH_SHORT,MDToast.TYPE_SUCCESS);mdToast.show();
					long ads_time = 15 * rewardItem.getAmount() * 3600 * 1000;
					addTime(ads_time);
					//progressDialog.dismiss();
					//stop();
				    //btnTimer();
                    //mButtonSet.setText("+2 Hours");
				}
			});
    }
	
	public static void updateMainViews(Context context) {
		Intent updateView = new Intent(UPDATE_VIEWS);
		LocalBroadcastManager.getInstance(context).sendBroadcast(updateView);
	}
	
	
	
	private void loadAd() {
    if (adIsLoading || mInterstitialAd != null) {
      return;
    }
    adIsLoading = true;
		AdRequest adRequest = new AdRequest.Builder().build();
		InterstitialAd.load(
			this,
			AD_UNIT_ID_INTERS,
			adRequest,
			new InterstitialAdLoadCallback() {
				@Override
				public void onAdLoaded(@NonNull InterstitialAd interstitialAd) {
					MainActivity.this.mInterstitialAd = interstitialAd;
                    adIsLoading = false;
					interstitialAd.setFullScreenContentCallback(
						new FullScreenContentCallback() {
							@Override
							public void onAdDismissedFullScreenContent() {
								MainActivity.this.mInterstitialAd = null;

							}

							@Override
							public void onAdFailedToShowFullScreenContent(AdError adError) {
								MainActivity.this.mInterstitialAd = null;

							}

							@Override
							public void onAdShowedFullScreenContent() {

							}
						});
				}

				@Override
				public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
					MainActivity.this.mInterstitialAd = null;


				}
			});
	}

	
	public void ShowInters() {
    if (adIsLoading || mInterstitialAd != null) {
      return;
    }
    InterstitialAd.load(MainActivity.this, AD_UNIT_ID_INTERS,
        new AdRequest.Builder().build(),  new InterstitialAdLoadCallback() {
      @Override
      public void onAdLoaded(@NonNull InterstitialAd interstitialAd) {
        //Log.d(TAG, "Ad was loaded.");
        MainActivity.this.adIsLoading = false;
        MainActivity.this.mInterstitialAd = interstitialAd;
      }
      @Override
      public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
        Log.d(TAG, loadAdError.toString());
        
        MainActivity.this.mInterstitialAd = null;
        MainActivity.this.adIsLoading = false;
      }
    });
  }
    


	


    private void showInterstitial() {
        if (mInterstitialAd != null) {
            mInterstitialAd.show(this);
        } else {
            startGame();
        }
    }

    private void startGame() {
    // Request a new ad if one isn't already loaded, hide the button, and kick off the timer.
    if (mInterstitialAd == null) {
      loadAd();
        }

        
    }
	
    


	public static void toast(Context contxt, int color, String string){
		LayoutInflater inflater = (LayoutInflater) contxt.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View inflate = inflater.inflate(R.layout.toast, (ViewGroup) null );
        LinearLayout ll1 = new LinearLayout(contxt);
		Toast llIl = Toast.makeText(contxt,Html.fromHtml(""),Toast.LENGTH_LONG);
		final TextView text1 = (TextView)inflate.findViewById(R.id.textqt);
		final ImageView img = (ImageView)inflate.findViewById(R.id.img);
        final RelativeLayout toastlayout = (RelativeLayout)inflate.findViewById(R.id.toastlayout);
		GradientDrawable var1 = new GradientDrawable();
		final Animation e = AnimationUtils.loadAnimation(contxt,R.drawable.grow);
		var1.setColor(contxt.getResources().getColor(color));
		if (color == R.color.red){
			img.setBackgroundResource(R.drawable.main_trinet_icon);

		} else if (color == R.color.colorPrimary){
			img.setBackgroundResource(R.drawable.main_trinet_icon);

		} else if (color == R.color.green){
			img.setBackgroundResource(R.drawable.main_trinet_icon);

		} else if (color == R.color.gray){
            img.setBackgroundResource(R.drawable.main_trinet_icon);
        }else {
			img.setBackgroundResource(R.drawable.main_trinet_icon);

		}
        var1.setCornerRadius((float)50);
        var1.setOrientation(Orientation.RIGHT_LEFT);
        var1.setStroke(0, Color.parseColor("#ffffff"));
		text1.setText(Html.fromHtml(string));
        ll1.setBackgroundDrawable(var1);
        ll1.addView(inflate);
		toastlayout.setAnimation(e);
		llIl.setView(ll1);
		llIl.show();

	}
}

