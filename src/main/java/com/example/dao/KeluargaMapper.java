package com.example.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Many;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.example.model.PendudukModel;
import com.example.model.KeluargaModel;


@Mapper
public interface KeluargaMapper {
	@Select("select * from keluarga where id = #{id}")
	KeluargaModel selectKeluarga(@Param("id") int id);
	
	@Select("select * from keluarga where nomor_kk = #{nomor_kk}")
	KeluargaModel selectKeluargaKK(@Param("nomor_kk") String nomor_kk);
	
	//@Select("select nomor_kk from keluarga where nomor_kk like '% #{tanggalSekarang} %' order by desc limit 1 ")
	//String countNKK(String tanggalSekarang);
	
	@Insert("insert into keluarga (nomor_kk,alamat,rt,rw,id_kelurahan,is_tidak_berlaku) "
			+ "values ('${nomor_kk}','${alamat}','${rt}','${rw}','${id_kelurahan}',0)")
	void addKeluarga(KeluargaModel keluarga);
	
	@Select("select id_kelurahan from keluarga where id = #{id_keluarga}")
	int selectIdKelurahan(int id_keluarga);
	
	@Update("update keluarga set nomor_kk = #{nomor_kk} , alamat = #{alamat} , rt = #{rt} , rw = #{rw},"
			+ "id_kelurahan = #{id_kelurahan} where id = #{id}")
	void updateKeluarga(KeluargaModel keluarga);
	
	@Select("select id_kelurahan from keluarga where nomor_kk = #{nomor_kk}")
	int selectIdKel(String nomor_kk);
	
	
}
