package com.slipkprojects.sockshttp.util;

import android.content.pm.PackageInfo;
import android.content.Context;
import android.content.pm.PackageManager;
import android.net.NetworkInfo;
import android.net.ConnectivityManager;
import android.annotation.SuppressLint;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import android.view.inputmethod.InputMethodManager;
import android.app.Activity;
import android.view.WindowManager;
import android.content.Intent;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import android.os.Build;
import com.slipkprojects.ultrasshservice.SocksHttpService;
import java.io.InputStream;
import java.io.Reader;
import android.content.ComponentName;
import androidx.appcompat.app.AlertDialog;
import android.content.DialogInterface;
import xyz.devxyz.vpn.R;

public class Utils
{
	@SuppressLint("NewApi")
    @SuppressWarnings("deprecation")
    public static void copyToClipboard(Context context, String text) throws Exception {
		int sdk = android.os.Build.VERSION.SDK_INT;
            
		if (sdk < android.os.Build.VERSION_CODES.HONEYCOMB) {
                android.text.ClipboardManager clipboard = (android.text.ClipboardManager) context
					.getSystemService(context.CLIPBOARD_SERVICE);
                clipboard.setText(text);
		}
		else {
                android.content.ClipboardManager clipboard = (android.content.ClipboardManager) context
					.getSystemService(context.CLIPBOARD_SERVICE);
                android.content.ClipData clip = android.content.ClipData
					.newPlainText(
					"Message", text);
                clipboard.setPrimaryClip(clip);
         }
	}
	
	public static PackageInfo getAppInfo(Context context){
		try {
			return context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
		} catch (PackageManager.NameNotFoundException e) {
			throw new RuntimeException(e);
		}
	}
	
	public static String readFromAssets(Context context, String filename) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(context.getAssets().open(filename)));

        // do reading, usually loop until end of file reading
        StringBuilder sb = new StringBuilder();
        String mLine = reader.readLine();
        while (mLine != null) {
            sb.append(mLine + '\n'); // process line
            mLine = reader.readLine();
        }
        reader.close();
        return sb.toString();
    }
	public static String readStream(InputStream in) {
        StringBuilder sb = new StringBuilder();
        try {
            Reader reader = new BufferedReader(new InputStreamReader(in));
            char[] buff = new char[1024];
            while (true) {
                int read = reader.read(buff, 0, buff.length);
                if (read <= 0) {
                    break;
                }
                sb.append(buff, 0, read);
            }
        } catch (Exception e) {

        }
        return sb.toString();
    }
	public static void hideKeyboard(Activity activity) {
		InputMethodManager inputManager = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
		
        if (activity.getWindow().getAttributes().softInputMode != WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN) {
            if (activity.getCurrentFocus() != null)
                inputManager.hideSoftInputFromWindow(activity.getCurrentFocus().getWindowToken(),
					0);
        }
    }
	
	public static void restart_app(Activity act) {
		PackageManager packageManager = act.getPackageManager();
		Intent intent = packageManager.getLaunchIntentForPackage(act.getPackageName());
		assert intent != null;
		ComponentName componentName = intent.getComponent();
		Intent mainIntent = Intent.makeRestartActivityTask(componentName);
		act.startActivity(mainIntent);
		System.exit(0);
	}
	
	public static void exitAll(Activity activity) {
		Intent stopTunnel = new Intent(SocksHttpService.TUNNEL_SSH_STOP_SERVICE);
		LocalBroadcastManager.getInstance(activity).sendBroadcast(stopTunnel);
		if (Build.VERSION.SDK_INT >= 16) {
			activity.finishAffinity();
		}
		System.exit(0);
	}
	
	public static void showExitDialog(final Activity a) {
		AlertDialog dialog = new AlertDialog.Builder(a).create();
		dialog.setTitle(a.getString(R.string.attention));
		dialog.setMessage(a.getString(R.string.alert_exit));
		dialog.setButton(DialogInterface.BUTTON_POSITIVE, a.getString(R.string.exit), new DialogInterface.OnClickListener() {

			    @Override
				public void onClick(DialogInterface dialog, int which){
					Utils.exitAll(a);
				}
			});
		dialog.setButton(DialogInterface.BUTTON_NEGATIVE, a.getString(R.string.minimize), new DialogInterface.OnClickListener() {

			    @Override
				public void onClick(DialogInterface dialog, int which) {
					Intent startMain = new Intent(Intent.ACTION_MAIN);
					startMain.addCategory(Intent.CATEGORY_HOME);
					startMain.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
					a.startActivity(startMain);
				}
			});
		dialog.show();
	}
}
