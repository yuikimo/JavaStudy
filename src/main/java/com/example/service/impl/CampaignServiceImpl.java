package com.example.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.entity.dto.Campaign;
import com.example.mapper.CampaignMapper;
import com.example.service.CampaignService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CampaignServiceImpl extends ServiceImpl<CampaignMapper, Campaign> implements CampaignService {
    @Resource
    private CampaignMapper campaignMapper;
    @Override
    public List<Campaign> getAllCampaign(int pageNumber) {
        Page<Campaign> page = Page.of(pageNumber, 2);
        campaignMapper.selectPage(page, null);
        return page.getRecords().stream().toList();
    }

    @Override
    public Long getTotalCampaign() {
        QueryWrapper<Campaign> queryWrapper = new QueryWrapper<>();
        return campaignMapper.selectCount(queryWrapper);
    }
}
