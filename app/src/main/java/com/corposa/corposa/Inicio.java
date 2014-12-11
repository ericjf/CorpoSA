package com.corposa.corposa;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import java.util.Timer;
import java.util.TimerTask;

public class Inicio extends Activity {

    public int currentimageindex=0;
    //    Timer timer;
//    TimerTask task;
    ImageView slidingimage;

    private int[] IMAGE_IDS = {
            R.drawable.slider2, R.drawable.slider4
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_inicio);

        final Handler mHandler = new Handler();

        // Create runnable for posting
        final Runnable mUpdateResults = new Runnable() {

            public void run() {
                AnimateandSlideShow();
            }

        };

        int delay = 1000; // delay for 1 sec.

        int period = 3000; // repeat every 4 sec.

        Timer timer = new Timer();

        timer.scheduleAtFixedRate(new TimerTask() {

            public void run() {

                mHandler.post(mUpdateResults);

            }

        }, delay, period);
    }



    /**
     * Helper method to start the animation on the splash screen
     */
    private void AnimateandSlideShow() {


        slidingimage = (ImageView)findViewById(R.id.imageView);
        slidingimage.setImageResource(IMAGE_IDS[currentimageindex%IMAGE_IDS.length]);

        currentimageindex++;
        Animation rotateimage = AnimationUtils.loadAnimation(this, R.anim.abc_fade_in);

        slidingimage.startAnimation(rotateimage);



    }
}
