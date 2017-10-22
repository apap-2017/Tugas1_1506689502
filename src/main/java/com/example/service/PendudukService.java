package com.example.service;

import com.example.model.PendudukModel;
import com.example.model.KotaModel;
import com.example.model.KecamatanModel;
import com.example.model.KelurahanModel;

import java.util.List;

public interface PendudukService {
	PendudukModel selectPenduduk(String nik);
	
	List<PendudukModel> pilihKeluarga(int id);
	
	void addPenduduk(PendudukModel penduduk);
	
	void updatePenduduk(PendudukModel penduduk);
	
	int selectKeluargaId(String nik);
	
	List<KotaModel> selectKota();
	
	List<KecamatanModel> selectKecamatan(String nama_kota);
	
	List<KelurahanModel> selectKelurahan(int id_kecamatan);
	
	List<PendudukModel> selectIsWafat(String nik);
	
}
