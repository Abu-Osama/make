package makemyhall.app.dcmindia.com.makemyhalln3;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import makemyhall.app.dcmindia.com.makemyhalln3.listeners.RecyclerItemClickListener;
import makemyhall.app.dcmindia.com.makemyhalln3.pojo.Listnew;
import makemyhall.app.dcmindia.com.makemyhalln3.pojo.PojoBanner;
import makemyhall.app.dcmindia.com.makemyhalln3.pojo.PojoItemServices;

public class AllItemServices extends AppCompatActivity {


    RecyclerView recyclerView;
    ArrayList<PojoItemServices> pojoItemServices;
    private ProgressDialog progress;
    LinearLayout linearLayout;
    MyRecyclerAdapter myRecyclerAdapter;
    String lat1, lon1,category1;
    ProgressDialog progressDialog;
    private String lat = "";
    private String lon = "";

    //recyclerview

    public class MyRecyclerAdapter extends RecyclerView.Adapter<MyRecyclerAdapter.MyViewHolder> {

        @Override
        public MyRecyclerAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

            View v = getLayoutInflater().inflate(R.layout.allservices, parent, false);
            MyRecyclerAdapter.MyViewHolder myViewHolder = new MyRecyclerAdapter.MyViewHolder(v);
            return myViewHolder;
        }


        public void onBindViewHolder(final MyRecyclerAdapter.MyViewHolder holder, final int position) {
            final PojoItemServices get = pojoItemServices.get(position);
            holder.hallname.setText(get.getServicesitemname());
           // holder.halladd.setText(get.getHalladd());
            //holder.hallprice.setText("\u20B9" + get.getHallprice());
            //holder.hallnumber.setText("+91" + get.getHallnumber());

            //holder.quickbooking.setText(get.getdiscount());

            Glide.with(AllItemServices.this).load(get.getServicesimage()).into(holder.hallimage);



          holder.linearLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {


                    myRecyclerAdapter.notifyDataSetChanged();
                    String category1=holder.hallname.getText().toString();
                    Intent in=new Intent(getApplication(),DetailsActivity.class);
                    in.putExtra("lat",lat);
                    in.putExtra("lon",lon);
                    in.putExtra("cat",category1);
                    startActivity(in);

                }
            });

        }

        @Override
        public int getItemCount() {
            return pojoItemServices.size();
        }



        public class MyViewHolder extends RecyclerView.ViewHolder {

            public ImageView hallimage;
            //recyclerView.getRecycledViewPool().clear();

            public TextView hallname, halladd, hallprice, hallnumber, discountprice;
            RatingBar rating;
            LinearLayout linearLayout;
            public MyViewHolder(View itemView) {
                super(itemView);

                hallimage = (ImageView) itemView.findViewById(R.id.imageviewservices);
                hallname = (TextView) itemView.findViewById(R.id.serviceslistname);
                linearLayout=itemView.findViewById(R.id.linearlayoutitemlist);
//                halladd = (TextView) itemView.findViewById(R.id.textview2);
//                hallprice = (TextView) itemView.findViewById(R.id.textview3);
//                //hallnumber = (TextView) itemView.findViewById(R.id.textview4);
//                discountprice= (TextView) findViewById(R.id.discount);


            }
        }


    }


    public class MyAsyncTask extends AsyncTask<String,Void,String> {
        URL myUrl;
        HttpURLConnection httpURLConnection;
        InputStream inputStream;
        InputStreamReader inputStreamReader;
        BufferedReader bufferedReader;
        String line;
        StringBuilder stringBuilder;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

        }

        @Override
        protected String doInBackground(String... p1) {

            //12 write login for connceting to server  and get json data
            try {
                myUrl=new URL(p1[0]);
                httpURLConnection= (HttpURLConnection) myUrl.openConnection();
                inputStream=httpURLConnection.getInputStream();
                inputStreamReader=new InputStreamReader(inputStream);
                bufferedReader=new BufferedReader(inputStreamReader);
                stringBuilder=new StringBuilder();
                line=bufferedReader.readLine();

                while(line!=null){

                    stringBuilder.append(line);
                    line=bufferedReader.readLine();
                }
                return stringBuilder.toString();//return final result json data to onpost execute


            } catch (MalformedURLException e) {
                e.printStackTrace();
                Log.d("b/34","Url is inpropper");
            } catch (IOException e) {
                e.printStackTrace();
                Log.d("b/34","Network problem ");
            }


            return "sone ting went wrong";
        }


        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            try {
                JSONObject jsonObject = new JSONObject(s);

                JSONArray jsonArray = jsonObject.getJSONArray(Config.TAG_Hall_ok);
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject jc = jsonArray.getJSONObject(i);
                    String servicename = jc.getString("service_name");
                    String servicebanner = jc.getString("service_banner");

                    PojoItemServices pojo=new PojoItemServices();
                    pojo.setServicesitemname(servicename);
                    pojo.setServicesimage(servicebanner);
                    pojoItemServices.add(pojo);

                }
                myRecyclerAdapter.notifyDataSetChanged();

            } catch (JSONException e) {
                e.printStackTrace();
            }


        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_item_services);

        Intent intent=getIntent();
        Bundle bundle=intent.getExtras();
        lat=bundle.getString("lat");
        lon=bundle.getString("lon");


        new MyAsyncTask().execute("http://www.makemyhall.com/m/itemallservices.php");
        pojoItemServices = new ArrayList<PojoItemServices>();
        recyclerView = (RecyclerView) findViewById(R.id.recyclerviewservices);
        recyclerView.setHasFixedSize(true);
        myRecyclerAdapter = new MyRecyclerAdapter();
        recyclerView.setAdapter(myRecyclerAdapter);
        //recyclerView.getRecycledViewPool().clear();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplication(),
                LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);


    }
}
