package com.example.service;

import com.example.model.KeluargaModel;

public interface KeluargaService {

	KeluargaModel selectKeluarga(int id);

	KeluargaModel selectKeluargaKK(String nomor_kk);
	
	//String countNKK(String tanggalSekarang);
	
	void addKeluarga(KeluargaModel keluarga);
	
	int selectIdKelurahan(int id_keluarga);
	
	void updateKeluarga(KeluargaModel keluarga);
	
	int selectIdKel(String nomor_kk);
	
}
