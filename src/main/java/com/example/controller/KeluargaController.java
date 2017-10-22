package com.example.controller;

import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.model.KeluargaModel;
import com.example.model.LokasiModel;
import com.example.model.PendudukModel;
import com.example.service.KeluargaService;
import com.example.service.LokasiService;
import com.example.service.PendudukService;

@Controller
public class KeluargaController {

	@Autowired
	PendudukService pendudukDAO;
	@Autowired
	KeluargaService keluargaDAO;
	@Autowired
	LokasiService lokasiDAO;
	
	@RequestMapping("/keluarga")
	public String submit(@RequestParam(value = "nomor_kk" , required = false) String nomor_kk, Model model) 
	{
		
		KeluargaModel keluarga = keluargaDAO.selectKeluargaKK(nomor_kk);
		String nkk = keluarga.getNomor_kk();
		LokasiModel lokasi = lokasiDAO.selectLokasi(keluarga.getId_kelurahan());
		List<PendudukModel> penduduks = pendudukDAO.pilihKeluarga(keluarga.getId());
		
		if(keluarga != null) {
			model.addAttribute("keluarga" ,keluarga);
			model.addAttribute("lokasi" , lokasi);
			model.addAttribute("penduduks" , penduduks);
			return "hasil-cari-keluarga";
		}
		else {
			model.addAttribute("nomor_kk", nomor_kk);
		return "nik-null";
		}
	}
	
	@RequestMapping("/keluarga/tambah")
	public String form() {
		return "add-keluarga";
	}
	
	@RequestMapping(value = "/keluarga/tambah/submit" , method = RequestMethod.POST)
	public String addKeluarga(
			@RequestParam(value = "alamat" , required = false) String alamat,
			@RequestParam(value = "rt" , required = false) String rt,
			@RequestParam(value = "rw" , required = false) String rw,
			@RequestParam(value = "nama_kelurahan" , required = false) String nama_kelurahan,
			@RequestParam(value = "nama_kecamatan" , required = false) String nama_kecamatan,
			@RequestParam(value = "nama_kota" , required = false) String nama_kota, Model model) {
		

		//buat ambil kode kecamatan dari nama kecamatan yang di insert
		LokasiModel kode_kec = lokasiDAO.selectKodeKec(nama_kecamatan);
		
		//buat ambil id kelurahan dari nama kelurahan yang di insert
		LokasiModel id_kel = lokasiDAO.selectIdKel(nama_kelurahan);
		//model.addAttribute("nama_kelurahan", nama_kelurahan);
		
		//return "cek";
		
		// id kelurahan
	    int id_kelurahan = id_kel.getId_kelurahan(); // null
	    //System.out.println(id_kelurahan);
	    //String nama = id_kel.getNama_kelurahan();
	    //model.addAttribute("idKel", idKel);
	    //return "cek";
	    
		//6digit awal
		String kode_kecamatan = kode_kec.getKode_kecamatan().substring(0,6);
		//System.out.println(kode_kecamatan);
		
		// 4digit akhir
		//int count = noKK.getCount();
		//String formatted = String.format("%04d", count+1);
		
		// 6 digit tanggal daftra
		String x = LocalDate.now().toString();
	    String tahun = x.substring(2,4);
	    String bulan = x.substring(5,7);
	    String hari = x.substring(8,10);
	    String tanggalSekarang = hari+bulan+tahun;
	    
	    String nkk_blmjadi = kode_kecamatan + tanggalSekarang;
	    //System.out.println(nkk_blmjadi);
	    
	    int i = 1;
	    String cekSama = "000" + 1;
	    KeluargaModel cek_sama = keluargaDAO.selectKeluargaKK(nkk_blmjadi + cekSama);
	    while (cek_sama != null) {
	    	i++;
	    	if(i <= 9) {
	    		cekSama = "000" + i;
	    	}
	    	else if(i > 9 && i <= 99) {
	    		cekSama = "00" + i;
	    	}
	    	else {
	    		cekSama = "0" + i;
	    	}
	    	cek_sama = keluargaDAO.selectKeluargaKK(nkk_blmjadi+cekSama);
	    }
	    String nomor_kk = nkk_blmjadi + cekSama;
	    //System.out.println(nomor_kk);
	    KeluargaModel keluargaBaru = new KeluargaModel();
	    keluargaBaru.setNomor_kk(nomor_kk);
	    model.addAttribute("nomor_kk" , nomor_kk);
	    keluargaBaru.setAlamat(alamat);
	    keluargaBaru.setId_kelurahan(id_kelurahan);
	    keluargaBaru.setRt(rt);
	    keluargaBaru.setRw(rw);
	    keluargaDAO.addKeluarga(keluargaBaru);
	    //keluarga.setNomor_kk(new_nkk);
	    
	    //String new_nkk = String.
	    //ngecount no kk nya buat 4 digit terakhir
	 	//String countNKK = keluargaDAO.countNKK(tanggalSekarang);
	 	
	 	//int lastNKK = Integer.parseInt(countNKK);
	 	//String formatted = String.format("%04d", lastNKK+1);
	
	 	//nkk udh jadi
	    //String generate_nkk = kode_kecamatan + tanggalSekarang + formatted;
	    
	  
	    
	    //KeluargaModel keluarga = new KeluargaModel (0,new_nkk,alamat,rt,rw,idKel,0);
	    
	    
	    return "sukses-add-kel";
	  
	    
	}
	
