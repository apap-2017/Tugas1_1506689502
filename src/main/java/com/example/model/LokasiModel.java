package com.example.model;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class LokasiModel {

	private int id_kelurahan;
	private String nama_kelurahan;
	private int id_kecamatan;
	private String nama_kecamatan;
	private int id_kota;
	private String nama_kota;
	private String kode_kecamatan;
	private String kode_kelurahan;
	private String kode_kota;
	
}
