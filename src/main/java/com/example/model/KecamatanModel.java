package com.example.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class KecamatanModel {
	private int id_kecamatan;
	private String kode_kecamatan;
	private String idKota;
	private String nama_kecamatan;
}