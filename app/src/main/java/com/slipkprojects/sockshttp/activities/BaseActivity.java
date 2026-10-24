package com.slipkprojects.sockshttp.activities;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import com.slipkprojects.sockshttp.preference.SettingsPreference;
import com.slipkprojects.ultrasshservice.config.Settings;
import androidx.appcompat.app.AppCompatDelegate;
import android.content.Context;
import com.slipkprojects.sockshttp.preference.LocaleHelper;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import static android.content.pm.PackageManager.GET_META_DATA;
import org.json.JSONObject;
import com.slipkprojects.sockshttp.util.Utils;
import java.io.File;
import com.slipkprojects.sockshttp.util.AESCrypt;
import java.io.FileInputStream;
import java.io.InputStream;

/**
 * Created by Pankaj on 03-11-2017.
 */

public abstract class BaseActivity extends AppCompatActivity {
	
	private int night_mode;
	
	@Override
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setModoNoturnoLocal();	
		resetTitles();
	}
	
	@Override
	protected void attachBaseContext(Context base) {
		super.attachBaseContext(LocaleHelper.setLocale(base));
	}
	
	public void setModoNoturnoLocal() {
		if (new Settings(this).getModoNoturno().equals("on")) {
            night_mode = 2;
        } else {
            night_mode = 1;
        }
        getDelegate().setLocalNightMode(night_mode);
	}
	
	protected void resetTitles() {
		try {
			ActivityInfo info = getPackageManager().getActivityInfo(getComponentName(), GET_META_DATA);
			if (info.labelRes != 0) {
				setTitle(info.labelRes);
			}
		} catch (PackageManager.NameNotFoundException e) {
			e.printStackTrace();
		}
	}
}
