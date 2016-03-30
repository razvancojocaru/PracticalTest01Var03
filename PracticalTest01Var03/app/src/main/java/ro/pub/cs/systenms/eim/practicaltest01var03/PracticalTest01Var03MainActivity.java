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

public class PracticalTest01Var03MainActivity extends AppCompatActivity {
    Button mBtnPlay;
    EditText mEtRiddle;
    EditText mEtAnswer;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_practical_test01_var03_main);

        /* Init elements */
        mBtnPlay = (Button)findViewById(R.id.btnPlay);
        mEtRiddle = (EditText)findViewById(R.id.etRiddle);
        mEtAnswer = (EditText)findViewById(R.id.etAnswer);

        /* Listeners */
        mBtnPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mEtRiddle.getText().toString().equals("") ||
                        mEtAnswer.getText().toString().equals("")) {
                    return;
                }

                Intent intentServ = new Intent(getApplicationContext(), PracticalTest01Var03Service.class);
                intentServ.putExtra("answer", mEtAnswer.getText().toString());
                getApplicationContext().startService(intentServ);

                Intent intent = new Intent(getApplicationContext(), PracticalTest01Var02PlayActivity.class);
                intent.putExtra("riddle", mEtRiddle.getText().toString());
                intent.putExtra("answer", mEtAnswer.getText().toString());
                startActivity(intent);
            }
        });


    }

    @Override
    protected void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putString("riddle", mEtRiddle.getText().toString());
        savedInstanceState.putString("answer", mEtAnswer.getText().toString());
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        if (savedInstanceState.getString("riddle") != null) {
            mEtRiddle.setText(savedInstanceState.getString("riddle"));
        }
        if (savedInstanceState.getString("answer") != null) {
            mEtAnswer.setText(savedInstanceState.getString("answer"));
        }
    }

}
