package com.skilldistillery.bmtk.controllers;

import java.security.Principal;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.skilldistillery.bmtk.entities.Company;
import com.skilldistillery.bmtk.entities.Customer;
import com.skilldistillery.bmtk.services.CompanyService;

@RestController
@RequestMapping("api")
@CrossOrigin({ "*", "http://localhost:4200" })
public class CompanyController {

	@Autowired
	private CompanyService compSvc;

	// get all companies

	@GetMapping(value = "companies")
	public List<Company> listAllCompanies() {
		return compSvc.listAllCompany();
	}
	
	// get my companies, as owner
	
	@GetMapping("myCompanies")
	public List<Company> listMyCompanies(HttpServletRequest req,
			HttpServletResponse res,
			Principal principal){
		List<Company> myCompanies = compSvc.listMyCompanies(principal.getName());
		return myCompanies;
	}

	// get single company
	
	@GetMapping(value = "companies/{id}")
	public Company listCompanyById(HttpServletRequest req,
			HttpServletResponse res,
			Principal principal,
			@PathVariable("id") Integer id) {
		return compSvc.listCompanyById(id);
	}
	
	@GetMapping(value="companies/{id}/owner")
	public Boolean checkCompanyOwner(HttpServletRequest req,
			HttpServletResponse res,
			Principal principal,
			@PathVariable("id") Integer id) {
		Boolean isOwner = false;
			try {
				isOwner = compSvc.isCompanyOwner(id, principal.getName());
				if (isOwner) {
					res.setStatus(200);
					return isOwner;
				} else {
					res.setStatus(200);
					return isOwner;
				}
				
			} catch (Exception e) {
				e.printStackTrace();
				res.setStatus(400);
				return isOwner;
			}

	}
	
	@GetMapping(value="companies/{id}/customers")
	public Set<Customer> getCustomersForACompany(HttpServletRequest req,
			HttpServletResponse res,
			Principal principal,
			@PathVariable("id") Integer id){
		Set<Customer> myCustomers = compSvc.getMyCustomers(id);
		
		
		return myCustomers;
	}
	
	// make new company

	@PostMapping(value = "companies")
	public Company createCompany(HttpServletRequest req,
			HttpServletResponse res,
			Principal principal,
			@RequestBody Company company) {
		
		if (principal != null) {
			try {
				company = compSvc.createCompany(company, principal.getName());
				if (company == null) {
					res.setStatus(404);
					return company;
				}
				res.setStatus(201);
				return company;
			} catch (Exception e) {
				e.printStackTrace();
				res.setStatus(400);
				return null;
			}
		} else {
			res.setStatus(401);
			return null;
		}
	}
	
	// update company

	@PutMapping(value = "companies/{cid}")
	public Company updateCompany(HttpServletRequest req,
			HttpServletResponse res,
			Principal principal,
			@PathVariable("cid") Integer cid, @RequestBody Company company) {
		try {
			company = compSvc.updateCompany(cid, company);
			if (company == null) {
				res.setStatus(404);
				return company;
			}
			res.setStatus(200);
			return company;
		} catch (Exception e) {
			e.printStackTrace();
			res.setStatus(400);
			return null;
		}
	}
	
	// delete company (or make inactive?)

	@DeleteMapping(value = "companies/{id}")
	public Boolean deleteCompany(@PathVariable("id") int id) {
		return compSvc.deleteCompany(id).booleanValue();
	}

}