	@RequestMapping(value = "/keluarga/ubah/{nomor_kk}")
	public String update(Model model,@PathVariable("nomor_kk") String nomor_kk) {
		KeluargaModel keluarga = keluargaDAO.selectKeluargaKK(nomor_kk);
		if(keluarga != null) {
			model.addAttribute("keluarga" , keluarga);
			return "update-keluarga";
		}
		return "not-found-keluarga";
	}
	
	@RequestMapping(value = "/keluarga/ubah/submit", method = RequestMethod.POST)
	public String updateSubmit(@ModelAttribute KeluargaModel keluarga,Model model,
			@RequestParam ( value ="id_kelurahan") int id_kelurahan,
			@RequestParam(value = "nomor_kk") String nomor_kk) {
		
		//dapetin idkel dari db
		int idKel = keluargaDAO.selectIdKel(nomor_kk);
		
		//dapetin kode kec berdasarkan id kel yang di input
		String kode = lokasiDAO.selectKecKode(id_kelurahan);
		String kode_kec = kode.substring(0,6);
		
		// 6 digit tanggal daftra
			String x = LocalDate.now().toString();
			String tahun = x.substring(2,4);
			String bulan = x.substring(5,7);
			String hari = x.substring(8,10);
			String tanggalSekarang = hari+bulan+tahun;
			
			String nkk_blmjadi = kode_kec + tanggalSekarang;
			
			int i = 1;
		    String cekSama = "000" + 1;
		    KeluargaModel cek_sama = keluargaDAO.selectKeluargaKK(nkk_blmjadi + cekSama);
		    while (cek_sama != null) {
		    	i++;
		    	if(i <= 9) {
		    		cekSama = "000" + i;
		    	}
		    	else if(i > 9 && i <= 99) {
		    		cekSama = "00" + i;
		    	}
		    	else {
		    		cekSama = "0" + i;
		    	}
		    	cek_sama = keluargaDAO.selectKeluargaKK(nkk_blmjadi+cekSama);
		    }
		
		//kalau id kel yang di db ga sama sama yang di input (berarti di ubah)
		if(idKel != id_kelurahan) {
			//ada nomor_nkk baru,karena ada kemungkinan kode kec nya brubah
			nomor_kk = kode_kec + tanggalSekarang + cekSama;
		}
		
		keluarga.setNomor_kk(nomor_kk);
		keluarga.setId_kelurahan(id_kelurahan);
		model.addAttribute("nomor_kk" , nomor_kk);
		keluargaDAO.updateKeluarga(keluarga);
		
		return "sukses-update-keluarga";
	}
}
