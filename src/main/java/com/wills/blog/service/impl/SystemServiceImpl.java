package com.wills.blog.service.impl;

import com.wills.blog.bean.BaseInfo;
import com.wills.blog.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import oshi.SystemInfo;
import oshi.hardware.CentralProcessor;
import oshi.hardware.GlobalMemory;
import oshi.hardware.HardwareAbstractionLayer;
import oshi.hardware.NetworkIF;
import oshi.software.os.FileSystem;
import oshi.software.os.OSFileStore;
import oshi.software.os.OperatingSystem;

import java.text.DecimalFormat;
import java.util.*;

@Service
public class SystemServiceImpl implements SystemService {

    @Autowired
    private CommentService commentService;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private FileService fileService;
    @Autowired
    private NoteService noteService;
    @Autowired
    private UserService userService;
    @Autowired
    private ArticalService articalService;

    public String getVal(double val){
        DecimalFormat df = new DecimalFormat("#.0");
        return df.format(val*100);
    }

    @Override
    public String getCpu() {
        SystemInfo s = new SystemInfo();
        HardwareAbstractionLayer h = s.getHardware();
        CentralProcessor processor = h.getProcessor();
        double cpuUsage = processor.getSystemCpuLoad();
        return getVal(cpuUsage);
    }

    @Override
    public String getDisk() {
        SystemInfo s = new SystemInfo();
        OperatingSystem os = s.getOperatingSystem();
        FileSystem f = os.getFileSystem();
        OSFileStore[] files = f.getFileStores();
        List<Double> val = getDiskVal(files);
        double used = val.get(0);
        double total = val.get(1);
        return getVal(used / total);
    }

    public List<Double> getDiskVal(OSFileStore[] files){
        List<Double> l = new ArrayList<>();
        double used = 0.0;
        double total = 0.0;
        if(files.length != 0){
            for (OSFileStore ff : files) {
                used += ff.getUsableSpace();
                total += ff.getTotalSpace();
            }
        }
        l.add(used);
        l.add(total);
        return l;
    }

    @Override
    public String getMem() {
        SystemInfo s = new SystemInfo();
        HardwareAbstractionLayer h = s.getHardware();
        GlobalMemory m = h.getMemory();
        double used = (double)m.getAvailable()/1024/1024;
        double total = (double)m.getTotal()/1024/1024;
        return getVal(1 - (used / total));
    }

    @Override
    public Map<String, Object> getHardware() {
        Map<String, Object> m = new HashMap<>();
        List<BaseInfo> baseInfo = new ArrayList<>();
        SystemInfo s = new SystemInfo();
        HardwareAbstractionLayer hardware = s.getHardware();
        GlobalMemory memory = hardware.getMemory();
        OperatingSystem os = s.getOperatingSystem();
        FileSystem system = os.getFileSystem();
        OSFileStore[] stores = system.getFileStores();
        CentralProcessor processor = hardware.getProcessor();
        NetworkIF[] fs = hardware.getNetworkIFs();
        baseInfo.add(new BaseInfo("Cpu",processor.getName(),
                        "Cpu频率",processor.getVendorFreq() / 1000 / 1000 + "MHZ"));
        baseInfo.add(new BaseInfo("系统架构",System.getProperty("os.arch"),
                "系统名称",System.getProperty("os.name")));
        baseInfo.add(new BaseInfo("硬盘容量",Math.ceil(getDiskVal(stores).get(1) / 1024 /1024 /1024)+"GB",
                "内存容量",memory.getTotal() / 1024 /1024 + "MB"));
        baseInfo.add(new BaseInfo("jdk",System.getProperty("java.version"),
                "jre",System.getProperty("java.specification.version")));
        m.put("baseInfo",baseInfo);
        List<BaseInfo> otherInfo = new ArrayList<>();
        otherInfo.add(new BaseInfo("评论总数",commentService.getAllCount()+"",
                "分类总数",categoryService.getAllCount()+""));
        otherInfo.add(new BaseInfo("文件总数",fileService.getAllCount()+"",
                "留言总数",noteService.getAllCount()+""));
        otherInfo.add(new BaseInfo("用户总数",userService.getTotalCount()+"",
                "文章总数",articalService.getAllCount()+""));
        otherInfo.add(new BaseInfo("阅读总数",articalService.getAllView()+"",
                "ip",Arrays.toString(fs[0].getIPv4addr())));
        m.put("otherInfo", otherInfo);
        return m;
    }

    @Override
    public Map<String, Object> article() {
        return articalService.getCategoryCount();
    }

}
