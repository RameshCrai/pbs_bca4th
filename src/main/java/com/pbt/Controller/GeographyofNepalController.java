package com.pbt.Controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.pbt.Dao.DistrictRepository;
import com.pbt.Dao.MunicipalityRepository;
import com.pbt.Dao.ProvinceRepository;
import com.pbt.Entities.District;
import com.pbt.Entities.MunicipalityName;
import com.pbt.Entities.Province;
import com.pbt.ExceptionHandler.MessageMaster;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/pbt")
public class GeographyofNepalController {

	long stateid = 0;
	long districtofmuniid = 0;
	private ProvinceRepository provinceRepo;

	private DistrictRepository districtRepo;

	private MunicipalityRepository municipalityRepo;

	public GeographyofNepalController(ProvinceRepository provinceRepo, DistrictRepository districtRepo,
			MunicipalityRepository municipalityRepo) {
		super();
		this.provinceRepo = provinceRepo;
		this.districtRepo = districtRepo;
		this.municipalityRepo = municipalityRepo;
	}

	@GetMapping("/settings")
	public String getSetting(HttpSession session) {
		if (session.getAttribute("user") == null) {
			return "Layout/Login";
		}

		return "pages/Dashboard/NepalStates";
	}

//	save province 
	@PostMapping("/saveState")
	public String saveProvince(@ModelAttribute Province province, HttpSession session) {

		Province provinceinfo = this.provinceRepo.findByState(province.getState());
		if (provinceinfo == null) {
			this.provinceRepo.save(province);
			session.setAttribute("mes", new MessageMaster("Province is Save Successfuly ", "alert-success"));

		} else {
			session.setAttribute("mes", new MessageMaster("State  is Duplicate ", "alert-danger"));

		}

		return "redirect:/pbt/settings";
	}

//list of province 
	@GetMapping("/listofstate")
	public String stateList(Model model) {

		List<Province> provincelist = this.provinceRepo.findAll();
		model.addAttribute("statelist", provincelist);

		return "pages/Dashboard/StateList";
	}

//	open district page
	@RequestMapping(value = "/district", method = { RequestMethod.GET, RequestMethod.POST })
	public String getDistrict(HttpSession session, Model model) {
		if (session.getAttribute("user") == null) {
			return "Layout/Login";
		}
		Province province = this.provinceRepo.findByStateID(this.stateid);
		model.addAttribute("s", province);

		List<District> listofDistrict = this.districtRepo.findByProvince(province);

		model.addAttribute("provincedistrict", listofDistrict);
		return "pages/Dashboard/Districtlist";
	}

//	district list by province name
	@GetMapping("/districtofprovince/{sid}")
	public String saveDistrict(@PathVariable("sid") Long sid, RedirectAttributes redirectAttributes) {
		this.stateid = sid;
		Province province = this.provinceRepo.findByStateID(sid);
		redirectAttributes.addFlashAttribute("s", province);

		List<District> listofDistrict = this.districtRepo.findByProvince(province);

		redirectAttributes.addFlashAttribute("provincedistrict", listofDistrict);

		return "redirect:/pbt/district";
	}

//	Save district with fk provinceId
	@PostMapping("/saveDistrict")
	public String saveDistrict(@ModelAttribute District district, HttpSession session) {
		Province provinceinfo = this.provinceRepo.findByStateID(this.stateid);
		District disinfo = this.districtRepo.findByDistrictName(district.getDistrictName());
		if (disinfo == null) {
			district.setProvince(provinceinfo);

			this.districtRepo.save(district);
			session.setAttribute("mes", new MessageMaster("District Save Successfuly ", "alert-success"));

		} else {

			session.setAttribute("mes",
					new MessageMaster("District Save Failed Please select Province Again?? ", "alert-danger"));

		}

		return "pages/Dashboard/DistrictofNepal";
	}

//	open district from province
	@GetMapping("/addDistrict/{sid}")
	public String getDistrictList(@PathVariable("sid") Long sid, Model model) {
		Province province = this.provinceRepo.findByStateID(sid);
		model.addAttribute("st", province);

		return "pages/Dashboard/DistrictofNepal";
	}

//	get Municipality
	@GetMapping("/municipalitylist")
	public String getMunicipality(Model model) {

		District disinfo = this.districtRepo.findByDistrictID(this.districtofmuniid);

		model.addAttribute("dist", disinfo);

		List<MunicipalityName> listofdistrictMunici = this.municipalityRepo.findByDistrict(disinfo);
		model.addAttribute("listofmunici", listofdistrictMunici);

		return "pages/Dashboard/MunicipalityList";
	}

//	Municipality by districtID 
	@GetMapping("/municipality/{mid}")
	public String saveMunicipality(@PathVariable("mid") Long mid, RedirectAttributes redirectAttribute) {
		this.districtofmuniid = mid;

		District disinfo = this.districtRepo.findByDistrictID(this.districtofmuniid);

		redirectAttribute.addFlashAttribute("dist", disinfo);

		List<MunicipalityName> listofdistrictMunici = this.municipalityRepo.findByDistrict(disinfo);
		redirectAttribute.addFlashAttribute("listofmunici", listofdistrictMunici);

		return "redirect:/pbt/municipalitylist";
	}

//	Save municipality
	@PostMapping("/saveMunicipality")
	public String saveMunicipality(@ModelAttribute MunicipalityName municipality, HttpSession session) {
		District districtinfo = this.districtRepo.findByDistrictID(this.districtofmuniid);

		MunicipalityName muni = this.municipalityRepo.findByNameofMunicipality(municipality.getNameofMunicipality());
		if (muni == null) {
			municipality.setDistrict(districtinfo);
			this.municipalityRepo.save(municipality);
			session.setAttribute("mes", new MessageMaster("Municipality  Save Successfuly ", "alert-success"));

		} else {
			session.setAttribute("mes", new MessageMaster("Duplicate Municipality?? ", "alert-danger"));
		}

		return "pages/Dashboard/Municipality";
	}

//	add municipality
	@GetMapping("/addMunicipality/{did}")
	public String getMunicipality(@PathVariable("did") long did, Model model) {
		this.districtofmuniid = did;
		District disinfo = this.districtRepo.findByDistrictID(this.districtofmuniid);

		model.addAttribute("dt", disinfo);

		return "pages/Dashboard/Municipality";
	}

}
