# DevxyzVPN

TriNet-style SSH/Socks VPN app, rebuilt as a clean AndroidX / AppCompat project
for Jepong's own VPS. Tunnel engine from the TriNetSocksVPN source she uploaded
(SSH Direct / Proxy / SSL Tunnel / PAY-SSL / SSL Remote Proxy / SlowDNS / UDP,
plus the Tun2Socks VpnService), with one new screen: **Jepong VPS Services**.

## What is new vs the uploaded TriNet source
- Package renamed `com.test.version` -> `xyz.devxyz.vpn`, app name DevxyzVPN.
- Removed the original author's keystore (`aizen_sarmiento.jks`), Firebase
  `google-services.json`, and AdMob IDs. Ads now use Google test IDs — replace
  them with Jepong's own AdMob IDs before any public release.
- Removed the dead remote-config URL (`LINK NG UPDATER RAW MO`) behaviour is
  unchanged, but the bundled `assets/config/config.json` now decrypts correctly
  with the in-code password and contains Jepong VPS placeholders, not the
  original author's (undecryptable) server list.
- New drawer item **Jepong VPS Services** (`JepongVpnActivity`): all 17
  client-facing services from `vpn-server-ubuntu.sh` (18 steps) with their
  exact ports. No domain or credential is hard-coded — type your VPS domain
  and NS domain in that screen; passwords/UUIDs/PSK stay on the server
  (`/etc/jepongvpn/`).

## Honest limits (do not claim otherwise)
- In-app: SSH (22 / Dropbear 109, 143), SSH-SSL (443), PAY-SSL, SlowDNS
  (UDP 53, dnstt), UDP mode.
- NOT in-app: WireGuard (51820), Shadowsocks (8388), Hysteria2 (4443),
  Xray VLESS/VMess/Trojan WS + gRPC + XHTTP (443), OpenVPN UDP/TCP (1194).
  The Jepong VPS screen copies the exact config/link and opens the official
  client (WireGuard, v2rayNG, OpenVPN Connect) instead of faking a connection.
- L2TP/IPSec (500, 4500, 1701) and IKEv2 (500, 4500) are Android System VPN /
  strongSwan, not in-app tunnels. PPTP (1723) is legacy only — Android 12+
  removed built-in PPTP.
- UDP mode vs the server's `udp_custom` (NNdroid) protocol is UNVERIFIED:
  TriNet's `libudp.so` may not speak it. If UDP fails, use a Stun/udp_custom
  client. Test before claiming it works.

## Build
CI: `.github/workflows/android.yml` — `./gradlew :app:assembleDebug`,
APK artifact `DevxyzVPN-debug`. Locally needs JDK 17 + Android SDK 33.
Debug signing only; no release keystore is shipped in this repo.

## AIDE note
This project uses AndroidX (`android.useAndroidX=true`, AppCompatActivity
everywhere). AIDE FCS (2023) on a phone cannot reliably build a Gradle
AndroidX project of this size — use Android Studio or the GitHub Actions
workflow above, then sideload the debug APK.
