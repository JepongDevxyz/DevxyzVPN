package com.slipkprojects.sockshttp.util;

import android.content.Context;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;

/**
 * Created by: KervzCodes
 * Date Crated: 08/10/2020
 * Project: SocksHttp-master (ENGLISH)
 **/
 

public class ConfigUtil {

    Context context;

	//public static final String PASSWORD = "PASSWORD HERE";
    public static final String PASSWORD = new String(new byte[]{114, 101, 98, 111, 114, 110, 115, 111, 99, 107, 115});
    
    
    public ConfigUtil(Context context) {
        this.context = context;
    }
	
	public String geNote()
    {
        try {
            String releaseNote = getJSONConfig().getString("ReleaseNotes");
            return releaseNote;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public String getVersion() {
        try {
            String version = getJSONConfig().getString("Version");
            return version;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public JSONArray getServersArray() {
        try {
            if (getJSONConfig() != null) {
                JSONArray array = getJSONConfig().getJSONArray("Servers");
                JSONArray sortItem = JSONUtils.sort(array, JSONUtils.getComparator("Name", 1));
                return sortItem;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

	public ArrayList getNetworkSSLArray(ArrayList arraylist) {
		try {
			if (getJSONConfig() != null) {
				JSONArray array = getJSONConfig().getJSONArray("Networks");
				JSONArray jarr2 = array.getJSONObject(0).getJSONArray("SSL");
				for (int i = 0; i < jarr2.length(); i++)
				{
					JSONObject obj = jarr2.getJSONObject(i);
					arraylist.add(obj.getString("Name"));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public ArrayList getNetworkSSHArray(ArrayList arraylist) {
		try {
			if (getJSONConfig() != null) {
				JSONArray array = getJSONConfig().getJSONArray("Networks");
				JSONArray jarr2 = array.getJSONObject(0).getJSONArray("SSH");
				for (int i = 0; i < jarr2.length(); i++)
				{
					JSONObject obj = jarr2.getJSONObject(i);
					arraylist.add(obj.getString("Name"));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}


    public JSONArray getNetworksArray() {
        try {
            if (getJSONConfig() != null) {
                JSONArray array = getJSONConfig().getJSONArray("Networks");
				JSONArray sortItem = JSONUtils.sort(array, JSONUtils.getComparator("Name", 1));
                return sortItem;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean versionCompare(String NewVersion, String OldVersion) {
        String[] vals1 = NewVersion.split("\\.");
        String[] vals2 = OldVersion.split("\\.");
        int i = 0;
        while (i < vals1.length && i < vals2.length && vals1[i].equals(vals2[i])) {
            i++;
        }
        if (i < vals1.length && i < vals2.length) {
            int diff = Integer.valueOf(vals1[i]).compareTo(Integer.valueOf(vals2[i]));
            return Integer.signum(diff) > 0;
        }
        return Integer.signum(vals1.length - vals2.length) > 0;
    }

    private JSONObject getJSONConfig() {
        try {
            File file = new File(context.getFilesDir(), "Config.json");
            if (file.exists()) {
                String json_file = readStream(new FileInputStream(file));
                String json = AESCrypt.decrypt(PASSWORD, json_file);
                return new JSONObject(json);
            } else {
                InputStream inputStream = context.getAssets().open("config/config.json");
                String json = AESCrypt.decrypt(PASSWORD, readStream(inputStream));
                return new JSONObject(json);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private String readStream(InputStream in) {
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
        } catch (Exception e) {}
        return sb.toString();
    }
}
