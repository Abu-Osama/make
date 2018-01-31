package makemyhall.app.dcmindia.com.makemyhalln3;

import android.*;
import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Geocoder;
import android.location.Location;


import android.net.Uri;
import android.os.Handler;
import android.os.ResultReceiver;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LocationSelectActivity extends AppCompatActivity {

    @Nullable
    @BindView(R.id.bangalore)TextView bangalore;
    @Nullable
    @BindView(R.id.chennai)TextView chennai;
    @Nullable
    @BindView(R.id.mumbai)TextView mumbai;
    @Nullable
     @BindView(R.id.pune)TextView pune;
    @Nullable
    @BindView(R.id.hyderabad)TextView hyderabad;
    @Nullable
    @BindView(R.id.mysore) TextView mysore;

   // @BindView(R.id.txtLat)TextView txtLat;
    //@Nullable
   // @BindView(R.id.detectlocation)Button detectlocation;
    private String banlati="12.9716";
    private String banlon="77.5946";
    private String mumlati="19.0760";
    private String mumlon="72.8777";
    private String chelti="13.0827";
    private String chelon="80.2707";
    private String punelati="18.5204";
    private String punelon="73.8567";
    private String hyderlati="17.3850";
    private String hyderalon="78.4867";
    private String mysorelati="12.2958";
    private String mysorelon="76.6394";


    protected Context context;
    private FusedLocationProviderClient fusedLocationClient;
    private static final int LOCATION_PERMISSION_REQUEST_CODE = 2;
    private LocationAddressResultReceiver addressResultReceiver;
    private TextView currentAddTv;
    private Location currentLocation;
    private LocationCallback locationCallback;
    private static final String TAG = MainActivity.class.getSimpleName();
    private static final int REQUEST_PERMISSIONS_REQUEST_CODE = 34;
    private static final String ADDRESS_REQUESTED_KEY = "address-request-pending";
    private static final String LOCATION_ADDRESS_KEY = "location-address";

    /**
     * Provides access to the Fused Location Provider API.
     */
    private FusedLocationProviderClient mFusedLocationClient;

    /**
     * Represents a geographical location.
     */
    private Location mLastLocation;

    /**
     * Tracks whether the user has requested an address. Becomes true when the user requests an
     * address and false when the address (or an error message) is delivered.
     */
    private boolean mAddressRequested;

    /**
     * The formatted location address.
     */
    private String mAddressOutput;

    /**
     * Receiver registered with this activity to get the response from FetchAddressIntentService.
     */
    //private AddressResultReceiver mResultReceiver;

    /**
     * Displays the location address.
     */
    private TextView mLocationAddressTextView;

    /**
     * Visible while the address is being fetched.
     */
    private ProgressBar mProgressBar;

    /**
     * Kicks off the request to fetch an address when pressed.
     */
    private Button mFetchAddressButton;

    boolean statusOfGPS;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location_select);
        ButterKnife.bind(this);


        addressResultReceiver = new LocationAddressResultReceiver(new Handler());

        //currentAddTv = findViewById(R.id.current_address);
        //add_label=findViewById(R.id.add_label);
