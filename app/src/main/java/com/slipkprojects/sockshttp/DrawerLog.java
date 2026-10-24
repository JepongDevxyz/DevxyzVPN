package com.slipkprojects.sockshttp;

import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;
import java.util.ArrayList;
import xyz.devxyz.vpn.R;
import android.content.pm.PackageInfo;
import com.slipkprojects.sockshttp.util.Utils;
import androidx.recyclerview.widget.LinearLayoutManager;
import android.os.Build;
import android.view.View;
import android.app.Activity;
import android.os.Handler;
import java.util.Timer;
import java.util.TimerTask;
import android.widget.Toast;
import android.util.Log;
import com.slipkprojects.sockshttp.adapter.LogsAdapter;
import com.slipkprojects.ultrasshservice.logger.SkStatus;
import com.slipkprojects.ultrasshservice.config.Settings;
import android.os.Looper;

/**
* @author Skank3r
*/

public class DrawerLog implements LogsAdapter.OnItemClickListener {
	
	private static final String TAG = DrawerLog.class.getSimpleName();
	
	private Activity mActivity;
	private Handler mHandler;
	private DrawerLayout drawerLayout;
	private RecyclerView drawerListView;
	private LogsAdapter mAdapter;
	
	public DrawerLog(Activity activity) {
		mActivity = activity;
		mHandler = new Handler(Looper.getMainLooper());
	}
	
	// inicia Drawer e Toolbar
	public void setDrawer(DrawerLayout.DrawerListener listener) {

		drawerLayout = mActivity.findViewById(R.id.drawerLayout);
		drawerListView = mActivity.findViewById(R.id.recyclerDrawerView);
		
		drawerLayout.addDrawerListener(listener);
		
		LinearLayoutManager layoutManager = new LinearLayoutManager(mActivity);
		
		mAdapter = new LogsAdapter(layoutManager, mActivity);
		mAdapter.setOnItemClickListener(this);

		drawerListView.setAdapter(mAdapter);
		drawerListView.setLayoutManager(layoutManager);
		
		mAdapter.scrollToLastPosition();
	}
	
	public DrawerLayout getDrawerLayout() {
		return drawerLayout;
	}

	public void clearLogs() {
		mAdapter.clearLog();
	}
	
	
	/**
	* Logs OnItemClickListener implementação
	*/
	
	@Override
	public void onItemClick(View view, int position, String logText) {}

	@Override
	public void onItemLongClick(View view, int position, String logText) {
		try {
			// copia log para clipboard
			Utils.copyToClipboard(mActivity, logText);
		} catch(Exception e) {
			SkStatus.logException("Error copying Log", e);
			Toast.makeText(mActivity, "Could not copy log", Toast.LENGTH_SHORT)
				.show();
		}
	}


	/**
	* Eventos
	*/
	
	public void onResume() {
		//if (new Settings(mActivity).getModoDebug()) {
		//	mAdapter.setLogLevel(4);
		//} else {
			mAdapter.setLogLevel(3);
		//}
	}
	
	public void onDestroy() {
		SkStatus.removeLogListener(mAdapter);
	}
	
}
