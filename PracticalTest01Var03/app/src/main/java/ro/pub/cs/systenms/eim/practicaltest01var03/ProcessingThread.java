package ro.pub.cs.systenms.eim.practicaltest01var03;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import java.util.Arrays;
import java.util.Date;
import java.util.Random;

public class ProcessingThread extends Thread {

    private Context context = null;
    private boolean isRunning = true;
    private Random random = new Random();

    private String answer;

    public ProcessingThread(Context context, String ans) {
        this.context = context;

        answer = ans;
    }
  
    @Override
    public void run() {
        Log.d("[ProcessingThread]", "Thread has started!");
        while (isRunning) {
            sendMessage();
            sleep();
        }
        Log.d("[ProcessingThread]", "Thread has stopped!");
    }
  
    private void sendMessage() {
        Intent intent = new Intent();
        intent.setAction("Hint");

        /* Create hint */
        char[] chars = new char[answer.length()];
        Arrays.fill(chars, '*');
        String result = new String(chars);

        int index = random.nextInt(answer.length());
        StringBuilder hint = new StringBuilder(result);
        hint.setCharAt(index, answer.charAt(index));

        intent.putExtra("hint", hint.toString());
        Log.d("[ProcessingThread]", "Message sent, action=" + intent.getAction());
        context.sendBroadcast(intent);
    }
  
    private void sleep() {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException interruptedException) {
            interruptedException.printStackTrace();
        }
    }

    public void stopThread() {
    isRunning = false;
  }
}