package makemyhall.app.dcmindia.com.makemyhalln3;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.graphics.Palette;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import makemyhall.app.dcmindia.com.makemyhalln3.pojo.Listinfo;
import makemyhall.app.dcmindia.com.makemyhalln3.pojo.Listnew;


public class TabsHeaderActivity extends AppCompatActivity {


    private static final String TAG = TabsHeaderActivity.class.getSimpleName();

    String latitude;
    String longitude;
    String hallname;
    String haddress;
    String mobnumnerhall;
    String emailidhall;
    double hallprice;
    String image;
    double dicount=5;
    double s,dis=5;
    ImageView imageView;
    double totalamount1=5;

    @Nullable
    @BindView(R.id.bookhall)Button hallbook;
    @Nullable
    @BindView(R.id.hotelname)TextView hotelname;
    @Nullable
    @BindView(R.id.hoteladdress)TextView halladdress;
    @Nullable
    @BindView(R.id.hotelprice)TextView hotelprice;
    @Nullable
    @BindView(R.id.hotelfullamount)TextView totalamount;

    @BindView(R.id.callhallinfo)Button callbutton;
    @BindView(R.id.hotelfullprice1)TextView totalprice;
    @BindView(R.id.halldiscount)TextView discounthotelprice;
    @BindView(R.id.hallinfolocation)TextView locationhall;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tabs_header);
        imageView = (ImageView) findViewById(R.id.htab_header);
        ButterKnife.bind(this);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        image = bundle.getString("imageslider");
        latitude = bundle.getString("latidude");
        longitude = bundle.getString("longitude");
        hallname = bundle.getString("hallname");
        haddress = bundle.getString("add");
        mobnumnerhall = bundle.getString("mobilenumber");
        emailidhall = bundle.getString("emailid");
        hallprice = Double.parseDouble(bundle.getString("hallprice"));
        dicount= Double.parseDouble(bundle.getString("discount"));
        totalprice.setText(String.valueOf("\u20B9"+hallprice));

        dis=100-dicount;

        totalamount1=(dis*hallprice/100);
        hotelname.setText(hallname);
        halladdress.setText(haddress);
        hotelprice.setText( String.valueOf("\u20B9"+hallprice));
        totalamount.setText(String.valueOf("\u20B9"+totalamount1));
        double discount1=hallprice-totalamount1;
        Log.d("price",String.valueOf(discount1));
        discounthotelprice.setText(String.valueOf("\u20B9"+discount1));

        Glide.with(this).load(image).into(imageView);

        hallbook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplication(),BookingActivity.class);
                intent.putExtra("hotelname",hallname);
                intent.putExtra("hallprice",String.valueOf(totalamount1));
                intent.putExtra("hallnumber",mobnumnerhall);
                startActivity(intent);
            }
        });

        callbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(isPermissionGranted()) {
                           call_action();
                       }
            }
        });

        locationhall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                String geoUri = "http://maps.google.com/maps?q=loc:" + latitude + "," + longitude + " " + hallname + "";
//                       startActivity(new Intent(android.content.Intent.ACTION_VIEW, Uri.parse(geoUri)));

                String strUri = "http://maps.google.com/maps?q=loc:" + latitude + "," + longitude + " (" + hallname + ")";
                Intent intent = new Intent(android.content.Intent.ACTION_VIEW, Uri.parse(strUri));

                intent.setClassName("com.google.android.apps.maps", "com.google.android.maps.MapsActivity");

                startActivity(intent);
            }
        });

        final Toolbar toolbar = (Toolbar) findViewById(R.id.htab_toolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) getSupportActionBar().setTitle("MakeMyhall");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        final TabLayout tabLayout = (TabLayout) findViewById(R.id.htab_tabs);

        final CollapsingToolbarLayout collapsingToolbarLayout = (CollapsingToolbarLayout)
                findViewById(R.id.htab_collapse_toolbar);

        try {
            Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.sliderimg);

            Palette.from(bitmap).generate(new Palette.PaletteAsyncListener() {
                @SuppressWarnings("ResourceType")
                @Override
                public void onGenerated(Palette palette) {

                    int vibrantColor = palette.getVibrantColor(R.color.primary_500);
                    int vibrantDarkColor = palette.getDarkVibrantColor(R.color.primary_700);
                    collapsingToolbarLayout.setContentScrimColor(vibrantColor);
                    collapsingToolbarLayout.setStatusBarScrimColor(vibrantDarkColor);
                }
            });

        } catch (Exception e) {
            // if Bitmap fetch fails, fallback to primary colors
            Log.e(TAG, "onCreate: failed to create bitmap from background", e.fillInStackTrace());
            collapsingToolbarLayout.setContentScrimColor(
                    ContextCompat.getColor(this, R.color.primary_500)
            );
            collapsingToolbarLayout.setStatusBarScrimColor(
                    ContextCompat.getColor(this, R.color.primary_700)
            );
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
       // getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
//            case R.id.action_settings:
//                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void call_action(){

        Intent callIntent=new Intent(Intent.ACTION_DIAL);
        callIntent.setData(Uri.parse("tel:"+mobnumnerhall));
        if (ContextCompat.checkSelfPermission(getApplication(), Manifest.permission.CALL_PHONE)
                != PackageManager.PERMISSION_GRANTED) {

            if(ActivityCompat.checkSelfPermission(getApplication(),Manifest.permission.CALL_PHONE)
                    !=PackageManager.PERMISSION_GRANTED)
                // TODO: Consider calling
                //    ActivityCompat#requestPermissions
                // here to request the missing permissions, and then overriding
                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                //                                          int[] grantResults)
                // to handle the case where the user grants the permission. See the documentation
                // for ActivityCompat#requestPermissions for more details.
                return;
        }
        startActivity(callIntent);

    }

    public  boolean isPermissionGranted() {
        if (Build.VERSION.SDK_INT >= 23) {
            if (checkSelfPermission(android.Manifest.permission.CALL_PHONE)
                    == PackageManager.PERMISSION_GRANTED) {
                Log.v("TAG","Permission is granted");
                return true;
            } else {

                Log.v("TAG","Permission is revoked");
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CALL_PHONE}, 1);
                return false;
            }
        }
        else { //permission is automatically granted on sdk<23 upon installation
            Log.v("TAG","Permission is granted");
            return true;
        }
    }


    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {

            case 1: {

                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(getApplicationContext(), "Permission granted", Toast.LENGTH_SHORT).show();
                    call_action();
                } else {
                    Toast.makeText(getApplicationContext(), "Permission denied", Toast.LENGTH_SHORT).show();
                }
                return;
            }

            // other 'case' lines to check for other
            // permissions this app might request
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        this.finish();
    }
}