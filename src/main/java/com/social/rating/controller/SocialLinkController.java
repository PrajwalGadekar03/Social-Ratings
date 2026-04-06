package com.social.rating.controller;

import com.social.rating.entity.SocialLink;
import com.social.rating.service.SocialLinkService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/social-links")
public class SocialLinkController {

    private final SocialLinkService service;

    public SocialLinkController(SocialLinkService service) {
        this.service = service;
    }

    @PostMapping("/add")
    public SocialLink addLink(@RequestParam String platform,
                              @RequestParam String url) {
        return service.addSocialLink(platform, url);
    }

    @GetMapping("/my-links")
    public List<SocialLink> getMyLinks() {
        return service.getMyLinks();
    }
}
