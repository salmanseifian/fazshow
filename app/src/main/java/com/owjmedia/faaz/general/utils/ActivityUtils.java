package com.owjmedia.faaz.general.utils;

import android.app.Activity;
import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;
import com.owjmedia.faaz.R;
import com.owjmedia.faaz.general.utils.CustomWidgets.TypefacedTextView;

/**
 * Created by salman on 11/8/17.
 */

public class ActivityUtils {

    public static void addFragmentToActivity(FragmentManager fragmentManager, Fragment fragment, int frameId) {
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.add(frameId, fragment);
        transaction.addToBackStack(null);
        transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        transaction.commit();
    }

    public static void replaceFragmentToActivity(FragmentManager fragmentManager, Fragment fragment, int frameId) {
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(frameId, fragment);
        transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        transaction.commit();
    }

    public static void addFragmentToActivity(FragmentManager fragmentManager, Fragment fragment, int frameId, ImageView sharedView, String name) {
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.add(frameId, fragment);
        transaction.addToBackStack(null);
        transaction.addSharedElement(sharedView, name);
        transaction.commit();
    }


    public static void showToast(Context context, String message, String animationName) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.custom_toast, (ViewGroup) ((Activity) context).findViewById(R.id.custom_toast));

        TypefacedTextView txtToast = view.findViewById(R.id.txt_toast);
        txtToast.setText(message);

        LottieAnimationView lottieToast = view.findViewById(R.id.lottie_toast);
        lottieToast.setAnimation(animationName);

        Toast toast = new Toast(context);
        toast.setDuration(Toast.LENGTH_SHORT);
        toast.setView(view);
        toast.show();
    }



}
