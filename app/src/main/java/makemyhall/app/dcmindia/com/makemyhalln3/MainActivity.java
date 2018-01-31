package makemyhall.app.dcmindia.com.makemyhalln3;

 import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import android.os.Bundle;
import android.support.annotation.Nullable;

import android.support.v4.app.FragmentTransaction;

import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener{


    //for payment gateway
    private TextView weddingbutton, weddingbtm2, weddingbtm3, weddingbtm4, weddingbtm5, weddingbtm6, serviceitem;
    private String category1, category2, category3, category4, category5, category6;

    private String lat = null;
    private String lon = null;
    private EditText placeedit;

    @Nullable
    @BindView(R.id.changelocation)TextView changelocation;


    @Override
    protected void onPause() {
        super.onPause();
        Toast.makeText(this, "onPause", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onStop() {
        super.onStop();
        Toast.makeText(this, "onstop", Toast.LENGTH_SHORT).show();

    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Toast.makeText(this, "onRestart", Toast.LENGTH_SHORT).show();
    }

    private boolean checkInternet() {
        ConnectivityManager manager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        //b. from network manger & get active network information
        //NetworkInfo networkInfo=manager.getActiveNetworkInfo();
        NetworkInfo networkInfo = manager.getActiveNetworkInfo();
        //c.check if network connected or not
        if (networkInfo == null || networkInfo.isConnected() == false) {
            //means there is no internet
            //webview.loadData("<h1>No Internet check internet<h1>", "text/html", null);
            return false;
        }
        return true;
    }



    @Override
    protected void onResume() {
        super.onResume();
        Toast.makeText(this, "onResume", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ButterKnife.bind(this);

        Toast.makeText(this, "oncreate", Toast.LENGTH_SHORT).show();

        Intent intent=getIntent();
        Bundle bundle=intent.getExtras();
        lat=bundle.getString("lat");
        lon=bundle.getString("lon");
        String location=bundle.getString("location");

        Bundle bundle1=new Bundle();
        bundle1.putString("lat",lat);
        bundle1.putString("lon",lon);
        WeddingServiceFragment weddingServiceFragment1=new WeddingServiceFragment();
        weddingServiceFragment1.setArguments(bundle1);

        changelocation.setText(location);


        changelocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent=new Intent(getApplication(),LocationSelectActivity.class);
                startActivity(intent);

            }
        });


//

        placeedit =findViewById(R.id.place_edit);

       // buildGoogleApiClient();


        placeedit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(checkInternet()){

                    Intent intent = new Intent(getApplication(), ActivityForLocation.class);
                    startActivity(intent);
                }
                 else{


                    Toast.makeText(MainActivity.this, "check Internet connectivity", Toast.LENGTH_SHORT).show();
                }

            }
        });




        if(checkInternet()){

            
         }
         else {

             Toast.makeText(this, "check Internet conncetivity", Toast.LENGTH_SHORT).show();
         }


