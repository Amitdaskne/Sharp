package com.sharp.pro;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.os.AsyncTask;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import net.lingala.zip4j.ZipFile;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.AsyncTask;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class Downtwo extends AsyncTask<String, String, String> {

    private Context context;
    private Dialog progressDialog;
    private ProgressBar progressBar;
    private TextView progressText;
    private File zipFile;

    private native String PASSJKPAPA();

    public Downtwo(Context context) {
    this.context = context;

    // Create Custom Dialog
    progressDialog = new Dialog(context);
    progressDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
    progressDialog.setContentView(R.layout.dialog_loading2);
    progressDialog.setCancelable(false);

    // Set wrap content and transparent background
    if (progressDialog.getWindow() != null) {
        progressDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        progressDialog.getWindow().setLayout(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
        );
        progressDialog.getWindow().setGravity(Gravity.CENTER);
    }

    progressBar = progressDialog.findViewById(R.id.progress_bar);
    progressText = progressDialog.findViewById(R.id.progress_text);

    progressDialog.show();
}


    @Override
protected void onPreExecute() {
    progressText.setText("Starting...");
}


    @Override
    protected String doInBackground(String... strings) {
        try {
            URL url = new URL(strings[1]);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.connect();
            int lengthOfFile = connection.getContentLength();
            InputStream input = connection.getInputStream();
            String fileName = "Saved.zip";

            File pathBase = new File(context.getFilesDir().getPath());
            if (!pathBase.exists()) {
                pathBase.mkdirs();
            }
            zipFile = new File(pathBase.toString(), fileName);
            OutputStream output = new FileOutputStream(zipFile);
            
            byte data[] = new byte[1024];
            long total = 0;
            int count;
            while ((count = input.read(data)) != -1) {
                total += count;
                int progress = (int) ((total * 100) / lengthOfFile);
                publishProgress("" + progress);
                output.write(data, 0, count);
            }
            output.close();
            input.close();

            return "Downloaded";
        } catch (Exception e) {
            e.printStackTrace();
            return "Error";
        }
    }

    @SuppressLint("SetTextI18n")
    @Override
protected void onProgressUpdate(String... progress) {
    progressText.setText(progress[0] + "%");
}


 @Override
protected void onPostExecute(String result) {
    progressDialog.dismiss();

    // Extract files directly into /data/data/com.ayan.vrecorder/files/
    String extractPath = context.getFilesDir().getAbsolutePath();

    try {
        ZipFile zipFile = new ZipFile(context.getFilesDir() + "/Saved.zip");
        zipFile.extractAll(extractPath); // ✅ Extracts directly into `files/`
    } catch (Exception e) {
        e.printStackTrace();
    }

    // Delete the zip file after extraction
    File zip = new File(context.getFilesDir(), "Saved.zip");
    if (zip.exists()) {
        zip.delete();
    }
}

}
