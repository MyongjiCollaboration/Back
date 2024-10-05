//package com.example.demo.service;
//
//import com.example.demo.dto.DiaryPhotoResponseDTO;
//import com.example.demo.entity.Diary;
//import com.example.demo.entity.DiaryPhoto;
//import com.example.demo.global.adapter.S3Adapter;
//import com.example.demo.repository.DiaryPhotoRepository;
//import lombok.RequiredArgsConstructor;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestPart;
//import org.springframework.web.multipart.MultipartFile;
//
//import java.util.Optional;
//import java.util.UUID;
//
//@Service
//@Transactional
//@RequiredArgsConstructor
//public class DiaryPhotoService {
//    private final DiaryPhotoRepository diaryPhotoRepository;
//    private final DiaryService diaryService;
//    private final S3Adapter s3Adapter;
//
//    // 다이어리 ID에 사진 저장
//    @PostMapping
//    public DiaryPhotoResponseDTO savePhotoToDiary(UUID diaryId, @RequestPart MultipartFile file) {
//        DiaryPhoto diaryPhoto = new DiaryPhoto();
//        String url = s3Adapter.uploadImage(file, "diary");
//        diaryPhoto.setUrl(url);
//
//        // 다이어리 가져오기 및 설정
//        Diary diary = diaryService.findDiaryEntityById(diaryId)
//                .orElseThrow(() -> new RuntimeException("Diary not found with id " + diaryId));
//        diaryPhoto.setDiary(diary);
//
//        DiaryPhoto savedPhoto = diaryPhotoRepository.save(diaryPhoto);
//
//        return DiaryPhotoResponseDTO.builder()
//                .id(savedPhoto.getId())
//                .url(savedPhoto.getUrl()).build();
//    }
//
//
//    // 다이어리 사진 삭제
//    public String deleteDiaryPhoto(UUID id) {
//        DiaryPhoto diaryPhoto = findDiaryPhotoById(id);
//        s3Adapter.deleteImage(diaryPhoto.getUrl());
//        diaryPhotoRepository.delete(diaryPhoto);
//
//        return "해당 다이어리 사진을 삭제하였습니다.";
//    }
//
//    // ID를 통해 DiaryPhoto 찾기
//    private DiaryPhoto findDiaryPhotoById(UUID id) {
//        Optional<DiaryPhoto> diaryPhoto = diaryPhotoRepository.findById(id);
//        if (diaryPhoto.isPresent())
//            return diaryPhoto.get();
//        else
//            throw new RuntimeException("존재하지 않은 다이어리 사진 ID 입니다");
//    }
//}
