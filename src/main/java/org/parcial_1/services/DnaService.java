package org.parcial_1.services;

import org.parcial_1.entities.Dna;
import org.parcial_1.repositories.DnaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;
import java.util.stream.IntStream;
import java.util.stream.Stream;

@Service
public class DnaService {

    private final DnaRepository dnaRepository;
    private static final int SEQUENCE_LENGTH = 4;

    @Autowired
    public DnaService(DnaRepository dnaRepository, StatsService statsService) {
        this.dnaRepository = dnaRepository;
    }

    public static boolean isMutant(String[] dna) {
        int n = dna.length;

        
        int sequenceCount = checkRows(dna, n);
        if (sequenceCount > 1) return true;


        sequenceCount += checkColumns(dna, n);
        if (sequenceCount > 1) return true;


        sequenceCount += checkDiagonal(dna, n, 1, 1);
        if (sequenceCount > 1) return true;


        sequenceCount += checkDiagonal(dna, n, 1, -1);
        return sequenceCount > 1;
    }

    private static int checkRows(String[] dna, int n) {
        int sequenceCount = 0;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j <= n - SEQUENCE_LENGTH; j++) {
                if (isMutantSequence(dna, i, j, 0, 1, n)) {
                    sequenceCount++;
                    if (sequenceCount > 1) return sequenceCount;
                }
            }
        }
        return sequenceCount;
    }

    private static int checkColumns(String[] dna, int n) {
        int sequenceCount = 0;

        for (int j = 0; j < n; j++) {
            for (int i = 0; i <= n - SEQUENCE_LENGTH; i++) {
                if (isMutantSequence(dna, i, j, 1, 0, n)) {
                    sequenceCount++;
                    if (sequenceCount > 1) return sequenceCount;
                }
            }
        }
        return sequenceCount;
    }

    private static int checkDiagonal(String[] dna, int n, int dx, int dy) {
        int sequenceCount = 0;

        for (int i = 0; i <= n - SEQUENCE_LENGTH; i++) {
            for (int j = (dy == 1 ? 0 : SEQUENCE_LENGTH - 1);
                 j < (dy == 1 ? n - SEQUENCE_LENGTH + 1 : n); j++) {
                if (isMutantSequence(dna, i, j, dx, dy, n)) {
                    sequenceCount++;
                    if (sequenceCount > 1) return sequenceCount;
                }
            }
        }
        return sequenceCount;
    }

    private static boolean isMutantSequence(String[] dna, int x, int y, int dx, int dy, int n) {
        char first = dna[x].charAt(y);
        for (int i = 1; i < SEQUENCE_LENGTH; i++) {
            if (dna[x + i * dx].charAt(y + i * dy) != first) {
                return false;
            }
        }
        return true;
    }

    public boolean analyzeDna(String[] dna) {
        String dnaSequence = String.join(",", dna);


        Optional<Dna> existingDna = dnaRepository.findByDna(dnaSequence);
        if (existingDna.isPresent()) {
            return existingDna.get().isMutant();
        }


        boolean isMutant = isMutant(dna);
        Dna dnaEntity = Dna.builder()
                .dna(dnaSequence)
                .isMutant(isMutant)
                .build();
        dnaRepository.save(dnaEntity);

        return isMutant;
    }
}
