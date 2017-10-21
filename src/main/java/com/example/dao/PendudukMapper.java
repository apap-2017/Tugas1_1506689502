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

import com.example.model.KeluargaModel;
import com.example.model.PendudukModel;

import java.util.List;

@Mapper
public interface PendudukMapper {
	@Select("select * from penduduk where nik = #{nik}")
	PendudukModel selectPenduduk (@Param("nik") String nik);
	
	// cari penduduk dengan id keluarga
	@Select("select * from penduduk where id_keluarga = #{id}")
	List<PendudukModel> pilihKeluarga(@Param("id") int id);
	
//	@Insert("insert into penduduk (nik,nama,tempat_lahir,tanggal_lahir,jenis_kelamin,is_wni,id_keluarga,agama,pekerjaan,"
//			+ "status_perkawinan,status_dalam_keluarga,golongan_darah,is_wafat) "
//			+ "values ('${nik}','${nama}','${tempat_lahir}','${tanggal_lahir}','${jenis_kelamin}','${is_wni}',,'${id_keluarga}'"
//			+ ",'${agama}' ,'${pekerjaan}' ,'${status_perkawinan}' ,'${status_dalam_keluarga}' ,'${golongan_darah}' ,'${is_wafat}')")
//	void addPenduduk(PendudukModel penduduk);
	
	@Insert("INSERT INTO penduduk (nik, jenis_kelamin, nama, tempat_lahir, tanggal_lahir, golongan_darah, agama, "
			   + "status_perkawinan, pekerjaan, is_wni, is_wafat, id_keluarga, status_dalam_keluarga) "
			   + "VALUES (#{nik}, #{jenis_kelamin}, #{nama}, #{tempat_lahir}, "
			   + "#{tanggal_lahir}, #{golongan_darah}, #{agama}, #{status_perkawinan}, #{pekerjaan}, "
			   + "#{is_wni}, #{is_wafat},#{id_keluarga},#{status_dalam_keluarga})")
	void addPenduduk(PendudukModel penduduk);
	
	@Update("update penduduk set nik = #{nik}, is_wni = #{is_wni}, agama = #{agama} , pekerjaan = #{pekerjaan} , status_perkawinan = #{status_perkawinan} ,"
			+ "status_dalam_keluarga = #{status_dalam_keluarga} , is_wafat = #{is_wafat} , id_keluarga = #{id_keluarga} where id = #{id}")
	void UpdatePenduduk(PendudukModel penduduk);
	
	@Select("select id_keluarga from penduduk where nik = #{nik}")
	int selectKeluargaId(String nik);
	
}
