package com.example.controller;

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
import com.example.model.KotaModel;
import com.example.model.KecamatanModel;
import com.example.model.KelurahanModel;


@Controller
public class PendudukController {

	@Autowired
	PendudukService pendudukDAO;
	@Autowired
	KeluargaService keluargaDAO;
	@Autowired
	LokasiService lokasiDAO;
	
	@RequestMapping("/")
	public String index () 
	{
		return "index";
	}
	
	@RequestMapping("/penduduk")
	public String cariSubmit(@RequestParam(value = "nik" , required = false) String nik, Model model) 
	{
		PendudukModel penduduk = pendudukDAO.selectPenduduk(nik);
		KeluargaModel keluarga = keluargaDAO.selectKeluarga(penduduk.getId_keluarga());
		LokasiModel lokasi = lokasiDAO.selectLokasi(keluarga.getId_kelurahan());
		
		
		if(penduduk != null) {
			model.addAttribute("penduduk" ,penduduk);
			model.addAttribute("keluarga" ,keluarga);
			model.addAttribute("lokasi" , lokasi);
			return "hasil-cari-penduduk";
		}
		else {
			model.addAttribute("nik", nik);
			return "nik-null";
		}
	}
	@RequestMapping("/penduduk/tambah")
	public String form_add() {
		return "add-penduduk";
	}
	
	@RequestMapping(value = "/penduduk/tambah/submit" , method = RequestMethod.POST)
	public String submit_penduduk(@RequestParam(value = "nama",required =false) String nama,
			@RequestParam(value ="tempat_lahir" , required=false) String tempat_lahir,
			@RequestParam(value ="tanggal_lahir" , required=false) String tanggal_lahir,
			@RequestParam(value="jenis_kelamin" , required=false) String jenis_kelamin,
			@RequestParam(value ="golongan_darah" , required=false) String golongan_darah,
			@RequestParam(value="agama" , required=false) String agama,
			@RequestParam(value = "status_perkawinan" , required=false) String status_perkawinan,
			@RequestParam(value ="status_dalam_keluarga" , required=false) String status_dalam_keluarga,
			@RequestParam(value= "pekerjaan" , required=false) String pekerjaan,
			@RequestParam(value ="is_wni", required=false) String is_wni,
			@RequestParam(value ="is_wafat" , required=false) String is_wafat,
			@RequestParam(value ="id_keluarga" , required= false) int id_keluarga,
			Model model) {
		
		//select id kelurahan dari id keluarga yg di input
		int id_kelurahan = keluargaDAO.selectIdKelurahan(id_keluarga);
		
		// ambil kode kecamatan dari id kelurahan
		String kode = lokasiDAO.selectKecKode(id_kelurahan);
		String kode_kecamatan = kode.substring(0,6); //6 digit pertama
		
		//6digit berikutnya
		String tanggal = tanggal_lahir.substring(8,10);
		if(jenis_kelamin.equalsIgnoreCase("P")) {
			int tgl_lahir = Integer.parseInt(tanggal) + 40;
			tanggal = String.valueOf(tgl_lahir);
		}
		String lahir = tanggal_lahir.substring(2,4);
		String tgl = tanggal_lahir.substring(5,7);
		String tglLahir = tanggal + tgl + lahir;
		
		String nik_blmjadi = kode_kecamatan + tglLahir;
		
		//cek sama dengan db
		int i = 1;
		String cekSama = "000" + 1;
		PendudukModel cek_sama = pendudukDAO.selectPenduduk(nik_blmjadi + cekSama);
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
	    	cek_sama = pendudukDAO.selectPenduduk(nik_blmjadi+cekSama);
	    }
		String nik = nik_blmjadi + cekSama;
		
		//is_wni
		is_wni = "1";
		if(is_wni.equalsIgnoreCase("wna")){
			is_wni = "0";
		}
		
		//is wafat
		is_wafat = "0";
		if(is_wafat.equalsIgnoreCase("hidup")) {
			is_wafat = "1";
		}
		
		//jenis kelamin
		jenis_kelamin = "1";
		if(jenis_kelamin.equalsIgnoreCase("L")) {
			jenis_kelamin = "0";
		}

		PendudukModel pendudukBaru = new PendudukModel();
		pendudukBaru.setNik(nik);
		model.addAttribute("nik" , nik);
		
		pendudukBaru.setAgama(agama);
		pendudukBaru.setGolongan_darah(golongan_darah);
		pendudukBaru.setId_keluarga(id_keluarga);
		pendudukBaru.setIs_wafat(is_wafat);
		pendudukBaru.setIs_wni(is_wni);
		pendudukBaru.setJenis_kelamin(jenis_kelamin);
		pendudukBaru.setNama(nama);
		pendudukBaru.setPekerjaan(pekerjaan);
		pendudukBaru.setStatus_dalam_keluarga(status_dalam_keluarga);
		pendudukBaru.setStatus_perkawinan(status_perkawinan);
		pendudukBaru.setTanggal_lahir(tanggal_lahir);
		pendudukBaru.setTempat_lahir(tempat_lahir);
		
