package com.example.emadsolutions.financial.advisor;

import com.example.emadsolutions.financial.advisor.dao.AdvisorReposotiry;
import com.example.emadsolutions.financial.advisor.entity.Advisor;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DataInit {

    private AdvisorReposotiry advisorReposotiry;

    @Autowired
    public DataInit(AdvisorReposotiry advisorReposotiry) {
        this.advisorReposotiry = advisorReposotiry;
    }
    @PostConstruct
    public void initAdvisors()
    {
        Advisor advisor1 = new Advisor("Emad","Hussien","10 ibrahim desouky st.","01116446214","emadhussien@gmail.com");
     //   Advisor advisor2 = new Advisor("Mohamed","Amr","10 Sbary st.","01116446221","Mo_amr@gmail.com");
        advisorReposotiry.save(advisor1);
       // advisorReposotiry.save(advisor2);
        System.out.println("Saving....");
    }
}
