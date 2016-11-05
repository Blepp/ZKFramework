package com.mkdika.zkspringhb.service;

import com.mkdika.zkspringhb.entity.Person;
import com.mkdika.zkspringhb.entity.PersonJob;
import java.util.List;

/**
 *
 * @author Maikel
 */
public interface ServiceDao {
    
    public boolean save(Object obj);        

    public boolean delete(Object obj);
    
    public boolean truncateDb();
    
    public Person getPersonById(Integer id);
    
    public List<Person> getPersons();  
       
    public PersonJob getPersonJobById (Integer id);
    
    public List<PersonJob> getPersonJobs();
}
