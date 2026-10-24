package com.slipkprojects.ultrasshservice.tunnel;

import android.content.Context;
import android.content.SharedPreferences;
import com.slipkprojects.sockshttp.MyApplication;
import java.io.File;
import java.io.PrintWriter;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.io.IOException;
import java.io.OutputStream;
import java.io.FileOutputStream;
import org.json.JSONObject;
import org.json.JSONException;
import xyz.devxyz.vpn.R;
import com.slipkprojects.ultrasshservice.config.Settings;
import com.slipkprojects.ultrasshservice.logger.SkStatus;
import com.slipkprojects.ultrasshservice.config.Settings;
import com.slipkprojects.ultrasshservice.config.SettingsConstants;
import com.slipkprojects.ultrasshservice.util.CustomNativeLoader;


public class UDPThread extends Thread
{
    private String address;
    private String ca;
    private String auth;
    Context context;
    private String dir;
    private String dwon;
    private UDPStreamGobbler err;
    private File fileConf;
    private File fileDns;
    private File fileca;
    private final Settings mConfig;
    private String listen;
    private String obfs;
    private UDPStreamGobbler out;
    private String rc_conn;
    private String rc_w;
    private String retry;
    private String serv;
    private String server;
    private Process udpProcess;
    private JSONObject JsonData;
    private JSONObject jsonObject;
    private Thread udpThread;
    private String up;
	private boolean isUDPRunning;
    private final SharedPreferences sp;
    private static final String UDP_BIN = "libudp.so";
    private Context mContext;
    
    private final OnUDPConnected listener;
    
    private String certificate = "-----BEGIN CERTIFICATE-----\nMIIDizCCAnOgAwIBAgIUGxLl5Ou4dR1h3c9lUcaM5bp4ZBswDQYJKoZIhvcNAQEL\nBQAwVTELMAkGA1UEBhMCQ04xCzAJBgNVBAgMAkdEMQswCQYDVQQHDAJTWjEUMBIG\nA1UECgwLWklWUE4sIEluYy4xFjAUBgNVBAMMDVpJVlBOIFJvb3QgQ0EwHhcNMjMw\nMjExMDkwMjM1WhcNMzMwMjA4MDkwMjM1WjBVMQswCQYDVQQGEwJDTjELMAkGA1UE\nCAwCR0QxCzAJBgNVBAcMAlNaMRQwEgYDVQQKDAtaSVZQTiwgSW5jLjEWMBQGA1UE\nAwwNWklWUE4gUm9vdCBDQTCCASIwDQYJKoZIhvcNAQEBBQADggEPADCCAQoCggEB\nAMQsHTq2UD4WDOvNUFGQuKd0PEitgQzSh12qH9aJ5jnCtbWjqVNDRQSW0ietg4Po\nqOfKLOBvGOJcGkrYlAAynnwsufdkZd2Jj2+FAXloAbMBK5cjqRANfPJ7ns3S5zL2\nt2+Xv/O6H58NL5QksyIHb2Vcosfelwuvj5Lq+MvyqGZikce5IaykgjjV0OsrBnsC\neK4yAeoxsqVixGwmcJDLGOIJDGYcDdaElqJqFCyOjOhLLDymx9JbeOb3DpiRNFNN\nlwXi2rfvpnmpGNwNt9sclWAQTL3cfV4GsCovT02r1qxcAqqRE4U1nqMRqk0KfyQn\nUebOat/0jNJI9YxJByuVBK0CAwEAAaNTMFEwHQYDVR0OBBYEFGk91bjhFZfcKkpm\n5SxVkqnSGhXBMB8GA1UdIwQYMBaAFGk91bjhFZfcKkpm5SxVkqnSGhXBMA8GA1Ud\nEwEB/wQFMAMBAf8wDQYJKoZIhvcNAQELBQADggEBAEr4aeE0ib5/7neEcRWCE1pg\nw0j/958bdaSdQJJvYEpc7brCHhp5lmNJA+MjVcCXCL4/8KfuEcyGNPPSPo7wbuYJ\nO9jsJmQOklfyvlKGJschvc8AZ0E0AGdrgGam1KApjrb6Xly5bqgV4KPBQ7KttBVw\nwFfTm0yjD3nAjaSXi3I/MG+gMGnUXoTMZa3iS2pomBMHLdTksiujbbH7RP9mzPT3\n7UvyVmtw7eQFEjEYceVWHlhXCjL9gpcJiX/wu9XzREDpNCqY2R3zb+ZGYuQD0L5h\nzv0u1CF+Cfkkg8luxol+aWc+1ac/8TGLV1WOGj4FuEMfxQPXWFqhc8VEyxZ/r/w=\n-----END CERTIFICATE-----";
   