//        detectlocation.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//                startLocationUpdates();
//                getAddress();
//
//            }
//        });

        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this);

        locationCallback = new LocationCallback() {
            @Override
            public void onLocationResult(LocationResult locationResult) {
                currentLocation = locationResult.getLocations().get(0);
                getAddress();
            };
        };

        bangalore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String locationnew=bangalore.getText().toString();

                Intent intent=new Intent(getApplication(),MainActivity.class);
                intent.putExtra("lat",banlati);
                intent.putExtra("lon",banlon);
                intent.putExtra("location",locationnew);
                startActivity(intent);

            }
        });
        mumbai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String locationnew=mumbai.getText().toString();

                Intent intent=new Intent(getApplication(),MainActivity.class);
                intent.putExtra("lat",mumlati);
                intent.putExtra("lon",mumlon);
                intent.putExtra("location",locationnew);
                startActivity(intent);


            }
        });
        chennai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String locationnew=chennai.getText().toString();

                Intent intent=new Intent(getApplication(),MainActivity.class);
                intent.putExtra("lat",chelti);
                intent.putExtra("lon",chelon);
                intent.putExtra("location",locationnew);
                startActivity(intent);

            }
        });
        pune.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String locationnew=pune.getText().toString();

                Intent intent=new Intent(getApplication(),MainActivity.class);
                intent.putExtra("lat",punelati);
                intent.putExtra("lon",punelon);
                intent.putExtra("location",locationnew);
                startActivity(intent);

            }
        });

        hyderabad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String locationnew=hyderabad.getText().toString();

                Intent intent=new Intent(getApplication(),MainActivity.class);
                intent.putExtra("lat",hyderlati);
                intent.putExtra("lon",hyderalon);
                intent.putExtra("location",locationnew);
                startActivity(intent);

            }
        });

        mysore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String locationnew=mysore.getText().toString();

                Intent intent=new Intent(getApplication(),MainActivity.class);
                intent.putExtra("lat",mysorelati);
                intent.putExtra("lon",mysorelon);
                intent.putExtra("location",locationnew);
                startActivity(intent);

            }
        });



    }



    @SuppressWarnings("MissingPermission")
    private void startLocationUpdates() {
        if (ContextCompat.checkSelfPermission(this,
                android.Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                    LOCATION_PERMISSION_REQUEST_CODE);
        } else {
            LocationRequest locationRequest = new LocationRequest();
            locationRequest.setInterval(2000);
            locationRequest.setFastestInterval(1000);
            locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);

            fusedLocationClient.requestLocationUpdates(locationRequest,
                    locationCallback,
                    null);
        }
    }

    @SuppressWarnings("MissingPermission")
    private void getAddress() {

        if (!Geocoder.isPresent()) {
            Toast.makeText(LocationSelectActivity.this,
                    "Can't find current address, ",
                    Toast.LENGTH_SHORT).show();
            return;
        }

        Intent intent = new Intent(this, GetAddressIntentService.class);
        intent.putExtra("add_receiver", addressResultReceiver);
        intent.putExtra("add_location", currentLocation);
        startService(intent);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        switch (requestCode) {
            case LOCATION_PERMISSION_REQUEST_CODE: {
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    startLocationUpdates();
                } else {
                    Toast.makeText(this, "Location permission not granted, " +
                                    "restart the app if you want the feature",
                            Toast.LENGTH_SHORT).show();
                }
                return;
            }

        }
    }
    private class LocationAddressResultReceiver extends ResultReceiver {
        LocationAddressResultReceiver(Handler handler) {
            super(handler);
        }

        @Override
        protected void onReceiveResult(int resultCode, Bundle resultData) {

            if (resultCode == 0) {
                //Last Location can be null for various reasons
                //for example the api is called first time
                //so retry till location is set
                //since intent service runs on background thread, it doesn't block main thread
                Log.d("Address", "Location null retrying");
                getAddress();
            }

            if (resultCode == 1) {
                Toast.makeText(LocationSelectActivity.this,
                        "Address not found, " ,
                        Toast.LENGTH_SHORT).show();
            }

            String currentAdd = resultData.getString("address_result");

            showResults(currentAdd);
        }
    }

    private void showResults(String currentAdd){
       // currentAddTv.setText(currentAdd);
        Log.d("address",currentAdd);
    }


    @Override
    protected void onResume() {
        super.onResume();
        startLocationUpdates();
    }

    @Override
    protected void onPause() {
        super.onPause();
        fusedLocationClient.removeLocationUpdates(locationCallback);
    }

    public void onStart() {
        super.onStart();

        if (!checkPermissions()) {
            requestPermissions();
           // Toast.makeText(this, "check gps", Toast.LENGTH_SHORT).show();
        } else {
            //getAddress();
        }
    }


    private boolean checkPermissions() {
        int permissionState = ActivityCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION);
        return permissionState == PackageManager.PERMISSION_GRANTED;
    }

    private void requestPermissions() {
        boolean shouldProvideRationale =
                ActivityCompat.shouldShowRequestPermissionRationale(this,
                        Manifest.permission.ACCESS_FINE_LOCATION);

        // Provide an additional rationale to the user. This would happen if the user denied the
        // request previously, but didn't check the "Don't ask again" checkbox.
        if (shouldProvideRationale) {
            Log.i(TAG, "Displaying permission rationale to provide additional context.");

            showSnackbar(R.string.permission_rationale, android.R.string.ok,
                    new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            // Request permission
                            ActivityCompat.requestPermissions(LocationSelectActivity.this,
                                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                                    REQUEST_PERMISSIONS_REQUEST_CODE);
                        }
                    });

        } else {
            Log.i(TAG, "Requesting permission");
            // Request permission. It's possible this can be auto answered if device policy
            // sets the permission in a given state or the user denied the permission
            // previously and checked "Never ask again".
            ActivityCompat.requestPermissions(LocationSelectActivity.this,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                    REQUEST_PERMISSIONS_REQUEST_CODE);
        }
    }

    /**
     * Callback received when a permissions request has been completed.
     */
//    @Override
//    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
//                                           @NonNull int[] grantResults) {
//        Log.i(TAG, "onRequestPermissionResult");
//        if (requestCode == REQUEST_PERMISSIONS_REQUEST_CODE) {
//            if (grantResults.length <= 0) {
//                // If user interaction was interrupted, the permission request is cancelled and you
//                // receive empty arrays.
//                Log.i(TAG, "User interaction was cancelled.");
//            } else if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
//                // Permission granted.
//                getAddress();
//            } else {
//                // Permission denied.
//
//                // Notify the user via a SnackBar that they have rejected a core permission for the
//                // app, which makes the Activity useless. In a real app, core permissions would
//                // typically be best requested during a welcome-screen flow.
//
//                // Additionally, it is important to remember that a permission might have been
//                // rejected without asking the user for permission (device policy or "Never ask
//                // again" prompts). Therefore, a user interface affordance is typically implemented
//                // when permissions are denied. Otherwise, your app could appear unresponsive to
//                // touches or interactions which have required permissions.
//                showSnackbar(R.string.permission_denied_explanation, R.string.settings,
//                        new View.OnClickListener() {
//                            @Override
//                            public void onClick(View view) {
//                                // Build intent that displays the App settings screen.
//                                Intent intent = new Intent();
//                                intent.setAction(
//                                        Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
//                                Uri uri = Uri.fromParts("package",
//                                        BuildConfig.APPLICATION_ID, null);
//                                intent.setData(uri);
//                                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                                startActivity(intent);
//                            }
//                        });
//            }
//        }
  //  }



    private void showSnackbar(final String text) {
        View container = findViewById(android.R.id.content);
        if (container != null) {
            Snackbar.make(container, text, Snackbar.LENGTH_LONG).show();
        }
    }

    /**
     * Shows a {@link Snackbar}.
     *
     * @param mainTextStringId The id for the string resource for the Snackbar text.
     * @param actionStringId   The text of the action item.
     * @param listener         The listener associated with the Snackbar action.
     */
    private void showSnackbar(final int mainTextStringId, final int actionStringId,
                              View.OnClickListener listener) {
        Snackbar.make(findViewById(android.R.id.content),
                getString(mainTextStringId),
                Snackbar.LENGTH_INDEFINITE)
                .setAction(getString(actionStringId), listener).show();
    }




}
