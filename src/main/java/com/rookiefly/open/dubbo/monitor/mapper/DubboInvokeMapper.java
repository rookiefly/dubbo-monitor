package com.rookiefly.open.dubbo.monitor.mapper;

import com.rookiefly.open.dubbo.monitor.domain.DubboInvoke;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface DubboInvokeMapper {

    List<DubboInvoke> countDubboInvoke(DubboInvoke dubboInvoke);

    List<String> getMethodsByService(DubboInvoke dubboInvoke);

    List<DubboInvoke> countDubboInvokeInfo(DubboInvoke dubboInvoke);

    List<DubboInvoke> countDubboInvokeSuccessTopTen(DubboInvoke dubboInvoke);

    List<DubboInvoke> countDubboInvokeFailureTopTen(DubboInvoke dubboInvoke);

    void addDubboInvoke(DubboInvoke dubboInvoke);

}