		pendudukDAO.addPenduduk(pendudukBaru);
		
		return "sukses-add-penduduk";
		
	}
	
	@RequestMapping(value = "/penduduk/ubah/{nik}")
	public String update(Model model,@PathVariable("nik") String nik) {
		PendudukModel penduduk = pendudukDAO.selectPenduduk(nik);
		if(penduduk != null) {
			model.addAttribute("penduduk" , penduduk);
			return "update-penduduk";
		}
		return "not-found-penduduk";
	}
	
	@RequestMapping(value ="/penduduk/ubah/submit", method = RequestMethod.POST)
    public String updateSubmit (@ModelAttribute PendudukModel penduduk, Model model, 
    		@RequestParam(value = "nik") String nik,
    		@RequestParam(value="id_keluarga") int id_keluarga,
    		@RequestParam(value ="tanggal_lahir") String tanggal_lahir,
    		@RequestParam(value = "jenis_kelamin") String jenis_kelamin) {
		
		//ngambil id keluarga dari db
		int kel_id = pendudukDAO.selectKeluargaId(nik);
		
		//ngambil id kelurahan dari id keluarga
		int id_kel = keluargaDAO.selectIdKelurahan(kel_id);
		
		//ambil kode kecamatan berdasarkan id kelurahan yang di input
		String kode = lokasiDAO.selectKecKode(id_kel);
		String kode_kecamatan = kode.substring(0,6);
		
		//tanggal lahir
		String tanggal = tanggal_lahir.substring(8,10);
		if(jenis_kelamin.equalsIgnoreCase("P")) {
			int tgl_lahir = Integer.parseInt(tanggal) + 40;
			tanggal = String.valueOf(tgl_lahir);
		}
		String lahir = tanggal_lahir.substring(2,4);
		String tgl = tanggal_lahir.substring(5,7);
		String tglLahir = tanggal + tgl + lahir;
		
		String nik_blmjadi = kode_kecamatan + tglLahir;
		
		//cek sama dengan db
		int i = 1;
		String cekSama = "000" + 1;
		PendudukModel cek_sama = pendudukDAO.selectPenduduk(nik_blmjadi + cekSama);
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
	    	cek_sama = pendudukDAO.selectPenduduk(nik_blmjadi+cekSama);
	    }
		
		//kalau id keluraga di input ga sama sama id keluarga yang di db
		if(kel_id != id_keluarga) {
			// nik nya berubah
			nik = nik_blmjadi + cekSama;
		}
		
		penduduk.setNik(nik);
		penduduk.setId_keluarga(id_keluarga);
		penduduk.setJenis_kelamin(jenis_kelamin);
		penduduk.setTanggal_lahir(tanggal_lahir);
		
        pendudukDAO.updatePenduduk(penduduk);
		model.addAttribute("nik" , nik);
        return "sukses-update-penduduk";
    }
	
	@RequestMapping(value ="/penduduk/mati" , method = RequestMethod.POST)
	public String mati(@ModelAttribute("penduduk") PendudukModel penduduk,
			@ModelAttribute("keluarga") KeluargaModel keluarga,
			Model model) {
		
		penduduk.setIs_wafat("1");
		pendudukDAO.updatePenduduk(penduduk);
		String nik = penduduk.getNik();
		//System.out.println(nik);
		model.addAttribute("nik" , nik);
		
		List<PendudukModel> isWafat_keluarga = pendudukDAO.selectIsWafat(nik);
		
		for(int i = 0; i < isWafat_keluarga.size(); i++) {
			System.out.println("masuk ga ya");
			if(isWafat_keluarga.get(i).getIs_wafat().equals("1")) {
				System.out.println("masuk sini ga ya");
				keluarga.setIs_tidak_berlaku(1);
				keluargaDAO.updateKeluarga(keluarga);
			}
		}
		
		return "sukses-update-mati";
	}
	
//	@RequestMapping(value ="/penduduk/cari")
//	public String cari_penduduk(Model model, 
//			@RequestParam(value = "nama_kota", required=false) String nama_kota,
//			@RequestParam(value = "nama_kecamatan", required=false) String nama_kecamatan){
//		//System.out.println(kota);
//		
//	
//			List<KotaModel> kotas = pendudukDAO.selectKota();
//			model.addAttribute("kotas" , kotas);
//			
//			return "penduduk-cari-kota";
//		
//		
//	}
}
