package com.kmdev.builditbigger;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import com.example.JokeFromJavaLib;
import com.kmdev.jokeandroidlib.JokeDisplayActivity;
public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private static final int RC_FETCH_JOKE_ANDROID_LIB = 23;
    private Button mBtnLoadJokeJavaLib;
    private TextView mTvShowJoke;
    private Button mBtnJokeFromAndroidLib;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewsById();
    }

    private void findViewsById() {
        mTvShowJoke = (TextView) findViewById(R.id.tv_loaded_joke);
        mBtnLoadJokeJavaLib = (Button) findViewById(R.id.button_load_java_joke);
        mBtnJokeFromAndroidLib = (Button) findViewById(R.id.button_android_joke);
        //click listeners
        mBtnLoadJokeJavaLib.setOnClickListener(this);
        mBtnJokeFromAndroidLib.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button_load_java_joke:
                loadJokeFromJavaLib();
                break;
            case R.id.button_android_joke:
                loadJokeFromAndroidLib();
                break;
        }
    }

    /**
     * this method is used to get joke from java library and display it on click of button.
     */
    private void loadJokeFromJavaLib() {
        String jokeFromJavaLib = JokeFromJavaLib.getJokeFromJava();
        mTvShowJoke.setText(jokeFromJavaLib);
    }

    /**
     * this method is used to display joke from android library.
     */
    private void loadJokeFromAndroidLib() {
        Intent intentStartJokeLibActivity = new Intent(MainActivity.this, JokeDisplayActivity.class);
        intentStartJokeLibActivity.putExtra(JokeDisplayActivity.ARG_JOKE_RETRIEVED, JokeFromJavaLib.getJokeFromJava());
        startActivityForResult(intentStartJokeLibActivity, RC_FETCH_JOKE_ANDROID_LIB);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK && requestCode == RC_FETCH_JOKE_ANDROID_LIB) {
            Bundle bundle = data.getExtras();
            if (bundle != null) {
                String joke = bundle.getString(JokeDisplayActivity.ARG_JOKE);
                mTvShowJoke.setText(joke);
            } else {
            }
        }
    }
    class EndpointsAsyncTask extends AsyncTask<Pair<Context, String>, Void, String> {
        private JokeApi myApiService = null;
        private Context context;

        @Override
        protected String doInBackground(Pair<Context, String>... params) {
            if (myApiService == null) {  // Only do this once
                JokeApi.Builder builder = new JokeApi.Builder(AndroidHttp.newCompatibleTransport(),
                        new AndroidJsonFactory(), null)
                        // options for running against local devappserver
                        // - 10.1.1.35 is localhost's IP address in Android emulator
                        // - turn off compression when running against local devappserver
                        .setRootUrl(Constants.ROOT_URL)
                        .setGoogleClientRequestInitializer(new GoogleClientRequestInitializer() {
                            @Override
                            public void initialize(AbstractGoogleClientRequest<?> abstractGoogleClientRequest) throws IOException {
                                abstractGoogleClientRequest.setDisableGZipContent(true);
                            }
                        });
                // end options for devappserver
                myApiService = builder.build();
            }
            context = params[0].first;
            String name = params[0].second;
            try {
                return myApiService.tellJoke().execute().getJoke();
            } catch (IOException e) {
                return e.getMessage();
            }
        }

        @Override
        protected void onPostExecute(String result) {
            Toast.makeText(context, result, Toast.LENGTH_LONG).show();
            mTvShowJoke.setText(result);
        }
    }
}
