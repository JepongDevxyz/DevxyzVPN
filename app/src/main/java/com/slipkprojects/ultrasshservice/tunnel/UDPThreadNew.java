package com.slipkprojects.ultrasshservice.tunnel;

import android.content.Context;
import android.content.SharedPreferences;
import com.slipkprojects.ultrasshservice.config.Settings;
import com.slipkprojects.ultrasshservice.logger.SkStatus;
import java.io.File;
import java.io.PrintWriter;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.UnknownHostException;
import android.widget.Toast;

public class UDPThreadNew extends Thread
{
    private final SharedPreferences sp;
    private String address;
    private String auth;
    private String ca;
    Context context;
    private String dir;
    private String dwon;
    private UDPStreamGobbler err;
    private File fileDns;
    private File fileca;
    private String listen;
    private String obfs;
    private UDPStreamGobbler out;
    private String rc_conn;
    private String rc_w;
    private String retry;
    private String serv;
    private String server;
    private Process udpProcess;
    private Thread udpThread;
    private String up;

	private boolean isUDPRunning;


    public UDPThreadNew(Context context) {
        this.context = context;
        this.dir = context.getFilesDir().getPath();
        Settings mConfig = new Settings(context);
        sp = mConfig.getPrefsPrivate();
    }

    private boolean printToFile(File file, String str) {
        try {
            PrintWriter printWriter = new PrintWriter(file);
            printWriter.println(str);
            printWriter.flush();
            printWriter.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public void run() {
		UDPThreadNew.this.fileca = new File(this.dir, "azzphuc.ca.crt");
        String privString = sp.getString(Settings.SERVIDOR_KEY,"");
		UDPThreadNew.this.serv = privString;
        if (privString.isEmpty()) {
			UDPThreadNew.this.serv = sp.getString(Settings.SERVIDOR_KEY,"");
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
			UDPThreadNew.this.address = getIPv4Addresses(inetAddressArr).getHostAddress();
        } catch (Exception e3) {
            e3.printStackTrace();
            if (String.valueOf(e3).contains("on a null object reference")) {
                SkStatus.logInfo("<font color='red'><strong>Invalid UDP Server</strong></font>");
                UDPThreadNew.this.stopVudp();
            }
        }
		UDPThreadNew.this.server = this.address + ":20000-25000";
        UDPThreadNew.this.obfs = "zivpn";
        String privString2 = "0hatbmzb8cml";
		UDPThreadNew.this.auth = privString2;
        if (privString2.isEmpty()) {
			UDPThreadNew.this.auth = "0hatbmzb8cml";
        }
        String privString3 = sp.getString("up_mbps","2");
		UDPThreadNew.this.up = privString3;
        if (privString3.isEmpty()) {
            UDPThreadNew.this.up = "2";
        }
        String privString4 = sp.getString("down_mbps","3");
		UDPThreadNew.this.dwon = privString4;
        if (privString4.isEmpty()) {
			UDPThreadNew.this.dwon = "3";
        }
		UDPThreadNew.this.retry = "3";
        String privString5 = sp.getString("udpwindow","1048576");
		UDPThreadNew.this.rc_conn = privString5;
        if (privString5.isEmpty()) {
            UDPThreadNew.this.rc_conn = "2621440";
        }
        try {
            this.rc_w = String.valueOf((Integer.parseInt(this.rc_conn) * 5) / 2);
        } catch (NumberFormatException e4) {
            e4.printStackTrace();
        }
		UDPThreadNew.this.listen = "127.0.0.1:1080";
        String str = this.dir + "/azzphuc.ca.crt";
		UDPThreadNew.this.ca = str;
		String format = String.format("{\n  \"server\": \"%s\",\n  \"obfs\": \"%s\",\n  \"auth_str\": \"%s\",\n  \"up_mbps\": %s,\n  \"down_mbps\": %s,\n  \"retry\": %s,\n  \"retry_interval\": 1,\n  \"socks5\": {\n    \"listen\": \"%s\"\n  },\n  \"insecure\": true,\n  \"ca\": \"%s\",\n  \"recv_window_conn\": %s,\n  \"recv_window\": %s\n}", this.server, this.obfs, this.auth, this.up, this.dwon, this.retry, this.listen, str, this.rc_conn, this.rc_w);
        if (!printToFile(this.fileca, "-----BEGIN CERTIFICATE-----\nMIIDizCCAnOgAwIBAgIUGxLl5Ou4dR1h3c9lUcaM5bp4ZBswDQYJKoZIhvcNAQEL\nBQAwVTELMAkGA1UEBhMCQ04xCzAJBgNVBAgMAkdEMQswCQYDVQQHDAJTWjEUMBIG\nA1UECgwLWklWUE4sIEluYy4xFjAUBgNVBAMMDVpJVlBOIFJvb3QgQ0EwHhcNMjMw\nMjExMDkwMjM1WhcNMzMwMjA4MDkwMjM1WjBVMQswCQYDVQQGEwJDTjELMAkGA1UE\nCAwCR0QxCzAJBgNVBAcMAlNaMRQwEgYDVQQKDAtaSVZQTiwgSW5jLjEWMBQGA1UE\nAwwNWklWUE4gUm9vdCBDQTCCASIwDQYJKoZIhvcNAQEBBQADggEPADCCAQoCggEB\nAMQsHTq2UD4WDOvNUFGQuKd0PEitgQzSh12qH9aJ5jnCtbWjqVNDRQSW0ietg4Po\nqOfKLOBvGOJcGkrYlAAynnwsufdkZd2Jj2+FAXloAbMBK5cjqRANfPJ7ns3S5zL2\nt2+Xv/O6H58NL5QksyIHb2Vcosfelwuvj5Lq+MvyqGZikce5IaykgjjV0OsrBnsC\neK4yAeoxsqVixGwmcJDLGOIJDGYcDdaElqJqFCyOjOhLLDymx9JbeOb3DpiRNFNN\nlwXi2rfvpnmpGNwNt9sclWAQTL3cfV4GsCovT02r1qxcAqqRE4U1nqMRqk0KfyQn\nUebOat/0jNJI9YxJByuVBK0CAwEAAaNTMFEwHQYDVR0OBBYEFGk91bjhFZfcKkpm\n5SxVkqnSGhXBMB8GA1UdIwQYMBaAFGk91bjhFZfcKkpm5SxVkqnSGhXBMA8GA1Ud\nEwEB/wQFMAMBAf8wDQYJKoZIhvcNAQELBQADggEBAEr4aeE0ib5/7neEcRWCE1pg\nw0j/958bdaSdQJJvYEpc7brCHhp5lmNJA+MjVcCXCL4/8KfuEcyGNPPSPo7wbuYJ\nO9jsJmQOklfyvlKGJschvc8AZ0E0AGdrgGam1KApjrb6Xly5bqgV4KPBQ7KttBVw\nwFfTm0yjD3nAjaSXi3I/MG+gMGnUXoTMZa3iS2pomBMHLdTksiujbbH7RP9mzPT3\n7UvyVmtw7eQFEjEYceVWHlhXCjL9gpcJiX/wu9XzREDpNCqY2R3zb+ZGYuQD0L5h\nzv0u1CF+Cfkkg8luxol+aWc+1ac/8TGLV1WOGj4FuEMfxQPXWFqhc8VEyxZ/r/w=\n-----END CERTIFICATE-----")) {
            interrupt();
            return;
        }
        File file = new File(this.context.getApplicationInfo().nativeLibraryDir, "libudp");
		UDPThreadNew.this.fileDns = file;

		String[] strArr = {file.getAbsolutePath(),"client", "-s", "hu``hqb`c", "--config", format};
        UDPStreamGobbler.OnResultListener onResultListener = str1 -> {
            //SkStatus.logInfo(str1);
            if (str1.contains("UDP running")) {
                isUDPRunning = true;
                SkStatus.logInfo("<font color='#FF7F27'><strong>UDP Connection Established</strong></font>");
            } else if (str1.contains("Failed to parse client configuration")) {
                isUDPRunning = false;
                SkStatus.logInfo("<strong>Invalid UDP setting</strong>");
                UDPThreadNew.this.stopVudp();
            } else if (str1.contains("auth error")) {
                isUDPRunning = false;
                SkStatus.logInfo("<strong>Authentication failed, invalid password/expired/may logged-in on another device</strong>");
                UDPThreadNew.this.stopVudp();
            } else if (str1.contains("too many connections")) {
                isUDPRunning = false;
                SkStatus.logInfo("<strong>Same account on multi-device not support</strong>");
                UDPThreadNew.this.stopVudp();
            }

        };
        try {
			UDPThreadNew.this.udpProcess = new ProcessBuilder(new String[0]).command(strArr).redirectErrorStream(true).start();
            UDPThreadNew.this.out = new UDPStreamGobbler(this.udpProcess.getInputStream(), onResultListener);
			UDPThreadNew.this.err = new UDPStreamGobbler(this.udpProcess.getErrorStream(), onResultListener);
			UDPThreadNew.this.out.setInterrupted(false);
			UDPThreadNew.this.out.start();
			UDPThreadNew.this.err.setInterrupted(false);
			UDPThreadNew.this.err.start();
			UDPThreadNew.this.udpProcess.waitFor();
        } catch (Exception e5) {
            SkStatus.logInfo(e5.toString());
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



    @Override
    public void interrupt() {
        UDPStreamGobbler uDPStreamGobbler = this.out;
        if (uDPStreamGobbler != null) {
            uDPStreamGobbler.setInterrupted(true);
            UDPThreadNew.this.out.interrupt();
			UDPThreadNew.this.out = null;
        }
        UDPStreamGobbler uDPStreamGobbler2 = this.err;
        if (uDPStreamGobbler2 != null) {
            uDPStreamGobbler2.setInterrupted(true);
			UDPThreadNew.this.err.interrupt();
			UDPThreadNew.this.err = null;
        }
        Process process = this.udpProcess;
        if (process != null) {
            process.destroy();
			UDPThreadNew.this.udpProcess = null;
        }
        super.interrupt();
    }

    public void stopVudp() {
        new Thread(() -> {
            SkStatus.logInfo("Stopping UDP....");
            interrupt();
            while (true) {
                try {
                    Thread.sleep(2000);
                    TunnelManagerHelper.stopSocksHttp(context);
                }
                catch (InterruptedException ex) {
                    continue;
                }
                break;
            }
        }).start();
    }

}
