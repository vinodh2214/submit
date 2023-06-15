package com.assesment.University.controller.service;

import com.assesment.University.entity.College;

public interface CollegeService {
    College getCollege(Long id);
    String addCollege(College college);
    String updateCollege(College college);
}
