package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.dao.KeluargaMapper;
import com.example.model.KeluargaModel;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service

public class KeluargaServiceDatabase implements KeluargaService{
	
	@Autowired
    private KeluargaMapper keluargaMapper;

	@Override
	public KeluargaModel selectKeluarga(int id) {
		log.info("id keluarga" + id);
		return keluargaMapper.selectKeluarga(id);
	}

	@Override
	public KeluargaModel selectKeluargaKK(String nomor_kk) {
		log.info("nomor kk" + nomor_kk);
		return keluargaMapper.selectKeluargaKK(nomor_kk);
	}

	

	@Override
	public void addKeluarga(KeluargaModel keluarga) {
		log.info("keluarga" + keluarga);
		keluargaMapper.addKeluarga(keluarga);
	}

	@Override
	public int selectIdKelurahan(int id_keluarga) {
		log.info("id_keluarga" + id_keluarga);
		return keluargaMapper.selectIdKelurahan(id_keluarga);
	}

	@Override
	public void updateKeluarga(KeluargaModel keluarga) {
		log.info("keluarga" + keluarga);
		keluargaMapper.updateKeluarga(keluarga);
		
	}

	
	

	

	

	
	
	
	
	
}