    public UDPThread(Context context, OnUDPConnected connected) {
        this.context = context;
        this.dir = context.getFilesDir().getPath();
        this.mConfig = new Settings(context);
        listener = connected;
        sp = mConfig.getPrefsPrivate();
        mContext = context;
   }
    
    public interface OnUDPConnected {
        void onUDPConnected(boolean result);
    }
    
    
    private boolean printToFile(File file, String str) {
        try {
            OutputStream out = new FileOutputStream(file);
            out.write(str.getBytes());
            out.flush();
            out.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    
    @Override
    public void run() {
        this.fileca = new File(this.dir, "azzphuc.ca.crt");
        String privString = sp.getString(Settings.SERVIDOR_KEY,"");
        this.serv = privString;
        if (privString.isEmpty()) {
            this.serv = sp.getString(Settings.SERVIDOR_KEY,"");
            try {
                sp.edit().putString(Settings.SERVIDOR_KEY, this.serv).apply();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        InetAddress[] inetAddressArr = new InetAddress[0];
        try {
            inetAddressArr = InetAddress.getAllByName(this.serv);
        } catch (UnknownHostException e2) {
            e2.printStackTrace();
        }
        try {
            this.address = getIPv4Addresses(inetAddressArr).getHostAddress();
        } catch (Exception e3) {
            e3.printStackTrace();
            if (String.valueOf(e3).contains("on a null object reference")) {
                SkStatus.logInfo("<font color='red'><strong>Invalid UDP Server</strong></font>");
                UDPThread.this.stopVudp();
                interrupt();
            }
        }
        this.server = this.address + ":20000-50000";
		String obfs_string = sp.getString(Settings.USUARIO_KEY,"test");

        this.obfs = obfs_string;
        if (obfs_string.equals("")) {
            this.obfs = "test";
            try {
                sp.edit().putString(Settings.USUARIO_KEY, this.obfs).apply();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        String privString2 = sp.getString(Settings.SENHA_KEY,"test");
        this.auth = privString2;
        if (privString2.equals("")) {
            this.auth = "test";
            try {
                sp.edit().putString(Settings.SENHA_KEY, this.auth).apply();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        String privString3 = sp.getString(Settings.UDP_SPEED_UP,"2");
        this.up = privString3;
        if (privString3.isEmpty()) {
            this.up = "2";
        }
        String privString4 = sp.getString(Settings.UDP_SPEED_DOWN,"3");
        this.dwon = privString4;
        if (privString4.isEmpty()) {
            this.dwon = "3";
        }
        this.retry = "3";
        String privString5 = sp.getString(Settings.UDP_WINDOW,"196608");
        this.rc_conn = privString5;
        if (privString5.isEmpty()) {
            this.rc_conn = "196608";
        }
        try {
            this.rc_w = String.valueOf((Integer.parseInt(this.rc_conn) * 5) / 2);
        } catch (NumberFormatException e4) {
            e4.printStackTrace();
        }
        this.listen = "127.0.0.1:1080";
        String str = this.dir + "/azzphuc.ca.crt";
        this.ca = str;
		String format = String.format("{\n  \"server\": \"%s\",\n  \"obfs\": \"%s\",\n  \"auth_str\": \"%s\",\n  \"up_mbps\": %s,\n  \"down_mbps\": %s,\n  \"retry\": %s,\n  \"retry_interval\": 1,\n  \"socks5\": {\n    \"listen\": \"%s\"\n  },\n  \"insecure\": true,\n  \"ca\": \"%s\",\n  \"recv_window_conn\": %s,\n  \"recv_window\": %s\n}", this.server, this.obfs, this.auth, this.up, this.dwon, this.retry, this.listen, str, this.rc_conn, this.rc_w);
        this.fileConf = new File(this.dir, "config.json");
        if (!printToFile(this.fileca, "-----BEGIN CERTIFICATE-----\nMIIDizCCAnOgAwIBAgIUGxLl5Ou4dR1h3c9lUcaM5bp4ZBswDQYJKoZIhvcNAQEL\nBQAwVTELMAkGA1UEBhMCQ04xCzAJBgNVBAgMAkdEMQswCQYDVQQHDAJTWjEUMBIG\nA1UECgwLWklWUE4sIEluYy4xFjAUBgNVBAMMDVpJVlBOIFJvb3QgQ0EwHhcNMjMw\nMjExMDkwMjM1WhcNMzMwMjA4MDkwMjM1WjBVMQswCQYDVQQGEwJDTjELMAkGA1UE\nCAwCR0QxCzAJBgNVBAcMAlNaMRQwEgYDVQQKDAtaSVZQTiwgSW5jLjEWMBQGA1UE\nAwwNWklWUE4gUm9vdCBDQTCCASIwDQYJKoZIhvcNAQEBBQADggEPADCCAQoCggEB\nAMQsHTq2UD4WDOvNUFGQuKd0PEitgQzSh12qH9aJ5jnCtbWjqVNDRQSW0ietg4Po\nqOfKLOBvGOJcGkrYlAAynnwsufdkZd2Jj2+FAXloAbMBK5cjqRANfPJ7ns3S5zL2\nt2+Xv/O6H58NL5QksyIHb2Vcosfelwuvj5Lq+MvyqGZikce5IaykgjjV0OsrBnsC\neK4yAeoxsqVixGwmcJDLGOIJDGYcDdaElqJqFCyOjOhLLDymx9JbeOb3DpiRNFNN\nlwXi2rfvpnmpGNwNt9sclWAQTL3cfV4GsCovT02r1qxcAqqRE4U1nqMRqk0KfyQn\nUebOat/0jNJI9YxJByuVBK0CAwEAAaNTMFEwHQYDVR0OBBYEFGk91bjhFZfcKkpm\n5SxVkqnSGhXBMB8GA1UdIwQYMBaAFGk91bjhFZfcKkpm5SxVkqnSGhXBMA8GA1Ud\nEwEB/wQFMAMBAf8wDQYJKoZIhvcNAQELBQADggEBAEr4aeE0ib5/7neEcRWCE1pg\nw0j/958bdaSdQJJvYEpc7brCHhp5lmNJA+MjVcCXCL4/8KfuEcyGNPPSPo7wbuYJ\nO9jsJmQOklfyvlKGJschvc8AZ0E0AGdrgGam1KApjrb6Xly5bqgV4KPBQ7KttBVw\nwFfTm0yjD3nAjaSXi3I/MG+gMGnUXoTMZa3iS2pomBMHLdTksiujbbH7RP9mzPT3\n7UvyVmtw7eQFEjEYceVWHlhXCjL9gpcJiX/wu9XzREDpNCqY2R3zb+ZGYuQD0L5h\nzv0u1CF+Cfkkg8luxol+aWc+1ac/8TGLV1WOGj4FuEMfxQPXWFqhc8VEyxZ/r/w=\n-----END CERTIFICATE-----") || !printToFile(this.fileConf, format)) {
            interrupt();
            return;
        }
        
        File file = new File(this.context.getApplicationInfo().nativeLibraryDir, "libudp.so");
        this.fileDns = file;
        String[] strArr = {file.getAbsolutePath(), "client", "--config", this.fileConf.getAbsolutePath()};
        final UDPStreamGobbler.OnResultListener onResultListener = str1 -> {
            if (str1.contains("ZIVPN UDP running")) {
                SkStatus.logInfo("<font color='green'><strong>UDP Client: Connected</strong></font>");
                //SkStatus.logInfo("<font color='green'><strong>UDP Connected Successfully</strong></font>");
                SkStatus.logInfo("<font color='#FF7F27'><strong>UDP Client: You Have Internet Now</strong></font>");
                listener.onUDPConnected(true);
            } else if (str1.contains("no recent network activity")) {
                SkStatus.logInfo("<font color='red'><strong>UDP Client: Connection lost, retrying...</strong></font>");
                interrupt();
                listener.onUDPConnected(false);
            } else if (str1.contains("Connection to server lost") || str1.contains("handshake did not complete in time")) {
                SkStatus.logInfo("<font color='red'><strong>UDP Client: Please wait, reconnecting UDP</strong></font>");
                interrupt();
                listener.onUDPConnected(false);
            } else if (str1.contains("Failed to parse client configuration")) {
                SkStatus.logInfo("<font color='red'><strong>Invalid UDP setting</strong></font>");
                listener.onUDPConnected(false);
                stopVudp();
            } else if (str1.contains("auth error")) {
                SkStatus.logInfo("<font color='red'><strong>UDP Client: Authentication failed, invalid password/expired/may logged-in on another device</strong></font>");
                listener.onUDPConnected(false);
                stopVudp();
                interrupt();
            } else if (str1.contains("too many connections")) {
                SkStatus.logInfo("<font color='red'><strong>Same account on multi-device not support</strong></font>");
                listener.onUDPConnected(false);
                stopVudp();
            } else if (str.contains("out of retries, exiting")){
                interrupt();
                listener.onUDPConnected(false);
                return;
            } else if (str.contains("failed to initialize client")){
                SkStatus.logInfo("<font color='red'><strong>Failed to initialize client</strong></font>");
                listener.onUDPConnected(false);
                stopVudp();
            }
        };
        try {
            ProcessBuilder processBuilder = new ProcessBuilder(strArr);
            processBuilder.redirectErrorStream(true);
            this.udpProcess = processBuilder.start();
            this.out = new UDPStreamGobbler(this.udpProcess.getInputStream(), onResultListener);
            this.err = new UDPStreamGobbler(this.udpProcess.getErrorStream(), onResultListener);
            this.out.setInterrupted(false);
            this.out.start();
            this.err.setInterrupted(false);
            this.err.start();
            this.udpProcess.waitFor();
        } catch (Exception e5) {
            //SkStatus.logInfo(e5.toString());
            e5.printStackTrace();
        }
    }

    public static Inet4Address getIPv4Addresses(InetAddress[] inetAddressArr) {
        for (InetAddress inetAddress : inetAddressArr) {
            if (inetAddress instanceof Inet4Address) {
                return (Inet4Address) inetAddress;
            }
        }
        return null;
    }

    public void stopVudp() {
        new Thread(() -> {
            SkStatus.logInfo("<b>UDP Client:</b> Stopping UDP....");
            interrupt();
            while (true) {
                try {
                    Thread.sleep(2000L);
                    TunnelManagerHelper.stopSocksHttp(context);
                    SkStatus.logInfo("<b>UDP Client:</b> Thread stopped");
                    SkStatus.clearLog();
                } catch (InterruptedException ex) {
                    continue;
                }
                break;
            }
        }).start();
    }

    @Override
    public void interrupt() {
        UDPStreamGobbler uDPStreamGobbler = this.out;
        if (uDPStreamGobbler != null) {
            uDPStreamGobbler.setInterrupted(true);
            this.out.interrupt();
            this.out = null;
        }
        uDPStreamGobbler = this.err;
        if (uDPStreamGobbler != null) {
            uDPStreamGobbler.setInterrupted(true);
            this.err.interrupt();
            this.err = null;
        }
        Process process = this.udpProcess;
        if (process != null) {
            process.destroy();
            this.udpProcess = null;
        }
        File file = this.fileConf;
        if (file != null && file.exists()) {
            this.fileConf.delete();
        }
        super.interrupt();
    }
}
