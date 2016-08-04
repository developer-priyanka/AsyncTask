package my.assignment.asynctask;


import android.content.Context;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.ProgressBar;


public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    Button startBtn;
    String TAG="Download Task";
    LinearLayout layout;
    LayoutParams layoutParams;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        startBtn=(Button)findViewById(R.id.button);
        layout=(LinearLayout)findViewById(R.id.layout);
        startBtn.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        new DownloadTask(this).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
        new DownloadTask(this).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);


    }

    class DownloadTask extends AsyncTask<Void,Integer,Void>{


        private ProgressBar progreeBar;
                ;
        private Context ctx;

        public DownloadTask(Context context) {
            ctx = context;
        }


        @Override
        protected void onPreExecute() {

            progreeBar = new ProgressBar(ctx,null,android.R.attr.progressBarStyleHorizontal);
            progreeBar.getProgressDrawable().setColorFilter(Color.BLUE, PorterDuff.Mode.SRC_IN);
            layoutParams = new LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.WRAP_CONTENT);
            layoutParams.setMargins(0,30,0,0);
            progreeBar.setLayoutParams(layoutParams);
            layout.addView(progreeBar);
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(Void... integers) {
            try {
                for (int i = 1; i < 11; i++) {
                    Thread.sleep(500);
                    publishProgress(i * 10);
                }
            } catch (InterruptedException e) {
                Log.e(TAG, e.toString());
            }
            return null;
        }


        @Override
        protected void onProgressUpdate(Integer... values)
        {
            progreeBar.setProgress(values[0]);
            
        }


    }
}
