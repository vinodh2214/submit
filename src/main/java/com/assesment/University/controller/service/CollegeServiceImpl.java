package com.assesment.University.controller.service;

import com.assesment.University.Exceptions.CollegeAlreadyExistException;
import com.assesment.University.Exceptions.NoSuchCollegeExistsException;
import com.assesment.University.entity.College;
import com.assesment.University.repsitory.CollegeRepository;

public class CollegeServiceImpl implements CollegeService {

    private CollegeRepository collegeRepository;

    public CollegeServiceImpl(CollegeRepository collegeRepository) {
        this.collegeRepository = collegeRepository;
    }

    @Override
    public College getCollege(Long id) {
        return collegeRepository.findById(id).orElseThrow(
                () -> new NoSuchCollegeExistsException(
                        "NO SUCH COLLEGE EXISTS WITH ID : " + id));
    }

    @Override
    public String addCollege(College college) {

        College existingCollege =
                collegeRepository.findById(college.getId())
                        .orElse(null);

        if (existingCollege == null) {
            collegeRepository.save(college);
            return "College added successfully";
        } else {
            throw new CollegeAlreadyExistException(
                    "College already exists");
        }
    }

    @Override
    public String updateCollege(College college) { // new date employee
        College existingCollege = // old college
                collegeRepository.findById(college.getId())
                        .orElse(null);

        if (existingCollege == null) {
            throw new NoSuchCollegeExistsException(
                    "No Such college exists exception");
        } else {
            existingCollege.setCollege_name(college.getCollege_name());

            collegeRepository.save(existingCollege); // merge - Persistence Context

            return "Employee updated successfully";
        }
    }
}
