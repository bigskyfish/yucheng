package com.floatcloud.file.mq;

import com.floatcloud.file.dao.FileBasic;
import com.floatcloud.file.enumeration.FileTypeEnum;
import com.floatcloud.yucheng.constant.EurekaConstant;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;

import javax.annotation.Resource;

@SpringBootTest
public class FileRemindProducer {

    @Resource
    FileRemindProducer fileRemindProducer;

//    @Test
//    public void  produceFileRemind(){
//        FileBasic fileBasic = new FileBasic(110, "金瓶梅", FileTypeEnum.remind);
//        fileRemindProducer.produceFileRemind(fileBasic, 10000L);
//    }

}
