package makemyhall.app.dcmindia.com.makemyhalln3;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class FlashActivityNew extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flash_new);

        Thread timerThread = new Thread(){
            public void run(){
                try{
                    sleep(3000);
                }catch(InterruptedException e){
                    e.printStackTrace();
                }finally{
                    Intent intent = new Intent(getApplication(),LocationSelectActivity.class);
                    startActivity(intent);

                }
            }
        };
        timerThread.start();
    }

    protected void onPause() {
        // TODO Auto-generated method stub
        super.onPause();
         finish();
    }

}
