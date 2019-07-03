package com.show.itmuch.contentcenter.service.content;

import com.show.itmuch.contentcenter.dao.content.ShareMapper;
import com.show.itmuch.contentcenter.domain.dto.content.ShareDTO;
import com.show.itmuch.contentcenter.domain.dto.user.UserDTO;
import com.show.itmuch.contentcenter.domain.entity.content.Share;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ShareService {

    private final ShareMapper shareMapper;
    private final RestTemplate restTemplate;

    public ShareDTO findById(Integer id) {
        // 获取分享详情
        Share share = this.shareMapper.selectByPrimaryKey(id);
        // 发布人ID
        Integer userId = share.getUserId();
        // 获取发布人信息
        UserDTO userDTO = restTemplate.getForObject("http://localhost:8080/users/{id}", UserDTO.class, userId);
        ShareDTO shareDTO = new ShareDTO();
        //消息的装配
        BeanUtils.copyProperties(share, shareDTO);
        shareDTO.setWxNickname(userDTO.getWxNickname());
        return shareDTO;

    }
}
