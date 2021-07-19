package com.example.user.crudwithretrofit;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.user.crudwithretrofit.data.ResponseSiswa;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailActivity extends AppCompatActivity {

    EditText editnama, editalamat, edithp;
    String nama, alamat, hp, aksi, id;
    Button btnTambah, btnEdit, btnHapus;

    ApiService apiService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        apiService = ConfigApi.getapiService();

        editnama = findViewById(R.id.etnama);
        editalamat = findViewById(R.id.etalamat);
        edithp = findViewById(R.id.ethp);

        nama = getIntent().getStringExtra("nama");
        alamat = getIntent().getStringExtra("alamat");
        hp = getIntent().getStringExtra("hp");
        aksi = getIntent().getStringExtra("aksi");
        id = getIntent().getStringExtra("id");

        editnama.setText(nama);
        editalamat.setText(alamat);
        edithp.setText(hp);

        btnTambah = findViewById(R.id.btnTambah);
        btnEdit = findViewById(R.id.btnEdit);
        btnHapus = findViewById(R.id.btnHapus);

        if (aksi.equals("tambah")) {
            btnTambah.setVisibility(View.VISIBLE);
            btnEdit.setVisibility(View.GONE);
            btnHapus.setVisibility(View.GONE);
        } else {
            btnTambah.setVisibility(View.GONE);
            btnEdit.setVisibility(View.VISIBLE);
            btnHapus.setVisibility(View.VISIBLE);
        }

        btnTambah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String addnama = editnama.getText().toString();
                String addalamat = editalamat.getText().toString();
                String addhp = edithp.getText().toString();

                addData(addnama, addalamat, addhp);
            }
        });

        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String upnama = editnama.getText().toString();
                String upalamat = editalamat.getText().toString();
                String uphp = edithp.getText().toString();

                UpdateData(upnama, upalamat, uphp, id);
            }
        });

        btnHapus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DeleteData(id);
            }
        });
    }

    private void DeleteData(String id) {
        apiService.hapusData(id).enqueue(new Callback<ResponseSiswa>() {
            @Override
            public void onResponse(Call<ResponseSiswa> call, Response<ResponseSiswa> response) {
                if (response.isSuccessful()) {
                    if (response.body().isSukses()) {
                        Toast.makeText(DetailActivity.this, response.body().getPesan(), Toast.LENGTH_SHORT).show();
                        Intent i = new Intent(getApplicationContext(), MainActivity.class);
                        startActivity(i);
                        finish();
                    } else {
                        Toast.makeText(DetailActivity.this, response.body().getPesan(), Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(DetailActivity.this, "Server is down", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseSiswa> call, Throwable t) {
                Toast.makeText(DetailActivity.this, "Server is down onfail", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void UpdateData(String upnama, String upalamat, String uphp, String id) {
        apiService.editData(id, upnama, upalamat, uphp).enqueue(new Callback<ResponseSiswa>() {
            @Override
            public void onResponse(Call<ResponseSiswa> call, Response<ResponseSiswa> response) {
                if (response.isSuccessful()) {
                    if (response.body().isSukses()) {
                        Toast.makeText(DetailActivity.this, response.body().getPesan(), Toast.LENGTH_SHORT).show();
                        Intent i = new Intent(getApplicationContext(), MainActivity.class);
                        startActivity(i);
                        finish();
                    } else {
                        Toast.makeText(DetailActivity.this, response.body().getPesan(), Toast.LENGTH_SHORT).show();

                    }
                } else {
                    Toast.makeText(DetailActivity.this, "Server is down", Toast.LENGTH_SHORT).show();
                }

            }


            @Override
            public void onFailure(Call<ResponseSiswa> call, Throwable t) {
                Toast.makeText(DetailActivity.this, "Server is down on fail", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void addData(String addnama, String addalamat, String addhp) {
        apiService.addData(addnama, addalamat, addhp).enqueue(new Callback<ResponseSiswa>() {
            @Override
            public void onResponse(Call<ResponseSiswa> call, Response<ResponseSiswa> response) {
                if (response.isSuccessful()) {
                    if (response.body().isSukses()) {
                        Toast.makeText(DetailActivity.this, response.body().getPesan(), Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(DetailActivity.this, response.body().getPesan(), Toast.LENGTH_SHORT).show();
                        Intent i = new Intent(getApplicationContext(), MainActivity.class);
                        startActivity(i);
                        finish();
                    }
                } else {
                    Toast.makeText(DetailActivity.this, "Server is down", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<ResponseSiswa> call, Throwable t) {
                Toast.makeText(DetailActivity.this, "Server is down on fail", Toast.LENGTH_SHORT).show();
            }
        });
    }


}


