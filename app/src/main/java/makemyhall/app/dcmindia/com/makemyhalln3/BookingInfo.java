package makemyhall.app.dcmindia.com.makemyhalln3;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;

public class BookingInfo extends AppCompatActivity {

    @BindView(R.id.nametext)TextView textView;
    @BindView(R.id.hallpricebo)TextView textViewprice;
    @BindView(R.id.hallmobilenumber)TextView textViewhallnumber;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking_info);
        ButterKnife.bind(this);
        Intent intent=getIntent();
        Bundle bundle=intent.getExtras();
        String namehotel=bundle.getString("hotelname");
        String hotelprice=String.valueOf(bundle.getString("hotelpricebook"));
        String hallmobile=bundle.getString("hallnumber");


        textView.setText(namehotel);
        textViewprice.setText(hotelprice);
        textViewhallnumber.setText(hallmobile);

    }
}
