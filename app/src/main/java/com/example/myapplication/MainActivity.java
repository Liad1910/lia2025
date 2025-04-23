package com.example.myapplication;

import android.Manifest;
import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.IntentSenderRequest;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.google.mlkit.vision.documentscanner.DocumentPage;
import com.google.mlkit.vision.documentscanner.GmsDocumentScannerOptions;
import com.google.mlkit.vision.documentscanner.GmsDocumentScanning;
import com.google.mlkit.vision.documentscanner.GmsDocumentScanningResult;
import com.google.mlkit.vision.documentscanner.GmsDocumentScanner;

public class MainActivity extends AppCompatActivity {
    private static final int START_GAME = 222;
    private static final int TERMS_REQUEST_CODE = 1;
    private static final int PERMISSION_REQUEST_CODE = 100;

    Button button1, button2, linear, boy, game, taknonButton, showDialogButton, scanButton;
    ConstraintLayout constraintLayout;
    public static MediaPlayer mediaPlayer;
    public static boolean isMusicPlaying = true;
    private SharedPreferences sharedPreferences;
    private ActivityResultLauncher<IntentSenderRequest> scannerLauncher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sharedPreferences = getSharedPreferences("AppPrefs", MODE_PRIVATE);
        isMusicPlaying = sharedPreferences.getBoolean("music_state", true);

        constraintLayout = findViewById(R.id.constraintLayout);
        game = findViewById(R.id.game);
        taknonButton = findViewById(R.id.startButton);
        linear = findViewById(R.id.linear);
        boy = findViewById(R.id.boy);
        showDialogButton = findViewById(R.id.showDialogButton);
        scanButton = findViewById(R.id.scanButton);

        game.setEnabled(false);
        checkPermissions();

        taknonButton.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, activity_taknon.class);
            startActivityForResult(intent, TERMS_REQUEST_CODE);
            game.setEnabled(true);
        });

        setupMediaPlayer();
        if (isMusicPlaying) {
            mediaPlayer.start();
        }

        showDialogButton.setOnClickListener(view -> showCustomDialog());
        initViews();
        setupScanner();
    }

    private void setupScanner() {
        scannerLauncher = registerForActivityResult(
                new ActivityResultContracts.StartIntentSenderForResult(),
                result -> {
                    if (result.getResultCode() == RESULT_OK) {
                        Intent data = result.getData();
                        GmsDocumentScanningResult scanningResult = GmsDocumentScanningResult.fromActivityResultIntent(data);

                        if (scanningResult.getPages() != null) {
                            for (DocumentPage page : scanningResult.getPages()) {
                                Uri imageUri = page.getImageUri();
                                Toast.makeText(this, "תמונה נסרקה: " + imageUri.toString(), Toast.LENGTH_SHORT).show();
                            }
                        }

                        if (scanningResult.getPdf() != null) {
                            Uri pdfUri = scanningResult.getPdf().getUri();
                            int pageCount = scanningResult.getPdf().getPageCount();
                            Toast.makeText(this, "PDF עם " + pageCount + " עמודים", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(this, "סריקה בוטלה", Toast.LENGTH_SHORT).show();
                    }
                });

        scanButton.setOnClickListener(v -> {
            GmsDocumentScannerOptions options = new GmsDocumentScannerOptions.Builder()
                    .setGalleryImportAllowed(false)
                    .setPageLimit(5)
                    .setResultFormats(
                            GmsDocumentScannerOptions.RESULT_FORMAT_JPEG,
                            GmsDocumentScannerOptions.RESULT_FORMAT_PDF)
                    .setScannerMode(GmsDocumentScannerOptions.SCANNER_MODE_FULL)
                    .build();

            GmsDocumentScanner scanner = GmsDocumentScanning.getClient(options);

            scanner.getStartScanIntent(MainActivity.this)
                    .addOnSuccessListener(intentSender -> {
                        IntentSenderRequest request = new IntentSenderRequest.Builder(intentSender).build();
                        scannerLauncher.launch(request);
                    })
                    .addOnFailureListener(e -> {
                        Toast.makeText(MainActivity.this, "שגיאה בהפעלת הסורק", Toast.LENGTH_SHORT).show();
                    });
        });
    }

    private void setupMediaPlayer() {
        if (mediaPlayer == null) {
            mediaPlayer = MediaPlayer.create(this, R.raw.background_music);
            mediaPlayer.setLooping(true);
        }
    }

    private void checkPermissions() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED ||
                ContextCompat.checkSelfPermission(this, Manifest.permission.READ_CALL_LOG) != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.READ_PHONE_STATE, Manifest.permission.READ_CALL_LOG},
                    PERMISSION_REQUEST_CODE);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == PERMISSION_REQUEST_CODE) {
            boolean allGranted = true;
            for (int result : grantResults) {
                if (result != PackageManager.PERMISSION_GRANTED) {
                    allGranted = false;
                    break;
                }
            }
            if (!allGranted) {
                Toast.makeText(this, "לא ניתן להשתמש באפליקציה ללא הרשאות מתאימות", Toast.LENGTH_LONG).show();
                finish();
            }
        }
    }

    private void initViews() {
        button1 = findViewById(R.id.button1);
        button2 = findViewById(R.id.button2);

        button1.setOnClickListener(view ->
                Toast.makeText(MainActivity.this, "you dumb🤤😋😫", Toast.LENGTH_SHORT).show());
        button2.setOnClickListener(view ->
                Toast.makeText(MainActivity.this, "YOU ARE SMART😎🤓", Toast.LENGTH_SHORT).show());

        linear.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, activity_linear.class);
            startActivity(intent);
        });

        boy.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, activity_boy.class);
            startActivity(intent);
        });

        game.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, activity_game.class);
            startActivityForResult(intent, START_GAME);
        });
    }

    private void showCustomDialog() {
        Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.activity_custom_dialog);
        dialog.setCancelable(true);

        EditText etUserName = dialog.findViewById(R.id.etUserName);
        EditText etPassword = dialog.findViewById(R.id.etPassword);
        Button btnDialogLogin = dialog.findViewById(R.id.btnDialogLogin);

        btnDialogLogin.setOnClickListener(view -> {
            String username = etUserName.getText().toString();
            String password = etPassword.getText().toString();
            Toast.makeText(MainActivity.this, "התחברת כ: " + username, Toast.LENGTH_SHORT).show();
            dialog.dismiss();
        });
        dialog.show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_home) {
            Toast.makeText(this, "עברתם לדף הראשי", Toast.LENGTH_SHORT).show();
            return true;
        } else if (id == R.id.action_page1) {
            Toast.makeText(this, "עברתם לדף אחד", Toast.LENGTH_SHORT).show();
            return true;
        } else if (id == R.id.new_item) {
            Intent intent = new Intent(this, NewActivity.class);
            startActivity(intent);
            return true;
        } else if (id == R.id.share) {
            Intent intent = new Intent(this, share.class);
            startActivity(intent);
            return true;
        } else if (id == R.id.action_settings) {
            Intent intent = new Intent(this, SettingsActivity.class);
            startActivity(intent);
            return true;
        } else {
            return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mediaPlayer != null) {
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }
}