package com.rxzone.Util;

import android.Manifest;
import android.app.Activity;
import android.app.Dialog;
import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Environment;
import android.os.Handler;
import android.os.SystemClock;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentManager;
import android.text.Html;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.text.style.ForegroundColorSpan;
import android.util.Base64;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;


import com.rxzone.rxzone.R;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Logins on 10-11-2017.
 */

public class Common {

    public static void makeTextViewResizable(final TextView tv, final int maxLine, final String expandText, final boolean viewMore) {

        if (tv.getTag() == null) {
            tv.setTag(tv.getText());
        }
        ViewTreeObserver vto = tv.getViewTreeObserver();
        vto.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {

            @SuppressWarnings("deprecation")
            @Override
            public void onGlobalLayout() {

                ViewTreeObserver obs = tv.getViewTreeObserver();
                obs.removeGlobalOnLayoutListener(this);
                if (maxLine == 0) {
                    int lineEndIndex = tv.getLayout().getLineEnd(0);
                    String text = tv.getText().subSequence(0, lineEndIndex - expandText.length() + 1) + " " + expandText;
                    tv.setText(text);
                    tv.setMovementMethod(LinkMovementMethod.getInstance());
                    tv.setText(
                            addClickablePartTextViewResizable(Html.fromHtml(tv.getText().toString()), tv, maxLine, expandText,
                                    viewMore), TextView.BufferType.SPANNABLE);
                } else if (maxLine > 0 && tv.getLineCount() >= maxLine) {
                    int lineEndIndex = tv.getLayout().getLineEnd(maxLine - 1);
                    String text = tv.getText().subSequence(0, lineEndIndex - expandText.length() + 1) + " " + expandText;
                    tv.setText(text);
                    tv.setMovementMethod(LinkMovementMethod.getInstance());
                    tv.setText(
                            addClickablePartTextViewResizable(Html.fromHtml(tv.getText().toString()), tv, maxLine, expandText,
                                    viewMore), TextView.BufferType.SPANNABLE);
                } else {
                    int lineEndIndex = tv.getLayout().getLineEnd(tv.getLayout().getLineCount() - 1);
                    String text = tv.getText().subSequence(0, lineEndIndex) + " " + expandText;
                    tv.setText(text);
                    tv.setMovementMethod(LinkMovementMethod.getInstance());
                    tv.setText(
                            addClickablePartTextViewResizable(Html.fromHtml(tv.getText().toString()), tv, lineEndIndex, expandText,
                                    viewMore), TextView.BufferType.SPANNABLE);
                }
            }
        });

    }

    private static SpannableStringBuilder addClickablePartTextViewResizable(final Spanned strSpanned, final TextView tv,
                                                                            final int maxLine, final String spanableText, final boolean viewMore) {
        String str = strSpanned.toString();
        SpannableStringBuilder ssb = new SpannableStringBuilder(strSpanned);

        if (str.contains(spanableText)) {
            ssb.setSpan(new ClickableSpan() {

                @Override
                public void onClick(View widget) {

                    if (viewMore) {
                        tv.setLayoutParams(tv.getLayoutParams());
                        tv.setText(tv.getTag().toString(), TextView.BufferType.SPANNABLE);
                        tv.invalidate();
                        makeTextViewResizable(tv, -1, "View Less", false);
                    } else {
                        tv.setLayoutParams(tv.getLayoutParams());
                        tv.setText(tv.getTag().toString(), TextView.BufferType.SPANNABLE);
                        tv.invalidate();
                        makeTextViewResizable(tv, 3, "View More", true);
                    }

                }
            }, str.indexOf(spanableText), str.indexOf(spanableText) + spanableText.length(), 0);

        }
        return ssb;

    }


    public static String formatString(String name) {
        String s = name;
        Log.e("datalength", s.length() + "");
        if (s.length() >= 220) {
            s = s.substring(0, 215) + " ...";
        }
        return s;
    }

    public static boolean toastMessage(Context mContext, String name) {
        Toast.makeText(mContext, name, Toast.LENGTH_SHORT).show();
        return false;
    }

    public static boolean editTextErrorCall(Context mContext, String name, EditText editText) {
        editText.setError(name);
        editText.requestFocus();
        return false;
    }

    public static String BitMapToString(Bitmap bitmap) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, baos);
        byte[] b = baos.toByteArray();
        String temp = Base64.encodeToString(b, Base64.DEFAULT);
        return temp;
    }

    public static Bitmap StringToBitMap(String encodedString) {
        try {
            byte[] encodeByte = Base64.decode(encodedString, Base64.DEFAULT);
            Bitmap bitmap = BitmapFactory.decodeByteArray(encodeByte, 0,
                    encodeByte.length);
            return bitmap;
        } catch (Exception e) {
            e.getMessage();
            return null;
        }
    }

    public static void setKeyboardFocus(final EditText primaryTextField) {
        (new Handler()).postDelayed(new Runnable() {
            public void run() {
                primaryTextField.dispatchTouchEvent(MotionEvent.obtain(SystemClock.uptimeMillis(), SystemClock.uptimeMillis(), MotionEvent.ACTION_DOWN, 0, 0, 0));
                primaryTextField.dispatchTouchEvent(MotionEvent.obtain(SystemClock.uptimeMillis(), SystemClock.uptimeMillis(), MotionEvent.ACTION_UP, 0, 0, 0));
            }
        }, 100);
    }


    //Like Event
    public static void likeAndUnLikeEvent(Context context, final ImageButton ib_like,
                                          final String userId, String entityId) {
        if (SharedPrefsUtil.getStringPreference(context, "IS_LOGGED_IN").equals("NOT_LOGGED_IN")) {
            Toast.makeText(context, "You need to first Login.", Toast.LENGTH_SHORT).show();
            return;
        } else {
            if (ib_like.getDrawable().getConstantState().equals
                    (context.getResources().getDrawable(R.drawable.ic_likepink).getConstantState())) {
                Log.e("LikeEvent", "PINK_COLOR");
                likeImageChangeAndpostRequest(ib_like, "LIKED", userId, entityId, context);
            } else {
                Log.e("LikeEvent", "NORMAL_COLOR");
                likeImageChangeAndpostRequest(ib_like, "UNLIKED", userId, entityId, context);
            }
        }
    }


    private static void likeImageChangeAndpostRequest(ImageButton ib_like,
                                                      String likeStr, String userId,
                                                      String entityId, Context context) {
        if (SharedPrefsUtil.getStringPreference(context, "IS_LOGGED_IN").equals("NOT_LOGGED_IN")) {
            Toast.makeText(context, "You need to first Login.", Toast.LENGTH_SHORT).show();
            return;
        } else {
            ib_like.setImageResource(0);
            if (likeStr.equals("LIKED")) {
                ib_like.setImageResource(R.drawable.ic_like);
                Toast.makeText(context, "UnLiked", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(context, "Liked", Toast.LENGTH_SHORT).show();
                ib_like.setImageResource(R.drawable.ic_like);
            }
//            new PostRetrofit().postRetrofitMethod("like", userId,
//                    entityId, ib_like, context);
        }

    }


    private static void shareAudioOrVideoLink(String shareableLink, Context context) {
        Intent shareIntent = new Intent(Intent.ACTION_SEND);
        shareIntent.putExtra(Intent.EXTRA_SUBJECT, "Flikster");
        shareIntent.putExtra(Intent.EXTRA_TEXT, shareableLink + "\n\n\n" + "Download **Flikster**" + "\n" +
                "https://play.google.com/store/apps/details?id=com.flikster&hl=en" +
                " and don't miss anything from movie industry. Stay connected to the world of Illusion.\n");
        shareIntent.setType("text/plain");
        context.startActivity(Intent.createChooser(shareIntent, "Complete action using ...."));
    }

    public static void getBitmapForShare(Bitmap bitmap, Context context) {
        Intent shareIntent = new Intent(Intent.ACTION_SEND);
        shareIntent.putExtra(Intent.EXTRA_SUBJECT, "Flikster");
        //shareIntent.putExtra(Intent.EXTRA_TEXT, shareableLink);
        shareIntent.putExtra(Intent.EXTRA_STREAM, getLocalBitmapUri(bitmap, context));
        shareIntent.setType("text/plain");
        context.startActivity(Intent.createChooser(shareIntent, "Complete action using ...."));
    }

    public static Uri getLocalBitmapUri(Bitmap bmp, Context context) {
        Uri bmpUri = null;
        try {
            File file = new File(context.getExternalFilesDir(Environment.DIRECTORY_PICTURES), "share_image_" + System.currentTimeMillis() + ".png");
            FileOutputStream out = new FileOutputStream(file);
            bmp.compress(Bitmap.CompressFormat.PNG, 90, out);
            out.close();
            bmpUri = Uri.fromFile(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bmpUri;
    }


    public static boolean emailValidator(String email) {
        Pattern pattern;
        Matcher matcher;
        final String EMAIL_PATTERN = "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
        pattern = Pattern.compile(EMAIL_PATTERN);
        matcher = pattern.matcher(email);
        return matcher.matches();
    }


   /* public static void openCommonDialog(Context context, String commontxtStr, String headertxtStr) {
        final Dialog dialog = new Dialog(context);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_common_menuitems);
        final Window window = dialog.getWindow();
        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT);
        window.clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        //WebView commontxt = (WebView) dialog.findViewById(R.id.commontxt);
        TextView commontxt = (TextView) dialog.findViewById(R.id.commontxt);
        ImageButton closebtn = (ImageButton) dialog.findViewById(R.id.closebtn);
        TextView headertxt = (TextView) dialog.findViewById(R.id.headertxt);
        if (headertxtStr != null && !headertxtStr.isEmpty()) {
            headertxt.setText(headertxtStr);
        } else {
            headertxt.setText("Flikster");
        }
        String htmlText = " %s ";
        String myData = "What do we do with your information?\n" +
                "When you purchase something from our store, as part of the buying and selling process, we collect the personal information you give us such as your name, address and email address.When you browse our store If you would like to: access, correct, amend or delete any personal information we have about you, register a complaint, or simply want more information contact our Privacy Compliance Officer at support@flikster.com";
        //commontxt.loadData(String.format(htmlText, myData), "text/html", "utf-8");
        commontxt.setText("\"Flikster - Movies & Fashion\" is extremely useful to get you the most popular and latest news of your favorite celebrities, Bollywood, Fashion trends. Manage your data feed cleaner and stay tuned with every piece of news from different sources in just few seconds under a single umbrella of \"Flikster - Movies & Fashion\". ");
//        commontxt.setText(String.format(htmlText, myData));
         *//*if (commontxtStr != null && !commontxtStr.isEmpty()) {
            commontxt.setText(commontxtStr + "");
            commontxt.setText(Html.fromHtml("first<br><b>second</b>"));
        } else {
            commontxt.setText("No Data Available" + "");
            Html.fromHtml("first<br><b>second</b>");
        }*//*

        closebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        window.setBackgroundDrawable(new ColorDrawable(context.getResources().getColor(R.color.translucent)));
        dialog.show();
    }*/

    public static void popBackStack(FragmentManager manager) {
        FragmentManager.BackStackEntry first = manager.getBackStackEntryAt(0);
        manager.popBackStack(first.getId(), FragmentManager.POP_BACK_STACK_INCLUSIVE);
    }

}
