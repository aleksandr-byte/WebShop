package ua.nure.webshop.service.impl;

import org.springframework.stereotype.Service;
import ua.nure.webshop.domain.Diagonal;
import ua.nure.webshop.repos.DiagonalRepository;
import ua.nure.webshop.service.ParametersService;

@Service
public class ParametersServiceImpl implements ParametersService {

    private final DiagonalRepository diagonalRepository;

    public ParametersServiceImpl(DiagonalRepository diagonalRepository) {
        this.diagonalRepository = diagonalRepository;
    }

    @Override
    public Iterable<Diagonal> finalAllDiagonals() {
        return diagonalRepository.findAll();
    }
}
