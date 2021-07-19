package com.example.user.crudwithretrofit;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.user.crudwithretrofit.data.ResponseSiswa;
import com.example.user.crudwithretrofit.data.SiswaItem;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    RecyclerView rc;
    ProgressBar progressBar;
    FloatingActionButton fab;

    List<ResponseSiswa> Responsedatasiswa = new ArrayList<>();
    List<SiswaItem> listsiswa = new ArrayList<>();

    ApiService apiService;
    AdapterList adapterList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        apiService = ConfigApi.getapiService();
        rc = findViewById(R.id.rc_list);
        progressBar = findViewById(R.id.pb);

        //new layout manager
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        rc.setLayoutManager(layoutManager);

        //set adapter
        adapterList = new AdapterList(listsiswa, getApplicationContext());
        rc.setAdapter(adapterList);
        
        dataAttachment();

        fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), DetailActivity.class);
                i.putExtra("nama", "");
                i.putExtra("alamat", "");
                i.putExtra("hp", "");
                i.putExtra("aksi", "tambah");
                startActivity(i);
            }
        });
    }

    private void dataAttachment() {

        Responsedatasiswa.clear();
        listsiswa.clear();
        apiService.readData().enqueue(new Callback<ResponseSiswa>() {
            @Override
            public void onResponse(Call<ResponseSiswa> call, Response<ResponseSiswa> response) {
                if (response.isSuccessful()) {
                    try {
                        int data = response.body().getSiswa().size();

                        for (int a =0; a < data; a++) {
                            SiswaItem model = new SiswaItem(response.body().getSiswa().get(a).getId(),
                                                            response.body().getSiswa().get(a).getNama(),
                                                            response.body().getSiswa().get(a).getAlamat(),
                                                            response.body().getSiswa().get(a).getHp());
                            listsiswa.add(model);
                        }
                        ResponseSiswa item = new ResponseSiswa(listsiswa);
                        Responsedatasiswa.add(item);

                        adapterList = new AdapterList(listsiswa, getApplicationContext());
                        rc.setAdapter(adapterList);
                        if (listsiswa.isEmpty()){
                            Toast.makeText(MainActivity.this, "Data tidak ada", Toast.LENGTH_SHORT).show();
                        }
                    } catch (NullPointerException e){
                        e.printStackTrace();
                    }
                } else {
                    Toast.makeText(MainActivity.this, "Server is down", Toast.LENGTH_SHORT).show();
                }
            } 

            @Override
            public void onFailure(Call<ResponseSiswa> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Server is down", Toast.LENGTH_SHORT).show();

            }
        });
    }
}
