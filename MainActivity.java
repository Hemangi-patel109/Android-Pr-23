package com.example.practical_22; 
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar; 
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
public class MainActivity extends AppCompatActivity { private ProgressBar progressBar;
private TextView textViewStatus; private Button buttonStart;
@Override
protected void onCreate(Bundle savedInstanceState) { super.onCreate(savedInstanceState);
setContentView(R.layout.activity_main);
progressBar = findViewById(R.id.progressBar);
textViewStatus = findViewById(R.id.textViewStatus); buttonStart = findViewById(R.id.buttonStart);
buttonStart.setOnClickListener(new View.OnClickListener() { @Override
public void onClick(View v) {
new MyAsyncTask().execute(10);
}
});
}
private class MyAsyncTask extends AsyncTask<Integer, Integer, String> { @Override
protected void onPreExecute() { super.onPreExecute();
textViewStatus.setText("Task Starting..."); progressBar.setProgress(0);
}
@Override
protected String doInBackground(Integer... params) { int count = params[0];
for (int i = 1; i <= count; i++) { try {
Thread.sleep(500); // Simulate work publishProgress(i * 10);
} catch (InterruptedException e) { e.printStackTrace();
}
}
return "Task Completed!";
}
@Override
protected void onProgressUpdate(Integer... values) { super.onProgressUpdate(values);
progressBar.setProgress(values[0]);
textViewStatus.setText("Progress: " + values[0] + "%");
}
@Override
protected void onPostExecute(String result) { super.onPostExecute(result);
textViewStatus.setText(result);
}
}
}
