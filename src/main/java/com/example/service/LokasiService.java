package com.example.service;

import com.example.model.LokasiModel;
import com.example.model.KotaModel;
import java.util.List;

public interface LokasiService {
	
	LokasiModel selectLokasi(int id_kelurahan);
	
	LokasiModel selectKodeKec(String nama_kecamatan);
	
	LokasiModel selectIdKel(String nama_kelurahan);
	
	String selectKecKode(int id_kelurahan);
	
	String kodeKecamatan(int id_kelurahan);
	
	
}
