/*
 * Copyright (C) 2015 Google Inc. All Rights Reserved.
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

package makemyhall.app.dcmindia.com.makemyhalln3;

import android.content.Intent;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Bundle;
import android.provider.CalendarContract;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.text.InputType;
import android.text.Spanned;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.places.AutocompleteFilter;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlaceAutocomplete;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import makemyhall.app.dcmindia.com.makemyhalln3.common.activities.SampleActivityBase;
import makemyhall.app.dcmindia.com.makemyhalln3.common.logger.Log;

public class ActivityForLocation extends AppCompatActivity implements View.OnClickListener {
    /**
     * Request code for the autocomplete activity. This will be used to identify results from the
     * autocomplete activity in onActivityResult.
     */
    private static final int REQUEST_CODE_AUTOCOMPLETE = 1;

    public TextView mPlaceDetailsText,category;

    private TextView mPlaceAttribution;
    private boolean mAutoHighlight;
    Button searchhall;
   // EditText ;
    String latisearch,lat,lon,message;

    private android.app.DatePickerDialog fromDatePickerDialog;
    private android.app.DatePickerDialog toDatePickerDialog;
    private EditText fromDateEtxt;
    private EditText toDateEtxt;

    private SimpleDateFormat dateFormatter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_location);
        ButterKnife.bind(this);
       // getActionBar().show();
        category=findViewById(R.id.category);
        searchhall=findViewById(R.id.searchhall);
        mPlaceDetailsText = findViewById(R.id.open_button);
        mPlaceAttribution = (TextView) findViewById(R.id.place_attribution);
        final String location=mPlaceDetailsText.getText().toString();
        final String cat=category.getText().toString();
        dateFormatter = new SimpleDateFormat("dd-MM-yyyy", Locale.US);

        findViewsById();

        setDateTimeField();

        category.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent=new Intent(getApplication(),CategoryList.class);
                startActivityForResult(intent,2);
               // finish();
            }
        });




        Calendar calendar = Calendar.getInstance();
        Date today = calendar.getTime();

