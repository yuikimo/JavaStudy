package com.example.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.entity.dto.Campaign;

import java.util.List;

public interface CampaignService extends IService<Campaign> {
    List<Campaign> getAllCampaign(int pageNumber);
    Long getTotalCampaign();
}
