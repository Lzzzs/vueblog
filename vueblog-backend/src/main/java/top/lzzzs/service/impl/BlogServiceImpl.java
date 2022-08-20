package top.lzzzs.service.impl;

import top.lzzzs.entity.Blog;
import top.lzzzs.mapper.BlogMapper;
import top.lzzzs.service.BlogService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @since 2022-08-20
 */
@Service
public class BlogServiceImpl extends ServiceImpl<BlogMapper, Blog> implements BlogService {

}
