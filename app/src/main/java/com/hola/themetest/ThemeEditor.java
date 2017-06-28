package com.hola.themetest;

import android.app.Fragment;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Environment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.json.JSONObject;
import org.xmlpull.v1.XmlPullParserFactory;
import org.xmlpull.v1.XmlSerializer;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;

/**
 * Created by zy on 17-6-22.
 */

public abstract class ThemeEditor extends Fragment {

    protected Theme mTheme;
    private String themeId;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        themeId = getActivity().getIntent().getStringExtra("theme");
        final ThemeParser parser = new ThemeParser(themeId);
        final ProgressDialog dialog = new ProgressDialog(getActivity());
        dialog.show();
        new Thread(
            new Runnable() {
                public void run() {
                    mTheme = parser.getTheme();
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            dialog.dismiss();
                            onLoaded();
                        }
                    });
                }
            }
        ).start();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View ret = inflater.inflate(getLayoutId(), container, false);
        onInflateView(ret);
        return ret;
    }

    protected abstract int getLayoutId();

    protected abstract void onSubmit();

    protected abstract void onLoaded();

    protected void onInflateView(View v) {
        // ignore;
    }

    protected static <T extends View> T $(View view, int id) {
        return (T) view.findViewById(id);
    }

    public void submit() {
        if (mTheme == null) {
            return;
        }
        onSubmit();
        final ProgressDialog dialog = new ProgressDialog(getActivity());
        dialog.show();
        new Thread(new Runnable() {
            @Override
            public void run() {
                File themeFolder = new File(Environment.getExternalStorageDirectory() + "/360launcher/theme/custom/" + themeId);
                if (!themeFolder.exists()) {
                    themeFolder.mkdirs();
                }
                saveManifest(themeFolder);
                saveInfo(themeFolder);
                savePreview(themeFolder);
                dialog.dismiss();
                startLauncher();
                getActivity().finish();
            }
        }).start();
    }

    private void startLauncher() {
        Intent intent = new Intent();
        intent.setClassName("com.qihoo360.launcher", "com.qihoo360.launcher.Launcher");
        intent.putExtra("flag", 4);
        getActivity().startActivity(intent);
    }

    private void savePreview(File dir) {
        File output = new File(dir, "preview/preview.jpg");
        if (output.exists()) {
            return;
        }
        if (!output.getParentFile().exists()) {
            output.getParentFile().mkdirs();
        }
        Bitmap preview = ThemeGenerator.generatePreview();
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(output);
            preview.compress(Bitmap.CompressFormat.JPEG, 100, fos);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                fos.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void saveInfo(File dir) {
        FileWriter writer = null;
        try {
            JSONObject json = new JSONObject();
            json.put("name", "Editor");
            json.put("author", "Editor");
            json.put("size", "1M");
            File target = new File(dir, "info.json");
            writer = new FileWriter(target);
            BufferedWriter bw = new BufferedWriter(writer);
            bw.write(json.toString());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                writer.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void saveManifest(File themeFolder) {
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(new File(themeFolder, "manifest.xml"));
            XmlSerializer dom = XmlPullParserFactory.newInstance().newSerializer();
            dom.setOutput(fos, "utf-8");
            dom.startDocument("utf-8", true);

            dom.startTag(null, "theme");

            dom.startTag(null, "packageName");
            dom.text(themeId);
            dom.endTag(null, "packageName");

            dom.startTag(null, "versionName");
            dom.text("2.0");
            dom.endTag(null, "versionName");

            dom.startTag(null, "versionCode");
            dom.text("2");
            dom.endTag(null, "versionCode");

            dom.startTag(null, "specification");
            dom.text("2");
            dom.endTag(null, "specification");

            dom.startTag(null, "features");
            dom.text("launcher");
            dom.endTag(null, "features");

            dom.startTag(null, "requiredFeatures");
            dom.text("launcher");
            dom.endTag(null, "requiredFeatures");

            dom.startTag(null, "encrypt");
            dom.text("0");
            dom.endTag(null, "encrypt");

            dom.startTag(null, "defaultDensity");
            dom.text("320");
            dom.endTag(null, "defaultDensity");

            dom.startTag(null, "requireValidation");
            dom.text("false");
            dom.endTag(null, "requireValidation");

            dom.startTag(null, "launcher");

            dom.startTag(null, "icon");

            dom.startTag(null, "size");
            dom.text(Integer.toString(mTheme.iconSize));
            dom.endTag(null, "size");

            dom.endTag(null, "icon");

            dom.startTag(null, "spacing");

            dom.startTag(null, "hPadding");
            dom.text(Integer.toString(mTheme.hPadding));
            dom.endTag(null, "hPadding");

            dom.startTag(null, "hGap");
            dom.text(Integer.toString(mTheme.hGap));
            dom.endTag(null, "hGap");

            dom.startTag(null, "vStartPadding");
            dom.text(Integer.toString(mTheme.vStartPadding));
            dom.endTag(null, "vStartPadding");

            dom.startTag(null, "vEndPadding");
            dom.text(Integer.toString(mTheme.vEndPadding));
            dom.endTag(null, "vEndPadding");

            dom.startTag(null, "textSpacing");
            dom.text(Integer.toString(mTheme.textSpacing));
            dom.endTag(null, "textSpacing");

            dom.startTag(null, "dockPadding");
            dom.text(Integer.toString(mTheme.dockPadding));
            dom.endTag(null, "dockPadding");

            dom.startTag(null, "dockGap");
            dom.text(Integer.toString(mTheme.dockGap));
            dom.endTag(null, "dockGap");

            dom.startTag(null, "folderGap");
            dom.text(Integer.toString(mTheme.folderGap));
            dom.endTag(null, "folderGap");

            dom.startTag(null, "indicatorSpacing");
            dom.text(Integer.toString(mTheme.indicatorSpacing));
            dom.endTag(null, "indicatorSpacing");

            dom.endTag(null, "spacing");

            dom.endTag(null, "launcher");

            dom.endTag(null, "theme");

            dom.endDocument();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                fos.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
