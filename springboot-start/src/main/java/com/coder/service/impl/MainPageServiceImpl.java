package com.coder.service.impl;

import com.coder.service.MainPageService;
import org.springframework.stereotype.Service;

/**
 * @描述
 * @作者 zhoujie
 * @创建时间 2017/12/12 15:28.
 */
@Service
public class MainPageServiceImpl implements MainPageService {
    @Override
    public String getMainContent() {
        return "ok";
    }
}
