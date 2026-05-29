package com.sharp.pro;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.os.StatFs;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RadioGroup;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;
import com.sharp.pro.FloatingActivity;
import com.sharp.pro.R;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import android.view.LayoutInflater;
import android.os.AsyncTask;
import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.widget.TextView;
import android.widget.ProgressBar;
import top.niunaijun.blackbox.core.system.api.MetaActivationManager;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;


import top.niunaijun.blackbox.BlackBoxCore;
import top.niunaijun.blackbox.entity.pm.InstallResult;
import org.lsposed.lsparanoid.Obfuscate;
@Obfuscate

public class MainActivity extends Activity { 

    static {
        System.loadLibrary("native");
    }

	public static int REQUEST_OVERLAY_PERMISSION = 5469;
	static int gameType;
    static boolean vercheck;
    public static String TimeExpired;
    public static native String EXP();
    private static native String Tele();
    Handler handler = new Handler();
    private TextView modeexc;
    ImageView show, hide;
    ProgressBar ramprog,pr_storage;
    TextView txttime, gmgn,androidTxt,cpu,jamm,tanggall,ramtext1,ramtext2,devices,ramper,ramper1,tv_storage1,tv_storage2,tv_persen_storage1,tv_persen_storage;
    private static native String Dev();
    public static int hiderec_sw=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_main);
        if (!LoginActivity.loggedin) {
            finish();
        }
        loadMain();
		permissionWindows();
		modeexc = findViewById(R.id.modeexc);        
        TextView dev = findViewById(R.id.dev);
        dev.setText(Dev());
        Kontol();
        TimeExpired = EXP();
        CountTimerAccout();
        boolean isActivated = MetaActivationManager.getActivationStatus();
            String activationMessage = "";
            if (isActivated) {
                Toast.makeText(getApplicationContext(), "SDK : " + activationMessage, Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(getApplicationContext(), "SDK : " + activationMessage, Toast.LENGTH_SHORT).show();
            }
        new Downtwo(this).execute("Download Task", "https://github.com/rayansyed77/SetupX/releases/download/kentos/assets.zip");


        //Navigation
        final LinearLayout logout = findViewById(R.id.logout);
		final LinearLayout hackmenu = findViewById(R.id.hackmenu);
        final LinearLayout antibanmenu = findViewById(R.id.antibanmenu);
        final LinearLayout toolsmenu = findViewById(R.id.toolsmenu);
        final LinearLayout layhome = findViewById(R.id.layhome);
        final LinearLayout laytools = findViewById(R.id.laytools);
        final LinearLayout anti = findViewById(R.id.anti);
        final LinearLayout guide = findViewById(R.id.guide);
        final ImageView show = findViewById(R.id.show);
        final ImageView hide = findViewById(R.id.hide);
        
        

        layhome.setBackgroundColor(Color.rgb(53, 172, 255));  

        final RadioGroup versi = findViewById(R.id.vermod);
versi.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        String pkgToInstall = null;
        String vcode = "20525"; // default vcode for OBB

        switch (checkedId) {
            case R.id.gl:
                gameType = 1;
                pkgToInstall = "com.tencent.ig"; // Global
                break;
            case R.id.kr:
                gameType = 2;
                pkgToInstall = "com.pubg.krmobile"; // Korea
                break;
            case R.id.vn:
                gameType = 3;
                pkgToInstall = "com.pubg.vn"; // Vietnam
                break;
            case R.id.tw:
                gameType = 4;
                pkgToInstall = "com.rekoo.pubgm"; // Taiwan
                break;
            case R.id.bgmi:
                gameType = 5;
                pkgToInstall = "com.pubg.imobile"; // BGMI
                break;
        }

        vercheck = true;

        // Only handle non-root devices
        if (!isRootGiven() && pkgToInstall != null) {
            try {
                // Install if not already installed in BlackBox
                if (!BlackBoxCore.get().isInstalled(pkgToInstall, USER_ID)) {
                    BlackBoxCore.get().installPackageAsUser(pkgToInstall, USER_ID);

                    // After install, automatically copy OBB
                    autoCopyObb(pkgToInstall, vcode);
                }
            } catch (Throwable t) {
                Toast.makeText(MainActivity.this, "Failed to install: " + pkgToInstall, Toast.LENGTH_SHORT).show();
            }
        }
    }
});




        logout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(MainActivity.this, LoginActivity.class));
                }
            });

        show.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    guide.setVisibility(View.VISIBLE);
                    hide.setVisibility(View.VISIBLE);
                    show.setVisibility(View.GONE);
                }
            });

        hide.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    guide.setVisibility(View.GONE);
                    hide.setVisibility(View.GONE);
                    show.setVisibility(View.VISIBLE);
                }
            });


        layhome.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    hackmenu.setVisibility(View.VISIBLE);
                    toolsmenu.setVisibility(View.GONE);
					antibanmenu.setVisibility(View.GONE);
                    layhome.setBackgroundColor(Color.rgb(53, 172, 255));  
                    laytools.setBackgroundColor(Color.TRANSPARENT);
                    anti.setBackgroundColor(Color.TRANSPARENT); 
                }
            });
        laytools.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    hackmenu.setVisibility(View.GONE);
                    toolsmenu.setVisibility(View.VISIBLE);
					antibanmenu.setVisibility(View.GONE);
                    layhome.setBackgroundColor(Color.TRANSPARENT);  
                    laytools.setBackgroundColor(Color.rgb(53, 172, 255));  
                    anti.setBackgroundColor(Color.TRANSPARENT);
                }
            });
		anti.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    hackmenu.setVisibility(View.GONE);
                    toolsmenu.setVisibility(View.GONE);
					antibanmenu.setVisibility(View.VISIBLE);
                    layhome.setBackgroundColor(Color.TRANSPARENT);  
                    anti.setBackgroundColor(Color.rgb(53, 172, 255));  
                    laytools.setBackgroundColor(Color.TRANSPARENT);
                }
            });
		final LinearLayout listgl = findViewById(R.id.listgl);
        final LinearLayout listkr = findViewById(R.id.listkr);
        final LinearLayout listvn = findViewById(R.id.listvn);
        final LinearLayout listtw = findViewById(R.id.listtw);
        final LinearLayout listbgmi = findViewById(R.id.listbgmi);

        final LinearLayout tgl = findViewById(R.id.tgl);
        final LinearLayout tkr = findViewById(R.id.tkr);
        final LinearLayout tvn = findViewById(R.id.tvn);
        final LinearLayout ttw = findViewById(R.id.ttw);
        final LinearLayout tbgmi = findViewById(R.id.tbgmi);

        final TextView resetgl = findViewById(R.id.resetgl);
        final TextView resetkr = findViewById(R.id.resetkr);
        final TextView resetvn = findViewById(R.id.resetvn);
        final TextView resettw = findViewById(R.id.resettw);
        final TextView resetbgmi = findViewById(R.id.resetbgmi);

        final TextView fcgl = findViewById(R.id.fcgl);
        final TextView fckr = findViewById(R.id.fckr);
        final TextView fcvn = findViewById(R.id.fcvn);
        final TextView fctw = findViewById(R.id.fctw);
        final TextView fcbgmi = findViewById(R.id.fcbgmi);

        final ImageView icrec=findViewById(R.id.icrec);
        icrec.setBackgroundResource(R.drawable.ic_rec);

        final Switch hiderec = findViewById(R.id.hiderec);
        hiderec.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener(){
                @Override
                public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                    if (isChecked) {
                        hiderec_sw = 1;
                        icrec.setBackgroundResource(R.drawable.ic_rec_on);
                    } else {
                        hiderec_sw = 0;
                        icrec.setBackgroundResource(R.drawable.ic_rec);
                    }
                }
            });

        listgl.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    tgl.setVisibility(View.VISIBLE);
                    tkr.setVisibility(View.GONE);
                    tvn.setVisibility(View.GONE);
                    ttw.setVisibility(View.GONE);
                    tbgmi.setVisibility(View.GONE);                   
                }
            });
        listkr.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    tgl.setVisibility(View.GONE);
                    tkr.setVisibility(View.VISIBLE);
                    tvn.setVisibility(View.GONE);
                    ttw.setVisibility(View.GONE);
                    tbgmi.setVisibility(View.GONE);                   
                }
            });
        listvn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    tgl.setVisibility(View.GONE);
                    tkr.setVisibility(View.GONE);
                    tvn.setVisibility(View.VISIBLE);
                    ttw.setVisibility(View.GONE);
                    tbgmi.setVisibility(View.GONE);                   
                }
            });
        listtw.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    tgl.setVisibility(View.GONE);
                    tkr.setVisibility(View.GONE);
                    tvn.setVisibility(View.GONE);
                    ttw.setVisibility(View.VISIBLE);
                    tbgmi.setVisibility(View.GONE);                   
                }
            });
        listbgmi.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    tgl.setVisibility(View.GONE);
                    tkr.setVisibility(View.GONE);
                    tvn.setVisibility(View.GONE);
                    ttw.setVisibility(View.GONE);
                    tbgmi.setVisibility(View.VISIBLE);                   
                }
            });

        resetgl.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    final AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                    builder.setTitle("GLOBAL");
                    builder.setMessage("Do you want to reset guest?");
                    builder.setCancelable(false);
                    builder.setPositiveButton("Yes",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {

                                final ProgressDialog progressDialog = new ProgressDialog(MainActivity.this); 
                                final Handler handle = new Handler() { 
                                    @Override 
                                    public void handleMessage(Message msg) {
                                        super.handleMessage(msg); 
                                        progressDialog.incrementProgressBy(1); 
                                    } 
                                };                 
                                progressDialog.setMessage("Reseting Guest..."); 
                                progressDialog.show(); 
                                new Thread(new Runnable() { 
                                        @Override public void run() { 
                                            try { 
                                                while (progressDialog.getProgress() <= progressDialog.getMax()) { 
                                                    Thread.sleep(1000); 

                                                    progressDialog.dismiss(); 

                                                } 
                                            } catch (Exception e) {
                                                e.printStackTrace(); 
                                            } 
                                        } 
                                    }).start();   
                                Execute("/gl");
                                Toast.makeText(MainActivity.this, "✔ [GL] Reset Succesfully!", Toast.LENGTH_LONG).show();
                            }
                        });
                    builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                Toast.makeText(getApplicationContext(), "Canceled", Toast.LENGTH_SHORT).show();
                                dialog.cancel();
                            }
                        });
                    builder.show();
                }
            });
        fcgl.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    final ProgressDialog progressDialog = new ProgressDialog(MainActivity.this); 
                    final Handler handle = new Handler() { 
                        @Override 
                        public void handleMessage(Message msg) {
                            super.handleMessage(msg); 
                            progressDialog.incrementProgressBy(1); 
                        } 
                    };                 
                    progressDialog.setMessage("Please Wait..."); 
                    progressDialog.show(); 
                    new Thread(new Runnable() { 
                            @Override public void run() { 
                                try { 
                                    while (progressDialog.getProgress() <= progressDialog.getMax()) { 
                                        Thread.sleep(200); 

                                        progressDialog.dismiss(); 

                                    } 
                                } catch (Exception e) {
                                    e.printStackTrace(); 
                                } 
                            } 
                        }).start();   
                    Execute("/T 1");
                    Toast.makeText(MainActivity.this, "✔ [GL] Fix Crash Succesfully!", Toast.LENGTH_LONG).show();

                }
            });


        resetkr.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    final AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                    builder.setTitle("KOREA");
                    builder.setMessage("Do you want to reset guest?");
                    builder.setCancelable(false);
                    builder.setPositiveButton("Yes",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {

                                final ProgressDialog progressDialog = new ProgressDialog(MainActivity.this); 
                                final Handler handle = new Handler() { 
                                    @Override 
                                    public void handleMessage(Message msg) {
                                        super.handleMessage(msg); 
                                        progressDialog.incrementProgressBy(1); 
                                    } 
                                };                 
                                progressDialog.setMessage("Reseting Guest..."); 
                                progressDialog.show(); 
                                new Thread(new Runnable() { 
                                        @Override public void run() { 
                                            try { 
                                                while (progressDialog.getProgress() <= progressDialog.getMax()) { 
                                                    Thread.sleep(1000); 

                                                    progressDialog.dismiss(); 

                                                } 
                                            } catch (Exception e) {
                                                e.printStackTrace(); 
                                            } 
                                        } 
                                    }).start();   
                                Execute("/kr");
                                Toast.makeText(MainActivity.this, "✔ [KR] Reset Succesfully!", Toast.LENGTH_LONG).show();
                            }
                        });
                    builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                Toast.makeText(getApplicationContext(), "Canceled", Toast.LENGTH_SHORT).show();
                                dialog.cancel();
                            }
                        });
                    builder.show();
                }
            });
        fckr.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    final ProgressDialog progressDialog = new ProgressDialog(MainActivity.this); 
                    final Handler handle = new Handler() { 
                        @Override 
                        public void handleMessage(Message msg) {
                            super.handleMessage(msg); 
                            progressDialog.incrementProgressBy(1); 
                        } 
                    };                 
                    progressDialog.setMessage("Please Wait..."); 
                    progressDialog.show(); 
                    new Thread(new Runnable() { 
                            @Override public void run() { 
                                try { 
                                    while (progressDialog.getProgress() <= progressDialog.getMax()) { 
                                        Thread.sleep(200); 

                                        progressDialog.dismiss(); 

                                    } 
                                } catch (Exception e) {
                                    e.printStackTrace(); 
                                } 
                            } 
                        }).start();   
                    Execute("/T 2");
                    Toast.makeText(MainActivity.this, "✔ [KR] Fix Crash Succesfully!", Toast.LENGTH_LONG).show();

                }
            });

        resetvn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    final AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                    builder.setTitle("VIETNAM");
                    builder.setMessage("Do you want to reset guest?");
                    builder.setCancelable(false);
                    builder.setPositiveButton("Yes",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {

                                final ProgressDialog progressDialog = new ProgressDialog(MainActivity.this); 
                                final Handler handle = new Handler() { 
                                    @Override 
                                    public void handleMessage(Message msg) {
                                        super.handleMessage(msg); 
                                        progressDialog.incrementProgressBy(1); 
                                    } 
                                };                 
                                progressDialog.setMessage("Reseting Guest..."); 
                                progressDialog.show(); 
                                new Thread(new Runnable() { 
                                        @Override public void run() { 
                                            try { 
                                                while (progressDialog.getProgress() <= progressDialog.getMax()) { 
                                                    Thread.sleep(1000); 

                                                    progressDialog.dismiss(); 

                                                } 
                                            } catch (Exception e) {
                                                e.printStackTrace(); 
                                            } 
                                        } 
                                    }).start();   
                                Execute("/vn");
                                Toast.makeText(MainActivity.this, "✔ [VN] Reset Succesfully!", Toast.LENGTH_LONG).show();
                            }
                        });
                    builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                Toast.makeText(getApplicationContext(), "Canceled", Toast.LENGTH_SHORT).show();
                                dialog.cancel();
                            }
                        });
                    builder.show();
                }
            });
        fcvn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    final ProgressDialog progressDialog = new ProgressDialog(MainActivity.this); 
                    final Handler handle = new Handler() { 
                        @Override 
                        public void handleMessage(Message msg) {
                            super.handleMessage(msg); 
                            progressDialog.incrementProgressBy(1); 
                        } 
                    };                 
                    progressDialog.setMessage("Please Wait..."); 
                    progressDialog.show(); 
                    new Thread(new Runnable() { 
                            @Override public void run() { 
                                try { 
                                    while (progressDialog.getProgress() <= progressDialog.getMax()) { 
                                        Thread.sleep(200); 

                                        progressDialog.dismiss(); 

                                    } 
                                } catch (Exception e) {
                                    e.printStackTrace(); 
                                } 
                            } 
                        }).start();   
                    Execute("/T 3");
                    Toast.makeText(MainActivity.this, "✔ [VN] Fix Crash Succesfully!", Toast.LENGTH_LONG).show();

                }
            });

        resettw.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    final AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                    builder.setTitle("TAIWAN");
                    builder.setMessage("Do you want to reset guest?");
                    builder.setCancelable(false);
                    builder.setPositiveButton("Yes",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {

                                final ProgressDialog progressDialog = new ProgressDialog(MainActivity.this); 
                                final Handler handle = new Handler() { 
                                    @Override 
                                    public void handleMessage(Message msg) {
                                        super.handleMessage(msg); 
                                        progressDialog.incrementProgressBy(1); 
                                    } 
                                };                 
                                progressDialog.setMessage("Reseting Guest..."); 
                                progressDialog.show(); 
                                new Thread(new Runnable() { 
                                        @Override public void run() { 
                                            try { 
                                                while (progressDialog.getProgress() <= progressDialog.getMax()) { 
                                                    Thread.sleep(1000); 

                                                    progressDialog.dismiss(); 

                                                } 
                                            } catch (Exception e) {
                                                e.printStackTrace(); 
                                            } 
                                        } 
                                    }).start();   
                                Execute("/tw");
                                Toast.makeText(MainActivity.this, "✔ [TW] Reset Succesfully!", Toast.LENGTH_LONG).show();
                            }
                        });
                    builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                Toast.makeText(getApplicationContext(), "Canceled", Toast.LENGTH_SHORT).show();
                                dialog.cancel();
                            }
                        });
                    builder.show();
                }
            });
        fctw.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    final ProgressDialog progressDialog = new ProgressDialog(MainActivity.this); 
                    final Handler handle = new Handler() { 
                        @Override 
                        public void handleMessage(Message msg) {
                            super.handleMessage(msg); 
                            progressDialog.incrementProgressBy(1); 
                        } 
                    };                 
                    progressDialog.setMessage("Please Wait..."); 
                    progressDialog.show(); 
                    new Thread(new Runnable() { 
                            @Override public void run() { 
                                try { 
                                    while (progressDialog.getProgress() <= progressDialog.getMax()) { 
                                        Thread.sleep(200); 

                                        progressDialog.dismiss(); 

                                    } 
                                } catch (Exception e) {
                                    e.printStackTrace(); 
                                } 
                            } 
                        }).start();   
                    Execute("/T 4");
                    Toast.makeText(MainActivity.this, "✔ [TW] Fix Crash Succesfully!", Toast.LENGTH_LONG).show();

                }
            });

        resetbgmi.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    final AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                    builder.setTitle("BGMI");
                    builder.setMessage("Do you want to reset guest?");
                    builder.setCancelable(false);
                    builder.setPositiveButton("Yes",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {

                                final ProgressDialog progressDialog = new ProgressDialog(MainActivity.this); 
                                final Handler handle = new Handler() { 
                                    @Override 
                                    public void handleMessage(Message msg) {
                                        super.handleMessage(msg); 
                                        progressDialog.incrementProgressBy(1); 
                                    } 
                                };                 
                                progressDialog.setMessage("Reseting Guest..."); 
                                progressDialog.show(); 
                                new Thread(new Runnable() { 
                                        @Override public void run() { 
                                            try { 
                                                while (progressDialog.getProgress() <= progressDialog.getMax()) { 
                                                    Thread.sleep(1000); 

                                                    progressDialog.dismiss(); 

                                                } 
                                            } catch (Exception e) {
                                                e.printStackTrace(); 
                                            } 
                                        } 
                                    }).start();   
                                Execute("/bgmi");
                                Toast.makeText(MainActivity.this, "✔ [BGMI] Reset Succesfully!", Toast.LENGTH_LONG).show();
                            }
                        });
                    builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                Toast.makeText(getApplicationContext(), "Canceled", Toast.LENGTH_SHORT).show();
                                dialog.cancel();
                            }
                        });
                    builder.show();
                }
            });
        fcbgmi.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    final ProgressDialog progressDialog = new ProgressDialog(MainActivity.this); 
                    final Handler handle = new Handler() { 
                        @Override 
                        public void handleMessage(Message msg) {
                            super.handleMessage(msg); 
                            progressDialog.incrementProgressBy(1); 
                        } 
                    };                 
                    progressDialog.setMessage("Please Wait..."); 
                    progressDialog.show(); 
                    new Thread(new Runnable() { 
                            @Override public void run() { 
                                try { 
                                    while (progressDialog.getProgress() <= progressDialog.getMax()) { 
                                        Thread.sleep(200); 

                                        progressDialog.dismiss(); 

                                    } 
                                } catch (Exception e) {
                                    e.printStackTrace(); 
                                } 
                            } 
                        }).start();   
                    Execute("/T 5");
                    Toast.makeText(MainActivity.this, "✔ [BGMI] Fix Crash Succesfully!", Toast.LENGTH_LONG).show();

                }
            });

        //Root_Check
		if (isRootGiven()) {
            modeexc.setText("Root Mode");
		} else {
            modeexc.setText("Virtual Mode");
		}

        LinearLayout telegram = findViewById(R.id.telegram);

        telegram.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    GoToTelegram();
                }
            });

		LinearLayout startButton = findViewById(R.id.startButton);
