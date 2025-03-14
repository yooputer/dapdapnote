package org.dapdapnote.controller;

import lombok.RequiredArgsConstructor;
import org.dapdapnote.dto.DdNoteDto;
import org.dapdapnote.entity.DdNoteEntity;
import org.dapdapnote.entity.GroupEntity;
import org.dapdapnote.entity.UserEntity;
import org.dapdapnote.repository.DdNoteRepository;
import org.dapdapnote.repository.GroupRepository;
import org.dapdapnote.repository.UserRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/ddnote")
@RequiredArgsConstructor
public class DdNoteController {

    private final UserRepository userRepository;
    private final DdNoteRepository ddNoteRepository;
    private final GroupRepository groupRepository;

    @GetMapping("/my")
    public List<DdNoteDto> myList(){
        UserEntity userEntity = userRepository.findByUserId("yoojin027")
                .orElseThrow(() -> new RuntimeException("User not found"));

        List<DdNoteDto> ddNoteDtoList = ddNoteRepository.findAllByWriter(userEntity).stream()
                .map(DdNoteEntity::toDto)
                .toList();

        return ddNoteDtoList;
    }

    @GetMapping("/group/{groupSeq}")
    public List<DdNoteDto> groupList(@PathVariable Long groupSeq){
        GroupEntity groupEntity = groupRepository.findById(groupSeq)
                .orElseThrow(() -> new RuntimeException("group not found"));

        List<DdNoteDto> ddNoteDtoList = ddNoteRepository.findAllByGroup(groupEntity).stream()
                .map(DdNoteEntity::toDto)
                .toList();

        return ddNoteDtoList;
    }
}