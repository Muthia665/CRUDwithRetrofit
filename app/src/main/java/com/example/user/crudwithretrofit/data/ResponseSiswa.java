package com.example.user.crudwithretrofit.data;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class ResponseSiswa{

	@SerializedName("siswa")
	private List<SiswaItem> siswa;

	public ResponseSiswa(List<SiswaItem> siswa) {
		this.siswa = siswa;
	}

	@SerializedName("pesan")
	private String pesan;

	@SerializedName("sukses")
	private boolean sukses;

	public void setSiswa(List<SiswaItem> siswa){
		this.siswa = siswa;
	}

	public List<SiswaItem> getSiswa(){
		return siswa;
	}

	public void setPesan(String pesan){
		this.pesan = pesan;
	}

	public String getPesan(){
		return pesan;
	}

	public void setSukses(boolean sukses){
		this.sukses = sukses;
	}

	public boolean isSukses(){
		return sukses;
	}
}