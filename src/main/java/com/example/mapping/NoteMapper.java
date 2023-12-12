package com.example.mapping;


import com.example.dto.EncounterDto;
import com.example.dto.NoteDto;
import com.example.dto.UserDto;
import com.example.model.Encounter;
import com.example.model.Note;
import com.example.model.User;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class NoteMapper implements StrategyMapper<Note, NoteDto> {
    private final StrategyMapper<Encounter, EncounterDto> encounterMapper;
    private final StrategyMapper<User, UserDto> userMapper;

    public NoteMapper(StrategyMapper<Encounter, EncounterDto> encounterMapper,
                      StrategyMapper<User, UserDto> userMapper) {
        this.encounterMapper = encounterMapper;
        this.userMapper = userMapper;
    }

    @Override
    public NoteDto map(Note source) {
        return new NoteDto(source.getId(), source.getText(), source.getDateTimeCreated(),
                userMapper.map(source.getEmployee()), userMapper.map(source.getPatient()),
                encounterMapper.map(source.getEncounter()));
    }

    @Override
    public List<NoteDto> mapAll(List<Note> source) {
        List<NoteDto> noteDtos = new ArrayList<>();
        for (Note n:
                source) {
            noteDtos.add(map(n));
        }
        return noteDtos;
    }
}
