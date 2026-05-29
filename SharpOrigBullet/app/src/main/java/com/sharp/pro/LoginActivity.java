package com.sharp.pro;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.LinearLayout;
import android.os.Build;
import android.Manifest;
import androidx.annotation.NonNull;
import android.content.pm.PackageManager;
import android.widget.Toast;
public class LoginActivity extends Activity {

    static {
        System.loadLibrary("native");
    }
    private final String USER = "USER";
    static boolean change = false;
    private Prefs prefs;
    TextView btnSignIn;
    Context ctx;
    static boolean loggedin = false;  
    static boolean hidden = true;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_login);
        prefs = Prefs.with(this);
        
        permisson(this);
        
        
        final Context m_Context = (Context) this;
        final EditText textUsername = findViewById(R.id.mail);
        textUsername.setText(prefs.read(USER, ""));
        TextView showpwd = findViewById(R.id.showpwd);
        showpwd.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (!hidden){
                    textUsername.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    hidden = true;
                    }else{
                        textUsername.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                        hidden =false;
                    }
                }
            });

        LinearLayout getKey = findViewById(R.id.getKey);
        getKey.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    Intent intent = new Intent(Intent.ACTION_VIEW);
                    intent.setData(Uri.parse(GetKey()));
                    startActivity(intent);
                }
            });

        btnSignIn = findViewById(R.id.init);
        btnSignIn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    TextView textUsername = findViewById(R.id.mail);
                    if (!textUsername.getText().toString().isEmpty()) {
                        prefs.write(USER, textUsername.getText().toString());
                        String userKey = textUsername.getText().toString().trim();
                        Auth(m_Context, userKey);
                    }
                    if (textUsername.getText().toString().isEmpty()) {
                        textUsername.setError("Please enter Licence Keys");
                    }
                    if (textUsername.getText().toString().isEmpty()) {
                        textUsername.setError("Please enter Licence Keys");
                    }
                    loggedin=true;
                }
            });
   }
    private static void Auth(final Context m_Context, final String userKey) {
        final ProgressDialog progressDialog = new ProgressDialog(m_Context, 5);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.setMessage("Please wait...");
        progressDialog.setCancelable(false);
        progressDialog.show();

        final Handler loginHandler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                if (msg.what == 0) {

                    Intent i = new Intent(m_Context.getApplicationContext(), MainActivity.class);             
                    m_Context.startActivity(i);
                } else if (msg.what == 1) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(m_Context, 5);
                    builder.setTitle("PRO - Error");
                    builder.setMessage(msg.obj.toString());
                    builder.setCancelable(false);
                    builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {                            
                                System.exit(0);
                            }
                        });
                    builder.show();
                }
                progressDialog.dismiss();
            }
        };

        new Thread(new Runnable() {
                @Override
                public void run() {
                    String result = Check(m_Context, userKey);
                    if (result.equals("OK")) {
                        loginHandler.sendEmptyMessage(0);               
                    } else {
                        Message msg = new Message();
                        msg.what = 1;
                        msg.obj = result;
                        loginHandler.sendMessage(msg);
                    }
                }
            }).start();

    }


private void permisson(Activity activity) {
        if (Build.VERSION.SDK_INT >= 23) {
            if (checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED
                    ||checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED) {
                requestPermissions(new String[] {Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1000);
            } else {
                //initializeLogic();
            }
        } else {
            //initializeLogic();
        }
    }

@Override
public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
    super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    if (requestCode == 1001) {
        boolean allGranted = true;
        for (int result : grantResults) {
            if (result != PackageManager.PERMISSION_GRANTED) {
                allGranted = false;
                break;
            }
        }

        if (!allGranted) {
            Toast.makeText(this, "All permissions Not Granted Plz restart app or Give Manual...", Toast.LENGTH_LONG).show();
            //restartApp();
        } else {
            // Continue normal flow
        }
    }
}



    private static native void Init(Context mContext);
    private static native String Check(Context mContext, String userKey);
    private native String GetKey();
}


