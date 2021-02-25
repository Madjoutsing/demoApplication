package com.example.demo.service.Option;

import com.example.demo.modele.OptionFiliere;
import com.example.demo.repository.OptionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OptionServiceImpl implements OptionIService{

    @Autowired
    public OptionRepository optionRepository;

    @Override
    public List<OptionFiliere> listOptions() {return optionRepository.findAll();}
}
