package ro.pub.cs.systenms.eim.practicaltest01var03;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class PracticalTest01Var02PlayActivity extends AppCompatActivity {
    TextView mTxtRiddle;
    EditText mEtAnswer;
    Button mBtnCheck;
    Button mBtnBack;

    String riddle;
    String answer;

    /* Define IntentFilter */
    private IntentFilter intentFilter = new IntentFilter();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_practical_test01_var02_play);

        /* Init elements */
        mBtnCheck = (Button)findViewById(R.id.btnCheck);
        mBtnBack = (Button)findViewById(R.id.btnBack);
        mEtAnswer = (EditText)findViewById(R.id.etAnswer);
        mTxtRiddle = (TextView)findViewById(R.id.txtRiddle);

        // intent from parent
        Intent intent = getIntent();
        if (intent != null) {
            riddle = intent.getStringExtra("riddle");
            answer = intent.getStringExtra("answer");

            mTxtRiddle.setText(riddle);
        }

                /* Listeners */
        mBtnCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (answer.equals(mEtAnswer.getText().toString())) {
                    Toast.makeText(getApplicationContext(), "Correct!", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getApplicationContext(), "Wrong!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        mBtnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        intentFilter.addAction("Hint");
    }

    /* Add broadcast receiver */
    private MessageBroadcastReceiver messageBroadcastReceiver = new MessageBroadcastReceiver();
    private class MessageBroadcastReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            Toast.makeText(getApplicationContext(), intent.getStringExtra("hint"), Toast.LENGTH_SHORT).show();
        }
    }

    /* Register broadcast receiver */
    @Override
    protected void onResume() {
        super.onResume();
        registerReceiver(messageBroadcastReceiver, intentFilter);
    }

    @Override
    protected void onPause() {
        unregisterReceiver(messageBroadcastReceiver);
        super.onPause();
    }


    @Override
    protected void onDestroy() {
        Intent intent = new Intent(this, PracticalTest01Var03Service.class);
        stopService(intent);
        super.onDestroy();
    }
}
