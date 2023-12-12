package com.example.mapping;

import com.example.dto.EncounterDto;
import com.example.model.Encounter;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.List;
//TODO
@ApplicationScoped
public class EncounterMapper implements StrategyMapper<Encounter, EncounterDto> {
    @Override
    public EncounterDto map(Encounter source) {
        return null;
    }

    @Override
    public List<EncounterDto> mapAll(List<Encounter> source) {
        return null;
    }
}
