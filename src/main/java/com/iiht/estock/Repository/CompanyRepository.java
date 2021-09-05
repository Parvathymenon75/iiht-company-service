package com.iiht.estock.Repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.iiht.estock.model.Company;

@Repository
public interface CompanyRepository extends MongoRepository<Company, String> {
	
	public Company findByCompanyCode(String companyCode);
	
    public List <Company> findAll();
    
    public Company findByid(String id);

}