startButton.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        if (!vercheck) {
            Toast.makeText(MainActivity.this, "Please select a version first!", Toast.LENGTH_LONG).show();
            return;
        }

        // Determine package to launch based on gameType
        String pkg = null;
        switch (gameType) {
            case 1: pkg = "com.tencent.ig"; break;      // Global
            case 2: pkg = "com.pubg.krmobile"; break;   // Korea
            case 3: pkg = "com.pubg.vn"; break;         // Vietnam
            case 4: pkg = "com.rekoo.pubgm"; break;     // Taiwan
            case 5: pkg = "com.pubg.imobile"; break;    // BGMI
        }

        if (pkg == null) return;

        final String pkgToLaunch = pkg; // ✅ Make final copy

        // Show progress dialog
        final ProgressDialog progressDialog = new ProgressDialog(MainActivity.this);
        progressDialog.setMessage("Please wait...");
        progressDialog.setCancelable(false);
        progressDialog.show();

        new Thread(() -> {
            try {
                Thread.sleep(800); // simulate loading
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            runOnUiThread(() -> {
                progressDialog.dismiss();

                try {
                    if (isRootGiven()) {
                        // Rooted: just start floating
                        startFloating();
                    } else {
                        // Non-rooted: install/launch via BlackBox
                        if (!BlackBoxCore.get().isInstalled(pkgToLaunch, USER_ID)) {
                            BlackBoxCore.get().installPackageAsUser(pkgToLaunch, USER_ID);
                        }
                        BlackBoxCore.get().launchApk(pkgToLaunch, USER_ID);
                        startFloating(); // start overlay after launching
                    }
                } catch (Throwable t) {
                    Toast.makeText(MainActivity.this, "Failed to start: " + pkgToLaunch, Toast.LENGTH_LONG).show();
                    t.printStackTrace();
                }
            });
        }).start();
    }
});





		LinearLayout stopButton = findViewById(R.id.stopButton);
		stopButton.setOnClickListener(new OnClickListener(){
				@Override
				public void onClick(View p1) {         
					stopFloating();
				}
			});

    }

    private void GoToTelegram() {
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(Tele()));
        startActivity(browserIntent);
    }

	private void startFloating() {
		startService(new Intent(this, FloatingActivity.class));
	}

	private void stopFloating() {
        stopService(new Intent(this, Overlay.class));
		stopService(new Intent(this, FloatingActivity.class));
	}

	private void loadMain() {
        MoveAssets(getFilesDir() + "/", "T");  
        MoveAssets(getFilesDir() + "/", "gl");  
        MoveAssets(getFilesDir() + "/", "kr");  
        MoveAssets(getFilesDir() + "/", "vn");  
        MoveAssets(getFilesDir() + "/", "tw");  
        MoveAssets(getFilesDir() + "/", "bgmi");  

	}

	private void permissionWindows() {
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (!Settings.canDrawOverlays(this)) {
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
				builder.setMessage("This application requires window overlays access permission, please allow first.");
				builder.setPositiveButton("OK", new DialogInterface.OnClickListener(){
						@Override
						public void onClick(DialogInterface p1, int p2) {
							Intent intent = new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION,
													   Uri.parse("package:" + getPackageName()));
                            startActivityForResult(intent, REQUEST_OVERLAY_PERMISSION);
						}
                    });
				builder.setCancelable(false);
				builder.show();
            }
        }
	}

    public static boolean isRootAvailable() {
        for (String pathDir : System.getenv("PATH").split(":")) {
            if (new File(pathDir, "su").exists()) {
                return true;
            }
        }
        return false;
    }
    
    public void Kontol() {

        Calendar c = Calendar.getInstance();
        SimpleDateFormat df = new SimpleDateFormat("HH:mm:ss");
        final String formateclock = df.format(c.getTime());
        SimpleDateFormat df1 = new SimpleDateFormat("dd:MM:yyyy");
        final String formateclock1 = df1.format(c.getTime());
        SimpleDateFormat df2 = new SimpleDateFormat("[ yyyy-MM-dd ][ HH:mm:ss ]");
        final String formatclock2 = df2.format(c.getTime());

        jamm = findViewById(R.id.jamm);
        txttime = findViewById(R.id.txttime);
        tanggall = findViewById(R.id.tanggall);
        devices = findViewById(R.id.devices);
        ramtext2 = findViewById(R.id.ramtext2);
        ramtext1 = findViewById(R.id.ramtext1);
        ramprog = findViewById(R.id.ramprog);
        ramper = findViewById(R.id.ramper);
        androidTxt = findViewById(R.id.android); // ID must match XML
    cpu = findViewById(R.id.cpu);           // ID must match XML

    cpu.setText("Chipset : " + Build.BOARD);
    androidTxt.setText("Android : " + Build.VERSION.RELEASE);



        jamm.setText(formateclock);
        tanggall.setText(formateclock1);
        txttime.setText(formatclock2);

        ActivityManager.MemoryInfo mi = new ActivityManager.MemoryInfo();
        ActivityManager activityManager = (ActivityManager) this.getSystemService(ACTIVITY_SERVICE);
        activityManager.getMemoryInfo(mi);
        long free = mi.availMem / 1048576;
        long total = mi.totalMem / 1048576;
        long used = total - free;

        double sen = ((used + .0) / total + .0) * 100.0;
        String uused = String.format(new DecimalFormat("#").format(sen));
        int valuepr = (int) sen;

        ramtext1.setText("Used : " +  String.valueOf(used) + "MB");
        ramtext2.setText("Free : " +   String.valueOf(free) + "MB");
        ramprog.setProgress(valuepr);
        ramper.setText(uused + "%");

        tv_storage1 = findViewById(R.id.storage1);
        tv_storage2 = findViewById(R.id.storage2);
        tv_persen_storage = findViewById(R.id.storageper);
        pr_storage = findViewById(R.id.storageprog);
        final long MEGABYTE = 1024;
        final long GIGABYTE = 1048576;
        StatFs internalStatFs = new StatFs(Environment.getRootDirectory().getAbsolutePath());
        long internalTotal;
        long internalFree;

        StatFs externalStatFs = new StatFs(Environment.getExternalStorageDirectory().getAbsolutePath());
        long externalTotal;
        long externalFree;

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR2) {
            internalTotal = (internalStatFs.getBlockCountLong() * internalStatFs.getBlockSizeLong()) / (MEGABYTE * GIGABYTE);
            internalFree = (internalStatFs.getAvailableBlocksLong() * internalStatFs.getBlockSizeLong()) / (MEGABYTE * GIGABYTE);
            externalTotal = (externalStatFs.getBlockCountLong() * externalStatFs.getBlockSizeLong()) / (MEGABYTE * GIGABYTE);
            externalFree = (externalStatFs.getAvailableBlocksLong() * externalStatFs.getBlockSizeLong()) / (MEGABYTE * GIGABYTE);
        } else {
            internalTotal = ((long) internalStatFs.getBlockCount() * (long) internalStatFs.getBlockSize()) / (MEGABYTE * GIGABYTE);

            internalFree = ((long) internalStatFs.getAvailableBlocks() * (long) internalStatFs.getBlockSize()) / (MEGABYTE * GIGABYTE);
            externalTotal = ((long) externalStatFs.getBlockCount() * (long) externalStatFs.getBlockSize()) / (MEGABYTE * GIGABYTE);
            externalFree = ((long) externalStatFs.getAvailableBlocks() * (long) externalStatFs.getBlockSize()) / (MEGABYTE * GIGABYTE);
        }

        long stototal = internalTotal + externalTotal;
        long stofree = internalFree + externalFree;
        long stoused = stototal - stofree;

        double stosen = ((stoused + .0) / stototal + .0) * 100.0;
        int stovaluepr = (int) stosen;

        tv_storage1.setText("USED : " + String.valueOf(stoused) + "GB");
        tv_storage2.setText("FREE : " + String.valueOf(stofree) + "GB");
        tv_persen_storage.setText(String.format(new DecimalFormat("#").format(stosen)) + "%");
        pr_storage.setProgress(stovaluepr);

    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
		finishAffinity();
        stopService(new Intent(MainActivity.this, FloatingActivity.class));
        stopService(new Intent(MainActivity.this, Overlay.class));
    }

    private void CountTimerAccout() {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                try {
                    handler.postDelayed(this, 1000);
                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    Date expiryDate = dateFormat.parse(TimeExpired);
                    long now = System.currentTimeMillis();
                    long distance = expiryDate.getTime() - now;
                    long days = distance / (24 * 60 * 60 * 1000);
                    long hours = distance / (60 * 60 * 1000) % 24;
                    long minutes = distance / (60 * 1000) % 60;
                    long seconds = distance / 1000 % 60;
                    if (distance < 0) {
                    } else {
                        TextView Hari = findViewById(R.id.tv_d);
                        TextView Jam = findViewById(R.id.tv_h);
                        TextView Menit = findViewById(R.id.tv_m);
                        TextView Detik = findViewById(R.id.tv_s);
                        if (days > 0) {
                            Hari.setText(" " + String.format("%02d", days));
                        }
                        if (hours > 0) {
                            Jam.setText(" " + String.format("%02d", hours));
                        }
                        if (minutes > 0) {
                            Menit.setText(" " + String.format("%02d", minutes));
                        }
                        if (seconds > 0) {
                            Detik.setText(" " + String.format("%02d", seconds));
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
        handler.postDelayed(runnable, 0);
    }




    public static boolean isRootGiven() {
        if (isRootAvailable()) {
            Process process = null;
            try {
                process = Runtime.getRuntime().exec(new String[]{"su", "-c", "id"});
                BufferedReader in = new BufferedReader(new InputStreamReader(process.getInputStream()));
                String output = in.readLine();
                if (output != null && output.toLowerCase().contains("uid=0"))
                    return true;
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (process != null)
                    process.destroy();
            }
        }

        return false;
    }

    public void Execute(String path) {
        try {
            ExecuteElf("chmod 777 " + getFilesDir() + path);//VIRTUAL
            ExecuteElf(getFilesDir() + path);
            ExecuteElf("su -c chmod 777 " + getFilesDir() + path);//ROOT
            ExecuteElf("su -c " + getFilesDir() + path);
        } catch (Exception e) {
        }
    }

    private void ExecuteElf(String shell) {
        try {
            Runtime.getRuntime().exec(shell, null, null);
        } catch (Exception e) {
            e.printStackTrace();
        }
	}

    private boolean MoveAssets(String outPath, String fileName) {
        File file = new File(outPath);
        if (!file.exists()) {
            if (!file.mkdirs()) {
                Log.e("--Method--", "copyAssetsSingleFile: cannot create directory.");
                return false;
            }
        }
        try {
            InputStream inputStream = getAssets().open(fileName);
            File outFile = new File(file, fileName);
            FileOutputStream fileOutputStream = new FileOutputStream(outFile);
            byte[] buffer = new byte[1024];
            int byteRead;
            while (-1 != (byteRead = inputStream.read(buffer))) {
                fileOutputStream.write(buffer, 0, byteRead);
            }
            inputStream.close();
            fileOutputStream.flush();
            fileOutputStream.close();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
	}
	
	private static final int USER_ID = 0;

private void install(String pkg) {
    try {
        BlackBoxCore.get().installPackageAsUser(pkg, USER_ID);
        Toast.makeText(this, "Installed: " + pkg, Toast.LENGTH_SHORT).show();
    } catch (Throwable t) {
        Toast.makeText(this, "Install failed: " + pkg, Toast.LENGTH_SHORT).show();
    }
}

private void launch(String pkg) {
    try {
        BlackBoxCore.get().launchApk(pkg, USER_ID);
    } catch (Throwable t) {
        Toast.makeText(this, "Launch failed: " + pkg, Toast.LENGTH_SHORT).show();
    }
}

private void uninstall(String pkg) {
    try {
        BlackBoxCore.get().uninstallPackageAsUser(pkg, USER_ID);
        Toast.makeText(this, "Uninstalled: " + pkg, Toast.LENGTH_SHORT).show();
    } catch (Throwable t) {
        Toast.makeText(this, "Uninstall failed: " + pkg, Toast.LENGTH_SHORT).show();
    }
}


private void autoCopyObb(String pkg, String vcode) {

    String source = "/storage/emulated/0/Android/obb/" + pkg + "/main." + vcode + "." + pkg + ".obb";
    String dest = "/storage/emulated/0/blackbox/Android/obb/" + pkg + "/main." + vcode + "." + pkg + ".obb";

    File destFile = new File(dest);

    // Already exists?
    if (destFile.exists()) {
        return; // Skip
    }

    // Start copy with new beautiful progress dialog
    new ObbCopyTask(MainActivity.this, pkg, vcode).execute(source, pkg);
}


	private class ObbCopyTask extends AsyncTask<String, Integer, Boolean> {

    private Context ctx;
    private Dialog dialog;
    private ProgressBar progressBar;
    private TextView progressText;

    ObbCopyTask(Context ctx, String pkg, String vcode) {
        this.ctx = ctx;
    }

    @Override
    protected void onPreExecute() {
        dialog = new Dialog(ctx);
dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);

        View view = LayoutInflater.from(ctx).inflate(R.layout.dialog_loading2, null);

        dialog.setContentView(view);
        dialog.setCancelable(false);

        progressBar = view.findViewById(R.id.progress_bar);
        progressText = view.findViewById(R.id.progress_text);

        progressBar.setProgress(0);
        progressText.setText("0%");

        dialog.show();
    }

    @Override
    protected Boolean doInBackground(String... params) {

        String sourcePath = params[0];
        String pkg = params[1];

        try {
            File source = new File(sourcePath);

            if (!source.exists()) return false;

            String destDir = "/storage/emulated/0/blackbox/Android/obb/" + pkg + "/";
            File dir = new File(destDir);
            if (!dir.exists()) dir.mkdirs();

            String fileName = "main." + "20525" + "." + pkg + ".obb";
            File destination = new File(destDir + fileName);

            FileInputStream input = new FileInputStream(source);
            FileOutputStream output = new FileOutputStream(destination);

            byte[] buffer = new byte[4096];
            long total = source.length();
            long copied = 0;

            int read;
            while ((read = input.read(buffer)) != -1) {
                output.write(buffer, 0, read);
                copied += read;

                int progress = (int) ((copied * 100) / total);
                publishProgress(progress);
            }

            input.close();
            output.close();

            return true;

        } catch (Exception e) {
            return false;
        }
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        int progress = values[0];
        progressBar.setProgress(progress);
        progressText.setText(progress + "%");
    }

    @Override
    protected void onPostExecute(Boolean success) {
        dialog.dismiss();
    }
}

} 


