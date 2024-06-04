package com.example.emadsolutions.financial.advisor.service;

import com.example.emadsolutions.financial.advisor.dao.AdvisorReposotiry;
import com.example.emadsolutions.financial.advisor.entity.Advisor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AdvisorServiceImpl implements AdvisorService{
    private final AdvisorReposotiry advisorReposotiry;
    @Autowired
    public AdvisorServiceImpl(AdvisorReposotiry advisorReposotiry ) {
        this.advisorReposotiry = advisorReposotiry;
    }


    @Override
    public Advisor findById(Long advisorId) {

        Optional<Advisor> advisor = advisorReposotiry.findById(advisorId);
        if (!advisor.isPresent()) {
            throw new RuntimeException("Did not find Advisor with the id - "+ advisorId);
        }
        return advisor.get();

    }
}
