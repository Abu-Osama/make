package makemyhall.app.dcmindia.com.makemyhalln3;

import android.content.Intent;
import android.hardware.camera2.CameraManager;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.test.suitebuilder.TestMethod;
import android.view.View;
import android.widget.TextView;

import com.bumptech.glide.GifTypeRequest;

import butterknife.BindView;
import butterknife.ButterKnife;
import makemyhall.app.dcmindia.com.makemyhalln3.pojo.PojoItemServices;

/**
 * Created by Abu Osama on 04-12-2017.
 */

public class CategoryList extends AppCompatActivity {


   private TextView textViewmarriage,textViewpartyhall,textViewexhi,textViewconference,textViewmeeting,textViewevent;

    @Nullable
    @BindView(R.id.Bandset)TextView Bandset;
    @Nullable
    @BindView(R.id.beautyparlor)TextView beautyparlor;
    @Nullable
    @BindView(R.id.caterers)TextView caterers;
    @Nullable
    @BindView(R.id.coconutmerchants)TextView coconutmerchants;
    @Nullable
    @BindView(R.id.coffeesuppliers)TextView coffeesuppliers;
    @Nullable
    @BindView(R.id.condiments)TextView condiments;
    @Nullable
    @BindView(R.id.dairyproduct)TextView dairyproduct;
    @Nullable
    @BindView(R.id.detectiveservices)TextView detectiveservices;
    @Nullable
    @BindView(R.id.entertainment)TextView entertainment;
    @Nullable
    @BindView(R.id.ethicwear)TextView ethicwear;
    @Nullable
    @BindView(R.id.flowerdecorators)TextView flowerdecorators;
    @Nullable
    @BindView(R.id.fruitmechants)TextView fruitmechants;

    @Nullable
    @BindView(R.id.furniture)TextView furniture;
    @Nullable
    @BindView(R.id.gasequipments)TextView gasequipments;
    @Nullable
    @BindView(R.id.generatorhire)TextView generatorhire;
    @Nullable
    @BindView(R.id.GentsBeautySaloon)TextView GentsBeautySaloon;
    @Nullable
    @BindView(R.id.GiftItems)TextView GiftItems;

    @Nullable
    @BindView(R.id.HorseProviders)TextView HorseProviders;
    @Nullable
    @BindView(R.id.Hotels)TextView Hotels;
    @Nullable
    @BindView(R.id.IcecreamSuppliers)TextView IcecreamSuppliers;
    @Nullable
    @BindView(R.id.JewelleryHires)TextView JewelleryHires;
    @Nullable
    @BindView(R.id.Ligths)TextView Ligths;
    @Nullable
    @BindView(R.id.MatrimonialServices)TextView MatrimonialServices;
    @Nullable
    @BindView(R.id.mehendi)TextView mehendi;
    @Nullable
    @BindView(R.id.menssuiting)TextView menssuiting;
    @Nullable
    @BindView(R.id.MenTailoring)TextView MenTailoring;
    @Nullable
    @BindView(R.id.MilkSuppliers)TextView MilkSuppliers;
    @Nullable
    @BindView(R.id.MineralWaterSuppliers)TextView MineralWaterSuppliers;
    @Nullable
    @BindView(R.id.Nadaswara)TextView Nadaswara;

