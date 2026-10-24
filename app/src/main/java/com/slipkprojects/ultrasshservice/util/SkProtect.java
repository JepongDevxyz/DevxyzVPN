package com.slipkprojects.ultrasshservice.util;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import android.util.Log;
import android.widget.Toast;
import xyz.devxyz.vpn.BuildConfig;
import xyz.devxyz.vpn.R;

/**
 * @author Skank3r
 */
 


public class SkProtect {

	private String TAG = SkProtect.class.getSimpleName();
	private String APP_BASE = "xyz.devxyz.vpn";
	private static SkProtect mInstance;
	private Context mContext;
	
	public static void init(Context context) {
		if (mInstance == null) {
			mInstance = new SkProtect(context);
		}
	}

	private SkProtect(Context context) {
		mContext = context;
	}
	
	public void simpleProtect() {
		if (!APP_BASE.equals(mContext.getPackageName().toLowerCase()) || !mContext.getString(R.string.app_name).toLowerCase().equals("trinet socks reborn")) {
			System.exit(0);
		}
	}

	public static void CharlieProtect() {
		if (mInstance == null) return;	
		mInstance.simpleProtect();
	}
}
