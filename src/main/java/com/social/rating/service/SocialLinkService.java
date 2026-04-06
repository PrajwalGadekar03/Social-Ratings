package com.social.rating.service;

import com.social.rating.entity.SocialLink;
import com.social.rating.repository.SocialLinkRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class SocialLinkService {

    private final SocialLinkRepository repository;
    private final UserService userService;

    public SocialLinkService(SocialLinkRepository repository,
                             UserService userService) {
        this.repository = repository;
        this.userService = userService;
    }

    public SocialLink addSocialLink(String platform, String url) {
        UUID userId = userService.getLoggedInUser().getId();

        SocialLink link = new SocialLink();
        link.setUserId(userId);
        link.setPlatform(platform);
        link.setUrl(url);

        return repository.save(link);
    }

    public List<SocialLink> getMyLinks() {
        UUID userId = userService.getLoggedInUser().getId();
        return repository.findByUserId(userId);
    }
}
