package makemyhall.app.dcmindia.com.makemyhalln3;


import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.squareup.picasso.Picasso;

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

import makemyhall.app.dcmindia.com.makemyhalln3.pojo.PojoBanner;
import makemyhall.app.dcmindia.com.makemyhalln3.pojo.PojoService;


/**
 * A simple {@link Fragment} subclass.
 */
public class BannerFragment extends Fragment {


    RecyclerView recyclerView;
    ArrayList<PojoBanner> pojoServices;
    private ProgressDialog progress;
    private String lat = "12.9716";
    private String lon = "77.5946";

    RecyclerAdapter myRecyclerAdapter;



    public  class   RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.MyViewHolder>{


        @Override
        public RecyclerAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

            View v=getActivity().getLayoutInflater().inflate(R.layout.rowbanner,parent,false);
            RecyclerAdapter.MyViewHolder myViewHolder=new RecyclerAdapter.MyViewHolder(v);
            return myViewHolder;
        }

        @Override
        public void onBindViewHolder(final RecyclerAdapter.MyViewHolder holder, int position) {

            final PojoBanner pojo = pojoServices.get(position);
            holder.textViewservice.setText(pojo.getServicename());
            // holder.halladd.setText(pojo.getHalladd());
            //holder.hallprice.setText("\u20B9" + get.getHallprice());
            //holder.hallnumber.setText("+91" + get.getHallnumber());

            //holder.discountprice.setText(get.getdiscount());

           // Glide.with(BannerFragment.this).load(pojo.getBanner()).into(holder.imageView);
            Picasso.with(getActivity()).load(pojo.getBanner()).into(holder.imageView);

            holder.linearLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    String category1=holder.textViewservice.getText().toString();

                    Intent in=new Intent(getActivity(),DetailsActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putString("lat", lat);
                    bundle.putString("lon", lon);
                    bundle.putString("cat", category1);
                    in.putExtras(bundle);
                    startActivity(in);

                }
            });


        }

        @Override
        public int getItemCount() {
            return pojoServices.size();
        }

        public  class  MyViewHolder extends RecyclerView.ViewHolder{

            ImageView imageView;
            TextView textViewservice;
            LinearLayout linearLayout;


            public MyViewHolder(View itemView) {
                super(itemView);

                imageView= (ImageView) itemView.findViewById(R.id.serviceimage1);
                textViewservice= (TextView) itemView.findViewById(R.id.servicename1);
                linearLayout = (LinearLayout) itemView.findViewById(R.id.layoutid1);
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
//                    String halladd = jc.getString(Config.TAG_Address);
//                    String hallprice = jc.getString(Config.TAG_Price);
//                    String hallbanner = jc.getString(Config.TAG_Banner);
//                    String latitude=jc.getString("hall_latitude");
//                    String longitude=jc.getString("hall_longitute");
//                    String emailid=jc.getString("hall_email");
//                    String discount1=jc.getString("hall_price_dis");

                    PojoBanner pojo=new PojoBanner();
                    pojo.setServicename(servicename);
                    pojo.setBanner(servicebanner);
                    pojoServices.add(pojo);

                }

                myRecyclerAdapter.notifyDataSetChanged();
            } catch (JSONException e) {
                e.printStackTrace();
            }


        }
    }


    public BannerFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.fragment_banner, container, false);


        new MyAsyncTask().execute("http://www.makemyhall.com/m/hallbanner.php");
        pojoServices = new ArrayList<PojoBanner>();
        recyclerView = (RecyclerView) v.findViewById(R.id.servicerecyclerviewbanner);
        recyclerView.setHasFixedSize(true);
        recyclerView.setItemViewCacheSize(20);
        myRecyclerAdapter= new RecyclerAdapter();
        recyclerView.setAdapter(myRecyclerAdapter);
        //recyclerView.getRecycledViewPool().clear();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(),
                LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
        return v;
    }


    @Override
    public void onPause() {
        super.onPause();
        myRecyclerAdapter.notifyDataSetChanged();
    }

    @Override
    public void onResume() {
        super.onResume();
        myRecyclerAdapter.notifyDataSetChanged();
    }
}
