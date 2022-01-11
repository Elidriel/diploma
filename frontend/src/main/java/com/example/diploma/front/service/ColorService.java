package com.example.diploma.front.service;

import com.example.diploma.persistence.dao.ColorDao;
import com.example.diploma.persistence.entity.ColorEntity;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ColorService {

    ColorDao colorDao;

    public List<String> getAllColors() {
        return colorDao.findAll().stream().map(ColorEntity::getValue).collect(Collectors.toList());
    }
}
