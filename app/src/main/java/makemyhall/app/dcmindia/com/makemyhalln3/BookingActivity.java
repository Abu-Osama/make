package makemyhall.app.dcmindia.com.makemyhalln3;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

public class BookingActivity extends AppCompatActivity {

    @Nullable
    @BindView(R.id.procedbook)Button procededtobooking;
    @Nullable
    @BindView(R.id.name)EditText editTextname;
    @Nullable
    @BindView(R.id.contactnumber)EditText editTextcontactnumber;
    @Nullable
    @BindView(R.id.emailid)EditText editTextemaiilid;
    @Nullable
    @BindView(R.id.payoffline)Button payoffline;
    String name,mobilenum,emailid,hallmobile;
    ProgressDialog progressDialog;
    String name1, price;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking);
        ButterKnife.bind(this);

        Intent intent=getIntent();
        Bundle bundle=intent.getExtras();
         name1=bundle.getString("hotelname");
         price=String.valueOf(bundle.getString("hallprice"));
         hallmobile=bundle.getString("hallnumber");

         payoffline.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {

                 name=editTextname.getText().toString();
                 mobilenum=editTextcontactnumber.getText().toString();
                 emailid=editTextemaiilid.getText().toString();

                 if(name.matches("")|| mobilenum.matches("") || emailid.matches("")){

                     Toast.makeText(BookingActivity.this, "Please Fill the data", Toast.LENGTH_SHORT).show();


                 }
                 else{

                     login(name,mobilenum,emailid,name1,price,hallmobile);

                     Intent intent1=new Intent(BookingActivity.this,PayOfflineDetails.class);
                     startActivity(intent1);

                 }






             }
         });



        //Intent intent=getIntent();
//        Bundle bundle=intent.getExtras();
//        String hallname=bundle.getString("hallname");
//        String halladd=bundle.getString("halladd");
//        String hallprice=bundle.getString("price");
//        Toast.makeText(this, ""+hallname+""+halladd+""+hallprice, Toast.LENGTH_SHORT).show();


       // mAPIService = ApiUtils.getAPIService();
        procededtobooking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                 name=editTextname.getText().toString();
                 mobilenum=editTextcontactnumber.getText().toString();
                 emailid=editTextemaiilid.getText().toString();

                 if(name.matches("")|| mobilenum.matches("") || emailid.matches("")){

                     Toast.makeText(BookingActivity.this, "Please Fill the data", Toast.LENGTH_SHORT).show();


                 }
                   else{

                     Intent intent=new Intent(getApplication(),MyActivity.class);
                     intent.putExtra("hotelname",name1);
                     intent.putExtra("hotelpricebook",price);
                     intent.putExtra("hallnumber",hallmobile);
                     startActivity(intent);


                     login(name,mobilenum,emailid,name1,price,hallmobile);

                 }


            }
        });
    }


    private void login(final String name, final String mobilenum, final String emailid,final String name1, final String price, final String hallmobile){
        //Getting values from edit texts
//        final String email = editTextEmail.getText().toString().trim();
//        final String password = editTextPassword.getText().toString().trim();

        //Creating a string request
        progressDialog = ProgressDialog.show(BookingActivity.this, "booking", "Please Wait", true, true);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, "http://www.makemyhall.com/m/makemyhallbooking.php",
                new com.android.volley.Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        //If we are getting success from server
                        Toast.makeText(BookingActivity.this, ""+response, Toast.LENGTH_SHORT).show();
                        progressDialog.dismiss();

                    }
                },
                new com.android.volley.Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //You can handle error here if you want
                    }
                }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params = new HashMap<>();
                //Adding parameters to request
                params.put("name", name);
                params.put("phone", mobilenum);
                params.put("email", emailid);
                params.put("hallname",name1);
                params.put("hallmobile",hallmobile);
                params.put("hallprice",price);
                //returning parameter
                return params;
            }
        };

        //Adding the string request to the queue
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }


}
