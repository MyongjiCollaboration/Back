package com.example.demo.service;

import com.example.demo.authentication.user.AuthenticatedUser;
import com.example.demo.dto.request.FamNameDto;
import com.example.demo.dto.request.user.SignupDto;
import com.example.demo.dto.response.MyPageResponseData;
import com.example.demo.dto.response.ResponseDto;
import com.example.demo.entity.Family;
import com.example.demo.entity.User;
import com.example.demo.repository.FamilyRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
@Slf4j
public class UserService {
    private final FamilyRepository familyRepository;
    public MyPageResponseData myPage(User user) {
        MyPageResponseData myPageResponseData = MyPageResponseData.of(user.getName(), user.getFamily().getName(), user.getFamily().getCode());
        return myPageResponseData;
    }

    public void changeFamName(User user, FamNameDto famName) {
        Family family = user.getFamily();
        log.info("change family name to {}", family.getName());
        family.setName(famName.getFamName());
        this.familyRepository.save(family);
    }
}
