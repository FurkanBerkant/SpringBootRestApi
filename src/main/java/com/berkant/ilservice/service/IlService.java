package com.berkant.ilservice.service;

import com.berkant.ilservice.Exception.IlAlreadyExistsException;
import com.berkant.ilservice.Exception.IlNotFoundException;
import com.berkant.ilservice.model.Il;
import com.berkant.ilservice.repository.IlRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class IlService{
    private final IlRepository ilRepository;


    public List<Il> getIller(String name){
        if(name == null){
            return ilRepository.findAll();
        }else {
        return ilRepository.findAllByName(name);
    }}
    public Il createIl(Il il){
       Optional<Il> ilByName= ilRepository.findByName(il.getName());
       if(ilByName.isPresent()){
           throw new IlAlreadyExistsException("Il adı kullaniliyor:"+il.getName());
       }
        return ilRepository.save(il);
    }

    public void deleteIl(String id) {
        ilRepository.deleteById(id);
    }
    public Il getIlById(String id){
        return ilRepository.findById(id).orElseThrow(()-> new IlNotFoundException("Il bulunamadı id:"+id));
    }
    public void updateIl(String id ,Il newIl){
        Il oldIl=getIlById(id);
        oldIl.setName(newIl.getName());
        ilRepository.save(oldIl);
    }
}