//

        mPlaceDetailsText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openAutocompleteActivity();
            }
        });


        searchhall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Log.d("location", String.valueOf(mPlaceDetailsText));


                if(mPlaceDetailsText.getText().toString().length()>0 && category.getText().toString().length()>0
                        &&fromDateEtxt.getText().toString().length()>0&&toDateEtxt.getText().toString().length()>0) {


                    String s = latisearch.toString();
                    String[] latLng = s.substring(10, s.length() - 1).split(",");
                    lat = latLng[0];
                    lon = latLng[1];
                    Intent intent = new Intent(getApplication(), DetailsActivity.class);
                    intent.putExtra("lat", lat);
                    intent.putExtra("lon", lon);
                    intent.putExtra("cat",message);
                    startActivity(intent);
                }

                else{

                    Toast.makeText(ActivityForLocation.this, "Fill the Field", Toast.LENGTH_SHORT).show();
                }


            }
        });
        // Retrieve the TextViews that will display details about the selected place.
       // mPlaceDetailsText = (TextView) findViewById(R.id.place_details);

    }




    private void findViewsById() {
        fromDateEtxt = (EditText) findViewById(R.id.etxt_fromdate);
        fromDateEtxt.setInputType(InputType.TYPE_NULL);
        fromDateEtxt.requestFocus();

        toDateEtxt = (EditText) findViewById(R.id.etxt_todate);
        toDateEtxt.setInputType(InputType.TYPE_NULL);
    }


    private void setDateTimeField() {
        fromDateEtxt.setOnClickListener(this);
        toDateEtxt.setOnClickListener(this);

        final Calendar newCalendar = Calendar.getInstance();
       // fromDatePickerDialog.getDatePicker().setMaxDate(System.currentTimeMillis());
        fromDatePickerDialog = new android.app.DatePickerDialog(this,R.style.Theme_AppCompat_DayNight_Dialog,
                new android.app.DatePickerDialog.OnDateSetListener() {


            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {

                Calendar newDate = Calendar.getInstance();
                //newDate.add(Calendar.DATE,1);
                newDate.set(year, monthOfYear, dayOfMonth);

                fromDateEtxt.setText(dateFormatter.format(newDate.getTime()));

            }

        },newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));
        fromDatePickerDialog.getDatePicker().setMinDate(newCalendar.getTimeInMillis());

        toDatePickerDialog = new android.app.DatePickerDialog(this,R.style.Theme_AppCompat_DayNight_Dialog, new android.app.DatePickerDialog.OnDateSetListener() {

            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                Calendar newDate = Calendar.getInstance();
                newDate.set(year, monthOfYear, dayOfMonth);

                    toDateEtxt.setText(dateFormatter.format(newDate.getTime()));



            }

        },newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));
        toDatePickerDialog.getDatePicker().setMinDate(newCalendar.getTimeInMillis());
    }
    private void openAutocompleteActivity() {
        try {
            // The autocomplete activity requires Google Play Services to be available. The intent
            // builder checks this and throws an exception if it is not the case.

            AutocompleteFilter autocompleteFilter = new AutocompleteFilter.Builder()
                    .setTypeFilter(Place.TYPE_COUNTRY)
                    .setCountry("IN")
                    .build();
            Intent intent = new PlaceAutocomplete.IntentBuilder(PlaceAutocomplete.MODE_FULLSCREEN)
                    .setFilter(autocompleteFilter)
                    .build(this);
            startActivityForResult(intent, REQUEST_CODE_AUTOCOMPLETE);
        } catch (GooglePlayServicesRepairableException e) {
            // Indicates that Google Play Services is either not installed or not up to date. Prompt
            // the user to correct the issue.
            GoogleApiAvailability.getInstance().getErrorDialog(this, e.getConnectionStatusCode(),
                    0 /* requestCode */).show();
        } catch (GooglePlayServicesNotAvailableException e) {
            // Indicates that Google Play Services is not available and the problem is not easily
            // resolvable.
            String message = "Google Play Services is not available: " +
                    GoogleApiAvailability.getInstance().getErrorString(e.errorCode);

            Log.e("", message);
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * Called after the autocomplete activity has finished to return its result.
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Check that the result was from the autocomplete widget.
        if (requestCode == REQUEST_CODE_AUTOCOMPLETE) {
            if (resultCode == RESULT_OK) {
                // Get the user's selected place from the Intent.
                Place place = PlaceAutocomplete.getPlace(this, data);
                Log.i("", "Place Selected: " + place.getName());

                // Format the place's details and display them in the TextView.
//                mPlaceDetailsText.setText(formatPlaceDetails(getResources(), place.getName(),
//                        place.getId(), place.getAddress(), place.getPhoneNumber(),
//                        place.getWebsiteUri()));
                mPlaceDetailsText.setText(place.getAddress());
                latisearch= String.valueOf(place.getLatLng());

                // Display attributions if required.
                CharSequence attributions = place.getAttributions();
                if (!TextUtils.isEmpty(attributions)) {
                    mPlaceAttribution.setText(Html.fromHtml(attributions.toString()));
                } else {
                    mPlaceAttribution.setText("");
                }
            } else if (resultCode == PlaceAutocomplete.RESULT_ERROR) {
                Status status = PlaceAutocomplete.getStatus(this, data);
                Log.e("", "Error: Status = " + status.toString());
            } else if (resultCode == RESULT_CANCELED) {
                // Indicates that the activity closed before a selection was made. For example if
                // the user pressed the back button.
            }

        }
        else if(requestCode==2)
        {
             message=data.getStringExtra("MESSAGE");
            category.setText(message);
        }
         else if(requestCode!=2){



        }

    }


//    @Override
//    public void onBackPressed() {
//        super.onBackPressed();
//        Intent intent=new Intent(getApplication(),MainActivity.class);
//        startActivity(intent);
//        finish();
//    }

    @Override
    public void onClick(View view) {
        if(view == fromDateEtxt) {
            fromDatePickerDialog.show();
        } else if(view == toDateEtxt) {
            toDatePickerDialog.show();
        }

    }


}
