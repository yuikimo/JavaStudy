package com.example.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.entity.dto.Campaign;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CampaignMapper extends BaseMapper<Campaign> {
}