    @Nullable
    @BindView(R.id.NurseryFlowerPots)TextView NurseryFlowerPots;
    @Nullable
    @BindView(R.id.Orchestra)TextView Orchestra;
    @Nullable
    @BindView(R.id.PanLeaf)TextView PanLeaf;
    @Nullable
    @BindView(R.id.Photographers)TextView Photographers;
    @Nullable
    @BindView(R.id.PlantainLeaf)TextView PlantainLeaf;
    @Nullable
    @BindView(R.id.PlasticPaperbags)TextView PlasticPaperbags;
    @Nullable
    @BindView(R.id.PoojaItems)TextView PoojaItems;
    @Nullable
    @BindView(R.id.Printers)TextView Printers;
    @Nullable
    @BindView(R.id.Provisions)TextView Provisions;
    @Nullable
    @BindView(R.id.Resorts)TextView Resorts;
    @Nullable
    @BindView(R.id.SilkSarees)TextView SilkSarees;
    @Nullable
    @BindView(R.id.SoftDrinks)TextView SoftDrinks;
    @Nullable
    @BindView(R.id.Sweets)TextView Sweets;
    @Nullable
    @BindView(R.id.TentsSuppliers)TextView TentsSuppliers;
    @Nullable
    @BindView(R.id.ToursTravellers)TextView ToursTravellers;
    @Nullable
    @BindView(R.id.Turbans)TextView Turbans;
    @Nullable
    @BindView(R.id.Videography)TextView Videography;
    @Nullable
    @BindView(R.id.WaterSuppliers)TextView WaterSuppliers;
    @Nullable
    @BindView(R.id.WeddingCards)TextView WeddingCards;









    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.categoryxml);
        ButterKnife.bind(this);
        textViewmarriage=findViewById(R.id.marriagehall1);
        textViewpartyhall=findViewById(R.id.partyhall2);
        textViewexhi=findViewById(R.id.exhibitionhall3);
        textViewconference=findViewById(R.id.conferencehall4);
        textViewmeeting=findViewById(R.id.meetinghall5);
        textViewevent=findViewById(R.id.eventhall6);

        textViewmarriage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String text=textViewmarriage.getText().toString();
                Intent intent=new Intent();
                intent.putExtra("MESSAGE",text);
                setResult(2,intent);
                finish();//finishing activity
            }
        });

        textViewpartyhall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String text=textViewpartyhall.getText().toString();
                Intent intent=new Intent();
                intent.putExtra("MESSAGE",text);
                setResult(2,intent);
                finish();//finishing activity
            }
        });

        textViewexhi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String text=textViewexhi.getText().toString();
                Intent intent=new Intent();
                intent.putExtra("MESSAGE",text);
                setResult(2,intent);
                finish();//finishing activity
            }
        });

        textViewconference.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String text=textViewconference.getText().toString();
                Intent intent=new Intent();
                intent.putExtra("MESSAGE",text);
                setResult(2,intent);
                finish();//finishing activity
            }
        });

        textViewmeeting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String text=textViewmeeting.getText().toString();
                Intent intent=new Intent();
                intent.putExtra("MESSAGE",text);
                setResult(2,intent);
                finish();//finishing activity
            }
        });

        textViewevent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String text=textViewevent.getText().toString();
                Intent intent=new Intent();
                intent.putExtra("MESSAGE",text);
                setResult(2,intent);
                finish();//finishing activity
            }
        });
        Bandset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String text=Bandset.getText().toString();
                Intent intent=new Intent();
                intent.putExtra("MESSAGE",text);
                setResult(2,intent);
                finish();//finishing activity

            }
        });

        beautyparlor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String text=beautyparlor.getText().toString();
                Intent intent=new Intent();
                intent.putExtra("MESSAGE",text);
                setResult(2,intent);
                finish();//finishing activity

            }
        });

        caterers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String text=caterers.getText().toString();
                Intent intent=new Intent();
                intent.putExtra("MESSAGE",text);
                setResult(2,intent);
                finish();//finishing activity

            }
        });

        coconutmerchants.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String text=coconutmerchants.getText().toString();
                Intent intent=new Intent();
                intent.putExtra("MESSAGE",text);
                setResult(2,intent);
                finish();//finishing activity
            }
        });

        coffeesuppliers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String text=coffeesuppliers.getText().toString();
                Intent intent=new Intent();
                intent.putExtra("MESSAGE",text);
                setResult(2,intent);
                finish();//finishing activity
            }
        });
        condiments.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String text=condiments.getText().toString();
                Intent intent=new Intent();
                intent.putExtra("MESSAGE",text);
                setResult(2,intent);
                finish();//finishing activity
            }
        });

        dairyproduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String text=dairyproduct.getText().toString();
                Intent intent=new Intent();
                intent.putExtra("MESSAGE",text);
                setResult(2,intent);
                finish();//finishing activity
            }
        });

        detectiveservices.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String text=detectiveservices.getText().toString();
                Intent intent=new Intent();
                intent.putExtra("MESSAGE",text);
                setResult(2,intent);
                finish();//finishing activity
            }
        });
        entertainment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String text=entertainment.getText().toString();
                Intent intent=new Intent();
                intent.putExtra("MESSAGE",text);
                setResult(2,intent);
                finish();//finishing activity
            }
        });

        ethicwear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String text=ethicwear.getText().toString();
                Intent intent=new Intent();
                intent.putExtra("MESSAGE",text);
                setResult(2,intent);
                finish();//finishing activity
            }
        });
        flowerdecorators.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String text=flowerdecorators.getText().toString();
                Intent intent=new Intent();
                intent.putExtra("MESSAGE",text);
                setResult(2,intent);
                finish();//finishing activity
            }
        });

        fruitmechants.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String text=fruitmechants.getText().toString();
                Intent intent=new Intent();
                intent.putExtra("MESSAGE",text);
                setResult(2,intent);
                finish();//finishing activity
            }
        });

        furniture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String text=furniture.getText().toString();
                Intent intent=new Intent();
                intent.putExtra("MESSAGE",text);
                setResult(2,intent);
                finish();//finishing activity
            }
        });

        gasequipments.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String text=gasequipments.getText().toString();
                Intent intent=new Intent();
                intent.putExtra("MESSAGE",text);
                setResult(2,intent);
                finish();//finishing activity

            }
        });
        generatorhire.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String text=generatorhire.getText().toString();
                Intent intent=new Intent();
                intent.putExtra("MESSAGE",text);
                setResult(2,intent);
                finish();//finishing activity
            }
        });
        GentsBeautySaloon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String text=GentsBeautySaloon.getText().toString();
                Intent intent=new Intent();
                intent.putExtra("MESSAGE",text);
                setResult(2,intent);
                finish();//finishing activity
            }
        });

        GiftItems.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String text=GiftItems.getText().toString();
                Intent intent=new Intent();
                intent.putExtra("MESSAGE",text);
                setResult(2,intent);
                finish();//finishing activity
            }
        });

        HorseProviders.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String text=HorseProviders.getText().toString();
                Intent intent=new Intent();
                intent.putExtra("MESSAGE",text);
                setResult(2,intent);
                finish();//finishing activity
            }
        });

        Hotels.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String text=Hotels.getText().toString();
                Intent intent=new Intent();
                intent.putExtra("MESSAGE",text);
                setResult(2,intent);
                finish();//finishing activity
            }
        });

        IcecreamSuppliers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String text=IcecreamSuppliers.getText().toString();
                Intent intent=new Intent();
                intent.putExtra("MESSAGE",text);
                setResult(2,intent);
                finish();//finishing activity
            }
        });
        JewelleryHires.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String text=JewelleryHires.getText().toString();
                Intent intent=new Intent();
                intent.putExtra("MESSAGE",text);
                setResult(2,intent);
                finish();//finishing activity
            }
        });

        Ligths.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String text=Ligths.getText().toString();
                Intent intent=new Intent();
                intent.putExtra("MESSAGE",text);
                setResult(2,intent);
                finish();//finishing activity
            }
        });

        MatrimonialServices.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String text=MatrimonialServices.getText().toString();
                Intent intent=new Intent();
                intent.putExtra("MESSAGE",text);
                setResult(2,intent);
                finish();//finishing activity
            }
        });

        mehendi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String text=mehendi.getText().toString();
                Intent intent=new Intent();
                intent.putExtra("MESSAGE",text);
                setResult(2,intent);
                finish();//finishing activity
            }
        });
        menssuiting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String text=menssuiting.getText().toString();
                Intent intent=new Intent();
                intent.putExtra("MESSAGE",text);
                setResult(2,intent);
                finish();//finishing activity
            }
        });
        MenTailoring.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String text=MenTailoring.getText().toString();
                Intent intent=new Intent();
                intent.putExtra("MESSAGE",text);
                setResult(2,intent);
                finish();//finishing activity
            }
        });

        MilkSuppliers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String text=MilkSuppliers.getText().toString();
                Intent intent=new Intent();
                intent.putExtra("MESSAGE",text);
                setResult(2,intent);
                finish();//finishing activity
            }
        });

        MineralWaterSuppliers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String text=MineralWaterSuppliers.getText().toString();
                Intent intent=new Intent();
                intent.putExtra("MESSAGE",text);
                setResult(2,intent);
                finish();//finishing activity
            }
        });

        Nadaswara.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String text=Nadaswara.getText().toString();
                Intent intent=new Intent();
                intent.putExtra("MESSAGE",text);
                setResult(2,intent);
                finish();//finishing activity
            }
        });

        NurseryFlowerPots.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String text=NurseryFlowerPots.getText().toString();
                Intent intent=new Intent();
                intent.putExtra("MESSAGE",text);
                setResult(2,intent);
                finish();//finishing activity
            }
        });

        Orchestra.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String text=Orchestra.getText().toString();
                Intent intent=new Intent();
                intent.putExtra("MESSAGE",text);
                setResult(2,intent);
                finish();//finishing activity
            }
        });

        PanLeaf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String text=PanLeaf.getText().toString();
                Intent intent=new Intent();
                intent.putExtra("MESSAGE",text);
                setResult(2,intent);
                finish();//finishing activity
            }
        });

        Photographers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String text=Photographers.getText().toString();
                Intent intent=new Intent();
                intent.putExtra("MESSAGE",text);
                setResult(2,intent);
                finish();//finishing activity
            }
        });

        PlantainLeaf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String text=PlantainLeaf.getText().toString();
                Intent intent=new Intent();
                intent.putExtra("MESSAGE",text);
                setResult(2,intent);
                finish();//finishing activity
            }
        });

        PlasticPaperbags.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String text=PlasticPaperbags.getText().toString();
                Intent intent=new Intent();
                intent.putExtra("MESSAGE",text);
                setResult(2,intent);
                finish();//finishing activity
            }
        });

        PoojaItems.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String text= PoojaItems.getText().toString();
                Intent intent=new Intent();
                intent.putExtra("MESSAGE",text);
                setResult(2,intent);
                finish();//finishing activity
            }
        });

        Printers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String text=Printers.getText().toString();
                Intent intent=new Intent();
                intent.putExtra("MESSAGE",text);
                setResult(2,intent);
                finish();//finishing activity
            }
        });

        Provisions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String text=Provisions.getText().toString();
                Intent intent=new Intent();
                intent.putExtra("MESSAGE",text);
                setResult(2,intent);
                finish();//finishing activity
            }
        });

        Resorts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String text=Resorts.getText().toString();
                Intent intent=new Intent();
                intent.putExtra("MESSAGE",text);
                setResult(2,intent);
                finish();//finishing activity
            }
        });
        SilkSarees.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String text=SilkSarees.getText().toString();
                Intent intent=new Intent();
                intent.putExtra("MESSAGE",text);
                setResult(2,intent);
                finish();//finishing activity
            }
        });

        SoftDrinks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String text=SoftDrinks.getText().toString();
                Intent intent=new Intent();
                intent.putExtra("MESSAGE",text);
                setResult(2,intent);
                finish();//finishing activity
            }
        });
        Sweets.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String text=Sweets.getText().toString();
                Intent intent=new Intent();
                intent.putExtra("MESSAGE",text);
                setResult(2,intent);
                finish();//finishing activity
            }
        });

        TentsSuppliers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String text= TentsSuppliers.getText().toString();
                Intent intent=new Intent();
                intent.putExtra("MESSAGE",text);
                setResult(2,intent);
                finish();//finishing activity
            }
        });
        ToursTravellers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String text= ToursTravellers.getText().toString();
                Intent intent=new Intent();
                intent.putExtra("MESSAGE",text);
                setResult(2,intent);
                finish();//finishing activity
            }
        });

        Turbans.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String text=Turbans.getText().toString();
                Intent intent=new Intent();
                intent.putExtra("MESSAGE",text);
                setResult(2,intent);
                finish();//finishing activity
            }
        });

        Videography.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String text=Videography.getText().toString();
                Intent intent=new Intent();
                intent.putExtra("MESSAGE",text);
                setResult(2,intent);
                finish();//finishing activity
            }
        });
        WaterSuppliers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String text=WaterSuppliers.getText().toString();
                Intent intent=new Intent();
                intent.putExtra("MESSAGE",text);
                setResult(2,intent);
                finish();//finishing activity
            }
        });
        WeddingCards.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String text=WeddingCards.getText().toString();
                Intent intent=new Intent();
                intent.putExtra("MESSAGE",text);
                setResult(2,intent);
                finish();//finishing activity
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent=new Intent(getApplication(),ActivityForLocation.class);
        startActivity(intent);
        finish();

    }
}
