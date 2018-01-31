package makemyhall.app.dcmindia.com.makemyhalln3;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.IntentSender;
import android.content.pm.PackageManager;
import android.location.Location;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.location.LocationSettingsResult;
import com.google.android.gms.location.LocationSettingsStates;
import com.google.android.gms.location.LocationSettingsStatusCodes;

public class MainActivityLocationResult extends AppCompatActivity implements GoogleApiClient.ConnectionCallbacks,
 GoogleApiClient.OnConnectionFailedListener, LocationListener     {

    Button connectButton;
    GoogleApiClient googleApiClient;
    Location lastLocation;
    LocationRequest locationRequest;
    private static final int PERMISSION_REQUEST_CODE_LOCATION = 1;

    int REQUEST_CHECK_SETTINGS  = 1000;

    /*!
    * 'onCreate' Called when the activity is first created. followed by onStart().
    * */
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_home);

        /*
        * Setting up the runtime permission. We should implement when we target  Android Marshmallow (API 23) as a target Sdk.
        * */
        requestPermission(PERMISSION_REQUEST_CODE_LOCATION,getApplicationContext(), this);

        connectButton = (Button) findViewById(R.id.button);

        connectButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(MainActivityLocationResult.this, "Button Pressed.!", Toast.LENGTH_SHORT).show();

                try {

                    googleApiClient = new GoogleApiClient.Builder(MainActivityLocationResult.this)
                            .addApi(LocationServices.API)
                            .addConnectionCallbacks(MainActivityLocationResult.this)
                            .addOnConnectionFailedListener(MainActivityLocationResult.this)
                            .build();

                    locationRequest = LocationRequest.create();
                    locationRequest.setInterval(1000);

                    locationRequest.setFastestInterval(1000);
                    locationRequest.setPriority(LocationRequest.PRIORITY_BALANCED_POWER_ACCURACY);

                    checkForLocationReuqestSetting(locationRequest);
                    googleApiClient.connect();

                } catch (SecurityException ex) {
                    ex.printStackTrace();
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        });


    }

    /*
    * onStart : Called when the activity is becoming visible to the user.
    * */
    @Override
    protected void onStart() {
        super.onStart();

    }

    /*
    * onStop : Called when the activity is no longer visible to the user
    * */
    @Override
    protected void onStop() {
        super.onStop();

        //Disconnect the google client api connection.
        if (googleApiClient != null) {
            googleApiClient.disconnect();
        }
    }

    /*
    * onPause : Called when the system is about to start resuming a previous activity.
    * */
    @Override
    protected void onPause() {
        try {
            super.onPause();

            /*
            * Stop retrieving locations when we go out of the application.
            * */
            if (googleApiClient != null) {
                stopLocationUpdates();
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    protected void stopLocationUpdates() {
        LocationServices.FusedLocationApi.removeLocationUpdates(googleApiClient, this);
    }

    /*
    * Callback method of GoogleApiClient.ConnectionCallbacks
    * */
    @Override
    public void onConnected(@Nullable Bundle bundle) {
        try {

            lastLocation = LocationServices.FusedLocationApi.getLastLocation(googleApiClient);

            LocationServices.FusedLocationApi.requestLocationUpdates(googleApiClient, locationRequest, this);

            ((TextView)(findViewById(R.id.fieldstatus))).setText("Loading...");

            if (lastLocation != null) {
                ((TextView)(findViewById(R.id.lastlocationfield))).setText("Last location is " + lastLocation);
            }

        } catch (SecurityException ex) {
            ex.printStackTrace();
        }

    }

    /*
    * Callback method of GoogleApiClient.ConnectionCallbacks
    * */
    @Override
    public void onConnectionSuspended(int i) {
        System.out.println("onConnectionSuspended called...");
    }

    /*
    * Callback method of GoogleApiClient.OnConnectionFailedListener
    * */
    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        System.out.println("onConnectionFailed called.");
    }


    /*
    * Callback method of Locationlistener.
    * */
    @Override
    public void onLocationChanged(final Location location) {
        System.out.println("location changed "+ location);

        ((TextView)(findViewById(R.id.fieldstatus))).setText("Load...!");

        if (location != null) {
            ((TextView)(findViewById(R.id.fieldstatus))).setText("Lat : " + location.getLatitude() + " lon : " + location.getLongitude());

        } else {
            ((TextView)(findViewById(R.id.fieldstatus))).setText("No location found..!");
        }
        Toast.makeText(MainActivityLocationResult.this, "called...", Toast.LENGTH_SHORT).show();
    }

    public static void requestPermission(int perCode, Context _c, Activity _a){

        String fineLocationPermissionString = Manifest.permission.ACCESS_FINE_LOCATION;
        String coarseLocationPermissionString = Manifest.permission.ACCESS_COARSE_LOCATION;

        if  (   ContextCompat.checkSelfPermission(_a, fineLocationPermissionString) != PackageManager.PERMISSION_GRANTED &&
                ContextCompat.checkSelfPermission(_a, coarseLocationPermissionString) != PackageManager.PERMISSION_GRANTED
                ) {

            //user has already cancelled the permission prompt. we need to advise him.
            if (ActivityCompat.shouldShowRequestPermissionRationale(_a,fineLocationPermissionString)){
                Toast.makeText(_c,"We must need your permission in order to access your reporting location.",
                        Toast.LENGTH_LONG).show();
            }

            ActivityCompat.requestPermissions(_a,new String[]{fineLocationPermissionString,
                    coarseLocationPermissionString},perCode);

        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {

        for (String per : permissions) {
            System.out.println("permissions are  " + per);
        }

        switch (requestCode) {

            case PERMISSION_REQUEST_CODE_LOCATION:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    Toast.makeText(this, "Permission loaded...", Toast.LENGTH_SHORT).show();

                } else {

                    Toast.makeText(getApplicationContext(),"Permission Denied, You cannot access location data.",
                            Toast.LENGTH_LONG).show();

                }
                break;
        }
    }

    /**
     * Google Fused API require Runtime permission. Runtime permission is available for Android Marshmallow 
     * or Greater versions. 
     * @param locationRequest needed to check whether we need to prompt settings alert.
     */
    private void checkForLocationReuqestSetting(LocationRequest locationRequest) {
        try {
            LocationSettingsRequest.Builder builder = new LocationSettingsRequest.Builder()
                    .addLocationRequest(locationRequest);

            PendingResult<LocationSettingsResult> result = LocationServices.SettingsApi.checkLocationSettings(googleApiClient,
                    builder.build());

            result.setResultCallback(new ResultCallback<LocationSettingsResult>() {
                @Override
                public void onResult(@NonNull LocationSettingsResult locationSettingsResult) {
                    final Status status = locationSettingsResult.getStatus();
                    final LocationSettingsStates locationSettingsStates = locationSettingsResult.getLocationSettingsStates();

                    switch (status.getStatusCode()) {
                        case LocationSettingsStatusCodes.SUCCESS:
                            // All location settings are satisfied. The client can
                            // initialize location requests here.
                            Log.d("MainActivity", "onResult: SUCCESS");
                            break;
                        case LocationSettingsStatusCodes.RESOLUTION_REQUIRED:
                            Log.d("MainActivity", "onResult: RESOLUTION_REQUIRED");
                            // Location settings are not satisfied, but this can be fixed
                            // by showing the user a dialog.
                            try {
                                // Show the dialog by calling startResolutionForResult(),
                                // and check the result in onActivityResult().
                                status.startResolutionForResult(
                                        MainActivityLocationResult.this,
                                        REQUEST_CHECK_SETTINGS);
                            } catch (IntentSender.SendIntentException e) {
                                // Ignore the error.
                            }
                            break;
                        case LocationSettingsStatusCodes.SETTINGS_CHANGE_UNAVAILABLE:
                            Log.d("MainActivity", "onResult: SETTINGS_CHANGE_UNAVAILABLE");
                            // Location settings are not satisfied. However, we have no way
                            // to fix the settings so we won't show the dialog.

                            break;
                    }

                }
            });

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_CHECK_SETTINGS){
            Toast.makeText(this, "Setting has changed...", Toast.LENGTH_SHORT).show();
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }
}
 
