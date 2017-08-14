package com.kmdev.jokeandroidlib;
import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
public class JokeDisplayActivity extends AppCompatActivity {
    public static final String ARG_JOKE_RETRIEVED = "joke_fetch";
    public static final String ARG_JOKE = "joke";
    public String mJoke;
    private TextView mTvViewJoke;
    private ImageView mImageBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_joke);
        mTvViewJoke = (TextView) findViewById(R.id.tv_view_joke);
        mImageBack = (ImageView) findViewById(R.id.image_back);
        mImageBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent result = new Intent();
                result.putExtra(ARG_JOKE, mJoke);
                setResult(Activity.RESULT_OK, result);
                finish();
            }
        });
        displayJoke();
    }

    private void displayJoke() {
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            mJoke = bundle.getString(ARG_JOKE_RETRIEVED);
        }
        mTvViewJoke.setText(mJoke);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent result = new Intent();
        result.putExtra(ARG_JOKE, mJoke);
        setResult(Activity.RESULT_OK, result);
        finish();
    }
}
