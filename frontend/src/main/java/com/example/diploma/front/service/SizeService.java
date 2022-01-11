package com.example.diploma.front.service;

import com.example.diploma.persistence.dao.SizeDao;
import com.example.diploma.persistence.entity.SizeEntity;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class SizeService {

    SizeDao sizeDao;

    public List<Integer> getAllSizes() {
        return sizeDao.findAll().stream().map(SizeEntity::getValue).collect(Collectors.toList());
    }
}
