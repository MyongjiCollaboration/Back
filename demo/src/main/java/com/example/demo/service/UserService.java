package com.example.demo.service;

import com.example.demo.dto.request.FamNameDto;
import com.example.demo.dto.response.MyPageResponseData;
import com.example.demo.entity.Family;
import com.example.demo.entity.Users;
import com.example.demo.repository.FamilyRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class UserService {
    private final FamilyRepository familyRepository;
    public MyPageResponseData myPage(Users user) {
        MyPageResponseData myPageResponseData = MyPageResponseData.of(user.getName(), user.getFamily().getName(), user.getFamily().getCode());
        return myPageResponseData;
    }

    public void changeFamName(Users user, FamNameDto famName) {
        Family family = user.getFamily();
        family.setName(famName.getFamName());
        this.familyRepository.save(family);
    }
}
