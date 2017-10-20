package com.example.service;

import com.example.model.LokasiModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.dao.LokasiMapper;
import com.example.model.LokasiModel;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service

public class LokasiServiceDatabase implements LokasiService{

	@Autowired
	LokasiMapper lokasiMapper;
	
	@Override
	public LokasiModel selectLokasi(int id_kelurahan) {
		log.info("id_kelurahan" , id_kelurahan);
		return lokasiMapper.selectLokasi(id_kelurahan);
	}

	@Override
	public LokasiModel selectKodeKec(String nama_kecamatan) {
		// TODO Auto-generated method stub
		return lokasiMapper.selectKodeKec(nama_kecamatan);
	}

	@Override
	public LokasiModel selectIdKel(String nama_kelurahan) {
		// TODO Auto-generated method stub
		return lokasiMapper.selectIdKel(nama_kelurahan);
	}

	@Override
	public String selectKecKode(int id_kelurahan) {
		log.info("id_kelurahan" + id_kelurahan);
		return lokasiMapper.selectKecKode(id_kelurahan);
	}

	@Override
	public String kodeKecamatan(int id_kelurahan) {
		log.info("id_kelurahan" + id_kelurahan);
		return lokasiMapper.kodeKecamatan(id_kelurahan);
	}


	

}
