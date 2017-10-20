package com.example.dao;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Many;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.example.model.LokasiModel;

@Mapper
public interface LokasiMapper {
	@Select("select kel.nama_kelurahan,kec.nama_kecamatan,kt.nama_kota "
			+ "from kelurahan kel,kecamatan kec,kota kt "
			+ "where kel.id_kecamatan = kec.id AND kec.id_kota = kt.id AND kel.id = #{id_kelurahan}")
	LokasiModel selectLokasi(@Param("id_kelurahan") int id_kelurahan);
	
	@Select("select kode_kecamatan from kecamatan where nama_kecamatan = #{nama_kecamatan}")
	LokasiModel selectKodeKec(@Param("nama_kecamatan") String nama_kecamatan);
	
	@Select("select id as id_kelurahan from kelurahan where nama_kelurahan = #{nama_kelurahan}")
	LokasiModel selectIdKel(@Param("nama_kelurahan") String nama_kelurahan);
	
	@Select("select kode_kecamatan from kecamatan kec,kelurahan kel where kec.id = kel.id_kecamatan and "
			+ "kel.id = #{id_kelurahan}")
	String selectKecKode(int id_kelurahan);
	
	@Select("select id_kelurahan from kecamatan kec,kelurahan kel,keluarga k "
			+ "where kel.id = #{id_kelurahan} and kel.id = k.id_kelurahan and kec.id = kel.id_kecamatan")
	String kodeKecamatan(int id_kelurahan);
}
