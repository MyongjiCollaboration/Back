package com.example.demo.service;

import com.example.demo.entity.Quiz;
import com.example.demo.entity.QuizAnswer;
import com.example.demo.dto.QuizAnswerRequestDto;
import com.example.demo.entity.Users;
import com.example.demo.repository.QuizAnswerRepository;
import com.example.demo.repository.QuizRepository;
import com.example.demo.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class QuizAnswerService {
    private final QuizAnswerRepository quizAnswerRepository;
    private final QuizRepository quizRepository; // QuizRepository 추가
    private final UserRepository userRepository; // UserRepository 추가


    public QuizAnswerRequestDto saveQuizAnswer(Users user, QuizAnswerRequestDto dto) {
        UUID quizId = dto.getQuizId();
//        UUID userId = dto.getUserId();


        // 퀴즈 조회
        Optional<Quiz> quizOptional = quizRepository.findById(quizId);
        // 사용자 조회
        //Optional<Users> userOptional = userRepository.findById(userId);

        // 퀴즈가 존재하지 않을 경우 예외 처리
        if (quizOptional.isEmpty()) {
            throw new RuntimeException("퀴즈를 찾을 수 없습니다. ID: " + quizId);
        }

        // 사용자가 존재하지 않을 경우 예외 처리
//        if (userOptional.isEmpty()) {
//            throw new RuntimeException("사용자를 찾을 수 없습니다. ID: " + userId);
//       }
        QuizAnswer quizAnswer = new QuizAnswer(dto.getContent(),quizOptional.get(), user);
        quizAnswerRepository.save(quizAnswer);
        QuizAnswerRequestDto quizAnswerRequestDto = QuizAnswerRequestDto.builder()
                .quizId(quizId)
                .content(quizAnswer.getContent())
                .userId(user.getId())
                .build();


        return quizAnswerRequestDto;
    }

}
