package com.team11.bookstore.service;

import com.team11.bookstore.model.M_Ratings;
import com.team11.bookstore.repository.RatingsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RatingService {

    @Autowired
    private RatingsRepository ratingsRepository;

    public M_Ratings createRating(M_Ratings rating) {
        return ratingsRepository.save(rating);
    }
}