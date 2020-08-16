package com.brkcnszgn.dateandtimepickerdialog;

import android.animation.Animator;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;

public class Animations {

    public static void fadeInOut(final View view, final Float alpha, Long duration) {
        view.animate().alpha(alpha).setDuration(duration).setListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                if (alpha == 0f) {

                } else {

                }
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
    }


    public static void fadeOut(View view) {
        AlphaAnimation alphaAnimation = new AlphaAnimation(1.0f, 0.0f);
        alphaAnimation.setDuration(300);
        //alphaAnimation.setRepeatCount(0);
        //alphaAnimation.setRepeatMode(Animation.REVERSE);
        view.startAnimation(alphaAnimation);
    }

    public static void fadeIn(View view) {
        AlphaAnimation alphaAnimation = new AlphaAnimation(0.0f, 1.0f);
        alphaAnimation.setDuration(300);
        //alphaAnimation.setRepeatCount(0);
        //alphaAnimation.setRepeatMode(Animation.REVERSE);
        view.startAnimation(alphaAnimation);
    }

    public static void translateVertical(View view, Float fromY, Float toY, int duration) {
        TranslateAnimation a = new TranslateAnimation(Animation.ABSOLUTE, 0f, Animation.ABSOLUTE, 0f, Animation.RELATIVE_TO_PARENT, fromY, Animation.RELATIVE_TO_PARENT, toY);
        a.setDuration(duration);
        a.setFillAfter(true);
        view.startAnimation(a);
    }

}
