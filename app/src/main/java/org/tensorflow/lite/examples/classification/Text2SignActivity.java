package org.tensorflow.lite.examples.classification;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.speech.RecognitionListener;
import android.speech.RecognizerIntent;
import android.speech.SpeechRecognizer;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.Locale;

public class Text2SignActivity extends AppCompatActivity  implements View.OnClickListener{

    ConstraintLayout root_layout;
    int AUDIO_PERMISSION_CODE = 101;
    ImageView speech;
    EditText text;
    SpeechRecognizer mSpeechRecognizer;
    Intent mSpeechRecognizerIntent;
    boolean on = false;

    Snackbar snkbar;





//Convert Project startes Here

    Button button;
    Button button1;
    int currentImage = -1;
    ImageView imageView;

    int[] images = {
            R.drawable.a,
            R.drawable.b,
            R.drawable.c,
            R.drawable.d,
            R.drawable.e,
            R.drawable.f,
            R.drawable.g,
            R.drawable.h,
            R.drawable.i,
            R.drawable.j,
            R.drawable.k,
            R.drawable.l,
            R.drawable.m,
            R.drawable.n,
            R.drawable.o,
            R.drawable.p,
            R.drawable.q,
            R.drawable.r,
            R.drawable.s,
            R.drawable.t,
            R.drawable.u,
            R.drawable.v,
            R.drawable.w,
            R.drawable.x,
            R.drawable.y,
            R.drawable.z


    };

    int i = -1;
    String result = "yourmomgay";

    //Convert Project ends here







    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text2_sign);




        //Convert Project starts here
        button = findViewById(R.id.button);
        button.setOnClickListener(this);
        button1 = findViewById(R.id.button1);
        button1.setOnClickListener(this);

        imageView = findViewById(R.id.imageView);

        imageView.setImageResource(R.drawable.null1);

        Toast.makeText(getApplicationContext(), Integer.toString(currentImage), Toast.LENGTH_SHORT).show();
        //Convert Project ends here










        root_layout = findViewById(R.id.root_layout);
        //check for permission to record audio
        if (!(ContextCompat.checkSelfPermission(this, Manifest.permission.RECORD_AUDIO) == PackageManager.PERMISSION_GRANTED)) {
            ActivityCompat.requestPermissions(Text2SignActivity.this,
                    new String[]{Manifest.permission.RECORD_AUDIO},
                    AUDIO_PERMISSION_CODE);

        }
        text = findViewById(R.id.textbox);
        speech = findViewById(R.id.speech);


        mSpeechRecognizer = SpeechRecognizer.createSpeechRecognizer(this);
        mSpeechRecognizerIntent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        mSpeechRecognizerIntent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        mSpeechRecognizerIntent.putExtra(RecognizerIntent.EXTRA_LANGUAGE,
                Locale.getDefault());

        mSpeechRecognizer.setRecognitionListener(new RecognitionListener() {
            @Override
            public void onReadyForSpeech(Bundle bundle) {

            }

            @Override
            public void onBeginningOfSpeech() {

            }

            @Override
            public void onRmsChanged(float v) {

            }

            @Override
            public void onBufferReceived(byte[] bytes) {

            }

            @Override
            public void onEndOfSpeech() {

            }

            @Override
            public void onError(int i) {

            }

            @Override
            public void onResults(Bundle bundle) {
                ArrayList<String> matches = bundle.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION);
                if(matches != null ) {
                    text.setText(matches.get(0));
                }
            }

            @Override
            public void onPartialResults(Bundle bundle) {

            }

            @Override
            public void onEvent(int i, Bundle bundle) {

            }
        });

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if(requestCode == AUDIO_PERMISSION_CODE) {
            if (grantResults.length > 0
                    && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "Permission Granted!", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Permission Denied!", Toast.LENGTH_SHORT).show();
            }
        }
    }

    public void record(View view) {
        snkbar = Snackbar.make(findViewById(R.id.root_layout), "Listening...", Snackbar.LENGTH_SHORT);
        if(on) {
            on = false;
            mSpeechRecognizer.stopListening();
            snkbar.dismiss();
        } else {
            on = true;
            mSpeechRecognizer.startListening(mSpeechRecognizerIntent);
            snkbar.show();
        }
    }



    //Convert Project starts here
    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.button:




                if (text.getText().equals(null) || text.getText().equals("")) {

                    Toast.makeText(getApplicationContext(), "empty", Toast.LENGTH_SHORT).show();

                } else {
                    result = text.getText().toString();
                }


                break;



            case R.id.button1:



                if (text.getText().equals(null) || text.getText().equals("")) {

                    //Toast.makeText(getApplicationContext(), "h", Toast.LENGTH_SHORT).show();

                } else {


                    if(i==result.length()-1){
                        i=-1;
                    }


                    i++;

                    Toast.makeText(getApplicationContext(), Integer.toString(i), Toast.LENGTH_SHORT).show();

                    char ch = result.charAt(i);
                    currentImage = ch;

                    if(currentImage >= 65 && currentImage <= 90){ //capital letter to small letters
                        currentImage+=32;
                    }

                    if(currentImage == 32){ //white space

                    }else{
                        currentImage = currentImage - 97;
                        imageView.setImageResource(images[currentImage]);
                    }
                }


                break;

        }


    }
    //Convert Project ends here

}

