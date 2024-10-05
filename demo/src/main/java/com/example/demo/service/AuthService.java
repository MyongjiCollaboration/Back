package com.example.demo.service;

import com.example.demo.authentication.JwtTokenProvider;
import com.example.demo.authentication.PasswordHashEncryption;
import com.example.demo.dto.request.user.LoginDto;
import com.example.demo.dto.request.user.SignupDto;
import com.example.demo.entity.Family;
import com.example.demo.authentication.JwtEncoder;
import com.example.demo.entity.Users;
import com.example.demo.exception.ConflictException;
import com.example.demo.exception.NotFoundException;
import com.example.demo.exception.UnauthorizedException;
import com.example.demo.exception.error.ErrorCode;
import com.example.demo.repository.FamilyRepository;
import com.example.demo.repository.UserRepository;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseCookie;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
@Slf4j
@RequiredArgsConstructor
public class AuthService {
    private final UserRepository userRepository;
    private final JwtTokenProvider jwtTokenProvider;
    private final PasswordHashEncryption passwordHashEncryption;
    private final FamilyRepository familyRepository;

    public void signup(SignupDto signupDto) {
        if (userRepository.findByName(signupDto.getNickname()).isPresent()) {
            log.info("1");
            // throw new ConflictException(ErrorCode.DUPLICATED_NAME);
            throw new RuntimeException("이름 중복");
        }
        log.info("2");

        // 중복 이메일 회원가입 방지
        if (userRepository.findByEmail(signupDto.getEmail()).isPresent()) {
            //throw new ConflictException(ErrorCode.DUPLICATED_EMAIL);
            throw new RuntimeException("이메일 중복");
        }
        log.info("3");

        // 비밀번호 암호화
        String plainPassword = signupDto.getPassword();
        String hashedPassword = passwordHashEncryption.encrypt(plainPassword);
        log.info("비밀번호 암호화 성공");


        Users newUser = Users.builder()
                .email(signupDto.getEmail())
                .password(hashedPassword)
                .name(signupDto.getNickname())
                .build();
        userRepository.save(newUser);
        log.info("유저 저장 성공");

        if(signupDto.getFamilyCode() == null){
            createFamily(newUser);
        }else{
            Family family = this.familyRepository.findByCode(signupDto.getFamilyCode());
            if (family == null) {
                throw new NotFoundException(ErrorCode.FAMILY_NOT_FOUND);
            }
            family.getUsers().add(newUser);
            newUser.setFamily(family);
            this.familyRepository.save(family);
        }
    }

    public String generateFamilyCode() {
        int leftLimit = 48; // '0'
        int rightLimit = 122; // 'z'
        int targetStringLength = 8; // 코드 길이
        Random random = new Random();

        return random.ints(leftLimit, rightLimit + 1)
                .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97)) // 숫자와 문자만 사용
                .limit(targetStringLength)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
    }
    // 로그인
    public void login(LoginDto loginDto, HttpServletResponse response) {

        Users user = this.userRepository.findByEmail(loginDto.getEmail())
                .orElseThrow(() -> new NotFoundException(ErrorCode.USER_NOT_FOUND));
        if(user == null) {
            throw new NotFoundException(ErrorCode.USER_NOT_FOUND);
        }

        if (!passwordHashEncryption.matches(loginDto.getPassword(), user.getPassword())) {
            throw new UnauthorizedException(ErrorCode.UNAUTHORIZED_USER);
        }

        String payload = user.getId().toString();
        String accessToken = jwtTokenProvider.createToken(payload);
        ResponseCookie cookie = ResponseCookie.from("AccessToken", JwtEncoder.encode(accessToken))
                .maxAge(Duration.ofMillis(1800000))
                .httpOnly(true)
                .sameSite("None").secure(true)
                .path("/")
                .build();
        response.addHeader("Set-Cookie", cookie.toString());
    }

    private void createFamily(Users user){
        List<Users> userList = new ArrayList<>();
        userList.add(user);
        String familyCode = generateFamilyCode();
        Family family = Family.builder()
                .code(familyCode)
                .users(userList)
                .name("")
                .build();
        user.setFamily(family);
        familyRepository.save(family);
    }
}
