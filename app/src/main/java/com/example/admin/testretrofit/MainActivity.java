package com.example.admin.testretrofit;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    final static String TAG = "get";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button requestHttp = findViewById(R.id.request);
        final TextView requestResult = findViewById(R.id.editText);
        final StringBuilder tempItem = null;
        requestHttp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl("http://api.icndb.com/jokes/")
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();

                ServerAPI request = retrofit.create(ServerAPI.class);

                Call<List<Item>> items = request.getProducts();
                items.enqueue(new Callback<List<Item>>() {
                    @Override
                    public void onResponse(Call<List<Item>> call, Response<List<Item>> response) {

                        List<Item> itemList = response.body();
                        for (Item e: itemList){
                            tempItem.append(e.toString());

                        }

                    }

                    @Override
                    public void onFailure(Call<List<Item>> call, Throwable t) {
                            t.printStackTrace();
                    }
                });



                Log.d(TAG, String.valueOf(tempItem));
                requestResult.setText(tempItem);


            }
        });
    }
}
