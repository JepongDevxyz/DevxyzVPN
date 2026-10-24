package com.slipkprojects.sockshttp.activities;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import xyz.devxyz.vpn.R;

/**
 * Jepong VPS services screen.
 * Source of truth: ~/workspace/vps/vpn-server-ubuntu.sh (18 steps, Oct 2026).
 * Ports below are copied from that installer, not guessed.
 * - "Sa DevxyzVPN mismo" = TriNet tunnel engine already in this app.
 * - "Labas na client" = the app cannot natively speak that protocol, so it
 *   copies the exact config and opens the official client instead of faking
 *   an in-app connection.
 * - "System VPN" = Android Settings VPN, not an in-app tunnel.
 * No real domain or credential is hard-coded: the user types her own domain.
 */
public class JepongVpnActivity extends BaseActivity {
    private EditText domainEdit, nsEdit;
    private LinearLayout list;
    private SharedPreferences prefs;

    private static class Svc {
        String name, detail, mode, pkg, pkgLabel;
        Svc(String n, String d, String m, String p, String pl) { name=n; detail=d; mode=m; pkg=p; pkgLabel=pl; }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jepong_vpn);
        prefs = getSharedPreferences("jepong_vpn", MODE_PRIVATE);
        domainEdit = findViewById(R.id.jepong_domain);
        nsEdit = findViewById(R.id.jepong_ns);
        list = findViewById(R.id.jepong_list);
        domainEdit.setText(prefs.getString("domain", ""));
        nsEdit.setText(prefs.getString("ns_domain", ""));
        Button save = findViewById(R.id.jepong_save);
        save.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                prefs.edit().putString("domain", domainEdit.getText().toString().trim())
                        .putString("ns_domain", nsEdit.getText().toString().trim()).apply();
                render();
                Toast.makeText(JepongVpnActivity.this, "Na-save ang domain.", Toast.LENGTH_SHORT).show();
            }
        });
        render();
    }

    private String dom() {
        String d = prefs.getString("domain", "").trim();
        return d.isEmpty() ? "ILAGAY-ANG-DOMAIN-MO" : d;
    }
    private String ns() {
        String d = prefs.getString("ns_domain", "").trim();
        return d.isEmpty() ? "ILAGAY-ANG-NS-DOMAIN-MO" : d;
    }

    private void render() {
        list.removeAllViews();
        String D = dom(), N = ns();
        Svc[] svcs = new Svc[] {
            new Svc("1. SSH — OpenSSH", "Port 22/tcp. SSH user mula sa vpn-adduser.", "Sa DevxyzVPN mismo (SSH Direct)", null, null),
            new Svc("2. SSH — Dropbear", "Port 109 at 143/tcp. Kaparehong SSH user.", "Sa DevxyzVPN mismo (SSH Direct)", null, null),
            new Svc("3. OpenVPN UDP", "Port 1194/udp. Client file: /root/jepong.ovpn (galing sa server).", "Labas na client: OpenVPN Connect", "net.openvpn.openvpn", "OpenVPN Connect"),
            new Svc("4. OpenVPN TCP", "Port 1194/tcp. Parehong PKI ng UDP (server-tcp.conf).", "Labas na client: OpenVPN Connect", "net.openvpn.openvpn", "OpenVPN Connect"),
            new Svc("5. Xray VLESS WS", "443 TLS, path /vless. Link: vless://UUID@" + D + ":443?path=%2Fvless&security=tls&encryption=none&type=ws#" + D + "-vless (UUID galing sa /etc/jepongvpn/xray-creds.txt).", "Labas na client: v2rayNG", "com.v2ray.ang", "v2rayNG"),
            new Svc("6. Xray VMess WS", "443 TLS, path /vmess. UUID galing sa xray-creds.txt, alterId 0.", "Labas na client: v2rayNG", "com.v2ray.ang", "v2rayNG"),
            new Svc("7. Xray Trojan WS", "443 TLS, path /trojan. Link: trojan://PASSWORD@" + D + ":443?path=%2Ftrojan&security=tls&type=ws (password galing sa xray-creds.txt).", "Labas na client: v2rayNG", "com.v2ray.ang", "v2rayNG"),
            new Svc("8. Xray VLESS gRPC", "443 TLS, nginx location /grpc, serviceName grpc (inbound 10004 sa server). UUID = VLESS UUID.", "Labas na client: v2rayNG", "com.v2ray.ang", "v2rayNG"),
            new Svc("9. Xray VLESS XHTTP", "443 TLS, path /xhttp (inbound 10005 sa server). UUID = VLESS UUID.", "Labas na client: v2rayNG", "com.v2ray.ang", "v2rayNG"),
            new Svc("10. SSH over WebSocket", "ws://" + D + ":8080 -> SSH 127.0.0.1:22. Hindi ito tunay na WS sa TriNet engine; gamitin ang SSH Proxy payload mode dito, o HTTP Custom/KPN Tunnel sa labas.", "Sa DevxyzVPN: SSH Proxy payload / Labas: HTTP Custom", null, null),
            new Svc("11. SlowDNS (dnstt)", "UDP 53. NS domain: " + N + ". Kailangan: NS domain + SlowDNS public key (mula sa server) + SSH user.", "Sa DevxyzVPN mismo (SlowDNS mode)", null, null),
            new Svc("12. UDP Custom (udp_custom)", "Port 36712/udp, range 25000-26000 (redirect sa 36712). PSK mula sa /etc/jepongvpn/udp-psk.txt. PAALALA: hindi pa napatunayang tugma ang TriNet libudp sa udp_custom ng NNdroid — kung ayaw kumonekta, Stun/udp_custom client ang gamitin.", "Sa DevxyzVPN mismo (UDP mode) — subukan muna", null, null),
            new Svc("13. WireGuard", "UDP 51820. Client config: /etc/jepongvpn/wireguard-client.conf (galing sa server).", "Labas na client: WireGuard", "com.wireguard.android", "WireGuard"),
            new Svc("14. Shadowsocks", "Port 8388/tcp+udp, method aes-256-gcm. Password mula sa /etc/jepongvpn/shadowsocks-pass.txt.", "Labas na client: Shadowsocks", "com.github.shadowsocks", "Shadowsocks"),
            new Svc("15. Hysteria2", "UDP 4443. Password mula sa /etc/jepongvpn/hysteria-pass.txt.", "Labas na client: v2rayNG / NekoBox", "com.v2ray.ang", "v2rayNG"),
            new Svc("16. L2TP/IPSec", "UDP 500, 4500, 1701. PSK + user/pass mula sa /etc/jepongvpn/l2tp-creds.txt.", "System VPN (Android Settings > VPN)", null, null),
            new Svc("17. IKEv2 (EAP)", "UDP 500, 4500. EAP-MSCHAPv2 user/pass mula sa /etc/jepongvpn/ikev2-creds.txt.", "System VPN / StrongSwan app", "org.strongswan.android", "strongSwan"),
            new Svc("18. PPTP (luma)", "TCP 1723. User/pass mula sa /etc/jepongvpn/pptp-creds.txt. PAALALA: tinanggal na ang built-in PPTP sa Android 12+; lumang phone lang ang may System PPTP.", "System VPN (lumang Android lang)", null, null),
        };
        for (final Svc s : svcs) {
            TextView title = new TextView(this);
            title.setText(s.name + "\n" + s.mode);
            title.setTextSize(16);
            title.setPadding(0, 24, 0, 4);
            TextView detail = new TextView(this);
            detail.setText(s.detail);
            detail.setPadding(0, 0, 0, 8);
            list.addView(title); list.addView(detail);
            LinearLayout row = new LinearLayout(this);
            row.setOrientation(LinearLayout.HORIZONTAL);
            Button copy = new Button(this); copy.setText("Kopyahin");
            copy.setOnClickListener(new View.OnClickListener() {
                @Override public void onClick(View v) {
                    ClipboardManager cm = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                    cm.setPrimaryClip(ClipData.newPlainText(s.name, s.name + "\n" + s.detail));
                    Toast.makeText(JepongVpnActivity.this, "Kinopya: " + s.name, Toast.LENGTH_SHORT).show();
                }
            });
            row.addView(copy);
            if (s.pkg != null) {
                Button open = new Button(this); open.setText("Buksan: " + s.pkgLabel);
                open.setOnClickListener(new View.OnClickListener() {
                    @Override public void onClick(View v) {
                        Intent i = getPackageManager().getLaunchIntentForPackage(s.pkg);
                        if (i != null) startActivity(i);
                        else {
                            Toast.makeText(JepongVpnActivity.this, "Hindi naka-install: " + s.pkgLabel + " — i-install muna sa Play Store.", Toast.LENGTH_LONG).show();
                            try { startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + s.pkg))); } catch (Exception e) { }
                        }
                    }
                });
                row.addView(open);
            }
            list.addView(row);
        }
        TextView note = new TextView(this);
        note.setText("Tandaan: walang totoong domain o credential na naka-hardcode dito. Ang mga password/UUID/PSK ay nasa server lang (/etc/jepongvpn/) at sa installer summary — ilagay mo ang sa iyo, huwag ipasa sa iba.");
        note.setPadding(0, 32, 0, 0);
        list.addView(note);
    }
}
