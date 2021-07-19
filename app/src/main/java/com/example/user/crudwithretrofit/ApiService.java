package com.example.user.crudwithretrofit;

import com.example.user.crudwithretrofit.data.ResponseSiswa;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ApiService {

    @GET("datasiswa.php")
    Call<ResponseSiswa> readData();

    @FormUrlEncoded
    @POST("tambahsiswa.php")
    Call<ResponseSiswa> addData(@Field("nama_siswa")String nama,
                                @Field("alamat_siswa")String alamat,
                                @Field("hp_siswa")String hp);

    @FormUrlEncoded
    @POST("editsiswa.php")
    Call<ResponseSiswa> editData(@Field("id_siswa") String id,
                                 @Field("nama_siswa")String nama,
                                 @Field("alamat_siswa")String alamat,
                                 @Field("hp_siswa")String hp);

    @FormUrlEncoded
    @POST("hapussiswa.php")
    Call<ResponseSiswa> hapusData (@Field("id_siswa") String id);
}
