package com.example.carbid;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.carbid.app.App;
import com.example.carbid.authSave.TokensSave;
import com.example.carbid.model.save.BrandFullSave;
import com.example.carbid.model.save.ModelFullSave;
import com.example.carbid.model.save.YearFullSave;
import com.example.carbid.retrofit.CarApi;
import com.example.carbid.retrofit.RetrofitService;

import java.io.Serializable;

public class FullSearchActivity extends AppCompatActivity {
   public TextView textBrandTxt;
   public TextView textModelTxt;
   public TextView textYearBeforeTxt;
   public TextView textYearAfterTxt;
   CardView buttChooseBrandC;
   CardView buttChooseModelC;
   CardView buttChooseYearBeforeC;
   CardView buttChooseYearAfterC;
    Button buttFullSearchB;
    TokensSave tokensSave= new TokensSave();
    BrandFullSave brandFullSave = new BrandFullSave();
    ModelFullSave modelFullSave = new ModelFullSave();
    YearFullSave yearFullSave = new YearFullSave();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_full_search);
        textBrandTxt = findViewById(R.id.textBrand);
        textModelTxt = findViewById(R.id.textModel);
        textYearBeforeTxt = findViewById(R.id.textYearBefore);
        textYearAfterTxt = findViewById(R.id.textYearAfter);
        buttFullSearchB = findViewById(R.id.buttFullSearch);
        buttChooseBrandC = findViewById(R.id.buttChooseBrand);
        buttChooseModelC = findViewById(R.id.buttChooseModel);
        buttChooseYearBeforeC = findViewById(R.id.buttChooseYearBefore);
        buttChooseYearAfterC=findViewById(R.id.buttChooseYearAfter);
        Intent intent = new Intent(FullSearchActivity.this,FullItemSearchActivity.class);
        buttChooseBrandC.setOnClickListener(v -> {
            ((App)getApplicationContext()).setFullSearchActivity(this);
           intent.putExtra("butt","brand");
           startActivity(intent);
        });
        buttChooseModelC.setOnClickListener(v -> {
            if(!brandFullSave.getID_BrandFull(getApplicationContext()).equals(""))
            {  ((App)getApplicationContext()).setFullSearchActivity(this);
                intent.putExtra("butt","model");
            startActivity(intent);
            }
            else{
                Toast toast = Toast.makeText(getApplicationContext(),"Выберите марку автомобиля",Toast.LENGTH_LONG);
                toast.show();
            }
        });
        buttChooseYearBeforeC.setOnClickListener(v -> {
            ((App)getApplicationContext()).setFullSearchActivity(this);
            intent.putExtra("butt","yearBefore");
            startActivity(intent);
        });
        buttChooseYearAfterC.setOnClickListener(v -> {
            intent.putExtra("butt","yearAfter");
            startActivity(intent);
        });
        buttFullSearchB.setOnClickListener(v -> {
            if(!textBrandTxt.getText().toString().equals("")&&!textModelTxt.getText().toString().equals("")&&!textYearBeforeTxt.getText().toString().equals("")&&!textYearAfterTxt.getText().toString().equals("")) {
                Intent intFull = new Intent(FullSearchActivity.this, FullSearchListActivity.class);
                intFull.putExtra("id_model", modelFullSave.getID_ModelFull(getApplicationContext()));
                intFull.putExtra("yearBefore", yearFullSave.getYearBFull(getApplicationContext()));
                intFull.putExtra("yearAfter",yearFullSave.getYearAFull(getApplicationContext()));
                startActivity(intFull);
                brandFullSave.setID_BrandFull(getApplicationContext(), "");
                modelFullSave.setID_ModelFull(getApplicationContext(), "");
                yearFullSave.setYearBFull(getApplicationContext(), "");
            }
            else
            {
                Toast toast = Toast.makeText(getApplicationContext(),"Не все поля заполнены",Toast.LENGTH_LONG);
                toast.show();
            }
        });
    }
}