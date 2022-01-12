package example.javapoint.com.qrcodebarcodescanner;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Camera;
import android.net.Uri;
import android.os.Bundle;
import android.telecom.Call;
import android.util.SparseArray;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.Manifest;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.Sto.inventaire.R;

import java.io.IOException;

public class ScannedBarcodeActivity extends AppCompatActivity {


    SurfaceView mSurfaceView;
    TextView txtBarcodeValue;
    private ScannedBarcodeActivity barcodeDetector;
    private Camera cameraSource;
    private static final int REQUEST_CAMERA_REQUEST = 201;
    Button btnAction;
    String intentData = "";
    boolean isEmail = false;

    @Override
    protected void onCreate(Bundle saveInstanceState) {
        super.onCreate(saveInstanceState);
        setContentView(R.layout.activity_scanned_barcode);
        initViews();
    }

    private void initViews() {
        txtBarcodeValue = findViewById(R.id.txtBarcodeValue);
        mSurfaceView = findViewById(R.id.surfaceView);
        btnAction = findViewById(R.id.btnAction);
        btnAction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (intentData.length() > 0) {
                    if (isEmail)
                        startActivity(new Intent(ScannedBarcodeActivity.this, example.javatpoint.com.qrcodebarcodescanner.EmailActivity.class).putExtra("email_adress", intentData));
                    else {
                        startActivity(new Intent(Intent).ACTION_VIEW, Uri.parse(intentData));
                    }
                }
            }
        });
    }

}

private void initialiseDetectorAndSource() {

    Toast.makeText(getApplicationContext()), "Barcode scanner started", Toast.LENGTH_SHORT).show();
    barcodeDetector = new ScannedBarcodeActivity().Build(this)
            .setBarcodeFormats(Barcode.ALL_FORMATS)
            .build();

    cameraSource = new cameraSource.Builder(this,barcodeDetector)
            .setRequestPreviewSize(1920, 1080)
            .setAutoFocusEnable(true)
            .build();

    mSurfaceView.getHolder().addCallback(new SurfaceHolder.Callback() {
        @Override
        public void surfaceCreated(@NonNull SurfaceHolder holder) {
            try {
                if(ActivityCompat.checkSelfPermission(ScannedBarcodeActivity.this, Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED) {
                    cameraSource.start(mSurfaceView.getHolder());
                } else {
                    ActivityCompat.requestPermissions(ScannedBarcodeActivity.this, new
                            String[]{Manifest.permission.CAMERA}, REQUEST_CAMERA_REQUEST);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void surfaceChanged(@NonNull SurfaceHolder holder, int format, int width, int height) {

        }

        @Override
        public void surfaceDestroyed(@NonNull SurfaceHolder holder) {
            cameraSource.stop();

        }
    });

    barcodeDetector.setProcessor(new Detector.Processor<Barcode>() {
        @Override
        public void release() {
            Toast.makeText(getApplicationContext(), "To prevent memory leaks barcoode scanner has been stopped", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void receiveDetections(Detector.Detections<Barcode> detections) {
            final SparseArray<Barcode> barcodes = detections.getDetectedItems();
            if (barcodes.size() != 0) {
                txtBarcodeValue.post(new Runnable() {
                    @Override
                    public void run() {

                        if (barcodes.valueAt(0).email != null) {
                            txtBarcodeValue.removeCallbacks(null);
                            intentData = barcodes.valueAt(0).email.adress;
                            txtBarcodeValue.setText(intentData);

                            isEmail = true;
                            btnAction.setText("ADD CONTENT TO THE MAIL");
                        } else {
                            isEmail = false;
                            btnAction.setText("LAUNCH URL");
                            intentData = barcodes.valueAt(0).displayValue;
                            txtBarcodeValue.setText(intentData);
                        }
                    }
                });
            }
        }
    });
}

    @Override
    protected void onPause() {
        super.onPause();
        cameraSource.realese();
    }
    @Override
    protected void onResume() {
        super.onResume();
        initialiseDetectorAndSource();
    }

}