package com.jamesswinton.hyterapttmapper;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;

public class StartOnBootReceiver extends BroadcastReceiver {

    // Hytera Service Values
    private static final String HYTERA_KEY_SERVICE
            = "com.meigsmart.meigkeyaccessibility";
    private static final String HYTERA_KEY_CLASS
            = "com.meigsmart.meigkeyaccessibility.KeyAccessibilityService";

    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction() != null &&
                intent.getAction().equals(Intent.ACTION_BOOT_COMPLETED)) {
            // Start Our Service
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                context.startForegroundService(new Intent(context, PTTMapperService.class));
            } else {
                context.startService(new Intent(context, PTTMapperService.class));
            }

            // Start Hytera Service
            Intent startHyteraServiceIntent = new Intent();
            startHyteraServiceIntent.putExtra("packageName", HYTERA_KEY_SERVICE);
            startHyteraServiceIntent.putExtra("className", HYTERA_KEY_CLASS);
            startHyteraServiceIntent.setAction("com.android.start_poc_service");
            context.sendBroadcast(startHyteraServiceIntent);
        }
    }
}
