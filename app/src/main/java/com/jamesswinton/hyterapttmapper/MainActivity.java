package com.jamesswinton.hyterapttmapper;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    // Debugging
    private static final String TAG = "MainActivity";

    // Hytera Service Values
    private static final String HYTERA_KEY_SERVICE
            = "com.meigsmart.meigkeyaccessibility";
    private static final String HYTERA_KEY_CLASS
            = "com.meigsmart.meigkeyaccessibility.KeyAccessibilityService";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Start Our Service
        startService(new Intent(this, PTTMapperService.class));

        // Start Hytera Service
        startHyteraService();

        // End Activity
        finish();
    }

    private void startHyteraService() {
        Intent intent = new Intent();
        intent.putExtra("packageName", HYTERA_KEY_SERVICE);
        intent.putExtra("className", HYTERA_KEY_CLASS);
        intent.setAction("com.android.start_poc_service");
        sendBroadcast(intent);
    }
}
