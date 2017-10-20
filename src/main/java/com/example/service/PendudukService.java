package com.example.service;

import com.example.model.PendudukModel;

import java.util.List;

public interface PendudukService {
	PendudukModel selectPenduduk(String nik);
	
	List<PendudukModel> pilihKeluarga(int id);
	
	void addPenduduk(PendudukModel penduduk);
	
	void updatePenduduk(PendudukModel penduduk);
	
}
