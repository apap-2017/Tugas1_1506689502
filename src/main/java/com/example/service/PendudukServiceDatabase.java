package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.dao.PendudukMapper;
import com.example.model.KecamatanModel;
import com.example.model.KelurahanModel;
import com.example.model.KotaModel;
import com.example.model.PendudukModel;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service

public class PendudukServiceDatabase implements PendudukService {

	@Autowired
    private PendudukMapper pendudukMapper;

	@Override
	public PendudukModel selectPenduduk(String nik) {
		log.info("nik" , nik);
		return pendudukMapper.selectPenduduk(nik);
	}

	@Override
	public List<PendudukModel> pilihKeluarga(int id) {
		log.info("id" , id);
		return pendudukMapper.pilihKeluarga(id);
	}

	@Override
	public void addPenduduk(PendudukModel penduduk) {
		log.info("penduduk" , penduduk);
		pendudukMapper.addPenduduk(penduduk);
	}

	@Override
	public void updatePenduduk(PendudukModel penduduk) {
		log.info("penduduk" , penduduk);
		pendudukMapper.UpdatePenduduk(penduduk);
		
	}

	@Override
	public int selectKeluargaId(String nik) {
		log.info("nik" , nik);
		return pendudukMapper.selectKeluargaId(nik);
	}

	@Override
	public List<KotaModel> selectKota() {
		// TODO Auto-generated method stub
		return pendudukMapper.selectKota();
	}

	@Override
	public List<PendudukModel> selectIsWafat(String nik) {
		// TODO Auto-generated method stub
		return pendudukMapper.selectIsWafat(nik);
	}

	@Override
	public List<KecamatanModel> selectKecamatan(String nama_kota) {
		// TODO Auto-generated method stub
		return pendudukMapper.selectKecamatan(nama_kota);
	}

	@Override
	public List<KelurahanModel> selectKelurahan(int id_kecamatan) {
		// TODO Auto-generated method stub
		return pendudukMapper.selectKelurahan(id_kecamatan);
	}

	
	

		
	
}