//        Bundle bundlefrg = new Bundle();
//        bundlefrg.putString("lati", lat);
//        bundlefrg.putString("lon",lon);
//// set Fragmentclass Arguments
//        WeddingServiceFragment fragobj = new WeddingServiceFragment();
//        fragobj.setArguments(bundle);


        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

        WeddingServiceFragment weddingServiceFragment = new WeddingServiceFragment();
        BannerFragment bannerFragment = new BannerFragment();
       // WeddingEventServices weddingEventServices =new WeddingEventServices();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.add(R.id.framelayout1, weddingServiceFragment);
       // transaction.add(R.id.framelayout2, bannerFragment);
        //transaction.add(R.id.framelayout3, weddingEventServices);
        transaction.commit();

        //here do code for category


        weddingbutton= findViewById(R.id.weddingbutton);
        weddingbtm2=   findViewById(R.id.weddingbutton2);
        weddingbtm3=    findViewById(R.id.btnwedding3);
        weddingbtm4=   findViewById(R.id.btnwedding4);
        weddingbtm5=   findViewById(R.id.btnweding5);
        weddingbtm6=   findViewById(R.id.btnwedding6);
        serviceitem=findViewById(R.id.allservicesitem1);

        serviceitem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this,AllItemServices.class);
                intent.putExtra("lat",lat);
                intent.putExtra("lon",lon);

                startActivity(intent);
            }
        });

        weddingbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(checkInternet()) {
                    category1="MarriageHall";
                    Intent intent = new Intent(getApplication(), DetailsActivity.class);
                    intent.putExtra("lat",lat);
                    intent.putExtra("lon",lon);
                    intent.putExtra("cat",category1);
                    startActivity(intent);
                }

                else{

                    Toast.makeText(getApplication(), "check internet connectivity", Toast.LENGTH_SHORT).show();
                }

            }
        });


        weddingbtm2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(checkInternet()) {
                    category2="PartyHall";
                    Intent intent = new Intent(getApplication(),DetailsActivity.class);
                    intent.putExtra("lat",lat);
                    intent.putExtra("lon",lon);
                    intent.putExtra("cat",category2);
                    startActivity(intent);
                }
                else{

                    Toast.makeText(getApplication(), "check internet connectivity", Toast.LENGTH_SHORT).show();
                }
            }
        });
        weddingbtm3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(checkInternet()) {

                    category3 = "ExhibitionHall";
                    Intent intent = new Intent(getApplication(), DetailsActivity.class);
                    intent.putExtra("lat", lat);
                    intent.putExtra("lon", lon);
                    intent.putExtra("cat", category3);
                    startActivity(intent);
                }
                else{

                    Toast.makeText(getApplication(), "check internet connectivity", Toast.LENGTH_SHORT).show();
                }

            }
        });


        weddingbtm4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(checkInternet()) {
                    category4="ConferenceHall";

                    Intent intent = new Intent(getApplication(), DetailsActivity.class);
                    intent.putExtra("lat",lat);
                    intent.putExtra("lon",lon);
                    intent.putExtra("cat",category4);
                    startActivity(intent);
                }
                else{

                    Toast.makeText(getApplication(), "check internet connectivity", Toast.LENGTH_SHORT).show();
                }
            }
        });
        weddingbtm5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if(checkInternet()) {
                    category5="MeetingHall";

                    Intent intent = new Intent(getApplication(), DetailsActivity.class);
                    intent.putExtra("lat",lat);
                    intent.putExtra("lon",lon);
                    intent.putExtra("cat",category5);
                    startActivity(intent);
                }
                else{

                    Toast.makeText(getApplication(), "check internet connectivity", Toast.LENGTH_SHORT).show();
                }
            }
        });

        weddingbtm6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(checkInternet()) {

                    category6="EventHall";
                    Intent intent = new Intent(getApplication(), DetailsActivity.class);
                    intent.putExtra("lat",lat);
                    intent.putExtra("lon",lon);
                    intent.putExtra("cat",category6);
                    startActivity(intent);
                }
                else{

                    Toast.makeText(getApplication(), "check internet connectivity", Toast.LENGTH_SHORT).show();
                }
            }
        });



//        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
//        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
//                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
//        drawer.addDrawerListener(toggle);
//        toggle.syncState();
//
//        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
//        navigationView.setNavigationItemSelectedListener(this);


    }

    @Override
    protected void onStart() {
        super.onStart();

        Toast.makeText(this, "onstart", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onBackPressed() {


        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);//***Change Here***
        startActivity(intent);
        finish();
        System.exit(0);

//        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
//        if (drawer.isDrawerOpen(GravityCompat.START)) {
//            drawer.closeDrawer(GravityCompat.START);
//        } else {
//            super.onBackPressed();
//
//        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
       // getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
//        int id = item.getItemId();
//
//        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            return true;
//        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer =  findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

}
